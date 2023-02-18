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

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.Funcionalidad;
import tecnofenix.entidades.Itr;
import tecnofenix.entidades.Rol;
import tecnofenix.entidades.Tutor;
import tecnofenix.entidades.TutorResponsableEvento;
import tecnofenix.entidades.Usuario;

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

public class UIListaTutores {

	public JFrame frame;
	private JTable table;
	private DefaultTableModel modelo;
	private Object[] fila;
	private List<Tutor> listTutores;
	private EJBUsuarioRemoto usuarioRemote;
	private JTextField textBuscarDept;
	private JTextField textBuscarNomb;
	private JTextField textBuscarId;
	private java.awt.List listaDeTutores;
	private List<Tutor> listTutoresSeleccionados;
	private List<Tutor> listTutoresMarcadosABorrar;


	public JButton btnSeleccionarTutores;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {
		usuarioRemote = new EJBUsuarioRemoto();
		//Para el datatable
		listTutores = new ArrayList<Tutor>();
		
		listTutoresSeleccionados= new ArrayList<Tutor>();
		listTutoresMarcadosABorrar= new ArrayList<Tutor>();
		
		frame = new JFrame("Listado de tutores");

		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(800, 800));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
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
		// crea un array que contiene los nombre de las columnas
		final String[] columnNames = { "Id", "CI","Nombre", "Apellido","Tipo","Area" };

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
				System.out.println(" CLICK en la tabla --- listaDeTutores tiene "+listaDeTutores.getItems().length + " Elementos");
				boolean flag=true;
				for (int i = 0; i < listaDeTutores.getItems().length; i++) {
					System.out.println(i);
					System.out.println(listaDeTutores.getItems()[i]);
					String nombre =table.getValueAt(table.getSelectedRow(), 1).toString()+" "+table.getValueAt(table.getSelectedRow(), 2).toString()+" "+table.getValueAt(table.getSelectedRow(),3 ).toString();
					System.out.println("Nombre lista : "+listaDeTutores.getItems()[i]);
					System.out.println("Comparar con "+nombre);
					if(listaDeTutores.getItems()[i].equals(nombre)) {
						System.out.println("Son iguales set false flag");
						flag=false;
					}
				}
				if(flag) {
					String nombre =table.getValueAt(table.getSelectedRow(), 1).toString()+" "+table.getValueAt(table.getSelectedRow(), 2).toString()+" "+table.getValueAt(table.getSelectedRow(),3 ).toString();
					listaDeTutores.add(nombre);
					System.out.println("listaDeTutores length"+listaDeTutores.getItems().length);
					Tutor tut = (Tutor)usuarioRemote.encontrarUsuario(Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()));
					System.out.println("listTutores size"+ listTutoresSeleccionados.size());
					if(listTutoresSeleccionados.contains(tut)) {
						System.out.println("Ya se encuentra ingresado");
					}else {
						listTutoresSeleccionados.add(tut);
					}
					
					
				}else {
					JOptionPane.showMessageDialog(null, "El tutor ya fue seleccionado",
							"Ya existe", JOptionPane.INFORMATION_MESSAGE);
				}
				}
	        }
	    });
		// Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 103, 780, 368);
		// definimos un layout
		// Agregamos el JScrollPane al contenedor
		panel.add(scrollPane);

		btnSeleccionarTutores = new JButton("Aceptar la lista de tutores");
		btnSeleccionarTutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnSeleccionarTutores.setBounds(561, 692, 216, 21);
		panel.add(btnSeleccionarTutores);
		
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
				
				buscarPor(textBuscarId.getText() ,textBuscarNomb.getText(),textBuscarDept.getText(),"","","");
			}
		});
		btnBuscar.setBounds(677, 60, 113, 21);
		panel.add(btnBuscar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 91, 790, 2);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Filtros de busqueda");
		lblNewLabel.setBounds(10, 10, 113, 13);
		panel.add(lblNewLabel);
		
		setListaDeTutores(new java.awt.List());
		getListaDeTutores().setBounds(10, 477, 509, 238);
		panel.add(getListaDeTutores());
		
		JButton btnEliminarSeleccionado = new JButton(">>> Eliminar seleccionado");
		btnEliminarSeleccionado.setBounds(561, 481, 216, 21);
		panel.add(btnEliminarSeleccionado);

		btnEliminarSeleccionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				listaDeTutores.getSelectedItem();
				if(borrarRow(listaDeTutores.getSelectedItem())) {
					Tutor tutAEliminar=null;
					for(Tutor tut : listTutoresSeleccionados) {
						String nombre =tut.getDocumento() +" "+tut.getNombres()+" "+tut.getApellidos();
						if(nombre.equals(listaDeTutores.getSelectedItem())) {
							tutAEliminar=tut;	
						}
						
					}
					if(tutAEliminar!=null) {
						listTutoresMarcadosABorrar.add(tutAEliminar);
						listTutoresSeleccionados.remove(tutAEliminar);
						listaDeTutores.remove(listaDeTutores.getSelectedItem());
					}
					
				}
				
			}
		});
		
		frame.pack();
		frame.setVisible(true);

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
	public void limpiarTabla() {
		if (listTutores != null || !listTutores.isEmpty() || listTutores.size() > 0) {
			listTutores.clear();
			
		}
//		System.out.println("Limpiar tabla "+modelo.getRowCount());
//		while (modelo.getRowCount()>0){
//			System.out.println("Limpiar tabla "+modelo.getRowCount());
//			modelo.removeRow(0);
//        }
		modelo.getDataVector().removeAllElements();
		modelo.fireTableDataChanged();
//		for (int i = 0; i < modelo.getRowCount(); i++) {
//			try {
//				modelo.removeRow(i);
//			} catch (ArrayIndexOutOfBoundsException e) {
////				System.out.println(e);
//			}
//			
//		}
//		for (int i = modelo.getRowCount(); i >0 ; i--) {
//			System.out.print("RowCount ");
//			System.out.print(modelo.getRowCount());
//			System.out.print(" Contador for "+i);
//			try {
//				modelo.removeRow(i);
//			} catch (ArrayIndexOutOfBoundsException e) {
//				System.out.println(e);
//			}
//			
//		}
	}
	
	public void actualizarLista() {
		System.out.println("Entrando a cargar la lista de ITRs actualizarLista UI");
		limpiarTabla();
		System.out.println("usuarioRemote.listarITR()");
		listTutores = usuarioRemote.listarTutores();
		System.out.println(listTutores.toString());
		// Se rellena cada posición del array con una de las columnas de la tabla en
		// base de datos.
		for (Tutor tutor : listTutores) {
//			"Id", "CI","Nombre", "Apellido","Tipo","Area"
			fila[0] = tutor.getId();
			fila[1] = tutor.getDocumento().toString();
			fila[2] = tutor.getNombres();
			fila[3] = tutor.getApellidos();
			fila[4] = tutor.getTipo().toString();
			fila[5] = tutor.getArea().toString();
			// Se añade al modelo la fila completa.
			modelo.addRow(fila);

		}
	}


	
	public void buscarPor(String id,String ci, String nombre ,String apellido,String tipo ,String area) {
		limpiarTabla();
		listTutores = usuarioRemote.buscarTutorPor(id,ci,nombre,apellido,tipo,area);
		if(listTutores != null) {
		System.out.println(listTutores.toString());
		// Se rellena cada posición del array con una de las columnas de la tabla en
		// base de datos.
		for (Tutor tutor : listTutores) {
//			"Id", "CI","Nombre", "Apellido","Tipo","Area"
			fila[0] = tutor.getId();
			fila[1] = tutor.getDocumento().toString();
			fila[2] = tutor.getNombres();
			fila[3] = tutor.getApellidos();
			fila[4] = tutor.getTipo().toString();
			fila[5] = tutor.getArea().toString();
			// Se añade al modelo la fila completa.
			modelo.addRow(fila);
		}
	}
	}
	
	public void limpiar() {
		textBuscarDept.setText("");
		textBuscarId.setText("");
		textBuscarNomb.setText("");

		
	}
	
	public List<Tutor> getListTutoresSeleccionados() {
		return listTutoresSeleccionados;
	}


	public void setListTutoresSeleccionados(List<Tutor> listTutoresSeleccionados) {
		this.listTutoresSeleccionados = listTutoresSeleccionados;
		getListaDeTutores().removeAll();
		for(Tutor tut :listTutoresSeleccionados ) {
			String nombre =tut.getDocumento() +" "+tut.getNombres()+" "+tut.getApellidos();
			listaDeTutores.add(nombre);
		}
	}

	
	public List<Tutor> getListTutoresMarcadosABorrar() {
		return listTutoresMarcadosABorrar;
	}


	public void setListTutoresMarcadosABorrar(List<Tutor> listTutoresMarcadosABorrar) {
		this.listTutoresMarcadosABorrar = listTutoresMarcadosABorrar;
	}
	
	
	
	
	public java.awt.List getListaDeTutores() {
		return listaDeTutores;
		
	}


	public void setListaDeTutores(java.awt.List listaDeTutores) {
		this.listaDeTutores = listaDeTutores;
		
	}
}
