package controller.UIMedico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import dto.MedicoDTO;
import dto.TurnoDTO;
import modelo.Admin_Paciente;
import modelo.Admin_Turno;
import presentacion.vista.UIMedicoHistoriaClinica;
import presentacion.vista.UIMedicoPrincipal;

public class ControllerMedicoPrincipal {

	private UIMedicoPrincipal medico;
	private Admin_Turno adm_turno;
	private ArrayList<TurnoDTO> turnos;
	private ControllerMedicoHistoria historia;
	private Admin_Paciente adm_pacientes;
	private UIMedicoHistoriaClinica guiHistoria;
	private MedicoDTO medico2;

	public ControllerMedicoPrincipal(UIMedicoPrincipal medico, MedicoDTO medico2) {
		this.medico = medico;
		this.medico2 = medico2;
		adm_turno = new Admin_Turno();
		adm_pacientes = new Admin_Paciente();
		adm_pacientes.obtenerPacientes();
		inicializar();
	}

	private void inicializar() {
		medico.setVisible(true);
		this.llenarTabla();
		seguirLlenando();
		controllerBotonHistoriaClinica();
	}

	private void controllerBotonHistoriaClinica() {
		this.medico.getBtnHistoriaClinica().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (medico.getSalaEspera().getSelectedRow() != -1) {
					int dni = (int) medico.getModelTurnos().getValueAt(medico.getSalaEspera().getSelectedRow(), 2);
					guiHistoria = new UIMedicoHistoriaClinica("paciente");
					historia = new ControllerMedicoHistoria(guiHistoria, dni, medico2);
					historia.llenarDatosPaciente();
					guiHistoria.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un paciente");
				}
			}

		});
	}

	@SuppressWarnings("deprecation")
	private void llenarTabla() {

		medico.getModelTurnos().setRowCount(0);
		medico.getModelTurnos().setColumnCount(0);
		medico.getModelTurnos().setColumnIdentifiers(medico.getColumnasSalaEspera());

		turnos = (ArrayList<TurnoDTO>) adm_turno.obtenerTurnos();

		Timestamp hoy = new Timestamp(System.currentTimeMillis());
		for (TurnoDTO t : turnos) {
			if (hoy.getDate() == t.getFecha().getDate() && hoy.getMonth() == t.getFecha().getMonth()
					&& t.getEstado().equals(TurnoDTO.Estado.ESPERANDO) && t.getMedico().equals(this.medico2)) {
				Object[] fila = { t.getPaciente().getNombre(), t.getPaciente().getApellido(), t.getPaciente().getDni(),
						t.getFecha().toString().substring(11, 16) };
				medico.getModelTurnos().addRow(fila);
			}
		}
	}

	private void seguirLlenando() {
		Timer timer = new Timer(90000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				llenarTabla();
			}
		});
		timer.setRepeats(true);
		timer.start();
	}
}
