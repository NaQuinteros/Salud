package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.InternacionesDTO;
import dto.MedicoDTO;
import dto.ModuloDTO;
import dto.PracticaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PracticasDeInternacionDAO;

public class PracticasDeInternacionDAOImpl implements PracticasDeInternacionDAO {

	private static final String readall = "SELECT * FROM TBL_PRACTICAS NATURAL JOIN TBL_INTERNACIONES NATURAL JOIN TBL_PRACTICASDEINTERNACION natural join tbl_modulos natural join tbl_medico";
	private static final String insert = "INSERT INTO TBL_PRACTICASDEINTERNACION(ID_RESERVA, COD_PRACTICA, FECHA_AUTORIZACION, COD_AUTORIZACION, ID_MEDICO) VALUES(?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM TBL_PRACTICASDEINTERNACION WHERE ID_INTERNACION = ? AND COD_PRACTICA = ?";
	private static final Conexion conexion = Conexion.getConexion();

	@Override
	public List<PracticaDTO> readAll(InternacionesDTO internacion) {
		PreparedStatement statement;
		ResultSet resultSet;
		List<PracticaDTO> practicas = new ArrayList<>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getInt("ID_RESERVA") == internacion.getId()) {
					ModuloDTO modulo = new ModuloDTO(resultSet.getInt("COD_MODULO"),
							resultSet.getString("DESCRIPCION_MODULO"));
					PracticaDTO practica = new PracticaDTO(resultSet.getInt("COD_PRACTICA"), modulo,
							resultSet.getString("DESCRIPCION_PRACTICA"), resultSet.getString("HONORARIO_PRACTICA"));
					practica.autorizar(resultSet.getDate("FECHA_AUTORIZACION"), resultSet.getInt("COD_AUTORIZACION"));
					practicas.add(practica);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return practicas;
	}

	@Override
	public boolean insert(PracticaDTO practica, InternacionesDTO internacion, MedicoDTO medico) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, internacion.getId());
			statement.setInt(2, practica.getCodPractica());
			if (practica.getFechaAutorizacion() == null)
				statement.setNull(3, java.sql.Types.DATE);
			else
				statement.setDate(3, practica.getFechaAutorizacion());
			if (practica.getCodigoAutorizacion() == null)
				statement.setNull(4, java.sql.Types.INTEGER);
			else
				statement.setInt(4, practica.getCodigoAutorizacion());
			statement.setInt(5, medico.get_idMedico());
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
	public boolean delete(PracticaDTO practica, InternacionesDTO internacion) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, internacion.getId());
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
	public boolean update(InternacionesDTO internacion, PracticaDTO practica) {
		PreparedStatement statement;
		try {
			String update = "UPDATE TBL_PRACTICASDEINTERNACION SET FECHA_AUTORIZACION = \""
					+ practica.getFechaAutorizacion() + "\"";
			update += "WHERE ID_RESERVA = " + internacion.getId();
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

	@Override
	public MedicoDTO getMedico(InternacionesDTO internacion, PracticaDTO practica) {
		PreparedStatement statement;
		ResultSet resultSet;
		MedicoDTO ret = null;
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next())
				if (resultSet.getInt("ID_RESERVA") == internacion.getId()
						&& resultSet.getInt("COD_PRACTICA") == practica.getCodPractica()) {
					ret = new MedicoDTO(resultSet.getInt("ID_MEDICO"), resultSet.getString("NOMBRE_MEDICO"), null, null, null, 0);
					ret.set_matricula(resultSet.getString("MATRICULA"));
					break;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return ret;

	}

}