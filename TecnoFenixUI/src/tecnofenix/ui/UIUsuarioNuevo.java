package tecnofenix.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tecnofenix.entidades.Usuario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.Analista;
import tecnofenix.entidades.Departamentos;
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Itr;
import tecnofenix.entidades.Rol;
import tecnofenix.entidades.TipoGenero;
import tecnofenix.entidades.TipoTutorEncargado;
import tecnofenix.entidades.Tutor;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UIUsuarioNuevo {

	public JFrame frame;
	private JTextField txtDocumento;
	private JTextField txtNombre;
	private JTextField txtUsuario;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtApellido;
	private JTextField txtEmailInstitucional;
	boolean emailInstValido = false;
	private JLabel lblEmailValido;
	private JLabel lblEmailPersonalValido;
	private JLabel lblpassValido;
	private JLabel lblpassCoincide;
	private JLabel lblemailPersonalCoincide;
	private JDateChooser fechaNacimientoChoser;
	private JComboBox<Itr> comboBoxITR;
	private JRadioButton rdbtnEstudiante;
	private JRadioButton rdbtnTutor;
	private JRadioButton rdbtnAnalista;
	private JLabel lblArea;
	private JLabel lbltipoTutor;
	private JComboBox cmbTipoTutor;
	private JComboBox cmbArea;
	private JComboBox cmbDepto;
	private JComboBox cmbBoxGenero;
	private JTextField txtRepetirEmail;
	private JTextField txtPass;
	private JTextField txtRepetirPass;
	private EJBUsuarioRemoto usuarioRemote;

	JYearChooser generacionEstudiante;
	private static final String EMAIL_VALIDO ="^(.+)@(\\S+)$";
	
	private static final String PASSWORD_PATTERN ="^(?!.*\\\\s)(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
	boolean passValido = false;
	boolean emailValido = false;
	boolean passCoincideValido = false;
	boolean emailPerCoincideValido = false;
	private List<Rol> roles;
	private JTextField txtLocalidad;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {

//		Usuario usuario = new Es
		frame = new JFrame("Usuario Nuevo");
		usuarioRemote = new EJBUsuarioRemoto();
		roles = new ArrayList<Rol>();
		roles = usuarioRemote.listarRoles();
		
		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(470, 780));
		frame.getContentPane().setSize(new Dimension(475, 785));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(null);

		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(23, 79, 133, 13);
		panel.add(lblDocumento);

		txtDocumento = new JTextField();
		txtDocumento.setBounds(120, 76, 189, 19);
		txtDocumento.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!((c >= '0') && (c <= '9') ||
		           (c == KeyEvent.VK_BACK_SPACE) ||
		           (c == KeyEvent.VK_DELETE))) {
		         
		          e.consume();
		        }
		      }
		    });
		txtDocumento.setColumns(10);
		panel.add(txtDocumento);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(23, 102, 86, 13);
		panel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setText("");
		txtNombre.setBounds(23, 115, 360, 19);
		txtNombre.setColumns(10);
		panel.add(txtNombre);

		JLabel lblNombreUsuario = new JLabel("Nombre de Usuario:");
		lblNombreUsuario.setBounds(23, 364, 133, 13);
		panel.add(lblNombreUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(23, 377, 360, 19);
		txtUsuario.setEnabled(false);
		txtUsuario.setColumns(10);
		panel.add(txtUsuario);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(23, 186, 133, 13);
		panel.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(23, 199, 127, 19);
		txtTelefono.setColumns(10);
		panel.add(txtTelefono);

		JLabel lblEmail = new JLabel("Email personal:");
		lblEmail.setBounds(23, 490, 133, 13);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(23, 503, 360, 19);
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validarMail();
				validarEmailPerIguales();
			}
		});
		txtEmail.setColumns(10);
		panel.add(txtEmail);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(23, 144, 86, 13);
		panel.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setText("");
		txtApellido.setBounds(23, 157, 360, 19);
		txtApellido.setColumns(10);
		panel.add(txtApellido);

		

		JLabel lblITR = new JLabel("ITR:");
		lblITR.setBounds(23, 569, 133, 13);
		panel.add(lblITR);

		comboBoxITR = new JComboBox<Itr>();
		comboBoxITR.setBounds(23, 581, 360, 21);
		List<Itr> listItr = usuarioRemote.listarITR();
		for (Itr itrItem : listItr) {
			comboBoxITR.addItem(itrItem);
		}
		comboBoxITR.setEnabled(true);
		comboBoxITR.setSelectedIndex(-1);
		panel.add(comboBoxITR);

		
		JLabel lblGeneracion = new JLabel("Generacion:");
		lblGeneracion.setBounds(23, 608, 133, 13);
		panel.add(lblGeneracion);
		
		generacionEstudiante = new JYearChooser();
		generacionEstudiante.setBounds(23, 624, 86, 19);
		panel.add(generacionEstudiante);

		JButton btnAcrualizar = new JButton("Crear usuario");
		btnAcrualizar.setBounds(238, 694, 145, 21);
		btnAcrualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnEstudiante.isSelected()) {
					addEstudiante();
				}
				if (rdbtnTutor.isSelected()) {
					addTutor();
				}
				if (rdbtnTutor.isSelected()) {
					addAnalista();
				}
			}
		});
		panel.add(btnAcrualizar);

		ButtonGroup butonGroup = new ButtonGroup();

		rdbtnEstudiante = new JRadioButton("Estudiante");
		rdbtnEstudiante.setBounds(53, 29, 86, 21);
		rdbtnEstudiante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnEstudiante.isSelected()) {
					validarMailInstitucional("estudiantes.utec.edu.uy");
					generacionEstudiante.setVisible(true);
					lblGeneracion.setVisible(true);
					cmbArea.setVisible(false);
					cmbTipoTutor.setVisible(false);
					lblArea.setVisible(false);
					lbltipoTutor.setVisible(false);
				}
			}
		});
		rdbtnEstudiante.setSelected(true);
		panel.add(rdbtnEstudiante);

		rdbtnTutor = new JRadioButton("Tutor");
		rdbtnTutor.setBounds(158, 29, 71, 21);
		rdbtnTutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnTutor.isSelected()) {
					validarMailInstitucional("tutor.utec.edu.uy");
					generacionEstudiante.setVisible(false);
					lblGeneracion.setVisible(false);
					cmbArea.setVisible(true);
					cmbTipoTutor.setVisible(true);
					lblArea.setVisible(true);
					lbltipoTutor.setVisible(true);
				}
			}
		});
		panel.add(rdbtnTutor);

		rdbtnAnalista = new JRadioButton("Analista");
		rdbtnAnalista.setBounds(251, 29, 86, 21);
		rdbtnAnalista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnAnalista.isSelected()) {
					validarMailInstitucional("analista.utec.edu.uy");
					generacionEstudiante.setVisible(false);
					lblGeneracion.setVisible(false);
					cmbArea.setVisible(false);
					cmbTipoTutor.setVisible(false);
					lblArea.setVisible(false);
					lbltipoTutor.setVisible(false);
				}
			}
		});
		panel.add(rdbtnAnalista);

		butonGroup.add(rdbtnEstudiante);
		butonGroup.add(rdbtnTutor);
		butonGroup.add(rdbtnAnalista);

		fechaNacimientoChoser = new JDateChooser();
		fechaNacimientoChoser.setBounds(23, 241, 99, 19);
		panel.add(fechaNacimientoChoser);

		JLabel lblfechaNacimiento = new JLabel("Fecha Nacimiento");
		lblfechaNacimiento.setBounds(23, 228, 86, 13);
		panel.add(lblfechaNacimiento);

		

		JLabel lblEmailInstitucional = new JLabel("Email institucional:");
		lblEmailInstitucional.setBounds(23, 322, 133, 13);
		panel.add(lblEmailInstitucional);

		lblEmailValido = new JLabel("Valido!");
		lblEmailValido.setBounds(389, 338, 56, 13);
		lblEmailValido.setVisible(false);
		panel.add(lblEmailValido);

		lblpassValido = new JLabel("Valido!");
		lblpassValido.setBounds(389, 422, 56, 13);
		lblpassValido.setVisible(false);
		panel.add(lblpassValido);
		
		lblpassCoincide = new JLabel("Valido!");
		lblpassCoincide.setBounds(389, 464, 92, 13);
		lblpassCoincide.setVisible(false);
		panel.add(lblpassCoincide);
		
		lblEmailPersonalValido = new JLabel("Valido!");
		lblEmailPersonalValido.setBounds(389, 506, 92, 13);
		lblEmailPersonalValido.setVisible(false);
		panel.add(lblEmailPersonalValido);
		
		lblemailPersonalCoincide = new JLabel("Valido!");
		lblemailPersonalCoincide.setBounds(389, 548, 92, 13);
		lblemailPersonalCoincide.setVisible(false);
		panel.add(lblemailPersonalCoincide);
		
		
		txtEmailInstitucional = new JTextField();
		txtEmailInstitucional.setBounds(23, 335, 360, 19);
		txtEmailInstitucional.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if (rdbtnTutor.isSelected()) {
					if (e.getKeyChar() == '@') {
						if (txtEmailInstitucional.getText().contains("@")) {
							if (!txtEmailInstitucional.getText().contains("tutor.utec.edu.uy")) {
								txtEmailInstitucional.setText(txtEmailInstitucional.getText() + "tutor.utec.edu.uy");
							}

						}
					}
					validarMailInstitucional("tutor.utec.edu.uy");
					
				}
				if (rdbtnAnalista.isSelected()) {
					if (e.getKeyChar() == '@') {
						if (txtEmailInstitucional.getText().contains("@")) {
							if (!txtEmailInstitucional.getText().contains("analista.utec.edu.uy")) {
								txtEmailInstitucional.setText(txtEmailInstitucional.getText() + "analista.utec.edu.uy");
							}

						}
					}
					validarMailInstitucional("analista.utec.edu.uy");
				}
				if (rdbtnEstudiante.isSelected()) {
					if (e.getKeyChar() == '@') {
						if (txtEmailInstitucional.getText().contains("@")) {
							if (!txtEmailInstitucional.getText().contains("estudiantes.utec.edu.uy")) {
								txtEmailInstitucional
										.setText(txtEmailInstitucional.getText() + "estudiantes.utec.edu.uy");
							}

						}
					}
					validarMailInstitucional("estudiantes.utec.edu.uy");
				}

			}
		});
		txtEmailInstitucional.setColumns(10);
		panel.add(txtEmailInstitucional);

		JLabel lblRepetirEmailPersonal = new JLabel("Repetir Email personal:");
		lblRepetirEmailPersonal.setBounds(23, 532, 133, 13);
		panel.add(lblRepetirEmailPersonal);

		txtRepetirEmail = new JTextField();
		txtRepetirEmail.setBounds(23, 545, 360, 19);
		txtRepetirEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validarEmailPerIguales();
			}
		});
		txtRepetirEmail.setColumns(10);
		panel.add(txtRepetirEmail);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(23, 406, 133, 13);
		panel.add(lblContrasea);

		txtPass = new JTextField();
		txtPass.setBounds(23, 419, 360, 19);
		txtPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validarContrasenia();
				validarContraseniasIguales();
			}
		});
		txtPass.setColumns(10);
		panel.add(txtPass);

		JLabel lblRepetirContrasea = new JLabel("Repetir contrase\u00F1a:");
		lblRepetirContrasea.setBounds(23, 448, 133, 13);
		panel.add(lblRepetirContrasea);

		txtRepetirPass = new JTextField();
		txtRepetirPass.setBounds(23, 461, 360, 19);
		txtRepetirPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validarContraseniasIguales();
				
			}
		});
		txtRepetirPass.setColumns(10);
		panel.add(txtRepetirPass);
		
		cmbArea = new JComboBox();
		cmbArea.setBounds(158, 620, 225, 21);
		panel.add(cmbArea);
		
		lblArea = new JLabel("Area:");
		lblArea.setBounds(158, 608, 45, 13);
		panel.add(lblArea);
		
		cmbTipoTutor = new JComboBox(TipoTutorEncargado.values());
		cmbTipoTutor.setSelectedIndex(0);
		cmbTipoTutor.setBounds(158, 663, 225, 21);
		panel.add(cmbTipoTutor);
		
		lbltipoTutor = new JLabel("Tipo");
		lbltipoTutor.setBounds(158, 651, 45, 13);
		panel.add(lbltipoTutor);
		
		JLabel lblLocalidad = new JLabel("Localidad:");
		lblLocalidad.setBounds(165, 228, 99, 13);
		panel.add(lblLocalidad);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setBounds(165, 241, 218, 19);
		panel.add(txtLocalidad);
		txtLocalidad.setColumns(10);
		
		JLabel lblDepartamento = new JLabel("Departamento:");
		lblDepartamento.setBounds(23, 278, 159, 13);
		panel.add(lblDepartamento);
		
		cmbDepto = new JComboBox(Departamentos.values());
		cmbDepto.setSelectedIndex(0);
		cmbDepto.setBounds(23, 291, 360, 21);
		panel.add(cmbDepto);
		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(166, 186, 45, 13);
		panel.add(lblGenero);
		
		cmbBoxGenero = new JComboBox(TipoGenero.values());
		cmbBoxGenero.setBounds(166, 198, 217, 21);
		cmbBoxGenero.setSelectedIndex(0);
		panel.add(cmbBoxGenero);

		frame.pack();
	}
	
	public void validarMailInstitucional(String patron) {
		if (txtEmailInstitucional.getText() != null && !txtEmailInstitucional.getText().isEmpty()) {
			if (!txtEmailInstitucional.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + patron + "$")) {
				lblEmailValido.setVisible(true);
				lblEmailValido.setText("Invalido!");
				lblEmailValido.setForeground(Color.RED);
				emailInstValido = false;
			} else {
				lblEmailValido.setVisible(true);
				lblEmailValido.setText("Valido!");
				lblEmailValido.setForeground(Color.GREEN);
				String email = txtEmailInstitucional.getText();
				email = email.replaceAll("@" + patron, "");
				txtUsuario.setText(email);
				emailInstValido = true;

			}
		}
	}
	public void validarContrasenia() {
		if (!txtPass.getText().matches(PASSWORD_PATTERN)) {
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
		
		if(!txtRepetirPass.getText().equals(txtPass.getText())) {
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
	
	public void validarMail() {
		if (!txtEmail.getText().matches(EMAIL_VALIDO)) {
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
	
	
	public void validarEmailPerIguales() {

		if(!txtEmail.getText().equals(txtRepetirEmail.getText())) {
			lblemailPersonalCoincide.setVisible(true);
			lblemailPersonalCoincide.setText("No coinciden!");
			lblemailPersonalCoincide.setForeground(Color.RED);
			emailPerCoincideValido = false;
		} else {
			lblemailPersonalCoincide.setVisible(true);
			lblemailPersonalCoincide.setText("Coinciden!");
			lblemailPersonalCoincide.setForeground(Color.GREEN);
			emailPerCoincideValido = true;

		}
	}
	
	
	
	public void addEstudiante() {
		if(validarDatos()) {

		Estudiante estudiante = 
				new Estudiante( Integer.valueOf(txtDocumento.getText()),
				txtUsuario.getText(),
				txtPass.getText(),
				txtApellido.getText(),
				txtNombre.getText(),
				fechaNacimientoChoser.getDate(),
				cmbDepto.getSelectedItem().toString(),
				TipoGenero.getCharGenero(cmbBoxGenero.getSelectedItem().toString()),
				txtLocalidad.getText(),
				txtEmailInstitucional.getText(),
				txtEmail.getText(),
				txtTelefono.getText(),
				(Itr) comboBoxITR.getSelectedItem(),
				setRolNuevoUsuario("ESTUDIANTE"),
				generacionEstudiante.getYear());
		
		estudiante.setValidado(false);
		estudiante.setActivo(true);
		estudiante = (Estudiante) usuarioRemote.crearUsuario(estudiante);
		JOptionPane.showMessageDialog(null, "Se creo el usuario Estudiante ["+txtUsuario.getText()+"] , para iniciar sesion debe esperar a que lo habiliten en el sistema",
				"Informacion", JOptionPane.INFORMATION_MESSAGE);
		limpiarDatos();
		}
	}
	public void addTutor() {
		if(validarDatos()) {
		Tutor tutor = new Tutor(Integer.valueOf(txtDocumento.getText()),
				txtUsuario.getText(),
				txtPass.getText(),
				txtApellido.getText(),
				txtNombre.getText(),
				fechaNacimientoChoser.getDate(),
				cmbDepto.getSelectedItem().toString(),
				TipoGenero.getCharGenero(cmbBoxGenero.getSelectedItem().toString()),
				txtLocalidad.getText(),
				txtEmailInstitucional.getText(),
				txtEmail.getText(),
				txtTelefono.getText(),
				(Itr) comboBoxITR.getSelectedItem(),
				TipoTutorEncargado.getIdTipo(cmbTipoTutor.getSelectedItem().toString()),
				cmbArea.getSelectedIndex(),
				setRolNuevoUsuario("TUTOR"));
				
		tutor.setValidado(false);
		tutor.setActivo(true);
		tutor = (Tutor) usuarioRemote.crearUsuario(tutor);
		JOptionPane.showMessageDialog(null, "Se creo el usuario Tutor ["+txtUsuario.getText()+"] , para iniciar sesion debe esperar a que lo habiliten en el sistema",
				"Informacion", JOptionPane.INFORMATION_MESSAGE);
		limpiarDatos();
		}
	}
	public void addAnalista() {
		if(validarDatos()) {
		Analista analista = new Analista( Integer.valueOf(txtDocumento.getText()),
				txtUsuario.getText(),
				txtPass.getText(),
				txtApellido.getText(),
				txtNombre.getText(),
				fechaNacimientoChoser.getDate(),
				cmbDepto.getSelectedItem().toString(),
				TipoGenero.getCharGenero(cmbBoxGenero.getSelectedItem().toString()),
				txtLocalidad.getText(),
				txtEmailInstitucional.getText(),
				txtEmail.getText(),
				txtTelefono.getText(),
				(Itr) comboBoxITR.getSelectedItem(),
				setRolNuevoUsuario("ANALISTA")
				);
		analista.setValidado(false);
		analista.setActivo(true);
		analista = (Analista) usuarioRemote.crearUsuario(analista);
		JOptionPane.showMessageDialog(null, "Se creo el usuario Analista ["+txtUsuario.getText()+"] , para iniciar sesion debe esperar a que lo habiliten en el sistema",
				"Informacion", JOptionPane.INFORMATION_MESSAGE);
		limpiarDatos();
		}
	}

	public boolean validarDatos() {
//		if (passValido && emailValido && passCoincideValido && emailPerCoincideValido) {
//			if (txtDocumento.getText().length() == 8 && txtApellido.getText() != "" && txtNombre.getText() != "") {
//				return true;
//			}
//		}
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
		if (comboBoxITR.getSelectedIndex() == -1 ) {

			JOptionPane.showMessageDialog(null,
					"Seleccione el ITR",
					"Datos no validos", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		
		if (!passValido) {
			JOptionPane.showMessageDialog(null,
					"El password no cumple con las validaciones necesarias [8 caracteres mínimo, 1 carácter especial (@#$%), al menos 1 mayúscula y al menos 1 minúscula]",
					"Datos no validos", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (!emailValido) {
			JOptionPane.showMessageDialog(null,
					"El mail es un dato requerido, su formato es usuario@dominio ",
					"Datos no validos", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (txtEmailInstitucional.getText() == null || txtEmailInstitucional.getText() == "") {
			JOptionPane.showMessageDialog(null,
					"El mail institucional es un dato requerido, su formato es usuario@dominio (El dominio se agrega automaticamente cuando ingrese el @)",
					"Datos no validos", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (!passCoincideValido) {
			JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Datos no validos",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (!emailPerCoincideValido) {
			JOptionPane.showMessageDialog(null, "Debe ingresar la misma direccion de correo electronico [no coinciden]",
					"Datos no validos", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if(rdbtnEstudiante.isSelected()) {
			
			if (generacionEstudiante.getYear() > 2022 ||generacionEstudiante.getYear()<2010) {

				JOptionPane.showMessageDialog(null,
						"La generacion debe ser el año en formato 4 cifras numericas, no puede ser vacío",
						"Datos no validos", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}
		

		return true;
	}
	 
	 public Rol setRolNuevoUsuario(String roleName) {	 
			Rol rol = null;
			System.out.println("Imprimiendo roles:");
			for (int i = 0; i < roles.size(); i++) {
				System.out.println(roles.get(i).getNombre());
				if(roles.get(i).getNombre().equals(roleName)) {
					rol=roles.get(i);
				}
			}
			return rol;
	 }
	 
	 public void limpiarDatos() {
		 txtApellido.setText("");
		 txtDocumento.setText("");
		 txtEmail.setText("");
		 txtEmailInstitucional.setText("");
		 txtNombre.setText("");
		 txtPass.setText("");
		 txtRepetirEmail.setText("");
		 txtRepetirPass.setText("");
		 txtTelefono.setText("");
		 txtUsuario.setText("");
	 }
}
