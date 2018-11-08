package controller.UIRecepcion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.MedicoDTO;
import dto.TurnoDTO;
import modelo.Admin_Medico;
import modelo.Admin_Turno;
import presentacion.vista.UISalaEsperaRecepcion;

public class ControladorSalaEspera {

	private Admin_Medico adm_medico;
	private Admin_Turno adm_turno;
	private List<TurnoDTO> turnosDelMedico, turnos;
	private UISalaEsperaRecepcion gui;

	public ControladorSalaEspera(UISalaEsperaRecepcion gui) {
		adm_medico = new Admin_Medico();
		adm_turno = new Admin_Turno();
		this.gui = gui;
		turnosDelMedico = new ArrayList<>();
		gui.setVisible(true);
		inicializar();
	}

	private void inicializar() {
		turnos = adm_turno.obtenerTurnos();
		llenarComboBox();
		comboBoxListener();
		btnListener();
	}

	private void btnListener() {
		gui.getBtnAgregarPaciente().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = gui.getSalaEspera().getSelectedRow();
				TurnoDTO turno = null;
				System.out.println(gui.getSalaEspera().getModel().getValueAt(row, 0));
				for (TurnoDTO t : turnos)
					if (t.getIdTurno() == (int) gui.getSalaEspera().getModel().getValueAt(row, 0)) {
						turno = t;
						break;
					}
				if (row > -1) {
					if (gui.getSalaEspera().getValueAt(row, 3).equals("Tomado")) {
						turno.setEstado(TurnoDTO.Estado.ESPERANDO);
						adm_turno.actualizarTurno(turno);
					} else {
						turno.setEstado(TurnoDTO.Estado.TOMADO);
						adm_turno.actualizarTurno(turno);
					}
				} else {
					JOptionPane.showMessageDialog(null, "No se seleccionó ningún paciente en la tabla");
				}
				llenarTabla();
			}
		});
	}

	private void llenarComboBox() {
		for (MedicoDTO medico : adm_medico.obtenerMedicos()) {
			gui.getComboBox().addItem(medico);
		}
		gui.getComboBox().setSelectedIndex(-1);
	}

	private void comboBoxListener() {
		gui.getComboBox().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				turnosDelMedico.clear();
				for (TurnoDTO t : turnos)
					if ((t.getEstado().equals(TurnoDTO.Estado.TOMADO)
							|| t.getEstado().equals(TurnoDTO.Estado.ESPERANDO))
							&& t.getMedico().equals(gui.getComboBox().getSelectedItem()))
						turnosDelMedico.add(t);
				llenarTabla();
			}

		});
	}

	@SuppressWarnings("deprecation")
	private void llenarTabla() {
		gui.getModelTurnos().setRowCount(0);
		Timestamp hoy = new Timestamp(System.currentTimeMillis());
		for (TurnoDTO turno : turnosDelMedico) {
			if (turno.getPaciente() != null && hoy.getDate() == turno.getFecha().getDate()
					&& hoy.getMonth() == turno.getFecha().getMonth()) {
				Object[] fila = { turno.getIdTurno(), turno.getPaciente().getNombre(),
						turno.getPaciente().getApellido(), turno.getPaciente().getDni(), turno.getEstado().toString(),
						turno.getFecha().toString().substring(11, 16) };
				gui.getModelTurnos().addRow(fila);
			}
		}
	}

}
