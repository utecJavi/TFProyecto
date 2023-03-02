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

public class UIEvento {


	public JFrame frame;
	private JTable tablaEventos;
	private EJBUsuarioRemoto ejb;

	private JTable tableTutoEditable;
	private DefaultTableModel modeloTutoEditable;
	private Object[] filaTutoEditable;
	
	
//	private JTextField textId;
	private JTextField textTitulo;
	private JComboBox<TipoEvento> comboBoxTipoEvento;
	private JComboBox<ModalidadEvento> comboBoxModalidadEvento;
	// este quizas sea un JList
//	private JComboBox<Set<Tutor>> comboBoxTutores;
//	// este quizas sea un JList
//	private JComboBox<Set<Analista>> comboBoxAnalistas;
	private JDateChooser dateinicio1;
	private JDateChooser datefin1;
	private JDateChooser dateinicio2;
	private JDateChooser datefin2;
	private JTextField textLocalizacion;
	private JComboBox<Itr> itrEventoComboBox;
	private Evento eventoEditable;
	private JTextField editarTextId;
	private JTextField editarTextTitulo;
	private JComboBox<TipoEvento> editarComboBoxTipoEvento;
	private JComboBox<ModalidadEvento> editarComboBoxModalidadEvento;
//	// este quizas sea un JList
//	private JComboBox<Set<Tutor>> editarComboBoxTutores;
//	// este quizas sea un JList
//	private JComboBox<Set<Analista>> editarComboBoxAnalistas;
	private JDateChooser editarDateFechaInicio;
	private JDateChooser editarDateFechaFin;
	private JTextField editarTextLocalizacion;
	private JComboBox<Itr> editarItrEventoComboBox;
	private JComboBox<TipoEstadoEvento> editarTipoEstadoEventoComboBox;
	
	private List<TutorResponsableEvento> listTutorResEvent = new ArrayList<TutorResponsableEvento>();
	public List<Tutor> listTutores= new ArrayList<Tutor>();
	private DefaultTableModel tableModel;
	private JTextField textIdEvento;
	private Date hoy;
	private SimpleDateFormat formatter;
	private JCheckBox chckbxEventoBorrado;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {
		//Usuario user
		//pasar el usuario activo para saber si es tutor y filtrar por los datos del tutor

		ejb = new EJBUsuarioRemoto();
		frame = new JFrame("Eventos");

		JPanel panel = new JPanel();
	    eventoEditable =new Evento();

		panel.setPreferredSize(new Dimension(1000, 800));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		
		panel.setLayout(null);
		hoy = new Date(System.currentTimeMillis());
		
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		tableModel = new DefaultTableModel(new String[] {"Id", "Titulo", "Tipo de evento", "Modalidad del evento", "Localizacion", "Inicio", "Fin","Estado","Borrado"}, 0) {
			 @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			 }
		};
		
		generateRows(ejb.listarEventos());
			
		tablaEventos = new JTable();
		tablaEventos.setModel(tableModel);
		tablaEventos.setCellSelectionEnabled(false);
		tablaEventos.setRowSelectionAllowed(true);
		tablaEventos.setForeground(Color.GREEN);
		tablaEventos.setColumnSelectionAllowed(false);
		tablaEventos.setBackground(Color.BLACK);
		tablaEventos.setDefaultEditor(Object.class, null);
		tablaEventos.setBounds(93, 215, 100, 100);	

//		panel.add(tablaUsuarios);
//
//		frame.pack();
//		table.setDefaultEditor(Object.class, null);
	
