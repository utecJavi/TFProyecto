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
import java.util.List;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Itr;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;

public class UIUsuarioNuevo {

	public JFrame frame;
	private JTextField txtDocumento;
	private JTextField txtNombre;
	private JTextField txtUsuario;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtApellido;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private EJBUsuarioRemoto usuarioRemote;
	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {

//		Usuario usuario = new Es
		frame = new JFrame("Usuario Nuevo");
		usuarioRemote = new EJBUsuarioRemoto();
		
		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(470, 780));
		frame.getContentPane().setSize(new Dimension(475, 785));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(23, 104, 133, 13);
		panel.add(lblDocumento);
		
		txtDocumento = new JTextField();
		txtDocumento.setBounds(116, 101, 189, 19);
		txtDocumento.setColumns(10);
		panel.add(txtDocumento);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(23, 127, 86, 13);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(23, 140, 360, 19);
		txtNombre.setColumns(10);
		panel.add(txtNombre);
		
		JLabel lblNombreUsuario = new JLabel("Nombre de Usuario:");
		lblNombreUsuario.setBounds(23, 211, 133, 13);
		panel.add(lblNombreUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(23, 224, 360, 19);
		txtUsuario.setColumns(10);
		panel.add(txtUsuario);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(23, 337, 133, 13);
		panel.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(23, 350, 360, 19);
		txtTelefono.setColumns(10);
		panel.add(txtTelefono);
		
		JLabel lblEmail = new JLabel("Email personal:");
		lblEmail.setBounds(23, 379, 133, 13);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(23, 392, 360, 19);
		txtEmail.setColumns(10);
		panel.add(txtEmail);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(23, 169, 86, 13);
		panel.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(23, 182, 360, 19);
		txtApellido.setColumns(10);
		panel.add(txtApellido);
		
		JLabel lblGeneracion = new JLabel("Generacion:");
		lblGeneracion.setBounds(23, 543, 133, 13);
		panel.add(lblGeneracion);
		
		JLabel lblITR = new JLabel("ITR:");
		lblITR.setBounds(23, 595, 133, 13);
		panel.add(lblITR);
		
		JComboBox<Itr> comboBoxITR = new JComboBox<Itr>();
		List<Itr>listItr = usuarioRemote.listarITR();
		for(Itr itrItem: listItr){
			comboBoxITR.addItem(itrItem);
		}
		comboBoxITR.setBounds(23, 607, 360, 21);
		comboBoxITR.setEnabled(true);
		panel.add(comboBoxITR);
		
//		txtNombre.setText(usuario.getNombres());
//		txtApellido.setText(usuario.getApellidos());
//		txtEmail.setText(usuario.getMail());
//		txtTelefono.setText(usuario.getTelefono());
//		txtUsuario.setText(usuario.getUsuario());
//		txtGeneracion.setText(String.valueOf(((Estudiante)usuario).getGeneracion()));
//		txtDocumento.setText(String.valueOf(usuario.getDocumento()));
//		
		JButton btnAcrualizar = new JButton("Crear usuario");
		btnAcrualizar.setBounds(276, 638, 107, 21);
		btnAcrualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panel.add(btnAcrualizar);
		
		ButtonGroup butonGroup=new ButtonGroup(); 
		
		JRadioButton rdbtnEstudiante = new JRadioButton("Estudiante");
		rdbtnEstudiante.setBounds(53, 29, 86, 21);
		panel.add(rdbtnEstudiante);
		
		JRadioButton rdbtnTutor = new JRadioButton("Tutor");
		rdbtnTutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnTutor.isSelected()) {
					
				}
			}
		});
		rdbtnTutor.setBounds(158, 29, 71, 21);
		panel.add(rdbtnTutor);
		
		JRadioButton rdbtnAnalista = new JRadioButton("Analista");
		rdbtnAnalista.setBounds(251, 29, 86, 21);
		panel.add(rdbtnAnalista);
		
		butonGroup.add(rdbtnEstudiante);  
		butonGroup.add(rdbtnTutor);
		butonGroup.add(rdbtnAnalista);
		
		
		
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(23, 514, 99, 19);
		panel.add(dateChooser);
		
		JLabel lblNewLabel = new JLabel("Fecha Nacimiento");
		lblNewLabel.setBounds(23, 502, 86, 13);
		panel.add(lblNewLabel);
		
		JYearChooser yearChooser = new JYearChooser();
		yearChooser.setBounds(23, 560, 86, 19);
		panel.add(yearChooser);
		
		JLabel lblEmailInstitucional = new JLabel("Email institucional:");
		lblEmailInstitucional.setBounds(23, 460, 133, 13);
		panel.add(lblEmailInstitucional);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(23, 473, 360, 19);
		panel.add(textField);
		
		JLabel lblRepetirEmailPersonal = new JLabel("Repetir Email personal:");
		lblRepetirEmailPersonal.setBounds(23, 418, 133, 13);
		panel.add(lblRepetirEmailPersonal);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(23, 431, 360, 19);
		panel.add(textField_1);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(23, 253, 133, 13);
		panel.add(lblContrasea);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(23, 266, 360, 19);
		panel.add(textField_2);
		
		JLabel lblRepetirContrasea = new JLabel("Repetir contrase\u00F1a:");
		lblRepetirContrasea.setBounds(23, 295, 133, 13);
		panel.add(lblRepetirContrasea);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(23, 308, 360, 19);
		panel.add(textField_3);
		
		frame.pack();
	}
}
