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
import tecnofenix.entidades.Tutor;
import tecnofenix.entidades.Usuario;
import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;

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
	private JDateChooser dateChooser;
	private JYearChooser yearChooser;
	private JComboBox<Itr> comboBoxITR;

	private EJBUsuarioRemoto usuarioRemote;

	
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
		
		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(800, 800));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(20, 507, 45, 13);
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
				"e-mail", "Usuario", "ITR"  };
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
		lblApellido.setBounds(20, 549, 45, 13);
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
		lblFechaNacimiento.setBounds(20, 591, 85, 13);
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
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(20, 604, 186, 19);
		panel.add(dateChooser);

		yearChooser = new JYearChooser();
		yearChooser.setBounds(19, 645, 104, 19);
		panel.add(yearChooser);
		
		frame.pack();
		frame.setVisible(true);

	}
	
	
	
	public void limpiar() {
		
		txtNombre.setText("");
		txtNombreUsuario.setText("");
		txtApellido.setText("");
		txtTelefono.setText("");
		dateChooser.cleanup();
		txtDocumento.setText("");
		txtEmail.setText("");
		yearChooser.setYear(2022);
	}

	public void validarDatos() {
		System.out.println("Validando datos USUARIO");


		if (txtNombre.getText().equals("") || txtNombreUsuario.getText().equals("") || txtApellido.getText().equals("")
				|| txtTelefono.getText().equals("") || txtEmail.getText().equals("")
				|| txtDocumento.getText().equals("") ||dateChooser.getDate()==null) {


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
				dateChooser.getDate(), txtEmail.getText(), txtTelefono.getText(),
				(Itr) comboBoxITR.getSelectedItem(),yearChooser.getYear());

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
				(Itr) comboBoxITR.getSelectedItem(), 1, 1);

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
	
}