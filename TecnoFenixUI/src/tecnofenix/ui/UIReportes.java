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

public class UIReportes {

	public JFrame frame;
	private JTable table;
	private DefaultTableModel modelo;
	private Object[] fila;
	private List<ConvocatoriaAsistenciaEventoEstudiante> allAsisEstuAEvento;
	private EJBUsuarioRemoto usuarioRemote;
	private JTextField textBuscarDept;
	private JTextField textBuscarNomb;
	private JTextField textBuscarId;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {
		usuarioRemote = new EJBUsuarioRemoto();
		allAsisEstuAEvento = new ArrayList<ConvocatoriaAsistenciaEventoEstudiante>();
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
//	            textEditarId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
//	            textEditarNom.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
//	            textEditarDepto.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
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
		lblNewLabel_2.setBounds(406, 483, 113, 13);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre");
		lblNewLabel_1_1.setBounds(208, 48, 74, 13);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Departamento");
		lblNewLabel_2_1.setBounds(406, 48, 113, 13);
		panel.add(lblNewLabel_2_1);
		
		textBuscarDept = new JTextField();
		textBuscarDept.setColumns(10);
		textBuscarDept.setBounds(406, 61, 188, 19);
		textBuscarDept.setText("");
		panel.add(textBuscarDept);
		
		textBuscarNomb = new JTextField();
		textBuscarNomb.setColumns(10);
		textBuscarNomb.setBounds(208, 61, 188, 19);
		textBuscarDept.setText("");
		panel.add(textBuscarNomb);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Id");
		lblNewLabel_1_1_1.setBounds(10, 48, 74, 13);
		panel.add(lblNewLabel_1_1_1);
		
		textBuscarId = new JTextField();
		textBuscarId.setColumns(10);
		textBuscarId.setBounds(10, 61, 188, 19);
		textBuscarId.setText("");
		panel.add(textBuscarId);
		
		JButton btnBuscar = new JButton("Filtrar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				buscarPor(textBuscarId.getText() ,textBuscarNomb.getText(),textBuscarDept.getText(),"",true);
			}
		});
		btnBuscar.setBounds(677, 60, 113, 21);
		panel.add(btnBuscar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 91, 790, 2);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Filtros de busqueda");
		lblNewLabel.setBounds(10, 10, 113, 13);
		panel.add(lblNewLabel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 506, 780, 2);
		panel.add(separator_1);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBackground(Color.DARK_GRAY);
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setBounds(20, 516, 311, 237);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("New label");
		lblNewLabel_3_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_3_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3_1.setBounds(406, 518, 311, 237);
		panel.add(lblNewLabel_3_1);

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
	
	public void buscarPor(String id, String tituloEvento,String nombre,String apellido ,Boolean asistencia) {
		limpiarTabla();
		allAsisEstuAEvento = usuarioRemote.filtrarAsistEstuAEventosPor( id,  tituloEvento, nombre, apellido , asistencia);
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
		textBuscarDept.setText("");
		textBuscarId.setText("");
		textBuscarNomb.setText("");
		
	}
}
