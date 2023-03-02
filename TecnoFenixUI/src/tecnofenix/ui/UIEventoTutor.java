package tecnofenix.ui;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.*;
import java.awt.Component;
import java.awt.Font;

public class UIEventoTutor {


	public JFrame frame;
	private JTable tablaEventos;
	private EJBUsuarioRemoto ejb;

	private JTable tableTutoEditable;
	private DefaultTableModel modeloTutoEditable;
	private Object[] filaTutoEditable;
	private Evento eventoEditable;
	
	private List<TutorResponsableEvento> listTutorResEvent = new ArrayList<TutorResponsableEvento>();
	public List<Tutor> listTutores= new ArrayList<Tutor>();
	private DefaultTableModel tableModel;
	private Date hoy;
	private SimpleDateFormat formatter;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar(Tutor user) {
		//Usuario user
		//pasar el usuario activo para saber si es tutor y filtrar por los datos del tutor

		ejb = new EJBUsuarioRemoto();
		frame = new JFrame("Litado eventos tutor");

		JPanel panel = new JPanel();
	    eventoEditable =new Evento();

		panel.setPreferredSize(new Dimension(1000, 800));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		
		panel.setLayout(null);
		hoy = new Date(System.currentTimeMillis());
		
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		tableModel = new DefaultTableModel(new String[] {"Id", "Titulo", "Tipo de evento", "Modalidad del evento", "Localizacion", "Inicio", "Fin","Borrado"}, 0) {
			 @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			 }
		};
	