		tablaEventos.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
	        public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && tablaEventos.getSelectedRow() != -1) {
				System.out.println("ACTUALIZANDO CLICK");
				eventoEditable = ejb.obtenerEvento(Integer.valueOf(tablaEventos.getValueAt(tablaEventos.getSelectedRow(), 0).toString()));
				editarTextId.setText(eventoEditable.getId().toString());
				editarTextTitulo.setText(eventoEditable.getTitulo());
				editarItrEventoComboBox.setSelectedItem(eventoEditable.getItr());
				System.out.println("TIPO ESTADO EVENTO"+eventoEditable.getTipoEstadoEvento());
				if(eventoEditable.getTipoEstadoEvento()!=null) {
				editarTipoEstadoEventoComboBox.setSelectedItem(eventoEditable.getTipoEstadoEvento());
				}else {
					editarTipoEstadoEventoComboBox.setSelectedIndex(0);
				}
				editarComboBoxTipoEvento.setSelectedItem(TipoEvento.fromString(eventoEditable.getTipo().getTipo()));
				editarComboBoxModalidadEvento.setSelectedItem(ModalidadEvento.fromString(eventoEditable.getModalidad().getModalidad()));
				editarTextLocalizacion.setText(eventoEditable.getLocalizacion());
				editarDateFechaInicio.setDate(eventoEditable.getInicio());
				editarDateFechaFin.setDate(eventoEditable.getFin());
				chckbxEventoBorrado.setSelected(eventoEditable.getBajaLogica());
//				editarTutores.clear();
//				for(TutorResponsableEvento tr: eventoEditable.getTutorResponsableEventoCollection()) {
//					editarTutores.add(tr.getTutorId());
//				}
//				listTutorResEvent=eventoEditable.getTutorResponsableEventoCollection();
					listTutorResEvent=ejb.obtenerTutoresDeEvento(eventoEditable.getId());
					if(listTutorResEvent!=null) {
						listTutores.clear();
						for(TutorResponsableEvento tre: listTutorResEvent) {
//							editarTutores.add(tre.getTutorId());
							listTutores.add(tre.getTutorId());
						} 
						actualizarLista(listTutores);
					}
				}
	        }
	    });
		// Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(tablaEventos);
		scrollPane.setBounds(10, 154, 964, 387);
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
		scrollPaneTutSeleccionados.setBounds(628, 582, 335, 186);
		panel.add(scrollPaneTutSeleccionados);
		
		
		
		
		
		
		JButton btnSeleccionarTutores = new JButton("Editar Tutores");
		btnSeleccionarTutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Editar tutores click");//listTutores!= null && !listTutores.isEmpty() &&
				if( eventoEditable!= null && eventoEditable.getId()!=null) {
				System.out.println("Lista de tutores "+listTutores.toString());
				UIListaTutores uiListaTutores = new UIListaTutores();
				uiListaTutores.inicializar(UIEvento.this);
				uiListaTutores.frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Primero debe seleccionar un evento",
							"Ya existe", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnSeleccionarTutores.setBounds(487, 685, 131, 21);
		panel.add(btnSeleccionarTutores);
		
		

		textTitulo = new JTextField();
		textTitulo.setText("");
		textTitulo.setBounds(116, 68, 202, 19);
		textTitulo.setColumns(10);
		panel.add(textTitulo);

		textLocalizacion = new JTextField();
		textLocalizacion.setText("");
		textLocalizacion.setBounds(333, 68, 188, 19);
		textLocalizacion.setColumns(10);
		panel.add(textLocalizacion);

		comboBoxTipoEvento = new JComboBox<>();
		comboBoxTipoEvento.setModel(new DefaultComboBoxModel<TipoEvento>(TipoEvento.values()));
		comboBoxTipoEvento.setBounds(10, 112, 202, 19);
		panel.add(comboBoxTipoEvento);

		comboBoxModalidadEvento = new JComboBox<>();
		comboBoxModalidadEvento.setModel(new DefaultComboBoxModel<ModalidadEvento>(ModalidadEvento.values()));
		comboBoxModalidadEvento.setBounds(227, 112, 188, 19);
		panel.add(comboBoxModalidadEvento);

		dateinicio1 = new JDateChooser();
//		dateFechafechaInicioInicio.setCalendar(Calendar.getInstance());
		dateinicio1.setBounds(531, 68, 96, 19);
		panel.add(dateinicio1);

		datefin1 = new JDateChooser();
//		dateFechafechaFinInicio.setCalendar(Calendar.getInstance());
		datefin1.setBounds(637, 68, 96, 19);
		panel.add(datefin1);

	
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 142, 964, 2);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Filtros de busqueda");
		lblNewLabel.setBounds(10, 10, 113, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Id");
		lblNewLabel_1_1_1_1_1.setBounds(10, 569, 74, 13);
		panel.add(lblNewLabel_1_1_1_1_1);


		// campos de editar
		editarTextId = new JTextField();
		editarTextId.setText("");
		editarTextId.setEnabled(false);
		editarTextId.setColumns(10);
		editarTextId.setBounds(10, 582, 188, 19);
		panel.add(editarTextId);

		editarTextTitulo = new JTextField();
		editarTextTitulo.setColumns(10);
		editarTextTitulo.setBounds(10, 625, 188, 19);
		panel.add(editarTextTitulo);
		
		editarComboBoxTipoEvento = new JComboBox<>();
		editarComboBoxTipoEvento.setModel(new DefaultComboBoxModel<TipoEvento>(TipoEvento.values() ));
		editarComboBoxTipoEvento.setBounds(227, 582, 188, 19);
		panel.add(editarComboBoxTipoEvento);

		editarComboBoxModalidadEvento = new JComboBox<>();
		editarComboBoxModalidadEvento.setModel(new DefaultComboBoxModel<ModalidadEvento>(ModalidadEvento.values()));
		editarComboBoxModalidadEvento.setBounds(227, 625, 188, 19);
		panel.add(editarComboBoxModalidadEvento);

		editarTextLocalizacion = new JTextField();
		editarTextLocalizacion.setBounds(227, 669, 188, 19);
		editarTextLocalizacion.setColumns(10);
		panel.add(editarTextLocalizacion);

		editarDateFechaInicio = new JDateChooser();
		editarDateFechaInicio.setBounds(10, 669, 96, 19);
		panel.add(editarDateFechaInicio);

		editarDateFechaFin = new JDateChooser();
		editarDateFechaFin.setBounds(116, 669, 96, 19);
		panel.add(editarDateFechaFin);
		
		JButton btnEditar = new JButton("Editar evento");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(eventoEditable!=null && eventoEditable.getId()!=null) {
					if(validarDatos()) {
						eventoEditable.setTitulo(editarTextTitulo.getText());
						eventoEditable.setItr((Itr)editarItrEventoComboBox.getSelectedItem());
						eventoEditable.setTipoEstadoEvento((TipoEstadoEvento)editarTipoEstadoEventoComboBox.getSelectedItem()); 
						eventoEditable.setTipo((TipoEvento)editarComboBoxTipoEvento.getSelectedItem());
						eventoEditable.setModalidad((ModalidadEvento)editarComboBoxModalidadEvento.getSelectedItem());
						eventoEditable.setLocalizacion(editarTextLocalizacion.getText());
						eventoEditable.setInicio(editarDateFechaInicio.getDate());
						eventoEditable.setFin(editarDateFechaFin.getDate());
						eventoEditable.setTutorResponsableEventoCollection(listTutorResEvent);
		
						eventoEditable=ejb.modificarEvento(eventoEditable);
						
						generateRows(ejb.listarEventos());
						
						
					}
				}
			}
		});
		
		
		btnEditar.setBounds(487, 716, 131, 21);
		panel.add(btnEditar);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 551, 964, 2);
		panel.add(separator_2);
		
		JLabel tituloEventoLabel = new JLabel("Titulo");
		tituloEventoLabel.setBounds(116, 53, 87, 13);
		panel.add(tituloEventoLabel);
		
		JLabel fechaInicioEventoLabel = new JLabel("Inicio");
		fechaInicioEventoLabel.setBounds(531, 53, 113, 13);
		panel.add(fechaInicioEventoLabel);
		
		JLabel fechaFinEventoLabel = new JLabel("Fin");
		fechaFinEventoLabel.setBounds(638, 54, 95, 13);
		panel.add(fechaFinEventoLabel);
		
		JLabel tipoEventoLabel = new JLabel("Tipo de evento");
		tipoEventoLabel.setBounds(10, 97, 188, 13);
		panel.add(tipoEventoLabel);
		
		JLabel modalidadEventoLabel = new JLabel("Modalidad del evento");
		modalidadEventoLabel.setBounds(227, 97, 188, 13);
		panel.add(modalidadEventoLabel);
		
		JLabel localizacionEventoLabel = new JLabel("Localizacion del evento");
		localizacionEventoLabel.setBounds(333, 53, 188, 13);
		panel.add(localizacionEventoLabel);
		
		JLabel editarTituloLabel = new JLabel("Titulo");
		editarTituloLabel.setBounds(10, 611, 45, 13);
		panel.add(editarTituloLabel);
		
		JLabel editarFechaInicioLabel = new JLabel("Fecha de inicio");
		editarFechaInicioLabel.setBounds(10, 657, 96, 13);
		panel.add(editarFechaInicioLabel);
		
		JLabel editarFechaFinLabel = new JLabel("Fecha de finalizacion");
		editarFechaFinLabel.setBounds(115, 657, 97, 13);
		panel.add(editarFechaFinLabel);
		
		JLabel editarTipoEventoLabel = new JLabel("Tipo de evento");
		editarTipoEventoLabel.setBounds(227, 569, 188, 13);
		panel.add(editarTipoEventoLabel);
		
		JLabel editarModalidadEventoLabel = new JLabel("Modalidad del evento");
		editarModalidadEventoLabel.setBounds(227, 611, 188, 13);
		panel.add(editarModalidadEventoLabel);
		
		JLabel editarLocalizacionEventoLabel = new JLabel("Localizacion del evento");
		editarLocalizacionEventoLabel.setBounds(227, 657, 188, 13);
		panel.add(editarLocalizacionEventoLabel);
		
		itrEventoComboBox = new JComboBox<Itr>();
		itrEventoComboBox.setModel(generateItrEventoComboBoxData());
		itrEventoComboBox.setBounds(425, 111, 188, 21);
		panel.add(itrEventoComboBox);
		
		JLabel itrEventoComboBoxLabel = new JLabel("ITR");
		itrEventoComboBoxLabel.setBounds(425, 97, 45, 13);
		panel.add(itrEventoComboBoxLabel);
		
		editarItrEventoComboBox = new JComboBox<Itr>();
		editarItrEventoComboBox.setModel(generateItrEventoComboBoxData());
		editarItrEventoComboBox.setBounds(425, 580, 188, 21);
		panel.add(editarItrEventoComboBox);
		
		JLabel editarItrEventoComboBoxLabel = new JLabel("ITR");
		editarItrEventoComboBoxLabel.setBounds(425, 569, 45, 13);
		panel.add(editarItrEventoComboBoxLabel);
		
		editarTipoEstadoEventoComboBox = new JComboBox<TipoEstadoEvento>();
		editarTipoEstadoEventoComboBox.setModel(generateTipoEstadoEventoComboBoxData());
		editarTipoEstadoEventoComboBox.setBounds(425, 624, 188, 21);
		panel.add(editarTipoEstadoEventoComboBox);
		
		JLabel editarTipoEstadoEventoComboBoxLabel = new JLabel("Tipo estado evento");
		editarTipoEstadoEventoComboBoxLabel.setBounds(425, 611, 193, 13);
		panel.add(editarTipoEstadoEventoComboBoxLabel);

		
		JLabel lblNewLabel_1 = new JLabel("Tutores del evento");
		lblNewLabel_1.setBounds(633, 569, 113, 13);
		panel.add(lblNewLabel_1);
		
		JButton btnBorrarEvento = new JButton("Borrar evento");
		btnBorrarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(eventoEditable!=null) {
					if(borrarRow(eventoEditable.getTitulo())) {
						if(validarBajaLogica()) {
						eventoEditable.setBajaLogica(true);
						eventoEditable=ejb.modificarEvento(eventoEditable);			
						generateRows(ejb.listarEventos());
						}
					}
				}
			}
		});
		btnBorrarEvento.setBounds(487, 747, 131, 21);
		panel.add(btnBorrarEvento);
		
		JLabel lblFechaDeFin = new JLabel("Inicio");
		lblFechaDeFin.setBounds(755, 53, 96, 13);
		panel.add(lblFechaDeFin);

		dateinicio2 = new JDateChooser();
