package es.studium.Clase;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * En esta clase declararemos un error al realizar el alta de los artículos
 * 
 * @author Moisés Adamuz
 *
 */
public class ErrorAltaArticulos extends JFrame {

	private JPanel contentPane;

	/**
	 * Diseñamos la interfaz
	 */
	public ErrorAltaArticulos() {
		setTitle("Error");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 303, 153);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u00A1No se ha podido realizar el alta correctamente!");
		lblNewLabel.setBounds(10, 27, 267, 14);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(93, 63, 89, 23);
		contentPane.add(btnNewButton);
	}

}
