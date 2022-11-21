package tecnofenix.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.Analista;
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Itr;
import tecnofenix.entidades.Rol;
import tecnofenix.entidades.Tutor;
import tecnofenix.entidades.Usuario;
import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;

public class UIUsuario {

	public JFrame frame;
	private JTextField txtNombre;
	private List<Usuario> allUsuarios;
//	MensajePopUp msj = new MensajePopUp();
	private JTable table;
	private DefaultTableModel modelo;
	private Object[] fila;
	private JTextField txtNombreUsuario;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtDocumento;
	private JDateChooser dateBuscarChooser;
	private JYearChooser yearBuscarChooser;
	private JComboBox<Itr> comboBoxITR;

	private EJBUsuarioRemoto usuarioRemote;
	private JTextField textBuscarDoc;
	private JTextField textBuscarNombre;
	private JTextField textBuscarApellido;
	private JTextField textBuscarUsuario;
	private JTextField textBuscarTelefono;
	private JTextField textBuscarMail;
	private JComboBox<String> comboBuscarTipoUsuario;
	private JTextField textBuscarID;
	private List<Rol> roles;
	
	
	public UIUsuario() {
		System.out.println("Instanciando ventana usuario");
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {

		usuarioRemote = new EJBUsuarioRemoto();
		allUsuarios = new ArrayList<Usuario>();
		frame = new JFrame("Administracion de usuarios");
		roles = usuarioRemote.listarRoles();
		
		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(800, 800));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(20, 507, 85, 13);
		panel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(20, 520, 360, 19);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		JButton btnAdd = new JButton("Agregar");
		btnAdd.setBounds(671, 645, 85, 21);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
		});
		panel.add(btnAdd);

		modelo = new DefaultTableModel();

		// se crea la Tabla con el modelo DefaultTableModel
		table = new JTable(modelo);
		table.setDefaultEditor(Object.class, null);
		table.setCellSelectionEnabled(true);
		table.setForeground(Color.GREEN);
		table.setColumnSelectionAllowed(true);
		table.setBackground(Color.BLACK);
		// crea un array que contiene los nombre de las columnas
		final String[] columnNames = { "Tipo","Id", "Generacion", "Documento", "Nombres", "Apellidos", "Fecha Nacimiento",
				"e-mail", "Usuario", "ITR" , "Activo" };
