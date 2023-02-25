package tecnofenix.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.Analista;
import tecnofenix.entidades.Departamentos;
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Itr;
import tecnofenix.entidades.Rol;
import tecnofenix.entidades.TipoGenero;
import tecnofenix.entidades.TipoTutorArea;
import tecnofenix.entidades.TipoTutorEncargado;
import tecnofenix.entidades.Tutor;
import tecnofenix.entidades.Usuario;
import com.toedter.calendar.JYearChooser;


import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ChangeEvent;

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
	private JComboBox cmbDepartamento;
	private JComboBox cmbBoxGenero;
	private JDateChooser fechaNacimientoCalendar;
	private JCheckBox chckbxActivarUsuario;
	private JCheckBox chckbxValidarUsuario;
	private JYearChooser yearBuscarChooser;
	private JYearChooser generacionAnioBuscar;
	
	private JComboBox<Itr> comboBoxITR;
	private JComboBox<Itr> comboBuscarITR;
	
	private EJBUsuarioRemoto usuarioRemote;
	private JTextField textBuscarDoc;
	private JTextField textBuscarNombre;
	private JTextField textBuscarApellido;
	private JComboBox cmbDepto;//departamentos combo
	private JTextField textBuscarUsuario;
	private JTextField textBuscarTelefono;
	private JTextField textBuscarMail;
	private JComboBox<String> comboBuscarTipoUsuario;
	private JTextField textBuscarID;
	private List<Rol> roles;
	private JTextField textLocalidad;
	
	private JCheckBox chckbxValidado;
	private JCheckBox chckbxActivo;
	private JCheckBox chckbxNoValidado;
	private JCheckBox chckbxNoActivo;
	private JCheckBox chckbxTodos;
	private JTextField txtEmailPersonal;
	private JTextField txtLocalidad;
	private JTextField txtId;
	private Usuario usuarioEditable;
	private JComboBox cmbArea;
	private JLabel lblArea;
	private JLabel lbltipoTutor;
	private JComboBox cmbTipoTutor;
	private JLabel lblGeneracion;
	
	public UIUsuario() {
		System.out.println("Instanciando ventana usuario");
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar(Usuario user) {

		usuarioRemote = new EJBUsuarioRemoto();
		allUsuarios = new ArrayList<Usuario>();
		frame = new JFrame("Administracion de usuarios");
		roles = usuarioRemote.listarRoles();
		
		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(1200, 1000));
		frame.getContentPane().add(panel, BorderLayout.EAST);
		panel.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(20, 507, 85, 13);
		panel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(20, 520, 360, 19);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(1084, 521, 85, 21);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
		});
		panel.add(btnModificar);

		modelo = new DefaultTableModel();

		// se crea la Tabla con el modelo DefaultTableModel
		table = new JTable(modelo);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setDefaultEditor(Object.class, null);
//		table.setCellSelectionEnabled(true);
		table.setForeground(Color.GREEN);
