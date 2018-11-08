package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import dto.ObraSocialDTO;
import dto.PlanDTO;

public interface __IGuiRegistradorPaciente {
	
	
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
	public JComboBox<ObraSocialDTO> getComboBoxObraSocial();
	
	
	/**
	 * choice string field
	 * */
	public JComboBox<PlanDTO> getComboBoxPlan();
	
	
	/**
	 * input string field
	 * */
	public JTextField getTextNombreContacto();
	
	
	/**
	 * input string field
	 * */
	public JTextField getTextTelefonoContacto();
	
	
	
	
	public JButton getButtonGuardarPaciente();
	
	public boolean setvisible(boolean value);
	
	public boolean isvisible();
	
	public boolean isEditando();
	
	public void setEditando(boolean value);


	public JCheckBox getChckbxParticular();
	
}
