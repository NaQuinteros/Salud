package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.EspecialidadDTO;
import dto.MedicoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.MedicoDAO;

public class MedicoDAOImpl implements MedicoDAO {

	private static final String insert = "INSERT INTO TBL_MEDICO(ID_MEDICO, NOMBRE_MEDICO, ID_ESPECIALIDAD, USUARIO_MEDICO, PASSWORD_MEDICO, INTERVALO_MEDICO, MATRICULA) VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM TBL_MEDICO WHERE ID_MEDICO = ?";
	private static final String readall = "SELECT * FROM TBL_MEDICO, TBL_ESPECIALIDAD WHERE TBL_MEDICO.ID_ESPECIALIDAD=TBL_ESPECIALIDAD.ID_ESPECIALIDAD";
	private static final Conexion conexion = Conexion.getConexion();

	@Override
	public boolean insert(MedicoDTO medico) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, medico.get_idMedico());
			statement.setString(2, medico.get_nombre());
			statement.setInt(3, medico.get_especialidad().getIdEspecialidad());
			statement.setString(4, medico.get_nombreUsuario());
			statement.setString(5, medico.get_password());
			if (medico.get_intervaloTurno() == null) {
				statement.setNull(6, 0);
			} else {
				statement.setInt(6, medico.get_intervaloTurno());
			}
			statement.setString(7, medico.get_matricula());

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
	public boolean delete(MedicoDTO medico) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, medico.get_idMedico());
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

	public boolean update(MedicoDTO medico) {
		PreparedStatement statement;
		String update = "UPDATE TBL_MEDICO SET NOMBRE_MEDICO = ?, " + "ID_ESPECIALIDAD = ?, USUARIO_MEDICO = ?, "
				+ "PASSWORD_MEDICO = ?, MATRICULA = ?, INTERVALO_MEDICO = ? WHERE ID_MEDICO = "+medico.get_idMedico();
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, medico.get_nombre());
			statement.setInt(2, medico.get_especialidad().getIdEspecialidad());
			statement.setString(3, medico.get_nombreUsuario());
			statement.setString(4, medico.get_password());
			statement.setString(5, medico.get_matricula());
			statement.setInt(6, medico.get_intervaloTurno());

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
	public List<MedicoDTO> readAll() {

		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<MedicoDTO> medicos = new ArrayList<MedicoDTO>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				MedicoDTO medico = new MedicoDTO(resultSet.getInt("ID_MEDICO"), resultSet.getString("NOMBRE_MEDICO"),
						resultSet.getString("USUARIO_MEDICO"), resultSet.getString("PASSWORD_MEDICO"),
						new EspecialidadDTO(resultSet.getInt("ID_ESPECIALIDAD"),
								resultSet.getString("NOMBRE_ESPECIALIDAD")),
						resultSet.getInt("INTERVALO_MEDICO"));
				medico.set_matricula(resultSet.getString("MATRICULA"));
				medicos.add(medico);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return medicos;
	}

}
