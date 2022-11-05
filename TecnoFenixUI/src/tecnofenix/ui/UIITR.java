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
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UIITR {

	public JFrame frame;
	private JTable table;
	private DefaultTableModel modelo;
	private Object[] fila;
	private List<Itr> allITR;
	private EJBUsuarioRemoto usuarioRemote;
	private JTextField textNombre;
	private JTextField textDepartamento;

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

		modelo = new DefaultTableModel();

		// se crea la Tabla con el modelo DefaultTableModel
		table = new JTable(modelo);
		table.setDefaultEditor(Object.class, null);
		table.setCellSelectionEnabled(true);
		table.setForeground(Color.GREEN);
		table.setColumnSelectionAllowed(true);
		table.setBackground(Color.BLACK);
		// crea un array que contiene los nombre de las columnas
		final String[] columnNames = { "Id", "Nombre", "Departamento" };

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
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 169, 780, 302);
		// definimos un layout
		// Agregamos el JScrollPane al contenedor
		panel.add(scrollPane);

		JButton btnAddItr = new JButton("Nuevo Itr");
		btnAddItr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevoItr();
			}
		});
		btnAddItr.setBounds(677, 481, 113, 21);
		panel.add(btnAddItr);

		textNombre = new JTextField();
		textNombre.setBounds(10, 494, 188, 19);
		panel.add(textNombre);
		textNombre.setColumns(10);

		textDepartamento = new JTextField();
		textDepartamento.setBounds(208, 494, 188, 19);
		panel.add(textDepartamento);
		textDepartamento.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(10, 481, 74, 13);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Departamento");
		lblNewLabel_2.setBounds(208, 481, 113, 13);
		panel.add(lblNewLabel_2);

		frame.pack();
		frame.setVisible(true);

	}

	public void actualizarLista() {
		System.out.println("Entrando a cargar la lista de ITRs actualizarLista UI");
		if (allITR != null && !allITR.isEmpty() && allITR.size() > 0) {
			allITR.clear();
			for (int i = 0; i < modelo.getRowCount(); i++) {
				modelo.removeRow(i);
			}
		}
		System.out.println("usuarioRemote.listarITR()");
		allITR = usuarioRemote.listarITR();
		System.out.println(allITR.toString());
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

	public boolean nuevoItr() {
		if (textNombre.getText().equals("") && textDepartamento.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Ingrese nombre de ITR y departamento validos", "Intente nuevamente",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			Itr itr = new Itr();
			itr.setId(null);
			itr.setNombre(textNombre.getText());
			itr.setDepartamento(textDepartamento.getText());
			itr = usuarioRemote.crearITR(itr);
			if (itr.getId() != null) {
				actualizarLista();
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "No se pudo crear el ITR", "Intente nuevamente",
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}

		return false;
	}

}
