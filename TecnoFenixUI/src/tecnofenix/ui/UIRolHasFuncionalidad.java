package tecnofenix.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.ConvocatoriaAsistenciaEventoEstudiante;
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Funcionalidad;
import tecnofenix.entidades.Rol;
import tecnofenix.entidades.Usuario;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.Component;


public class UIRolHasFuncionalidad {
	
	public JFrame frame;

	private JLabel lblRol;
	private JLabel lblSeleccioneFuncionalidad;

	private JButton btnAgregar;

	EJBUsuarioRemoto usuarioRemote;
	private String[] listaTmp;
	private JButton btnAgregar_1;
	
	private JTable tablaRol;
	private DefaultTableModel modeloRol;
	private Object[] filaRol;
	private JScrollPane scrollPaneRol;
	private Rol rolSeleccionado;
	private List<Rol> listaRol;
	private JLabel lblRolSelccionadoDisplay;
	
	private JTable tablaFuncionalidad;
	private DefaultTableModel modeloFuncionalidad;
	private Object[] filaFuncionalidad;
	private JScrollPane scrollPaneFuncionalidad;
	private List<Funcionalidad> listaFuncionalidades;
	
	private JTable tablaRolHasFuncionalidad;
	private DefaultTableModel modeloRolHasFuncionalidad;
	private Object[] filaRolHasFuncionalidad;
	private JScrollPane scrollPaneRolHasFuncionalidad;
	

	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	
	public void inicializar(Usuario user) {
		usuarioRemote = new EJBUsuarioRemoto();
		frame = new JFrame("Rol tiene funcionalidades");
		
		JPanel panel = new JPanel();
		// definimos un layout
		panel.setPreferredSize(new Dimension(1400, 500));// changed it to preferredSize, Thanks!

		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		rolSeleccionado= new Rol();
		listaRol= new ArrayList<Rol>();
		listaRol=usuarioRemote.listarRoles();
		listaFuncionalidades =  new ArrayList<Funcionalidad>();
		
		//tablas modelos
		modeloRol = new DefaultTableModel();
		modeloFuncionalidad = new DefaultTableModel();
		modeloRolHasFuncionalidad= new DefaultTableModel();
		
		// se crea la Tabla con el modelo DefaultTableModel
		tablaRol = new JTable(modeloRol);
		tablaRol.setDefaultEditor(Object.class, null);
		tablaRol.setCellSelectionEnabled(false);
		tablaRol.setRowSelectionAllowed(true);
		tablaRol.setForeground(Color.GREEN);
		tablaRol.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaRol.setBackground(Color.BLACK);
		// crea un array que contiene los nombre de las columnas
		final String[] columnNamesRol = {"Id","Nombre", "Descripcion" ,"Activo"};

		// insertamos las columnas
		for (int column = 0; column < columnNamesRol.length; column++) {
			// agrega las columnas a la tabla
			modeloRol.addColumn(columnNamesRol[column]);
		}

		// Se crea un array que será una de las filas de la tabla.
		filaRol = new Object[columnNamesRol.length];
	
		// se define el tamaño de la tabla
		tablaRol.setBounds(31, 58, 241, 369);

			//ROL
		tablaRol.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
	        public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && tablaRol.getSelectedRow() != -1) {
					Integer selecRol=Integer.valueOf(tablaRol.getValueAt(tablaRol.getSelectedRow(), 0).toString());
					rolSeleccionado =usuarioRemote.obtenerRolPorId(selecRol);
					lblRolSelccionadoDisplay.setText(rolSeleccionado.getNombre());
					cargarTablaRolHasFuncionalidad(rolSeleccionado);
				}
	        }
	    });
		// Creamos un JscrollPane y le agregamos la JTable

		scrollPaneRol = new JScrollPane(tablaRol);
		// definimos un layout
		scrollPaneRol.setBounds(31, 58, 492, 103);
		// Agregamos el JScrollPane al contenedor
		panel.add(scrollPaneRol);
		
		
		
		
		//FUNCIONALIDADES
		
		// se crea la Tabla con el modelo DefaultTableModel
		tablaFuncionalidad = new JTable(modeloFuncionalidad);
		tablaFuncionalidad.setDefaultEditor(Object.class, null);
		tablaFuncionalidad.setCellSelectionEnabled(false);
		tablaFuncionalidad.setRowSelectionAllowed(true);
		tablaFuncionalidad.setForeground(Color.GREEN);
		tablaFuncionalidad.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaFuncionalidad.setBackground(Color.BLACK);
		// crea un array que contiene los nombre de las columnas
		final String[] columnNamesFuncionalidad = {"Id","Nombre", "Descripcion" ,"Activo"};

		// insertamos las columnas
		for (int column = 0; column < columnNamesFuncionalidad.length; column++) {
			// agrega las columnas a la tabla
			modeloFuncionalidad.addColumn(columnNamesFuncionalidad[column]);
		}

		// Se crea un array que será una de las filas de la tabla.
		filaFuncionalidad = new Object[columnNamesFuncionalidad.length];
	
		// se define el tamaño de la tabla
		tablaFuncionalidad.setBounds(282, 58, 241, 369);

			//EVENTOS
		tablaFuncionalidad.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
	        public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && tablaFuncionalidad.getSelectedRow() != -1) {
				System.out.println("Click en rol");
					 
				}
	        }
	    });
		// Creamos un JscrollPane y le agregamos la JTable
		scrollPaneFuncionalidad = new JScrollPane(tablaFuncionalidad);
		// definimos un layout
		scrollPaneFuncionalidad.setBounds(31, 194, 492, 278);
		// Agregamos el JScrollPane al contenedor
		panel.add(scrollPaneFuncionalidad);
	
		
		//ROLHASFUNCIONALIDADES
		
		// se crea la Tabla con el modelo DefaultTableModel
		tablaRolHasFuncionalidad = new JTable(modeloRolHasFuncionalidad);
		tablaRolHasFuncionalidad.setDefaultEditor(Object.class, null);
		tablaRolHasFuncionalidad.setCellSelectionEnabled(false);
		tablaRolHasFuncionalidad.setRowSelectionAllowed(true);
		tablaRolHasFuncionalidad.setForeground(Color.GREEN);
