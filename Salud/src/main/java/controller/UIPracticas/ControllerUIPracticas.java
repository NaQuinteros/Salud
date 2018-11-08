package controller.UIPracticas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import dto.PracticaDTO;
import modelo.Admin_Practica;
import presentacion.vista.UIPracticas;
import presentacion.vista._ConsultarPractica;

public class ControllerUIPracticas {

	private UIPracticas uiPracticas;
	private Admin_Practica admin_practica;
	private _ConsultarPractica guiReceptor;
	private ArrayList<PracticaDTO> practicas;

	public ControllerUIPracticas(UIPracticas uiPracticas, _ConsultarPractica guiReceptor) {
		this.uiPracticas = uiPracticas;
		this.guiReceptor = guiReceptor;
		this.practicas = new ArrayList<>();

		try {
			admin_practica = new Admin_Practica();
		} catch (IOException e) {
			e.printStackTrace();
		}
		inicializar();
	}

	public void agregarPracticas(List<PracticaDTO> practicasAdjuntar) {
		for (PracticaDTO p : practicasAdjuntar) {
			practicas.add(p);
		}
		llenarTabla();
	}

	private void inicializar() {
		this.controllerBtnCodigo();
		this.controllerBtnDesc();
		this.controllerBtnOk();
		this.controllerBtnQuitar();
		llenarJTextCod();
		llenarJTextDesc();
	}

	private void llenarJTextCod() {
		for (PracticaDTO practica : admin_practica.obtenerPracticas()) {
			this.uiPracticas.getCmbCod().addItem(String.valueOf(practica.getCodPractica()));
		}
		this.uiPracticas.getCmbCod().setSelectedIndex(-1);
		AutoCompleteDecorator.decorate(this.uiPracticas.getCmbCod());
	}

	private void llenarJTextDesc() {
		for (PracticaDTO practica : admin_practica.obtenerPracticas()) {
			this.uiPracticas.getCmbDesc().addItem(practica.getDescripcionPractica());
		}
		this.uiPracticas.getCmbDesc().setSelectedIndex(-1);
		AutoCompleteDecorator.decorate(this.uiPracticas.getCmbDesc());
	}

	public void controllerBtnCodigo() {
		this.uiPracticas.getBtnAgregar_cod().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (uiPracticas.getCmbCod().getSelectedIndex() < 0)
					JOptionPane.showMessageDialog(null, "Ingrese el código de la práctica");
				else {
					int aux = practicas.size();
					for (PracticaDTO p : admin_practica.obtenerPracticas()) {
						if (p.getCodPractica() == Integer.valueOf((String) uiPracticas.getCmbCod().getSelectedItem())) {
							practicas.add(p);
						}
					}
					if (practicas.size() != aux)
						llenarTabla();
					else
						JOptionPane.showMessageDialog(null, "La práctica que ingresó no existe");
				}
			}
		});
	}

	public void controllerBtnDesc() {
		this.uiPracticas.getBtnAgregar_desc().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int aux = practicas.size();
				for (PracticaDTO p : admin_practica.obtenerPracticas()) {
					if (uiPracticas.getCmbDesc().getSelectedItem().equals(p.getDescripcionPractica())) {
						practicas.add(p);
					}
				}
				if (practicas.size() != aux)
					llenarTabla();
				else
					JOptionPane.showMessageDialog(null, "La práctica que ingresó no existe");
			}

		});
	}

	private void controllerBtnQuitar() {
		this.uiPracticas.getBtnQuitar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (uiPracticas.getTable().getSelectedRow() != -1) {

					int cod = (int) uiPracticas.getTable().getValueAt(uiPracticas.getTable().getSelectedRow(), 1);
					for (PracticaDTO p : practicas) {
						if (p.getCodPractica() == cod) {
							practicas.remove(p);
							break;
						}
					}
					llenarTabla();
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una práctica en la tabla");
				}
			}

		});
	}

	public void controllerBtnOk() {
		this.uiPracticas.getBtnOk().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				guiReceptor.entregarPracticas(practicas);
				uiPracticas.dispose();
			}

		});
	}

	public void llenarTabla() {
		this.uiPracticas.getTableModel().setRowCount(0);
		Collections.sort(practicas);
		for (PracticaDTO p : this.practicas) {
			Object[] fila = { p.getModulo().getDescripcionModulo(), p.getCodPractica(), p.getDescripcionPractica() };
			this.uiPracticas.getTableModel().addRow(fila);
		}
	}

}
