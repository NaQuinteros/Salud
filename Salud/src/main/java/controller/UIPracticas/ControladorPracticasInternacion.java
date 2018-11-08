package controller.UIPracticas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dto.CoberturaDTO;
import dto.FacturaDTO;
import dto.InternacionesDTO;
import dto.MedicoDTO;
import dto.PlanDTO;
import dto.PracticaDTO;
import modelo.Admin_Internaciones;
import modelo.Admin_Medico;
import modelo.Admin_Paciente;
import modelo.Admin_Practica;
import presentacion.vista.UIPracticas;
import presentacion.vista.UIPracticasInternacion;
import presentacion.vista._ConsultarPractica;
import reportes.Factura;

public class ControladorPracticasInternacion implements _ConsultarPractica {
	private UIPracticasInternacion uiPracticas;
	private InternacionesDTO internacion;
	private List<PracticaDTO> practicas;
	private Admin_Internaciones admin_internaciones;
	private Admin_Practica admin_practica;
	private Admin_Paciente admin_paciente;
	private Admin_Medico admin_medico;

	public ControladorPracticasInternacion(UIPracticasInternacion uiPracticas, InternacionesDTO internacion)
			throws IOException {
		this.uiPracticas = uiPracticas;
		practicas = new ArrayList<>();
		this.admin_internaciones = new Admin_Internaciones();
		this.admin_paciente = new Admin_Paciente();
		this.admin_medico = new Admin_Medico();
		this.internacion = internacion;

		this.practicas = new ArrayList<>();
		this.admin_practica = new Admin_Practica();
		inicializar();
	}

	private void inicializar() {
		uiPracticas.setvisible(true);
		llenarTablaPracticas();
		botonAgregarPractica();
		botonAutorizar();
		botonBorrar();
		botonPagar();
		llenarComboMedico();
		comboMedico();
		cambioDePractica();
		if (internacion != null)
			uiPracticas.getLblTurno().setText("");
		else
			uiPracticas.getLblTurno().setText("Error: No hay ninguna internación seleccionada.");
	}

