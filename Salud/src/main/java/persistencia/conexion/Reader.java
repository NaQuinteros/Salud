package persistencia.conexion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Reader {

	private String user;
	private String password;
	private String ip;
	private String port;

	public Reader() {
		readXML();
	}

	public void readXML() {
		File f = new File("config.xml");
		if (!f.exists()) {
			this.user = null;
			this.password = null;
			this.ip = null;
			this.port = null;
		} else {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("config.xml");
			try {
				Document readDoc = (Document) builder.build(xmlFile);
				this.user = readDoc.getRootElement().getChild("username").getText();
				this.password = readDoc.getRootElement().getChild("password").getText();
				this.ip = readDoc.getRootElement().getChild("ip").getText();
				this.port = readDoc.getRootElement().getChild("port").getText();

			} catch (JDOMException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void writeXML(String usr, String pass, String i, String p) {
		Document doc = new Document();

		Element root = new Element("config");
		doc.setRootElement(root);

		Element username = new Element("username");
		Element password = new Element("password");
		Element ip = new Element("ip");
		Element port = new Element("port");

		username.addContent(usr);
		password.addContent(pass);
		ip.addContent(i);
		port.addContent(p);

		root.addContent(username);
		root.addContent(password);
		root.addContent(ip);
		root.addContent(port);

		XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());

		try {

			xmlOutput.output(doc, new FileOutputStream(new File("config.xml")));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPort() {
		return ip;
	}

	public void setPort(String ip) {
		this.ip = ip;
	}

	public String getIP() {
		return port;
	}

	public void setIP(String port) {
		this.port = port;
	}

}
