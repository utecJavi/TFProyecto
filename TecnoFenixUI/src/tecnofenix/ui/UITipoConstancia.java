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
import tecnofenix.entidades.TipoConstancia;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tecnofenix.interfaces.ConstanciaBeanRemote;
import tecnofenix.exception.ServiciosException;


public class UITipoConstancia {


	public JFrame frame;
	
	@EJB
	ConstanciaBeanRemote constanciaBeanRemote;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {

		
		try {
			InitialContext ctx = new InitialContext();
			constanciaBeanRemote = (ConstanciaBeanRemote) ctx.lookup("ejb:/TecnoFenixEJB/ConstanciaBean!tecnofenix.interfaces.ConstanciaBeanRemote");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}

		frame = new JFrame("Tipo de constancias");
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
		
		final String[] columnNames = {"Id","Tipo"};
		for (int column = 0; column < columnNames.length; column++) {
			modelo.addColumn(columnNames[column]);
		}

		Object[] filas = new Object[columnNames.length];
		
		JScrollPane scrollPanel = new JScrollPane(table);
        scrollPanel.setBounds(10, 169, 780, 302);
        panel.add(scrollPanel);
        
        JLabel lblNuevoTipo = new JLabel("Nuevo Tipo:");
        lblNuevoTipo.setBounds(20, 507, 90, 13);
		panel.add(lblNuevoTipo);
		
		JTextField txtNuevoTipo = new JTextField();
		txtNuevoTipo.setBounds(20, 520, 360, 19);
		panel.add(txtNuevoTipo);
		txtNuevoTipo.setColumns(10);
		
		
		JButton btnListarTipoConstancias = new JButton("Listar tipos");
		btnListarTipoConstancias.setBounds(87, 481, 189, 19);
		btnListarTipoConstancias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Borro lo previamente cargado en la tabla
				modelo.getDataVector().removeAllElements();
				modelo.fireTableDataChanged();
				
        		try {
        			List<TipoConstancia> tipoConstancias = constanciaBeanRemote.listadoTipoConstancia(false);
        			
        			for(TipoConstancia tipo : tipoConstancias) {
        				
        				filas[0] = tipo.getId();
        				filas[1] = tipo.getTipo();
        				
        				modelo.addRow(filas);
        			}
        			
        			autoAjustarTabla(table);
        		} catch (ServiciosException se) {
        			System.out.println("Error al consultar tipos de constancia: " + se.getMessage());
        		}
        		
        		
			}
			
		});
		panel.add(btnListarTipoConstancias);
		
		JButton btnModificarTipoConstancia = new JButton("Modificar tipo");
		btnModificarTipoConstancia.setBounds(286, 481, 189, 19);
		btnModificarTipoConstancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				if (row >= 0) {
					try {
						Integer id = Integer.parseInt(modelo.getValueAt(row, 0).toString());
						String tipo = txtNuevoTipo.getText();
						TipoConstancia tipoConstancia = new TipoConstancia();
						tipoConstancia.setId(id);
						tipoConstancia.setTipo(tipo);
						
						constanciaBeanRemote.modificarTipoConstancia(tipoConstancia);
						
					} catch (ServiciosException | NumberFormatException se) {
	        			System.out.println("Error al modificar tipo de constancia: " + se.getMessage());
	        			JOptionPane.showMessageDialog(null, "Hubo un error al modificar tipo de constancia.", "Error", JOptionPane.ERROR_MESSAGE);
	        		}
					
					JOptionPane.showMessageDialog(null, "Se modificó el tipo de constancia.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					
				}
				
			}
			
		});
		panel.add(btnModificarTipoConstancia);
		
		JButton btnBorrarTipoConstancia = new JButton("Borrar tipo");
		btnBorrarTipoConstancia.setBounds(485, 481, 189, 19);
		btnBorrarTipoConstancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				int row = table.getSelectedRow();
				if (row >= 0) {
					try {
						Integer id = Integer.parseInt(modelo.getValueAt(row, 0).toString());
						String tipo = modelo.getValueAt(row, 1).toString();
						TipoConstancia tipoConstancia = new TipoConstancia();
						tipoConstancia.setId(id);
						tipoConstancia.setTipo(tipo);
						
						constanciaBeanRemote.borrarTipoConstancia(tipoConstancia);
						
					} catch (ServiciosException | NumberFormatException se) {
	        			System.out.println("Error al borrar tipo de constancia: " + se.getMessage());
	        			JOptionPane.showMessageDialog(null, "Hubo un error al borrar tipo de constancia.", "Error", JOptionPane.ERROR_MESSAGE);
	        		}
					
					JOptionPane.showMessageDialog(null, "Se borró el tipo de constancia.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					
				}
			}
			
		});
		panel.add(btnBorrarTipoConstancia);
		
		JButton btnAltaTipoConstancia = new JButton("Alta tipo");
		btnAltaTipoConstancia.setBounds(390, 520, 189, 19);
		btnAltaTipoConstancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					
					String tipo = txtNuevoTipo.getText();
					
					TipoConstancia tipoConstancia = new TipoConstancia();
					tipoConstancia.setTipo(tipo);
					tipoConstancia.setBaja(false);
					
					constanciaBeanRemote.crearTipoConstancia(tipoConstancia);
					
				} catch (ServiciosException se) {
        			System.out.println("Error al consultar constancia: " + se.getMessage());
        			JOptionPane.showMessageDialog(null, "Hubo un error al ingresar tipo de constancia.", "Error", JOptionPane.ERROR_MESSAGE);
        		}
				
				JOptionPane.showMessageDialog(null, "Se ingresó el tipo de constancia.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					
			}
			
		});
		panel.add(btnAltaTipoConstancia);
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
