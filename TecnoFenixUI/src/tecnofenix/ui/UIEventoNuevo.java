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
import tecnofenix.entidades.Evento;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import tecnofenix.entidades.TipoEvento;
import tecnofenix.entidades.ModalidadEvento;
import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import com.toedter.calendar.JDateChooser;

public class UIEventoNuevo {

	public JFrame frame;
	private JTable tablaUsuarios;
	private EJBUsuarioRemoto ejb;
	private JTextField tituloEventoBtn;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {


		ejb = new EJBUsuarioRemoto();
		frame = new JFrame("Nuevo evento");
		
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(500, 500));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
	
		panel.setLayout(null);
		
		JButton crearEventoBtn = new JButton("Aceptar");
		crearEventoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Evento evento = new Evento(null, Date.valueOf(LocalDate.now()));
				ejb.crearEvento(evento);
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
		
		tituloEventoBtn = new JTextField();
		tituloEventoBtn.setBounds(33, 46, 96, 19);
		panel.add(tituloEventoBtn);
		tituloEventoBtn.setColumns(10);
		
		JLabel tituloEventoLabel = new JLabel("Titulo");
		tituloEventoLabel.setBounds(33, 29, 45, 13);
		panel.add(tituloEventoLabel);
		
		JComboBox tipoEvento = new JComboBox();
		tipoEvento.setModel(new DefaultComboBoxModel(TipoEvento.values()));
		tipoEvento.setBounds(33, 86, 96, 21);
		panel.add(tipoEvento);
		
		JLabel tipoEventoLabel = new JLabel("Tipo de evento");
		tipoEventoLabel.setBounds(33, 73, 74, 13);
		panel.add(tipoEventoLabel);
		
		JComboBox modalidadEvento = new JComboBox();
		modalidadEvento.setModel(new DefaultComboBoxModel(ModalidadEvento.values()));
		modalidadEvento.setBounds(33, 133, 96, 21);
		panel.add(modalidadEvento);
		
		JLabel modalidadEventoLabel = new JLabel("Modalidad de evento");
		modalidadEventoLabel.setBounds(33, 117, 96, 13);
		panel.add(modalidadEventoLabel);
		
		JDateChooser fechaInicioDateChooser = new JDateChooser();
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
		
		JComboBox<?> itrComboBox = new JComboBox();
		itrComboBox.setBounds(167, 45, 96, 21);
		panel.add(itrComboBox);
		
		JLabel itrLabel = new JLabel("ITR");
		itrLabel.setBounds(166, 29, 45, 13);
		panel.add(itrLabel);
		
		frame.pack();
	}
}
