package tecnofenix.ui;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.Departamentos;
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Funcionalidad;
import tecnofenix.entidades.TipoGenero;
import tecnofenix.entidades.TipoTutorEncargado;
import tecnofenix.entidades.Tutor;
import tecnofenix.entidades.Usuario;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UIFuncionalidad {

	public JFrame frame;
	private JTextField txtNombre;
	private JTextField txtDesc;
	private Funcionalidad funcionalidad;
	private EJBUsuarioRemoto usuarioRemote;
	private List<Funcionalidad> allFuncionalidad;
	JTable table;
	DefaultTableModel modelo;
	Object[] fila;
	private JTextField txtId;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar(Usuario usr) {

		funcionalidad = new Funcionalidad();
		usuarioRemote = new EJBUsuarioRemoto();
		
		frame = new JFrame("Nombre de la funcionalidad de la UI");

		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(800, 400));// changed it to preferredSize, Thanks!
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(135, 325, 72, 13);
		panel.add(lblNombre);

		JLabel lblDesc = new JLabel("Descripcion");
		lblDesc.setBounds(333, 325, 78, 13);
		panel.add(lblDesc);

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
				if(validarDatos()) {
					addFuncionalidad();
				}
			}
		});
		btnAdd.setBounds(705, 321, 85, 21);
		panel.add(btnAdd);

		modelo = new DefaultTableModel();

		// se crea la Tabla con el modelo DefaultTableModel
		table = new JTable(modelo);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
//		table.setDefaultEditor(Object.class, null);
//		table.setCellSelectionEnabled(true);
		table.setForeground(Color.GREEN);
//		table.setColumnSelectionAllowed(true);
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
		allFuncionalidad = usuarioRemote.listarFuncionalidades();

		for (Funcionalidad fun : allFuncionalidad) {

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
		
		JButton btnBorrar = new JButton("Borrar seleccionado");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = table.getSelectedRow();

				if (row >= 0) {
//					System.out.println(modelo.getValueAt(row, column));
					String mensaje="Id "+modelo.getValueAt(row, 0).toString() +" Nombre "+modelo.getValueAt(row, 1).toString() +" Desc "+modelo.getValueAt(row, 2).toString();

					if (borrarRow(mensaje)) {
						usuarioRemote.borrarFuncionalidad(allFuncionalidad.get(row));
						modelo.removeRow(row);
						
					}
				}else {
					JOptionPane.showMessageDialog(null, "Primero debe seleccionar un registro", "Seleccione con click en la tabla",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnBorrar.setBounds(24, 352, 133, 21);
		panel.add(btnBorrar);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(20, 325, 72, 13);
		panel.add(lblId);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBounds(45, 322, 80, 19);
		panel.add(txtId);

		
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
	        public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
				System.out.println("Buscando funcionalidad seleccionado... ");
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
		System.out.println("Validando datos UIFuncionalidad");

		if (txtNombre.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "El nombre de la funcionalidad no puede ser vacío", "Datos invalidos",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (txtDesc.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "La descripcion de la funcionalidad no puede ser vacía", "Datos invalidos",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		} 
			
		

		return true;
	}

	public void addFuncionalidad() {

		Funcionalidad funTemp = new Funcionalidad();
		if(txtId.getText()!= null && txtId.getText()!="" ) {
			funTemp.setId(Long.valueOf(txtId.getText()));
		}
		funTemp.setNombre(txtNombre.getText());
		funTemp.setDescripcion(txtDesc.getText());
		funTemp= usuarioRemote.crearFuncionalidad(funTemp);
		if (funTemp != null && funTemp.getId() != null) {

			allFuncionalidad.add(funTemp);
			fila[0] = funTemp.getId();
			fila[1] = funTemp.getNombre();
			fila[2] = funTemp.getDescripcion();
			modelo.addRow(fila);
			
			JOptionPane.showMessageDialog(null, "La funcionalidad fue creada/editada con exito", "Datos invalidos",
					JOptionPane.INFORMATION_MESSAGE);
			
		} else {
			JOptionPane.showMessageDialog(null, "Hubo un erro ingresando la funcionalidad", "Datos invalidos",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	

	
	public boolean borrarRow(String mensaje) {
//		msj.mostrarMensaje(Mensajes.BAJA);
		
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog (null, "Seguro quieres borrar: "+mensaje,"Warning",dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
}
