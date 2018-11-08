package presentacion.vista;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class UILogin {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JFrame frame;



	/**
	 * Create the frame.
	 */
	public UILogin() {
		this.initialize();
	}
		
private void initialize(){
		frame= new JFrame();
		frame.setTitle("UI Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 320, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.getFrame().setLocation(dim.width/2-this.getFrame().getSize().width/2, dim.height/2-this.getFrame().getSize().height/2);
		
		JLabel lblUsuario = new JLabel("Nombre de usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsuario.setBounds(10, 64, 158, 14);
		contentPane.add(lblUsuario);
		
		frame.setResizable(false);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContrasea.setBounds(10, 89, 98, 14);
		contentPane.add(lblContrasea);
		
		textField = new JTextField();
		textField.setBounds(178, 62, 116, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(178, 87, 116, 20);
		contentPane.add(passwordField);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(178, 129, 116, 38);
		contentPane.add(btnLogin);
		
		JLabel lblGestorSiSalud = new JLabel("Gestor Si Salud");
		lblGestorSiSalud.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGestorSiSalud.setBounds(84, 11, 179, 26);
		contentPane.add(lblGestorSiSalud);
		frame.setVisible(true);
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	//Getters
	public JButton getBtnLogin() {
		return btnLogin;
	}
	public JPasswordField getTxtPassword() {
		return passwordField;
	}
	public JTextField getTxtUsuario() {
		return textField;
	}
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
