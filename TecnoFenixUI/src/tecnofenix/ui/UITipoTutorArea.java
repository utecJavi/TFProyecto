package tecnofenix.ui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.TipoArea;
import tecnofenix.entidades.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UITipoTutorArea {

	public JFrame frame;
	private JTable table;
	private DefaultTableModel modelo;
	private Object[] fila;
	private List<TipoArea> listTipoArea;

	private EJBUsuarioRemoto usuarioRemote;
	private JTextField textNombre;
	private JTextField textBuscarNomb;
	private JTextField textBuscarId;
	private JTextField textEditarId;
	private JTextField textEditarNom;
	private JCheckBox chckbxActivos;
	private JCheckBox chckbxTEEActivo;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {
		usuarioRemote = new EJBUsuarioRemoto();
		listTipoArea = new ArrayList<TipoArea>();
		frame = new JFrame("Administracion tipos de areas");

		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(650, 700));
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(null);

		modelo = new DefaultTableModel();

		// se crea la Tabla con el modelo DefaultTableModel
		table = new JTable(modelo);
		table.setDefaultEditor(Object.class, null);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setForeground(Color.GREEN);
		table.setColumnSelectionAllowed(false);
		table.setBackground(Color.BLACK);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// crea un array que contiene los nombre de las columnas
		final String[] columnNames = { "Id", "Nombre","Activo"};

		// insertamos las columnas
		for (int column = 0; column < columnNames.length; column++) {
			// agrega las columnas a la tabla
			modelo.addColumn(columnNames[column]);
		}

		// Se crea un array que ser� una de las filas de la tabla.
		fila = new Object[columnNames.length];

		actualizarLista();
		// se define el tama�o de la tabla
		table.setBounds(93, 215, 100, 100);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
					public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
				System.out.println("ACTUALIZANDO CLICK");
							textEditarId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
							textEditarNom.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
							
							if(table.getValueAt(table.getSelectedRow(), 2).toString()== "Si") {
								chckbxTEEActivo.setSelected(true);
				            }
				            if(table.getValueAt(table.getSelectedRow(), 2).toString()== "No") {
				            	chckbxTEEActivo.setSelected(false);
				            }
				}
					}
			});
		// Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 103, 612, 368);
		// definimos un layout
		// Agregamos el JScrollPane al contenedor
		panel.add(scrollPane);

		JButton btnAddTipoArea = new JButton("Nuevo tipo area");
		btnAddTipoArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevoTipoArea();
			}
		});
		btnAddTipoArea.setBounds(431, 495, 191, 21);
		panel.add(btnAddTipoArea);

		textNombre = new JTextField();
		textNombre.setBounds(208, 496, 188, 19);
		panel.add(textNombre);
		textNombre.setColumns(10);


		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(208, 483, 74, 13);
		panel.add(lblNewLabel_1);


		JLabel lblNewLabel_1_1 = new JLabel("Nombre");
		lblNewLabel_1_1.setBounds(208, 48, 74, 13);
		panel.add(lblNewLabel_1_1);


		textBuscarNomb = new JTextField();
		textBuscarNomb.setColumns(10);
		textBuscarNomb.setBounds(208, 61, 188, 19);
		panel.add(textBuscarNomb);

		JLabel lblNewLabel_1_1_1 = new JLabel("Id");
		lblNewLabel_1_1_1.setBounds(10, 48, 74, 13);
		panel.add(lblNewLabel_1_1_1);

		textBuscarId = new JTextField();
		textBuscarId.setColumns(10);
		textBuscarId.setBounds(10, 61, 188, 19);
		textBuscarId.setText("");
		panel.add(textBuscarId);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				buscarPor(textBuscarId.getText() ,textBuscarNomb.getText());
			}
		});
		btnBuscar.setBounds(509, 60, 113, 21);
		panel.add(btnBuscar);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 90, 612, 3);
		panel.add(separator);

		JLabel lblNewLabel = new JLabel("Filtros de busqueda");
		lblNewLabel.setBounds(10, 10, 113, 13);
		panel.add(lblNewLabel);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 556, 612, 3);
		panel.add(separator_1);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Id");
		lblNewLabel_1_1_1_1_1.setBounds(10, 569, 74, 13);
		panel.add(lblNewLabel_1_1_1_1_1);

		textEditarId = new JTextField();
		textEditarId.setText("");
		textEditarId.setEnabled(false);
		textEditarId.setColumns(10);
		textEditarId.setBounds(10, 582, 188, 19);
		panel.add(textEditarId);

		JLabel lblNewLabel_1_2 = new JLabel("Nombre");
		lblNewLabel_1_2.setBounds(208, 569, 74, 13);
		panel.add(lblNewLabel_1_2);

		textEditarNom = new JTextField();
		textEditarNom.setColumns(10);
		textEditarNom.setBounds(208, 582, 188, 19);
		panel.add(textEditarNom);


		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validar()) {
					editar(textEditarId.getText() ,textEditarNom.getText(),chckbxTEEActivo.isSelected());
	
				}
				
			}
		});
		btnEditar.setBounds(527, 581, 113, 21);
		panel.add(btnEditar);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textEditarId.getText().equals("")) {
				if(confirmarSiNo("Seguro quiere eliminar el estado de evento "+textEditarNom.getText())) {
					limpiarTabla();
					TipoArea ta = new TipoArea(Integer.valueOf(textEditarId.getText()), textEditarNom.getText(),chckbxTEEActivo.isSelected());
					ta.setBajaLogica(false);
					ta = usuarioRemote.editarTipoArea(ta);
					JOptionPane.showMessageDialog(null, "TipoArea actualizado",
							"TipoArea actualizado", JOptionPane.INFORMATION_MESSAGE);
					System.out.println(ta.toString());
					actualizarLista();
					limpiar();
				}
				}
			}
		});
		btnBorrar.setBounds(527, 607, 113, 21);
		panel.add(btnBorrar);

		chckbxActivos = new JCheckBox("Activo");
		chckbxActivos.setSelected(true);
		chckbxActivos.setBounds(402, 60, 93, 21);
		panel.add(chckbxActivos);
		
		chckbxTEEActivo = new JCheckBox("Activo");
		chckbxTEEActivo.setEnabled(false);
		chckbxTEEActivo.setSelected(true);
		chckbxTEEActivo.setBounds(428, 581, 93, 21);
		panel.add(chckbxTEEActivo);
		
		JButton btnReActivar = new JButton("Activar");
		btnReActivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textEditarId.getText().equals("")) {
				if(confirmarSiNo("Seguro quiere activar el estado de evento "+textEditarNom.getText())) {
					limpiarTabla();
					TipoArea tEE = new TipoArea(Integer.valueOf(textEditarId.getText()), textEditarNom.getText(),chckbxTEEActivo.isSelected());
					tEE.setBajaLogica(true);
					tEE = usuarioRemote.editarTipoArea(tEE);
					JOptionPane.showMessageDialog(null, "TipoArea actualizado",
							"TipoArea actualizado", JOptionPane.INFORMATION_MESSAGE);
					System.out.println(tEE.toString());
					actualizarLista();
					limpiar();
				}
				}
			}
		});
		btnReActivar.setBounds(527, 635, 113, 21);
		panel.add(btnReActivar);

		frame.pack();
		frame.setVisible(true);

	}


	public void limpiarTabla() {
		if (listTipoArea != null || !listTipoArea.isEmpty() || listTipoArea.size() > 0) {
			listTipoArea.clear();

		}

		modelo.getDataVector().removeAllElements();
		modelo.fireTableDataChanged();

	}

	public void actualizarLista() {
		System.out.println("Entrando a cargar la lista de ITRs actualizarLista UI");
		limpiarTabla();
		System.out.println("usuarioRemote.listarITR()");
		listTipoArea = usuarioRemote.listarTipoArea();
		System.out.println(listTipoArea.toString());
		// Se rellena cada posici�n del array con una de las columnas de la tabla en
		// base de datos.
		for (TipoArea ta : listTipoArea) {

			fila[0] = ta.getId();
			fila[1] = ta.getNombre();
			if(ta.getBajaLogica()) {
				fila[2] = "Si";
			}else {
				fila[2] = "No";
			}

			// Se a�ade al modelo la fila completa.
			modelo.addRow(fila);

		}
		autoAjustarTabla(table);
	}

	public boolean nuevoTipoArea() {
		if (textNombre.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Ingrese nombre validos", "Intente nuevamente",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			TipoArea ta = new TipoArea();
			ta.setId(null);
			ta.setNombre(textNombre.getText());
			ta.setBajaLogica(true);  
			ta = usuarioRemote.crearTipoArea(ta);
			if (ta.getId() != null) {
				actualizarLista();
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "No se pudo crear el estado de evento", "Intente nuevamente",
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}

		return false;
	}

	public void buscarPor(String id, String nombre) {
		limpiarTabla();
		listTipoArea = usuarioRemote.buscarTipoAreaPor(id,nombre);
		if(listTipoArea != null) {
		System.out.println(listTipoArea.toString());
		// Se rellena cada posici�n del array con una de las columnas de la tabla en
		// base de datos.
		for (TipoArea ta : listTipoArea) {
			if(chckbxActivos.isSelected() && ta.getBajaLogica()) {
				fila[0] = ta.getId();
				fila[1] = ta.getNombre();
				if(ta.getBajaLogica()) {
					fila[2] = "Si";
				}else {
					fila[2] = "No";
				}
				// Se a�ade al modelo la fila completa.
				modelo.addRow(fila);
			}
			if(!chckbxActivos.isSelected() && !ta.getBajaLogica()) {
				fila[0] = ta.getId();
				fila[1] = ta.getNombre();
				if(ta.getBajaLogica()) {
					fila[2] = "Si";
				}else {
					fila[2] = "No";
				}
				// Se a�ade al modelo la fila completa.
				modelo.addRow(fila);
			}

		}
	}
	} 
	public void editar(String id, String nombre,Boolean activo) {
		limpiarTabla();
		TipoArea ta = new TipoArea(Integer.valueOf(id),nombre,activo);
		ta = usuarioRemote.editarTipoArea(ta);
		JOptionPane.showMessageDialog(null, "TipoArea actualizado",
				"TipoArea actualizado", JOptionPane.INFORMATION_MESSAGE);
		System.out.println(ta.toString());
		actualizarLista();
		limpiar();
		// Se rellena cada posici�n del array con una de las columnas de la tabla en
		// base de datos.

	}

	public void limpiar() {

		textBuscarId.setText("");
		textBuscarNomb.setText("");
		textNombre.setText("");
		textEditarNom.setText("");
		textEditarId.setText("");

	}

	 public void autoAjustarTabla(JTable table) {
				final TableColumnModel columnModel = table.getColumnModel();
				for (int column = 0; column < table.getColumnCount(); column++) {
						int width = 15; // Min width
						for (int row = 0; row < table.getRowCount(); row++) {
								TableCellRenderer renderer = table.getCellRenderer(row, column);
								Component comp = table.prepareRenderer(renderer, row, column);
								width = Math.max(comp.getPreferredSize().width +1 , width);
						}
						if(width > 300)
								width=300;
						columnModel.getColumn(column).setPreferredWidth(width);
				}
		}


		public boolean confirmarSiNo(String mensaje) {

			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null,mensaje,"Warning",dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION){
				return true;
			}
			return false;
		}

		
		public Boolean validar() {
			if(textEditarNom.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El nombre no puede ser vacio",
						"TipoArea error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if(textEditarId.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El id no puede ser vacio",
						"TipoArea error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			return true;
		}
		
    }