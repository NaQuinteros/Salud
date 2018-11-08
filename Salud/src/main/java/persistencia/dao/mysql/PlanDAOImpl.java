package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CoberturaDTO;
import dto.ObraSocialDTO;
import dto.PlanDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.CoberturaDAO;
import persistencia.dao.interfaz.PlanDAO;

public class PlanDAOImpl implements PlanDAO {

	private static final String insert = "INSERT INTO TBL_PLAN(id_plan, nombre_plan, id_obrasocial) VALUES(?, ?, ?)";
	private static final String delete = "DELETE FROM TBL_PLAN WHERE id_plan = ?";
	private static final String readall = "SELECT * FROM TBL_PLAN LEFT JOIN TBL_COBERTURA ON TBL_PLAN.ID_PLAN = TBL_COBERTURA.ID_PLAN";
	private static final Conexion conexion = Conexion.getConexion();

	@Override
	public boolean insert(PlanDTO plan, ObraSocialDTO obra) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, plan.getIdPlan());
			statement.setString(2, plan.getNombrePlan());
			statement.setInt(3, obra.getIdObraSocial());
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
	public boolean delete(PlanDTO plan) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, plan.getIdPlan());
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
	public List<PlanDTO> readAll(ObraSocialDTO obra) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		List<PlanDTO> planes = new ArrayList<>();
		List<CoberturaDTO> coberturas = new ArrayList<>();
		CoberturaDAO coberturaDao = new CoberturaDAOImpl();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getInt("ID_OBRASOCIAL") == obra.getIdObraSocial()) {
					PlanDTO plan = new PlanDTO(resultSet.getInt("ID_PLAN"), resultSet.getString("NOMBRE_PLAN"));
					coberturas = coberturaDao.readAll(plan);
					plan.setCobertura(coberturas);
					if (!planes.contains(plan))
						planes.add(plan);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return planes;
	}

	@Override
	public boolean update(PlanDTO plan) {
		return false;
	}

}
