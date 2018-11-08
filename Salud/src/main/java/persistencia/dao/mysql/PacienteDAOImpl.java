package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ObraSocialDTO;
import dto.PacienteDTO;
import dto.PlanDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ObraSocialDAO;
import persistencia.dao.interfaz.PacienteDAO;

public class PacienteDAOImpl implements PacienteDAO {

	private static final String insert = "INSERT INTO TBL_PACIENTE(ID_PACIENTE, NOMBRE_PACIENTE, APELLIDO_PACIENTE, DNI_PACIENTE, TELEFONO_PACIENTE, EMAIL_PACIENTE, ID_OBRASOCIAL, ID_PLAN, NOMBRECONTACTO_PACIENTE, TELEFONOCONTACTO_PACIENTE, NROAFILIADO_PACIENTE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	private static final String delete = "DELETE FROM TBL_PACIENTE WHERE ID_PACIENTE = ?";
	private static final String readall = "SELECT * FROM TBL_PACIENTE";
	private static final Conexion conexion = Conexion.getConexion();

	@Override
	public boolean insert(PacienteDTO paciente) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, paciente.getIdPaciente());
			statement.setString(2, paciente.getNombre());
			statement.setString(3, paciente.getApellido());
			statement.setInt(4, paciente.getDni());
			statement.setString(5, paciente.getTelefono());
			statement.setString(6, paciente.getEmail());
			if (paciente.getObraSocial() == null)
				statement.setNull(7, java.sql.Types.INTEGER);
			else
				statement.setInt(7, paciente.getObraSocial().getIdObraSocial());
			if (paciente.getPlan() == null)
				statement.setNull(8, java.sql.Types.INTEGER);
			else
				statement.setInt(8, paciente.getPlan().getIdPlan());
			statement.setString(9, paciente.getNombreContacto());
			statement.setString(10, paciente.getTelefonoContacto());
			statement.setString(11, paciente.getNroAfiliado());

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
	public boolean delete(PacienteDTO paciente) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, paciente.getIdPaciente());
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
	public boolean update(PacienteDTO paciente) {
		PreparedStatement statement;
		String update = "UPDATE TBL_PACIENTE SET NOMBRE_PACIENTE = ?, APELLIDO_PACIENTE = ?, "
				+ "DNI_PACIENTE = ?, TELEFONO_PACIENTE = ?, EMAIL_PACIENTE = ?, " + "NOMBRECONTACTO_PACIENTE = ?, "
				+ "TELEFONOCONTACTO_PACIENTE = ?, NROAFILIADO_PACIENTE = ?, "
				+ "SEXO_PACIENTE = ?, ESTADOCIVIL_PACIENTE = ?, OCUPACION_PACIENTE = ?, NACIONALIDAD_PACIENTE = ?, DIRECCION_PACIENTE = ?, FECHANACIMIENTO_PACIENTE = ?, ID_OBRASOCIAL = ?, ID_PLAN = ?, NROAFILIADO_PACIENTE = ?"
				+ "WHERE ID_PACIENTE = ?";
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, paciente.getNombre());
			statement.setString(2, paciente.getApellido());
			statement.setInt(3, paciente.getDni());
			statement.setString(4, paciente.getTelefono());
			statement.setString(5, paciente.getEmail());
			statement.setString(6, paciente.getNombreContacto());
			statement.setString(7, paciente.getTelefonoContacto());
			statement.setString(8, paciente.getNroAfiliado());
			statement.setString(9, paciente.getSexo());
			statement.setString(10, paciente.getEstadoCivil());
			statement.setString(11, paciente.getOcupacion());
			statement.setString(12, paciente.getNacionalidad());
			statement.setString(13, paciente.getDireccion());
			statement.setTimestamp(14, paciente.getFechaNacimiento());
			if (paciente.getObraSocial() != null) {
				statement.setInt(15, paciente.getObraSocial().getIdObraSocial());
				statement.setInt(16, paciente.getPlan().getIdPlan());
				statement.setString(17, paciente.getNroAfiliado());
			} else {
				statement.setNull(15, java.sql.Types.INTEGER);
				statement.setNull(16, java.sql.Types.INTEGER);
				statement.setNull(17, java.sql.Types.VARCHAR);
			}
			statement.setInt(18, paciente.getIdPaciente());
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
	public List<PacienteDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<PacienteDTO> pacientes = new ArrayList<PacienteDTO>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			ObraSocialDAO obraDAO = new ObraSocialDAOImpl();
			List<ObraSocialDTO> obras = obraDAO.readAll();
			while (resultSet.next()) {
				ObraSocialDTO obraSocial = null;
				PlanDTO plan = null;
				Integer idObra = resultSet.getInt("ID_OBRASOCIAL");
				if (!resultSet.wasNull()) {
					for (ObraSocialDTO os : obras)
						if (os.getIdObraSocial() == idObra) {
							obraSocial = os;
							break;
						}
					Integer planObra = resultSet.getInt("ID_PLAN");
					for (PlanDTO p : obraSocial.getPlanes())
						if (p.getIdPlan() == planObra) {
							plan = p;
							break;
						}

				}
				PacienteDTO paciente = new PacienteDTO(resultSet.getInt("ID_PACIENTE"),
						resultSet.getString("NOMBRE_PACIENTE"), resultSet.getString("APELLIDO_PACIENTE"),
						resultSet.getInt("DNI_PACIENTE"), resultSet.getString("TELEFONO_PACIENTE"),
						resultSet.getString("EMAIL_PACIENTE"), obraSocial, plan,
						resultSet.getString("NOMBRECONTACTO_PACIENTE"),
						resultSet.getString("TELEFONOCONTACTO_PACIENTE"), resultSet.getString("NROAFILIADO_PACIENTE"));
				paciente.setObraSocial(obraSocial);
				paciente.setPlan(plan);
				if(obraSocial == null)
					paciente.setNroAfiliado(null);
				paciente.setDireccion(resultSet.getString("DIRECCION_PACIENTE"));
				if (resultSet.wasNull())
					paciente.setDireccion(null);
				paciente.setEstadoCivil(resultSet.getString("ESTADOCIVIL_PACIENTE"));
				if (resultSet.wasNull())
					paciente.setEstadoCivil(null);
				paciente.setSexo(resultSet.getString("SEXO_PACIENTE"));
				if (resultSet.wasNull())
					paciente.setSexo(null);
				paciente.setOcupacion(resultSet.getString("OCUPACION_PACIENTE"));
				if (resultSet.wasNull())
					paciente.setOcupacion(null);
				paciente.setNacionalidad(resultSet.getString("NACIONALIDAD_PACIENTE"));
				if (resultSet.wasNull())
					paciente.setNacionalidad(null);
				paciente.setFechaNacimiento(resultSet.getTimestamp("FECHANACIMIENTO_PACIENTE"));
				if (resultSet.wasNull())
					paciente.setFechaNacimiento(null);
				if (!pacientes.contains(paciente))
					pacientes.add(paciente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return pacientes;
	}

}
