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
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Itr;
import tecnofenix.entidades.Rol;
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
	JLabel lblEmailValido;
	JLabel lblEmailPersonalValido;
	JLabel lblpassValido;
	JLabel lblpassCoincide;
	JLabel lblemailPersonalCoincide;
	JDateChooser dateChooser;
	JComboBox<Itr> comboBoxITR;
	JRadioButton rdbtnEstudiante;
	JRadioButton rdbtnTutor;
	JRadioButton rdbtnAnalista;
	JComboBox cmbTipoTutor;
	JComboBox cmbArea;
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
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(23, 104, 133, 13);
		panel.add(lblDocumento);

		txtDocumento = new JTextField();
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
		lblNombreUsuario.setBounds(23, 295, 133, 13);
		panel.add(lblNombreUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setEnabled(false);
		txtUsuario.setBounds(23, 308, 360, 19);
		txtUsuario.setColumns(10);
		panel.add(txtUsuario);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(23, 211, 133, 13);
		panel.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(23, 224, 360, 19);
		txtTelefono.setColumns(10);
		panel.add(txtTelefono);

		JLabel lblEmail = new JLabel("Email personal:");
		lblEmail.setBounds(23, 421, 133, 13);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validarMail();
				validarEmailPerIguales();
			}
		});
		txtEmail.setBounds(23, 434, 360, 19);
		txtEmail.setColumns(10);
		panel.add(txtEmail);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(23, 169, 86, 13);
		panel.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setBounds(23, 182, 360, 19);
		txtApellido.setColumns(10);
		panel.add(txtApellido);

		

		JLabel lblITR = new JLabel("ITR:");
		lblITR.setBounds(23, 595, 133, 13);
		panel.add(lblITR);

		comboBoxITR = new JComboBox<Itr>();
		List<Itr> listItr = usuarioRemote.listarITR();
		for (Itr itrItem : listItr) {
			comboBoxITR.addItem(itrItem);
		}
		comboBoxITR.setBounds(23, 607, 360, 21);
		comboBoxITR.setEnabled(true);
		panel.add(comboBoxITR);

		
		JLabel lblGeneracion = new JLabel("Generacion:");
		lblGeneracion.setBounds(23, 543, 133, 13);
		panel.add(lblGeneracion);
		
		generacionEstudiante = new JYearChooser();
		generacionEstudiante.setBounds(23, 560, 86, 19);
		panel.add(generacionEstudiante);

		JButton btnAcrualizar = new JButton("Crear usuario");
		btnAcrualizar.setBounds(276, 638, 107, 21);
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
		rdbtnEstudiante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnEstudiante.isSelected()) {
					validarMailInstitucional("estudiantes.utec.edu.uy");
					generacionEstudiante.setVisible(true);
					lblGeneracion.setVisible(true);
				}
			}
		});
		rdbtnEstudiante.setBounds(53, 29, 86, 21);
		rdbtnEstudiante.setSelected(true);
		panel.add(rdbtnEstudiante);

		rdbtnTutor = new JRadioButton("Tutor");
		rdbtnTutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnTutor.isSelected()) {
					validarMailInstitucional("tutor.utec.edu.uy");
					generacionEstudiante.setVisible(false);
					lblGeneracion.setVisible(false);
				}
			}
		});
		rdbtnTutor.setBounds(158, 29, 71, 21);
		panel.add(rdbtnTutor);

		rdbtnAnalista = new JRadioButton("Analista");
		rdbtnAnalista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnAnalista.isSelected()) {
					validarMailInstitucional("analista.utec.edu.uy");
					generacionEstudiante.setVisible(false);
					lblGeneracion.setVisible(false);
				}
			}
		});
		rdbtnAnalista.setBounds(251, 29, 86, 21);
		panel.add(rdbtnAnalista);

		butonGroup.add(rdbtnEstudiante);
		butonGroup.add(rdbtnTutor);
		butonGroup.add(rdbtnAnalista);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(23, 514, 99, 19);
		panel.add(dateChooser);

		JLabel lblNewLabel = new JLabel("Fecha Nacimiento");
		lblNewLabel.setBounds(23, 502, 86, 13);
		panel.add(lblNewLabel);

		

		JLabel lblEmailInstitucional = new JLabel("Email institucional:");
		lblEmailInstitucional.setBounds(23, 253, 133, 13);
		panel.add(lblEmailInstitucional);

		lblEmailValido = new JLabel("Valido!");
		lblEmailValido.setBounds(389, 269, 56, 13);
		lblEmailValido.setVisible(false);
		panel.add(lblEmailValido);

		lblpassValido = new JLabel("Valido!");
		lblpassValido.setBounds(389, 353, 56, 13);
		lblpassValido.setVisible(false);
		panel.add(lblpassValido);
		
		lblpassCoincide = new JLabel("Valido!");
		lblpassCoincide.setBounds(389, 395, 92, 13);
		lblpassCoincide.setVisible(false);
		panel.add(lblpassCoincide);
		
		lblEmailPersonalValido = new JLabel("Valido!");
		lblEmailPersonalValido.setBounds(389, 437, 92, 13);
		lblEmailPersonalValido.setVisible(false);
		panel.add(lblEmailPersonalValido);
		
		lblemailPersonalCoincide = new JLabel("Valido!");
		lblemailPersonalCoincide.setBounds(389, 476, 92, 13);
		lblemailPersonalCoincide.setVisible(false);
		panel.add(lblemailPersonalCoincide);
		
		
		txtEmailInstitucional = new JTextField();
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
		txtEmailInstitucional.setBounds(23, 266, 360, 19);
		panel.add(txtEmailInstitucional);

		JLabel lblRepetirEmailPersonal = new JLabel("Repetir Email personal:");
		lblRepetirEmailPersonal.setBounds(23, 460, 133, 13);
		panel.add(lblRepetirEmailPersonal);

		txtRepetirEmail = new JTextField();
		txtRepetirEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validarEmailPerIguales();
			}
		});
		txtRepetirEmail.setColumns(10);
		txtRepetirEmail.setBounds(23, 473, 360, 19);
		panel.add(txtRepetirEmail);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(23, 337, 133, 13);
		panel.add(lblContrasea);

		txtPass = new JTextField();
		txtPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validarContrasenia();
				validarContraseniasIguales();
			}
		});
		txtPass.setColumns(10);
		txtPass.setBounds(23, 350, 360, 19);
		panel.add(txtPass);

		JLabel lblRepetirContrasea = new JLabel("Repetir contrase\u00F1a:");
		lblRepetirContrasea.setBounds(23, 379, 133, 13);
		panel.add(lblRepetirContrasea);

		txtRepetirPass = new JTextField();
		txtRepetirPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validarContraseniasIguales();
				
			}
		});
		txtRepetirPass.setColumns(10);
		txtRepetirPass.setBounds(23, 392, 360, 19);
		panel.add(txtRepetirPass);
		
		cmbArea = new JComboBox();
		cmbArea.setBounds(158, 516, 225, 21);
		panel.add(cmbArea);
		
		JLabel lblArea = new JLabel("Area:");
		lblArea.setBounds(155, 502, 45, 13);
		panel.add(lblArea);
		
		cmbTipoTutor = new JComboBox();
		cmbTipoTutor.setBounds(158, 560, 225, 21);
		panel.add(cmbTipoTutor);
		
		JLabel lbltipoTutor = new JLabel("Tipo");
		lbltipoTutor.setBounds(158, 543, 45, 13);
		panel.add(lbltipoTutor);

		frame.pack();
	}
	
	public void validarMailInstitucional(String patron) {
		if (!txtEmailInstitucional.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+patron+"$")) {
			lblEmailValido.setVisible(true);
			lblEmailValido.setText("Invalido!");
			lblEmailValido.setForeground(Color.RED);
			emailInstValido = false;
		} else {
			lblEmailValido.setVisible(true);
			lblEmailValido.setText("Valido!");
			lblEmailValido.setForeground(Color.GREEN);
			String email =txtEmailInstitucional.getText();
			email=email.replaceAll("@"+patron,"");
			txtUsuario.setText(email);
			emailInstValido = true;

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

		Estudiante estudiante = new Estudiante( Integer.valueOf(txtDocumento.getText()),
				txtUsuario.getText(), txtPass.getText(), txtApellido.getText(), txtNombre.getText(),
				dateChooser.getDate(), txtEmail.getText(), txtTelefono.getText(),
				(Itr) comboBoxITR.getSelectedItem(),generacionEstudiante.getYear(),setRolNuevoUsuario("ESTUDIANTE"));
		
		estudiante.setValidado(false);
		estudiante.setActivo(true);
		estudiante = (Estudiante) usuarioRemote.crearUsuario(estudiante);
		JOptionPane.showMessageDialog(null, "Se creo el usuario Estudiante ["+txtUsuario.getText()+"] , para iniciar sesion debe esperar a que lo habiliten en el sistema",
				"Error", JOptionPane.INFORMATION_MESSAGE);
		limpiarDatos();
		}
	}
	public void addTutor() {
		if(validarDatos()) {
		Tutor tutor = new Tutor( Integer.valueOf(txtDocumento.getText()),
				txtUsuario.getText(),  txtPass.getText(), txtApellido.getText(), txtNombre.getText(),
				dateChooser.getDate(), txtEmail.getText(), txtTelefono.getText(),
				(Itr) comboBoxITR.getSelectedItem(), cmbTipoTutor.getSelectedIndex(), cmbArea.getSelectedIndex(),setRolNuevoUsuario("TUTOR"));
				
		tutor.setValidado(false);
		tutor.setActivo(true);
		tutor = (Tutor) usuarioRemote.crearUsuario(tutor);
		JOptionPane.showMessageDialog(null, "Se creo el usuario Tutor ["+txtUsuario.getText()+"] , para iniciar sesion debe esperar a que lo habiliten en el sistema",
				"Error", JOptionPane.INFORMATION_MESSAGE);
		limpiarDatos();
		}
	}
	public void addAnalista() {
		if(validarDatos()) {
		Analista analista = new Analista( Integer.valueOf(txtDocumento.getText()),
				txtUsuario.getText(), txtPass.getText(), txtApellido.getText(), txtNombre.getText(),
				dateChooser.getDate(), txtEmail.getText(), txtTelefono.getText(),
				(Itr) comboBoxITR.getSelectedItem(),setRolNuevoUsuario("ANALISTA"));
		analista.setValidado(false);
		analista.setActivo(true);
		analista = (Analista) usuarioRemote.crearUsuario(analista);
		System.err.println(analista.toString());
		System.out.println("Se creo el usuario tutor");
		}
	}
	 public boolean validarDatos() {
		 if(passValido && emailValido && passCoincideValido && emailPerCoincideValido) {
			 if(txtDocumento.getText().length()==8 && txtApellido.getText()!= "" && txtNombre.getText()!= "") {				 
			 return true; 
			 }		
		 }
		 return false;
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
