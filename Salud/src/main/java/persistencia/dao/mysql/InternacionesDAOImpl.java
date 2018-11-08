package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.InternacionesDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.InternacionesDAO;

public class InternacionesDAOImpl  implements InternacionesDAO{

	private static final String insert = "INSERT INTO TBL_INTERNACIONES(ID_HABITACION, ID_RESERVA, ID_PACIENTE, INICIO_RESERVA, FIN_RESERVA,INGRESO_REAL, EGRESO_REAL,DIAGNOSTICO_INICIAL, OBSERVACION_INICIAL, MOTIVO_EGRESO,DIAGNOSTICO_FINAL,OBSERVACION_FINAL,ESTADO) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM TBL_INTERNACIONES WHERE ID_RESERVA = ?";
	private static final String readall = "SELECT * FROM TBL_INTERNACIONES";
	private static final Conexion conexion = Conexion.getConexion();

	@Override
	public boolean insert(InternacionesDTO reserva) {
		PreparedStatement statement;
		try {

			statement = conexion.getSQLConexion().prepareStatement(insert);
			if (reserva.getIdHabitacion()!=null)
			statement.setInt(1, reserva.getIdHabitacion());
			else statement.setInt(1,java.sql.Types.INTEGER);
			statement.setInt(2, reserva.getId());
			statement.setInt(3, reserva.getIdPaciente());
			statement.setTimestamp(4, reserva.getInicio());
			statement.setTimestamp(5, reserva.getFin());
			statement.setTimestamp(6, reserva.getIngresoReal());
			statement.setTimestamp(7, reserva.getEgresoReal());
			statement.setString(8, reserva.getDiagnosticoInicial());
			statement.setString(9, reserva.getObservacionInicial());
			statement.setInt(10, reserva.getMotivoEgreso());
			statement.setString(11, reserva.getDiagnosticoFinal());
			statement.setString(12, reserva.getObservacionFinal());
			statement.setString(13, reserva.getEstado());

			if (statement.executeUpdate() > 0) 
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally 
		{
			conexion.cerrarConexion();
		}
		return false;

	}

	@Override
	public boolean delete(InternacionesDTO habitacion) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, habitacion.getId());
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally 
		{
			conexion.cerrarConexion();
		}
		return false;

	}

	
	@Override
	public List<InternacionesDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<InternacionesDTO> reservas = new ArrayList<InternacionesDTO>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				reservas.add(new InternacionesDTO(
						resultSet.getInt("ID_HABITACION"), 
						resultSet.getInt("ID_RESERVA"),
						resultSet.getInt("ID_PACIENTE"), 
						resultSet.getTimestamp("INICIO_RESERVA"),
						resultSet.getTimestamp("FIN_RESERVA"),
						resultSet.getTimestamp("INGRESO_REAL" ),
						resultSet.getTimestamp( "EGRESO_REAL"),
						resultSet.getString("DIAGNOSTICO_INICIAL"),
						resultSet.getString("OBSERVACION_INICIAL"),
						resultSet.getInt("MOTIVO_EGRESO"),
						resultSet.getString("DIAGNOSTICO_FINAL"),
						resultSet.getString("OBSERVACION_FINAL"),
						resultSet.getString("ESTADO")
					 	));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return reservas;
	}
	
	@Override
	public boolean update(InternacionesDTO internacion) {
		PreparedStatement statement;
		String update = "UPDATE TBL_INTERNACIONES SET ID_HABITACION = ?, ID_RESERVA = ?, "
				+ "ID_PACIENTE = ?, INICIO_RESERVA = ?, FIN_RESERVA = ?, " + "INGRESO_REAL = ?, "
				+ "EGRESO_REAL = ?, DIAGNOSTICO_INICIAL = ?, "
				+ "OBSERVACION_INICIAL = ?, MOTIVO_EGRESO = ?, DIAGNOSTICO_FINAL = ?, OBSERVACION_FINAL = ?, ESTADO = ? "
				+ "WHERE ID_RESERVA = ?";

		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			if (internacion.getIdHabitacion()==null)
				statement.setNull(1, java.sql.Types.INTEGER);
			else statement.setInt(1, internacion.getIdHabitacion());
			statement.setInt(2, internacion.getId());
			statement.setInt(3, internacion.getIdPaciente());
			statement.setTimestamp(4, internacion.getInicio());
			statement.setTimestamp(5, internacion.getFin());
			statement.setTimestamp(6, internacion.getIngresoReal());
			statement.setTimestamp(7, internacion.getEgresoReal());
			statement.setString(8, internacion.getDiagnosticoInicial());
			statement.setString(9, internacion.getObservacionInicial());
			statement.setInt(10, internacion.getMotivoEgreso());
			statement.setString(11, internacion.getDiagnosticoFinal());
			statement.setString(12, internacion.getObservacionFinal());
			statement.setString(13, internacion.getEstado());
			statement.setInt(14, internacion.getId());

			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0) //
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return false;
	}


}
