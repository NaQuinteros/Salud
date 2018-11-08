package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.AdministradorDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.AdministradorDAO;

public class AdministradorDAOImpl implements AdministradorDAO {

	private static final String insert = "INSERT INTO TBL_ADMINISTRADOR(ID_ADMINISTRADOR, NOMBRE_ADMINISTRADOR, USUARIO_ADMINISTRADOR, PASS_ADMINISTRADOR) VALUES(?, ?, ?, ?)";
	private static final String delete = "DELETE FROM TBL_ADMINISTRADOR WHERE ID_ADMINISTRADOR = ?";
	private static final String readall = "SELECT * FROM TBL_ADMINISTRADOR";
	private static final Conexion conexion = Conexion.getConexion();

	@Override
	public boolean insert(AdministradorDTO admin) {
		PreparedStatement statement;
		try {

			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, admin.getId());
			statement.setString(2, admin.getNombre());
			statement.setString(3, admin.getUsuario());
			statement.setString(4, admin.getPass());

			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return false;

	}

	@Override
	public boolean delete(AdministradorDTO admin) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, admin.getId());
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return false;
	}

	@Override
	public boolean update(AdministradorDTO admin) {
		PreparedStatement statement;
		String update = "UPDATE TBL_ADMINISTRADOR SET NOMBRE_ADMINISTRADOR = ?, " + "USUARIO_ADMINISTRADOR = ?, "
				+ "PASS_ADMINISTRADOR = ?" + " WHERE ID_ADMINISTRADOR = " + admin.getId();
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, admin.getNombre());
			statement.setString(2, admin.getUsuario());
			statement.setString(3, admin.getPass());

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
	public ArrayList<AdministradorDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<AdministradorDTO> administradores = new ArrayList<>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				administradores.add(new AdministradorDTO(resultSet.getInt("ID_ADMINISTRADOR"),
						resultSet.getString("NOMBRE_ADMINISTRADOR"), resultSet.getString("USUARIO_ADMINISTRADOR"),
						resultSet.getString("PASS_ADMINISTRADOR")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return administradores;
	}

}