	private void comboMedico() {
		this.uiPracticas.getComboBox().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiPracticas.getBtnAgregar().setEnabled(uiPracticas.getComboBox().getSelectedIndex() > -1);
			}
		});
	}

	private void llenarComboMedico() {
		List<MedicoDTO> medicos = admin_medico.obtenerMedicos();
		uiPracticas.getComboBox().removeAllItems();
		for (MedicoDTO m : medicos)
			uiPracticas.getComboBox().addItem(m);
		uiPracticas.getComboBox().setSelectedIndex(-1);
	}

	private void botonAutorizar() {
		this.uiPracticas.getBtnAutorizar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int reply = 0;
				int fila = uiPracticas.getTablaPracticas().getSelectedRow();
				if (fila > -1)
					if (uiPracticas.getTablaPracticas().getModel().getValueAt(fila, 4)
							.equals(PracticaDTO.Autorizacion.REQUIERE_AUTORIZACION))
						if (uiPracticas.getDateChooser().getDate() != null
								&& uiPracticas.getDateChooser().getDate().getTime() <= System.currentTimeMillis())
							if (!uiPracticas.getTxtCodigo().getText().equals("")) {
								if (!uiPracticas.getTablaPracticas().getModel().getValueAt(fila, 3).equals("$0.0"))
									reply = JOptionPane.showConfirmDialog(null,
											"Está a punto de autorizar una práctica que no está totalmente cubierta. El paciente deberá abonar la suma de "
													+ uiPracticas.getTablaPracticas().getModel().getValueAt(fila, 3)
													+ " \n¿Continuar?",
											"Autorizar Práctica", JOptionPane.YES_NO_OPTION);
								if (reply == JOptionPane.YES_OPTION) {
									internacion.getPracticas().get(fila).autorizar(
											new Date(uiPracticas.getDateChooser().getDate().getTime()),
											Integer.parseInt(uiPracticas.getTxtCodigo().getText()));
									admin_internaciones.autorizarPracticaEnInternacion(
											internacion.getPracticas().get(fila), internacion);
									asignarSesiones(internacion.getPracticas().get(fila),
											(Integer) uiPracticas.getSpSesionesAutorizacion().getValue());
									llenarTablaPracticas();
									JOptionPane.showMessageDialog(null, "Practica autorizada exitosamente.");
									imprimirFactura(internacion.getPracticas().get(fila),
											calcularDiferencial(internacion.getPracticas().get(fila)), fila);
								} else {
								}
							} else
								JOptionPane.showMessageDialog(null, "Ingrese el código que figura en la autorización.");
						else
							JOptionPane.showMessageDialog(null,
									"La fecha ingresada no es válida. Ingrese la fecha que figura en la autorización.");
					else
						JOptionPane.showMessageDialog(null, "La práctica seleccionada no requiere una autorización.");
				else
					JOptionPane.showMessageDialog(null, "Elija una práctica.");
			}
		});
	}

	private void botonPagar() {
		uiPracticas.getBtnPagar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int fila = uiPracticas.getTablaPracticas().getSelectedRow();
				int reply = 0;
				if (fila > -1) {
					double arancel = Double.parseDouble(internacion.getPracticas().get(fila).getHonorarios());
					double costoFinal = calcularDiferencial(internacion.getPracticas().get(fila));
					Boolean pagoParticular = null;
					if (uiPracticas.getTablaPracticas().getModel().getValueAt(fila, 4)
							.equals(PracticaDTO.Autorizacion.REQUIERE_AUTORIZACION) || arancel == costoFinal) {
						pagoParticular = true;
						reply = JOptionPane
								.showConfirmDialog(null,
										"Está a punto de autorizar una práctica como particular. El paciente deberá abonar la suma de $"
												+ arancel + " \n¿Continuar?",
										"Autorizar Práctica", JOptionPane.YES_NO_OPTION);
					} else if (uiPracticas.getTablaPracticas().getModel().getValueAt(fila, 4)
							.equals(PracticaDTO.Autorizacion.REQUIERE_PAGO) && arancel != costoFinal) {

						pagoParticular = false;
						reply = JOptionPane.showConfirmDialog(null,
								"Está a punto de autorizar una práctica con diferencial. El paciente deberá abonar la suma de $"
										+ costoFinal + " \n¿Continuar?",
								"Autorizar Práctica", JOptionPane.YES_NO_OPTION);
					}
					if (reply == JOptionPane.YES_OPTION) {
						internacion.getPracticas().get(fila).pagar(new Date(System.currentTimeMillis()));
						admin_internaciones.autorizarPracticaEnInternacion(internacion.getPracticas().get(fila),
								internacion);
						asignarSesiones(internacion.getPracticas().get(fila),
								(Integer) uiPracticas.getSpSesionesParticular().getValue());
						llenarTablaPracticas();
						JOptionPane.showMessageDialog(null,
								"La práctica se ha marcado como abonada. A continuación podrá imprimir la factura.");
						if (pagoParticular)
							imprimirFactura(internacion.getPracticas().get(fila), arancel, fila);
						else
							imprimirFactura(internacion.getPracticas().get(fila), costoFinal, fila);
					} else {
					}
				} else
					JOptionPane.showMessageDialog(null, "Elija una práctica.");
			}
		});
	}

	private void botonBorrar() {
		this.uiPracticas.getBtnBorrar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int fila = uiPracticas.getTablaPracticas().getSelectedRow();
				if (fila > -1) {
					if (!admin_internaciones.borrarPracticaDeInternacion(internacion.getPracticas().get(fila),
							internacion))
						JOptionPane.showMessageDialog(null, "ERROR");
					internacion.getPracticas().remove(fila);
					llenarTablaPracticas();
				} else
					JOptionPane.showMessageDialog(null, "Elija una práctica.");
			}
		});
	}

	private void asignarSesiones(PracticaDTO practica, int sesiones) {
		if (sesiones > 0)
			this.admin_practica.asignarSesiones(admin_paciente.obtenerPacienteById(this.internacion.getIdPaciente()),
					practica, sesiones);
	}

	private Integer obtenerSesiones(PracticaDTO practica) {
		return this.admin_practica
				.obtenerSesiones(this.admin_paciente.obtenerPacienteById(this.internacion.getIdPaciente()), practica);
	}

	private void modificarSesiones(PracticaDTO practica, int sesiones) {
		this.admin_practica.modificarSesiones(this.admin_paciente.obtenerPacienteById(this.internacion.getIdPaciente()),
				practica, sesiones);
	}

	private void borrarSesiones(PracticaDTO practica) {
		this.admin_practica.borrarSesiones(this.admin_paciente.obtenerPacienteById(this.internacion.getIdPaciente()),
				practica);
	}

	@SuppressWarnings("unused")
	private List<Integer> obtenerPracticasConSesiones(PracticaDTO practica) {
		return this.admin_practica
				.obtenerPracticasConSesiones(this.admin_paciente.obtenerPacienteById(this.internacion.getIdPaciente()));
	}

	private void llenarTablaPracticas() {
		vaciarTablaPracticas();
		this.internacion.setPracticas(admin_internaciones.obtenerPracticasDeInternacion(this.internacion));
		List<PracticaDTO> practicasDelTurno = internacion.getPracticas();
		for (PracticaDTO p : practicasDelTurno) {
			String sesionesString;
			String codigo;
			Integer sesiones = obtenerSesiones(p);
			if (sesiones == null || sesiones == 0)
				sesionesString = "";
			else
				sesionesString = String.valueOf(sesiones);
			if (p.getCodigoAutorizacion() == null || p.getCodigoAutorizacion() == 0)
				codigo = "";
			else
				codigo = String.valueOf(p.getCodigoAutorizacion());
			Double monto = 0.0;
			try {
				monto = Double.parseDouble(p.getHonorarios());
			} catch (Exception e) {

			} finally {
			Object[] fila = { p.getCodPractica(), p.getDescripcionPractica(),
					"$" + monto, "$" + calcularDiferencial(p), getAutorizacion(p),
					codigo, p.getFechaAutorizacion(), sesionesString,
					admin_internaciones.obtenerMedicoDePractica(this.internacion, p) };
			uiPracticas.getModelPracticas().addRow(fila);
			}
		}
	}

	private PracticaDTO.Autorizacion getAutorizacion(PracticaDTO p) {

		if (p.getAutorizacion() != null)
			return p.getAutorizacion();
		else {
			if (internacion.getPaciente().getPlan() != null)
				for (CoberturaDTO c : admin_paciente.obtenerPacienteById(this.internacion.getIdPaciente()).getPlan()
						.getCobertura())
					if (c.getCodPractica() == p.getCodPractica()) {
						if (c.requiereAutorizacion()) {
							p.setAutorizacion(PracticaDTO.Autorizacion.REQUIERE_AUTORIZACION);
							return p.getAutorizacion();
						} else
							break;
					}
			p.setAutorizacion(PracticaDTO.Autorizacion.REQUIERE_PAGO);
			return p.getAutorizacion();
		}
	}

	private double calcularDiferencial(PracticaDTO p) {
		DecimalFormat df2 = new DecimalFormat(",##");
		double precio = Double.parseDouble(p.getHonorarios());
		PlanDTO plan = this.admin_paciente.obtenerPacienteById(this.internacion.getIdPaciente()).getPlan();
		if (plan == null || plan.getCobertura() == null || plan.getCobertura().isEmpty())
			return precio;
		if (p.getHonorarios().equals(""))
			return 0;
		for (CoberturaDTO c : plan.getCobertura())
			if (c.getCodPractica() == p.getCodPractica())
				return precio - precio * c.getPorcentaje() / 100;
		return Double.parseDouble(df2.format(precio));
	}

	private boolean requiereAutorizacion(PracticaDTO p) {
		PlanDTO plan = this.admin_paciente.obtenerPacienteById(this.internacion.getIdPaciente()).getPlan();
		for (CoberturaDTO c : plan.getCobertura())
			if (c.getCodPractica() == p.getCodPractica())
				return c.requiereAutorizacion();
		return false;
	}

	private void vaciarTablaPracticas() {
		this.uiPracticas.getModelPracticas().setRowCount(0);
	}

	private void imprimirFactura(PracticaDTO p, double monto, int fila) {
		FacturaDTO factura = new FacturaDTO(monto, p,
				this.admin_paciente.obtenerPacienteById(this.internacion.getIdPaciente()),
				(MedicoDTO) uiPracticas.getTablaPracticas().getValueAt(fila, 8));// TODO
		Factura lista = new Factura(factura);
		lista.mostrar();
	}

	@Override
	public void entregarPracticas(List<PracticaDTO> practicasRecibidas) {
		for (PracticaDTO p : practicasRecibidas) {
			if (!internacion.getPracticas().contains(p)) {
				if (calcularDiferencial(p) == 0.0 && !requiereAutorizacion(p))
					p.pagar(null);
				Integer sesiones = obtenerSesiones(p);
				// Si hay sesiones pendientes, me fijo en todos los
				// turnos para encontrar la autorización de esta
				// práctica
				if (sesiones != null && sesiones > 0) {
					if (sesiones == 1)
						borrarSesiones(p);
					else {
						modificarSesiones(p, sesiones - 1);
						List<InternacionesDTO> turnos = admin_internaciones.obtenerInternaciones();
						for (InternacionesDTO t : turnos)
							if (t.getIdPaciente() == internacion.getIdPaciente())
								for (PracticaDTO p2 : t.getPracticas())
									if (p2.getCodPractica() == p.getCodPractica())
										p.autorizar(p2.getFechaAutorizacion(), p2.getCodigoAutorizacion());
					}
				}
				internacion.getPracticas().add(p);
				admin_internaciones.agregarPracticaEnInternacion(
						internacion.getPracticas().get(internacion.getPracticas().size() - 1), internacion,
						(MedicoDTO) uiPracticas.getComboBox().getSelectedItem());
				llenarTablaPracticas();
			} else
				JOptionPane.showMessageDialog(null,
						"El turno ya contiene algunas de las prácticas que se intentaron agregar.");
		}
	}

	private void cambioDePractica() {
		uiPracticas.getTablaPracticas().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int fila = uiPracticas.getTablaPracticas().getSelectedRow();
				if (fila > -1) {
					if (!uiPracticas.getTablaPracticas().getModel().getValueAt(fila, 4)
							.equals(PracticaDTO.Autorizacion.REQUIERE_PAGO))
						uiPracticas.getTabbedPane().setSelectedIndex(0);
					else
						uiPracticas.getTabbedPane().setSelectedIndex(1);
				}
			}
		});
	}

	public void abrirVentanaPracticas() {
		UIPracticas practica = new UIPracticas();
		ControllerUIPracticas controllerUI = new ControllerUIPracticas(practica, this);
		practicas.clear();
		controllerUI.agregarPracticas(practicas);
	}

	private void botonAgregarPractica() {
		this.uiPracticas.getBtnAgregar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirVentanaPracticas();
			}
		});
	}

}
