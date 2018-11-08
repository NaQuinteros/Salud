package dto;

import java.util.List;

public class InformeMedicoDTO {
	private MedicoDTO medico;
	private List<RenglonInformeMedicoDTO> renglones;
	
	public InformeMedicoDTO(MedicoDTO medico, List<RenglonInformeMedicoDTO> renglones){
		this.medico = medico;
		this.renglones = renglones;
	}
	
	public MedicoDTO getMedico() {
		return medico;
	}
	public void setMedico(MedicoDTO medico) {
		this.medico = medico;
	}
	public List<RenglonInformeMedicoDTO> getRenglones() {
		return renglones;
	}
	public void setRenglones(List<RenglonInformeMedicoDTO> renglones) {
		this.renglones = renglones;
	}
}
