package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.HabitacionDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.HabitacionDAO;

public class HabitacionDAOImpl implements HabitacionDAO {

	private static final String insert = "INSERT INTO TBL_HABITACION(ID_HABITACION, NOMBRE_HABITACION, DESCRIPCION_HABITACION) VALUES(?, ?, ?)";
	private static final String delete = "DELETE FROM TBL_HABITACION WHERE ID_HABITACION = ?";
	private static final String readall = "SELECT * FROM TBL_HABITACION";
	private static final Conexion conexion = Conexion.getConexion();

	@Override
	public boolean insert(HabitacionDTO habitacion) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, habitacion.getId());
			statement.setString(2, habitacion.getNombre());
			if (habitacion.getDesc() == null)
				statement.setNull(3, java.sql.Types.VARCHAR);
			else
				statement.setString(3, habitacion.getDesc());
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
	public boolean delete(HabitacionDTO habitacion) {
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
		} finally {
			conexion.cerrarConexion();
		}
		return false;

	}

	@Override
	public List<HabitacionDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<HabitacionDTO> habitaciones = new ArrayList<HabitacionDTO>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				habitaciones.add(new HabitacionDTO(resultSet.getInt("ID_HABITACION"),
						resultSet.getString("NOMBRE_HABITACION"), resultSet.getString("DESCRIPCION_HABITACION")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return habitaciones;
	}

	@Override
	public boolean update(HabitacionDTO habitacion) {
		PreparedStatement statement;
		String update = "UPDATE TBL_HABITACION SET NOMBRE_HABITACION = ?, DESCRIPCION_HABITACION = ? WHERE ID_HABITACION = "
				+ habitacion.getId();
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, habitacion.getNombre());
			statement.setString(2, habitacion.getDesc());

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
