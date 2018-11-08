
package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PacienteDTO;
import dto.RenglonHistoriaClinicaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.RenglonHistoriaClinicaDAO;

public class RenglonHistoriaClinicaDAOImpl implements RenglonHistoriaClinicaDAO {

	static final String insert = "INSERT INTO TBL_RENGLONHISTORIACLINICA(ID_ENTRADA, FECHA_ENTRADA, MEDICO_ENTRADA, DIAGNOSTICO_ENTRADA, ID_PACIENTE, ESPECIALIDAD_ENTRADA) VALUES(?, ?, ?, ?, ?, ?)";
	static final String readof = "SELECT * FROM TBL_RENGLONHISTORIACLINICA NATURAL JOIN TBL_PACIENTE WHERE DNI_PACIENTE = ?";

	Conexion conexion = Conexion.getConexion();

	@Override
	public boolean insert(RenglonHistoriaClinicaDTO r) {
		PreparedStatement statement = null;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, r.getIdEntrada());
			statement.setTimestamp(2, r.getFecha());
			statement.setString(3, r.getMedico());
			statement.setString(4, r.getDiagnostico());
			statement.setInt(5, r.getIdPaciente());
			statement.setString(6, r.getEspecialidad());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return true;
	}

	@Override
	public List<RenglonHistoriaClinicaDTO> readAll(PacienteDTO paciente) {
		PreparedStatement statement = null;
		ResultSet resultset;
		List<RenglonHistoriaClinicaDTO> renglones = new ArrayList<RenglonHistoriaClinicaDTO>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readof);
			statement.setInt(1, paciente.getDni());
			resultset = statement.executeQuery();
			while (resultset.next()) {
				RenglonHistoriaClinicaDTO renglon = new RenglonHistoriaClinicaDTO(resultset.getInt("ID_ENTRADA"),
						resultset.getTimestamp("FECHA_ENTRADA"), resultset.getString("MEDICO_ENTRADA"),
						resultset.getString("DIAGNOSTICO_ENTRADA"), resultset.getInt("ID_PACIENTE"), resultset.getString("ESPECIALIDAD_ENTRADA"));
				renglones.add(renglon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return renglones;
	}
}
