package tecnofenix.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tecnofenix.interfaces.ConstanciaBeanRemote;
import tecnofenix.interfaces.EventoBeanRemote;
import tecnofenix.exception.ServiciosException;

import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Analista;
import tecnofenix.entidades.Usuario;
import tecnofenix.entidades.Evento;
import tecnofenix.entidades.Constancia;
import tecnofenix.entidades.Constancia.EstadoConstancia;

public class UIModificarConstancia {
	
	public JFrame frame;
	
	@EJB
	ConstanciaBeanRemote constanciaBeanRemote;
	
	@EJB
	EventoBeanRemote eventoBeanRemote;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar(Usuario usuario, Constancia constancia) {
		try {
			InitialContext ctx = new InitialContext();
			constanciaBeanRemote = (ConstanciaBeanRemote) ctx.lookup("ejb:/TecnoFenixEJB/ConstanciaBean!tecnofenix.interfaces.ConstanciaBeanRemote");
			eventoBeanRemote = (EventoBeanRemote) ctx.lookup("ejb:/TecnoFenixEJB/EventoBean!tecnofenix.interfaces.EventoBeanRemote");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}

		frame = new JFrame("Ver constancia");
		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(550, 550));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		JLabel lblDetalle = new JLabel("Detalle:");
		lblDetalle.setBounds(23, 102, 86, 13);
		panel.add(lblDetalle);

		JTextField txtDetalle = new JTextField();
		txtDetalle.setBounds(23, 115, 360, 19);
		txtDetalle.setText(constancia.getDetalle());
		txtDetalle.setEnabled(usuario instanceof Estudiante);
		panel.add(txtDetalle);
		
		JLabel lblEventos = new JLabel("Evento:");
		lblEventos.setBounds(23, 200, 86, 13);
		panel.add(lblEventos);
		
		JTextField txtEvento = new JTextField();
		txtEvento.setBounds(23, 213, 360, 19);
		txtEvento.setText(constancia.getEventoId().getId().toString());
		txtEvento.setEnabled(false);
		panel.add(txtEvento);
		
		JLabel lblModEventos = new JLabel("Modificar evento:");
		lblModEventos.setBounds(23, 250, 150, 13);
		lblModEventos.setVisible(usuario instanceof Estudiante);
		panel.add(lblModEventos);
		
		JComboBox<Evento> comboEventos = new JComboBox<Evento>();
		comboEventos.setBounds(23, 263, 360, 21);
		List<Evento> eventos = new ArrayList<Evento>();
		try {
			eventos = eventoBeanRemote.obtenerEventos();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hubo un error al intentar traer la lista de eventos.", "Error", JOptionPane.ERROR_MESSAGE);
		}

		for(Evento eventoItem: eventos){
			comboEventos.addItem(eventoItem);
		}
		comboEventos.setVisible(usuario instanceof Estudiante);
		panel.add(comboEventos);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(23, 300, 86, 13);
		lblEstado.setVisible(usuario instanceof Analista);
		panel.add(lblEstado);
		
		JComboBox<Constancia.EstadoConstancia> comboEstados = new JComboBox<Constancia.EstadoConstancia>();
		comboEstados.setBounds(23, 313, 360, 21);
		comboEstados.addItem(Constancia.EstadoConstancia.INGRESADO);
		comboEstados.addItem(Constancia.EstadoConstancia.EN_PROCESO);
		comboEstados.addItem(Constancia.EstadoConstancia.FINALIZADO);
		comboEstados.setVisible(usuario instanceof Analista);
		panel.add(comboEstados);
		
		JButton btnModificarConstancia = new JButton("Modificar constancia");
		btnModificarConstancia.setBounds(23, 481, 189, 19);
		btnModificarConstancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (usuario instanceof Estudiante) {
					constancia.setDetalle(txtDetalle.getText());
					Evento evento = (Evento) comboEventos.getSelectedItem();
					constancia.setEventoId(evento);
				} else if (usuario instanceof Analista) {

					Constancia.EstadoConstancia estado = (Constancia.EstadoConstancia) comboEstados.getSelectedItem();
					constancia.setEstado(estado);
				}
				
        		try {
        			constanciaBeanRemote.modificarConstancia(constancia);
        		} catch (ServiciosException se) {
        			System.out.println("Error al crear constancia: " + se.getMessage());
        			JOptionPane.showMessageDialog(null, "Hubo un error al modificar la constancia.", "Error", JOptionPane.ERROR_MESSAGE);
        		}
        		
        		JOptionPane.showMessageDialog(null, "Se modifico la constancia.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        		
			}
			
		});
		panel.add(btnModificarConstancia);
		frame.pack();
		
	}
	
}
