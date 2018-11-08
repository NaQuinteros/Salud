package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PlanDTO;
import dto.PracticaDTO;
import dto.CoberturaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.CoberturaDAO;

public class CoberturaDAOImpl implements CoberturaDAO {

	private static final String readall = "SELECT * FROM tbl_cobertura natural join tbl_practicas natural join tbl_plan";
	private static final String insert = "INSERT INTO tbl_cobertura(ID_PLAN, COD_PRACTICA, PORCENTAJE_COBERTURA, REQUIERE_AUTORIZACION) VALUES(?, ?, ?, ?)";
	private static final String delete = "DELETE FROM tbl_cobertura WHERE ID_PLAN = ? and COD_PRACTICA = ?";
	private static final Conexion conexion = Conexion.getConexion();

	@Override
	public List<CoberturaDTO> readAll(PlanDTO plan) {
		PreparedStatement statement;
		ResultSet resultSet;
		List<CoberturaDTO> coberturas = new ArrayList<>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getInt("ID_PLAN") == plan.getIdPlan()) {
					CoberturaDTO cobertura = new CoberturaDTO(resultSet.getInt("COD_PRACTICA"),
							resultSet.getInt("PORCENTAJE_COBERTURA"), resultSet.getBoolean("REQUIERE_AUTORIZACION"));
					coberturas.add(cobertura);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return coberturas;
	}

	@Override
	public boolean insert(CoberturaDTO cobertura, PlanDTO plan) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, plan.getIdPlan());
			statement.setInt(2, cobertura.getCodPractica());
			statement.setInt(3, cobertura.getPorcentaje());
			statement.setBoolean(4, cobertura.requiereAutorizacion());
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
	public boolean delete(PracticaDTO practica, PlanDTO plan) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, plan.getIdPlan());
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
	public boolean update(CoberturaDTO cobertura, PlanDTO plan){
		PreparedStatement statement;
		try {
			String update = "UPDATE TBL_cobertura SET PORCENTAJE_COBERTURA = \"" + cobertura.getPorcentaje() + "\"";
			int requiere = (cobertura.requiereAutorizacion()) ? 1 : 0;
			update += ", REQUIERE_AUTORIZACION = \"" + requiere + "\"";
			update += "where cod_PRACTICA = \"" + cobertura.getCodPractica() + "\"";
			update += " and id_plan = \"" + plan.getIdPlan() + "\"";
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
