package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JTextField;

public interface __IGuiBuscadorDePacientes {
	
	public JTextField getTextDni();
	
	public JButton getButtonBuscar();
	
	public void visible(boolean value);
	
	public boolean isvisible();
}
