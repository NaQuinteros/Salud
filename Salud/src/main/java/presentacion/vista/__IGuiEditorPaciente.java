package presentacion.vista;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTextField;

public interface __IGuiEditorPaciente {
	
	
	/**
	 * input string field
	 * */
	public JTextField getTextNombrePaciente();
	
	
	/**
	 * input string field
	 * */
	public JTextField getTextApellidoPaciente();
	
	/**
	 * input string field
	 * */
	public JTextField getTextDniPaciente();
	
	
	/**
	 * input string field
	 * */
	public JTextField getTextTelefonoPaciente();
	
	
	/**
	 * input string field
	 * */
	public JTextField getTextEmailPaciente();
	
	
	/**
	 * input string field
	 * */
	public JTextField getTextNroAfiliado();
	
	
	/**
	 * choice string field
	 * */
	public JTextField getTextObraSocial();
	
	
	/**
	 * choice string field
	 * */
	public JTextField getTextPlan();
	
	
	/**
	 * input string field
	 * */
	public JTextField getTextNombreContacto();
	
	
	/**
	 * input string field
	 * */
	public JTextField getTextTelefonoContacto();
	
	
	
	/**
	 * commannd
	 * */
	public JButton getButtonRegistrarPaciente();

	
	/**
	 * command
	 * */
	public JButton getButtonConsultarPaciente();
	
	
	/**
	 * command
	 * */
	public JButton getButtonEditarPaciente();
	
	
	/**
	 * command
	 * */
	public JButton getButtonEliminarPaciente();
	
	/**
	 * setter
	 * */
	public void visible(boolean value);
	
	
	/**
	 * getter
	 * */
	public boolean isvisible();


	public JButton getBtnNuevoTurno();
	
	
	public JTextField getTextIDTurno();


	public AbstractButton gethHorarios();



	public AbstractButton getBtnCancelarTurno();

}
