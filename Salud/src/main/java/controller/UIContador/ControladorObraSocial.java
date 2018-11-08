package controller.UIContador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.UIPracticas.ControllerUIPracticas;
import dto.CoberturaDTO;
import dto.ObraSocialDTO;
import dto.PacienteDTO;
import dto.PlanDTO;
import dto.PracticaDTO;
import modelo.Admin_Cobertura;
import modelo.Admin_Paciente;
import modelo.Admin_Practica;
import presentacion.vista.UIObraSocial;
import presentacion.vista.UIPracticas;
import presentacion.vista._ConsultarPractica;

public class ControladorObraSocial implements _ConsultarPractica {
	private Admin_Practica adminPractica;
	private Admin_Cobertura adminCobertura;
	private Admin_Paciente adminPaciente;
	private UIObraSocial ui;
	private List<ObraSocialDTO> obras;
	private List<PlanDTO> planes;
	private List<PacienteDTO> pacientes;
	private List<PracticaDTO> practicas;

	public ControladorObraSocial(UIObraSocial uiObraSocial) throws IOException {
		this.ui = uiObraSocial;
		this.adminCobertura = new Admin_Cobertura();
		this.adminPractica = new Admin_Practica();
		this.adminPaciente = new Admin_Paciente();
		inicializar();
	}

	private void inicializar() {
		llenarListaObras();
		cambiarObra();
		cambiarPlan();
		cambiarPractica();
		botonAgregarObra();
		botonBorrarObra();
		botonAgregarPlan();
		botonBorrarPlan();
		botonAgregarPractica();
		botonBorrarPractica();
		botonCobertura();
	}

	private void llenarListaObras() {
		ui.getModelObra().setRowCount(0);
		obras = adminCobertura.obtenerObras();
		for (ObraSocialDTO o : obras) {
			Object[] fila = { o.getNombreObraSocial(), o.getPlanes().size(), getPracticasObra(o), getPacientesObra(o) };
			ui.getModelObra().addRow(fila);
		}
	}

	private void llenarListaPlanes(ObraSocialDTO obra) {
		ui.getModelPlan().setRowCount(0);
		planes = obra.getPlanes();
		for (PlanDTO p : planes) {
			Object[] fila = { p.getNombrePlan(), p.getCobertura().size(), getPacientesPlan(p, obra) };
			ui.getModelPlan().addRow(fila);
		}
	}

	private void llenarListaPracticas(PlanDTO plan) {
		DecimalFormat df2 = new DecimalFormat(".##");
		ui.getModelPracticas().setRowCount(0);
		practicas = adminPractica.obtenerPracticas();
		String autorizacion;
		for (CoberturaDTO c : plan.getCobertura()) {
			for (PracticaDTO p : practicas)
				if (p.getCodPractica() == c.getCodPractica()) {
					if (c.requiereAutorizacion())
						autorizacion = "Requiere";
					else
						autorizacion = "No Requiere";
					Object[] fila = { p.getCodPractica(), p.getDescripcionPractica(), "$" + p.getHonorarios(),
							c.getPorcentaje() + "%",
							"$" + df2.format(Double.parseDouble(p.getHonorarios())
									- Double.parseDouble(p.getHonorarios()) * c.getPorcentaje() / 100.0),
							autorizacion };
					ui.getModelPracticas().addRow(fila);
				}
		}
	}

	private int getPacientesObra(ObraSocialDTO o) {
		pacientes = adminPaciente.obtenerPacientes();
		Set<PacienteDTO> ret = new HashSet<>();
		for (PacienteDTO p : pacientes) {
			if (p.getObraSocial() != null && p.getObraSocial().equals(o))
				ret.add(p);
		}
		return ret.size();
	}

	private int getPacientesPlan(PlanDTO plan, ObraSocialDTO obra) {
		pacientes = adminPaciente.obtenerPacientes();
		Set<PacienteDTO> ret = new HashSet<>();
		for (PacienteDTO p : pacientes) {
			if (p.getObraSocial() != null && p.getObraSocial().equals(obra) && p.getPlan() != null
					&& p.getPlan().equals(plan))
				ret.add(p);
		}
		return ret.size();
	}

