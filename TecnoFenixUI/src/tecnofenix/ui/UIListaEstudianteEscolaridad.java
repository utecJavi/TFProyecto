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
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tecnofenix.interfaces.EstudianteBeanRemote;
import tecnofenix.exception.ServiciosException;


public class UIListaEstudianteEscolaridad {


	public JFrame frame;
	
	@EJB
	EstudianteBeanRemote estudianteBeanRemote;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {

		
		try {
			InitialContext ctx = new InitialContext();
			estudianteBeanRemote = (EstudianteBeanRemote) ctx.lookup("ejb:/TecnoFenixEJB/EstudianteBean!tecnofenix.interfaces.EstudianteBeanRemote");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}

		frame = new JFrame("Listado de estudiantes para escolaridad");
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
		
		final String[] columnNames = {"Id","Usuario", "Nombre", "Apellido", "Documento"};
		for (int column = 0; column < columnNames.length; column++) {
			modelo.addColumn(columnNames[column]);
		}

		Object[] filas = new Object[columnNames.length];
		
		JScrollPane scrollPanel = new JScrollPane(table);
        scrollPanel.setBounds(10, 169, 780, 302);
        panel.add(scrollPanel);
        
		JButton btnListarEstudiantes = new JButton("Listar estudiantes");
		btnListarEstudiantes.setBounds(87, 481, 189, 19);
		btnListarEstudiantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Borro lo previamente cargado en la tabla
				modelo.getDataVector().removeAllElements();
				modelo.fireTableDataChanged();
				
        		try {
        			List<Estudiante> estudiantes = estudianteBeanRemote.listarEstudiantes();
        			
        			for(Estudiante estudiante : estudiantes) {
        				
        				filas[0] = estudiante.getId();
        				filas[1] = estudiante.getUsuario();
        				filas[2] = estudiante.getNombres();
        				filas[3] = estudiante.getApellidos();
        				filas[4] = estudiante.getDocumento();
        				
        				modelo.addRow(filas);
        			}
        			
        			autoAjustarTabla(table);
        		} catch (ServiciosException se) {
        			System.out.println("Error al consultar estudiantes: " + se.getMessage());
        		}
			}
			
		});
		panel.add(btnListarEstudiantes);		
		
		JButton btnVerEscolaridad = new JButton("Ver escolaridad");
		btnVerEscolaridad.setBounds(286, 481, 189, 19);
		btnVerEscolaridad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row >= 0) {
					try {
						Integer id = Integer.parseInt(modelo.getValueAt(row, 0).toString());
						Estudiante estudiante = estudianteBeanRemote.buscarEstudiantePorId(id);
						
						UIEscolaridad verEscolaridad = new UIEscolaridad();
						verEscolaridad.inicializar(estudiante);
						verEscolaridad.frame.setVisible(true);
						
					} catch (ServiciosException | NumberFormatException se) {
	        			System.out.println("Error al consultar constancia: " + se.getMessage());
	        		}
					
				}
			}
			
		});
		panel.add(btnVerEscolaridad);
		
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
