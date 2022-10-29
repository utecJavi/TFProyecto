package tecnofenix.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
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
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Itr;

public class UIUsuario {

	public JFrame frame;
	private JTextField txtId;
	private JTextField txtNombre;
	private List<Estudiante> allEstudiantes;
//	MensajePopUp msj = new MensajePopUp();
	JTable table;
	DefaultTableModel modelo;
	Object[] fila;
	private JTextField txtNombreUsuario;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtFechaNacimiento;
	private JTextField txtEmail;
	private JTextField txtDocumento;
	private JComboBox<Date> comboBoxGeneracion;
	private JComboBox<Itr> comboBoxITR;

	private EJBUsuarioRemoto usuarioRemote;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {

//		this.daoPersona = new DAOPersona();
		usuarioRemote = new EJBUsuarioRemoto();
		frame = new JFrame("Administracion de usuarios");

		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(800, 800));
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(null);

		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(20, 325, 45, 13);
		panel.add(lblId);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(20, 348, 45, 13);
		panel.add(lblNombre);

		txtId = new JTextField();
		txtId.setBounds(44, 322, 85, 19);
		panel.add(txtId);
		txtId.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(20, 361, 360, 19);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		JButton btnAdd = new JButton("Agregar");
		btnAdd.setBounds(671, 486, 85, 21);
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
		final String[] columnNames = { "Id", "Generacion", "Documento", "Nombres", "Apellidos", "Fecha Nacimiento",
				"e-mail", "Usuario", "ITR" };

//U_TIPO, ID, APELLIDOS, CONTRASENIA, DEPARTAMENTO, DOCUMENTO, FECHA_NACIMIENTO, GENERO, LOCALIDAD, MAIL, NOMBRES, TELEFONO, USUARIO, AREA, TIPO, GENERACION, ID_ITR
		// insertamos las columnas
		for (int column = 0; column < columnNames.length; column++) {
			// agrega las columnas a la tabla
			modelo.addColumn(columnNames[column]);
		}

		// Se crea un array que será una de las filas de la tabla.
		fila = new Object[columnNames.length];
		allEstudiantes = usuarioRemote.listarEstudiantes();
		// Se rellena cada posición del array con una de las columnas de la tabla en
		// base de datos.

		for (Estudiante estu : allEstudiantes) {
			fila[0] = estu.getId();
			fila[1] = estu.getGeneracion();
			fila[2] = estu.getDocumento();
			fila[3] = estu.getNombres();
			fila[4] = estu.getApellidos();
			fila[5] = estu.getFechaNacimiento();
			fila[6] = estu.getMail();
			fila[7] = estu.getUsuario();
			fila[8] = estu.getIdItr().getNombre();
			modelo.addRow(fila);
		}

		// Se añade al modelo la fila completa.

		// se define el tamaño de la tabla
//		table.setPreferredScrollableViewportSize(new Dimension(99, 99));
		table.setBounds(93, 215, 100, 100);
		// Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 10, 780, 302);
		// definimos un layout
		// Agregamos el JScrollPane al contenedor
		panel.add(scrollPane);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiar.setBounds(671, 548, 85, 21);
		panel.add(btnLimpiar);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(671, 517, 85, 21);
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
		lblNombreUsuario.setBounds(396, 348, 133, 13);
		panel.add(lblNombreUsuario);

		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setColumns(10);
		txtNombreUsuario.setBounds(396, 361, 360, 19);
		panel.add(txtNombreUsuario);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(20, 390, 45, 13);
		panel.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(20, 403, 360, 19);
		panel.add(txtApellido);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(396, 390, 133, 13);
		panel.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(396, 403, 360, 19);
		panel.add(txtTelefono);

		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
		lblFechaNacimiento.setBounds(20, 432, 85, 13);
		panel.add(lblFechaNacimiento);

		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(20, 445, 360, 19);
		panel.add(txtFechaNacimiento);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(396, 432, 133, 13);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(396, 445, 360, 19);
		panel.add(txtEmail);

		JLabel lblGeneracion = new JLabel("Generacion:");
		lblGeneracion.setBounds(20, 474, 133, 13);
		panel.add(lblGeneracion);

		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(139, 325, 133, 13);
		panel.add(lblDocumento);

		txtDocumento = new JTextField();
		txtDocumento.setColumns(10);
		txtDocumento.setBounds(206, 322, 189, 19);
		panel.add(txtDocumento);

//		DAORol daoRol= new DAORol();
//		List<Rol> rolCollection= new ArrayList<Rol>();
//		rolCollection=daoRol.getAll();

		comboBoxGeneracion = new JComboBox<Date>();
		
		for (int i = 2011; i <= 2022; i++) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, i);
			cal.set(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			Date dateTmp=new Date(cal.getTimeInMillis());			
			comboBoxGeneracion.addItem(dateTmp);
		}

