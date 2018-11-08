package controller.UIMedico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import dto.EspecialidadDTO;
import dto.MedicoDTO;
import dto.PacienteDTO;
import dto.PatologiaDTO;
import dto.PracticaDTO;
import dto.RenglonHistoriaClinicaDTO;
import modelo.Admin_Especialidad;
import modelo.Admin_Medico;
import modelo.Admin_Paciente;
import modelo.Admin_Patologias;
import modelo.Admin_RenglonHistoriaClinica;
import presentacion.vista.UIAgregarEntrada;
import presentacion.vista.UIMedicoHistoriaClinica;

public class ControllerMedicoHistoria {

	private UIMedicoHistoriaClinica guiHistoria;
	private PacienteDTO paciente;
	private Admin_Paciente adm_pacientes;
	private boolean flagEdit = false;
	private boolean flagHitos = false;
	private MedicoDTO medico;
	private Admin_RenglonHistoriaClinica adm_historia;
	private ControllerMedicoHistoria t = this;
	private int dni;
	private Admin_Patologias adm_patologia;
	private Admin_Especialidad adm_especialidad;
	private Admin_Medico admin_medico;

	public ControllerMedicoHistoria(UIMedicoHistoriaClinica guiHistoria, int dni, MedicoDTO medico) {
		this.medico = medico;
		this.guiHistoria = guiHistoria;
		adm_historia = new Admin_RenglonHistoriaClinica();
		adm_pacientes = new Admin_Paciente();
		adm_patologia = new Admin_Patologias();
		adm_especialidad = new Admin_Especialidad();
		admin_medico = new Admin_Medico();
		crearPaciente(dni);
		this.guiHistoria.setPaciente(this.paciente.getNombre() + " " + this.paciente.getApellido());
		inicializar();
		llenarTablaPatologias();
		llenarCmbPatologias();
		llenarCmbEspecialidad();
		llenarCmbMedico();
		listenerFiltrarEspecialidad();
		listenerFiltrarMedico();
	}

