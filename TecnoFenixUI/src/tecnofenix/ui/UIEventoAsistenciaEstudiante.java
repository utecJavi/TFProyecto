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
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Evento;
import tecnofenix.entidades.Tutor;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class UIEventoAsistenciaEstudiante {

	public JFrame frame;
	private JTable table;
	private DefaultTableModel modelo;
	private Object[] fila;
	
	private JTable tableEstudiante;
	private DefaultTableModel modeloEstudiante;
	private Object[] filaEstudiante;
	
	private List<Evento> listEventos;
	private List<Estudiante> listEstudiantes;
	private EJBUsuarioRemoto usuarioRemote;
	private JTextField textBuscarCI;
	private JTextField textBuscarTitulo;
	private JTextField textBuscarId;
	private Evento eventoSeleccionado;
	private Estudiante estudianteSeleccionado;


	public JButton btnSeleccionarTutores;
	private JTextField textField;
	private JTextField textBuscarNombre;
	private JTextField textBuscarApellido;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {
		usuarioRemote = new EJBUsuarioRemoto();
		//Para el datatable
		listEventos = new ArrayList<Evento>();
		listEstudiantes= new ArrayList<Estudiante>();
		
		eventoSeleccionado= new Evento();
		estudianteSeleccionado= new Estudiante();
		
		frame = new JFrame("Listado de tutores");

		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(800, 800));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(null);

		modelo = new DefaultTableModel();
		modeloEstudiante = new DefaultTableModel();

		// se crea la Tabla con el modelo DefaultTableModel
		table = new JTable(modelo);
//		table.setDefaultEditor(Object.class, null);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setForeground(Color.GREEN);
		table.setColumnSelectionAllowed(false);
		table.setBackground(Color.BLACK);
		// crea un array que contiene los nombre de las columnas
		final String[] columnNames = {"Id", "Titulo", "Tipo de evento", "Modalidad del evento", "Localizacion", "Inicio", "Fin" };

		// insertamos las columnas
		for (int column = 0; column < columnNames.length; column++) {
			// agrega las columnas a la tabla
			modelo.addColumn(columnNames[column]);
		}

		// Se crea un array que será una de las filas de la tabla.
		fila = new Object[columnNames.length];
		
		listEventos = usuarioRemote.obtenerEventos();
		actualizarListaEvento();
		// se define el tamaño de la tabla
		table.setBounds(93, 215, 100, 100);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
	        public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
				
					
				
				}
	        }
	    });
		// Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPaneEvento = new JScrollPane(table);
		scrollPaneEvento.setBounds(10, 103, 386, 368);
		// definimos un layout
		// Agregamos el JScrollPane al contenedor
		panel.add(scrollPaneEvento);
		
		
// ESTUDIANTE TABLE
		// se crea la Tabla con el modelo DefaultTableModel
				tableEstudiante = new JTable(modeloEstudiante);
//				table.setDefaultEditor(Object.class, null);
				tableEstudiante.setCellSelectionEnabled(false);
				tableEstudiante.setRowSelectionAllowed(true);
				tableEstudiante.setForeground(Color.GREEN);
				tableEstudiante.setColumnSelectionAllowed(false);
				tableEstudiante.setBackground(Color.BLACK);
				// crea un array que contiene los nombre de las columnas
				final String[] columnNamesEstudiante = { "Id", "CI","Nombre", "Apellido"};

				// insertamos las columnas
				for (int column = 0; column < columnNamesEstudiante.length; column++) {
					// agrega las columnas a la tabla
					modeloEstudiante.addColumn(columnNamesEstudiante[column]);
				}

				// Se crea un array que será una de las filas de la tabla.
				filaEstudiante = new Object[columnNamesEstudiante.length];

				listEstudiantes = usuarioRemote.buscarEstudiantePor(null,null,null);
				actualizarListaEstudiante();
				// se define el tamaño de la tabla
				tableEstudiante.setBounds(93, 215, 100, 100);

				tableEstudiante.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
					@Override
			        public void valueChanged(ListSelectionEvent event) {
						if (!event.getValueIsAdjusting() && tableEstudiante.getSelectedRow() != -1) {
					
						}
			        }
			    });
				// Creamos un JscrollPane y le agregamos la JTable
				JScrollPane scrollPaneEstudiante = new JScrollPane(tableEstudiante);
				scrollPaneEstudiante.setBounds(406, 103, 371, 368);
				// definimos un layout
				// Agregamos el JScrollPane al contenedor
