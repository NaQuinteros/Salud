package controller.UIContador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import dto.CoberturaDTO;
import dto.InternacionesDTO;
import dto.RenglonComprobantePractica;
import dto.MedicoDTO;
import dto.ObraSocialDTO;
import dto.PracticaDTO;
import dto.TurnoDTO;
import modelo.Admin_Cobertura;
import modelo.Admin_Internaciones;
import modelo.Admin_Medico;
import modelo.Admin_Turno;
import presentacion.vista.UIContablePrincipal;
import presentacion.vista.UIObraSocial;
import reportes.ComprobantePracticaMedico;
import reportes.ComprobantePracticaObraSocial;

public class ControladorContablePrincipal {

	private Admin_Cobertura admin_obra;
	private Admin_Turno admin_turno;
	private List<ObraSocialDTO> listaOS = new ArrayList<ObraSocialDTO>();
	private List<MedicoDTO> listaMedicos = new ArrayList<MedicoDTO>();
	private UIContablePrincipal uiPrincipal = new UIContablePrincipal();
	private List<TurnoDTO> listaTurno;
	private Admin_Internaciones admin_internacion;
	private Admin_Medico admin_medico;
	private Date spinnerDate;
	private List<RenglonComprobantePractica> listaComprobante;
	private ComprobantePracticaObraSocial comprobanteOS;
	private ComprobantePracticaMedico comprobanteMed;
	private List<InternacionesDTO> listaInternaciones;
	private List<RenglonComprobantePractica> listaComprobanteOS;
	private List<RenglonComprobantePractica> listaComprobanteMedico;
	DecimalFormat df = new DecimalFormat(",##");
	SimpleDateFormat dt1 = new SimpleDateFormat("yyyyy-mm-dd");

	@SuppressWarnings("deprecation")
	public ControladorContablePrincipal() {
		listaComprobanteOS = new ArrayList<RenglonComprobantePractica>();
		listaComprobanteMedico = new ArrayList<RenglonComprobantePractica>();
		listaComprobante = new ArrayList<RenglonComprobantePractica>();
		spinnerDate = (Date) uiPrincipal.getSpinner().getValue();
		admin_internacion = new Admin_Internaciones();
		listaInternaciones = admin_internacion.obtenerInternaciones();
		admin_medico = new Admin_Medico();
		admin_turno = new Admin_Turno();
		admin_obra = new Admin_Cobertura();
		listaMedicos = admin_medico.obtenerMedicos();
		listaOS = admin_obra.obtenerObras();
		listaTurno = admin_turno.obtenerTurnos();
		uiPrincipal.getComboBox().setSelectedIndex(spinnerDate.getMonth());
		inicializar();
	}

	public void inicializar() {
		uiPrincipal.getBtnImprimirMedico().setEnabled(false);
		uiPrincipal.getBtnImprimirOS().setEnabled(false);
		uiPrincipal.getComboBox().setSelectedIndex(5);
		llenarPracticasDeTurno();
		llenarPracticasDeInternacion();
		agregarTodasLasPracticas();
		llenarListaMedicos();
		llenarListaOS();
		filtrarPorOS();
		filtrarPorMedico();
		btnBuscarPracticas();
		btnImprimirOS();
		btnImprimirMedico();
		menuObras();
		uiPrincipal.setVisible(true);
	}

