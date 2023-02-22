package tecnofenix.ui;

import java.awt.Dimension;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Evento;
import tecnofenix.entidades.Itr;

import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import tecnofenix.entidades.TipoEvento;
import tecnofenix.entidades.TipoGenero;
import tecnofenix.entidades.TipoTutorArea;
import tecnofenix.entidades.TipoTutorEncargado;
import tecnofenix.entidades.Tutor;
import tecnofenix.entidades.TutorResponsableEvento;
import tecnofenix.entidades.ModalidadEvento;
import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.awt.Color;

import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import java.awt.Component;

public class UIEventoNuevo {

	public JFrame frame;

	private JTable table;
	private DefaultTableModel modelo;
	private Object[] fila;
	
	private EJBUsuarioRemoto ejb;
	private JTextField txtTituloEvento;
	private EJBUsuarioRemoto usuarioRemote;
	private JComboBox<Itr> cmbItr;
	private JDateChooser fechaInicioDateChooser;
	private JDateChooser fechaFinDateChooser;
	private JComboBox cmbTipoEvento;
	private JComboBox cmbModalidadEvento;
	private JTextField textLocalizacion;
	
	private List<TutorResponsableEvento> listTutorResEvent = new ArrayList<TutorResponsableEvento>();
	public List<Tutor> listTutores= new ArrayList<Tutor>();
//	private UIListaTutores uiListaTutores = new UIListaTutores();
//	private 
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {


		ejb = new EJBUsuarioRemoto();
		frame = new JFrame("Nuevo evento");
		
//		uiListaTutores.inicializar();
//		uiListaTutores.frame.setVisible(false);
		
		usuarioRemote = new EJBUsuarioRemoto();
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(500, 500));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
	
		panel.setLayout(null);
		
	
		txtTituloEvento = new JTextField();
		txtTituloEvento.setBounds(33, 46, 217, 19);
		panel.add(txtTituloEvento);
		txtTituloEvento.setColumns(10);
		
		JLabel tituloEventoLabel = new JLabel("Titulo");
		tituloEventoLabel.setBounds(33, 29, 45, 13);
		panel.add(tituloEventoLabel);
		
		cmbTipoEvento = new JComboBox();
		cmbTipoEvento.setModel(new DefaultComboBoxModel(TipoEvento.values()));
		cmbTipoEvento.setBounds(33, 86, 217, 21);
		panel.add(cmbTipoEvento);
		
		JLabel tipoEventoLabel = new JLabel("Tipo de evento");
		tipoEventoLabel.setBounds(33, 73, 74, 13);
		panel.add(tipoEventoLabel);
		
		cmbModalidadEvento = new JComboBox();
		cmbModalidadEvento.setModel(new DefaultComboBoxModel(ModalidadEvento.values()));
		cmbModalidadEvento.setBounds(33, 133, 217, 21);
		panel.add(cmbModalidadEvento);
		
		JLabel modalidadEventoLabel = new JLabel("Modalidad de evento");
		modalidadEventoLabel.setBounds(33, 117, 96, 13);
		panel.add(modalidadEventoLabel);
		
		fechaInicioDateChooser = new JDateChooser();
		fechaInicioDateChooser.setBounds(33, 179, 96, 19);
		panel.add(fechaInicioDateChooser);
		
		JLabel fechaInicioLabel = new JLabel("Fecha de inicio");
		fechaInicioLabel.setBounds(33, 164, 145, 13);
		panel.add(fechaInicioLabel);
		
		fechaFinDateChooser = new JDateChooser();
		fechaFinDateChooser.setBounds(293, 179, 96, 19);
		panel.add(fechaFinDateChooser);
		
		JLabel fechaFinLabel = new JLabel("Fecha de finalizacion");
		fechaFinLabel.setBounds(293, 164, 145, 13);
		panel.add(fechaFinLabel);
		
		cmbItr = new JComboBox();
		cmbItr.setBounds(293, 45, 167, 21);
				
		List<Itr>listItr = usuarioRemote.listarITR();
		for(Itr itrItem: listItr){
			cmbItr.addItem(itrItem);
			System.out.println(itrItem.toString());
		}
		panel.add(cmbItr);

