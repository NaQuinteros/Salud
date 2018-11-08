package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ModuloDTO;
import dto.PracticaDTO;
import dto.TurnoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PracticasDelTurnoDAO;

public class PracticasDelTurnoDAOImpl implements PracticasDelTurnoDAO {

	private static final String readall = "SELECT * FROM tbl_practicasdelturno join tbl_practicas on tbl_practicasdelturno.cod_practica = tbl_practicas.cod_practica join tbl_turno on tbl_practicasdelturno.id_turno = tbl_turno.id_turno join tbl_modulos on tbl_practicas.cod_modulo = tbl_modulos.cod_modulo";
	private static final String insert = "INSERT INTO TBL_practicasdelturno(ID_TURNO, COD_PRACTICA, FECHA_AUTORIZACION, COD_AUTORIZACION) VALUES(?, ?, ?, ?)";
	private static final String delete = "DELETE FROM TBL_practicasdelturno WHERE ID_TURNO = ? AND COD_PRACTICA = ?";
	private static final Conexion conexion = Conexion.getConexion();

	@Override
	public List<PracticaDTO> readAll(TurnoDTO turno) {
		PreparedStatement statement;
		ResultSet resultSet;
		List<PracticaDTO> practicas = new ArrayList<>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getInt("ID_TURNO") == turno.getIdTurno()) {
					ModuloDTO modulo = new ModuloDTO(resultSet.getInt("COD_MODULO"),
							resultSet.getString("DESCRIPCION_MODULO"));
					PracticaDTO practica = new PracticaDTO(resultSet.getInt("COD_PRACTICA"), modulo,
							resultSet.getString("DESCRIPCION_PRACTICA"), resultSet.getString("HONORARIO_PRACTICA"));
					practica.autorizar(resultSet.getDate("FECHA_AUTORIZACION"), resultSet.getInt("COD_AUTORIZACION"));
					practica.pagar(resultSet.getDate("FECHA_AUTORIZACION"));
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
	public boolean insert(PracticaDTO practica, TurnoDTO turno) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, turno.getIdTurno());
			statement.setInt(2, practica.getCodPractica());
			if (practica.getFechaAutorizacion() == null)
				statement.setNull(3, java.sql.Types.DATE);
			else
				statement.setDate(3, practica.getFechaAutorizacion());
			if (practica.getCodigoAutorizacion() == null)
				statement.setNull(4, java.sql.Types.INTEGER);
			else
				statement.setInt(4, practica.getCodigoAutorizacion());
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
	public boolean delete(PracticaDTO practica, TurnoDTO turno) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, turno.getIdTurno());
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
	public boolean update(TurnoDTO turno, PracticaDTO practica) {
		PreparedStatement statement;
		try {
			String update = "UPDATE TBL_PRACTICASDELTURNO SET FECHA_AUTORIZACION = \"" + practica.getFechaAutorizacion()
					+ "\"";
			if (practica.getCodigoAutorizacion() != null)
				update += ", COD_AUTORIZACION = \"" + practica.getCodigoAutorizacion() + "\"";
			update += ", AUTORIZACION_PRACTICA = \"" + practica.getAutorizacion() + "\"";
			update += "WHERE ID_TURNO = " + turno.getIdTurno();
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
