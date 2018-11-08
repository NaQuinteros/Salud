package main;

import controller.UILogin.ControladorLogin;
import persistencia.conexion.Reader;
import presentacion.vista.VentanaC;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) {
		Reader reader = new Reader();
		if (reader.getUser() == null) {
			VentanaC ventana = new VentanaC();
		} 
		else{
		ControladorLogin login = new ControladorLogin();
	}}

}
