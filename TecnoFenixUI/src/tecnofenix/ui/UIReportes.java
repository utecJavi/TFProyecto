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
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

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
//		table.setDefaultEditor(Object.class, null);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setForeground(Color.GREEN);
		table.setColumnSelectionAllowed(false);
		table.setBackground(Color.BLACK);
		// crea un array que contiene los nombre de las columnas
		final String[] columnNames = { "Id", "Evento","Nombre", "Asistencia","Calificacion" };

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
		lblNombre.setBounds(207, 10, 74, 13);
		panel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(207, 49, 113, 13);
		panel.add(lblApellido);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(207, 62, 188, 19);
		textApellido.setText("");
		panel.add(textApellido);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(207, 23, 188, 19);
		textApellido.setText("");
		panel.add(textNombre);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Id");
		lblNewLabel_1_1_1.setBounds(10, 10, 74, 13);
		panel.add(lblNewLabel_1_1_1);
		
		textBuscarId = new JTextField();
		textBuscarId.setColumns(10);
		textBuscarId.setBounds(10, 23, 39, 19);
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
					chckbxAsistio.isSelected());
			}
		});
		btnBuscar.setBounds(677, 60, 113, 21);
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
		lblDocumento.setBounds(10, 48, 74, 13);
		panel.add(lblDocumento);
		
		textDocumento = new JTextField();
		textDocumento.setColumns(10);
		textDocumento.setBounds(10, 61, 188, 19);
		panel.add(textDocumento);
		
		chckbxAsistio = new JCheckBox("Asistio");
		chckbxAsistio.setSelected(true);
		chckbxAsistio.setBounds(416, 61, 74, 21);
		panel.add(chckbxAsistio);

		
		ButtonGroup butonGroup = new ButtonGroup();

		rdbtnmMayorQue = new JRadioButton("Mayor igual");
		rdbtnmMayorQue.setBounds(493, 21, 86, 21);
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
		rdbtnMenorQue.setBounds(581, 22, 91, 21);
		rdbtnMenorQue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnMenorQue.isSelected()) {
					consultaNota=" <= ";
				}
			}
		});
		panel.add(rdbtnMenorQue);

		rdbtnIgualQue = new JRadioButton("Igual");
		rdbtnIgualQue.setBounds(672, 22, 86, 21);
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
		textCalificacion.setText("0");
		textCalificacion.setColumns(10);
		textCalificacion.setBounds(416, 23, 74, 19);
		panel.add(textCalificacion);
		
		JLabel lblCalificacion = new JLabel("Calificacion");
		lblCalificacion.setBounds(416, 10, 96, 13);
		panel.add(lblCalificacion);
		
		textTituloEvento = new JTextField();
		textTituloEvento.setText("");
		textTituloEvento.setColumns(10);
		textTituloEvento.setBounds(59, 23, 138, 19);
		panel.add(textTituloEvento);
		
		JLabel lblTItuloEvento = new JLabel("Titulo Evento");
		lblTItuloEvento.setBounds(59, 10, 138, 13);
		panel.add(lblTItuloEvento);
		
		JButton btnLimpiaar = new JButton("Limpiar");
		btnLimpiaar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnLimpiaar.setBounds(551, 61, 113, 21);
		panel.add(btnLimpiaar);
		
		
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
			fila[1] = cAEE.getEventoId().getTitulo();
			fila[2] = cAEE.getEstudianteId().getNombres()+" "+cAEE.getEstudianteId().getApellidos();
			if (cAEE.getAsistencia()) {
				fila[3] = "Si";
			} else {
				fila[3] = "No";
			}
			fila[4] = cAEE.getCalificacion();
			
			// Se añade al modelo la fila completa.
			modelo.addRow(fila);

		}
		}
	}
	
	public void buscarPor(String id, String tituloEvento,String nombre,String apellido,String documento,String valorLogico,String calificacion,Boolean asistencia) {
		limpiarTabla();
		allAsisEstuAEvento = usuarioRemote.filtrarAsistEstuAEventosPor( id,  tituloEvento, nombre, apellido ,documento,valorLogico,calificacion, asistencia);
		if(allAsisEstuAEvento != null) {
		System.out.println(allAsisEstuAEvento.toString());
		// Se rellena cada posición del array con una de las columnas de la tabla en
		// base de datos.
		for (ConvocatoriaAsistenciaEventoEstudiante cAEE : allAsisEstuAEvento) {

			fila[0] = cAEE.getId();
			fila[1] = cAEE.getEventoId().getTitulo();
			fila[2] = cAEE.getEstudianteId().getNombres()+" "+cAEE.getEstudianteId().getApellidos();
			if (cAEE.getAsistencia()) {
				fila[3] = "Si";
			} else {
				fila[3] = "No";
			}
			fila[4] = cAEE.getCalificacion();
			
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
		textCalificacion.setText("0");
		chckbxAsistio.setSelected(true);
		rdbtnmMayorQue.setSelected(true);
		consultaNota=" >= ";
		lblDatosEvento.setText("...");
		lblDatosEstudiante.setText("...");
		actualizarLista();
		
	}
	
	public void mostrarDatos() {

		String datosEvento=new String();
		 datosEvento="Id: "+ convEESeleccionada.getEventoId().getId()+"\n"
		 + "Titulo: "+ convEESeleccionada.getEventoId().getTitulo()+"\n"
		 + "Tipo de evento: "+ convEESeleccionada.getEventoId().getTipo().getTipo()+"\n"
		 + "Modalidad del evento: "+ convEESeleccionada.getEventoId().getModalidad().getModalidad()+"\n"
		 + "Localizacion: "+ convEESeleccionada.getEventoId().getLocalizacion()+"\n"
		 + "Inicio: "+ convEESeleccionada.getEventoId().getInicio()+"\n"
		 + "Fin: "+ convEESeleccionada.getEventoId().getId()+"\n";
		 lblDatosEvento.setText(datosEvento);;
		 
		 String datosEstudiante=new String();
		 datosEstudiante= "Id: "+ convEESeleccionada.getEstudianteId().getId()+"\n"
		 + "Nombre: "+ convEESeleccionada.getEstudianteId().getNombres()+ " "+ convEESeleccionada.getEstudianteId().getApellidos()+"\n"
		 + "Documento: "+ convEESeleccionada.getEstudianteId().getDocumento()+"\n"
		 + "Mail: "+ convEESeleccionada.getEstudianteId().getMail()+"\n"
		 + "Fecha nacimiento: "+ convEESeleccionada.getEstudianteId().getFechaNacimiento()+"\n"
		 + "ITR: "+ convEESeleccionada.getEstudianteId().getItr().getNombre()+"\n";
		 lblDatosEstudiante.setText(datosEstudiante);;
	}
}