//				JScrollPane scrollPaneEstudiante = new JScrollPane((Component) null);
//				scrollPaneEstudiante.setBounds(406, 103, 371, 368);
//				panel.add(scrollPaneEstudiante);
				panel.add(scrollPaneEstudiante);
		///FINNNNNNNNNNNNNNN
		
		
		btnSeleccionarTutores = new JButton("Confirmar asistencia");
		btnSeleccionarTutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnSeleccionarTutores.setBounds(487, 661, 290, 21);
		panel.add(btnSeleccionarTutores);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre evento");
		lblNewLabel_1_1.setBounds(72, 48, 102, 13);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Documento");
		lblNewLabel_2_1.setBounds(406, 10, 113, 13);
		panel.add(lblNewLabel_2_1);
		
		textBuscarCI = new JTextField();
		textBuscarCI.setColumns(10);
		textBuscarCI.setBounds(406, 23, 188, 19);
		textBuscarCI.setText("");
		panel.add(textBuscarCI);
		
		textBuscarTitulo = new JTextField();
		textBuscarTitulo.setColumns(10);
		textBuscarTitulo.setBounds(72, 61, 188, 19);
		textBuscarCI.setText("");
		panel.add(textBuscarTitulo);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Id");
		lblNewLabel_1_1_1.setBounds(10, 48, 74, 13);
		panel.add(lblNewLabel_1_1_1);
		
		textBuscarId = new JTextField();
		textBuscarId.setColumns(10);
		textBuscarId.setBounds(10, 61, 52, 19);
		textBuscarId.setText("");
		panel.add(textBuscarId);
		
		JButton btnBuscarEstudiantes = new JButton("Buscar");
		btnBuscarEstudiantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				buscarEstudiantePor(textBuscarCI.getText() ,textBuscarNombre.getText(),textBuscarApellido.getText());
			}
		});
		btnBuscarEstudiantes.setBounds(727, 60, 63, 21);
		panel.add(btnBuscarEstudiantes);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 91, 790, 2);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Filtros de busqueda");
		lblNewLabel.setBounds(10, 10, 113, 13);
		panel.add(lblNewLabel);
		
		JButton btnEliminarSeleccionado = new JButton("Limpiar estudiante");
		btnEliminarSeleccionado.setBounds(487, 693, 290, 21);
		panel.add(btnEliminarSeleccionado);
		

		
		JLabel lblDatosEvento = new JLabel("Datos evento");
		lblDatosEvento.setBackground(Color.DARK_GRAY);
		lblDatosEvento.setVerticalAlignment(SwingConstants.TOP);
		lblDatosEvento.setBounds(20, 481, 757, 72);
		panel.add(lblDatosEvento);
		
		JLabel lblDatosEstudiante = new JLabel("Datos estudiante");
		lblDatosEstudiante.setVerticalAlignment(SwingConstants.TOP);
		lblDatosEstudiante.setBackground(Color.DARK_GRAY);
		lblDatosEstudiante.setBounds(20, 563, 757, 72);
		panel.add(lblDatosEstudiante);
		
		JComboBox comboBoxAsistencia = new JComboBox();
		comboBoxAsistencia.setBounds(20, 661, 203, 21);
		panel.add(comboBoxAsistencia);
		
		JLabel lblNewLabel_2 = new JLabel("Asistio evento?");
		lblNewLabel_2.setBounds(20, 645, 203, 13);
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(233, 662, 244, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Nota");
		lblNewLabel_3.setBounds(233, 645, 45, 13);
		panel.add(lblNewLabel_3);
		
		JButton btnBuscarEventos = new JButton("Buscar");
		btnBuscarEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarEventosPor(textBuscarId.getText(), textBuscarTitulo.getText());
			}
		});
		btnBuscarEventos.setBounds(283, 60, 113, 21);
		panel.add(btnBuscarEventos);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Nombre");
		lblNewLabel_2_1_1.setBounds(406, 48, 113, 13);
		panel.add(lblNewLabel_2_1_1);
		
		textBuscarNombre = new JTextField();
		textBuscarNombre.setText("");
		textBuscarNombre.setColumns(10);
		textBuscarNombre.setBounds(406, 61, 148, 19);
		panel.add(textBuscarNombre);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Apellido");
		lblNewLabel_2_1_1_1.setBounds(564, 48, 113, 13);
		panel.add(lblNewLabel_2_1_1_1);
		
		textBuscarApellido = new JTextField();
		textBuscarApellido.setText("");
		textBuscarApellido.setColumns(10);
		textBuscarApellido.setBounds(564, 61, 148, 19);
		panel.add(textBuscarApellido);

		btnEliminarSeleccionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
			}
		});
		

		
		frame.pack();
		frame.setVisible(true);

	}