//		dateFechafechaInicioFin.setCalendar(Calendar.getInstance());
		dateinicio2.setBounds(755, 68, 96, 19);
		panel.add(dateinicio2);
		
		JLabel lblFinalizacionFin = new JLabel("Fin");
		lblFinalizacionFin.setBounds(862, 54, 95, 13);
		panel.add(lblFinalizacionFin);
		
		datefin2 = new JDateChooser();
//		dateFechafechaFinFIN.setCalendar(Calendar.getInstance());
		datefin2.setBounds(861, 68, 96, 19);
		panel.add(datefin2);
		
		textIdEvento = new JTextField();
		textIdEvento.setText("");
		textIdEvento.setColumns(10);
		textIdEvento.setBounds(10, 68, 87, 19);
		panel.add(textIdEvento);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(10, 53, 87, 13);
		panel.add(lblId);

		
		

		JButton filtrarEventosBtn = new JButton("Filtrar");
		filtrarEventosBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//todo logica de filtros
				List<Evento> listEventos=new ArrayList<Evento>();
				String modalidad="";
				String tipoEvento="";
				String itrNombre="";
				if(comboBoxModalidadEvento.getSelectedIndex()!=0) {
					modalidad=((ModalidadEvento)comboBoxModalidadEvento.getSelectedItem()).name();
				}
				if(comboBoxTipoEvento.getSelectedIndex()!=0) {
					tipoEvento=((TipoEvento)comboBoxTipoEvento.getSelectedItem()).name();
				}
				if(itrEventoComboBox.getSelectedIndex()!=0) {
					itrNombre=itrEventoComboBox.getSelectedItem().toString();
				}
				
				Date inicio1=null;
				Date fin1=null;
				Date inicio2=null;
				Date fin2=null;
				