		JLabel itrLabel = new JLabel("ITR");
		itrLabel.setBounds(293, 29, 45, 13);
		panel.add(itrLabel);
		
		JButton crearEventoBtn = new JButton("Aceptar");
		crearEventoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if(validarDatos()) {
					addEvento();
//				} 
				
			}
		});
		crearEventoBtn.setBounds(293, 377, 85, 21);
		panel.add(crearEventoBtn);
		
		JButton cancelarEventoBtn = new JButton("Cancelar");
		cancelarEventoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				
			}
		});
		cancelarEventoBtn.setBounds(389, 377, 85, 21);
		panel.add(cancelarEventoBtn);
		
		JLabel lblNewLabel = new JLabel("Tutores seleccionados");
		lblNewLabel.setBounds(33, 208, 167, 13);
		panel.add(lblNewLabel);
		
		JButton btnSeleccionarTutores = new JButton("Seleccionar Tutores");
		btnSeleccionarTutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				uiListaTutores.frame.setVisible(true);
				UIListaTutores uiListaTutores = new UIListaTutores();
				uiListaTutores.inicializar(UIEventoNuevo.this);
				uiListaTutores.frame.setVisible(true);
			}
		});
		
//		uiListaTutores.btnSeleccionarTutores.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
////				if(eventoEditable.getTutorResponsableEventoCollection()!=null && eventoEditable.getTutorResponsableEventoCollection().isEmpty()) {
//				System.out.println("Presiono el boton aceptar tutores tamaño lista "+uiListaTutores.getListTutoresSeleccionados().size());
//				listTutorResEvent.clear();
//				
//				for(Tutor tut :uiListaTutores.getListTutoresSeleccionados()) {
//						
//						System.out.println("Entre por el tutor " +tut.getNombres()+" "+tut.getApellidos());
//						TutorResponsableEvento tutResEvent = new TutorResponsableEvento();
//						tutResEvent.setTutorId(tut);
//						listTutorResEvent.add(tutResEvent);
//						
//						listaDeTutores.add(tut.getDocumento() +" "+tut.getNombres()+" "+tut.getApellidos());
//					}
//				
////				}//else {
////					listTutorResEvent.clear();
////					for(Tutor tut :uiListaTutores.getListTutores()) {
////						TutorResponsableEvento tutResEvent = new TutorResponsableEvento();
////						tutResEvent.setTutorId(tut);
////						tutResEvent.setEventoId(eventoEditable);
////						listTutorResEvent.add(tutResEvent);
////						
////					}
////				}
//			}
//		});
		
		
		
		
//		});
		
		btnSeleccionarTutores.setBounds(33, 377, 167, 21);
		panel.add(btnSeleccionarTutores);
		
		JLabel lblNewLabel_1 = new JLabel("Localizacion del evento");
		lblNewLabel_1.setBounds(293, 73, 217, 13);
		panel.add(lblNewLabel_1);
		
		textLocalizacion = new JTextField();
		textLocalizacion.setBounds(293, 87, 167, 19);
		panel.add(textLocalizacion);
		textLocalizacion.setColumns(10);
		
		
		

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
		final String[] columnNames = { "Id", "CI","Nombre", "Apellido","Tipo","Area" };

		// insertamos las columnas
		for (int column = 0; column < columnNames.length; column++) {
			// agrega las columnas a la tabla
			modelo.addColumn(columnNames[column]);
		}

		// Se crea un array que será una de las filas de la tabla.
		fila = new Object[columnNames.length];

		// se define el tamaño de la tabla
		table.setBounds(93, 215, 100, 100);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
	        public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
				
				}
	        }
	    });
		JScrollPane scrollPaneTutSeleccionados = new JScrollPane(table);
		scrollPaneTutSeleccionados.setBounds(33, 223, 457, 137);
		panel.add(scrollPaneTutSeleccionados);
		
		
		frame.pack();
	}
	
	public void obtenerListaDeTutores(List<Tutor> lista) {
		System.out.println("Presiono el boton aceptar tutores tamaño lista "+lista.size());
		listTutorResEvent.clear();
		for(Tutor tut :lista) {

				System.out.println("Entre por el tutor " +tut.getNombres()+" "+tut.getApellidos());
				TutorResponsableEvento tutResEvent = new TutorResponsableEvento();
				tutResEvent.setTutorId(tut);
				listTutorResEvent.add(tutResEvent);
			
			}
		actualizarLista(lista);
	}
	
	
	public void addEvento() {
		if(validarDatos()) { //todo
		Evento evento =new Evento(txtTituloEvento.getText(),
				(TipoEvento)cmbTipoEvento.getSelectedItem(),
				(ModalidadEvento)cmbModalidadEvento.getSelectedItem(), 
				fechaInicioDateChooser.getDate(),
				fechaFinDateChooser.getDate(),
				textLocalizacion.getText(),
				false,
				(Itr)cmbItr.getSelectedItem(),
				null,
				null,
				null,
				null,
				null,
				null);
		evento =ejb.crearEvento(evento);
		System.out.println("Evento creado =" +evento.getId());
		for(TutorResponsableEvento tr : listTutorResEvent) {
			System.out.println("Seteando evento crado recien a tutores idevento:" +evento.getId());
			System.out.println("Tutor a setear evento "+tr.getTutorId());
			tr.setEventoId(evento);
			ejb.asignarTutorAEvento(tr);
		}
		if(evento != null) {
			JOptionPane.showMessageDialog(null, "Se creo el evento ["+txtTituloEvento.getText()+"]",
				"Informacion", JOptionPane.INFORMATION_MESSAGE);
		}
		
		limpiarDatos();
		}
	}
	
	public Boolean validarDatos() {
		if(txtTituloEvento.getText()=="" ) {
			JOptionPane.showMessageDialog(null, "El titulo del evento es un dato obligatorio",
					"Informacion", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(cmbTipoEvento.getSelectedIndex()==0  ) {
			JOptionPane.showMessageDialog(null, "Seleccione el tipo de evento",
					"Informacion", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(cmbModalidadEvento.getSelectedIndex()==0  ) {
			JOptionPane.showMessageDialog(null, "Seleccione la modalidad de evento",
					"Informacion", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(fechaInicioDateChooser.getDate()==null ) {
			JOptionPane.showMessageDialog(null, "Ingrese la fecha de inicio del evento",
					"Informacion", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(fechaFinDateChooser.getDate()==null ) {
			JOptionPane.showMessageDialog(null, "Ingrese la fecha de fin del evento",
					"Informacion", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if(cmbItr.getSelectedIndex()==0){
			JOptionPane.showMessageDialog(null, "Seleccione el ITR correspondiente al evento",
					"Informacion", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}


		return true;
	}
	
	public void limpiarTabla() {
//		if (listTutores != null || !listTutores.isEmpty() || listTutores.size() > 0) {
//			listTutores.clear();
//			
//		}
		modelo.getDataVector().removeAllElements();
		modelo.fireTableDataChanged();

	}
	
	public void actualizarLista(List<Tutor> listarTutSel) {
		System.out.println("Entrando a cargar la lista de TUTORES");
		limpiarTabla();
		System.out.println("usuarioRemote.listarTutores()");
		this.listTutores = listarTutSel;
		System.out.println(listTutores.toString());
		// Se rellena cada posición del array con una de las columnas de la tabla en
		// base de datos.
		for (Tutor tutor : listTutores) {
//			"Id", "CI","Nombre", "Apellido","Tipo","Area"
			fila[0] = tutor.getId();
			fila[1] = tutor.getDocumento().toString();
			fila[2] = tutor.getNombres();
			fila[3] = tutor.getApellidos();
			fila[4] = TipoTutorEncargado.getIdTipo(tutor.getTipo());
			fila[5] = TipoTutorArea.getIdArea(tutor.getArea());
			// Se añade al modelo la fila completa.
			modelo.addRow(fila);

		}
	} 
	
	public void limpiarDatos() {
		txtTituloEvento.setText("");
		cmbTipoEvento.setSelectedIndex(0);
		cmbModalidadEvento.setSelectedIndex(0);; 
		fechaInicioDateChooser.setDate(null);;
		fechaFinDateChooser.setDate(null);
		textLocalizacion.setText("");
		cmbItr.setSelectedIndex(0);
		listTutorResEvent.clear();
		listTutores.clear();
	}
}
