package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.InternacionDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.InternacionDAO;

public class InternacionDAOImpl implements InternacionDAO {

	private static final String insert = "INSERT INTO TBL_INTERNACION(ID_INTERNACION, USUARIO_INTERNACION, PASSWORD_INTERNACION, NOMBRE_INTERNACION) VALUES(?, ?, ?, ? )";
	private static final String delete = "DELETE FROM TBL_INTERNACION WHERE ID_INTERNACION = ?";
	private static final String readall = "SELECT * FROM TBL_INTERNACION";
	private static final Conexion conexion = Conexion.getConexion();

	@Override
	public boolean insert(InternacionDTO internacion) {
		PreparedStatement statement;
		try {

			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, internacion.get_idInternacion());
			statement.setString(2, internacion.get_nombreUsuario());
			statement.setString(3, internacion.get_password());
			statement.setString(4, internacion.get_nombre());

			if (statement.executeUpdate() > 0) // Si se ejecutó devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;

	}

	@Override
	public boolean delete(InternacionDTO internacion) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, internacion.get_idInternacion());
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0) // Si se ejecutó devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	@Override
	public boolean update(InternacionDTO internacion) {
		PreparedStatement statement;
		String update = "UPDATE TBL_INTERNACION SET USUARIO_INTERNACION = ?, " + " PASSWORD_INTERNACION = ?, "
				+ "NOMBRE_INTERNACION = ?" + "WHERE ID_INTERNACION = " + internacion.get_idInternacion();
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, internacion.get_nombreUsuario());
			statement.setString(2, internacion.get_password());
			statement.setString(3, internacion.get_nombre());

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
	public List<InternacionDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<InternacionDTO> internacion = new ArrayList<>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				internacion.add(new InternacionDTO(resultSet.getInt("ID_INTERNACION"),
						resultSet.getString("USUARIO_INTERNACION"), resultSet.getString("PASSWORD_INTERNACION"),
						resultSet.getString("NOMBRE_INTERNACION")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return internacion;
	}

}
