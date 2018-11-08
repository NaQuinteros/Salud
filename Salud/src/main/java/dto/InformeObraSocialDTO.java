package dto;

import java.util.List;

public class InformeObraSocialDTO {
	private ObraSocialDTO obra;
	private List<RenglonInformeObraSocialDTO> renglones;
	
	public InformeObraSocialDTO(ObraSocialDTO obra, List<RenglonInformeObraSocialDTO> renglones){
		this.obra = obra;
		this.renglones = renglones;
	}

	public ObraSocialDTO getObra() {
		return obra;
	}

	public void setObra(ObraSocialDTO obra) {
		this.obra = obra;
	}

	public List<RenglonInformeObraSocialDTO> getRenglones() {
		return renglones;
	}

	public void setRenglones(List<RenglonInformeObraSocialDTO> renglones) {
		this.renglones = renglones;
	}
	

}
