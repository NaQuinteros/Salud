package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ModuloDTO;
import dto.PracticaDTO;
import dto.RenglonHistoriaClinicaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.RenglonPracticasDAO;

public class RenglonPracticasDAOImpl implements RenglonPracticasDAO{

	private static final String insert = "INSERT INTO TBL_RENGLONPRACTICAS(ID_ENTRADA, COD_PRACTICA) VALUES(?, ?)";
	private static final String readall = "SELECT * FROM TBL_PRACTICAS natural join TBL_RENGLONHISTORIACLINICA natural join TBL_RENGLONPRACTICAS natural join TBL_MODULOS";
	private static final Conexion conexion = Conexion.getConexion();

	@Override
	public boolean insert(PracticaDTO practica, RenglonHistoriaClinicaDTO renglon) {
		PreparedStatement statement;
		try {
			
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, renglon.getIdEntrada());
			statement.setInt(2, practica.getCodPractica());

			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	@Override
	public List<PracticaDTO> readAll(RenglonHistoriaClinicaDTO renglon) {
		PreparedStatement statement;
		ResultSet resultSet;
		List<PracticaDTO> practicas = new ArrayList<>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getInt("ID_ENTRADA") == renglon.getIdEntrada()) {
					ModuloDTO modulo = new ModuloDTO(resultSet.getInt("COD_MODULO"),
							resultSet.getString("DESCRIPCION_MODULO"));
					PracticaDTO practica = new PracticaDTO(resultSet.getInt("COD_PRACTICA"), modulo,
							resultSet.getString("DESCRIPCION_PRACTICA"), resultSet.getString("HONORARIO_PRACTICA"));
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

}