//				if(dateFechaInicio.getDate()!=null) {
//					fechaInicioInicio=formatter.format(dateFechaInicio.getDate());
//				}
//				if(dateFechaInicioFIN.getDate()!=null) {
//					fechaFinInicio=formatter.format(dateFechaInicioFIN.getDate());
//				}
//				if(dateFechaFin.getDate()!=null) {
//					fechaInicioFin=formatter.format(dateFechaFin.getDate());
//				}
//				if(dateFechaFinFIN.getDate()!=null) {
//					fechaFinFIN=formatter.format(dateFechaFinFIN.getDate());
//				}
				if(dateinicio1.getDate()!=null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(dateinicio1.getDate());
					calendar.set(Calendar.HOUR_OF_DAY, 0); // Establecemos la hora a las 9 de la mañana
					calendar.set(Calendar.MINUTE, 0); // Establecemos los minutos a 30
					calendar.set(Calendar.SECOND, 0); // Establecemos los segundos a 0
					calendar.set(Calendar.MILLISECOND, 0); // Establecemos los milisegundos a 0
					inicio1=calendar.getTime();
					System.out.println("fechaInicioInicio "+inicio1.toString());
				}
				if(datefin1.getDate()!=null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(datefin1.getDate());
					calendar.set(Calendar.HOUR_OF_DAY, 23); // Establecemos la hora a las 9 de la mañana
					calendar.set(Calendar.MINUTE, 59); // Establecemos los minutos a 30
					calendar.set(Calendar.SECOND, 59); // Establecemos los segundos a 0
					calendar.set(Calendar.MILLISECOND, 0); // Establecemos los milisegundos a 0
					fin1=calendar.getTime();
					System.out.println("fechaFinInicio "+fin1.toString());
				}
				
				if(dateinicio2.getDate()!=null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(dateinicio2.getDate());
					calendar.set(Calendar.HOUR_OF_DAY, 0); // Establecemos la hora a las 9 de la mañana
					calendar.set(Calendar.MINUTE, 0); // Establecemos los minutos a 30
					calendar.set(Calendar.SECOND, 0); // Establecemos los segundos a 0
					calendar.set(Calendar.MILLISECOND, 0); // Establecemos los milisegundos a 0
					inicio2=calendar.getTime();
					System.out.println("fechaInicioFin "+inicio2.toString());
				}
				
				if(datefin2.getDate()!=null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(datefin2.getDate());
					calendar.set(Calendar.HOUR_OF_DAY, 23); // Establecemos la hora a las 9 de la mañana
					calendar.set(Calendar.MINUTE, 59); // Establecemos los minutos a 30
					calendar.set(Calendar.SECOND, 59); // Establecemos los segundos a 0
					calendar.set(Calendar.MILLISECOND, 0); // Establecemos los milisegundos a 0
					fin2=calendar.getTime();
					System.out.println("fechaFinFIN "+fin2.toString());
				}
				 
				 try {
						listEventos=ejb.buscarEventosPor(textIdEvento.getText(), textTitulo.getText(),textLocalizacion.getText(),
								modalidad, tipoEvento, itrNombre, inicio1,  fin1, inicio2,  fin2, false);
				
				} catch (Exception e2) {
					System.out.println(e2);
				}
				 generateRows(listEventos);
			}
		});
		filtrarEventosBtn.setBounds(633, 111, 113, 21);
		panel.add(filtrarEventosBtn);
		
		chckbxEventoBorrado = new JCheckBox("Evento borrado");
		chckbxEventoBorrado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxEventoBorrado.setEnabled(false);
		chckbxEventoBorrado.setBounds(227, 701, 188, 21);
		panel.add(chckbxEventoBorrado);
		
		JLabel lblNewLabel_2 = new JLabel("Filtros por periodo de tiempo");
		lblNewLabel_2.setBounds(628, 10, 202, 13);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha finalizacion");
		lblNewLabel_3.setBounds(755, 34, 183, 13);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Fecha inicio");
		lblNewLabel_4.setBounds(532, 34, 188, 13);
		panel.add(lblNewLabel_4);
		
		JButton btnActivarEvento = new JButton("Activar evento");
		btnActivarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(eventoEditable!=null) {
					if(activarEvento(eventoEditable.getTitulo())) {
						eventoEditable.setBajaLogica(false);
						eventoEditable=ejb.modificarEvento(eventoEditable);			
						generateRows(ejb.listarEventos());
						
					}
				}
			}
		});
		btnActivarEvento.setBounds(227, 728, 131, 21);
		panel.add(btnActivarEvento);
		
		
		
		frame.pack();
