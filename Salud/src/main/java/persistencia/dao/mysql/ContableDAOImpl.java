package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ContableDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ContableDAO;

public class ContableDAOImpl implements ContableDAO {

	private static final String insert = "INSERT INTO TBL_CONTABLE(ID_CONTABLE, NOMBRE_CONTABLE, USUARIO_CONTABLE, PASS_CONTABLE) VALUES( ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM TBL_CONTABLE WHERE ID_CONTABLE = ?";
	private static final String readall = "SELECT * FROM TBL_CONTABLE";
	private static final Conexion conexion = Conexion.getConexion();

	@Override
	public boolean insert(ContableDTO contable) {
		PreparedStatement statement;
		try {

			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, contable.get_idContable());
			statement.setString(2, contable.get_nombre());
			statement.setString(3, contable.get_nombreUsuario());
			statement.setString(4, contable.get_password());

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
	public boolean delete(ContableDTO contable) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, contable.get_idContable());
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
	public boolean update(ContableDTO contable) {
		PreparedStatement statement;
		String update = "UPDATE TBL_CONTABLE SET NOMBRE_CONTABLE = ?, " + "USUARIO_CONTABLE = ?, "
				+ "PASS_CONTABLE = ?" + " WHERE ID_CONTABLE = " + contable.get_idContable();
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, contable.get_nombre());
			statement.setString(2, contable.get_nombreUsuario());
			statement.setString(3, contable.get_password());

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
	public List<ContableDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ContableDTO> contables = new ArrayList<>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				contables.add(new ContableDTO(resultSet.getInt("ID_CONTABLE"), resultSet.getString("NOMBRE_CONTABLE"),
						resultSet.getString("USUARIO_CONTABLE"), resultSet.getString("PASS_CONTABLE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return contables;
	}

}