//U_TIPO, ID, APELLIDOS, CONTRASENIA, DEPARTAMENTO, DOCUMENTO, FECHA_NACIMIENTO, GENERO, LOCALIDAD, MAIL, NOMBRES, TELEFONO, USUARIO, AREA, TIPO, GENERACION, ID_ITR

		// insertamos las columnas
		for (int column = 0; column < columnNames.length; column++) {
			// agrega las columnas a la tabla
			modelo.addColumn(columnNames[column]);
		}

		// Se crea un array que será una de las filas de la tabla.
		fila = new Object[columnNames.length];
			
		actualizarLista();
		
		


		// se define el tamaño de la tabla
		table.setBounds(93, 215, 100, 100);
		// Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 169, 780, 302);
		// definimos un layout
		// Agregamos el JScrollPane al contenedor
		panel.add(scrollPane);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(671, 707, 85, 21);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		panel.add(btnLimpiar);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(671, 676, 85, 21);
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = table.getSelectedRow();

				if (row >= 0) {
//					System.out.println(modelo.getValueAt(row, column));
					String mensaje = "Id " + modelo.getValueAt(row, 0).toString() + " Nombre "
							+ modelo.getValueAt(row, 1).toString() + " Desc " + modelo.getValueAt(row, 2).toString();

					if (borrarRow(mensaje)) {
//						daoPersona.delete(allPersona.get(row));
						modelo.removeRow(row);

					}
				}
			}
		});
		panel.add(btnBorrar);

		JLabel lblNombreUsuario = new JLabel("Nombre de Usuario:");
		lblNombreUsuario.setBounds(396, 507, 133, 13);
		panel.add(lblNombreUsuario);

		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setBounds(396, 520, 360, 19);
		txtNombreUsuario.setColumns(10);
		panel.add(txtNombreUsuario);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(20, 549, 103, 13);
		panel.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setBounds(20, 562, 360, 19);
		txtApellido.setColumns(10);
		panel.add(txtApellido);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(396, 549, 133, 13);
		panel.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(396, 562, 360, 19);
		txtTelefono.setColumns(10);
		panel.add(txtTelefono);

		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
		lblFechaNacimiento.setBounds(20, 591, 118, 13);
		panel.add(lblFechaNacimiento);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(396, 591, 133, 13);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(396, 604, 360, 19);
		txtEmail.setColumns(10);
		panel.add(txtEmail);

		JLabel lblGeneracion = new JLabel("Generacion:");
		lblGeneracion.setBounds(20, 633, 133, 13);
		panel.add(lblGeneracion);

		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(20, 484, 133, 13);
		panel.add(lblDocumento);

		txtDocumento = new JTextField();
		txtDocumento.setBounds(87, 481, 189, 19);
		txtDocumento.setColumns(10);
		panel.add(txtDocumento);

		comboBoxITR = new JComboBox<Itr>();
		comboBoxITR.setBounds(20, 688, 360, 21);
		List<Itr>listItr = usuarioRemote.listarITR();
		for(Itr itrItem: listItr){
			comboBoxITR.addItem(itrItem);
		}
		panel.add(comboBoxITR);

		JLabel lblITR = new JLabel("ITR:");
		lblITR.setBounds(20, 676, 133, 13);
		panel.add(lblITR);
		
		JButton btnAgregarTutor = new JButton("Agregar tutor");
		btnAgregarTutor.setBounds(541, 645, 128, 21);
		btnAgregarTutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTutor();
			}
			
		});

		panel.add(btnAgregarTutor);
		
		dateBuscarChooser = new JDateChooser();
		dateBuscarChooser.setBounds(20, 604, 186, 19);
		panel.add(dateBuscarChooser);

		yearBuscarChooser = new JYearChooser();
		yearBuscarChooser.setBounds(19, 645, 104, 19);
		panel.add(yearBuscarChooser);
		
		JLabel lblDocumento_1 = new JLabel("Documento:");
		lblDocumento_1.setBounds(20, 26, 133, 13);
		panel.add(lblDocumento_1);
		
		textBuscarDoc = new JTextField();
		textBuscarDoc.setColumns(10);
		textBuscarDoc.setBounds(87, 23, 189, 19);
		textBuscarDoc.setText("");
		panel.add(textBuscarDoc);
		
		JLabel lblNombre_1 = new JLabel("Nombre:");
		lblNombre_1.setBounds(20, 49, 85, 13);
		panel.add(lblNombre_1);
		
		textBuscarNombre = new JTextField();
		textBuscarNombre.setColumns(10);
		textBuscarNombre.setBounds(20, 62, 360, 19);
		textBuscarNombre.setText("");
		panel.add(textBuscarNombre);
		
		JLabel lblApellido_1 = new JLabel("Apellido:");
		lblApellido_1.setBounds(20, 91, 103, 13);
		panel.add(lblApellido_1);
		
		textBuscarApellido = new JTextField();
		textBuscarApellido.setColumns(10);
		textBuscarApellido.setBounds(20, 104, 360, 19);
		textBuscarApellido.setText("");
		panel.add(textBuscarApellido);
		
		JLabel lblFechaNacimiento_1 = new JLabel("Tipo de usuario:");
		lblFechaNacimiento_1.setBounds(20, 133, 118, 13);
		panel.add(lblFechaNacimiento_1);
		
		JLabel lblNombreUsuario_1 = new JLabel("Nombre de Usuario:");
		lblNombreUsuario_1.setBounds(390, 7, 133, 13);
		panel.add(lblNombreUsuario_1);
		
		textBuscarUsuario = new JTextField();
		textBuscarUsuario.setColumns(10);
		textBuscarUsuario.setBounds(390, 20, 360, 19);
		textBuscarUsuario.setText("");
		panel.add(textBuscarUsuario);
		
		JLabel lblTelefono_1 = new JLabel("Telefono:");
		lblTelefono_1.setBounds(390, 49, 133, 13);
		panel.add(lblTelefono_1);
		
		textBuscarTelefono = new JTextField();
		textBuscarTelefono.setColumns(10);
		textBuscarTelefono.setBounds(390, 62, 360, 19);
		textBuscarTelefono.setText("");
		panel.add(textBuscarTelefono);
		
		JLabel lblEmail_1 = new JLabel("Email:");
		lblEmail_1.setBounds(390, 91, 133, 13);
		panel.add(lblEmail_1);
		
		textBuscarMail = new JTextField();
		textBuscarMail.setColumns(10);
		textBuscarMail.setBounds(390, 104, 360, 19);
		textBuscarMail.setText("");
		panel.add(textBuscarMail);
		
		JLabel lblITR_1 = new JLabel("ITR:");
		lblITR_1.setBounds(396, 132, 133, 13);
		panel.add(lblITR_1);
		
		JComboBox<Itr> comboBuscarITR = new JComboBox<Itr>();
		comboBuscarITR.addItem(new Itr(null,"",""));
		for(Itr itrItem: listItr){
			comboBuscarITR.addItem(itrItem);
		}
		comboBuscarITR.setBounds(396, 144, 210, 21);
		comboBuscarITR.setSelectedIndex(0);
		panel.add(comboBuscarITR);
		
		JYearChooser yearChooBuscar = new JYearChooser();
		yearChooBuscar.setBounds(216, 146, 164, 19);
		panel.add(yearChooBuscar);
		
		JLabel lblGeneracion_1 = new JLabel("Generacion:");
		lblGeneracion_1.setBounds(216, 133, 133, 13);
		panel.add(lblGeneracion_1);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarPor( comboBuscarTipoUsuario.getSelectedItem().toString(),  textBuscarID.getText() , "", 
						textBuscarDoc.getText(), textBuscarNombre.getText(), textBuscarApellido.getText()
						, textBuscarMail.getText(), textBuscarUsuario.getText(), 
						comboBuscarITR.getSelectedItem().toString(), String.valueOf(yearChooBuscar.getYear()));
				
				
			}
		});
		btnBuscar.setBounds(671, 138, 85, 21);
		panel.add(btnBuscar);
		
		comboBuscarTipoUsuario = new JComboBox<String>();
		comboBuscarTipoUsuario.setBounds(20, 144, 189, 21);
		comboBuscarTipoUsuario.addItem("");
		comboBuscarTipoUsuario.addItem("ESTUDIANTE");
		comboBuscarTipoUsuario.addItem("TUTOR");
		comboBuscarTipoUsuario.addItem("ANALISTA");
		comboBuscarTipoUsuario.setSelectedItem("");
		panel.add(comboBuscarTipoUsuario);
		
		textBuscarID = new JTextField();
		textBuscarID.setColumns(10);
		textBuscarID.setBounds(306, 23, 74, 19);
		textBuscarID.setText("");
		panel.add(textBuscarID);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(304, 7, 45, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Filtros de Busqueda");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(20, 7, 164, 13);
		panel.add(lblNewLabel_1);
		
		frame.pack();
		frame.setVisible(true);

	}
	
	
	
	public void limpiar() {
		
		txtNombre.setText("");
		txtNombreUsuario.setText("");
		txtApellido.setText("");
		txtTelefono.setText("");
		dateBuscarChooser.cleanup();
		txtDocumento.setText("");
		txtEmail.setText("");
		yearBuscarChooser.setYear(2022);
	}

	public void validarDatos() {
		System.out.println("Validando datos USUARIO");


		if (txtNombre.getText().equals("") || txtNombreUsuario.getText().equals("") || txtApellido.getText().equals("")
				|| txtTelefono.getText().equals("") || txtEmail.getText().equals("")
				|| txtDocumento.getText().equals("") ||dateBuscarChooser.getDate()==null) {


			System.out.println("FALTAN DATOS");
		} else {

			System.out.println("AGREGANDO USUARIO ESTUDIANTE");
			addEstudiante();
		}
	}


	public void addEstudiante() {
//		(Integer.valueOf(txtId.getText()), txtNombre.getText(),txtDesc.getText());
//		Rol idRol = new Rol();
		Estudiante estudiante = new Estudiante( Integer.valueOf(txtDocumento.getText()),
				txtNombreUsuario.getText(), "123456", txtApellido.getText(), txtNombre.getText(),
				dateBuscarChooser.getDate(), txtEmail.getText(), txtTelefono.getText(),
				(Itr) comboBoxITR.getSelectedItem(),yearBuscarChooser.getYear(),setRolNuevoUsuario("ESTUDIANTE"));

		estudiante = (Estudiante) usuarioRemote.crearUsuario(estudiante);
		System.err.println(estudiante.toString());
		System.out.println("Se creo el usuario");
		actualizarLista();
	}
	public void addTutor() {
//		(Integer.valueOf(txtId.getText()), txtNombre.getText(),txtDesc.getText());
//		Rol idRol = new Rol();
		Tutor tutor = new Tutor( Integer.valueOf(txtDocumento.getText()),
				txtNombreUsuario.getText(), "123456", txtApellido.getText(), txtNombre.getText(),
				new Date(System.currentTimeMillis()), txtEmail.getText(), txtTelefono.getText(),
				(Itr) comboBoxITR.getSelectedItem(), 1, 1,setRolNuevoUsuario("TUTOR"));

		tutor = (Tutor) usuarioRemote.crearUsuario(tutor);
		System.err.println(tutor.toString());
		System.out.println("Se creo el usuario tutor");
		actualizarLista();
	}


	public boolean borrarRow(String mensaje) {
//		msj.mostrarMensaje(Mensajes.BAJA);

		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(null, "Seguro quieres borrar: " + mensaje, "Warning",
				dialogButton);
		if (dialogResult == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}
	public void actualizarLista() {
		
		limpiarTabla();
		
		allUsuarios= usuarioRemote.listarUsuarios();
		
		cargarTabla(allUsuarios);
	

	}
	
	
	 public void cargarTabla(List<Usuario> listPasada) {
		 	// Se rellena cada posición del array con una de las columnas de la tabla en
			// base de datos.
			for (Usuario usuTemp : listPasada) {
				if (usuTemp instanceof Estudiante) {
					fila[0] = usuTemp.getDecriminatorValue();
					fila[1] = usuTemp.getId();
					fila[2] = ((Estudiante) usuTemp).getGeneracion();
					fila[3] = usuTemp.getDocumento();
					fila[4] = usuTemp.getNombres();
					fila[5] = usuTemp.getApellidos();
					fila[6] = usuTemp.getFechaNacimiento();
					fila[7] = usuTemp.getMail();
					fila[8] = usuTemp.getUsuario();
					fila[9] = usuTemp.getItr().getNombre();
					if (usuTemp.getValidado()) {
						fila[10] = "Si";
					} else {
						fila[10] = "No";
					}
					// Se añade al modelo la fila completa.
					modelo.addRow(fila);
				}
				if (usuTemp instanceof Tutor) {
					fila[0] = usuTemp.getDecriminatorValue();
					fila[1] = usuTemp.getId();
					fila[2] = "";
					fila[3] = usuTemp.getDocumento();
					fila[4] = usuTemp.getNombres();
					fila[5] = usuTemp.getApellidos();
					fila[6] = usuTemp.getFechaNacimiento();
					fila[7] = usuTemp.getMail();
					fila[8] = usuTemp.getUsuario();
					fila[9] = usuTemp.getItr().getNombre();
					if (usuTemp.getValidado()) {
						fila[10] = "Si";
					} else {
						fila[10] = "No";
					}
					// Se añade al modelo la fila completa.
					modelo.addRow(fila);
				}
				if (usuTemp instanceof Analista) {
					fila[0] = usuTemp.getDecriminatorValue();
					fila[1] = usuTemp.getId();
					fila[2] = "";
					fila[3] = usuTemp.getDocumento();
					fila[4] = usuTemp.getNombres();
					fila[5] = usuTemp.getApellidos();
					fila[6] = usuTemp.getFechaNacimiento();
					fila[7] = usuTemp.getMail();
					fila[8] = usuTemp.getUsuario();
					fila[9] = usuTemp.getItr().getNombre();
					if (usuTemp.getValidado()) {
						fila[10] = "Si";
					} else {
						fila[10] = "No";
					}
					// Se añade al modelo la fila completa.
					modelo.addRow(fila);
				}
			}
	 }
	public void buscarPor(String tipo, String id ,String depto,String doc,String nombre,String apellido
			,String mail,String usuario,String itrNombre,String generacion) {
		limpiarTabla();
		allUsuarios = usuarioRemote.buscarUsuarioPor(tipo, id, depto, doc, nombre, apellido, mail, usuario, itrNombre, generacion);
		if(allUsuarios != null) {
		System.out.println(allUsuarios.toString());
		// Se rellena cada posición del array con una de las columnas de la tabla en
		// base de datos.
		cargarTabla(allUsuarios);
	}
	}
	
	public void limpiarTabla() {
		if (allUsuarios != null || !allUsuarios.isEmpty() || allUsuarios.size() > 0) {
			allUsuarios.clear();	
		}
		modelo.getDataVector().removeAllElements();
		modelo.fireTableDataChanged();

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
}