//		frame.setVisible(true);
		
	}
	
	private void generateRows(List<Evento> eventos) {
		tableModel.getDataVector().removeAllElements();
		tableModel.fireTableDataChanged();
		for (Evento evento : eventos) {
			Vector<String> row = new Vector<String>(7);
			row.add(evento.getId().toString());
			row.add(evento.getTitulo());
			row.add(evento.getTipo().getTipo());
			row.add(evento.getModalidad().getModalidad());
			row.add(evento.getLocalizacion());		
			row.add(formatter.format(evento.getInicio()));
			row.add(formatter.format(evento.getFin()));
			if(evento.getTipoEstadoEvento()!=null) {
				row.add(evento.getTipoEstadoEvento().toString());
			}else {
				row.add("---");
			}
			
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
	
	private DefaultComboBoxModel<TipoEstadoEvento> generateTipoEstadoEventoComboBoxData() {
		DefaultComboBoxModel<TipoEstadoEvento> comboBoxModel = new DefaultComboBoxModel<>();
		TipoEstadoEvento vacioTEE = new TipoEstadoEvento();
		vacioTEE.setNombre("");
		comboBoxModel.addElement(vacioTEE);
		
		ejb.listarTipoEstadoEvento().forEach(comboBoxModel::addElement);
//		for(TipoEstadoEvento tEEItem: ejb.listarTipoEstadoEvento()){
//			if(tEEItem.getActivo()) {
//				comboBoxModel.addElement(tEEItem);
//			}
//		}
		return comboBoxModel;
	}

	
	public Boolean validarDatos() {
		if(editarTextTitulo.getText()=="" ) {
			JOptionPane.showMessageDialog(null, "El titulo del evento es un dato obligatorio",
					"Informacion", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(editarComboBoxTipoEvento.getSelectedIndex()==0  ) {
			JOptionPane.showMessageDialog(null, "Seleccione el tipo de evento",
					"Informacion", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if(editarTipoEstadoEventoComboBox.getSelectedIndex()==0  ) {
			JOptionPane.showMessageDialog(null, "Seleccione el tipo de estado del evento",
					"Informacion", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if(editarComboBoxModalidadEvento.getSelectedIndex()==0  ) {
			JOptionPane.showMessageDialog(null, "Seleccione la modalidad de evento",
					"Informacion", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(editarDateFechaInicio.getDate()==null ) {
			JOptionPane.showMessageDialog(null, "Ingrese la fecha de inicio del evento",
					"Informacion", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(editarDateFechaFin.getDate()==null ) {
			JOptionPane.showMessageDialog(null, "Ingrese la fecha de fin del evento",
					"Informacion", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(editarItrEventoComboBox.getSelectedIndex()==0){
			JOptionPane.showMessageDialog(null, "Seleccione el ITR correspondiente al evento",
					"Informacion", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}


		return true;
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
	
	public boolean borrarRow(String mensaje) {
		//msj.mostrarMensaje(Mensajes.BAJA);

		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog (null, "Seguro quieres borrar: "+mensaje,"Warning",dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION){
			return true;
		}
		return false;
		}
	public boolean activarEvento(String mensaje) {
		//msj.mostrarMensaje(Mensajes.BAJA);

		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog (null, "Seguro quieres activar: "+mensaje,"Warning",dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION){
			return true;
		}
		return false;
		}
	public Boolean validarBajaLogica(){
		if(eventoEditable.getInicio().before(hoy)) {
			List<ConvocatoriaAsistenciaEventoEstudiante> list = new ArrayList<ConvocatoriaAsistenciaEventoEstudiante>();
			
			list=ejb.listarConvocatoriaEventEstuPorEvento(eventoEditable);
			for(ConvocatoriaAsistenciaEventoEstudiante conv :list) {
				if(conv.getAsistencia()) {
					JOptionPane.showMessageDialog(null, "No es posible eliminar un evento con asistentes",
							"Informacion", JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
			}
			System.out.println("Termine de corroborar no hay asistentes al evento se puede borrar");
			return true;
		}else {
			JOptionPane.showMessageDialog(null, "No es posible eliminar un evento todavia no empezo",
					"Informacion", JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		return false;
	}
}
