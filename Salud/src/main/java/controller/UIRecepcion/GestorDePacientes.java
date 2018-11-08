package controller.UIRecepcion;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import dto.ObraSocialDTO;
import dto.PacienteDTO;
import dto.PlanDTO;
import dto.TurnoDTO;
import modelo.Admin_Cobertura;
import modelo.Admin_Paciente;
import modelo.Admin_Turno;
import presentacion.vista.UIBuscadorPacientes;
import presentacion.vista.UIHorario;
import presentacion.vista.UIRecepcion;
import presentacion.vista.UIRegistroDePaciente;
import presentacion.vista.UISalaEsperaRecepcion;
import presentacion.vista.UITurnosCancelados;
import presentacion.vista.__IGuiBuscadorDePacientes;

public class GestorDePacientes {

	private UIRecepcion uirecepcionista;
	private UIRegistroDePaciente uiregistrador;
	private UIBuscadorPacientes uibuscador;
	private Admin_Paciente adminpacientes;
	private Admin_Turno admin_turnos;
	private ControladorCorreo controladorCorreo;
	private Admin_Cobertura admin_obra;
	private ControladorTurnosCancelados controladorTurnosCancelados;
	private UITurnosCancelados uiTurnosCancelados;
	private ControladorTurnos controladorTurnos;
	protected TurnoDTO turnoAReasignar;

	public GestorDePacientes(UIRecepcion uirecepcionista) {
		uirecepcionista.setVisible(true);
		this.uirecepcionista = uirecepcionista;
		this.uiregistrador = new UIRegistroDePaciente();
		this.uibuscador = new UIBuscadorPacientes();
		this.adminpacientes = new Admin_Paciente();
		this.admin_turnos = new Admin_Turno();
		this.setControladorTurnos(new ControladorTurnos(uirecepcionista));
		this.admin_obra = new Admin_Cobertura();
		// Listeners
		listenerCmbObra();
		controlBotonRegistrarNuevoPaciente();
		controlButtonGuardarPaciente();
		controlBotonConsultarPaciente();
		controlBotonBuscarPaciente();
		controlBotonEditarPaciente();
		controlHHorario();
		controlSalaDeEspera();
		controlTurnoCancelado();
		esParticular();
		cronometrarCorreos();
		cerrarTurnoCancelado();
		llenarCmbObras();
	}