//
//	public boolean borrarRow(String mensaje) {
////		msj.mostrarMensaje(Mensajes.BAJA);
//		
//		int dialogButton = JOptionPane.YES_NO_OPTION;
//		int dialogResult = JOptionPane.showConfirmDialog (null, "Seguro quieres borrar: "+mensaje,"Warning",dialogButton);
//		if(dialogResult == JOptionPane.YES_OPTION){
//			return true;
//		}
//		return false;
//	}
	public void limpiarTablaEventos() {
		if (listEventos != null || !listEventos.isEmpty() || listEventos.size() > 0) {
			listEventos.clear();
			
		}
		modelo.getDataVector().removeAllElements();
		modelo.fireTableDataChanged();
	}
	public void limpiarTablaEstudiantes() {
		if (listEstudiantes != null || !listEstudiantes.isEmpty() || listEstudiantes.size() > 0) {
			listEstudiantes.clear();
			
		}
		modeloEstudiante.getDataVector().removeAllElements();
		modeloEstudiante.fireTableDataChanged();
	}
	
	public void actualizarListaEvento() {
		limpiarTablaEventos();
		for (Evento tutor : listEventos) {
//			"Id", "Titulo", "Tipo de evento", "Modalidad del evento", "Localizacion", "Inicio", "Fin"
			fila[0] = tutor.getId();
			fila[1] = tutor.getTitulo();
			fila[2] = tutor.getTipo().getTipo();
			fila[3] = tutor.getModalidad().getModalidad();
			fila[4] = tutor.getLocalizacion();
			fila[5] = tutor.getInicio().toString();
			fila[6] = tutor.getFin().toString();
			// Se añade al modelo la fila completa.
			modelo.addRow(fila);

		}
	}
	
	public void actualizarListaEstudiante() {

		limpiarTablaEstudiantes();
		
		for (Estudiante tutor : listEstudiantes) {
//			"Id", "CI","Nombre", "Apellido"
			filaEstudiante[0] = tutor.getId();
			filaEstudiante[1] = tutor.getDocumento().toString();
			filaEstudiante[2] = tutor.getNombres();
			filaEstudiante[3] = tutor.getApellidos();
			// Se añade al modelo la fila completa.
			modeloEstudiante.addRow(filaEstudiante);

		}
	}

	public void buscarEventosPor(String id,String titulo) {
		limpiarTablaEventos();
		listEventos = usuarioRemote.buscarEventosPor(id,titulo);
		if(listEventos != null) {
			// Se rellena cada posición del array con una de las columnas de la tabla en
		// base de datos.
			for (Evento tutor : listEventos) {
//				"Id", "Titulo", "Tipo de evento", "Modalidad del evento", "Localizacion", "Inicio", "Fin"
				fila[0] = tutor.getId();
				fila[1] = tutor.getTitulo();
				fila[2] = tutor.getTipo().getTipo();
				fila[3] = tutor.getModalidad().getModalidad();
				fila[4] = tutor.getLocalizacion();
				fila[5] = tutor.getInicio().toString();
				fila[6] = tutor.getFin().toString();
				// Se añade al modelo la fila completa.
				modelo.addRow(fila);

			}
		}
	}
	
	public void buscarEstudiantePor(String ci, String nombre ,String apellido) {
		limpiarTablaEstudiantes();
		listEstudiantes = usuarioRemote.buscarEstudiantePor(ci,nombre,apellido);
		if(listEstudiantes != null) {
			// Se rellena cada posición del array con una de las columnas de la tabla en
		// base de datos.
		for (Estudiante tutor : listEstudiantes) {
//			"Id", "CI","Nombre", "Apellido"
			filaEstudiante[0] = tutor.getId();
			filaEstudiante[1] = tutor.getDocumento().toString();
			filaEstudiante[2] = tutor.getNombres();
			filaEstudiante[3] = tutor.getApellidos();
			// Se añade al modelo la fila completa.
			modeloEstudiante.addRow(filaEstudiante);

		}
		}
	}
	
	public void limpiar() {
		textBuscarCI.setText("");
		textBuscarId.setText("");
		textBuscarTitulo.setText("");

		
	}
	
}
