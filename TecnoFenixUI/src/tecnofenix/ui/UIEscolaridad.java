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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.EscolaridadDTO;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tecnofenix.interfaces.EstudianteBeanRemote;
import tecnofenix.exception.ServiciosException;

public class UIEscolaridad {


	public JFrame frame;
	
	@EJB
	EstudianteBeanRemote estudianteBeanRemote;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar(Estudiante estudiante) {
		
		try {
			InitialContext ctx = new InitialContext();
			estudianteBeanRemote = (EstudianteBeanRemote) ctx.lookup("ejb:/TecnoFenixEJB/EstudianteBean!tecnofenix.interfaces.EstudianteBeanRemote");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
		
		frame = new JFrame("Escolaridad");

		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(800, 800));
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(null);
		
		JLabel lblNombreEstudiante = new JLabel("Escolaridad del estudiante " + estudiante.getNombres() + " " + estudiante.getApellidos());
		lblNombreEstudiante.setBounds(150, 50, 500, 20);
		panel.add(lblNombreEstudiante);
		
		DefaultTableModel modelo = new DefaultTableModel();

		JTable table = new JTable(modelo);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setForeground(Color.GREEN);
		table.setBackground(Color.BLACK);
		table.setBounds(93, 215, 100, 100);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		final String[] columnNames = {"Evento","Tipo","Modalidad","Fecha","ITR","Calificacion"};
		for (int column = 0; column < columnNames.length; column++) {
			modelo.addColumn(columnNames[column]);
		}

		Object[] filas = new Object[columnNames.length];
		
		JScrollPane scrollPanel = new JScrollPane(table);
        scrollPanel.setBounds(10, 169, 780, 302);
        panel.add(scrollPanel);
        
        cargarTabla(modelo, estudiante, filas);
        
        autoAjustarTabla(table);
		
        JButton btnExportarEscolaridad = new JButton("Exportar escolaridad a PDF");
        btnExportarEscolaridad.setBounds(286, 481, 250, 19);
        btnExportarEscolaridad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Exportar a PDF!!!!!!!!!!!!!
				
			}
			
		});
		panel.add(btnExportarEscolaridad);        
		
	}
	
	private void cargarTabla(DefaultTableModel modelo, Estudiante estudiante, Object[] filas) {
		//Borro lo previamente cargado en la tabla
		modelo.getDataVector().removeAllElements();
		modelo.fireTableDataChanged();
		
		try {
			List<EscolaridadDTO> escolaridadDTOs = estudianteBeanRemote.obtenerEscolaridad(estudiante.getId());
			
			for(EscolaridadDTO escolaridadDTO : escolaridadDTOs) {
				
				filas[0] = escolaridadDTO.getEvento();
				filas[1] = escolaridadDTO.getTipo();
				filas[2] = escolaridadDTO.getModalidad();
				filas[3] = escolaridadDTO.getFecha();
				filas[4] = escolaridadDTO.getItr();
				filas[5] = escolaridadDTO.getCalificacion();
				
				modelo.addRow(filas);
			}
			
		} catch (ServiciosException se) {
			System.out.println("Error al consultar escolaridad: " + se.getMessage());
		}
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
