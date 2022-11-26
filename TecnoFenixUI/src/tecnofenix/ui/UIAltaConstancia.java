package src.tecnofenix.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import tecnofenix.entidades.Usuario;
import tecnofenix.entidades.Evento;
import tecnofenix.entidades.Constancia;

public class UIAltaConstancia {

	public JFrame frame;
	
	@EJB
	ConstanciaBeanRemote constanciaBeanRemote;
	
	@EJB
	EventoBeanRemote eventoBeanRemote;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar(Usuario usuario) {
		try {
			InitialContext ctx = new InitialContext();
			constanciaBeanRemote = (ConstanciaBeanRemote) ctx.lookup("ejb:/TecnoFenixEJB/ConstanciaBean!tecnofenix.interfaces.ConstanciaBeanRemote");
			eventoBeanRemote = (EventoBeanRemote) ctx.lookup("ejb:/TecnoFenixEJB/EventoBean!tecnofenix.interfaces.EventoBeanRemote");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}

		frame = new JFrame("Solicitar constancia");
		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(500, 500));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		JLabel lblDetalle = new JLabel("Detalle:");
		lblDetalle.setBounds(23, 102, 86, 13);
		panel.add(lblDetalle);

		JTextField txtDetalle = new JTextField();
		txtDetalle.setBounds(23, 115, 360, 19);
		panel.add(txtDetalle);
		
		JLabel lblEventos = new JLabel("Evento:");
		lblEventos.setBounds(23, 200, 86, 13);
		panel.add(lblEventos);
		
		JComboBox<Evento> comboEventos = new JComboBox<Evento>();
		comboEventos.setBounds(23, 213, 360, 21);
		List<Evento> eventos = eventoBeanRemote.obtenerEventos();
		for(Evento eventoItem: eventos){
			comboEventos.addItem(eventoItem);
		}
		panel.add(comboEventos);
		
		JButton btnAltaConstancia = new JButton("Solicitar constancia");
		btnAltaConstancia.setBounds(23, 481, 189, 19);
		btnAltaConstancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
        		Constancia constancia = new Constancia();
        		
        		String detalle = txtDetalle.getText();
        		Evento evento = (Evento) comboEventos.getSelectedItem();
        		
        		constancia.setDetalle(detalle);
        		constancia.setFecha(new Date());
        		constancia.setEventoId(evento);
        		constancia.setEstudianteId((Estudiante) usuario);
        		constancia.setEstado(Constancia.EstadoConstancia.INGRESADO);
        		
        		try {
        			constanciaBeanRemote.crearConstancia(constancia);
        		} catch (ServiciosException se) {
        			System.out.println("Error al crear constancia: " + se.getMessage());
        			JOptionPane.showMessageDialog(null, "Hubo un error al ingresar constancia.", "Error", JOptionPane.ERROR_MESSAGE);
        		}
        		
        		JOptionPane.showMessageDialog(null, "Se ingresó la constancia.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        		
			}
			
		});
		panel.add(btnAltaConstancia);
	}

}
