package presentacion.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import persistencia.conexion.Reader;
import java.awt.Font;

public class VentanaC extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Reader reader;
	private JPanel contentPane;
	private String userpassImp;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtIp;
	private JTextField txtPuerto;
	private JButton btnEditarLocalidad;
	private JButton btnEditarTipoDe;

	public boolean state = false;

	public VentanaC() {
		super();
		reader = new Reader();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.setTitle("SiSalud");
		this.setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 309, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 281, 255);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(18, 70, 79, 14);
		panel.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(18, 98, 79, 14);
		panel.add(lblPassword);

		txtUsername = new JTextField();
		txtUsername.setBounds(107, 67, 164, 20);
		panel.add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setBounds(107, 95, 164, 20);
		panel.add(txtPassword);
		txtPassword.setColumns(10);

		JLabel lblIp = new JLabel("Puerto");
		lblIp.setBounds(18, 129, 79, 14);
		panel.add(lblIp);

		txtIp = new JTextField();
		txtIp.setBounds(107, 126, 164, 20);
		panel.add(txtIp);
		txtIp.setColumns(10);

		JLabel lblPuerto = new JLabel("IP");
		lblPuerto.setBounds(18, 160, 79, 14);
		panel.add(lblPuerto);

		JLabel lblLocalidad = new JLabel("Configure la conexi\u00F3n a la base de datos");
		lblLocalidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLocalidad.setBounds(29, 23, 242, 14);
		panel.add(lblLocalidad);

		txtPuerto = new JTextField();
		txtPuerto.setBounds(107, 157, 164, 20);
		panel.add(txtPuerto);
		txtPuerto.setColumns(10);

		btnEditarLocalidad = new JButton("Predeterminado");
		btnEditarLocalidad.addActionListener(this);
		btnEditarLocalidad.setBounds(18, 203, 140, 23);
		btnEditarLocalidad.setActionCommand("Default");
		panel.add(btnEditarLocalidad);

		btnEditarTipoDe = new JButton("Aceptar");
		btnEditarTipoDe.setBounds(168, 203, 103, 23);
		btnEditarTipoDe.setActionCommand("Aceptar");
		panel.add(btnEditarTipoDe);
		btnEditarTipoDe.addActionListener(this);

		this.setVisible(true);

	}

	public JTextField getTxtUsername() {
		return txtUsername;
	}

	public void setTxtUsername(JTextField txtUsername) {
		this.txtUsername = txtUsername;
	}

	public JTextField getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(JTextField txtPassword) {
		this.txtPassword = txtPassword;
	}

	public JTextField getTxtIp() {
		return txtIp;
	}

	public void setTxtIp(JTextField txtIp) {
		this.txtIp = txtIp;
	}

	public JTextField getTxtPuerto() {
		return txtPuerto;
	}

	public void setTxtPuerto(JTextField txtPuerto) {
		this.txtPuerto = txtPuerto;
	}

	public JButton getBtnEditarLocalidad() {
		return btnEditarLocalidad;
	}

	public void setBtnEditarLocalidad(JButton btnEditarLocalidad) {
		this.btnEditarLocalidad = btnEditarLocalidad;
	}

	public JButton getBtnEditarTipoDe() {
		return btnEditarTipoDe;
	}

	public void setBtnEditarTipoDe(JButton btnEditarTipoDe) {
		this.btnEditarTipoDe = btnEditarTipoDe;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Aceptar") {

			reader.writeXML(this.getTxtUsername().getText(), this.getTxtPassword().getText(), this.getTxtIp().getText(),
					this.getTxtPuerto().getText());

			userpassImp = "mysql -u " + this.getTxtUsername().getText() + " -p" + this.getTxtPassword().getText();
			importar();
			dispose();
		}
		if (e.getActionCommand() == "Default") {
			this.getTxtUsername().setText("root");
			this.getTxtPassword().setText("root");
			this.getTxtIp().setText("3306");
			this.getTxtPuerto().setText("localhost");
		}
	}

	private void importar() {
		File bat = new File("Import.bat");
		PrintWriter batwriter;
		
		try {
			batwriter = new PrintWriter(bat);
			batwriter.println(userpassImp + " -e \"create database if not exists Salud\";");
			batwriter.println(userpassImp + " -e \"use Salud\";");
			batwriter.println(userpassImp + " -h " + this.getTxtPuerto().getText() + " Salud < \"" + "sql\\Database.sql" + "\"");
			batwriter.close();
			Process p;
			try {
				p = Runtime.getRuntime().exec("Import.bat");
				p.waitFor();
				if (p.exitValue() != 0)
					JOptionPane.showMessageDialog(null,
							"La operación no finalizó normalmente. Contacte al encargado del sistema.");
				else
					JOptionPane.showMessageDialog(null,
							"Cambios realizados con éxito. Por favor, vuelva a abrir la aplicación.");
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,
						"No se encontró el archivo necesario para importar la base de datos. Contacte al encargado del sistema.");
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				JOptionPane.showMessageDialog(null,
						"La operación se interrumpió inesperadamente. Contacte al encargado del sistema.");
			}
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null,
					"No se encontró el archivo con la base de datos. Contacte al encargado del sistema.");
		}
	}

}