		generateRows(ejb.listarEventosTutor(user));
	
	
		tablaEventos = new JTable();
		tablaEventos.setModel(tableModel);
		tablaEventos.setCellSelectionEnabled(false);
		tablaEventos.setRowSelectionAllowed(true);
		tablaEventos.setForeground(Color.GREEN);
		tablaEventos.setColumnSelectionAllowed(false);
		tablaEventos.setBackground(Color.BLACK);
		tablaEventos.setDefaultEditor(Object.class, null);
		tablaEventos.setBounds(93, 215, 100, 100);	

	
		tablaEventos.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
	        public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && tablaEventos.getSelectedRow() != -1) {
				System.out.println("ACTUALIZANDO CLICK");
				eventoEditable = ejb.obtenerEvento(Integer.valueOf(tablaEventos.getValueAt(tablaEventos.getSelectedRow(), 0).toString()));
				listTutorResEvent=ejb.obtenerTutoresDeEvento(eventoEditable.getId());
				if(listTutorResEvent!=null) {
					listTutores.clear();
					for(TutorResponsableEvento tre: listTutorResEvent) {
//						editarTutores.add(tre.getTutorId());
						listTutores.add(tre.getTutorId());
					}  
					actualizarLista(listTutores);
				}
	        }
			}
	    });
		// Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(tablaEventos);
		scrollPane.setBounds(10, 57, 964, 472);
		// definimos un layut
		// Agregamos el JScrollPane al contenedor
		panel.add(scrollPane);

		//TABLA DE TUTORES EDITABLE DE SELECCION
		

		modeloTutoEditable = new DefaultTableModel();

		// se crea la Tabla con el modelo DefaultTableModel
		tableTutoEditable = new JTable(modeloTutoEditable);
		tableTutoEditable.setDefaultEditor(Object.class, null);
		tableTutoEditable.setCellSelectionEnabled(false);
		tableTutoEditable.setRowSelectionAllowed(true);
		tableTutoEditable.setForeground(Color.GREEN);
		tableTutoEditable.setColumnSelectionAllowed(false);
		tableTutoEditable.setBackground(Color.BLACK);
		// crea un array que contiene los nombre de las columnas
		final String[] columnNames = { "Id", "CI","Nombre", "Apellido","Tipo","Area" };

		// insertamos las columnas
		for (int column = 0; column < columnNames.length; column++) {
			// agrega las columnas a la tabla
			modeloTutoEditable.addColumn(columnNames[column]);
		}

		// Se crea un array que será una de las filas de la tabla.
		filaTutoEditable = new Object[columnNames.length];

		// se define el tamaño de la tabla
		tableTutoEditable.setBounds(93, 215, 100, 100);
		tableTutoEditable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
	        public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && tableTutoEditable.getSelectedRow() != -1) {

					}
		        }
	
	    });
		
		JScrollPane scrollPaneTutSeleccionados = new JScrollPane(tableTutoEditable);
		scrollPaneTutSeleccionados.setBounds(10, 562, 953, 206);
		panel.add(scrollPaneTutSeleccionados);
		
		

		
		JLabel lblNewLabel_1 = new JLabel("Tutores del evento");
		lblNewLabel_1.setBounds(10, 539, 113, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Listado de eventos ");
		lblNewLabel.setBounds(10, 34, 113, 13);
		panel.add(lblNewLabel);
		
		
		
		frame.pack();
//		frame.setVisible(true);
		
	}
	
	private void generateRows(List<Evento> eventos) {
		tableModel.getDataVector().removeAllElements();
		tableModel.fireTableDataChanged();
		for (Evento evento : eventos) {
			Vector<String> row = new Vector<String>(6);
			row.add(evento.getId().toString());
			row.add(evento.getTitulo());
			row.add(evento.getTipo().getTipo());
			row.add(evento.getModalidad().getModalidad());
			row.add(evento.getLocalizacion());		
			row.add(formatter.format(evento.getInicio()));
			row.add(formatter.format(evento.getFin()));
			if(evento.getBajaLogica()) {
				row.add("Si");
			}else {
				row.add("No");
			}
			

			tableModel.addRow(row);
//			"Id", "Titulo", "Tipo de evento", "Modalidad del evento", "Localizacion", "Inicio", "Fin"
		}
	}
	
	
	public void limpiarTabla() {
//		if (listTutores != null || !listTutores.isEmpty() || listTutores.size() > 0) {
//			listTutores.clear();
//			
//		}
		modeloTutoEditable.getDataVector().removeAllElements();
		modeloTutoEditable.fireTableDataChanged();

	}
	
	public void actualizarLista(List<Tutor> listarTutSel) {
		System.out.println("Entrando a cargar la tabla de TUTORES");
		limpiarTabla();
		this.listTutores = listarTutSel;
		// Se rellena cada posición del array con una de las columnas de la tabla en
		// base de datos.
		for (Tutor tutor : listTutores) {
//			"Id", "CI","Nombre", "Apellido","Tipo","Area"
			filaTutoEditable[0] = tutor.getId();
			filaTutoEditable[1] = tutor.getDocumento().toString();
			filaTutoEditable[2] = tutor.getNombres();
			filaTutoEditable[3] = tutor.getApellidos();
			filaTutoEditable[4] = tutor.getTipo().getNombre();
			filaTutoEditable[5] = tutor.getArea().getNombre();
			// Se añade al modelo la fila completa.
			modeloTutoEditable.addRow(filaTutoEditable);

		}
	} 
	
	
	private DefaultComboBoxModel<Itr> generateItrEventoComboBoxData() {
		DefaultComboBoxModel<Itr> comboBoxModel = new DefaultComboBoxModel<>();
		Itr itrVacio =new Itr();
		itrVacio.setNombre("");
		comboBoxModel.addElement(itrVacio);
		ejb.listarITR().forEach(comboBoxModel::addElement);
		return comboBoxModel;
	}
	
	
	public void limpiarCampos() {
		listTutorResEvent.clear();
	}
	 
	public void obtenerListaDeTutores(List<Tutor> lista) {
		System.out.println("Presiono el boton aceptar tutores tamaño lista "+lista.size());

		for(Tutor tut :lista) {
			Boolean agregar=true;
			for(TutorResponsableEvento tutRE :listTutorResEvent) {
				if(tutRE.getTutorId().getId().equals(tut.getId())) {
					agregar = false;
					System.out.println("EL tutor"+ tut.getNombres() +" "+tut.getApellidos() 
					+"  ya se encuentra en la lista no se agregara");
				}
			}
			if(agregar) {
				TutorResponsableEvento tutResEvent = new TutorResponsableEvento();
				tutResEvent.setTutorId(tut);
				tutResEvent.setEventoId(eventoEditable);
				listTutorResEvent.add(tutResEvent);
			}
			}
		actualizarLista(lista);
	}
	
	
	
}
