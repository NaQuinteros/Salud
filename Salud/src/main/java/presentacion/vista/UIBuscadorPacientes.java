package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class UIBuscadorPacientes extends JFrame implements __IGuiBuscadorDePacientes {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JButton btnBuscar;
	
	public UIBuscadorPacientes() {
		this.setTitle("Buscar paciente");
		setResizable(false);
		setBounds(100, 100, 235, 136);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 301, 217, 1);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(10, 38, 46, 14);
		getContentPane().add(lblDni);
		
		textField = new JTextField();
		textField.setBounds(43, 35, 178, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblIngreseElDni = new JLabel("Ingrese el DNI del paciente que desea buscar ");
		lblIngreseElDni.setBounds(10, 11, 227, 14);
		getContentPane().add(lblIngreseElDni);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 63, 217, 32);
		getContentPane().add(panel_1);
		
		btnBuscar = new JButton("Buscar");

		panel_1.add(btnBuscar);
	
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public JTextField getTextDni() {
		return this.textField;
	}
	
	@Override
	public JButton getButtonBuscar() {
		return this.btnBuscar;
	}
	
	@Override
	public void visible(boolean value) {
		this.setVisible(value);
	}

	@Override
	public boolean isvisible() {
		// TODO Auto-generated method stub
		return this.isVisible();
	}
}
