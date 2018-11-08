package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ObraSocialDTO;
import dto.PlanDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ObraSocialDAO;
import persistencia.dao.interfaz.PlanDAO;

public class ObraSocialDAOImpl implements ObraSocialDAO {

	private static final String insert = "INSERT INTO TBL_OBRASOCIAL(id_obrasocial, nombre_obrasocial) VALUES(?, ?)";
	private static final String delete = "DELETE FROM TBL_OBRASOCIAL WHERE id_obrasocial = ?";
	private static final String readall = "SELECT * FROM TBL_OBRASOCIAL LEFT JOIN TBL_PLAN ON TBL_OBRASOCIAL.ID_OBRASOCIAL = TBL_PLAN.ID_OBRASOCIAL";
	private static final Conexion conexion = Conexion.getConexion();
	private PlanDAO planDao = new PlanDAOImpl();

	@Override
	public boolean insert(ObraSocialDTO obra) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, obra.getIdObraSocial());
			statement.setString(2, obra.getNombreObraSocial());
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
	public boolean delete(ObraSocialDTO obra) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			for (PlanDTO plan : obra.getPlanes())
				planDao.delete(plan);
			statement.setInt(1, obra.getIdObraSocial());
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
	public List<ObraSocialDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		List<ObraSocialDTO> obras = new ArrayList<>();
		List<PlanDTO> planes = new ArrayList<>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ObraSocialDTO obra = new ObraSocialDTO(resultSet.getInt("ID_OBRASOCIAL"),
						resultSet.getString("NOMBRE_OBRASOCIAL"));
				planes = planDao.readAll(obra);
				obra.setPlanes(planes);
				if (!obras.contains(obra)) {
					obras.add(obra);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}

		return obras;
	}

	@Override
	public boolean update(ObraSocialDTO obra) {
		return false;
	}

}
