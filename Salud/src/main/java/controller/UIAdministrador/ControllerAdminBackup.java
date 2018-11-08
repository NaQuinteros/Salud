package controller.UIAdministrador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import dto.CorreoDTO;
import modelo.Admin_Correo;
import persistencia.conexion.Reader;
import presentacion.vista.UIAdministrador;

public class ControllerAdminBackup {
	private UIAdministrador gui;
	private Admin_Correo adm;
	private String userpassImp, userpassExp;
	private Reader reader;

	public ControllerAdminBackup(UIAdministrador gui) {
		this.gui = gui;
		adm = new Admin_Correo();
		this.reader = new Reader();
		userpassImp = "mysql -u " + this.reader.getUser() + " -p" + this.reader.getPassword();
		userpassExp = "mysqldump -u " + this.reader.getUser() + " -p" + this.reader.getPassword();
		inicializador();
	}

	private void inicializador() {
		botonExportar();
		botonImportar();
		botonAceptar();
	}

	private void botonAceptar() {
		this.gui.getBtnAceptar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gui.getTxtCorreo().getText().equals("") || gui.getTxtPassword().getText().equals("")
						|| !gui.getTxtCorreo().getText().contains("@")) {
					JOptionPane.showMessageDialog(null,
							"Por favor introduzca un correo válido. El password ingresado debe de ser de la forma como es indicada en el manual");
				} else {
					if (adm.obtenerEnviador() == null) {
						adm.agregarCorreo(
								new CorreoDTO(0, gui.getTxtCorreo().getText(), gui.getTxtPassword().getText()));
					} else {
						CorreoDTO c = adm.obtenerEnviador();
						c.setUsuarioCorreo(gui.getTxtCorreo().getText());
						c.setPassword(gui.getTxtPassword().getText());
						adm.editarCorreo(c);
						JOptionPane.showMessageDialog(null, "Los datos han sido actualizados");
					}
				}
			}
		});
	}

	private void botonExportar() {
		this.gui.getBtnExportar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int retrival = chooser.showSaveDialog(gui);
				if (retrival == JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter fw = new FileWriter(chooser.getSelectedFile() + ".sql");
						File bat = new File("Dump.bat");
						PrintWriter batwriter = new PrintWriter(bat);
						batwriter.println(
								userpassExp + " Salud > \"" + chooser.getSelectedFile().getAbsolutePath() + ".sql\"");
						fw.close();
						batwriter.close();
						exportar();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
	}

	private void botonImportar() {
		this.gui.getBtnImportar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".sql", "sql", "bak");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(gui);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File bat = new File("Import.bat");
					PrintWriter batwriter;
					try {
						batwriter = new PrintWriter(bat);
						batwriter.println(userpassImp + " -e \"create database if not exists Salud\";");
						batwriter.println(userpassImp + " -e \"use Salud\";");
						batwriter.println(userpassImp + " -h " + reader.getIP() + " Salud < \""
								+ chooser.getSelectedFile().getAbsolutePath() + "\"");
						batwriter.close();
						importar();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}

	private void importar() {
		Process p;
		try {
			p = Runtime.getRuntime().exec("Import.bat");
			p.waitFor();
			if (p.exitValue() == 0)
				JOptionPane.showMessageDialog(null, "La base de datos se ha restaurado con éxito.");
			else
				JOptionPane.showMessageDialog(null,
						"La operación no finalizó normalmente. Contacte al encargado del sistema.");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null,
					"No se encontró el archivo necesario para importar la base de datos. Contacte al encargado del sistema.");
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			JOptionPane.showMessageDialog(null,
					"La operación se interrumpió inesperadamente. Contacte al encargado del sistema.");
		}
	}

	private void exportar() {
		Process p;
		try {
			p = Runtime.getRuntime().exec("Dump.bat");
			p.waitFor();
			if (p.exitValue() == 0)
				JOptionPane.showMessageDialog(null, "La base de datos se ha exportado exitosamente.");
			else
				JOptionPane.showMessageDialog(null,
						"La operación no finalizó normalmente. Contacte al encargado del sistema.");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null,
					"No se encontró el archivo necesario para exportar la base de datos. Contacte al encargado del sistema.");
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			JOptionPane.showMessageDialog(null,
					"La operación se interrumpió inesperadamente. Contacte al encargado del sistema.");
		}
	}
}
