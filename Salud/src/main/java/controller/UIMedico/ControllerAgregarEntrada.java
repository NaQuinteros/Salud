package controller.UIMedico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controller.UIPracticas.ControllerUIPracticas;
import dto.PracticaDTO;
import dto.RenglonHistoriaClinicaDTO;
import modelo.Admin_RenglonHistoriaClinica;
import presentacion.vista.UIAgregarEntrada;
import presentacion.vista.UIPracticas;
import presentacion.vista._ConsultarPractica;

public class ControllerAgregarEntrada implements _ConsultarPractica {

	private UIAgregarEntrada gui;
	private ControllerMedicoHistoria controllerHistoria;
	private Admin_RenglonHistoriaClinica admin_historia;
	private List<PracticaDTO> practicas;
	private ControllerAgregarEntrada THIS = this;

	public ControllerAgregarEntrada(UIAgregarEntrada gui, ControllerMedicoHistoria controllerHistoria) {
		this.gui = gui;
		this.controllerHistoria = controllerHistoria;
		admin_historia = new Admin_RenglonHistoriaClinica();
		this.practicas = new ArrayList<>();
		inicializar();
	}

	private void inicializar() {
		botonAgregarEntrada();
		botonAgregarPractica();
	}

	private void botonAgregarEntrada() {
		this.gui.getBtnAgregarEntrada().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!gui.getTxtAreaDiag().getText().equals("") && gui.getTxtAreaDiag().getText().length() < 300) {
					Timestamp hoy = new Timestamp(System.currentTimeMillis());
					RenglonHistoriaClinicaDTO renglon = new RenglonHistoriaClinicaDTO(hoy,
							controllerHistoria.getMedico().get_nombre(), gui.getTxtAreaDiag().getText(),
							controllerHistoria.getPaciente().getIdPaciente(), controllerHistoria.getMedico().get_especialidad().getNombreEspecialidad());
					renglon.setPracticas((ArrayList<PracticaDTO>) practicas);

					admin_historia.crearEntrada(renglon);
					ArrayList<RenglonHistoriaClinicaDTO> renglones = (ArrayList<RenglonHistoriaClinicaDTO>) admin_historia.obtener(controllerHistoria.getPaciente());
					renglon = renglones.get(renglones.size() - 1);
					for (PracticaDTO practica : practicas) {
						admin_historia.agregarPracticaEnEntrada(practica, renglon);
					}
					controllerHistoria.llenarTablaRenglones();

					gui.dispose();
					gui.getTxtAreaDiag().setText("");
					JOptionPane.showMessageDialog(null, "Entrada guardada con éxito");
					controllerHistoria.getGuiHistoria().dispose();

				} else {
					JOptionPane.showMessageDialog(null, "Ingrese un diagnóstico");
				}
			}
		});
	}

	private void botonAgregarPractica() {
		this.gui.getBtnAgregarPractica().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UIPracticas practica = new UIPracticas();
				ControllerUIPracticas controllerUI = new ControllerUIPracticas(practica, THIS);
				controllerUI.agregarPracticas(practicas);
			}
		});
	}
	
	@Override
	public void entregarPracticas(List<PracticaDTO> prac) {
		this.practicas = prac;
	}
}
