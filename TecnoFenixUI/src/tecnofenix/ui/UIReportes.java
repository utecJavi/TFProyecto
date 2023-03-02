package tecnofenix.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.ConvocatoriaAsistenciaEventoEstudiante;
import tecnofenix.entidades.Itr;

import java.awt.Component;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import tecnofenix.entidades.RegistroAsistencia;

public class UIReportes {

	public JFrame frame;
	private JTable table;
	private DefaultTableModel modelo;
	private Object[] fila;
	private List<ConvocatoriaAsistenciaEventoEstudiante> allAsisEstuAEvento;
	private EJBUsuarioRemoto usuarioRemote;
	private JTextField textApellido;
	private JTextField textNombre;
	private JTextField textBuscarId;
	private ConvocatoriaAsistenciaEventoEstudiante convEESeleccionada;
	private JTextArea lblDatosEvento;
	private JTextArea lblDatosEstudiante;
	private JTextField textDocumento;
	
	private JRadioButton rdbtnmMayorQue;
	private JRadioButton rdbtnMenorQue;
	private JRadioButton rdbtnIgualQue;
	private String consultaNota;
	private JTextField textCalificacion;
	private JTextField textTituloEvento;
	private JCheckBox chckbxAsistio;
	private JCheckBox chckbxNoAsistio;
	private JComboBox<RegistroAsistencia> comboBoxRegAsistencia;
	private JLabel lblRegAsist;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {
		usuarioRemote = new EJBUsuarioRemoto();
		allAsisEstuAEvento = new ArrayList<ConvocatoriaAsistenciaEventoEstudiante>();
		convEESeleccionada= new ConvocatoriaAsistenciaEventoEstudiante();
		consultaNota= new String(" >= ");
		frame = new JFrame("Reportes");
		
		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(800, 800));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		modelo = new DefaultTableModel();

		// se crea la Tabla con el modelo DefaultTableModel
		table = new JTable(modelo);
		table.setDefaultEditor(Object.class, null);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setForeground(Color.GREEN);
		table.setColumnSelectionAllowed(false);
		table.setBackground(Color.BLACK);
		// crea un array que contiene los nombre de las columnas
		final String[] columnNames = { "Id","Id Evento", "Titulo Evento","Nombre Estudiante", "Asistencia","Calificacion" };

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

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
	        public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
				System.out.println("ACTUALIZANDO CLICK TABLA");
				Integer select= Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
				convEESeleccionada=usuarioRemote.obtenerDatosConvPorId(select);
				