	private void listenerFiltrarMedico() {
		this.guiHistoria.getBtnFiltrarMedico().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String filtro = (String) guiHistoria.getCmbMedico().getSelectedItem();
				guiHistoria.getHistoriaClinica().setRowCount(0);
				guiHistoria.getHistoriaClinica().setColumnCount(0);
				guiHistoria.getHistoriaClinica().setColumnIdentifiers(guiHistoria.getColumnasHistoria());

				ArrayList<RenglonHistoriaClinicaDTO> todosRenglones = (ArrayList<RenglonHistoriaClinicaDTO>) adm_historia
						.obtener(paciente);
				for (RenglonHistoriaClinicaDTO renglon : todosRenglones) {
					renglon.setPracticas((ArrayList<PracticaDTO>) adm_historia.leerPracticasDeEntrada(renglon));
				}
				for (RenglonHistoriaClinicaDTO renglon : todosRenglones) {
					if (renglon.getIdPaciente() == paciente.getIdPaciente() && renglon.getMedico().equals(filtro)) {
						@SuppressWarnings("deprecation")
						Object[] fila = { renglon.getFecha().toGMTString().substring(0, 11), renglon.getMedico(),
								renglon.getDiagnostico(),
								renglon.getPracticas().toString().replaceAll("\\[", "").replaceAll("\\]", " - "),
								renglon.getEspecialidad() };
						guiHistoria.getHistoriaClinica().addRow(fila);
					}
				}
			}
		});
	}

	private void listenerFiltrarEspecialidad() {
		this.guiHistoria.getBtnFiltrarEspecialidad().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String filtro = (String) guiHistoria.getCmbEspecialidad().getSelectedItem();
				guiHistoria.getHistoriaClinica().setRowCount(0);
				guiHistoria.getHistoriaClinica().setColumnCount(0);
				guiHistoria.getHistoriaClinica().setColumnIdentifiers(guiHistoria.getColumnasHistoria());

				ArrayList<RenglonHistoriaClinicaDTO> todosRenglones = (ArrayList<RenglonHistoriaClinicaDTO>) adm_historia
						.obtener(paciente);
				for (RenglonHistoriaClinicaDTO renglon : todosRenglones) {
					renglon.setPracticas((ArrayList<PracticaDTO>) adm_historia.leerPracticasDeEntrada(renglon));
				}
				for (RenglonHistoriaClinicaDTO renglon : todosRenglones) {
					if (renglon.getIdPaciente() == paciente.getIdPaciente()
							&& renglon.getEspecialidad().equals(filtro)) {
						@SuppressWarnings("deprecation")
						Object[] fila = { renglon.getFecha().toGMTString().substring(0, 11), renglon.getMedico(),
								renglon.getDiagnostico(),
								renglon.getPracticas().toString().replaceAll("\\[", "").replaceAll("\\]", " - "),
								renglon.getEspecialidad() };
						guiHistoria.getHistoriaClinica().addRow(fila);
					}
				}
			}

		});
	}

	private void llenarCmbMedico() {
		ArrayList<MedicoDTO> medicos = (ArrayList<MedicoDTO>) this.admin_medico.obtenerMedicos();
		for (MedicoDTO m : medicos) {
			this.guiHistoria.getCmbMedico().addItem(m.get_nombre());
		}
		AutoCompleteDecorator.decorate(this.guiHistoria.getCmbMedico());
		this.guiHistoria.getCmbMedico().setSelectedIndex(-1);

	}

	private void llenarCmbPatologias() {
		ArrayList<PatologiaDTO> patologias = (ArrayList<PatologiaDTO>) this.adm_patologia.obtenerPatologias();
		for (PatologiaDTO p : patologias) {
			this.guiHistoria.getCmbPatologías().addItem(p);
		}
		AutoCompleteDecorator.decorate(this.guiHistoria.getCmbPatologías());
		this.guiHistoria.getCmbPatologías().setSelectedIndex(-1);
	}

	public void llenarDatosPaciente() {
		this.guiHistoria.getTxtNombre().setText(paciente.getNombre());
		this.guiHistoria.getTxtApellido().setText(paciente.getApellido());
		this.guiHistoria.getTxtTeléfono().setText(paciente.getTelefono());
		this.guiHistoria.getTxtDNI().setText(String.valueOf(paciente.getDni()));
		this.guiHistoria.getTxtEmail().setText(paciente.getEmail());
		this.guiHistoria.getTxtDireccion().setText(paciente.getDireccion());
		if (paciente.getObraSocial() != null) {
			this.guiHistoria.getTxtObra().setText(paciente.getObraSocial().getNombreObraSocial());
			this.guiHistoria.getTxtPlan().setText(paciente.getPlan().getNombrePlan());
		}
		this.guiHistoria.getTxtNum().setText(paciente.getNroAfiliado());
		this.guiHistoria.getTxtOcupacion().setText(paciente.getOcupacion());
		this.guiHistoria.getTxtNacionalidad().setText(paciente.getNacionalidad());
		this.guiHistoria.getChoNac().setDate(paciente.getFechaNacimiento());
		try {

			switch (paciente.getEstadoCivil()) {
			case "Soltero/a":
				this.guiHistoria.getCmbEstado().setSelectedIndex(2);
				break;
			case "Casado/a":
				this.guiHistoria.getCmbEstado().setSelectedIndex(0);
				break;
			case "Divorciado/a":
				this.guiHistoria.getCmbEstado().setSelectedIndex(1);
				break;
			case "Viudo/a":

				this.guiHistoria.getCmbEstado().setSelectedIndex(3);
				break;
			default:
				this.guiHistoria.getCmbEstado().setSelectedIndex(-1);
				break;
			}
		} catch (NullPointerException e) {
			this.guiHistoria.getCmbEstado().setSelectedIndex(-1);
		}
		try {
			switch (paciente.getSexo()) {
			case "Hombre":
				this.guiHistoria.getCmbSexo().setSelectedIndex(0);
				break;
			case "Mujer":
				this.guiHistoria.getCmbSexo().setSelectedIndex(1);
				break;
			default:
				this.guiHistoria.getCmbSexo().setSelectedIndex(-1);
				break;
			}

		} catch (NullPointerException e) {
			this.guiHistoria.getCmbSexo().setSelectedIndex(-1);
		}

	}

	private void llenarCmbEspecialidad() {
		this.guiHistoria.getCmbEspecialidad().removeAllItems();
		for (EspecialidadDTO e : adm_especialidad.obtenerEspecialidades()) {
			this.guiHistoria.getCmbEspecialidad().addItem(e.toString());
		}
		AutoCompleteDecorator.decorate(this.guiHistoria.getCmbEspecialidad());
		this.guiHistoria.getCmbEspecialidad().setSelectedIndex(-1);
	}

	private void inicializar() {
		this.guiHistoria.setVisible(true);
		llenarTablaRenglones();
		this.botonEditar();
		this.botonQuitarPatologia();
		this.botonMostrarHitos();
		this.botonAgregarEntrada();
		this.botonDetalles();
		this.botonAgregarPatologia();
	}

	@SuppressWarnings("deprecation")
	public void llenarTablaRenglones() {

		this.guiHistoria.getHistoriaClinica().setRowCount(0);
		this.guiHistoria.getHistoriaClinica().setColumnCount(0);
		this.guiHistoria.getHistoriaClinica().setColumnIdentifiers(this.guiHistoria.getColumnasHistoria());

		ArrayList<RenglonHistoriaClinicaDTO> todosRenglones = (ArrayList<RenglonHistoriaClinicaDTO>) this.adm_historia
				.obtener(paciente);
		for (RenglonHistoriaClinicaDTO renglon : todosRenglones) {
			renglon.setPracticas((ArrayList<PracticaDTO>) this.adm_historia.leerPracticasDeEntrada(renglon));
		}
		for (RenglonHistoriaClinicaDTO renglon : todosRenglones) {
			if (renglon.getIdPaciente() == this.paciente.getIdPaciente()) {
				Object[] fila = { renglon.getFecha().toGMTString().substring(0, 11), renglon.getMedico(),
						renglon.getDiagnostico(),
						renglon.getPracticas().toString().replaceAll("\\[", "").replaceAll("\\]", " - "),
						renglon.getEspecialidad() };
				this.guiHistoria.getHistoriaClinica().addRow(fila);
			}
		}
	}

	public void llenarTablaPatologias() {
		this.guiHistoria.getPatologias().setRowCount(0);
		this.guiHistoria.getPatologias().setColumnCount(0);
		this.guiHistoria.getPatologias().setColumnIdentifiers(this.guiHistoria.getColumnasPatologia());
		ArrayList<PatologiaDTO> patologias = (ArrayList<PatologiaDTO>) this.adm_patologia
				.obtenerPatologiasDelPaciente(this.paciente);

		if (!flagHitos) {
			String importante;
			for (PatologiaDTO p : patologias) {
				importante = (p.isImportante()) ? "Si" : "No";
				Object[] fila = { p.getDescripcion(), importante };
				this.guiHistoria.getPatologias().addRow(fila);
			}
		} else {
			for (PatologiaDTO p : patologias) {
				if (p.isImportante()) {
					Object[] fila = { p.getDescripcion(), "Si" };
					this.guiHistoria.getPatologias().addRow(fila);
				}
			}
		}
	}

	private void botonMostrarHitos() {
		this.guiHistoria.getBtnVerHitos().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				flagHitos = !flagHitos;
				if (flagHitos) {
					guiHistoria.getBtnVerHitos().setText("Esconder importantes");
					llenarTablaPatologias();
				} else {
					guiHistoria.getBtnVerHitos().setText("Mostrar todas");
					llenarTablaPatologias();
				}
			}

		});
	}

	private void botonDetalles() {

		this.guiHistoria.getBtnMostrarDetalles().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(guiHistoria.getTable().getSelectedRow() == -1)) {
					JOptionPane.showMessageDialog(null,
							"Diagnóstico: \n"
									+ guiHistoria.getTable().getValueAt(guiHistoria.getTable().getSelectedRow(), 2)
									+ "\nPracticas: \n"
									+ guiHistoria.getTable().getValueAt(guiHistoria.getTable().getSelectedRow(), 3));
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione la entrada a detallar");
				}
			}

		});

	}

	private void botonesEditarGuardar(boolean f) {
		if (f) {
			this.guiHistoria.getBtnEditar().setText("Guardar");
		} else {
			this.guiHistoria.getBtnEditar().setText("Editar");
			this.paciente.setDireccion(this.guiHistoria.getTxtDireccion().getText());
			try {
				this.paciente.setEstadoCivil(this.guiHistoria.getCmbEstado().getSelectedItem().toString());
			} catch (NullPointerException e) {
			}
			try {
				this.paciente.setSexo(this.guiHistoria.getCmbSexo().getSelectedItem().toString());
			} catch (NullPointerException e) {
			}
			this.paciente.setOcupacion(this.guiHistoria.getTxtOcupacion().getText());
			this.paciente.setNacionalidad(this.guiHistoria.getTxtNacionalidad().getText());
			try {
				this.paciente.setFechaNacimiento(new Timestamp(this.getGuiHistoria().getChoNac().getDate().getTime()));
			} catch (NullPointerException e) {
			}
			this.adm_pacientes.editarPaciente(this.paciente);
		}
		this.guiHistoria.getCmbEstado().setEnabled(f);
		this.guiHistoria.getCmbSexo().setEnabled(f);
		this.guiHistoria.getTxtDireccion().setEditable(f);
		this.guiHistoria.getTxtOcupacion().setEditable(f);
		this.guiHistoria.getTxtNacionalidad().setEditable(f);
		this.guiHistoria.getChoNac().setEnabled(f);
	}

	private void botonEditar() {
		this.guiHistoria.getBtnEditar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				flagEdit = !flagEdit;
				botonesEditarGuardar(flagEdit);
			}

		});
	}

	private void botonAgregarPatologia() {
		this.guiHistoria.getAgregarPatologia().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (guiHistoria.getCmbPatologías().getSelectedIndex() != -1) {
					boolean f = false;
					PatologiaDTO p = (PatologiaDTO) guiHistoria.getCmbPatologías().getSelectedItem();
					for (PatologiaDTO patologiaPaciente : adm_patologia.obtenerPatologiasDelPaciente(paciente)) {
						if (p.getIdPatologia().equals(patologiaPaciente.getIdPatologia())) {
							f = true;
							break;
						}
					}
					if (f) {
						JOptionPane.showMessageDialog(null, "La patología ya existe para este paciente");
					} else {
						if (guiHistoria.getImportante().isSelected())
							p.setImportante(true);
						adm_patologia.agregarPatologiaEnPaciente(p, paciente);
						llenarTablaPatologias();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una de las posibles patologías");
				}
			}

		});
	}

	private void botonQuitarPatologia() {

		this.guiHistoria.getBtnQuitarPatologia().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PatologiaDTO patologia = null;
				if (guiHistoria.getTable_patologias().getSelectedRow() != -1) {
					String spato = (String) guiHistoria.getTable_patologias()
							.getValueAt(guiHistoria.getTable_patologias().getSelectedRow(), 0);
					for (PatologiaDTO p : adm_patologia.obtenerPatologias()) {
						if (p.getDescripcion().equals(spato)) {
							patologia = p;
							break;
						}
					}
					adm_patologia.borrarPatologiaDePaciente(patologia, paciente);
					llenarTablaPatologias();
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una patologia en la tabla");
				}
			}
		});
	}

	private void botonAgregarEntrada() {
		this.guiHistoria.getBtnAgregarEntrada().addActionListener(new ActionListener() {

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				UIAgregarEntrada guiEntrada = new UIAgregarEntrada();
				ControllerAgregarEntrada controller = new ControllerAgregarEntrada(guiEntrada, t);
				guiEntrada.setVisible(true);
			}

		});
	}

	private void crearPaciente(int dni) {
		for (PacienteDTO paciente : adm_pacientes.obtenerPacientes()) {
			if (dni == paciente.getDni()) {
				this.paciente = paciente;
				break;
			}
		}
	}

	public UIMedicoHistoriaClinica getGuiHistoria() {
		return guiHistoria;
	}

	public PacienteDTO getPaciente() {
		return paciente;
	}

	public Admin_Paciente getAdm_pacientes() {
		return adm_pacientes;
	}

	public boolean isFlagEdit() {
		return flagEdit;
	}

	public boolean isFlagHitos() {
		return flagHitos;
	}

	public MedicoDTO getMedico() {
		return medico;
	}

	public Admin_RenglonHistoriaClinica getAdm_historia() {
		return adm_historia;
	}

	public ControllerMedicoHistoria getT() {
		return t;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
		this.crearPaciente(dni);
		llenarDatosPaciente();
	}

}
