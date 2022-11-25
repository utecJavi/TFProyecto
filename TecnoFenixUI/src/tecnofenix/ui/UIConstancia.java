package tecnofenix.ui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//import tecnocanarios.dao.DAOPersona;
//import tecnocanarios.dao.DAORol;
//import tecnocanarios.entidades.Persona;
//import tecnocanarios.entidades.Rol;
//import tecnocanarios.mensajes.MensajePopUp;
//import tecnocanarios.mensajes.Mensajes;
import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.Analista;
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Usuario;
import tecnofenix.entidades.Constancia;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tecnofenix.interfaces.ConstanciaBeanRemote;
import tecnofenix.exception.ServiciosException;


public class UIConstancia {


	public JFrame frame;
	
	@EJB
	ConstanciaBeanRemote constanciaBeanRemote;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar(Usuario usuario) {

		
		try {
			InitialContext ctx = new InitialContext();
			constanciaBeanRemote = (ConstanciaBeanRemote) ctx.lookup("ejb:/TecnoFenixEJB/ConstanciaBean!tecnofenix.interfaces.ConstanciaBeanRemote");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}

		frame = new JFrame("Constancias");
		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(800, 800));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(null);
		
		JScrollPane scrollPanel = new JScrollPane();
        scrollPanel.setBounds(10, 169, 780, 302);
        panel.add(scrollPanel);
        
        JTextArea textAreaListado = new JTextArea();
        scrollPanel.setViewportView(textAreaListado);
        
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(20, 507, 90, 13);
		panel.add(lblUsuario);
		lblUsuario.setVisible(usuario instanceof Analista);

		JTextField txtUsuario = new JTextField();
		txtUsuario.setBounds(20, 520, 360, 19);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		txtUsuario.setVisible(usuario instanceof Analista);
		
		JButton btnListarConstancias = new JButton("Listar constancias");
		btnListarConstancias.setBounds(87, 481, 189, 19);
		btnListarConstancias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Borro lo previamente cargado en el textfield
        		textAreaListado.setText(null);
        		
        		String user = null;
        		if (usuario instanceof Estudiante) {
        			user = usuario.getUsuario();
        		} else {
        			user = txtUsuario.getText();
        		}
        		
        		try {
        			List<Constancia> constancias = constanciaBeanRemote.listadoConstancias(user);
        			
        			for(int i=0; i < constancias.size(); i++) {
        				String texto = textAreaListado.getText();
        				String textoConstancia = constancias.get(i).getDetalle() + " " + constancias.get(i).getEstudianteId().getUsuario();
        				textAreaListado.setText(texto + textoConstancia + "\n");
        			}
        		} catch (ServiciosException se) {
        			textAreaListado.setText(se.getMessage());
        		}
        		
        		
			}
			
		});
		panel.add(btnListarConstancias);
		
		
		JButton btnSolicitarConstancia = new JButton("Solicitar constancia");
		btnSolicitarConstancia.setBounds(286, 481, 189, 19);
		btnSolicitarConstancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIAltaConstancia altaConstancia = new UIAltaConstancia();
				altaConstancia.inicializar(usuario);
				altaConstancia.frame.setVisible(true);
			}
			
		});
		btnSolicitarConstancia.setVisible(usuario instanceof Estudiante);
		panel.add(btnSolicitarConstancia);
		
		JButton btnModificarConstancia = new JButton("Ver constancia");
		btnModificarConstancia.setBounds(485, 481, 189, 19);
		btnModificarConstancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//Llamar a panel de modificacion de constancia
				
				//Tiene botón de modificar sólo si es estudiante
			}
			
		});
		panel.add(btnModificarConstancia);
		
		JButton btnModificarEstadoConstancia = new JButton("Modificar estado constancia");
		btnModificarEstadoConstancia.setBounds(390, 520, 189, 19);
		btnModificarEstadoConstancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//Llamar a panel de modificacion de estado constancia
			}
			
		});
		btnModificarEstadoConstancia.setVisible(usuario instanceof Analista);
		panel.add(btnModificarEstadoConstancia);
		
		JButton btnBorrarConstancia = new JButton("Borrar constancia");
		btnBorrarConstancia.setBounds(589, 520, 189, 19);
		btnBorrarConstancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//dialogo si desea borrar y luego llama a borrar
			}
			
		});
		btnBorrarConstancia.setVisible(usuario instanceof Estudiante);
		panel.add(btnBorrarConstancia);
	}
}
