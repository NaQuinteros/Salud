package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.EspecialidadDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.EspecialidadDAO;

public class EspecialidadDAOImpl implements EspecialidadDAO {

	private static final String insert = "INSERT INTO TBL_ESPECIALIDAD(ID_ESPECIALIDAD, NOMBRE_ESPECIALIDAD) VALUES(?, ?)";
	private static final String delete = "DELETE FROM TBL_ESPECIALIDAD WHERE ID_ESPECIALIDAD = ?";
	private static final String readall = "SELECT * FROM TBL_ESPECIALIDAD";
	private static final Conexion conexion = Conexion.getConexion();

	@Override
	public boolean insert(EspecialidadDTO especialidad) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, especialidad.getIdEspecialidad());
			statement.setString(2, especialidad.getNombreEspecialidad());

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
	public boolean delete(EspecialidadDTO especialidad) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, especialidad.getIdEspecialidad());
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
	public boolean update(EspecialidadDTO especialidad) {
		PreparedStatement statement;
		String update = "UPDATE TBL_ESPECIALIDAD SET NOMBRE_ESPECIALIDAD = ? WHERE ID_ESPECIALIDAD = "
				+ especialidad.getIdEspecialidad();
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, especialidad.getNombreEspecialidad());

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
	public List<EspecialidadDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<EspecialidadDTO> especialidades = new ArrayList<>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				especialidades.add(new EspecialidadDTO(resultSet.getInt("ID_ESPECIALIDAD"),
						resultSet.getString("NOMBRE_ESPECIALIDAD")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return especialidades;
	}

}
