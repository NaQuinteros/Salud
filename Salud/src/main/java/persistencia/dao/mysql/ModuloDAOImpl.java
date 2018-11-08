package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ModuloDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ModuloDAO;

public class ModuloDAOImpl implements ModuloDAO {

	private static final String insert = "INSERT INTO TBL_MODULOS(COD_MODULO, DESCRIPCION_MODULO) VALUES(?, ?)";
	private static final String delete = "DELETE FROM TBL_MODULOS WHERE COD_MODULO = ?";
	private static final String readall = "SELECT * FROM TBL_MODULOS";
	private static final Conexion conexion = Conexion.getConexion();

	@Override
	public boolean insert(ModuloDTO modulo) {
		PreparedStatement statement;
		try {

			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, modulo.getCodModulo());
			statement.setString(2, modulo.getDescripcionModulo());

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
	public boolean delete(ModuloDTO modulo) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, modulo.getCodModulo());
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
	public List<ModuloDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		List<ModuloDTO> modulos = new ArrayList<>();
		System.out.println("Hola");
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ModuloDTO modulo = new ModuloDTO(resultSet.getInt("COD_MODULO"), resultSet.getString("DESCRIPCION_MODULO"));
				modulos.add(modulo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return modulos;
	}

	@Override
	public boolean update(ModuloDTO paciente) {
		// TODO Auto-generated method stub
		return false;
	}

}
