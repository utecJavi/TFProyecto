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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.Rol;
import tecnofenix.entidades.Usuario;





public class UIRol {

	public JFrame frame;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtDesc;
	private List<Rol> listRoles;
	EJBUsuarioRemoto usuarioRemote;
	JTable table;
	DefaultTableModel modelo;
	Object[] fila;

	/**
	 * @wbp.parser.entryPoint
	 * 
	 */
	public void inicializar(Usuario user) {

		usuarioRemote = new EJBUsuarioRemoto();

		frame = new JFrame("Administracion de Roles");

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
		listRoles = usuarioRemote.listarRoles();

		for (Rol rol : listRoles) {

			fila[0] = rol.getId();
			fila[1] = rol.getNombre();
			fila[2] = rol.getDescripcion();
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
		table.getColumnModel().getColumn(0).setPreferredWidth(20); // Define el ancho preferido de la columna 0
		table.getColumnModel().getColumn(1).setPreferredWidth(360); // Define el ancho preferido de la columna 1
		table.getColumnModel().getColumn(2).setPreferredWidth(400); 
		
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

					String mensaje="Id "+modelo.getValueAt(row, 0).toString() +" Nombre "+modelo.getValueAt(row, 1).toString() +" Desc "+modelo.getValueAt(row, 2).toString();

					if (borrarRow(mensaje)) {
						usuarioRemote.borrarRol(listRoles.get(row));
						modelo.removeRow(row);
						
					}
				}else {
					JOptionPane.showMessageDialog(null, "Primero debe seleccionar un registro", "Seleccione con click en la tabla",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnBorrar.setBounds(40, 351, 85, 21);
		panel.add(btnBorrar);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
	        public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
				System.out.println("Buscando rol seleccionado... ");
				txtId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				txtNombre.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				txtDesc.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				}
			}
	    });
		
		frame.pack();
		frame.setVisible(true);

	}

	public void limpiar() {
		txtId.setText("");
		txtNombre.setText("");
		txtDesc.setText("");

	}

	public boolean validarDatos() {
		System.out.println("Validando datos UIRol");

		if (txtNombre.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "El nombre del rol no puede ser vacío", "Datos invalidos",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (txtDesc.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "La descripcion del rol no puede ser vacío", "Datos invalidos",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		} 
			
		

		return true;
	}
		public void addRol() {

				Rol rolTemp = new Rol();
				if(txtId.getText()!= null && txtId.getText()!="" ) {
					rolTemp.setId(Integer.valueOf(txtId.getText()));
				}
				rolTemp.setNombre(txtNombre.getText());
				rolTemp.setDescripcion(txtDesc.getText());
				rolTemp= usuarioRemote.crearRol(rolTemp);
				if (rolTemp != null && rolTemp.getId() != null) {

					listRoles.add(rolTemp);
					fila[0] = rolTemp.getId();
					fila[1] = rolTemp.getNombre();
					fila[2] = rolTemp.getDescripcion();
					modelo.addRow(fila);
					
					JOptionPane.showMessageDialog(null, "La funcionalidad fue creada/editada con exito", "Datos invalidos",
							JOptionPane.INFORMATION_MESSAGE);
					
				} else {
					JOptionPane.showMessageDialog(null, "Hubo un erro ingresando la funcionalidad", "Datos invalidos",
							JOptionPane.INFORMATION_MESSAGE);
				}
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
		
		
		
	
		
}
