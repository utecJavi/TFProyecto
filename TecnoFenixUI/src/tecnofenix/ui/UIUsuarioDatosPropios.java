package tecnofenix.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

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
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import com.toedter.calendar.JDateChooser;

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
	private JPasswordField textRepetirContraseniaNueva;
	private JPasswordField textContraseniaNueva;
	private JPasswordField textContraseniaActual;
	private JCheckBox cambiarPassCheckbox;
	private JCheckBox hidePasswordCheckboxRepetir;
	private JCheckBox hidePasswordCheckboxNuevo;
	private JCheckBox hidePasswordCheckbox;
	private JLabel lblContraseaActual;
	private JLabel lblRepetirContraseaNueva;
	private JLabel lblContraseaNueva;
	private JDateChooser fechaNacimientoChoser;
	private JComboBox cmbDepto;
	private JComboBox cmbBoxGenero;
	
	boolean passValido = false;
	boolean emailValido = false;
	boolean passCoincideValido = false;
	boolean emailPerCoincideValido = false;
	
	private static final String EMAIL_VALIDO ="^(.+)@(\\S+)$";
	
	private static final String PASSWORD_PATTERN ="^(?!.*\\\\s)(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
	private JLabel lblpassValido;
	private JLabel lblpassCoincide;
	private JLabel lblEmailPersonalValido;
	
	
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
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
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
		txtMailPersonal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validarMail();
			}
		});
		panel.add(txtMailPersonal);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(23, 102, 86, 13);
		panel.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(23, 115, 360, 19);
		panel.add(txtApellido);

		JLabel lblGeneracion = new JLabel("Generacion:");
		lblGeneracion.setBounds(181, 437, 202, 13);
		panel.add(lblGeneracion);

		txtGeneracion = new JTextField();
		txtGeneracion.setColumns(10);
		txtGeneracion.setEnabled(false);
		txtGeneracion.setBounds(181, 449, 202, 21);
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

		cmbDepto = new JComboBox(Departamentos.values());
		cmbDepto.setBounds(23, 406, 360, 21);
		panel.add(cmbDepto);

		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(181, 186, 140, 13);
		panel.add(lblGenero);

		cmbBoxGenero = new JComboBox(TipoGenero.values());
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

		
		JLabel lblEmailPersonal = new JLabel("Email personal:");
		lblEmailPersonal.setBounds(23, 270, 133, 13);
		panel.add(lblEmailPersonal);
		
		textRepetirContraseniaNueva = new JPasswordField();
		textRepetirContraseniaNueva.setColumns(10);
		textRepetirContraseniaNueva.setBounds(23, 594, 291, 19);
		textRepetirContraseniaNueva.setVisible(false);
		textRepetirContraseniaNueva.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validarContrasenia();
				validarContraseniasIguales();
			}
		});
		panel.add(textRepetirContraseniaNueva);
		
		lblRepetirContraseaNueva = new JLabel("Repetir contrase\u00F1a nueva:");
		lblRepetirContraseaNueva.setBounds(23, 581, 202, 13);
		lblRepetirContraseaNueva.setVisible(false);
		panel.add(lblRepetirContraseaNueva);
		
		textContraseniaNueva = new JPasswordField();
		textContraseniaNueva.setColumns(10);
		textContraseniaNueva.setBounds(23, 552, 291, 19);
		textContraseniaNueva.setVisible(false);
		textContraseniaNueva.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validarContrasenia();
				validarContraseniasIguales();
			}
		});
		panel.add(textContraseniaNueva);
		
		lblContraseaNueva = new JLabel("Contrase\u00F1a nueva:");
		lblContraseaNueva.setBounds(23, 539, 133, 13);
		lblContraseaNueva.setVisible(false);
		panel.add(lblContraseaNueva);
		
		lblContraseaActual = new JLabel("Contrase\u00F1a actual:");
		lblContraseaActual.setBounds(23, 503, 133, 13);
		lblContraseaActual.setVisible(false);
		panel.add(lblContraseaActual);
		
		textContraseniaActual = new JPasswordField();
		textContraseniaActual.setColumns(10);
		textContraseniaActual.setBounds(23, 516, 291, 19);
		textContraseniaActual.setVisible(false);
		textContraseniaActual.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validarContraseniaActual();
				
			}
		});
		panel.add(textContraseniaActual);
		

		hidePasswordCheckbox = new JCheckBox("Ver");
		hidePasswordCheckbox.setBounds(331, 515, 52, 21);
		hidePasswordCheckbox.setVisible(false);
		hidePasswordCheckbox.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textContraseniaActual.setEchoChar((char) 0);
				} else {
					textContraseniaActual.setEchoChar('*');

				}
			}
		});
		panel.add(hidePasswordCheckbox);
		
		hidePasswordCheckboxNuevo = new JCheckBox("Ver");
		hidePasswordCheckboxNuevo.setBounds(331, 551, 52, 21);
		hidePasswordCheckboxNuevo.setVisible(false);
		hidePasswordCheckboxNuevo.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textContraseniaNueva.setEchoChar((char) 0);
				} else {
					textContraseniaNueva.setEchoChar('*');

				}
			}
		});
		panel.add(hidePasswordCheckboxNuevo);
		
		hidePasswordCheckboxRepetir = new JCheckBox("Ver");
		hidePasswordCheckboxRepetir.setBounds(331, 593, 52, 21);
		hidePasswordCheckboxRepetir.setVisible(false);
		hidePasswordCheckboxRepetir.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textRepetirContraseniaNueva.setEchoChar((char) 0);
				} else {
					textRepetirContraseniaNueva.setEchoChar('*');

				}
			}
		});
		panel.add(hidePasswordCheckboxRepetir);
		
		fechaNacimientoChoser = new JDateChooser();
		fechaNacimientoChoser.setBounds(26, 451, 130, 19);
		panel.add(fechaNacimientoChoser);
		fechaNacimientoChoser.setDate(user.getFechaNacimiento());
		
		
		JLabel lblfechaNacimiento = new JLabel("Fecha Nacimiento");
		lblfechaNacimiento.setBounds(26, 438, 130, 13);
		panel.add(lblfechaNacimiento);

		

		JButton btnAcrualizar = new JButton("Actualizar Datos");
		btnAcrualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validarDatos()) {
				user.setNombres(txtNombre.getText());
				user.setApellidos(txtApellido.getText());
				user.setMailPersonal(txtMailPersonal.getText()); 
				user.setGenero(TipoGenero.getCharGenero(cmbBoxGenero.getSelectedItem().toString()));
				System.out.println(TipoGenero.getCharGenero(cmbBoxGenero.getSelectedItem().toString()));
				user.setLocalidad(txtLocalidad.getText());
				user.setTelefono(txtTelefono.getText());
				user.setDepartamento(cmbDepto.getSelectedItem().toString());
				user.setFechaNacimiento(fechaNacimientoChoser.getDate());
				if(cambiarPassCheckbox.isSelected()) {
					user.setContrasenia(String.valueOf(textContraseniaNueva.getPassword()));
				}
				user = usuarioRemote.modificarUsuario(user);
				JOptionPane.showMessageDialog(null, "Se modificaron los datos solicitados",
						"Cambios personales", JOptionPane.INFORMATION_MESSAGE);	
				frame.dispose();
				}
				
			}
		});
		btnAcrualizar.setBounds(224, 650, 159, 21);
		panel.add(btnAcrualizar);
		
		lblpassValido = new JLabel("-");
		lblpassValido.setBounds(235, 539, 77, 13);
		lblpassValido.setVisible(false);
		panel.add(lblpassValido);

		lblpassCoincide = new JLabel("-");
		lblpassCoincide.setBounds(235, 581, 77, 13);
		lblpassCoincide.setVisible(false);
		panel.add(lblpassCoincide);
		
		lblEmailPersonalValido = new JLabel("-");
		lblEmailPersonalValido.setBounds(306, 270, 77, 13);
		panel.add(lblEmailPersonalValido);
		
		cambiarPassCheckbox = new JCheckBox("Cambiar contraseña");
		cambiarPassCheckbox.setBounds(23, 476, 251, 21);
		cambiarPassCheckbox.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					lblContraseaActual.setVisible(true);
					textContraseniaActual.setVisible(true);
					hidePasswordCheckbox.setVisible(true);
					validarContraseniaActual();
				}else {
					hidePasswordCheckbox.setVisible(false);
					lblContraseaActual.setVisible(false);
					textContraseniaNueva.setVisible(false);
					textRepetirContraseniaNueva.setVisible(false);
					hidePasswordCheckboxRepetir.setVisible(false);
					hidePasswordCheckboxNuevo.setVisible(false);
					textContraseniaActual.setVisible(false);
					lblRepetirContraseaNueva.setVisible(false);
					lblContraseaNueva.setVisible(false);
					lblpassValido.setVisible(false);
					lblpassCoincide.setVisible(false);
				}
			}
		});
		panel.add(cambiarPassCheckbox);
		
		
		frame.pack();
	}
	
	
	public boolean validarDatos() {

		if (txtDocumento.getText().length() == 8) {
		} else {
			JOptionPane.showMessageDialog(null,
					"La cedula de identidad es un dato obligatorio, ingresela sin puntos ni guiones [maximo 8 numeros]",
					"Datos no validos", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (txtApellido.getText() == null || txtApellido.getText() == "") {

			JOptionPane.showMessageDialog(null,
					"El apellido es un dato obligatorio, no puede ser vacío",
					"Datos no validos", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (txtNombre.getText() == null || txtNombre.getText() == "") {

			JOptionPane.showMessageDialog(null,
					"El nombre es un dato obligatorio, no puede ser vacío",
					"Datos no validos", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (txtTelefono.getText() == null || txtTelefono.getText() == "") {

			JOptionPane.showMessageDialog(null,
					"Ingrese su numero de telefono, no puede ser vacío",
					"Datos no validos", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if (fechaNacimientoChoser.getDate() == null ) {

			JOptionPane.showMessageDialog(null,
					"Seleccione su fecha de nacimiento, no puede ser vacío",
					"Datos no validos", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if (cmbDepto.getSelectedIndex() == 0 ) {

			JOptionPane.showMessageDialog(null,
					"Seleccione el departamento",
					"Datos no validos", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		validarMail();
		if (!emailValido) {
			JOptionPane.showMessageDialog(null,
					"El mail personal es un dato requerido, su formato es usuario@dominio ",
					"Datos no validos", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if(cambiarPassCheckbox.isSelected()) {
			if (!passValido) {
				JOptionPane.showMessageDialog(null,
						"El password no cumple con las validaciones necesarias [8 caracteres mínimo, 1 carácter especial (@#$%), al menos 1 mayúscula y al menos 1 minúscula]",
						"Datos no validos", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			if (!passCoincideValido) {
				JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Datos no validos",
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}

		return true;
	}
	
	public void validarMail() {
		if (!txtMailPersonal.getText().matches(EMAIL_VALIDO)) {
			lblEmailPersonalValido.setVisible(true);
			lblEmailPersonalValido.setText("Invalido!");
			lblEmailPersonalValido.setForeground(Color.RED);
			emailValido = false;
		} else {
			lblEmailPersonalValido.setVisible(true);
			lblEmailPersonalValido.setText("Valido!");
			lblEmailPersonalValido.setForeground(Color.GREEN);
			emailValido = true;

		}
	}
	
	
	public void validarContraseniaActual() {
		if(String.valueOf(textContraseniaActual.getPassword()).equals(user.getContrasenia())) {
			textContraseniaNueva.setVisible(true);
			textRepetirContraseniaNueva.setVisible(true);
			hidePasswordCheckboxRepetir.setVisible(true);
			hidePasswordCheckboxNuevo.setVisible(true);
			textContraseniaActual.setEditable(false);
			lblRepetirContraseaNueva.setVisible(true);
			lblContraseaNueva.setVisible(true);
			lblpassValido.setVisible(true);
			lblpassCoincide.setVisible(true);
		}
		
	}
	
	public void validarContrasenia() {
		if (!String.valueOf(textContraseniaNueva.getPassword()).matches(PASSWORD_PATTERN)) {
			lblpassValido.setVisible(true);
			lblpassValido.setText("Invalido!");
			lblpassValido.setForeground(Color.RED);
			passValido = false;
		} else {
			lblpassValido.setVisible(true);
			lblpassValido.setText("Valido!");
			lblpassValido.setForeground(Color.GREEN);
			passValido = true;

		}
	}
	
	public void validarContraseniasIguales() {
		
		if(!String.valueOf(textContraseniaNueva.getPassword()).equals(String.valueOf(textRepetirContraseniaNueva.getPassword()))) {
			lblpassCoincide.setVisible(true);
			lblpassCoincide.setText("No coinciden!");
			lblpassCoincide.setForeground(Color.RED);
			passCoincideValido = false;
		} else {
			lblpassCoincide.setVisible(true);
			lblpassCoincide.setText("Coinciden!");
			lblpassCoincide.setForeground(Color.GREEN);
			passCoincideValido = true;

		}
	}
}
