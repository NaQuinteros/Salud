package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.RecepcionDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.RecepcionDAO;

public class RecepcionDAOImpl implements RecepcionDAO {

	private static final String insert = "INSERT INTO TBL_RECEPCION(ID_RECEPCION, USUARIO_RECEPCION, PASSWORD_RECEPCION, NOMBRE_RECEPCION) VALUES(?, ?, ?, ?)";
	private static final String delete = "DELETE FROM TBL_RECEPCION WHERE ID_RECEPCION = ?";
	private static final String readall = "SELECT * FROM TBL_RECEPCION";
	private static final Conexion conexion = Conexion.getConexion();

	@Override
	public boolean insert(RecepcionDTO recepcion) {
		PreparedStatement statement;
		try {

			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, recepcion.get_idRecepcion());
			statement.setString(2, recepcion.get_nombreUsuario());
			statement.setString(3, recepcion.get_password());
			statement.setString(4, recepcion.getNombre());

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
	public boolean delete(RecepcionDTO recepcion) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, recepcion.get_idRecepcion());
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
	public boolean update(RecepcionDTO recepcion) {
		PreparedStatement statement;
		String update = "UPDATE TBL_RECEPCION SET NOMBRE_RECEPCION = ?, " + "USUARIO_RECEPCION = ?, "
				+ "PASSWORD_RECEPCION = ?" + " WHERE ID_RECEPCION = "+ recepcion.get_idRecepcion();
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, recepcion.getNombre());
			statement.setString(2, recepcion.get_nombreUsuario());
			statement.setString(3, recepcion.get_password());

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

	@Override
	public List<RecepcionDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<RecepcionDTO> recepcionistas = new ArrayList<>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				recepcionistas.add(new RecepcionDTO(resultSet.getInt("ID_RECEPCION"),
						resultSet.getString("USUARIO_RECEPCION"), resultSet.getString("PASSWORD_RECEPCION"), resultSet.getString("NOMBRE_RECEPCION")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally
		{
			conexion.cerrarConexion();
		}
		return recepcionistas;
	}

}
