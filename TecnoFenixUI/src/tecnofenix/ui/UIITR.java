package tecnofenix.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.Itr;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;

public class UIITR {

	public JFrame frame;
	private JTable table;
	private DefaultTableModel modelo;
	private Object[] fila;
	private List<Itr> allITR;
	private EJBUsuarioRemoto usuarioRemote;
	private JTextField textNombre;
	private JTextField textDepartamento;
	private JTextField textBuscarDept;
	private JTextField textBuscarNomb;
	private JTextField textBuscarId;
	private JTextField textEditarId;
	private JTextField textEditarNom;
	private JTextField textEditarDepto;
	private JCheckBox chckbxActivos;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {
		usuarioRemote = new EJBUsuarioRemoto();
		allITR = new ArrayList<Itr>();
		frame = new JFrame("ITR");
		
		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(900, 800));
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
		final String[] columnNames = { "Id", "Nombre", "Departamento" ,"Activo"};

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

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
	        public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
				System.out.println("ACTUALIZANDO CLICK");
	            textEditarId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
	            textEditarNom.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
	            textEditarDepto.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				}
	        }
	    });
		// Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 103, 863, 368);
		// definimos un layout
		// Agregamos el JScrollPane al contenedor
		panel.add(scrollPane);

		JButton btnAddItr = new JButton("Nuevo Itr");
		btnAddItr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevoItr();
			}
		});
		btnAddItr.setBounds(760, 495, 113, 21);
		panel.add(btnAddItr);

		textNombre = new JTextField();
		textNombre.setBounds(208, 496, 188, 19);
		panel.add(textNombre);
		textNombre.setColumns(10);

		textDepartamento = new JTextField();
		textDepartamento.setBounds(406, 496, 188, 19);
		panel.add(textDepartamento);
		textDepartamento.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(208, 483, 74, 13);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Departamento");
		lblNewLabel_2.setBounds(406, 483, 113, 13);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre");
		lblNewLabel_1_1.setBounds(208, 48, 74, 13);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Departamento");
		lblNewLabel_2_1.setBounds(406, 48, 113, 13);
		panel.add(lblNewLabel_2_1);
		
		textBuscarDept = new JTextField();
		textBuscarDept.setColumns(10);
		textBuscarDept.setBounds(406, 61, 188, 19);
		textBuscarDept.setText("");
		panel.add(textBuscarDept);
		
		textBuscarNomb = new JTextField();
		textBuscarNomb.setColumns(10);
		textBuscarNomb.setBounds(208, 61, 188, 19);
		textBuscarDept.setText("");
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
				
				buscarPor(textBuscarId.getText() ,textBuscarNomb.getText(),textBuscarDept.getText());
			}
		});
		btnBuscar.setBounds(760, 60, 113, 21);
		panel.add(btnBuscar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 91, 880, 2);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Filtros de busqueda");
		lblNewLabel.setBounds(10, 10, 113, 13);
		panel.add(lblNewLabel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 557, 863, 2);
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
		
		JLabel lblNewLabel_2_2 = new JLabel("Departamento");
		lblNewLabel_2_2.setBounds(406, 569, 113, 13);
		panel.add(lblNewLabel_2_2);
		
		textEditarDepto = new JTextField();
		textEditarDepto.setColumns(10);
		textEditarDepto.setBounds(406, 582, 188, 19);
		panel.add(textEditarDepto);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar(textEditarId.getText() ,textEditarNom.getText(),textEditarDepto.getText());
				
			}
		});
		btnEditar.setBounds(637, 581, 113, 21);
		panel.add(btnEditar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textEditarId.getText()!="") {
				if(confirmarSiNo("Seguro quiere eliminar el ITR "+textEditarNom.getText())) {
					limpiarTabla();
					Itr itr = new Itr(Integer.valueOf(textEditarId.getText()), textEditarDepto.getText(), textEditarNom.getText());
					itr.setActivo(false);
					itr = usuarioRemote.editarITR(itr);
					JOptionPane.showMessageDialog(null, "Itr actualizado",
							"Itr actualizado", JOptionPane.INFORMATION_MESSAGE);
					System.out.println(itr.toString());
					actualizarLista();
					limpiar();
				}
				}
			}
		});
		btnBorrar.setBounds(760, 581, 113, 21);
		panel.add(btnBorrar);
		
		chckbxActivos = new JCheckBox("Activo");
		chckbxActivos.setSelected(true);
		chckbxActivos.setBounds(602, 60, 93, 21);
		panel.add(chckbxActivos);

		frame.pack();
		frame.setVisible(true);

	}

	
	public void limpiarTabla() {
		if (allITR != null || !allITR.isEmpty() || allITR.size() > 0) {
			allITR.clear();
			
		}

		modelo.getDataVector().removeAllElements();
		modelo.fireTableDataChanged();

	}
	
	public void actualizarLista() {
		System.out.println("Entrando a cargar la lista de ITRs actualizarLista UI");
		limpiarTabla();
		System.out.println("usuarioRemote.listarITR()");
		allITR = usuarioRemote.listarITR();
		System.out.println(allITR.toString());
		// Se rellena cada posición del array con una de las columnas de la tabla en
		// base de datos.
		for (Itr itr : allITR) {

			fila[0] = itr.getId();
			fila[1] = itr.getNombre();
			fila[2] = itr.getDepartamento();
			if(itr.getActivo()) {
				fila[3] = "Si";	
			}else {
				fila[3] = "No";	
			}		
			
			// Se añade al modelo la fila completa.
			modelo.addRow(fila);

		}
		autoAjustarTabla(table);
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
	
	public void buscarPor(String id, String nombre ,String depto) {
		limpiarTabla();
		allITR = usuarioRemote.buscarItrPor(id,nombre,depto);
		if(allITR != null) {
		System.out.println(allITR.toString());
		// Se rellena cada posición del array con una de las columnas de la tabla en
		// base de datos.
		for (Itr itr : allITR) {
			if(chckbxActivos.isSelected() && itr.getActivo()) {
				fila[0] = itr.getId();
				fila[1] = itr.getNombre();
				fila[2] = itr.getDepartamento();
				if(itr.getActivo()) {
					fila[3] = "Si";	
				}else {
					fila[3] = "No";	
				}	
				// Se añade al modelo la fila completa.
				modelo.addRow(fila);
			}
			if(!chckbxActivos.isSelected() && !itr.getActivo()) {
				fila[0] = itr.getId();
				fila[1] = itr.getNombre();
				fila[2] = itr.getDepartamento();
				if(itr.getActivo()) {
					fila[3] = "Si";	
				}else {
					fila[3] = "No";	
				}	
				// Se añade al modelo la fila completa.
				modelo.addRow(fila);
			}
			
		}
	}
	}
	public void editar(String id, String nombre ,String depto) {
		limpiarTabla();
		Itr itr = new Itr(Integer.valueOf(id), depto, nombre);
		itr = usuarioRemote.editarITR(itr);
		JOptionPane.showMessageDialog(null, "Itr actualizado",
				"Itr actualizado", JOptionPane.INFORMATION_MESSAGE);
		System.out.println(itr.toString());
		actualizarLista();
		limpiar();
		// Se rellena cada posición del array con una de las columnas de la tabla en
		// base de datos.
		
	}	
	
	public void limpiar() {
		textBuscarDept.setText("");
		textBuscarId.setText("");
		textBuscarNomb.setText("");
		textDepartamento.setText("");
		textNombre.setText("");
		textEditarDepto.setText("");
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
}
