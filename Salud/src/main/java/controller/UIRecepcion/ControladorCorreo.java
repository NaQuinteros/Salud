package controller.UIRecepcion;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

import dto.CorreoDTO;
import dto.TurnoDTO;
import modelo.Admin_Correo;

public class ControladorCorreo implements Runnable {

	// public static void main(String[] args) {
	// ControladorCorreo c = new ControladorCorreo();
	// c.enviarCorreo(new CorreoDTO("grela.lucas@gmail.com", "gayrvgxhzxwqllfy",
	// "grela.lucas@gmail.com",
	// "Mail automatico", "OK"));
	// }
	// private String password = "gayrvgxhzxwqllfy";

	private ArrayList<TurnoDTO> turnos;
	// Si es true es mensaje de asignacion de turno, false de cancelacion;
	private boolean f;
	private CorreoDTO enviador;
	private Admin_Correo admin;

	public ControladorCorreo(ArrayList<TurnoDTO> turnos, boolean f) {
		this.turnos = turnos;
		this.f = f;
		admin = new Admin_Correo();
		enviador = admin.obtenerEnviador();
	}

	@Override
	public void run() {

		if (enviador.getUsuarioCorreo() != null) {

			for (TurnoDTO turno : this.turnos) {
				CorreoDTO c;
				if (this.f) {
					c = new CorreoDTO(enviador.getUsuarioCorreo(), enviador.getPassword(),
							turno.getPaciente().getEmail(), "SiSalud - Recordatorio de Turno",
							"Usted tiene un turno mañana a las " + turno.getHoraString() + ", con el Doctor "
									+ turno.getMedico().get_nombre() + ".");
				} else {
					c = new CorreoDTO(enviador.getUsuarioCorreo(), enviador.getPassword(),
							turno.getPaciente().getEmail(), "SiSalud - Turno Cancelado",
							"Su turno con el Doctor " + turno.getMedico().get_nombre()
									+ " fue cancelado, por favor comuníquese con el hospital.");
				}
				try {
					Properties p = new Properties();
					p.put("mail.smtp.host", "smtp.gmail.com");
					p.setProperty("mail.smtp.starttls.enable", "true");
					p.setProperty("mail.smtp.port", "587");
					p.setProperty("mail.smtp.user", c.getUsuarioCorreo());
					p.setProperty("mail.smtp.auth", "true");

					Session s = Session.getDefaultInstance(p, null);
					BodyPart texto = new MimeBodyPart();
					texto.setText(c.getMensaje());
					MimeMultipart m = new MimeMultipart();
					m.addBodyPart(texto);

					MimeMessage mensaje = new MimeMessage(s);
					mensaje.setFrom(new InternetAddress(c.getUsuarioCorreo()));
					mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(c.getDestino()));
					mensaje.setSubject(c.getAsunto());
					mensaje.setContent(m);

					Transport t = s.getTransport("smtp");
					t.connect(c.getUsuarioCorreo(), c.getPassword());
					t.sendMessage(mensaje, mensaje.getAllRecipients());
					t.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Por favor solicite al administrador configurar el correo de notificación de turnos");
		}
	}
}
