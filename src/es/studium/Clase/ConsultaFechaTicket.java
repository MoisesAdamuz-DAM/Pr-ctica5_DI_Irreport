package es.studium.Clase;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import java.awt.Desktop;

/**
 * En esta clase realizaremos una consulta de los tickets, pero indicándole unos
 * parámetros, según las fechas que indiquemos
 * 
 * @author Moisés Adamuz
 *
 */
public class ConsultaFechaTicket extends JFrame implements WindowListener {

	private JPanel contentPane;
	private JTextField txtDesde;
	private JTextField txtHasta;

	/**
	 * Creamos la interfaz de la consulta
	 */
	public ConsultaFechaTicket() {
		setTitle("Consulta Tickets");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 326, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);

		JLabel lblDesde = new JLabel("Fecha Desde:");
		lblDesde.setBounds(28, 44, 89, 14);
		contentPane.add(lblDesde);

		JLabel lblHasta = new JLabel("Fecha Hasta:");
		lblHasta.setBounds(28, 109, 73, 14);
		contentPane.add(lblHasta);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					// Compilar el informe generando fichero jasper
					JasperCompileManager.compileReportToFile("TicketFecha.jrxml");
					System.out.println("Fichero TicketFecha.jasper generado CORRECTAMENTE!");
					// Objeto para guardar parámetros necesarios para el informe
					HashMap<String, Object> parametros = new HashMap<String, Object>();
					parametros.put("FechaDesde", txtDesde.getText());
					parametros.put("FechaHasta", txtHasta.getText());
					// Cargar el informe compilado
					JasperReport report = (JasperReport) JRLoader.loadObjectFromFile("TicketFecha.jasper");
					// Conectar a la base de datos para sacar la información
					Class.forName("com.mysql.jdbc.Driver");
					String servidor = "jdbc:mysql://localhost:3306/Tiendecita?useSSL=false";
					String usuarioDB = "root";
					String passwordDB = "Studium2020;";
					Connection conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);

					String consulta = "SELECT * FROM tickets WHERE FechaTickets BETWEEN \"" + txtDesde.getText()
							+ "\" AND \"" + txtHasta.getText() + "\";";
					// PreparedStatement sentencia= (PreparedStatement)
					// conexion.prepareStatement(consulta);
					// ResultSet rs = sentencia.executeQuery();
					Statement stmt = conexion.createStatement();
					ResultSet rs = stmt.executeQuery(consulta);
					while (rs.next()) {
						System.out.println(rs.getInt("idTickets") + "-" + rs.getString("FechaTickets") + ", "
								+ rs.getInt("TotalTicket") + ", " + rs.getInt("idArticulosFK1"));

					}

					// Completar el informe con los datos de la base de datos
					JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion);
					// Mostrar el informe en JasperViewer
					JasperViewer.viewReport(print, false);
					// Para exportarlo a pdf
					JasperExportManager.exportReportToPdfFile(print, "TicketFecha.pdf");

					// Abrir el fichero PDF generado
					File path = new File("TicketFecha.pdf");
					Desktop.getDesktop().open(path);

					setVisible(false);
				} catch (Exception ex) {
					System.out.println("Error: " + ex.toString());
				}

			}
		});
		btnAceptar.setBounds(112, 156, 89, 23);
		contentPane.add(btnAceptar);

		txtDesde = new JTextField();
		txtDesde.setBounds(123, 41, 122, 20);
		contentPane.add(txtDesde);
		txtDesde.setColumns(10);

		txtHasta = new JTextField();
		txtHasta.setBounds(123, 106, 122, 20);
		contentPane.add(txtHasta);
		txtHasta.setColumns(10);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}
