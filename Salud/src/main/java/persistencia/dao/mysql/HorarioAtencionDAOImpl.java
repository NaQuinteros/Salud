package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.HorarioAtencionDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.HorarioAtencionDAO;

public class HorarioAtencionDAOImpl implements HorarioAtencionDAO {

	private static final String insert = "INSERT INTO TBL_HORARIOATENCION(ID_HORARIOATENCION, DIASEMANA_HORARIOATENCION, HORAINICIO_HORARIOATENCION, HORAFIN_HORARIOATENCION, ID_MEDICO) VALUES(?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM TBL_HORARIOATENCION WHERE ID_HORARIOATENCION = ?";
	private static final String readall = "SELECT * FROM TBL_HORARIOATENCION";
	private static final Conexion conexion = Conexion.getConexion();

	@Override
	public boolean insert(HorarioAtencionDTO horario) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, horario.getIdHorario());
			statement.setInt(2, horario.getDiaSemana());
			statement.setInt(3, horario.getHoraInicio());
			statement.setInt(4, horario.getHoraFin());
			statement.setInt(5, horario.get_idMedico());
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
	public boolean delete(HorarioAtencionDTO horario) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, horario.getIdHorario());
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
	public boolean update(HorarioAtencionDTO horario) {

		return false;
	}

	@Override
	public List<HorarioAtencionDTO> readAll() {

		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<HorarioAtencionDTO> horarios = new ArrayList<>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				horarios.add(new HorarioAtencionDTO(resultSet.getInt("ID_HORARIOATENCION"),
						resultSet.getInt("DIASEMANA_HORARIOATENCION"), resultSet.getInt("HORAINICIO_HORARIOATENCION"),
						resultSet.getInt("HORAFIN_HORARIOATENCION"), resultSet.getInt("ID_MEDICO")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally //
		{
			conexion.cerrarConexion();
		}
		return horarios;
	}

	@Override
	public boolean delete(int horarioID) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, horarioID);
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

}
