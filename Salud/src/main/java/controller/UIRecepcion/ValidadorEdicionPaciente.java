package controller.UIRecepcion;

import java.util.ArrayList;
import java.util.List;

import dto.PacienteDTO;
import presentacion.vista.__IGuiRegistradorPaciente;

public class ValidadorEdicionPaciente {

	private List<String> errorlst;
	private PacienteDTO nuevopaciente;
	private __IGuiRegistradorPaciente edicion;

	/**
	 * funcion: constructor parametrizado
	 * 
	 * @input: $editor un editor de pacientes
	 * @return: void
	 */
	public ValidadorEdicionPaciente(__IGuiRegistradorPaciente editor) {
		setErrorlst(new ArrayList<String>());
		setNuevopaciente(new PacienteDTO());
		setEdicion(editor);
	}

	/**
	 * funcion: m�todo utilitario
	 * 
	 * @input: $cadena
	 * @return: true si verifica que $cadena es un numero
	 */
	static public boolean esNumero(String textfield) {
		ArrayList<String> arr = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			arr.add(Integer.toString(i));
		}
		boolean aux = true;
		String retValue = textfield.replace(" ", "");
		for (int i = 0; i < retValue.length() && aux; i++) {
			if (!arr.contains(String.valueOf(retValue.charAt(i)))) {
				aux = false;
			}
		}
		return aux;
	}

	/**
	 * funcion: m�todo utilitario
	 * 
	 * @input: $textfield
	 * @return: true si verifica que $cadena es un string solo de letras
	 */
	static public boolean esSinSimbolosNiNumeros(String textfield) {
		for (int index = 0; index < textfield.length(); index++)
			if (!Character.isLetter(textfield.charAt(index)) && textfield.charAt(index) != ' ')
				return false;
		return true;
	}

	private void validarEmail(int sizefield) {
		/// DEBERIA VERIFICAR QUE LO QUE HAY EN $auxemail sea una expression
		/// regular.!!!
		String auxemail = getEdicion().getTextEmailPaciente().getText();
		sizefield = sizefield + 20; // Es m�s grande de lo normal este campo
		if (!auxemail.isEmpty()) {
			if (auxemail.length() > sizefield || !auxemail.contains("@") || !auxemail.contains(".")) {
				getErrorlst().add("El Email es inv�lido");
			}
		} else {
			getErrorlst().add("El campo Email es obligatorio");
		}

		getNuevopaciente().setEmail(auxemail);
	}

	/**
	 * funcion: servicio
	 * 
	 * @input: $sizefield restriccion de longitud de str en txtfield
	 * @return: void::a�ade errormsg si encuentra errores
	 */
	private void validarNumero(String text, int sizefield, String valorinv�lido, String valorexcedido,
			String valorobligatorio) {
		if (text.length() != 0) {
			if (!esNumero(text)) {
				getErrorlst().add(valorinv�lido);
			} else {
				if (text.length() > sizefield) {
					getErrorlst().add(valorexcedido);
				}
			}
		} else {
			getErrorlst().add(valorobligatorio);
		}
	}

	private void validarString(String text, int sizefield, String strvacio, String strinv�lido, String strexcedido) {
		if (!text.isEmpty()) {
			if (!esSinSimbolosNiNumeros(text)) {
				getErrorlst().add(strinv�lido);
			} else {
				if (text.length() > sizefield) {
					getErrorlst().add(strexcedido);
				}
			}
		} else {
			getErrorlst().add(strvacio);
		}
	}

	/**
	 * funcion: utilitario
	 * 
	 * @input: $errorlst clase contenedora para almacenar errormsg
	 * @return: void
	 */
	private void setErrorlst(List<String> errorlst) {
		this.errorlst = errorlst;
	}

	/**
	 * funcion: getter
	 * 
	 * @input:
	 * @return: $PacienteDTO
	 */
	public PacienteDTO getNuevopaciente() {
		return nuevopaciente;
	}

	/**
	 * funcion: setter
	 * 
	 * @input: $PacienteDTO
	 * @return: setea un nuevo paciente a la instancia
	 */
	private void setNuevopaciente(PacienteDTO nuevopaciente) {
		this.nuevopaciente = nuevopaciente;
	}

	/**
	 * funcion: servicio
	 * 
	 * @input: $sizefield restriccion de longitud de str en txtfield
	 * @return: void::a�ade errormsg si encuentra errores
	 */
	public List<String> getErrorlst() {
		return errorlst;
	}

	/**
	 * funcion: servicio
	 * 
	 * @input: $sizefield restriccion de longitud de str en txtfield
	 * @return: void::a�ade errormsg si encuentra errores
	 */
	public void validate() {

		/// validacion informacion basica
		validarApellidoPaciente();
		validarEmail(15);
		validarNombrePaciente();
		validarDniPaciente();
		validarTelefonoPaciente();

		validarNombreContacto();
		validarTelefonoContacto();

		if (!getEdicion().getChckbxParticular().isSelected())
			validarNroAfiliado();

		if (!errorlst.isEmpty()) {
			setNuevopaciente(null);
		}
	}

	/**
	 * funcion: servicio
	 * 
	 * @input: $sizefield restriccion de longitud de str en txtfield
	 * @return: void::a�ade errormsg si encuentra errores
	 */
	private void validarTelefonoContacto() {
		validarNumero(getEdicion().getTextTelefonoContacto().getText(), 15,
				"El tel�fono de emergencia ingresado no es v�lido",
				"El tel�fono de emergencia ingresado excede la cantidad de d�gitos permitida",
				"El tel�fono de emergencia es obligatorio");
		getNuevopaciente().setTelefonoContacto(getEdicion().getTextTelefonoContacto().getText());
	}

	/**
	 * funcion: servicio
	 * 
	 * @input: $sizefield restriccion de longitud de str en txtfield
	 * @return: void::a�ade errormsg si encuentra errores
	 */
	private void validarNroAfiliado() {
		validarNumero(getEdicion().getTextNroAfiliado().getText(), 15, "El n� de afiliado ingresado no es v�lido",
				"El n� de afiliado ingresado excede la cantidad de d�gitos permitida", "El n� de afiliado es obligatorio");
		getNuevopaciente().setNroAfiliado(getEdicion().getTextNroAfiliado().getText());
	}

	/**
	 * funcion: servicio
	 * 
	 * @input: $sizefield restriccion de longitud de str en txtfield
	 * @return: void::a�ade errormsg si encuentra errores
	 */
	private void validarTelefonoPaciente() {
		validarNumero(getEdicion().getTextTelefonoPaciente().getText(), 15, "El tel�fono ingresado no es v�lido",
				"El tel�fono ingresado excede la cantidad de d�gitos permitida", "El tel�fono es obligatorio");
		getNuevopaciente().setTelefono(getEdicion().getTextTelefonoPaciente().getText());
	}

	private void validarDniPaciente() {
		validarNumero(getEdicion().getTextDniPaciente().getText(), 15, "El DNI ingresado no es v�lido",
				"El DNI ingresado excede la cantidad de d�gitos permitida", "El DNI es obligatorio");
		getNuevopaciente().setDni(Integer.parseInt(getEdicion().getTextDniPaciente().getText()));
	}

	private void validarNombreContacto() {
		// validacion del nombre de contacto
		validarString(getEdicion().getTextNombreContacto().getText(), 15, "El nombre del contacto de emergencia no puede ser vacio",
				"El nombre del contacto de emergencia es inv�lido", "El nombre del contacto de emergencia excede la cantidad de d�gitos permitida");
		getNuevopaciente().setNombreContacto(getEdicion().getTextNombreContacto().getText());
	}

	/**
	 * funcion: servicio
	 * 
	 * @input: $sizefield restriccion de longitud de str en txtfield
	 * @return: void::a�ade errormsg si encuentra errores
	 */
	private void validarApellidoPaciente() {
		validarString(getEdicion().getTextApellidoPaciente().getText(), 15,
				"El apellido del paciente no puede ser vacio", "El apellido del paciente es inv�lido",
				"El apellido del paciente excede la cantidad de d�gitos permitida");
		getNuevopaciente().setApellido(getEdicion().getTextApellidoPaciente().getText());
	}

	/**
	 * funcion: servicio
	 * 
	 * @input: $sizefield restriccion de longitud de str en txtfield
	 * @return: void::a�ade errormsg si encuentra errores
	 */
	private void validarNombrePaciente() {
		/// validacion nombre del paciente
		validarString(getEdicion().getTextNombrePaciente().getText(), 15, "El nombre del paciente no puede ser vac�o",
				"El nombre del paciente es inv�lido", "El nombre del paciente excede la cantidad de d�gitos permitida");
		getNuevopaciente().setNombre(getEdicion().getTextNombrePaciente().getText());
	}

	public __IGuiRegistradorPaciente getEdicion() {
		return edicion;
	}

	public void setEdicion(__IGuiRegistradorPaciente edicion) {
		this.edicion = edicion;
	}
}
