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
import tecnofenix.entidades.Tutor;
import tecnofenix.entidades.TutorResponsableEvento;
import tecnofenix.entidades.ModalidadEvento;
import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import com.toedter.calendar.JDateChooser;

public class UIEventoNuevo {

	public JFrame frame;
	private JTable tablaUsuarios;
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
	private UIListaTutores uiListaTutores = new UIListaTutores();
//	private 
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {


		ejb = new EJBUsuarioRemoto();
		frame = new JFrame("Nuevo evento");
		
		uiListaTutores.inicializar();
		uiListaTutores.frame.setVisible(false);
		
		usuarioRemote = new EJBUsuarioRemoto();
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(500, 500));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
	
		panel.setLayout(null);
		
	
		txtTituloEvento = new JTextField();
		txtTituloEvento.setBounds(33, 46, 145, 19);
		panel.add(txtTituloEvento);
		txtTituloEvento.setColumns(10);
		
		JLabel tituloEventoLabel = new JLabel("Titulo");
		tituloEventoLabel.setBounds(33, 29, 45, 13);
		panel.add(tituloEventoLabel);
		
		cmbTipoEvento = new JComboBox();
		cmbTipoEvento.setModel(new DefaultComboBoxModel(TipoEvento.values()));
		cmbTipoEvento.setBounds(33, 86, 145, 21);
		panel.add(cmbTipoEvento);
		
		JLabel tipoEventoLabel = new JLabel("Tipo de evento");
		tipoEventoLabel.setBounds(33, 73, 74, 13);
		panel.add(tipoEventoLabel);
		
		cmbModalidadEvento = new JComboBox();
		cmbModalidadEvento.setModel(new DefaultComboBoxModel(ModalidadEvento.values()));
		cmbModalidadEvento.setBounds(33, 133, 145, 21);
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
		fechaFinDateChooser.setBounds(33, 223, 96, 19);
		panel.add(fechaFinDateChooser);
		
		JLabel fechaFinLabel = new JLabel("Fecha de finalizacion");
		fechaFinLabel.setBounds(33, 208, 145, 13);
		panel.add(fechaFinLabel);
		
		cmbItr = new JComboBox();
		cmbItr.setBounds(211, 45, 167, 21);
				
		List<Itr>listItr = usuarioRemote.listarITR();
		for(Itr itrItem: listItr){
			cmbItr.addItem(itrItem);
			System.out.println(itrItem.toString());
		}
		panel.add(cmbItr);

		JLabel itrLabel = new JLabel("ITR");
		itrLabel.setBounds(211, 29, 45, 13);
		panel.add(itrLabel);
		
		JButton crearEventoBtn = new JButton("Aceptar");
		crearEventoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if(validarDatos()) {
					addEvento();
//				}
				
			}
		});
		crearEventoBtn.setBounds(293, 294, 85, 21);
		panel.add(crearEventoBtn);
		
		JButton cancelarEventoBtn = new JButton("Cancelar");
		cancelarEventoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				
			}
		});
		cancelarEventoBtn.setBounds(388, 294, 85, 21);
		panel.add(cancelarEventoBtn);
		
		java.awt.List listaDeTutores = new java.awt.List();
		listaDeTutores.setBounds(211, 161, 262, 81);
		panel.add(listaDeTutores);
		
		JLabel lblNewLabel = new JLabel("Tutores seleccionados");
		lblNewLabel.setBounds(211, 141, 167, 13);
		panel.add(lblNewLabel);
		
		JButton btnSeleccionarTutores = new JButton("Seleccionar Tutores");
		btnSeleccionarTutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uiListaTutores.frame.setVisible(true);
				
			}
		});
		
		uiListaTutores.btnSeleccionarTutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if(eventoEditable.getTutorResponsableEventoCollection()!=null && eventoEditable.getTutorResponsableEventoCollection().isEmpty()) {
				System.out.println("Presiono el boton aceptar tutores tamaño lista "+uiListaTutores.getListTutoresSeleccionados().size());
				listTutorResEvent.clear();
				
				for(Tutor tut :uiListaTutores.getListTutoresSeleccionados()) {
						
						System.out.println("Entre por el tutor " +tut.getNombres()+" "+tut.getApellidos());
						TutorResponsableEvento tutResEvent = new TutorResponsableEvento();
						tutResEvent.setTutorId(tut);
						listTutorResEvent.add(tutResEvent);
						
						listaDeTutores.add(tut.getDocumento() +" "+tut.getNombres()+" "+tut.getApellidos());
					}
				
//				}//else {
//					listTutorResEvent.clear();
//					for(Tutor tut :uiListaTutores.getListTutores()) {
//						TutorResponsableEvento tutResEvent = new TutorResponsableEvento();
//						tutResEvent.setTutorId(tut);
//						tutResEvent.setEventoId(eventoEditable);
//						listTutorResEvent.add(tutResEvent);
//						
//					}
//				}
			}
		});
		
		btnSeleccionarTutores.setBounds(211, 113, 167, 21);
		panel.add(btnSeleccionarTutores);
		
		JLabel lblNewLabel_1 = new JLabel("Localizacion del evento");
		lblNewLabel_1.setBounds(211, 73, 217, 13);
		panel.add(lblNewLabel_1);
		
		textLocalizacion = new JTextField();
		textLocalizacion.setBounds(211, 87, 167, 19);
		panel.add(textLocalizacion);
		textLocalizacion.setColumns(10);
		
		
		frame.pack();
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
	
	public void limpiarDatos() {
		txtTituloEvento.setText("");
		cmbTipoEvento.setSelectedIndex(0);
		cmbModalidadEvento.setSelectedIndex(0);; 
		fechaInicioDateChooser.setDate(null);;
		fechaFinDateChooser.setDate(null);
		textLocalizacion.setText("");
		cmbItr.setSelectedIndex(0);
	
	}
}