	private void controlTurnoCancelado() {
		this.uirecepcionista.getMntmTurnosCancelados().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiTurnosCancelados = new UITurnosCancelados();
				try {
					controladorTurnosCancelados = new ControladorTurnosCancelados(uiTurnosCancelados);
					reasignarTurno();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private void reasignarTurno() {
		this.uiTurnosCancelados.getBtnReasignar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				turnoAReasignar = controladorTurnosCancelados.getTurnoSeleccionado();
				if (turnoAReasignar == null)
					JOptionPane.showMessageDialog((Component) getVistaregistropacientes(),
							"Seleccione el turno que desea reasignar.");
				else {
					getBuscador().getTextDni()
							.setText(String.valueOf(controladorTurnosCancelados.getPaciente().getDni()));
					buscarPaciente();
					uirecepcionista.requestFocus();
				}
			}
		});
	}

	private void cerrarTurnoCancelado() {
		this.uirecepcionista.getBtnAsignar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (turnoLibre() && turnoAReasignar != null && pacienteElegido() != null
						&& turnoAReasignar.getPaciente().equals(pacienteElegido())) {
					turnoAReasignar.setEstado(TurnoDTO.Estado.CERRADO);
					admin_turnos.actualizarTurno(turnoAReasignar);
					uiTurnosCancelados.dispose();
					turnoAReasignar = null;
				}
			}
		});

	}

	private boolean turnoLibre() {
		int fila = this.uirecepcionista.getTablaTurnos().getSelectedRow();
		if (fila < 0)
			return false;
		String estado = (String) this.uirecepcionista.getTablaTurnos().getModel().getValueAt(fila, 1);
		return estado.equals("Libre");
	}

	private PacienteDTO pacienteElegido() {
		if (uirecepcionista.getTextDniPaciente().getText().isEmpty())
			return null;
		List<PacienteDTO> pacientes = adminpacientes.obtenerPacientes();
		for (PacienteDTO p : pacientes)
			if (p.getDni() == Integer.parseInt(uirecepcionista.getTextDniPaciente().getText()))
				return p;
		return null;
	}

	private void controlSalaDeEspera() {
		this.uirecepcionista.getMntmConsultarSala().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UISalaEsperaRecepcion g = new UISalaEsperaRecepcion();
				@SuppressWarnings("unused")
				ControladorSalaEspera cse = new ControladorSalaEspera(g);
			}

		});
	}

	private void controlHHorario() {
		this.uirecepcionista.gethHorarios().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uirecepcionista.getTablaMedicos().clearSelection();
				UIHorario ui = new UIHorario();
				try {
					@SuppressWarnings("unused")
					ControladorHorario cha = new ControladorHorario(ui);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				ui.setVisible(true);
			}
		});

	}

	private void esParticular() {
		this.uiregistrador.getChckbxParticular().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (uiregistrador.getChckbxParticular().isSelected()) {
					uiregistrador.getTextNroAfiliado().setText("");
					uiregistrador.getTextNroAfiliado().setEnabled(false);
					uiregistrador.getComboBoxObraSocial().setSelectedIndex(-1);
					uiregistrador.getComboBoxPlan().setSelectedIndex(-1);
					uiregistrador.getComboBoxObraSocial().setEnabled(false);
					uiregistrador.getComboBoxPlan().setEnabled(false);
				} else {
					uiregistrador.getComboBoxObraSocial().setEnabled(true);
					uiregistrador.getComboBoxPlan().setEnabled(true);
					uiregistrador.getTextNroAfiliado().setEnabled(true);
				}
			}

		});
	}

	public void controlButtonGuardarPaciente() {
		this.getVistaregistropacientes().getButtonGuardarPaciente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ValidadorEdicionPaciente validator = new ValidadorEdicionPaciente(getVistaregistropacientes());
				validator.validate();
				if (validator.getErrorlst().isEmpty()) {
					PacienteDTO nuevopaciente = validator.getNuevopaciente();
					if (!uiregistrador.getChckbxParticular().isSelected()) {
						nuevopaciente
								.setObraSocial((ObraSocialDTO) uiregistrador.getComboBoxObraSocial().getSelectedItem());
						nuevopaciente.setPlan((PlanDTO) uiregistrador.getComboBoxPlan().getSelectedItem());
					}
					List<PacienteDTO> pacientes = adminpacientes.obtenerPacientes();
					if (!pacientes.contains(nuevopaciente)) {
						adminpacientes.agregarPaciente(nuevopaciente);
						JOptionPane.showMessageDialog((Component) getVistaregistropacientes(),
								"El paciente ha sido registrado exitosamente");
						getBuscador().getTextDni().setText(getVistaregistropacientes().getTextDniPaciente().getText());
						getVistaregistropacientes().dispose();
						buscarPaciente();
					} else {
						if (!uiregistrador.isEditando())
							JOptionPane.showMessageDialog((Component) getVistaregistropacientes(),
									"Ya se encuentra registrado un paciente con el mismo DNI");
						else {
							for (int i = 0; i < pacientes.size(); i++) {
								if (pacientes.get(i).getDni() == nuevopaciente.getDni()) {
									if (!uiregistrador.getChckbxParticular().isSelected()) {
										nuevopaciente.setObraSocial((ObraSocialDTO) uiregistrador
												.getComboBoxObraSocial().getSelectedItem());
										nuevopaciente
												.setPlan((PlanDTO) uiregistrador.getComboBoxPlan().getSelectedItem());
									}
									nuevopaciente.setIdPaciente(pacientes.get(i).getIdPaciente());
									adminpacientes.editarPaciente(nuevopaciente);
									break;
								}
							}

							JOptionPane.showMessageDialog((Component) getVistaregistropacientes(),
									"El paciente ha sido editado exitosamente");
							// vaciamos los campos para su proximo uso
							getVistaregistropacientes().setvisible(false);
							getBuscador().getTextDni().setText(String.valueOf(nuevopaciente.getDni()));
							buscarPaciente();
						}
					}
				} else {
					String ERROR = "";
					for (String str : validator.getErrorlst()) {
						ERROR += "\n" + str;
					}
					JOptionPane.showMessageDialog((Component) getVistaregistropacientes(), ERROR);
				}
			}
		});
	}

	private void llenarCmbObras() {
		uiregistrador.getComboBoxObraSocial().removeAllItems();
		for (ObraSocialDTO obra : this.admin_obra.obtenerObras()) {
			uiregistrador.getComboBoxObraSocial().addItem(obra);
		}
		this.uiregistrador.getComboBoxObraSocial().setSelectedIndex(-1);
	}

	private void listenerCmbObra() {
		this.uiregistrador.getComboBoxObraSocial().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ObraSocialDTO obra = (ObraSocialDTO) uiregistrador.getComboBoxObraSocial().getSelectedItem();
				if (obra != null)
					llenarCmbPlan(obra);
			}

		});
	}

	private void llenarCmbPlan(ObraSocialDTO obra) {
		this.uiregistrador.getComboBoxPlan().removeAllItems();
		for (PlanDTO plan : obra.getPlanes()) {
			this.uiregistrador.getComboBoxPlan().addItem(plan);
		}
		this.uiregistrador.getComboBoxPlan().setSelectedIndex(-1);
	}

	public void controlBotonRegistrarNuevoPaciente() {
		this.getVistaRecepcionista().getButtonRegistrarPaciente().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Si se está editando llena los campos
				if (uiregistrador.isEditando()) {
					uiregistrador.setEditando(false);
					UIRegistroDePaciente reg = (UIRegistroDePaciente) uiregistrador;
					UIRecepcion rec = getVistaRecepcionista();
					
					reg.getTextDniPaciente().setText(rec.getTextDniPaciente().getText());
					reg.getTextApellidoPaciente().setText(rec.getTextApellidoPaciente().getText());
					reg.getTextEmailPaciente().setText(rec.getTextEmailPaciente().getText());
					reg.getTextNombreContacto().setText(rec.getTextNombreContacto().getText());
					reg.getTextNombrePaciente().setText(rec.getTextNombrePaciente().getText());
					reg.getTextNroAfiliado().setText(rec.getTextNroAfiliado().getText());
					reg.getTextTelefonoContacto().setText(rec.getTextTelefonoContacto().getText());
					reg.getTextTelefonoPaciente().setText(rec.getTextTelefonoPaciente().getText());
					ActionListener[] listener = uiregistrador.getComboBoxObraSocial().getActionListeners();
					uiregistrador.getComboBoxObraSocial().removeActionListener(listener[0]);
					for (int i = 0; i < getVistaregistropacientes().getComboBoxObraSocial().getItemCount(); i++) {
						if (getVistaregistropacientes().getComboBoxObraSocial().getItemAt(i).getNombreObraSocial()
								.equals(rec.getTextObraSocial().getText()))
							reg.getComboBoxObraSocial().setSelectedIndex(i);
					}
					if (reg.getComboBoxObraSocial().getSelectedIndex() > -1) {
						llenarCmbPlan((ObraSocialDTO) reg.getComboBoxObraSocial().getSelectedItem());
						for (int i = 0; i < getVistaregistropacientes().getComboBoxPlan().getItemCount(); i++)
							if (getVistaregistropacientes().getComboBoxPlan().getItemAt(i).getNombrePlan()
									.equals(rec.getTextPlan().getText()))
								reg.getComboBoxPlan().setSelectedIndex(i);
					}
					uiregistrador.getComboBoxObraSocial().addActionListener(listener[0]);

				} else {
					getVistaregistropacientes().getTextApellidoPaciente().setText("");
					getVistaregistropacientes().getTextDniPaciente().setText("");
					getVistaregistropacientes().getTextEmailPaciente().setText("");
					getVistaregistropacientes().getTextNombreContacto().setText("");
					getVistaregistropacientes().getTextNombrePaciente().setText("");
					getVistaregistropacientes().getTextNroAfiliado().setText("");
					getVistaregistropacientes().getTextTelefonoContacto().setText("");
					getVistaregistropacientes().getTextTelefonoPaciente().setText("");
				}
				if (!getVistaregistropacientes().isvisible())
					getVistaregistropacientes().setvisible(true);

			}
		});
	}

	public void controlBotonConsultarPaciente() {
		this.getVistaRecepcionista().getButtonConsultarPaciente().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				getBuscador().getTextDni().setText("");
				if (!getBuscador().isvisible()) {
					getBuscador().visible(true);
				}
			}
		});
	}

	public void controlBotonEditarPaciente() {
		this.getVistaRecepcionista().getButtonEditarPaciente().addActionListener(new ActionListener() {
			// Seteamos Editando a true para avisarle a RegistrarPaciente que
			// tiene que llenar los campos
			@Override
			public void actionPerformed(ActionEvent e) {
				uiregistrador.setEditando(true);
				// chequeamos si se lleno al menos un elemento de la vista de
				// recepción y si no lo tiene entonces se avisa que no hay nada
				// que editar
				if (!getVistaRecepcionista().getTextDniPaciente().getText().isEmpty()) {
					// ejecutamos la ventana de RegistrarPaciente que va a tener
					// todos los datos de los campos de la vista de Recepcion
					getVistaRecepcionista().getButtonRegistrarPaciente().doClick();
					uiregistrador.setEditando(true);
				} else
					JOptionPane.showMessageDialog((Component) uiregistrador,
							"Debe consultar por un paciente antes de editarlo");
			}
		});
	}

	public void controlBotonBuscarPaciente() {
		this.getBuscador().getButtonBuscar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buscarPaciente();
			}
		});
	}

	private void buscarPaciente() {
		String dniBuscado = getBuscador().getTextDni().getText();
		PacienteDTO paciente = null;
		if (dniBuscado.length() == 0) {
			JOptionPane.showMessageDialog((Component) getBuscador(), "No hay ningún DNI ingresado para la búsqueda");
		} else {
			List<PacienteDTO> lst = adminpacientes.obtenerPacientes();
			for (PacienteDTO aux : lst) {
				if (String.valueOf(aux.getDni()).equals(dniBuscado)) {
					paciente = aux;
					break;
				}
			}
		}
		if (paciente != null) {
			getVistaRecepcionista().getTextNombrePaciente().setText(paciente.getNombre());
			getVistaRecepcionista().getTextApellidoPaciente().setText(paciente.getApellido());
			getVistaRecepcionista().getTextDniPaciente().setText(String.valueOf(paciente.getDni()));
			getVistaRecepcionista().getTextEmailPaciente().setText(paciente.getEmail());
			getVistaRecepcionista().getTextTelefonoPaciente().setText(paciente.getTelefono());
			getVistaRecepcionista().getTextNroAfiliado().setText(paciente.getNroAfiliado());
			if (paciente.getObraSocial() != null) {
				getVistaRecepcionista().getTextObraSocial().setText(paciente.getObraSocial().getNombreObraSocial());
				getVistaRecepcionista().getTextPlan().setText(paciente.getPlan().getNombrePlan());
			}
			getVistaRecepcionista().getTextNombreContacto().setText(paciente.getNombreContacto());
			getVistaRecepcionista().getTextTelefonoContacto().setText(paciente.getTelefonoContacto());

			getBuscador().visible(false);
			getBuscador().getTextDni().setText("");
		} else {
			JOptionPane.showMessageDialog((Component) getBuscador(),
					"No se ha encontrado con ningún paciente con el DNI ingresado");
		}
		boolean enabled = uirecepcionista.getTablaMedicos().getSelectedRow() > -1
				&& uirecepcionista.getDateMedicos().getDate() != null
				&& !uirecepcionista.getTextDniPaciente().getText().isEmpty()
				&& uirecepcionista.getTablaTurnos().getSelectedRow() > -1 && uirecepcionista.getTablaTurnos()
						.getValueAt(uirecepcionista.getTablaTurnos().getSelectedRow(), 1).equals("Libre");
		uirecepcionista.getBtnAsignar().setEnabled(enabled);
	}

	public UIRecepcion getVistaRecepcionista() {
		return uirecepcionista;
	}

	public void setVistaRecepcionista(UIRecepcion vista) {
		this.uirecepcionista = vista;
	}

	public __IGuiBuscadorDePacientes getBuscador() {
		return uibuscador;
	}

	public UIRegistroDePaciente getVistaregistropacientes() {
		return uiregistrador;
	}

	public void setVistaregistracionpacientes(UIRegistroDePaciente vistaregistracionpacientes) {
		this.uiregistrador = vistaregistracionpacientes;
	}

	private void cronometrarCorreos() {
		enviarAlertasDeTurno();
		Timer timer = new Timer(3600 * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				enviarAlertasDeTurno();
			}
		});
		timer.setRepeats(true);
		timer.start();
	}

	@SuppressWarnings("deprecation")
	private void enviarAlertasDeTurno() {

		ArrayList<TurnoDTO> turnos = (ArrayList<TurnoDTO>) this.admin_turnos.obtenerTurnos();
		LocalDate tomorrow = LocalDate.now().plusDays(1L);
		ArrayList<TurnoDTO> turnosANotificar = new ArrayList<>();
		for (TurnoDTO turno : turnos) {
			Timestamp fechaTurno = turno.getFecha();

			if (turno.getPaciente() != null) {
				if (!turno.getPaciente().getEmail().isEmpty() && fechaTurno.getDate() == tomorrow.getDayOfMonth()
						&& fechaTurno.getMonth() == tomorrow.getMonthValue() - 1
						&& fechaTurno.getYear() == tomorrow.getYear() - 1900 && !turno.isNotificadoCorreo()) {
					turno.setNotificadoCorreo(true);
					this.admin_turnos.actualizarTurno(turno);
					turnosANotificar.add(turno);
				}
			}
		}
		this.controladorCorreo = new ControladorCorreo(turnosANotificar, true);
		Thread tr = new Thread(controladorCorreo);
		tr.start();
	}

	/**
	 * @return the controladorTurnos
	 */
	public ControladorTurnos getControladorTurnos() {
		return controladorTurnos;
	}

	/**
	 * @param controladorTurnos
	 *            the controladorTurnos to set
	 */
	public void setControladorTurnos(ControladorTurnos controladorTurnos) {
		this.controladorTurnos = controladorTurnos;
	}

}
