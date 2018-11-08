package reportes;

import java.util.List;

import dto.RenglonComprobantePractica;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ComprobantePracticaObraSocial {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint reporteLleno;

	// Recibe la lista de personas para armar el reporte
	public ComprobantePracticaObraSocial(List<RenglonComprobantePractica> comPractica) {
		try {
			this.reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes\\InformeObraSocial.jasper");
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, null,
					new JRBeanCollectionDataSource(comPractica));
		} catch (JRException ex) {
			ex.printStackTrace();
		}
	}

	public void mostrar() {
		this.reporteViewer = new JasperViewer(this.reporteLleno, false);
		this.reporteViewer.setVisible(true);
	}

}