//		tablaRolHasFuncionalidad.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaRolHasFuncionalidad.setBackground(Color.BLACK);
		tablaRolHasFuncionalidad.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desactiva el ajuste automático del ancho de las columnas

		// crea un array que contiene los nombre de las columnas
		final String[] columnNamesRolHasFuncionalidad = {"Id", "Nombre Rol", "Id", "Nombre Funcionalidad" };

		// insertamos las columnas
		for (int column = 0; column < columnNamesRolHasFuncionalidad.length; column++) {
			// agrega las columnas a la tabla
			modeloRolHasFuncionalidad.addColumn(columnNamesRolHasFuncionalidad[column]);
		}

		// Se crea un array que será una de las filas de la tabla.
		filaRolHasFuncionalidad = new Object[columnNamesRolHasFuncionalidad.length];
	
		// se define el tamaño de la tabla
		tablaRolHasFuncionalidad.setBounds(657, 58, 305, 369);

			//ROL HAS FUNCIONALIDADES
		tablaRolHasFuncionalidad.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
	        public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && tablaRolHasFuncionalidad.getSelectedRow() != -1) {
					 
					 
				}
	        }
	    });
		// Creamos un JscrollPane y le agregamos la JTable
		scrollPaneRolHasFuncionalidad = new JScrollPane(tablaRolHasFuncionalidad);
		// definimos un layout
		scrollPaneRolHasFuncionalidad.setBounds(657, 58, 716, 414);
		tablaRolHasFuncionalidad.getColumnModel().getColumn(0).setPreferredWidth(20); // Define el ancho preferido de la columna 0
		tablaRolHasFuncionalidad.getColumnModel().getColumn(1).setPreferredWidth(70); // Define el ancho preferido de la columna 1
		tablaRolHasFuncionalidad.getColumnModel().getColumn(2).setPreferredWidth(20); 
		tablaRolHasFuncionalidad.getColumnModel().getColumn(3).setPreferredWidth(600);
		// Agregamos el JScrollPane al contenedor
		panel.add(scrollPaneRolHasFuncionalidad);
		
		
		//FIN CARGA TABLAS
		
	
		
		lblRol = new JLabel("Seleccione Rol:");
		lblRol.setBounds(31, 43, 152, 13);
		panel.add(lblRol);
		

		
		lblSeleccioneFuncionalidad = new JLabel("Seleccione Funcionalidad:");
		lblSeleccioneFuncionalidad.setBounds(31, 171, 152, 13);
		panel.add(lblSeleccioneFuncionalidad);
		
				
		
		btnAgregar = new JButton("Agregar >>>");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rolSeleccionado!=null && rolSeleccionado.getId()!=null) {
				int[] selectedRows=tablaFuncionalidad.getSelectedRows();
				if(selectedRows.length>0) {
				for (int i = 0; i < selectedRows.length; i++) {
					Integer selec=Integer.valueOf(tablaFuncionalidad.getValueAt(selectedRows[i], 0).toString());
					Funcionalidad fun = new Funcionalidad();
					fun = usuarioRemote.obtenerFuncionalidadPorId(selec);
					
					Boolean cargar=true;
					for (Funcionalidad f : rolSeleccionado.getFuncionalidades()) {
						if(f.getId().equals(fun.getId())) {
							//el rol ya se tiene cargada esa funcionalidad
							cargar=false;
						}
					}
					if(cargar)rolSeleccionado.getFuncionalidades().add(fun);	
				}
				
				cargarTablaRolHasFuncionalidad(rolSeleccionado);
				}else {
					JOptionPane.showMessageDialog(null, "Primero debe seleccionar como minimo una funcionalidad, haciendo click en el listado de funcionalidades",
							"Informacion", JOptionPane.INFORMATION_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Primero debe seleccionar un Rol, de la lista de roles",
						"Informacion", JOptionPane.INFORMATION_MESSAGE);
			}
			}
		});
		btnAgregar.setBounds(533, 192, 114, 21);
		panel.add(btnAgregar);
		
		btnAgregar_1 = new JButton("<<< Quitar");
		btnAgregar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rolSeleccionado!=null && rolSeleccionado.getId()!=null) {
					int[] selectedRows=tablaRolHasFuncionalidad.getSelectedRows();
					if(selectedRows.length>0) {
						if(confirmarSiNo("Seguro quieres borrar las funcionalidades seleccionadas?")) {
							for (int i = 0; i < selectedRows.length; i++) {
								Integer selec=Integer.valueOf(tablaRolHasFuncionalidad.getValueAt(selectedRows[i], 2).toString());
								System.out.println("VALOR SELECCIONADO idFunc "+selec);
								Funcionalidad fun = new Funcionalidad();
								fun = usuarioRemote.obtenerFuncionalidadPorId(selec);
								rolSeleccionado.getFuncionalidades().remove(fun);
							}
						}
					cargarTablaRolHasFuncionalidad(rolSeleccionado);
					}else {
						JOptionPane.showMessageDialog(null, "Primero debe seleccionar como minimo una funcionalidad para borrar, haciendo click en el listado de funcionalidades que tiene el rol",
								"Informacion", JOptionPane.INFORMATION_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Primero debe seleccionar un Rol, de la lista de roles",
							"Informacion", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnAgregar_1.setBounds(533, 223, 114, 21);
		panel.add(btnAgregar_1);
		
		JLabel lblRolSelccionado = new JLabel("Rol selccionado:");
		lblRolSelccionado.setBounds(659, 43, 131, 13);
		panel.add(lblRolSelccionado);
		
		lblRolSelccionadoDisplay = new JLabel("---");
		lblRolSelccionadoDisplay.setBounds(810, 43, 152, 13);
		panel.add(lblRolSelccionadoDisplay);
		
		JButton btnAceptarGuardar = new JButton("Guardar");
		btnAceptarGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rolSeleccionado!=null && rolSeleccionado.getId()!=null) {
					if(confirmarSiNo("Seguro que desea guardar los cambios realizados en el rol "+rolSeleccionado.getNombre())) {
						try {
							usuarioRemote.modificarRol(rolSeleccionado);
							lblRolSelccionadoDisplay.setText("---");
							rolSeleccionado=null;
							tablaRol.clearSelection();
							tablaFuncionalidad.clearSelection();
							cargarTablaRolHasFuncionalidad(rolSeleccionado);
						} catch (Exception e2) {
							System.out.println(e2);
							JOptionPane.showMessageDialog(null, "Hubo un error actualizando las funcionalidades de los roles",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
						JOptionPane.showMessageDialog(null, "Las funcionalidades fueron guardadas correctamente",
								"Informacion", JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
				
				
			}
		});
		btnAceptarGuardar.setBounds(533, 254, 114, 21);
		panel.add(btnAceptarGuardar);
		
		
		cargarTablaRol(listaRol);
		
		listaFuncionalidades = usuarioRemote.listarFuncionalidades();
		cargarTablaFuncionalidad(listaFuncionalidades);
		
		frame.pack();

		frame.setVisible(true);

	}
	
	
	public boolean confirmarSiNo(String mensaje) {
		
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog (null,mensaje,"Warning",dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
	
	 public void cargarTablaRol(List<Rol> listPasada) {
		 limpiarTablaRol();
			for (Rol rol : listPasada) {
//				"Id","Nombre", "Descripcion" ,"Activo"
				filaRol[0] = rol.getId();
				filaRol[1] = rol.getNombre();
				filaRol[2] = rol.getDescripcion();
				filaRol[3] = rol.getActivo();
				if(rol.getActivo()!=null) {
					if (rol.getActivo()) {
						filaRol[3] = "Si";
					} else {
						filaRol[3] = "No";
					}
				}else {
					filaRol[3] = "---";
				}
				// Se añade al modelo la fila completa.
				modeloRol.addRow(filaRol);

			}
			autoAjustarTabla(tablaRol);
	 }
	 

	 
	 
	 public void cargarTablaFuncionalidad(List<Funcionalidad> listPasada) {
		 limpiarTablaFuncionalidad();
			for (Funcionalidad fun : listPasada) {
//				"Id","Nombre", "Descripcion" ,"Activo"
				filaFuncionalidad[0] = fun.getId();
				filaFuncionalidad[1] = fun.getNombre();
				filaFuncionalidad[2] = fun.getDescripcion();
				filaFuncionalidad[3] = fun.getActivo();
				if(fun.getActivo()!=null) {
					if (fun.getActivo()) {
						filaFuncionalidad[3] = "Si";
					} else {
						filaFuncionalidad[3] = "No";
					}
				}else {
					filaFuncionalidad[3] = "---";
				}
				// Se añade al modelo la fila completa.
				modeloFuncionalidad.addRow(filaFuncionalidad);

			}
			autoAjustarTabla(tablaFuncionalidad);
	 }
	 
	
	 public void cargarTablaRolHasFuncionalidad(Rol rolSelec) {
		 limpiarTablaRolHasFuncionalidad();
		 if(rolSelec!=null) {
			for (Funcionalidad fun : rolSelec.getFuncionalidades()) {
//				"IdRol", "Nombre Rol", "IdFunc", "Nombre Fun" "
				filaRolHasFuncionalidad[0] = rolSelec.getId();
				filaRolHasFuncionalidad[1] = rolSelec.getNombre();
				filaRolHasFuncionalidad[2] = fun.getId();
				filaRolHasFuncionalidad[3] = fun.getNombre();
				
				// Se añade al modelo la fila completa.
				modeloRolHasFuncionalidad.addRow(filaRolHasFuncionalidad);

			}
//			autoAjustarTabla(tablaRolHasFuncionalidad);
 
		 }
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
	 public void limpiarTablaRol() {
			modeloRol.getDataVector().removeAllElements();
			modeloRol.fireTableDataChanged();
		}
	 public void limpiarTablaFuncionalidad() {
			modeloFuncionalidad.getDataVector().removeAllElements();
			modeloFuncionalidad.fireTableDataChanged();
		}
	 public void limpiarTablaRolHasFuncionalidad() {
			 modeloRolHasFuncionalidad.getDataVector().removeAllElements();
			 modeloRolHasFuncionalidad.fireTableDataChanged();
		}
}
