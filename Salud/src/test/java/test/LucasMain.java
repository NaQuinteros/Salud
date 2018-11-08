package test;

import controller.UIAdministrador.ControllerAdminAdministrador;
import controller.UIAdministrador.ControllerAdminContable;
import controller.UIAdministrador.ControllerAdminInternacion;
import controller.UIAdministrador.ControllerAdminRecepcion;
import presentacion.vista.UIAdministrador;

public class LucasMain {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		UIAdministrador admin = new UIAdministrador();
		ControllerAdminRecepcion car = new ControllerAdminRecepcion(admin);
		ControllerAdminAdministrador caa = new ControllerAdminAdministrador(admin);
		ControllerAdminContable cac = new ControllerAdminContable(admin);
		ControllerAdminInternacion cai = new ControllerAdminInternacion(admin);
	}

}
