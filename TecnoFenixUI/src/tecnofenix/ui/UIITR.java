package tecnofenix.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.Itr;


import java.awt.Component;

public class UIITR {

	public JFrame frame;
	private JTable table;
	private DefaultTableModel modelo;
	private Object[] fila;
	private List<Itr> allITR;
	private EJBUsuarioRemoto usuarioRemote;
	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {
		usuarioRemote = new EJBUsuarioRemoto();
		frame = new JFrame("ITR");

		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(800, 800));
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(10, 169, 402, 302);
		panel.add(scrollPane);

		modelo = new DefaultTableModel();

		// se crea la Tabla con el modelo DefaultTableModel
		table = new JTable(modelo);
		table.setDefaultEditor(Object.class, null);
		table.setCellSelectionEnabled(true);
		table.setForeground(Color.GREEN);
		table.setColumnSelectionAllowed(true);
		table.setBackground(Color.BLACK);
		// crea un array que contiene los nombre de las columnas
		final String[] columnNames = {  "Id", "Departamento", "Nombre"};

		// insertamos las columnas
		for (int column = 0; column < columnNames.length; column++) {
			// agrega las columnas a la tabla
			modelo.addColumn(columnNames[column]);
		}

		// Se crea un array que será una de las filas de la tabla.
		fila = new Object[columnNames.length];

		actualizarLista();

		// se define el tamaño de la tabla
		table.setBounds(93, 215, 100, 100);
		// Creamos un JscrollPane y le agregamos la JTable
		scrollPane.setBounds(10, 169, 780, 302);
		// definimos un layout
		// Agregamos el JScrollPane al contenedor
		panel.add(scrollPane);
		
		frame.pack();
		frame.setVisible(true);

	}

	public void actualizarLista() {

		if (allITR != null && !allITR.isEmpty() && allITR.size() > 0)
			allITR.clear();
		for (int i = 0; i < modelo.getRowCount(); i++) {
			modelo.removeRow(i);
		}
		allITR = usuarioRemote.listarITR();
		// Se rellena cada posición del array con una de las columnas de la tabla en
		// base de datos.
		for (Itr itr : allITR) {
			
				fila[0] = itr.getId();
				fila[1] = itr.getNombre();
				fila[2] = itr.getDepartamento();
				// Se añade al modelo la fila completa.
				modelo.addRow(fila);
			
		}
	}
}
