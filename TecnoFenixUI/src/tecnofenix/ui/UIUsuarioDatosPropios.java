package tecnofenix.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tecnofenix.entidades.Usuario;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.sql.Date;
import tecnofenix.entidades.Itr;
import javax.swing.JButton;

public class UIUsuarioDatosPropios {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar(Usuario usuario) {


		frame = new JFrame("Usuario Datos Propios");

		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(800, 800));
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(null);
		
		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(23, 37, 133, 13);
		panel.add(lblDocumento);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setColumns(10);
		textField.setBounds(90, 34, 189, 19);
		panel.add(textField);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(23, 60, 45, 13);
		panel.add(lblNombre);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(23, 73, 360, 19);
		panel.add(textField_1);
		
		JLabel lblNombreUsuario = new JLabel("Nombre de Usuario:");
		lblNombreUsuario.setBounds(23, 144, 133, 13);
		panel.add(lblNombreUsuario);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setColumns(10);
		textField_2.setBounds(23, 157, 360, 19);
		panel.add(textField_2);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(23, 186, 133, 13);
		panel.add(lblTelefono);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(23, 199, 360, 19);
		panel.add(textField_3);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(23, 228, 133, 13);
		panel.add(lblEmail);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(23, 241, 360, 19);
		panel.add(textField_4);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(23, 102, 45, 13);
		panel.add(lblApellido);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(23, 115, 360, 19);
		panel.add(textField_5);
		
		JLabel lblGeneracion = new JLabel("Generacion:");
		lblGeneracion.setBounds(23, 287, 133, 13);
		panel.add(lblGeneracion);
		
		JComboBox<Date> comboBoxGeneracion = new JComboBox<Date>();
		comboBoxGeneracion.setEnabled(false);
		comboBoxGeneracion.setBounds(23, 299, 360, 21);
		panel.add(comboBoxGeneracion);
		
		JLabel lblITR = new JLabel("ITR:");
		lblITR.setBounds(23, 330, 133, 13);
		panel.add(lblITR);
		
		JComboBox<Itr> comboBoxITR = new JComboBox<Itr>();
		comboBoxITR.setEnabled(false);
		comboBoxITR.setBounds(23, 342, 360, 21);
		panel.add(comboBoxITR);
		
		JButton btnAcrualizar = new JButton("Actualizar Datos");
		btnAcrualizar.setBounds(276, 498, 107, 21);
		panel.add(btnAcrualizar);
	}
}
