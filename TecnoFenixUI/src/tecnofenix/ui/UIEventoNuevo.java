package tecnofenix.ui;

import java.awt.Dimension;
import java.sql.Date;
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
//	private 
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {


		ejb = new EJBUsuarioRemoto();
		frame = new JFrame("Nuevo evento");
		
		
		usuarioRemote = new EJBUsuarioRemoto();
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(500, 500));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
	
		panel.setLayout(null);
		
	
		txtTituloEvento = new JTextField();
		txtTituloEvento.setBounds(33, 46, 96, 19);
		panel.add(txtTituloEvento);
		txtTituloEvento.setColumns(10);
		
		JLabel tituloEventoLabel = new JLabel("Titulo");
		tituloEventoLabel.setBounds(33, 29, 45, 13);
		panel.add(tituloEventoLabel);
		
		cmbTipoEvento = new JComboBox();
		cmbTipoEvento.setModel(new DefaultComboBoxModel(TipoEvento.values()));
		cmbTipoEvento.setBounds(33, 86, 96, 21);
		panel.add(cmbTipoEvento);
		
		JLabel tipoEventoLabel = new JLabel("Tipo de evento");
		tipoEventoLabel.setBounds(33, 73, 74, 13);
		panel.add(tipoEventoLabel);
		
		cmbModalidadEvento = new JComboBox();
		cmbModalidadEvento.setModel(new DefaultComboBoxModel(ModalidadEvento.values()));
		cmbModalidadEvento.setBounds(33, 133, 96, 21);
		panel.add(cmbModalidadEvento);
		
		JLabel modalidadEventoLabel = new JLabel("Modalidad de evento");
		modalidadEventoLabel.setBounds(33, 117, 96, 13);
		panel.add(modalidadEventoLabel);
		
		fechaInicioDateChooser = new JDateChooser();
		fechaInicioDateChooser.setBounds(33, 179, 96, 19);
		panel.add(fechaInicioDateChooser);
		
		JLabel fechaInicioLabel = new JLabel("Fecha de inicio");
		fechaInicioLabel.setBounds(33, 164, 96, 13);
		panel.add(fechaInicioLabel);
		
		JDateChooser fechaFinDateChooser = new JDateChooser();
		fechaFinDateChooser.setBounds(33, 223, 96, 19);
		panel.add(fechaFinDateChooser);
		
		JLabel fechaFinLabel = new JLabel("Fecha de finalizacion");
		fechaFinLabel.setBounds(33, 208, 96, 13);
		panel.add(fechaFinLabel);
		
		cmbItr = new JComboBox();
		cmbItr.setBounds(167, 45, 96, 21);
				
		List<Itr>listItr = usuarioRemote.listarITR();
		for(Itr itrItem: listItr){
			cmbItr.addItem(itrItem);
			System.out.println(itrItem.toString());
		}
		panel.add(cmbItr);

		JLabel itrLabel = new JLabel("ITR");
		itrLabel.setBounds(166, 29, 45, 13);
		panel.add(itrLabel);
		
		JButton crearEventoBtn = new JButton("Aceptar");
		crearEventoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if(validarDatos()) {
					addEvento();
//				}
				
			}
		});
		crearEventoBtn.setBounds(33, 294, 85, 21);
		panel.add(crearEventoBtn);
		
		JButton cancelarEventoBtn = new JButton("Cancelar");
		cancelarEventoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				
			}
		});
		cancelarEventoBtn.setBounds(155, 294, 85, 21);
		panel.add(cancelarEventoBtn);
		
		
		frame.pack();
	}
	
	public void addEvento() {
//		if(validarDatos()) { 
			Evento evento =new Evento(txtTituloEvento.getText(),
					(TipoEvento)cmbTipoEvento.getSelectedItem(),
					(ModalidadEvento)cmbModalidadEvento.getSelectedItem(), 
					fechaInicioDateChooser.getDate(),
					fechaFinDateChooser.getDate(),
					"Localizacion",
					false,
					(Itr) cmbItr.getSelectedItem(),
					null,
					null,
					null,
					null,
					null,
					null);

			evento=ejb.crearEvento(evento);
		if(evento != null) {
			JOptionPane.showMessageDialog(null, "Se creo el evento ["+txtTituloEvento.getText()+"]",
				"Informacion", JOptionPane.INFORMATION_MESSAGE);
		}
		
		limpiarDatos();
//		}
	}
	
	public Boolean validarDatos() {
		
		return false;
	}
	
	public void limpiarDatos() {
		
	
	}
}
