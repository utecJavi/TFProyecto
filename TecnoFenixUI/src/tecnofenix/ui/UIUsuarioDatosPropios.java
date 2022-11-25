package tecnofenix.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tecnofenix.entidades.Usuario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.Departamentos;
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Itr;
import tecnofenix.entidades.TipoGenero;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UIUsuarioDatosPropios {

	public JFrame frame;
	private EJBUsuarioRemoto usuarioRemote;
	private JTextField txtDocumento;
	private JTextField txtNombre;
	private JTextField txtUsuario;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtApellido;
	private JTextField txtGeneracion;
	private JTextField txtLocalidad;
	private Usuario user;
	private JTextField txtMailPersonal;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar(Usuario usuario) {

		frame = new JFrame("Usuario Datos Propios");
		System.out.println("Iniciando ventana Usuario Datos Propios");
		usuarioRemote = new EJBUsuarioRemoto();
		user = usuario;
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
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(23, 241, 360, 19);
		panel.add(txtEmail);
		
		txtMailPersonal = new JTextField();
		txtMailPersonal.setText((String) null);
		txtMailPersonal.setColumns(10);
		txtMailPersonal.setBounds(23, 283, 360, 19);
		panel.add(txtMailPersonal);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(23, 102, 86, 13);
		panel.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(23, 115, 360, 19);
		panel.add(txtApellido);

		JLabel lblGeneracion = new JLabel("Generacion:");
		lblGeneracion.setBounds(23, 437, 133, 13);
		panel.add(lblGeneracion);

		txtGeneracion = new JTextField();
		txtGeneracion.setColumns(10);
		txtGeneracion.setEnabled(false);
		txtGeneracion.setBounds(23, 449, 360, 21);
		panel.add(txtGeneracion);

		JLabel lblITR = new JLabel("ITR:");
		lblITR.setBounds(23, 309, 133, 13);
		panel.add(lblITR);

		JTextField comboBoxITR = new JTextField();
		comboBoxITR.setEnabled(false);
		comboBoxITR.setBounds(23, 321, 360, 21);
		panel.add(comboBoxITR);

		JLabel lblLocalidad = new JLabel("Localidad:");
		lblLocalidad.setBounds(26, 352, 99, 13);
		panel.add(lblLocalidad);

		txtLocalidad = new JTextField();
		txtLocalidad.setColumns(10);
		txtLocalidad.setBounds(26, 368, 357, 19);
		panel.add(txtLocalidad);

		JLabel lblDepartamento = new JLabel("Departamento:");
		lblDepartamento.setBounds(26, 394, 159, 13);
		panel.add(lblDepartamento);

		JComboBox cmbDepto = new JComboBox(Departamentos.values());
		cmbDepto.setBounds(23, 406, 360, 21);
		panel.add(cmbDepto);

		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(181, 186, 202, 13);
		panel.add(lblGenero);

		JComboBox cmbBoxGenero = new JComboBox(TipoGenero.values());
		cmbBoxGenero.setSelectedIndex(0);
		cmbBoxGenero.setBounds(181, 198, 202, 21);
		panel.add(cmbBoxGenero);
		

		txtNombre.setText(user.getNombres());
		txtApellido.setText(user.getApellidos());
		txtEmail.setText(user.getMail());
		txtMailPersonal.setText(user.getMailPersonal());
		txtTelefono.setText(user.getTelefono());
		txtUsuario.setText(user.getUsuario());
		if (user instanceof Estudiante) {
			txtGeneracion.setText(String.valueOf(((Estudiante) user).getGeneracion()));
			txtGeneracion.setVisible(true);
			lblGeneracion.setVisible(true);
		} else {
			txtGeneracion.setVisible(false);
			lblGeneracion.setVisible(false);
		}
		txtDocumento.setText(String.valueOf(user.getDocumento()));
		txtLocalidad.setText(user.getLocalidad());
		cmbBoxGenero.setSelectedItem(TipoGenero.fromString(user.getGenero()));
		cmbDepto.setSelectedItem(Departamentos.fromString(user.getDepartamento()));
		comboBoxITR.setText(user.getItr().toString());

		JButton btnAcrualizar = new JButton("Actualizar Datos");
		btnAcrualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.setNombres(txtNombre.getText());
				user.setApellidos(txtApellido.getText());
				user.setMailPersonal(txtMailPersonal.getText()); 
				user.setGenero(TipoGenero.getCharGenero(cmbBoxGenero.getSelectedItem().toString()));
				System.out.println(TipoGenero.getCharGenero(cmbBoxGenero.getSelectedItem().toString()));
				user.setLocalidad(txtLocalidad.getText());
				user.setTelefono(txtTelefono.getText());
				user.setDepartamento(cmbDepto.getSelectedItem().toString());
				user = usuarioRemote.modificarUsuario(user);
				JOptionPane.showMessageDialog(null, "Se modificaron los datos solicitados",
						"Cambios personales", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnAcrualizar.setBounds(276, 498, 107, 21);
		panel.add(btnAcrualizar);
		
		JLabel lblEmailPersonal = new JLabel("Email personal:");
		lblEmailPersonal.setBounds(23, 270, 133, 13);
		panel.add(lblEmailPersonal);
		
		

		frame.pack();
	}
}