	private int getPracticasObra(ObraSocialDTO o) {
		Set<Integer> ret = new HashSet<>();
		for (PlanDTO plan : o.getPlanes())
			for (CoberturaDTO cobertura : plan.getCobertura())
				ret.add(cobertura.getCodPractica());
		return ret.size();
	}

	private void cambiarObra() {
		ui.getTablaObras().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (ui.getTablaObras().getSelectedRow() > -1) {
					llenarListaPlanes(adminCobertura.obtenerObras().get(ui.getTablaObras().getSelectedRow()));
				} else
					ui.getModelPlan().setRowCount(0);
				ui.getBtnAgregarPlan().setEnabled(ui.getTablaObras().getSelectedRow() > -1);
				ui.getBtnBorrarObraSocial().setEnabled(ui.getTablaObras().getSelectedRow() > -1);
			}
		});
	}

	private void cambiarPlan() {
		ui.getTablaPlanes().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (ui.getTablaPlanes().getSelectedRow() > -1) {
					llenarListaPracticas(planSeleccionado());
				} else
					ui.getModelPracticas().setRowCount(0);
				ui.getBtnBorrarPlan().setEnabled(ui.getTablaPlanes().getSelectedRow() > -1);
				ui.getBtnAgregarPractica().setEnabled(ui.getTablaPlanes().getSelectedRow() > -1);
				ui.getChckbxAutorizacion().setEnabled(ui.getTablaPlanes().getSelectedRow() > -1);
				ui.getSpCobertura().setEnabled(ui.getTablaPlanes().getSelectedRow() > -1);
			}
		});
	}

	private void cambiarPractica() {
		ui.getTablaPracticas().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (ui.getTablaPracticas().getSelectedRow() > -1) {
					mostrarCobertura();
				}
				ui.getBtnBorrarPractica().setEnabled(ui.getTablaPracticas().getSelectedRow() > -1);
				ui.getSpCobertura().setEnabled(ui.getTablaPracticas().getSelectedRow() > -1);
				ui.getBtnCobertura().setEnabled(ui.getTablaPracticas().getSelectedRow() > -1);
				ui.getChckbxAutorizacion().setEnabled(ui.getTablaPracticas().getSelectedRow() > -1);
			}
		});
	}

	private void mostrarCobertura() {
		ui.getSpCobertura().setValue(coberturaSeleccionada().getPorcentaje());
		ui.getChckbxAutorizacion().setSelected(coberturaSeleccionada().requiereAutorizacion());
	}

	private ObraSocialDTO obraSeleccionada() {
		return adminCobertura.obtenerObras().get(ui.getTablaObras().getSelectedRow());
	}

	private PlanDTO planSeleccionado() {
		return obraSeleccionada().getPlanes().get(ui.getTablaPlanes().getSelectedRow());
	}

	private CoberturaDTO coberturaSeleccionada() {
		return planSeleccionado().getCobertura().get(ui.getTablaPracticas().getSelectedRow());
	}

	private PracticaDTO practicaSeleccionada() {
		practicas = adminPractica.obtenerPracticas();
		int codigo = (int) ui.getTablaPracticas().getValueAt(ui.getTablaPracticas().getSelectedRow(), 0);
		for (PracticaDTO p : practicas)
			if (p.getCodPractica() == codigo)
				return p;
		throw new RuntimeException();
	}

	private void botonAgregarObra() {
		ui.getBtnAgregarObraSocial().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newname = JOptionPane.showInputDialog(null, "Ingrese el nombre de la Obra Social nueva",
						"Agregar Obra Social", JOptionPane.QUESTION_MESSAGE);
				ObraSocialDTO nueva = new ObraSocialDTO(newname);
				obras = adminCobertura.obtenerObras();
				if (obras.contains(nueva))
					JOptionPane.showMessageDialog(null, "Ya existe una Obra Social con ese nombre.");
				else if (adminCobertura.agregarObra(nueva))
					JOptionPane.showMessageDialog(null, "Se agregó la Obra Social exitosamente.");
				llenarListaObras();
			}
		});
	}

	private void botonBorrarObra() {
		ui.getBtnBorrarObraSocial().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (adminCobertura.borrarObra(obraSeleccionada()))
					JOptionPane.showMessageDialog(null, "Se borró la Obra Social exitosamente.");
				llenarListaObras();
			}
		});
	}

	private void botonAgregarPlan() {
		ui.getBtnAgregarPlan().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newname = JOptionPane.showInputDialog(null, "Ingrese el nombre del Plan nuevo", "Agregar Plan",
						JOptionPane.QUESTION_MESSAGE);
				PlanDTO nuevo = new PlanDTO(newname);
				planes = obraSeleccionada().getPlanes();
				if (planes.contains(nuevo))
					JOptionPane.showMessageDialog(null, "La Obra Social seleccionada ya tiene un Plan con ese nombre.");
				else if (adminCobertura.agregarPlan(nuevo, obraSeleccionada())) {
					JOptionPane.showMessageDialog(null, "Se agregó el Plan exitosamente.");
				}
				int row = ui.getTablaObras().getSelectedRow();
				llenarListaObras();
				ui.getTablaObras().setRowSelectionInterval(row, row);
				llenarListaPlanes(obraSeleccionada());
			}
		});
	}

	private void botonBorrarPlan() {
		ui.getBtnBorrarPlan().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (adminCobertura.borrarPlan(planSeleccionado()))
					JOptionPane.showMessageDialog(null, "Se borró el Plan exitosamente.");
				llenarListaObras();
			}
		});
	}

	private void botonBorrarPractica() {
		ui.getBtnBorrarPractica().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adminCobertura.borrarCobertura(practicaSeleccionada(), planSeleccionado());
				int obra = ui.getTablaObras().getSelectedRow();
				int plan = ui.getTablaPlanes().getSelectedRow();
				llenarListaObras();
				ui.getTablaObras().setRowSelectionInterval(obra, obra);
				llenarListaPlanes(obraSeleccionada());
				llenarListaPlanes(obraSeleccionada());
				ui.getTablaPlanes().setRowSelectionInterval(plan, plan);
				llenarListaPracticas(planSeleccionado());
				JOptionPane.showMessageDialog(null, "Se borró la cobertura exitosamente.");
			}
		});
	}

	private void botonCobertura() {
		ui.getBtnCobertura().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CoberturaDTO cobertura = new CoberturaDTO(practicaSeleccionada().getCodPractica(),
						(int) ui.getSpCobertura().getValue(), ui.getChckbxAutorizacion().isSelected());
				adminCobertura.editarCobertura(cobertura, planSeleccionado());
				llenarListaPracticas(planSeleccionado());
			}
		});
	}

	private void botonAgregarPractica() {
		ControladorObraSocial este = this;
		this.ui.getBtnAgregarPractica().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UIPracticas practica = new UIPracticas();
				ControllerUIPracticas controllerUI = new ControllerUIPracticas(practica, este);
				practicas.clear();
				controllerUI.agregarPracticas(practicas);
			}
		});
	}

	@Override
	public void entregarPracticas(List<PracticaDTO> practicasRecibidas) {
		for (PracticaDTO p : practicasRecibidas) {
			CoberturaDTO cobertura = new CoberturaDTO(p.getCodPractica(), (int) ui.getSpCobertura().getValue(),
					ui.getChckbxAutorizacion().isSelected());
			if (!planSeleccionado().getCobertura().contains(cobertura))
				adminCobertura.agregarCobertura(cobertura, planSeleccionado());
		}
		int obra = ui.getTablaObras().getSelectedRow();
		int plan = ui.getTablaPlanes().getSelectedRow();
		llenarListaObras();
		ui.getTablaObras().setRowSelectionInterval(obra, obra);
		llenarListaPlanes(obraSeleccionada());
		llenarListaPlanes(obraSeleccionada());
		ui.getTablaPlanes().setRowSelectionInterval(plan, plan);
		llenarListaPracticas(planSeleccionado());

	}
}
