package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PatologiaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PatologiaDAO;

public class PatologiaDAOImpl implements PatologiaDAO {

	private final String readall = "SELECT * FROM TBL_PATOLOGIAS";
	private static final Conexion conexion = Conexion.getConexion();
	
	@Override
	public List<PatologiaDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<PatologiaDTO> patologias = new ArrayList<>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				PatologiaDTO patologia = new PatologiaDTO(resultSet.getString("ID_PATOLOGIA"), resultSet.getString("DESC_PATOLOGIA"));
				patologias.add(patologia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();	
		}
		return patologias;
	}

}
