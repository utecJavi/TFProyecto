package tecnofenix.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tecnofenix.entidades.Usuario;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import tecnofenix.entidades.Departamentos;
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Itr;
import tecnofenix.entidades.TipoGenero;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UIUsuarioDatosPropios {

	public JFrame frame;
	private JTextField txtDocumento;
	private JTextField txtNombre;
	private JTextField txtUsuario;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtApellido;
	private JTextField txtGeneracion;
	private JTextField txtLocalidad;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar(Usuario usuario) {

		frame = new JFrame("Usuario Datos Propios");

		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(400, 700));
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(null);

		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(23, 37, 133, 13);
		panel.add(lblDocumento);

		txtDocumento = new JTextField();
		txtDocumento.setEnabled(false);
		txtDocumento.setColumns(10);
		txtDocumento.setBounds(116, 34, 189, 19);
		panel.add(txtDocumento);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(23, 60, 86, 13);
		panel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(23, 73, 360, 19);
		panel.add(txtNombre);

		JLabel lblNombreUsuario = new JLabel("Nombre de Usuario:");
		lblNombreUsuario.setBounds(23, 144, 133, 13);
		panel.add(lblNombreUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setEnabled(false);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(23, 157, 360, 19);
		panel.add(txtUsuario);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(23, 186, 133, 13);
		panel.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(23, 199, 148, 19);
		panel.add(txtTelefono);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(23, 228, 133, 13);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(23, 241, 360, 19);
		panel.add(txtEmail);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(23, 102, 86, 13);
		panel.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(23, 115, 360, 19);
		panel.add(txtApellido);

		JLabel lblGeneracion = new JLabel("Generacion:");
		lblGeneracion.setBounds(23, 398, 133, 13);
		panel.add(lblGeneracion);

		txtGeneracion = new JTextField();
		txtGeneracion.setColumns(10);
		txtGeneracion.setEnabled(false);
		txtGeneracion.setBounds(23, 410, 360, 21);
		panel.add(txtGeneracion);

		JLabel lblITR = new JLabel("ITR:");
		lblITR.setBounds(23, 270, 133, 13);
		panel.add(lblITR);

		JComboBox<Itr> comboBoxITR = new JComboBox<Itr>();
		comboBoxITR.setEnabled(false);
		comboBoxITR.setBounds(23, 282, 360, 21);
		panel.add(comboBoxITR);

		JLabel lblLocalidad = new JLabel("Localidad:");
		lblLocalidad.setBounds(26, 313, 99, 13);
		panel.add(lblLocalidad);

		txtLocalidad = new JTextField();
		txtLocalidad.setColumns(10);
		txtLocalidad.setBounds(26, 329, 357, 19);
		panel.add(txtLocalidad);

		JLabel lblDepartamento = new JLabel("Departamento:");
		lblDepartamento.setBounds(26, 355, 159, 13);
		panel.add(lblDepartamento);

		JComboBox cmbDepto = new JComboBox(Departamentos.values());
		cmbDepto.setBounds(23, 367, 360, 21);
		panel.add(cmbDepto);
		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(181, 186, 202, 13);
		panel.add(lblGenero);
		
		JComboBox cmbBoxGenero = new JComboBox(TipoGenero.values());
		cmbBoxGenero.setSelectedIndex(0);
		cmbBoxGenero.setBounds(181, 198, 202, 21);
		panel.add(cmbBoxGenero);
		
		
		txtNombre.setText(usuario.getNombres());
		txtApellido.setText(usuario.getApellidos());
		txtEmail.setText(usuario.getMail());
		txtTelefono.setText(usuario.getTelefono());
		txtUsuario.setText(usuario.getUsuario());
		if (usuario instanceof Estudiante) {
			txtGeneracion.setText(String.valueOf(((Estudiante) usuario).getGeneracion()));
			txtGeneracion.setVisible(true);
			lblGeneracion.setVisible(true);
		} else {
			txtGeneracion.setVisible(false);
			lblGeneracion.setVisible(false);
		}
		txtDocumento.setText(String.valueOf(usuario.getDocumento()));
		txtLocalidad.setText(usuario.getLocalidad());
		cmbBoxGenero.setSelectedItem(TipoGenero.fromString(usuario.getGenero()));
//		cmbDepto.setSelectedItem(Departamentos.valueOf(usuario.getDepartamento()));
		
		JButton btnAcrualizar = new JButton("Actualizar Datos");
		btnAcrualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnAcrualizar.setBounds(276, 498, 107, 21);
		panel.add(btnAcrualizar);

	
		
		frame.pack();
	}
}