//		for(Rol rol:rolCollection) {
//			String nom =rol.getNombre();
//			comboBox.addItem(nom);
//		}

		// Accion a realizar cuando el JComboBox cambia de item seleccionado.
		comboBoxGeneracion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(comboBoxGeneracion.getSelectedItem().toString());
			}
		});

		comboBoxGeneracion.setBounds(20, 486, 360, 21);
		panel.add(comboBoxGeneracion);

		comboBoxITR = new JComboBox<Itr>();
		List<Itr>listItr = usuarioRemote.listarITR();
		for(Itr itrItem: listItr){
			comboBoxITR.addItem(itrItem);
		}
		
		
		
		comboBoxITR.setBounds(20, 529, 360, 21);
		panel.add(comboBoxITR);

		JLabel lblITR = new JLabel("ITR:");
		lblITR.setBounds(20, 517, 133, 13);
		panel.add(lblITR);

		frame.pack();
		frame.setVisible(true);

	}

	public void limpiar() {
		txtId.setText("");
		txtNombre.setText("");
		txtNombreUsuario.setText("");
		txtApellido.setText("");
		txtTelefono.setText("");
		txtFechaNacimiento.setText("");
		txtDocumento.setText("");
		txtEmail.setText("");
		txtFechaNacimiento.setText("");
	}

	public void validarDatos() {
		System.out.println("Validando datos UIESTUDIANTE");
//		if (txtId.getText().equals("")) {
//			msj.mostrarMensaje(Mensajes.ALTA_FUNCIONALIDAD_ID);
//			txtId.setText(String.valueOf(daoPersona.maxId()+1));
//		} else {
//			if (validarId(Integer.valueOf(txtId.getText()))) {

		if (txtNombre.getText().equals("") || txtNombreUsuario.getText().equals("") || txtApellido.getText().equals("")
				|| txtTelefono.getText().equals("") || txtEmail.getText().equals("")
				|| txtDocumento.getText().equals("")) {
//					msj.mostrarMensaje(Mensajes.ERROR);
			System.out.println("FALTAN DATOS");
		} else {
//					if (txtApellido.getText().equals("")) {
////						msj.mostrarMensaje(Mensajes.ALTA_FUNCIONALIDAD_DESC);
//					} else {
			System.out.println("AGREGANDO USUARIO ESTIDIANTE");
			addEstudiante();
		}
	}
//			}
//		}
//	}

	public void addEstudiante() {
//		(Integer.valueOf(txtId.getText()), txtNombre.getText(),txtDesc.getText());
//		Rol idRol = new Rol();
		Estudiante estudiante = new Estudiante(null, Integer.valueOf(txtDocumento.getText()),
				txtNombreUsuario.getText(), "123456", txtApellido.getText(), txtNombre.getText(),
				new Date(System.currentTimeMillis()), txtEmail.getText(), txtTelefono.getText(),
				new Date(System.currentTimeMillis()), (Itr) comboBoxITR.getSelectedItem());

		estudiante = (Estudiante) usuarioRemote.crearUsuario(estudiante);
		System.err.println(estudiante.toString());
		System.out.println("Se creo el usuario");
//		Date fechNac = new Date(System.currentTimeMillis());
//		comboBoxGeneracion.getSelectedItem();
//		idRol=DAORol.getByName(comboBox.getSelectedItem().toString());
//		Persona perTemp = new Persona(Integer.valueOf(txtId.getText()),
//				txtDocumento.getText(),
//				txtApellido.getText(),
//				txtSegApellido.getText(),
//				txtNombre.getText(), 
//				txtSegNombre.getText(), 
//				fechNac,
//				txtClave.getText(), 
//				idRol.getId(), 
//				txtEmail.getText());
//		
//		if (daoPersona.insert(perTemp) != null) {
//
//			allPersona.add(perTemp);
//			fila[0] = perTemp.getIdPersona();
//			fila[1] = perTemp.getDocumento();
//			fila[2] = perTemp.getNombre1() + " "+perTemp.getNombre2();
//			fila[3] = perTemp.getApellido1() + " "+perTemp.getApellido2();
//			fila[4] = perTemp.getFechaNac();
//			fila[5] = perTemp.getEmail();
//			fila[6] = perTemp.getIdRol();
//			modelo.addRow(fila);
//			
//			msj.mostrarMensaje(Mensajes.ALTA_FUNCIONALIDAD_EXITO);
//			
//		} else {
//			msj.mostrarMensaje(Mensajes.ALTA_FUNCIONALIDAD_ERRORINSERTANDO);
//		}
	}

	public boolean validarId(Integer id) {
//		if (daoPersona.maxId()+1 == id) {
//			return true;
//		}
//		msj.mostrarMensaje(Mensajes.ALTA_FUNCIONALIDAD_IDREPETIDO);
		return false;
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
}