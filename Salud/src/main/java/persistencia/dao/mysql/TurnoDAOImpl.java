package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dto.EspecialidadDTO;
import dto.MedicoDTO;
import dto.PacienteDTO;
import dto.TurnoDTO;
import dto.TurnoDTO.Estado;
import modelo.Admin_Turno;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PacienteDAO;
import persistencia.dao.interfaz.TurnoDAO;

public class TurnoDAOImpl implements TurnoDAO {

	private static final String insert = "INSERT INTO TBL_TURNO(ID_TURNO, ID_MEDICO, ID_PACIENTE, FECHA_TURNO, ESTADO_TURNO, TURNO_AUTORIZADO, NOTIFICADO_TURNO) VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM TBL_TURNO WHERE ID_TURNO = ?";
	private static final String readall = "SELECT * FROM TBL_TURNO LEFT JOIN TBL_PACIENTE ON TBL_TURNO.ID_PACIENTE = TBL_PACIENTE.ID_PACIENTE NATURAL JOIN TBL_MEDICO NATURAL JOIN TBL_ESPECIALIDAD";
	private static final Conexion conexion = Conexion.getConexion();
	@SuppressWarnings("unused")
	private static Admin_Turno admin_turno = new Admin_Turno();

	@Override
	public boolean insert(TurnoDTO turno) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, turno.getIdTurno());
			statement.setInt(2, turno.getMedico().get_idMedico());
			if (turno.getPaciente() == null)
				statement.setNull(3, java.sql.Types.INTEGER);
			else
				statement.setInt(3, turno.getPaciente().getIdPaciente());
			statement.setTimestamp(4, turno.getFecha());
			statement.setString(5, turno.getEstado().toString());
			statement.setBoolean(6, turno.getAutorizado());
			statement.setBoolean(7, turno.isNotificadoCorreo());
			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return false;
	}

	public boolean deleteByID(String id) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, id);
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
	public boolean delete(TurnoDTO turno) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, turno.getIdTurno());
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
	public boolean update(TurnoDTO turno) {
		PreparedStatement statement;
		try {
			String update = "UPDATE TBL_TURNO SET ID_MEDICO = \"" + turno.getMedico().get_idMedico() + "\"";
			if (turno.getPaciente() == null)
				update += ", ID_PACIENTE = null";
			else
				update += ", ID_PACIENTE = \"" + turno.getPaciente().getIdPaciente() + "\"";
			update += ", FECHA_TURNO = \"" + turno.getFecha() + "\"";
			update += ", ESTADO_TURNO = \"" + turno.getEstado() + "\"";
			update += ", TURNO_AUTORIZADO = \"" + (turno.getAutorizado() ? 1 : 0) + "\"";
			update += ", NOTIFICADO_TURNO = \"" + (turno.isNotificadoCorreo() ? 1 : 0) + "\"";
			update += "where ID_TURNO = " + turno.getIdTurno();
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

	@Override
	public List<TurnoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<TurnoDTO> turnos = new ArrayList<TurnoDTO>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				EspecialidadDTO especialidad = new EspecialidadDTO(resultSet.getInt("ID_ESPECIALIDAD"),
						resultSet.getString("NOMBRE_ESPECIALIDAD"));
				MedicoDTO medico = new MedicoDTO(resultSet.getInt("ID_MEDICO"), resultSet.getString("NOMBRE_MEDICO"),
						resultSet.getString("USUARIO_MEDICO"), resultSet.getString("PASSWORD_MEDICO"), especialidad,
						resultSet.getInt("INTERVALO_MEDICO"));
				medico.set_matricula(resultSet.getString("MATRICULA"));
				PacienteDTO paciente = null;
				Integer idPaciente = resultSet.getInt("ID_PACIENTE");

				if (!resultSet.wasNull()) {
					PacienteDAO pacientedao = new PacienteDAOImpl();
					List<PacienteDTO> pacientes = pacientedao.readAll();
					for (PacienteDTO p : pacientes)
						if (p.getIdPaciente() == idPaciente)
							paciente = p;
				}
				Timestamp fecha = resultSet.getTimestamp("FECHA_TURNO");
				TurnoDTO turno = new TurnoDTO(resultSet.getInt("ID_TURNO"), medico, paciente, fecha,
						Estado.valueOf(Estado.class, resultSet.getString("ESTADO_TURNO").toUpperCase()),
						resultSet.getBoolean("TURNO_AUTORIZADO"));
				turno.setNotificadoCorreo(resultSet.getBoolean("NOTIFICADO_TURNO"));
				turnos.add(turno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return turnos;
	}

}
