package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PacienteDTO;
import dto.PatologiaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PatologiaDePacienteDAO;

public class PatologiaDePacienteDAOImpl implements PatologiaDePacienteDAO {

	private static final Conexion conexion = Conexion.getConexion();
	private final String insert = "INSERT INTO TBL_PATOLOGIAPACIENTE(ID_PATOLOGIA, ID_PACIENTE, IMPORTANTE_PATOLOGIA) VALUES(?, ?, ?)";
	private final String delete = "DELETE FROM TBL_PATOLOGIAPACIENTE WHERE ID_PACIENTE = ? AND ID_PATOLOGIA = ?";
	private final String readall = "SELECT * FROM TBL_PACIENTE NATURAL JOIN TBL_PATOLOGIAS NATURAL JOIN TBL_PATOLOGIAPACIENTE";

	@Override
	public List<PatologiaDTO> readAll(PacienteDTO p) {
		PreparedStatement statement;
		ResultSet resultSet;
		List<PatologiaDTO> patologias = new ArrayList<>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getInt("ID_PACIENTE") == p.getIdPaciente()) {
					PatologiaDTO patologia = new PatologiaDTO(resultSet.getString("ID_PATOLOGIA"),
							resultSet.getString("DESC_PATOLOGIA"));
					patologia.setImportante(resultSet.getBoolean("IMPORTANTE_PATOLOGIA"));
					patologias.add(patologia);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return patologias;
	}

	@Override
	public boolean delete(PatologiaDTO patologia, PacienteDTO paciente) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, paciente.getIdPaciente());
			statement.setString(2, patologia.getIdPatologia());
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
	public boolean insert(PatologiaDTO patologia, PacienteDTO paciente) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, patologia.getIdPatologia());
			statement.setInt(2, paciente.getIdPaciente());
			statement.setBoolean(3, patologia.isImportante());

			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return false;
	}

}
