package es.studium.Clase;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JLabel;
import java.awt.TextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.awt.event.ActionEvent;

/**
 * En esta clase, consultamos los artículos
 * 
 * @author Moisés Adamuz
 *
 */
public class ConsultarArticulos extends JFrame {

	TextArea consulta = new TextArea();
	private JPanel contentPane;

	/**
	 * Creamos la interfaz de la consulta
	 */
	public ConsultarArticulos() {
		setTitle("Consultar Art\u00EDculos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 629, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		// Conectar a la base de datos
		Connection con = conectar();
		// Sacar la información
		rellenarTextArea(con, consulta);

		JLabel lblNewLabel = new JLabel("Art\u00EDculos:");
		lblNewLabel.setBounds(20, 92, 59, 14);
		contentPane.add(lblNewLabel);

		consulta.setBounds(85, 34, 365, 160);
		contentPane.add(consulta);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					// Compilar el informe generando fichero jasper
					JasperCompileManager.compileReportToFile("ArticulosTickets.jrxml");
					System.out.println("Fichero ArticulosTickets.jasper generado CORRECTAMENTE!");
					// Objeto para guardar parámetros necesarios para el informe
					HashMap<String, Object> parametros = new HashMap<String, Object>();
					// Cargar el informe compilado
					JasperReport report = (JasperReport) JRLoader.loadObjectFromFile("ArticulosTickets.jasper");
					// Conectar a la base de datos para sacar la información
					Class.forName("com.mysql.jdbc.Driver");
					String servidor = "jdbc:mysql://localhost:3306/Tiendecita?useSSL=false";
					String usuarioDB = "root";
					String passwordDB = "Studium2020;";
					Connection conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);

					String consulta = "SELECT * FROM articulos";
					// PreparedStatement sentencia= (PreparedStatement)
					// conexion.prepareStatement(consulta);
					// ResultSet rs = sentencia.executeQuery();
					Statement stmt = conexion.createStatement();
					ResultSet rs = stmt.executeQuery(consulta);
					while (rs.next()) {
						System.out.println(rs.getInt("idArticulos") + "-" + rs.getString("DescripcionArticulos") + ", "
								+ rs.getInt("CantidadStock") + ", " + rs.getDouble("PrecioArticulos"));

					}

					// Completar el informe con los datos de la base de datos
					JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion);
					// Mostrar el informe en JasperViewer
					JasperViewer.viewReport(print, false);
					// Para exportarlo a pdf
					JasperExportManager.exportReportToPdfFile(print, "ArticulosTickets.pdf");

					// Abrir el fichero PDF generado
					File path = new File("ArticulosTickets.pdf");
					Desktop.getDesktop().open(path);
				} catch (Exception ex) {
					System.out.println("Error: " + ex.toString());
				}
			}
		});
		btnAceptar.setBounds(470, 84, 120, 30);
		contentPane.add(btnAceptar);

	}

	/**
	 * Con este método, rellenamos el textArea con los datos obtenidos de la BD
	 * @param con, realizamos la coexión con la base de datos
	 * @param t,   indicamo el text area, donde queremos que nos meta la información
	 *             de la consulta
	 */
	private void rellenarTextArea(Connection con, TextArea t) {
		String sqlSelect = "SELECT * FROM Articulos";
		try {
			// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlSelect);
			while (rs.next()) {
				if (consulta.getText().length() == 0) {
					consulta.setText(rs.getInt("idArticulos") + "-" + rs.getString("DescripcionArticulos") + ", "
							+ rs.getInt("CantidadStock") + ", " + rs.getDouble("PrecioArticulos"));
				} else {
					consulta.setText(consulta.getText() + "\n" + rs.getInt("idArticulos") + "-"
							+ rs.getString("DescripcionArticulos") + ", " + rs.getInt("CantidadStock") + ", "
							+ rs.getDouble("PrecioArticulos"));
				}
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("ERROR:al consultar");
			ex.printStackTrace();
		}
	}

	/**
	 * Establecemos la conexión con la base de datos
	 * 
	 * @return
	 */
	private Connection conectar() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/Tiendecita?useSSL=false";
		String login = "root";
		String password = "Studium2020;";
		Connection con = null;

		try {
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD empresa
			con = DriverManager.getConnection(url, login, password);
			if (con != null) {
				System.out.println("Conectado a la base de datos");
			}
		} catch (SQLException ex) {
			System.out.println("ERROR:La dirección no es válida o el usuario y clave");
			ex.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error 1-" + cnfe.getMessage());
		}
		return con;
	}

	/**
	 * Cerramos la conexión con la base de datos
	 * 
	 * @param con
	 */
	private void desconectar(Connection con) {
		// TODO Auto-generated method stub
		try {
			con.close();
		} catch (Exception e) {
		}
	}
}
