package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

import presentacion.vista.VentanaC;

	public class Conexion 
	{
		private static Conexion instancia;
		private Connection conexion;
		private Reader reader;
		
		public Conexion()
		{
			reader = new Reader();
			String statement = "jdbc:mysql://" +reader.getIP() + ":" + reader.getPort()  + "/salud?useSSL=no";
			try
			{
				conexion = DriverManager.getConnection(statement, reader.getUser(), reader.getPassword());
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Hubo un error al conectarse a la base de datos, por favor configure el archivo config.xml en la carpeta de la aplicación.");
				@SuppressWarnings("unused")
				VentanaC ventana = new VentanaC();
				e.printStackTrace();
			}
		}
		
		public static Conexion getConexion()   
		{								
			if(instancia == null)
			{
				instancia = new Conexion();
			}
			return instancia;
		}

		public Connection getSQLConexion() 
		{
			return conexion;
		}
		
		public void cerrarConexion()
		{
			instancia = null;
		}
	}