	private void menuObras() {
		uiPrincipal.getMenuObras().addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				UIObraSocial ui = new UIObraSocial();
				try {
					ControladorObraSocial c = new ControladorObraSocial(ui);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				ui.setvisible(true);

			}
		});

	}

	private void btnImprimirMedico() {
		uiPrincipal.getBtnImprimirMedico().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				comprobanteMed = new ComprobantePracticaMedico(listaComprobanteMedico);
				comprobanteMed.mostrar();
			}

		});

	}

	private void btnImprimirOS() {
		uiPrincipal.getBtnImprimirOS().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comprobanteOS = new ComprobantePracticaObraSocial(listaComprobanteOS);
				comprobanteOS.mostrar();
			}

		});
	}

	private void btnBuscarPracticas() {
		uiPrincipal.getBtnBuscarPracticas().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiPrincipal.getTableMedicos().getSelectionModel().clearSelection();
				uiPrincipal.getTableOS().getSelectionModel().clearSelection();
				listaComprobante.clear();
				spinnerDate = (Date) uiPrincipal.getSpinner().getValue();
				llenarPracticasDeTurno();
				llenarPracticasDeInternacion();
				agregarTodasLasPracticas();
			}

		});
	}

	private void filtrarPorMedico() {
		uiPrincipal.getTableMedicos().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (uiPrincipal.getTableMedicos().getSelectionModel().getValueIsAdjusting()) {
					uiPrincipal.getTableOS().getSelectionModel().clearSelection();
					listaComprobanteMedico.clear();
					TableColumnModel columnModel2 = uiPrincipal.getTablePracticas().getColumnModel();
					TableColumn column2 = columnModel2.getColumn(7);
					column2.setMaxWidth(1000000);
					column2.setMinWidth(50);
					column2.setPreferredWidth(50);
					TableColumn column341 = columnModel2.getColumn(8);
					column341.setMaxWidth(1000000);
					column341.setMinWidth(75);
					column341.setPreferredWidth(75);
					uiPrincipal.getLblPracticas().setText("Prácticas del Médico: " + uiPrincipal.getTableMedicos()
							.getValueAt(uiPrincipal.getTableMedicos().getSelectedRow(), 1));
					uiPrincipal.getBtnImprimirMedico().setEnabled(true);
					uiPrincipal.getBtnImprimirOS().setEnabled(false);
					uiPrincipal.getModelPracticas().setRowCount(0);
					uiPrincipal.getModelPracticas().setColumnCount(0);
					uiPrincipal.getModelPracticas().setColumnIdentifiers(uiPrincipal.getColumnasPracticas());

					for (RenglonComprobantePractica renglon : listaComprobante) {
						if (renglon.getIdMedico() == (int) uiPrincipal.getTableMedicos()
								.getValueAt(uiPrincipal.getTableMedicos().getSelectedRow(), 0)) {
							Object[] fila = { renglon.getIdMedico(), renglon.getIdOS(), renglon.getCodigo(),
									renglon.getDescripcion(), renglon.getFechaAut(), renglon.getNombreMedico(),
									renglon.getNombrePaciente(), renglon.getNombreOS() + " " + renglon.getNombrePlan(),
									round(renglon.getMontoTotal()), round(renglon.getMontoCubierto()) };
							uiPrincipal.getModelPracticas().addRow(fila);
							listaComprobanteMedico.add(renglon);
						}
					}
					if (listaComprobanteMedico.isEmpty())
						uiPrincipal.getBtnImprimirMedico().setEnabled(false);
					else
						uiPrincipal.getBtnImprimirMedico().setEnabled(true);
					TableColumnModel columnModel = uiPrincipal.getTablePracticas().getColumnModel();
					TableColumn column = columnModel.getColumn(5);
					column.setMaxWidth(0);
					column.setMinWidth(0);
					column.setPreferredWidth(0);
					TableColumnModel columnModel23 = uiPrincipal.getTablePracticas().getColumnModel();
					TableColumn column23 = columnModel23.getColumn(9);
					column23.setMaxWidth(0);
					column23.setMinWidth(0);
					column23.setPreferredWidth(0);
				}

			}
		});
	}

	private void filtrarPorOS() {
		uiPrincipal.getTableOS().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (uiPrincipal.getTableOS().getSelectionModel().getValueIsAdjusting()) {
					uiPrincipal.getTableMedicos().getSelectionModel().clearSelection();
					listaComprobanteOS.clear();
					uiPrincipal.getLblPracticas().setText("Practicas de la OS: "
							+ uiPrincipal.getTableOS().getValueAt(uiPrincipal.getTableOS().getSelectedRow(), 1));
					uiPrincipal.getBtnImprimirMedico().setEnabled(false);
					uiPrincipal.getBtnImprimirOS().setEnabled(true);
					uiPrincipal.getTableMedicos().getSelectionModel().setSelectionInterval(-1, -1);
					TableColumnModel columnModel = uiPrincipal.getTablePracticas().getColumnModel();
					TableColumn column = columnModel.getColumn(5);
					column.setMaxWidth(1000000);
					column.setMinWidth(60);
					column.setPreferredWidth(60);
					TableColumn column341 = columnModel.getColumn(9);
					column341.setMaxWidth(1000000);
					column341.setMinWidth(75);
					column341.setPreferredWidth(75);
					uiPrincipal.getModelPracticas().setRowCount(0);
					uiPrincipal.getModelPracticas().setColumnCount(0);
					uiPrincipal.getModelPracticas().setColumnIdentifiers(uiPrincipal.getColumnasPracticas());

					for (RenglonComprobantePractica renglon : listaComprobante) {
						if (renglon.getIdOS() != null && renglon.getIdOS() == (int) uiPrincipal.getTableOS()
								.getValueAt(uiPrincipal.getTableOS().getSelectedRow(), 0)) {
							Object[] fila = { renglon.getIdMedico(), renglon.getIdOS(), renglon.getCodigo(),
									renglon.getDescripcion(), renglon.getFechaAut(), renglon.getNombreMedico(),
									renglon.getNombrePaciente(), renglon.getNombreOS() + " " + renglon.getNombrePlan(),
									round(renglon.getMontoTotal()), round(renglon.getMontoCubierto()) };
							uiPrincipal.getModelPracticas().addRow(fila);
							listaComprobanteOS.add(renglon);
						}
					}
				}

				if (listaComprobanteOS.isEmpty())
					uiPrincipal.getBtnImprimirOS().setEnabled(false);
				else
					uiPrincipal.getBtnImprimirOS().setEnabled(true);
				TableColumnModel columnModel1 = uiPrincipal.getTablePracticas().getColumnModel();
				TableColumn column1 = columnModel1.getColumn(7);
				column1.setMaxWidth(0);
				column1.setMinWidth(0);
				column1.setPreferredWidth(0);
				TableColumnModel columnModel13 = uiPrincipal.getTablePracticas().getColumnModel();
				TableColumn column13 = columnModel13.getColumn(8);
				column13.setMaxWidth(0);
				column13.setMinWidth(0);
				column13.setPreferredWidth(0);

			}
		});

	}

	// @SuppressWarnings({ "deprecation", "unused" })
	// private void llenarListaPracticas() {
	// TableColumnModel columnModel3 =
	// uiPrincipal.getTablePracticas().getColumnModel();
	// TableColumn column3 = columnModel3.getColumn(7);
	// column3.setMaxWidth(1000000);
	// column3.setMinWidth(75);
	// column3.setPreferredWidth(75);
	// TableColumnModel columnModel4 =
	// uiPrincipal.getTablePracticas().getColumnModel();
	// TableColumn column4 = columnModel4.getColumn(5);
	// column4.setMaxWidth(1000000);
	// column4.setMinWidth(75);
	// column4.setPreferredWidth(75);
	// TableColumnModel columnModel34 =
	// uiPrincipal.getTablePracticas().getColumnModel();
	// TableColumn column34 = columnModel34.getColumn(9);
	// column34.setMaxWidth(1000000);
	// column34.setMinWidth(75);
	// column34.setPreferredWidth(75);
	// TableColumnModel columnModel341 =
	// uiPrincipal.getTablePracticas().getColumnModel();
	// TableColumn column341 = columnModel341.getColumn(8);
	// column341.setMaxWidth(1000000);
	// column341.setMinWidth(75);
	// column341.setPreferredWidth(75);
	// uiPrincipal.getModelPracticas().setRowCount(0);
	// uiPrincipal.getModelPracticas().setColumnCount(0);
	// uiPrincipal.getModelPracticas().setColumnIdentifiers(uiPrincipal.getColumnasPracticas());
	// for (TurnoDTO aux : listaTurno) {
	// String idObra = "";
	// String nombreObra = "Particular";
	// String nombrePlan = "";
	// if (aux.getPaciente() != null &&
	// aux.getEstado().equals(TurnoDTO.Estado.TOMADO)
	// && aux.getFecha().getMonth() ==
	// uiPrincipal.getComboBox().getSelectedIndex()
	// && aux.getFecha().getYear() + 1900 == spinnerDate.getYear() + 1900) {
	//
	// if (aux.getPaciente().getObraSocial() != null) {
	// idObra =
	// String.valueOf(aux.getPaciente().getObraSocial().getIdObraSocial());
	// nombreObra = aux.getPaciente().getObraSocial().getNombreObraSocial();
	// nombrePlan = aux.getPaciente().getPlan().getNombrePlan();
	// }
	//
	// List<PracticaDTO> listaPracticas =
	// admin_turno.obtenerPracticasDelTurno(aux);
	// if (listaPracticas.size() > 0)
	// for (int i = 0; i < listaPracticas.size(); i++) {
	// PracticaDTO PracticaAux = listaPracticas.get(i);
	// if
	// (PracticaAux.getAutorizacion().equals(PracticaDTO.Autorizacion.AUTORIZADA))
	// {
	// int porcentaje = 0;
	// for (CoberturaDTO c : aux.getPaciente().getPlan().getCobertura())
	// if (c.getCodPractica() == PracticaAux.getCodPractica())
	// porcentaje = c.getPorcentaje();
	// Object[] fila = { aux.getMedico().get_idMedico(), idObra,
	// PracticaAux.getCodPractica(),
	// PracticaAux.getDescripcionPractica(), PracticaAux.getFechaAutorizacion(),
	// aux.getMedico().get_nombre(),
	// aux.getPaciente().getNombre() + aux.getPaciente().getApellido(),
	// nombreObra + nombrePlan, PracticaAux.getHonorarios(),
	// (porcentaje * Double.parseDouble(PracticaAux.getHonorarios())) / 100 };
	// uiPrincipal.getModelPracticas().addRow(fila);
	// }
	// }
	// }
	// }
	//
	// }

	@SuppressWarnings("deprecation")
	private void agregarTodasLasPracticas() {
		TableColumnModel columnModel = uiPrincipal.getTablePracticas().getColumnModel();
		TableColumn column3 = columnModel.getColumn(7);
		column3.setMaxWidth(75);
		column3.setMinWidth(75);
		column3.setPreferredWidth(75);
		TableColumn column4 = columnModel.getColumn(5);
		column4.setMaxWidth(1000000);
		column4.setMinWidth(75);
		column4.setPreferredWidth(75);
		TableColumn column34 = columnModel.getColumn(9);
		column34.setMaxWidth(1000000);
		column34.setMinWidth(75);
		column34.setPreferredWidth(75);
		TableColumn column341 = columnModel.getColumn(8);
		column341.setMaxWidth(1000000);
		column341.setMinWidth(75);
		column341.setPreferredWidth(75);
		uiPrincipal.getBtnImprimirMedico().setEnabled(false);
		uiPrincipal.getBtnImprimirOS().setEnabled(false);
		uiPrincipal.getModelPracticas().setRowCount(0);
		uiPrincipal.getModelPracticas().setColumnCount(0);
		uiPrincipal.getModelPracticas().setColumnIdentifiers(uiPrincipal.getColumnasPracticas());
		uiPrincipal.getLblPracticas().setText(
				"Prácticas de " + uiPrincipal.getComboBox().getSelectedItem() + " " + (spinnerDate.getYear() + 1900));
		for (RenglonComprobantePractica renglon : listaComprobante) {
			int porcentaje = 0;
			if (renglon.getPaciente().getPlan() != null)
				for (CoberturaDTO c : renglon.getPaciente().getPlan().getCobertura())
					if (c.getCodPractica() == renglon.getCodigo())
						porcentaje = c.getPorcentaje();
			String idOS = "";
			if (renglon.getIdOS() != null)
				idOS = renglon.getIdOS().toString();
			Object[] fila = { renglon.getIdMedico(), idOS, renglon.getCodigo(), renglon.getDescripcion(),
					renglon.getFechaAut(), renglon.getNombreMedico(), renglon.getNombrePaciente(),
					renglon.getNombreOS() + renglon.getNombrePlan(),round(renglon.getMontoTotal()),
					round((porcentaje * renglon.getMontoTotal()) / 100) };
			uiPrincipal.getModelPracticas().addRow(fila);
		}
	}

	private void llenarListaOS() {
		uiPrincipal.getModelOS().setRowCount(0);
		uiPrincipal.getModelOS().setColumnCount(0);
		uiPrincipal.getModelOS().setColumnIdentifiers(uiPrincipal.getColumnasOS());

		for (ObraSocialDTO obra : listaOS) {
			int practicas = 0;
			double montoTotal = 0;
			for (int i = 0; i < uiPrincipal.getModelPracticas().getRowCount(); i++) {
				if (uiPrincipal.getModelPracticas().getValueAt(i, 1) != "" && obra.getIdObraSocial() == Integer
						.parseInt((String) uiPrincipal.getModelPracticas().getValueAt(i, 1))) {
					montoTotal += (double) uiPrincipal.getTablePracticas().getValueAt(i, 9);
					practicas++;
				}
			}
			Object[] fila = { obra.getIdObraSocial(), obra.getNombreObraSocial(), practicas, "$"+df.format(montoTotal) };
			uiPrincipal.getModelOS().addRow(fila);
		}
	}

	private void llenarListaMedicos() {

		uiPrincipal.getModelMedicos().setRowCount(0);
		uiPrincipal.getModelMedicos().setColumnCount(0);
		uiPrincipal.getModelMedicos().setColumnIdentifiers(uiPrincipal.getColumnasMedicos());

		for (MedicoDTO medico : listaMedicos) {
			int practicas = 0;
			double montoTotal = 0;
			for (int i = 0; i < uiPrincipal.getModelPracticas().getRowCount(); i++) {
				if (medico.get_idMedico() == (int) uiPrincipal.getModelPracticas().getValueAt(i, 0)) {
					montoTotal += (double) uiPrincipal.getTablePracticas().getValueAt(i, 8);
					practicas++;
				}
			}

			Object[] fila = { medico.get_idMedico(), medico.get_nombre(), medico.get_matricula(), practicas,
					"$"+df.format(montoTotal) };
			uiPrincipal.getModelMedicos().addRow(fila);
		}
	}

	@SuppressWarnings("deprecation")
	private void llenarPracticasDeTurno() {
		for (TurnoDTO aux : listaTurno) {
			String nombreObra = "Particular";
			String nombrePlan = "";
			Integer idObraSocial = null;
			if (aux.getPaciente() != null && aux.getEstado().equals(TurnoDTO.Estado.TOMADO)
					&& aux.getFecha().getMonth() == uiPrincipal.getComboBox().getSelectedIndex()
					&& (aux.getFecha().getYear() + 1900) == (spinnerDate.getYear() + 1900)) {

				if (aux.getPaciente().getObraSocial() != null) {
					nombreObra = aux.getPaciente().getObraSocial().getNombreObraSocial();
					nombrePlan = aux.getPaciente().getPlan().getNombrePlan();
					idObraSocial = aux.getPaciente().getObraSocial().getIdObraSocial();
				}

				List<PracticaDTO> listaPracticas = admin_turno.obtenerPracticasDelTurno(aux);
				if (listaPracticas.size() > 0)
					for (int i = 0; i < listaPracticas.size(); i++) {
						PracticaDTO PracticaAux = listaPracticas.get(i);
						if (PracticaAux.getAutorizacion().equals(PracticaDTO.Autorizacion.AUTORIZADA)) {
							int porcentaje = 0;
							if (aux.getPaciente().getPlan() != null)
								for (CoberturaDTO c : aux.getPaciente().getPlan().getCobertura())
									if (c.getCodPractica() == PracticaAux.getCodPractica())
										porcentaje = c.getPorcentaje();
							RenglonComprobantePractica compAux = new RenglonComprobantePractica(idObraSocial,
									aux.getMedico().get_idMedico(), PracticaAux.getCodPractica(),
									PracticaAux.getDescripcionPractica(), aux.getMedico().get_nombre(),
									aux.getPaciente().getNombre() + aux.getPaciente().getApellido(), nombreObra,
									nombrePlan,round( Double.parseDouble(PracticaAux.getHonorarios())),
									round(porcentaje * Double.parseDouble(PracticaAux.getHonorarios())) / 100,
									PracticaAux.getFechaAutorizacion(),
									(String) uiPrincipal.getComboBox().getSelectedItem() + " "
											+ (spinnerDate.getYear() + 1900),
									aux.getPaciente());
							listaComprobante.add(compAux);

						}
					}
			}
		}

	}

	@SuppressWarnings("deprecation")
	public void llenarPracticasDeInternacion() {
		for (InternacionesDTO internacion : listaInternaciones) {
			String nombreObra = "Particular";
			String nombrePlan = "";
			Integer idObraSocial = null;
			List<PracticaDTO> listaPracticas = admin_internacion.obtenerPracticasDeInternacion(internacion);
			if (internacion.getPaciente().getObraSocial() != null) {
				nombreObra = internacion.getPaciente().getObraSocial().getNombreObraSocial();
				nombrePlan = internacion.getPaciente().getPlan().getNombrePlan();
				idObraSocial = internacion.getPaciente().getObraSocial().getIdObraSocial();
			}

			if (listaPracticas.size() > 0)
				for (int i = 0; i < listaPracticas.size(); i++) {
					PracticaDTO PracticaAux = listaPracticas.get(i);
					if (PracticaAux.getFechaAutorizacion() != null
							&& PracticaAux.getAutorizacion().equals(PracticaDTO.Autorizacion.AUTORIZADA)
							&& PracticaAux.getFechaAutorizacion().getMonth() == uiPrincipal.getComboBox()
									.getSelectedIndex()
							&& PracticaAux.getFechaAutorizacion().getYear() + 1900 == (spinnerDate.getYear() + 1900)) {
						int porcentaje = 0;
						if (internacion.getPaciente().getPlan() != null)
							for (CoberturaDTO c : internacion.getPaciente().getPlan().getCobertura())
								if (c.getCodPractica() == PracticaAux.getCodPractica())
									porcentaje = c.getPorcentaje();
						MedicoDTO medico = admin_internacion.obtenerMedicoDePractica(internacion, PracticaAux);
						RenglonComprobantePractica compAux = new RenglonComprobantePractica(idObraSocial,
								medico.get_idMedico(), PracticaAux.getCodPractica(),
								PracticaAux.getDescripcionPractica(), medico.get_nombre(),
								internacion.getPaciente().getNombre() + internacion.getPaciente().getApellido(),
								nombreObra, nombrePlan, Double.parseDouble(PracticaAux.getHonorarios()),
								(porcentaje * Double.parseDouble(PracticaAux.getHonorarios())) / 100,
								PracticaAux.getFechaAutorizacion(), (String) uiPrincipal.getComboBox().getSelectedItem()
										+ " " + (spinnerDate.getYear() + 1900),
								internacion.getPaciente());
						listaComprobante.add(compAux);

					}
				}

		}

	}
	public  double round(double value) {
	    long factor = (long) Math.pow(10, 2);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
