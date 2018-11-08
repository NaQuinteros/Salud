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
	 * funcion: método utilitario
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
	 * funcion: método utilitario
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
		sizefield = sizefield + 20; // Es más grande de lo normal este campo
		if (!auxemail.isEmpty()) {
			if (auxemail.length() > sizefield || !auxemail.contains("@") || !auxemail.contains(".")) {
				getErrorlst().add("El Email es inválido");
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
	 * @return: void::añade errormsg si encuentra errores
	 */
	private void validarNumero(String text, int sizefield, String valorinválido, String valorexcedido,
			String valorobligatorio) {
		if (text.length() != 0) {
			if (!esNumero(text)) {
				getErrorlst().add(valorinválido);
			} else {
				if (text.length() > sizefield) {
					getErrorlst().add(valorexcedido);
				}
			}
		} else {
			getErrorlst().add(valorobligatorio);
		}
	}

	private void validarString(String text, int sizefield, String strvacio, String strinválido, String strexcedido) {
		if (!text.isEmpty()) {
			if (!esSinSimbolosNiNumeros(text)) {
				getErrorlst().add(strinválido);
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
	 * @return: void::añade errormsg si encuentra errores
	 */
	public List<String> getErrorlst() {
		return errorlst;
	}

	/**
	 * funcion: servicio
	 * 
	 * @input: $sizefield restriccion de longitud de str en txtfield
	 * @return: void::añade errormsg si encuentra errores
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
	 * @return: void::añade errormsg si encuentra errores
	 */
	private void validarTelefonoContacto() {
		validarNumero(getEdicion().getTextTelefonoContacto().getText(), 15,
				"El teléfono de emergencia ingresado no es válido",
				"El teléfono de emergencia ingresado excede la cantidad de dígitos permitida",
				"El teléfono de emergencia es obligatorio");
		getNuevopaciente().setTelefonoContacto(getEdicion().getTextTelefonoContacto().getText());
	}

	/**
	 * funcion: servicio
	 * 
	 * @input: $sizefield restriccion de longitud de str en txtfield
	 * @return: void::añade errormsg si encuentra errores
	 */
	private void validarNroAfiliado() {
		validarNumero(getEdicion().getTextNroAfiliado().getText(), 15, "El nº de afiliado ingresado no es válido",
				"El nº de afiliado ingresado excede la cantidad de dígitos permitida", "El nº de afiliado es obligatorio");
		getNuevopaciente().setNroAfiliado(getEdicion().getTextNroAfiliado().getText());
	}

	/**
	 * funcion: servicio
	 * 
	 * @input: $sizefield restriccion de longitud de str en txtfield
	 * @return: void::añade errormsg si encuentra errores
	 */
	private void validarTelefonoPaciente() {
		validarNumero(getEdicion().getTextTelefonoPaciente().getText(), 15, "El teléfono ingresado no es válido",
				"El teléfono ingresado excede la cantidad de dígitos permitida", "El teléfono es obligatorio");
		getNuevopaciente().setTelefono(getEdicion().getTextTelefonoPaciente().getText());
	}

	private void validarDniPaciente() {
		validarNumero(getEdicion().getTextDniPaciente().getText(), 15, "El DNI ingresado no es válido",
				"El DNI ingresado excede la cantidad de dígitos permitida", "El DNI es obligatorio");
		getNuevopaciente().setDni(Integer.parseInt(getEdicion().getTextDniPaciente().getText()));
	}

	private void validarNombreContacto() {
		// validacion del nombre de contacto
		validarString(getEdicion().getTextNombreContacto().getText(), 15, "El nombre del contacto de emergencia no puede ser vacio",
				"El nombre del contacto de emergencia es inválido", "El nombre del contacto de emergencia excede la cantidad de dígitos permitida");
		getNuevopaciente().setNombreContacto(getEdicion().getTextNombreContacto().getText());
	}

	/**
	 * funcion: servicio
	 * 
	 * @input: $sizefield restriccion de longitud de str en txtfield
	 * @return: void::añade errormsg si encuentra errores
	 */
	private void validarApellidoPaciente() {
		validarString(getEdicion().getTextApellidoPaciente().getText(), 15,
				"El apellido del paciente no puede ser vacio", "El apellido del paciente es inválido",
				"El apellido del paciente excede la cantidad de dígitos permitida");
		getNuevopaciente().setApellido(getEdicion().getTextApellidoPaciente().getText());
	}

	/**
	 * funcion: servicio
	 * 
	 * @input: $sizefield restriccion de longitud de str en txtfield
	 * @return: void::añade errormsg si encuentra errores
	 */
	private void validarNombrePaciente() {
		/// validacion nombre del paciente
		validarString(getEdicion().getTextNombrePaciente().getText(), 15, "El nombre del paciente no puede ser vacío",
				"El nombre del paciente es inválido", "El nombre del paciente excede la cantidad de dígitos permitida");
		getNuevopaciente().setNombre(getEdicion().getTextNombrePaciente().getText());
	}

	public __IGuiRegistradorPaciente getEdicion() {
		return edicion;
	}

	public void setEdicion(__IGuiRegistradorPaciente edicion) {
		this.edicion = edicion;
	}
}
