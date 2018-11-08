package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PacienteDTO;
import dto.PracticaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.SesionesDAO;

public class SesionesDAOImpl implements SesionesDAO {

	private static final String readall = "SELECT * FROM tbl_sesiones natural join tbl_paciente natural join tbl_practicas";
	private static final String insert = "INSERT INTO tbl_sesiones(id_paciente, COD_PRACTICA, sesiones) VALUES(?, ?, ?)";
	private static final String delete = "DELETE FROM tbl_sesiones WHERE id_paciente = ? AND COD_PRACTICA = ?";
	private static final Conexion conexion = Conexion.getConexion();

	@Override
	public Integer readSesiones(PacienteDTO paciente, PracticaDTO practica) {
		PreparedStatement statement;
		ResultSet resultSet;
		int ret = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getInt("ID_paciente") == paciente.getIdPaciente()
						&& resultSet.getInt("COD_PRACTICA") == practica.getCodPractica()) {
					ret = resultSet.getInt("SESIONES");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return ret;
	}

	@Override
	public List<Integer> readPracticas(PacienteDTO paciente) {
		PreparedStatement statement;
		ResultSet resultSet;
		List<Integer> ret = new ArrayList<>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getInt("ID_paciente") == paciente.getIdPaciente())
					ret.add(resultSet.getInt("COD_PRACTICA"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return ret;
	}

	@Override
	public boolean insert(PacienteDTO paciente, PracticaDTO practica, int sesiones) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, paciente.getIdPaciente());
			statement.setInt(2, practica.getCodPractica());
			statement.setInt(3, sesiones);
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
	public boolean delete(PacienteDTO paciente, PracticaDTO practica) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, paciente.getIdPaciente());
			statement.setInt(2, practica.getCodPractica());
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
	public boolean update(PacienteDTO paciente, PracticaDTO practica, int sesiones) {
		PreparedStatement statement;
		try {
			String update = "UPDATE tbl_sesiones SET sesiones = \"" + sesiones + "\"";
			update += "WHERE id_paciente = " + paciente.getIdPaciente();
			update += " AND COD_PRACTICA = " + practica.getCodPractica();
			statement = conexion.getSQLConexion().prepareStatement(update);
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

}
