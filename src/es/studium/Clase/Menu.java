package es.studium.Clase;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JasperCompileManager;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

/**
 * Este programa es un CRUD, que a su vez continen dos consultas con las cuales
 * podemos generar informes de iReports En esta clase, tenemos la interfaz
 * principal, con la cual podremos tabrajar con las diferentes opciones de menú
 * en esta clase encontramos las distintas altas, bajas, consultas y opciones de
 * modificar del programa
 * 
 * @author Moisés Adamuz Fernández
 * @version 1.0
 * @since 04/02/2021
 *
 */
public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Con este método, arrancamos la aplicación
	 * 
	 * @param args, Realizamos el main, con el cual ejecutamos toda la aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Diseñamos la interfaz
	 */
	public Menu() {
		setTitle("MENU PRINCIPAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Art\u00EDculos");
		menuBar.add(mnNewMenu);

		// Vinculo AltaArticulos con el menuItem Alta Artiiculos
		JMenuItem mntmNewMenuItem = new JMenuItem("Alta Art\u00EDculos");
		mnNewMenu.add(mntmNewMenuItem);
		// Le otorgamos funcionalidad al botón del MenuAltaArticulos
		/**
		 * En este MenúItem, hemos introducido la clase AltaArticulos,de tal manera que
		 * al pulsar sobre el, se nos abrirá la interfaz de AltaArticulos
		 */
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AltaArticulos altaarticulos = new AltaArticulos();

			}
		});

		// Vinculo BajaArticulos con el menuItem Baja Artiiculos
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Baja Art\u00EDculos");
		mnNewMenu.add(mntmNewMenuItem_1);
		// Le otorgamos funcionalidad al botón del MenuBajaArticulos
		/**
		 * En este MenúItem, hemos introducido la clase BajaArt,de tal manera que al
		 * pulsar sobre el, se nos abrirá la interfaz de BajaArticulos
		 */
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				BajaArt bajaArticulos = new BajaArt();

			}
		});

		// Vinculo ConsultarArticulos con el menuItem Consultar Artiiculos
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Consultar Art\u00EDculos");
		mnNewMenu.add(mntmNewMenuItem_2);
		// Le otorgamos funcionalidad al botón del MenuConsultaArticulos
		/**
		 * En este MenúItem, hemos introducido la clase ConsultarArticulos,de tal manera
		 * que al pulsar sobre el, se nos abrirá la interfaz de ConsultarArticulos
		 */
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ConsultarArticulos consulta = new ConsultarArticulos();

			}
		});

		// Vinculo ModificarArticulos con el menuItem Modificar Artiiculos
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Modificar Art\u00EDculos");
		mnNewMenu.add(mntmNewMenuItem_3);
		// Le otorgamos funcionalidad al botón del MenuModificarArticulos
		/**
		 * En este MenúItem, hemos introducido la clase ModificarArticulos,de tal manera
		 * que al pulsar sobre el, se nos abrirá la interfaz de ModificarArticulos
		 */
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ModificiarArticulos modificar = new ModificiarArticulos();

			}
		});

		JMenu mnNewMenu_1 = new JMenu("Tickets");
		menuBar.add(mnNewMenu_1);

		// Vinculo AltaTickets con el menuItem Alta Tickets
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Alta Tickets");
		mnNewMenu_1.add(mntmNewMenuItem_4);
		// Le otorgamos funcionalidad al botón del MenuAltaTickets
		/**
		 * En este MenúItem, hemos introducido la clase AltaTickets,de tal manera que al
		 * pulsar sobre el, se nos abrirá la interfaz de AltaTickets
		 */
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AltaTickets tickets = new AltaTickets();

			}
		});

		// Vinculo ConsultarTickets con el menuItem Consultar Tickets
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Consultar Tickets");
		mnNewMenu_1.add(mntmNewMenuItem_6);
		// Le otorgamos funcionalidad al botón del MenuConsultarTickets
		/**
		 * En este MenúItem, hemos introducido la clase ConsultarTickets,de tal manera
		 * que al pulsar sobre el, se nos abrirá la interfaz de ConsultarTickets
		 */
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ConsultarTickets consultar = new ConsultarTickets();

			}
		});

		// Vinculo ConsultaFechaTicket con el menuItem Consultar por Fechas
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Consultar por Fechas");
		mnNewMenu_1.add(mntmNewMenuItem_5);
		// Le otorgamos funcionalidad al botón del MenuConsultaTicketFecha
		/**
		 * En este MenúItem, hemos introducido la clase ConsultaTicketFecha,de tal
		 * manera que al pulsar sobre el, se nos abrirá la interfaz de
		 * ConsultaTicketFecha
		 */
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ConsultaFechaTicket consultaFechaTickets = new ConsultaFechaTicket();

			}
		});

	}
}
