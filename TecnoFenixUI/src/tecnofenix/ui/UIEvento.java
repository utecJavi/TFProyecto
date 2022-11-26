package tecnofenix.ui;




import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.Evento;

public class UIEvento {


	public JFrame frame;
	private JTable tablaUsuarios;
	private EJBUsuarioRemoto ejb;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {


		ejb = new EJBUsuarioRemoto();
		frame = new JFrame("Listado de eventos");

		JPanel panel = new JPanel();

		panel.setPreferredSize(new Dimension(800, 800));
		frame.getContentPane().add(panel);
	
		panel.setLayout(null);
		
		DefaultTableModel tableModel = new DefaultTableModel(new String[] {"ID", "INICIO", "FIN"}, 0) {
			 @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			 }
		};
		generateRows(tableModel);
		tablaUsuarios = new JTable();
		tablaUsuarios.setModel(tableModel);
		tablaUsuarios.setBounds(20, 11, 770, 271);
		panel.add(tablaUsuarios);

		frame.pack();
	}
	
	private void generateRows(DefaultTableModel tableModel) {
		List<Evento> eventos = ejb.obtenerEventos();
		for (Evento evento : eventos) {
			Vector<String> row = new Vector<String>(3);
			row.add(evento.getId().toString());
			row.add(evento.getInicio().toString());
			row.add(evento.getFin().toString());
			tableModel.addRow(row);
		}
	}
}