//		table.setColumnSelectionAllowed(true);
		table.setBackground(Color.BLACK);
		// crea un array que contiene los nombre de las columnas
		final String[] columnNames = {"Id","Tipo", "Generacion", "Documento", "Nombres", "Apellidos", "Fecha Nacimiento",
				"e-mail", "Usuario", "ITR" ,"Depto","Localidad", "Validado","Activo" };
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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
		// Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 169, 1159, 302);
		// definimos un layout
		// Agregamos el JScrollPane al contenedor
		panel.add(scrollPane);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
	        public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
				System.out.println("Buscando usuario seleccionado... ");
				usuarioEditable=usuarioRemote.encontrarUsuario(Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()));
	           
				txtId.setText(usuarioEditable.getId().toString());
	            txtDocumento.setText(usuarioEditable.getDocumento().toString());
	            txtApellido.setText(usuarioEditable.getApellidos());
	            txtNombre.setText(usuarioEditable.getNombres());
	            txtEmail.setText(usuarioEditable.getMail());
	            txtEmailPersonal.setText(usuarioEditable.getMailPersonal());
	            txtTelefono.setText(usuarioEditable.getTelefono());
	            txtLocalidad.setText(usuarioEditable.getLocalidad());
	            txtNombreUsuario.setText(usuarioEditable.getUsuario());
	            comboBoxITR.setSelectedItem(usuarioEditable.getItr());
	            cmbBoxGenero.setSelectedItem(TipoGenero.fromString(usuarioEditable.getGenero()));
	            cmbDepartamento.setSelectedItem(Departamentos.fromString(usuarioEditable.getDepartamento()));
	            fechaNacimientoCalendar.setDate(usuarioEditable.getFechaNacimiento());
	            chckbxActivarUsuario.setSelected(usuarioEditable.getActivo());
	            chckbxValidarUsuario.setSelected(usuarioEditable.getValidado());
	            
	            if(usuarioEditable instanceof Estudiante) {
	            	lblGeneracion.setVisible(true);
	            	yearBuscarChooser.setVisible(true);
	            	yearBuscarChooser.setYear(((Estudiante)usuarioEditable).getGeneracion());
	            }else {
	            	lblGeneracion.setVisible(false);
	            	yearBuscarChooser.setVisible(false);
	            }
	            if(usuarioEditable instanceof Tutor) {
	            	cmbTipoTutor.setVisible(true);
	            	cmbArea.setVisible(true);
	            	lblArea.setVisible(true);
	            	lbltipoTutor.setVisible(true);
	            	cmbTipoTutor.setSelectedItem(TipoTutorEncargado.getIdTipo(((Tutor)usuarioEditable).getTipo()));
	            	cmbArea.setSelectedItem(TipoTutorArea.getIdArea(((Tutor)usuarioEditable).getTipo()));
	            }else {
	            	lblArea.setVisible(false);
	            	lbltipoTutor.setVisible(false);
	            	cmbTipoTutor.setVisible(false);
	            	cmbArea.setVisible(false);
	            }
				}
	        }
	    });

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(1084, 583, 85, 21);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		panel.add(btnLimpiar);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(1084, 552, 85, 21);
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row >= 0) {
					String mensaje = "CI " + modelo.getValueAt(row, 3).toString() + " Nombre "
							+ modelo.getValueAt(row, 4).toString() + " " +modelo.getValueAt(row, 5).toString();
					if (borrarRow(mensaje)) {
						usuarioEditable.setActivo(false);
						usuarioRemote.modificarUsuario(usuarioEditable);
						actualizarLista();

					}
				}else {
					JOptionPane.showMessageDialog(null, "Ningun usuario esta seleccionado, haga click sobre la tabla para seleccionar uno",
							"Intente nuevamente", JOptionPane.INFORMATION_MESSAGE);
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

		lblGeneracion = new JLabel("Generacion:");
		lblGeneracion.setBounds(20, 720, 133, 13);
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
		
		fechaNacimientoCalendar = new JDateChooser();
		fechaNacimientoCalendar.setBounds(20, 604, 186, 19);
		panel.add(fechaNacimientoCalendar);

		yearBuscarChooser = new JYearChooser();
		yearBuscarChooser.setBounds(19, 732, 104, 19);
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
		lblITR_1.setBounds(396, 133, 133, 13);
		panel.add(lblITR_1);
		
		comboBuscarITR = new JComboBox<Itr>();
		comboBuscarITR.addItem(new Itr(null,"",""));
		for(Itr itrItem: listItr){
			comboBuscarITR.addItem(itrItem);
		}
		comboBuscarITR.setBounds(396, 145, 338, 21);
		comboBuscarITR.setSelectedIndex(0);
		panel.add(comboBuscarITR);
		
		generacionAnioBuscar = new JYearChooser();
		generacionAnioBuscar.setBounds(216, 146, 164, 19);
		panel.add(generacionAnioBuscar);
		
		JLabel lblGeneracion_1 = new JLabel("Generacion:");
		lblGeneracion_1.setBounds(216, 133, 133, 13);
		panel.add(lblGeneracion_1);
		
		JButton btnBuscar = new JButton("Filtrar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarPor(comboBuscarTipoUsuario.getSelectedItem().toString(), 
						textBuscarID.getText() , 
						cmbDepto.getSelectedItem().toString(), 
						textBuscarDoc.getText(),
						textBuscarNombre.getText(),
						textBuscarApellido.getText(),
						textBuscarMail.getText(),
						textBuscarUsuario.getText(), 
						comboBuscarITR.getSelectedItem().toString(),
						String.valueOf(generacionAnioBuscar.getYear()),
						chckbxValidado.isSelected(),
						chckbxActivo.isSelected(),
						chckbxTodos.isSelected(),
						textLocalidad.getText(),
						textBuscarTelefono.getText(),
						chckbxNoValidado.isSelected(),
						chckbxNoActivo.isSelected()
						);
				
				
			}
		});
		btnBuscar.setBounds(1049, 138, 85, 21);
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
		lblNewLabel.setBounds(286, 26, 45, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Filtros de Busqueda");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(20, 7, 164, 13);
		panel.add(lblNewLabel_1);
		
		chckbxValidado = new JCheckBox("Validado");
		chckbxValidado.setSelected(true);
		chckbxValidado.setBounds(777, 116, 93, 21);
		panel.add(chckbxValidado);
		
		chckbxActivo = new JCheckBox("Activo");
		chckbxActivo.setSelected(true);
		chckbxActivo.setBounds(872, 116, 74, 21);
		panel.add(chckbxActivo);
		
		JLabel lblLocalidad = new JLabel("Localidad:");
		lblLocalidad.setBounds(777, 7, 99, 13);
		panel.add(lblLocalidad);
		
		textLocalidad = new JTextField();
		textLocalidad.setColumns(10);
		textLocalidad.setBounds(777, 23, 357, 19);
		panel.add(textLocalidad);
		
		JLabel lblDepartamento = new JLabel("Departamento:");
		lblDepartamento.setBounds(777, 49, 159, 13);
		panel.add(lblDepartamento);
		
		cmbDepto = new JComboBox(Departamentos.values());
		cmbDepto.setBounds(774, 61, 360, 21);
		panel.add(cmbDepto);
		
		chckbxNoValidado = new JCheckBox("No Validado");
		chckbxNoValidado.setSelected(false);
		chckbxNoValidado.setBounds(777, 142, 93, 21);
		panel.add(chckbxNoValidado);
		
		chckbxNoActivo = new JCheckBox("No Activo");
		chckbxNoActivo.setSelected(false);
		chckbxNoActivo.setBounds(872, 142, 88, 21);
		panel.add(chckbxNoActivo);
		
		
		chckbxTodos = new JCheckBox("Todos");
		chckbxTodos.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		        	chckbxActivo.setVisible(false);
		        	chckbxValidado.setVisible(false);
		        	chckbxNoActivo.setVisible(false);
		        	chckbxNoValidado.setVisible(false);
		        } else {
		        	chckbxActivo.setVisible(true);
		        	chckbxValidado.setVisible(true);
		        	chckbxNoActivo.setVisible(true);
		        	chckbxNoValidado.setVisible(true);
		        }
		    }
		});
		chckbxTodos.setBounds(962, 142, 65, 21);
		panel.add(chckbxTodos);
		
		JButton btnLimpiarFiltros = new JButton("Limpiar");
		btnLimpiarFiltros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFiltros();
			}
		});
		btnLimpiarFiltros.setBounds(1049, 116, 85, 21);
		panel.add(btnLimpiarFiltros);
		
		JLabel lblEmailPersonal = new JLabel("Email personal:");
		lblEmailPersonal.setBounds(396, 633, 133, 13);
		panel.add(lblEmailPersonal);
		
		txtEmailPersonal = new JTextField();
		txtEmailPersonal.setColumns(10);
		txtEmailPersonal.setBounds(396, 646, 360, 19);
		panel.add(txtEmailPersonal);
		
		chckbxActivarUsuario = new JCheckBox("Activo");
		chckbxActivarUsuario.setBounds(777, 519, 93, 21);
		panel.add(chckbxActivarUsuario);
		
		JLabel lblLocalidad_1 = new JLabel("Localidad:");
		lblLocalidad_1.setBounds(399, 678, 99, 13);
		panel.add(lblLocalidad_1);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setColumns(10);
		txtLocalidad.setBounds(399, 694, 357, 19);
		panel.add(txtLocalidad);
		
		JLabel lblDepartamento_1 = new JLabel("Departamento:");
		lblDepartamento_1.setBounds(399, 720, 159, 13);
		panel.add(lblDepartamento_1);
		
		cmbDepartamento = new JComboBox(Departamentos.values());
		cmbDepartamento.setBounds(396, 732, 360, 21);
		cmbDepartamento.setSelectedIndex(0);
		panel.add(cmbDepartamento);
		
		JLabel lblNewLabel_2 = new JLabel("ID:");
		lblNewLabel_2.setBounds(286, 484, 45, 13);
		panel.add(lblNewLabel_2);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setText("");
		txtId.setColumns(10);
		txtId.setBounds(306, 481, 74, 19);
		panel.add(txtId);
		
		chckbxValidarUsuario = new JCheckBox("Validado");
		chckbxValidarUsuario.setBounds(777, 552, 93, 21);
		panel.add(chckbxValidarUsuario);
		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(20, 633, 202, 13);
		panel.add(lblGenero);
		
		cmbBoxGenero = new JComboBox(TipoGenero.values());
		cmbBoxGenero.setSelectedIndex(0);
		cmbBoxGenero.setBounds(20, 646, 202, 21);
		panel.add(cmbBoxGenero);
		
		cmbArea = new JComboBox(TipoTutorArea.values());
		cmbArea.setBounds(777, 603, 225, 21);
		panel.add(cmbArea);
		
		lblArea = new JLabel("Area:");
		lblArea.setBounds(777, 591, 45, 13);
		panel.add(lblArea);
		
		lbltipoTutor = new JLabel("Tipo");
		lbltipoTutor.setBounds(777, 634, 45, 13);
		panel.add(lbltipoTutor);
		
		cmbTipoTutor = new JComboBox(TipoTutorEncargado.values());
		cmbTipoTutor.setSelectedIndex(0);
		cmbTipoTutor.setBounds(777, 646, 225, 21);
		panel.add(cmbTipoTutor);
		
		
		frame.pack();
		frame.setVisible(true);

	}
	
	
	
	public void limpiar() {
		usuarioEditable=null;
		txtId.setText("");
        txtDocumento.setText("");
        txtApellido.setText("");
        txtNombre.setText("");
        txtEmail.setText("");
        txtEmailPersonal.setText("");
        txtTelefono.setText("");
        txtLocalidad.setText("");
        txtNombreUsuario.setText("");
        comboBoxITR.setSelectedIndex(0);
        cmbBoxGenero.setSelectedIndex(0);
        cmbDepartamento.setSelectedIndex(0);
        fechaNacimientoCalendar.cleanup();
        chckbxActivarUsuario.setSelected(false);
        chckbxValidarUsuario.setSelected(false);
        
		txtNombre.setText("");
		txtNombreUsuario.setText("");
		txtApellido.setText("");
		txtTelefono.setText("");
		fechaNacimientoCalendar.cleanup();
		txtDocumento.setText("");
		txtEmail.setText("");
		yearBuscarChooser.setYear(2022);
	}

	public void validarDatos() {
		System.out.println("Validando datos USUARIO");


		if (txtNombre.getText().equals("") || txtNombreUsuario.getText().equals("") || txtApellido.getText().equals("")
				|| txtTelefono.getText().equals("") || txtEmail.getText().equals("")
				|| txtDocumento.getText().equals("") ||fechaNacimientoCalendar.getDate()==null) {


			System.out.println("FALTAN DATOS");
		} else {

			System.out.println("AGREGANDO USUARIO ");
			modificarUsuario();
		}
	}


	public void modificarUsuario() {
		//el id y documento siguen igual siempre
		usuarioEditable.setApellidos(txtApellido.getText());
		usuarioEditable.setNombres(txtNombre.getText());
		usuarioEditable.setMail(txtEmail.getText());
		usuarioEditable.setMailPersonal(txtEmailPersonal.getText());
		usuarioEditable.setTelefono(txtTelefono.getText());
		usuarioEditable.setLocalidad(txtLocalidad.getText());
		usuarioEditable.setUsuario(txtNombreUsuario.getText());
		usuarioEditable.setItr((Itr)comboBoxITR.getSelectedItem());
		usuarioEditable.setGenero(TipoGenero.getCharGenero(cmbBoxGenero.getSelectedItem().toString()));
		usuarioEditable.setDepartamento(cmbDepartamento.getSelectedItem().toString());
		usuarioEditable.setFechaNacimiento(fechaNacimientoCalendar.getDate());
		usuarioEditable.setActivo(chckbxActivarUsuario.isSelected());
		usuarioEditable.setValidado( chckbxValidarUsuario.isSelected());
        
        if(usuarioEditable instanceof Estudiante) {	
        	((Estudiante)usuarioEditable).setGeneracion(yearBuscarChooser.getYear());
        }
        if(usuarioEditable instanceof Tutor) {
        	((Tutor)usuarioEditable).setTipo(TipoTutorEncargado.getIdTipo(cmbTipoTutor.getSelectedItem().toString()));
        	((Tutor)usuarioEditable).setTipo(TipoTutorArea.getIdArea(cmbArea.getSelectedItem().toString()));
        }
        
        usuarioEditable=usuarioRemote.modificarUsuario(usuarioEditable);
        JOptionPane.showMessageDialog(null, "Se modifico el usuario " + usuarioEditable.getNombres() +" "+ usuarioEditable.getApellidos(),
				"Modificar usuario", JOptionPane.INFORMATION_MESSAGE);
	}


	public boolean borrarRow(String mensaje) {

		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(null, "Seguro quieres dar de baja al usuario: " + mensaje, "Warning",
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
		 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		 	// Se rellena cada posición del array con una de las columnas de la tabla en
			// base de datos. 
			for (Usuario usuTemp : listPasada) {
				if (usuTemp instanceof Estudiante) {
					fila[0] = usuTemp.getId();
					fila[1] = usuTemp.getDecriminatorValue();
					fila[2] = ((Estudiante) usuTemp).getGeneracion();
					fila[3] = usuTemp.getDocumento();
					fila[4] = usuTemp.getNombres();
					fila[5] = usuTemp.getApellidos();
					fila[6] = formatter.format(usuTemp.getFechaNacimiento());
					fila[7] = usuTemp.getMail();
					fila[8] = usuTemp.getUsuario();
					fila[9] = usuTemp.getItr().getNombre();
					fila[10] = usuTemp.getDepartamento();
					fila[11] = usuTemp.getLocalidad();
					if (usuTemp.getValidado()) {
						fila[12] = "Si";
					} else {
						fila[12] = "No";
					}
					if (usuTemp.getActivo()) {
						fila[13] = "Si";
					} else {
						fila[13] = "No";
					}
					// Se añade al modelo la fila completa.
					modelo.addRow(fila);
				}
				if (usuTemp instanceof Tutor) {
					fila[0] = usuTemp.getId();
					fila[1] = usuTemp.getDecriminatorValue();
					fila[2] = "";
					fila[3] = usuTemp.getDocumento();
					fila[4] = usuTemp.getNombres();
					fila[5] = usuTemp.getApellidos();
					fila[6] = formatter.format(usuTemp.getFechaNacimiento());
					fila[7] = usuTemp.getMail();
					fila[8] = usuTemp.getUsuario();
					fila[9] = usuTemp.getItr().getNombre();
					fila[10] = usuTemp.getDepartamento();
					fila[11] = usuTemp.getLocalidad();
					if (usuTemp.getValidado()) {
						fila[12] = "Si";
					} else {
						fila[12] = "No";
					}
					if (usuTemp.getActivo()) {
						fila[13] = "Si";
					} else {
						fila[12] = "No";
					}
					// Se añade al modelo la fila completa.
					modelo.addRow(fila);
				}
				if (usuTemp instanceof Analista) {
					fila[0] = usuTemp.getId();
					fila[1] = usuTemp.getDecriminatorValue();
					fila[2] = "";
					fila[3] = usuTemp.getDocumento();
					fila[4] = usuTemp.getNombres();
					fila[5] = usuTemp.getApellidos();
					fila[6] = formatter.format(usuTemp.getFechaNacimiento());
					fila[7] = usuTemp.getMail();
					fila[8] = usuTemp.getUsuario();
					fila[9] = usuTemp.getItr().getNombre();
					fila[10] = usuTemp.getDepartamento();
					fila[11] = usuTemp.getLocalidad();
					if (usuTemp.getValidado()) {
						fila[12] = "Si";
					} else {
						fila[12] = "No";
					}
					if (usuTemp.getActivo()) {
						fila[13] = "Si";
					} else {
						fila[13] = "No";
					}
					// Se añade al modelo la fila completa.
					modelo.addRow(fila);
				}
			}
			autoAjustarTabla(table);
	 }
	public void buscarPor(String tipo, String id ,String depto,String doc,String nombre,String apellido
			,String mail,String usuario,String itrNombre,String generacion, Boolean validado ,Boolean activo,Boolean todos, String localidad, String telefono,Boolean noValidados ,Boolean noActivos) {
		limpiarTabla();
		allUsuarios = usuarioRemote.buscarUsuarioPor(tipo, id, depto, doc, nombre, apellido, mail, usuario, itrNombre, generacion,validado,activo,todos,localidad,telefono,noValidados,noActivos);
		if(allUsuarios != null) {
		System.out.println(allUsuarios.toString());
		// Se rellena cada posición del array con una de las columnas de la tabla en
		// base de datos.
		cargarTabla(allUsuarios);
	}
	}
	
	
	public void limpiarFiltros() {
		comboBuscarTipoUsuario.setSelectedIndex(0);
		textBuscarID.setText("");
		cmbDepto.setSelectedIndex(0);
		textBuscarTelefono.setText("");
		textBuscarDoc.setText("");
		textBuscarNombre.setText("");
		textBuscarApellido.setText("");
		textBuscarMail.setText("");
		textBuscarUsuario.setText("");
		comboBuscarITR.setSelectedIndex(0);
		generacionAnioBuscar.setYear(2023);
		chckbxValidado.setSelected(false);
		chckbxActivo.setSelected(false);
		chckbxNoValidado.setSelected(false);
		chckbxNoActivo.setSelected(false);
		chckbxTodos.setSelected(true);
		textLocalidad.setText("");
		
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
	 
	 
	 public void autoAjustarTabla(JTable table) {
		    final TableColumnModel columnModel = table.getColumnModel();
		    for (int column = 0; column < table.getColumnCount(); column++) {
		        int width = 15; // Min width
		        for (int row = 0; row < table.getRowCount(); row++) {
		            TableCellRenderer renderer = table.getCellRenderer(row, column);
		            Component comp = table.prepareRenderer(renderer, row, column);
		            width = Math.max(comp.getPreferredSize().width +1 , width);
		        }
		        if(width > 300)
		            width=300;
		        columnModel.getColumn(column).setPreferredWidth(width);
		    }
		}
}