				if(convEESeleccionada!=null) {
					mostrarDatos();
				}
				}
	        }
	    });
		// Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 103, 780, 368);
		// definimos un layout
		// Agregamos el JScrollPane al contenedor
		panel.add(scrollPane);

		JLabel lblNewLabel_1 = new JLabel("Datos del evento: ");
		lblNewLabel_1.setBounds(20, 483, 136, 13);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Datos del Estudiante:");
		lblNewLabel_2.setBounds(406, 483, 198, 13);
		panel.add(lblNewLabel_2);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(243, 10, 74, 13);
		panel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(243, 49, 113, 13);
		panel.add(lblApellido);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(243, 62, 188, 19);
		textApellido.setText("");
		panel.add(textApellido);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(243, 23, 188, 19);
		textApellido.setText("");
		panel.add(textNombre);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Id Evento");
		lblNewLabel_1_1_1.setBounds(10, 10, 74, 13);
		panel.add(lblNewLabel_1_1_1);
		
		textBuscarId = new JTextField();
		textBuscarId.setColumns(10);
		textBuscarId.setBounds(10, 23, 74, 19);
		textBuscarId.setText("");
		panel.add(textBuscarId);
		
		JButton btnBuscar = new JButton("Filtrar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			buscarPor(textBuscarId.getText(),
					textTituloEvento.getText() ,
					textNombre.getText(),
					textApellido.getText(),
					textDocumento.getText(),
					consultaNota,
					textCalificacion.getText(),
					((RegistroAsistencia)comboBoxRegAsistencia.getSelectedItem()).name());
			}
		});
		btnBuscar.setBounds(704, 61, 86, 21);
		panel.add(btnBuscar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 91, 790, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 506, 780, 2);
		panel.add(separator_1);
		
		lblDatosEvento = new JTextArea("...");
		lblDatosEvento.setLineWrap(true);
		lblDatosEvento.setForeground(Color.BLACK);
		lblDatosEvento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDatosEvento.setEditable(false);
		lblDatosEvento.setBackground(SystemColor.menu);
		lblDatosEvento.setBounds(20, 518, 376, 175);
		panel.add(lblDatosEvento);
		
		lblDatosEstudiante = new JTextArea("...");
		lblDatosEstudiante.setLineWrap(true);
		lblDatosEstudiante.setForeground(Color.BLACK);
		lblDatosEstudiante.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDatosEstudiante.setEditable(false);
		lblDatosEstudiante.setBackground(SystemColor.menu);
		lblDatosEstudiante.setBounds(406, 518, 371, 175);
		panel.add(lblDatosEstudiante);
		
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(45, 49, 74, 13);
		panel.add(lblDocumento);
		
		textDocumento = new JTextField();
		textDocumento.setColumns(10);
		textDocumento.setBounds(45, 62, 188, 19);
		panel.add(textDocumento);
		
		chckbxAsistio = new JCheckBox("Asistio");
		chckbxAsistio.setSelected(false);
		chckbxAsistio.setBounds(213, 388, 74, 21);
		panel.add(chckbxAsistio);

		chckbxNoAsistio = new JCheckBox("No Asistio");
		chckbxNoAsistio.setSelected(false);
		chckbxNoAsistio.setBounds(110, 388, 101, 21);
		panel.add(chckbxNoAsistio);
		
		ButtonGroup butonGroup = new ButtonGroup();

		rdbtnmMayorQue = new JRadioButton("Mayor igual");
		rdbtnmMayorQue.setBounds(525, 20, 86, 21);
		rdbtnmMayorQue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnmMayorQue.isSelected()) {
					consultaNota=" >= ";
				}
			}
		});
		rdbtnmMayorQue.setSelected(true);
		panel.add(rdbtnmMayorQue);

		rdbtnMenorQue = new JRadioButton("Menor igual");
		rdbtnMenorQue.setBounds(613, 21, 91, 21);
		rdbtnMenorQue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnMenorQue.isSelected()) {
					consultaNota=" <= ";
				}
			}
		});
		panel.add(rdbtnMenorQue);

		rdbtnIgualQue = new JRadioButton("Igual");
		rdbtnIgualQue.setBounds(704, 21, 86, 21);
		rdbtnIgualQue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnIgualQue.isSelected()) {
					consultaNota=" = ";
				}
			}
		});
		panel.add(rdbtnIgualQue);

		butonGroup.add(rdbtnmMayorQue);
		butonGroup.add(rdbtnMenorQue);
		butonGroup.add(rdbtnIgualQue);
		
		textCalificacion = new JTextField();
		textCalificacion.setColumns(10);
		textCalificacion.setBounds(441, 23, 74, 19);
		panel.add(textCalificacion);
		
		JLabel lblCalificacion = new JLabel("Calificacion");
		lblCalificacion.setBounds(441, 10, 96, 13);
		panel.add(lblCalificacion);
		
		textTituloEvento = new JTextField();
		textTituloEvento.setText("");
		textTituloEvento.setColumns(10);
		textTituloEvento.setBounds(95, 23, 138, 19);
		panel.add(textTituloEvento);
		
		JLabel lblTItuloEvento = new JLabel("Titulo Evento");
		lblTItuloEvento.setBounds(95, 10, 138, 13);
		panel.add(lblTItuloEvento);
		
		JButton btnLimpiaar = new JButton("Limpiar");
		btnLimpiaar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiaar.setBounds(613, 61, 87, 21);
		panel.add(btnLimpiaar);
		
		comboBoxRegAsistencia = new JComboBox<RegistroAsistencia>(RegistroAsistencia.values());
		comboBoxRegAsistencia.setBounds(440, 61, 157, 21);
		panel.add(comboBoxRegAsistencia);
		
		lblRegAsist = new JLabel("Asistencia");
		lblRegAsist.setBounds(441, 49, 113, 13);
		panel.add(lblRegAsist);
		
		
		frame.pack();
		frame.setVisible(true);

	}

	
	public void limpiarTabla() {
		if (allAsisEstuAEvento != null || !allAsisEstuAEvento.isEmpty() || allAsisEstuAEvento.size() > 0) {
			allAsisEstuAEvento.clear();
			
		}

		modelo.getDataVector().removeAllElements();
		modelo.fireTableDataChanged();

	}
	
	public void actualizarLista() {
		System.out.println("Entrando a cargar la lista de Eventos asistidos por estudiantes UI");
		limpiarTabla();
		System.out.println("usuarioRemote.listarAllConvocAsistenciaEventEstu()");
		allAsisEstuAEvento = usuarioRemote.listarAllConvocAsistenciaEventEstu();
		System.out.println(allAsisEstuAEvento.toString());
		// Se rellena cada posición del array con una de las columnas de la tabla en
		// base de datos.
		if(allAsisEstuAEvento!=null) {
		for (ConvocatoriaAsistenciaEventoEstudiante cAEE : allAsisEstuAEvento) {
			fila[0] = cAEE.getId();
			fila[1] = cAEE.getEventoId().getId();
			fila[2] = cAEE.getEventoId().getTitulo();
			fila[3] = cAEE.getEstudianteId().getNombres()+" "+cAEE.getEstudianteId().getApellidos();
			fila[4] = cAEE.getRegistroAsistencia().toString();
//			if(cAEE.getAsistencia()!=null) {
//				if (cAEE.getAsistencia()) {
//					fila[4] = "Si";
//				} else {
//					fila[4] = "No";
//				}
//			}else {
//				fila[4] = "---";
//			}
			if(cAEE.getCalificacion() != null) {
				fila[5] = cAEE.getCalificacion();
			}else {
				fila[5] ="---";
			}
			// Se añade al modelo la fila completa.
			modelo.addRow(fila);

		}
		}
	}
	
	public void buscarPor(String id, String tituloEvento,String nombre,String apellido,String documento,String valorLogico,String calificacion,String registroAsistencia) {
		limpiarTabla();
		allAsisEstuAEvento = usuarioRemote.filtrarAsistEstuAEventosPor( id,  tituloEvento, nombre, apellido ,documento,valorLogico,calificacion, registroAsistencia);
		if(allAsisEstuAEvento != null) {
		System.out.println(allAsisEstuAEvento.toString());
		// Se rellena cada posición del array con una de las columnas de la tabla en
		// base de datos.
		for (ConvocatoriaAsistenciaEventoEstudiante cAEE : allAsisEstuAEvento) {

			fila[0] = cAEE.getId();
			fila[1] = cAEE.getEventoId().getId();
			fila[2] = cAEE.getEventoId().getTitulo();
			fila[3] = cAEE.getEstudianteId().getNombres()+" "+cAEE.getEstudianteId().getApellidos();
			fila[4] = cAEE.getRegistroAsistencia().toString();
//			if(cAEE.getRegistroAsistencia()!=null) {
//				if (cAEE.getAsistencia()) {
//					fila[4] = "Si";
//				} else {
//					fila[4] = "No";
//				}
//			}else {
//				fila[4] = "---";
//			}
			if(cAEE.getCalificacion() != null) {
				fila[5] = cAEE.getCalificacion();
			}else {
				fila[5] ="---";
			}
			// Se añade al modelo la fila completa.
			modelo.addRow(fila);

		}
	}
	}
	
	public void limpiar() {
		textApellido.setText("");
		textBuscarId.setText("");
		textNombre.setText("");
		textTituloEvento.setText("");
		textCalificacion.setText("");
		chckbxAsistio.setSelected(true);
		rdbtnmMayorQue.setSelected(true);
		consultaNota=" >= ";
		lblDatosEvento.setText("...");
		lblDatosEstudiante.setText("...");
		actualizarLista();
		
	}
	
	public void mostrarDatos() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		String datosEvento=new String();
		 datosEvento="Id: "+ convEESeleccionada.getEventoId().getId()+"\n"
		 + "Titulo: "+ convEESeleccionada.getEventoId().getTitulo()+"\n"
		 + "Tipo de evento: "+ convEESeleccionada.getEventoId().getTipo().getTipo()+"\n"
		 + "Modalidad del evento: "+ convEESeleccionada.getEventoId().getModalidad().getModalidad()+"\n"
		 + "Localizacion: "+ convEESeleccionada.getEventoId().getLocalizacion()+"\n"
		 + "Inicio: "+ formatter.format(convEESeleccionada.getEventoId().getInicio()) +"\n"
		 + "Fin: "+ formatter.format(convEESeleccionada.getEventoId().getId())+"\n";
		 lblDatosEvento.setText(datosEvento);;
		 
		 String datosEstudiante=new String();
		 datosEstudiante= "Id: "+ convEESeleccionada.getEstudianteId().getId()+"\n"
		 + "Nombre: "+ convEESeleccionada.getEstudianteId().getNombres()+ " "+ convEESeleccionada.getEstudianteId().getApellidos()+"\n"
		 + "Documento: "+ convEESeleccionada.getEstudianteId().getDocumento()+"\n"
		 + "Mail: "+ convEESeleccionada.getEstudianteId().getMail()+"\n"
		 + "Fecha nacimiento: "+ formatter.format(convEESeleccionada.getEstudianteId().getFechaNacimiento())+"\n"
		 + "ITR: "+ convEESeleccionada.getEstudianteId().getItr().getNombre()+"\n";
		 lblDatosEstudiante.setText(datosEstudiante);;
	}
}
