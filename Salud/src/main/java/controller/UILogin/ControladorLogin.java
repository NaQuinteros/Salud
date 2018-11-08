package controller.UILogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import controller.UIAdministrador.ControllerAdminAdministrador;
import controller.UIAdministrador.ControllerAdminBackup;
import controller.UIAdministrador.ControllerAdminContable;
import controller.UIAdministrador.ControllerAdminEspecialidad;
import controller.UIAdministrador.ControllerAdminHabitacion;
import controller.UIAdministrador.ControllerAdminInternacion;
import controller.UIAdministrador.ControllerAdminMedico;
import controller.UIAdministrador.ControllerAdminRecepcion;
import controller.UIContador.ControladorContablePrincipal;
import controller.UIInternacion.ControladorInternacionPrincipal;
import controller.UIMedico.ControllerMedicoPrincipal;
import controller.UIRecepcion.GestorDePacientes;
import dto.AdministradorDTO;
import dto.ContableDTO;
import dto.InternacionDTO;
import dto.MedicoDTO;
import dto.RecepcionDTO;
import modelo.Admin_Internacion;
import modelo.Administracion;
import presentacion.vista.UIAdministrador;
import presentacion.vista.UILogin;
import presentacion.vista.UIMedicoPrincipal;
import presentacion.vista.UIRecepcion;

public class ControladorLogin implements ActionListener {
	private List<MedicoDTO> medicos;
	private List<RecepcionDTO> recepcionistas;
	private List<InternacionDTO> personalI;
	private UILogin uilogin;
	private Administracion admin;
	private Admin_Internacion internacion;
	private List<AdministradorDTO> administradores;
	private List<ContableDTO> contadores;

	public ControladorLogin() {
		this.internacion = new Admin_Internacion();
		uilogin = new UILogin();
		uilogin.getBtnLogin().addActionListener(this);
		this.admin = new Administracion();
		internacion = new Admin_Internacion();
		this.medicos = admin.obtenerMedicos();
		this.recepcionistas = admin.obtenerRecepcion();
		this.personalI = internacion.obtenerPersonalInternacion();
		this.administradores = admin.obtenerAdministradores();
		this.contadores = admin.obtenerContables();
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {

		// BOTON LOGIN
		if (e.getSource() == this.uilogin.getBtnLogin()) {
			String user = this.uilogin.getTxtUsuario().getText().toLowerCase();
			String password = this.uilogin.getTxtPassword().getText();
			if (this.uilogin.getTxtUsuario().getText().isEmpty() || this.uilogin.getTxtPassword().getText().isEmpty()) {
				JOptionPane.showMessageDialog(uilogin.getFrame(), "Debe completar todos los campos");
			} else {
				boolean foundPass = false;
				boolean foundUser = false;
				
				for (RecepcionDTO cuentaRecepcion : recepcionistas) {
					if (cuentaRecepcion.get_nombreUsuario().toLowerCase().equals(user)) {
						foundUser = true;
						if (cuentaRecepcion.get_password().equals(password)) {
							foundPass = true;
							abrirRecepcion();
						}
						break;
					}
				}
				
				for (MedicoDTO cuentaMedico : medicos) {
					if (cuentaMedico.get_nombreUsuario().toLowerCase().equals(user)) {
						foundUser = true;
						if (cuentaMedico.get_password().equals(password)) {
							foundPass = true;
							abrirMedico(cuentaMedico);
						}
						break;
					}
				}
				
				for (AdministradorDTO cuentaAdmin : administradores) {
					if (cuentaAdmin.getUsuario().toLowerCase().equals(user)) {
						foundUser = true;
						if (cuentaAdmin.getPass().equals(password)) {
							foundPass = true;
							abrirAdministrador();
						}
					}
				}
				for (InternacionDTO cuentaInternacion : personalI) {
					if (cuentaInternacion.get_nombreUsuario().toLowerCase().equals(user)) {
						foundUser = true;
						if (cuentaInternacion.get_password().equals(password)) {
							foundPass = true;
							abrirInternacion();
						}
						break;
					}
				}
				
				for (ContableDTO cuentaContable : contadores) {
					if (cuentaContable.get_nombreUsuario().toLowerCase().equals(user)) {
						foundUser = true;
						if (cuentaContable.get_password().equals(password)) {
							foundPass = true;
							abrirContable();
						}
						break;
					}
				}
				
				if (!foundUser)
					JOptionPane.showMessageDialog(uilogin.getFrame(), "Usuario Incorrecto.");
				else if (!foundPass)
					JOptionPane.showMessageDialog(uilogin.getFrame(), "Contraseña Incorrecta.");
			}
		}
	}

	@SuppressWarnings("unused")
	private void abrirAdministrador() {
		UIAdministrador admin = new UIAdministrador();
		ControllerAdminRecepcion car = new ControllerAdminRecepcion(admin);
		ControllerAdminAdministrador caa = new ControllerAdminAdministrador(admin);
		ControllerAdminContable cac = new ControllerAdminContable(admin);
		ControllerAdminInternacion cai = new ControllerAdminInternacion(admin);
		ControllerAdminHabitacion cah = new ControllerAdminHabitacion(admin);
		ControllerAdminEspecialidad cae = new ControllerAdminEspecialidad(admin);
		ControllerAdminMedico cam = new ControllerAdminMedico(admin);
		ControllerAdminBackup cab = new ControllerAdminBackup(admin);
		this.uilogin.getFrame().dispose();
	}

	private void abrirInternacion() {
		@SuppressWarnings("unused")
		ControladorInternacionPrincipal controlInt = new ControladorInternacionPrincipal();
		this.uilogin.getFrame().dispose();
	}

	private void abrirContable() {
		@SuppressWarnings("unused")
		ControladorContablePrincipal control = new ControladorContablePrincipal();
		this.uilogin.getFrame().dispose();
	}
	
	private void abrirMedico(MedicoDTO cuentaMedico) {
		UIMedicoPrincipal GuiMedicoPrincipal = new UIMedicoPrincipal();
		@SuppressWarnings("unused")
		ControllerMedicoPrincipal a = new ControllerMedicoPrincipal(GuiMedicoPrincipal, cuentaMedico);
		this.uilogin.getFrame().dispose();
	}

	@SuppressWarnings("unused")
	private void abrirRecepcion() {
		UIRecepcion vistaTurno = new UIRecepcion();
		GestorDePacientes gestor = new GestorDePacientes(vistaTurno);
		vistaTurno.setVisible(true);
		this.uilogin.getFrame().dispose();
	}
}
