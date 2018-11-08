package reportes;

import java.util.ArrayList;
import java.util.List;

import dto.FacturaDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Factura {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint reporteLleno;

	// Recibe la lista de personas para armar el reporte
	public Factura(FacturaDTO factura) {
		List<FacturaDTO> facturas = new ArrayList<>();
		facturas.add(factura);
		try {
			this.reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes\\Factura.jasper");
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, null,
					new JRBeanCollectionDataSource(facturas));
		} catch (JRException ex) {
			ex.printStackTrace();
		}
	}

	public void mostrar() {
		this.reporteViewer = new JasperViewer(this.reporteLleno, false);
		this.reporteViewer.setVisible(true);
	}
}
