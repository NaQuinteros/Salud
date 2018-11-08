package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ModuloDTO;
import dto.PracticaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PracticaDAO;

public class PracticaDAOImpl implements PracticaDAO {

	private static final String readall = "SELECT * FROM tbl_practicas join tbl_modulos on tbl_practicas.COD_MODULO=tbl_modulos.COD_MODULO";
	private static final Conexion conexion = Conexion.getConexion();

	@Override
	public List<PracticaDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<PracticaDTO> practicas = new ArrayList<>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ModuloDTO modulo = new ModuloDTO(resultSet.getInt("COD_MODULO"),
						resultSet.getString("DESCRIPCION_MODULO"));
				practicas.add(new PracticaDTO(resultSet.getInt("COD_PRACTICA"), modulo,
						resultSet.getString("DESCRIPCION_PRACTICA"), resultSet.getString("HONORARIO_PRACTICA")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();	
		}
		return practicas;
	}

	@Override
	public boolean insert(PracticaDTO paciente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(PracticaDTO paciente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(PracticaDTO paciente) {
		// TODO Auto-generated method stub
		return false;
	}

}
