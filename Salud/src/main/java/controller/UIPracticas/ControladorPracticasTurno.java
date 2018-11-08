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
import dto.PlanDTO;
import dto.PracticaDTO;
import dto.TurnoDTO;
import modelo.Admin_Modulo;
import modelo.Admin_Practica;
import modelo.Admin_Turno;
import presentacion.vista.UIPracticas;
import presentacion.vista.UIPracticasTurno;
import presentacion.vista._ConsultarPractica;
import reportes.Factura;

public class ControladorPracticasTurno implements _ConsultarPractica {
	private UIPracticasTurno uiPracticas;
	private TurnoDTO turno;
	private List<PracticaDTO> practicas;
	private Admin_Turno admin_turno;
	private Admin_Practica admin_practica;

	public ControladorPracticasTurno(UIPracticasTurno uiPracticas, TurnoDTO turno) throws IOException {
		this.uiPracticas = uiPracticas;
		new ArrayList<>();
		this.admin_turno = new Admin_Turno();
		new Admin_Modulo();
		this.turno = turno;
		this.practicas = new ArrayList<>();
		this.admin_practica = new Admin_Practica();
		inicializar();
	}

	private void inicializar() {
		llenarTablaPracticas();
		botonAgregarPractica();
		botonAutorizar();
		botonBorrar();
		botonPagar();
		cambioDePractica();
		if (turno != null)
			uiPracticas.getLblTurno().setText(turno.toString());
		else
			uiPracticas.getLblTurno().setText("Error: No hay ningún turno seleccionado.");
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
									turno.getPracticas().get(fila).autorizar(
											new Date(uiPracticas.getDateChooser().getDate().getTime()),
											Integer.parseInt(uiPracticas.getTxtCodigo().getText()));
									admin_turno.autorizarPracticaEnTurno(turno.getPracticas().get(fila), turno);
									asignarSesiones(turno.getPracticas().get(fila),
											(Integer) uiPracticas.getSpSesionesAutorizacion().getValue());
									llenarTablaPracticas();
									JOptionPane.showMessageDialog(null, "Practica autorizada exitosamente.");
									imprimirFactura(turno.getPracticas().get(fila),
											calcularDiferencial(turno.getPracticas().get(fila)));
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
					double arancel = Double.parseDouble(turno.getPracticas().get(fila).getHonorarios());
					double costoFinal = calcularDiferencial(turno.getPracticas().get(fila));
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
						turno.getPracticas().get(fila).pagar(new Date(System.currentTimeMillis()));
						admin_turno.autorizarPracticaEnTurno(turno.getPracticas().get(fila), turno);
						asignarSesiones(turno.getPracticas().get(fila),
								(Integer) uiPracticas.getSpSesionesParticular().getValue());
						llenarTablaPracticas();
						JOptionPane.showMessageDialog(null,
								"La práctica se ha marcado como abonada. A continuación podrá imprimir la factura.");
						if (pagoParticular)
							imprimirFactura(turno.getPracticas().get(fila), arancel);
						else
							imprimirFactura(turno.getPracticas().get(fila), costoFinal);
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
					if (!admin_turno.borrarPracticaDelTurno(turno.getPracticas().get(fila), turno))
						JOptionPane.showMessageDialog(null, "ERROR");
					turno.getPracticas().remove(fila);
					llenarTablaPracticas();
				} else
					JOptionPane.showMessageDialog(null, "Elija una práctica.");
			}
		});
	}

	private void asignarSesiones(PracticaDTO practica, int sesiones) {
		if (sesiones > 0)
			this.admin_practica.asignarSesiones(this.turno.getPaciente(), practica, sesiones);
	}

	private Integer obtenerSesiones(PracticaDTO practica) {
		return this.admin_practica.obtenerSesiones(this.turno.getPaciente(), practica);
	}

	private void modificarSesiones(PracticaDTO practica, int sesiones) {
		this.admin_practica.modificarSesiones(this.turno.getPaciente(), practica, sesiones);
	}

	private void borrarSesiones(PracticaDTO practica) {
		this.admin_practica.borrarSesiones(this.turno.getPaciente(), practica);
	}

	@SuppressWarnings("unused")
	private List<Integer> obtenerPracticasConSesiones(PracticaDTO practica) {
		return this.admin_practica.obtenerPracticasConSesiones(this.turno.getPaciente());
	}

	private void llenarTablaPracticas() {
		vaciarTablaPracticas();
		List<PracticaDTO> practicasDelTurno = turno.getPracticas();
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
				Object[] fila = { p.getCodPractica(), p.getDescripcionPractica(), "$" + monto,
						"$" + calcularDiferencial(p), getAutorizacion(p), codigo, p.getFechaAutorizacion(),
						sesionesString };
				uiPracticas.getModelPracticas().addRow(fila);
			}
		}
	}

	private PracticaDTO.Autorizacion getAutorizacion(PracticaDTO p) {
		// if (turno.getPaciente().getPlan() == null ||
		// turno.getPaciente().getPlan().getCobertura() == null
		// || turno.getPaciente().getPlan().getCobertura().isEmpty())
		// return PracticaDTO.Autorizacion.REQUIERE_PAGO;
		if (p.getAutorizacion() != null)
			return p.getAutorizacion();
		else {
			if (turno.getPaciente().getPlan() != null)
				for (CoberturaDTO c : turno.getPaciente().getPlan().getCobertura())
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
		Double precio = Double.parseDouble(p.getHonorarios());
		PlanDTO plan = this.turno.getPaciente().getPlan();
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
		PlanDTO plan = this.turno.getPaciente().getPlan();
		for (CoberturaDTO c : plan.getCobertura())
			if (c.getCodPractica() == p.getCodPractica())
				return c.requiereAutorizacion();
		return false;
	}

	private void vaciarTablaPracticas() {
		this.uiPracticas.getModelPracticas().setRowCount(0);
	}

	private void imprimirFactura(PracticaDTO p, double monto) {
		FacturaDTO factura = new FacturaDTO(monto, p, this.turno.getPaciente(), this.turno.getMedico());
		Factura lista = new Factura(factura);
		lista.mostrar();
	}

	@Override
	public void entregarPracticas(List<PracticaDTO> practicasRecibidas) {
		for (PracticaDTO p : practicasRecibidas) {
			if (!turno.getPracticas().contains(p)) {
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
						List<TurnoDTO> turnos = admin_turno.obtenerTurnos();
						for (TurnoDTO t : turnos)
							if (t.getPaciente() != null
									&& t.getPaciente().getIdPaciente() == turno.getPaciente().getIdPaciente())
								for (PracticaDTO p2 : t.getPracticas())
									if (p2.getCodPractica() == p.getCodPractica())
										p.autorizar(p2.getFechaAutorizacion(), p2.getCodigoAutorizacion());
					}
				}
				turno.getPracticas().add(p);
				admin_turno.agregarPracticaEnTurno(turno.getPracticas().get(turno.getPracticas().size() - 1), turno);
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
