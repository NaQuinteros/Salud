package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.CorreoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.CorreoDAO;

public class CorreoDAOImpl implements CorreoDAO {

	private static final String insert = "INSERT INTO TBL_CORREO(ID_CORREO, CUENTA_CORREO, PASSWORD_CORREO) VALUES(?, ?, ?)";
	private static final String delete = "DELETE FROM TBL_CORREO WHERE ID_CORREO = ?";
	private static final String readall = "SELECT * FROM TBL_CORREO";
	private static final Conexion conexion = Conexion.getConexion();

	
	@Override
	public boolean insert(CorreoDTO c) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, 0);
			statement.setString(2, c.getUsuarioCorreo());
			statement.setString(3, c.getPassword());
			
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
	public boolean delete(CorreoDTO c) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, 0);
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
	public boolean update(CorreoDTO c) {
		PreparedStatement statement;
		String update = "UPDATE TBL_CORREO SET CUENTA_CORREO = ?, PASSWORD_CORREO = ? WHERE ID_CORREO = 0";
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, c.getUsuarioCorreo());
			statement.setString(2, c.getPassword());
			
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
	public CorreoDTO read() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		CorreoDTO correo = null;
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
			correo = new CorreoDTO(0, resultSet.getString("CUENTA_CORREO"), resultSet.getString("PASSWORD_CORREO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return correo;
	}

}
