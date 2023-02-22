package tecnofenix.ui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

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
import tecnofenix.entidades.Constancia.EstadoConstancia;
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
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		DefaultTableModel modelo = new DefaultTableModel();

		JTable table = new JTable(modelo);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setForeground(Color.GREEN);
		table.setBackground(Color.BLACK);
		table.setBounds(93, 215, 100, 100);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setDefaultEditor(Object.class, null);
		final String[] columnNames = {"Id","Detalle", "Evento", "Estudiante", "Estado"};
		for (int column = 0; column < columnNames.length; column++) {
			modelo.addColumn(columnNames[column]);
		}

		Object[] filas = new Object[columnNames.length];
		
		JScrollPane scrollPanel = new JScrollPane(table);
        scrollPanel.setBounds(10, 169, 780, 302);
        panel.add(scrollPanel);
        
        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(20, 507, 90, 13);
		panel.add(lblEstado);
		
		JComboBox<String> comboEstados = new JComboBox<String>();
		comboEstados.setBounds(20, 520, 360, 21);
		comboEstados.addItem("");
		comboEstados.addItem("INGRESADO");
		comboEstados.addItem("EN_PROCESO");
		comboEstados.addItem("FINALIZADO");
		comboEstados.setSelectedItem("");
		panel.add(comboEstados);
		
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(20, 560, 90, 13);
		panel.add(lblUsuario);
		lblUsuario.setVisible(usuario instanceof Analista);

		JTextField txtUsuario = new JTextField();
		txtUsuario.setBounds(20, 573, 360, 19);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		txtUsuario.setVisible(usuario instanceof Analista);
		
		JButton btnListarConstancias = new JButton("Listar constancias");
		btnListarConstancias.setBounds(87, 481, 189, 19);
		btnListarConstancias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Borro lo previamente cargado en la tabla
				modelo.getDataVector().removeAllElements();
				modelo.fireTableDataChanged();
				
        		String user = null;
        		if (usuario instanceof Estudiante) {
        			user = usuario.getUsuario();
        		} else {
        			user = txtUsuario.getText();
        		}
        		
        		String estado = null;
        		if (comboEstados.getSelectedItem().toString() != "") {
        			estado = comboEstados.getSelectedItem().toString();
        		}
        		
        		try {
        			List<Constancia> constancias = constanciaBeanRemote.listadoConstancias(user, estado);
        			
        			for(Constancia constancia : constancias) {
        				
        				filas[0] = constancia.getId();
        				filas[1] = constancia.getDetalle();
        				filas[2] = constancia.getEventoId();
        				filas[3] = constancia.getEstudianteId().getUsuario();
        				filas[4] = constancia.getEstado();
        				
        				modelo.addRow(filas);
        			}
        			
        			autoAjustarTabla(table);
        		} catch (ServiciosException se) {
        			System.out.println("Error al consultar constancia: " + se.getMessage());
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
			
				int row = table.getSelectedRow();
				if (row >= 0) {
					try {
						Integer id = Integer.parseInt(modelo.getValueAt(row, 0).toString());
						Constancia constancia = constanciaBeanRemote.buscarConstancia(id);
						
						UIModificarConstancia verConstancia = new UIModificarConstancia();
						verConstancia.inicializar(usuario, constancia);
						verConstancia.frame.setVisible(true);
						
					} catch (ServiciosException | NumberFormatException se) {
	        			System.out.println("Error al consultar constancia: " + se.getMessage());
	        		}
					
				}
			}
			
		});
		panel.add(btnModificarConstancia);
		
		JButton btnBorrarConstancia = new JButton("Borrar constancia");
		btnBorrarConstancia.setBounds(390, 520, 189, 19);
		btnBorrarConstancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				int row = table.getSelectedRow();
				if (row >= 0) {
					try {
						Integer id = Integer.parseInt(modelo.getValueAt(row, 0).toString());
						Constancia constancia = constanciaBeanRemote.buscarConstancia(id);
						
						constanciaBeanRemote.borrarConstancia(constancia);
						
						//Borro lo previamente cargado en la tabla
						modelo.getDataVector().removeAllElements();
						modelo.fireTableDataChanged();
						
					} catch (ServiciosException | NumberFormatException se) {
	        			System.out.println("Error al consultar constancia: " + se.getMessage());
	        			JOptionPane.showMessageDialog(null, "Hubo un error al borrar la constancia.", "Error", JOptionPane.ERROR_MESSAGE);
	        		}
					
					JOptionPane.showMessageDialog(null, "Se borró la constancia.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					
				}
			}
			
		});
		btnBorrarConstancia.setVisible(usuario instanceof Estudiante);
		panel.add(btnBorrarConstancia);
		
		JButton btnGestionTipos = new JButton("Gestionar tipos");
		btnGestionTipos.setBounds(390, 573, 189, 19);
		btnGestionTipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UITipoConstancia tiposConstancia = new UITipoConstancia();
				tiposConstancia.inicializar();
				tiposConstancia.frame.setVisible(true);
			}
			
		});
		btnGestionTipos.setVisible(usuario instanceof Analista);
		panel.add(btnGestionTipos);
		
		frame.pack();
	}
	
	private void autoAjustarTabla(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 15;
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width + 1 , width);
	        }
	        
	        if (width > 300)
	            width=300;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
	
}
