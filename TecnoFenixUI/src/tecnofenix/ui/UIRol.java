package tecnofenix.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;





public class UIRol {
/*
	public JFrame frame;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtDesc;
	private DAORol daoRol;
	private List<Rol> allRol;
	MensajePopUp msj = new MensajePopUp();
	JTable table;
	DefaultTableModel modelo;
	Object[] fila;

	/**
	 * @wbp.parser.entryPoint
	 */
	/*
	public void inicializar() {

		this.daoRol = new DAORol();

		frame = new JFrame("Nombre de la funcionalidad de la UI");

		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(800, 400));// changed it to preferredSize, Thanks!
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(null);

		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(20, 325, 45, 13);
		panel.add(lblId);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(135, 325, 45, 13);
		panel.add(lblNombre);

		JLabel lblDesc = new JLabel("Descripcion");
		lblDesc.setBounds(333, 325, 78, 13);
		panel.add(lblDesc);

		txtId = new JTextField();
		txtId.setBounds(47, 322, 78, 19);
		panel.add(txtId);
		txtId.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(190, 322, 133, 19);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtDesc = new JTextField();
		txtDesc.setBounds(421, 322, 271, 19);
		panel.add(txtDesc);
		txtDesc.setColumns(10);

		JButton btnAdd = new JButton("Agregar");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarDatos();
			}
		});
		btnAdd.setBounds(705, 321, 85, 21);
		panel.add(btnAdd);

		modelo = new DefaultTableModel();

		// se crea la Tabla con el modelo DefaultTableModel
		table = new JTable(modelo);
		table.setDefaultEditor(Object.class, null);
		table.setCellSelectionEnabled(true);
		table.setForeground(Color.GREEN);
		table.setColumnSelectionAllowed(true);
		table.setBackground(Color.BLACK);
		// crea un array que contiene los nombre de las columnas
		final String[] columnNames = { "Id", "Nombre", "Descripcion" };
		// insertamos las columnas
		for (int column = 0; column < columnNames.length; column++) {
			// agrega las columnas a la tabla
			modelo.addColumn(columnNames[column]);
		}

		// Se crea un array que será una de las filas de la tabla.
		fila = new Object[columnNames.length];
		// Se rellena cada posición del array con una de las columnas de la tabla en
		// base de datos.
		allRol = daoRol.getAll();

		for (Rol fun : allRol) {

			fila[0] = fun.getId();
			fila[1] = fun.getNombre();
			fila[2] = fun.getDescripcion();
			modelo.addRow(fila);
		}

		// Se añade al modelo la fila completa.

		// se define el tamaño de la tabla
//		table.setPreferredScrollableViewportSize(new Dimension(99, 99));
		table.setBounds(93, 215, 100, 100);
		// Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setSize(780, 302);
		scrollPane.setLocation(10, 10);
		// definimos un layout
		// Agregamos el JScrollPane al contenedor
		panel.add(scrollPane);

		JButton btnGuardar = new JButton("Limpiar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		btnGuardar.setBounds(705, 352, 85, 21);
		panel.add(btnGuardar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = table.getSelectedRow();

				if (row >= 0) {
//					System.out.println(modelo.getValueAt(row, column));
					String mensaje="Id "+modelo.getValueAt(row, 0).toString() +" Nombre "+modelo.getValueAt(row, 1).toString() +" Desc "+modelo.getValueAt(row, 2).toString();

					if (borrarRow(mensaje)) {
						daoRol.delete(allRol.get(row));
						modelo.removeRow(row);
						
					}
				}
			}
		});
		btnBorrar.setBounds(40, 351, 85, 21);
		panel.add(btnBorrar);

		frame.pack();
		frame.setVisible(true);

	}

	public void limpiar() {
		txtId.setText("");
		txtNombre.setText("");
		txtDesc.setText("");

	}

	public void validarDatos() {
		System.out.println("Validando datos UIRol");
		if (txtId.getText().equals("")) {
			msj.mostrarMensaje(Mensajes.ALTA_FUNCIONALIDAD_ID);
			txtId.setText(String.valueOf(daoRol.maxId()+1));
		} else {
			if (validarId(Integer.valueOf(txtId.getText()))) {

				if (txtNombre.getText().equals("")) {
					msj.mostrarMensaje(Mensajes.ALTA_FUNCIONALIDAD_NOMBRE);
				} else {
					if (txtDesc.getText().equals("")) {
						msj.mostrarMensaje(Mensajes.ALTA_FUNCIONALIDAD_DESC);
					} else {
						addRol();
					}
				}
			}
		}
	}
		public void addRol() {

			Rol funTemp = new Rol(Integer.valueOf(txtId.getText()), txtNombre.getText(),txtDesc.getText());
			if (daoRol.insert(funTemp) != null) {

				allRol.add(funTemp);
				fila[0] = funTemp.getId();
				fila[1] = funTemp.getNombre();
				fila[2] = funTemp.getDescripcion();
				modelo.addRow(fila);
				
				msj.mostrarMensaje(Mensajes.ALTA_FUNCIONALIDAD_EXITO);
				
			} else {
				msj.mostrarMensaje(Mensajes.ALTA_FUNCIONALIDAD_ERRORINSERTANDO);
			}
		}

		public boolean validarId(Integer id) {
			if (daoRol.maxId()+1 == id) {
				return true;
			}
			msj.mostrarMensaje(Mensajes.ALTA_FUNCIONALIDAD_IDREPETIDO);
			return false;
		}

		
		public boolean borrarRow(String mensaje) {
//			msj.mostrarMensaje(Mensajes.BAJA);
			
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Seguro quieres borrar: "+mensaje,"Warning",dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION){
				return true;
			}
			return false;
		}
		
		
		
		
		public DAORol getDaoRol() {
			return daoRol;
		}

		public void setDaoRol(DAORol daoRol) {
			this.daoRol = daoRol;
		}
		*/
}
