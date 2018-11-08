package test;
import java.io.IOException;

import controller.UIContador.ControladorObraSocial;
import controller.UIRecepcion.ControladorHorario;
import controller.UIRecepcion.ControladorTurnosCancelados;
import controller.UIRecepcion.GestorDePacientes;
import presentacion.vista.UIHorario;
import presentacion.vista.UIObraSocial;
import presentacion.vista.UIRecepcion;
import presentacion.vista.UITurnosCancelados;

@SuppressWarnings("unused")
public class MarianoMain {

	public static void main(String[] args) throws IOException {

		// EspecialidadDTO esp1 = new EspecialidadDTO(1,"Traumatólogo");
		// EspecialidadDTO esp2 = new EspecialidadDTO(2,"Cardiólogo");
		// EspecialidadDTO esp3 = new EspecialidadDTO(3,"Ginecólogo");
		//
		// EspecialidadDAO especialidaddao = new EspecialidadDAOImpl();
		// especialidaddao.insert(esp1);
		// especialidaddao.insert(esp2);
		// especialidaddao.insert(esp3);
		//
		// MedicoDAO medicodao = new MedicoDAOImpl();
		// medicodao.insert(new MedicoDTO("Mariano","Mariano","Elmedico",esp1));
		// medicodao.insert(new MedicoDTO("Lucas","Luciano","Elmedic",esp3));
		// medicodao.insert(new MedicoDTO("Gaston","Gaston","Elmedi",esp2));
		// medicodao.insert(new MedicoDTO("Nahuel","Pedro","Elmed",esp2));

		// HorarioAtencionDAO horariodao = new HorarioAtencionDAOImpl();
		// horariodao.insert(new HorarioAtencionDTO(1,1800,2230,0));
		// UIPracticasTurno pt = new UIPracticasTurno();
		// ControladorPracticas cp = new ControladorPracticas(pt, null);
		// pt.setVisible(true);
		// }
		// }

		Boolean horarios = false;
		if (horarios == null) {
			UIObraSocial os = new UIObraSocial();
			ControladorObraSocial c = new ControladorObraSocial(os);
			os.setvisible(true);
		} else {
			if (horarios) {
				UIHorario vistaHorario = new UIHorario();
				ControladorHorario controlador = new ControladorHorario(vistaHorario);
				controlador.getUiHorarios().setVisible(true);
			} else {
				UIRecepcion vistaTurno = new UIRecepcion();
				GestorDePacientes gestor = new GestorDePacientes(vistaTurno);
				vistaTurno.setVisible(true);
			}
		}
	}
}
