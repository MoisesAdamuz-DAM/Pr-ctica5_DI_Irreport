package es.studium.Clase;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

/**
 * Declaramos la interfaz que confirma un error en realizar la modificación
 * 
 * @author Moisés Adamuz
 *
 */
public class ErrorModificar extends JFrame {

	private JPanel contentPane;

	/**
	 * Diseñamos la interfaz
	 */
	public ErrorModificar() {
		setTitle("Error");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 306, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u00A1No se ha podido realizar la modificaci\u00F3n!");
		lblNewLabel.setBounds(27, 25, 254, 14);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(91, 64, 89, 31);
		contentPane.add(btnNewButton);
	}

}
