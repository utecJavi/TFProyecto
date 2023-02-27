package tecnofenix.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.ConvocatoriaAsistenciaEventoEstudiante;
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Evento;
import tecnofenix.entidades.Itr;
import tecnofenix.entidades.Tutor;
import tecnofenix.entidades.Usuario;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;


public class UIHabilitarEstudianteEvento {
//	Habilitar a un estudiante para un evento
//	Ver estudiantes habilitados para un evento
//	Buscar estudiantes habilitados por evento
//	Eliminar habilitación de un estudiante para un evento
//	Generar reporte de habilitaciones

		public JFrame frame;
		private JTable table;
		private DefaultTableModel modelo;
		private Object[] fila;
		
		private JTable tableEstudiante;
		private DefaultTableModel modeloEstudiante;
		private Object[] filaEstudiante;
		
		private JTable tableHabilitados;
		private DefaultTableModel modeloHabilitados;
		private Object[] filaHabilitados;
		
		private List<Evento> listEventos;
		private List<Estudiante> listEstudiantes;
		private List<ConvocatoriaAsistenciaEventoEstudiante> listHabilitados;
		
		private EJBUsuarioRemoto usuarioRemote;
		private JTextField textBuscarCI;
		private JTextField textBuscarTitulo;
		private JTextField textBuscarId;
		
		private JTextArea lblDatosEvento;
		private JComboBox<Itr> comboBoxITR;
		
		private Evento eventoSeleccionado;
		private Estudiante estudianteSeleccionado;
		
		public JButton btnConfirmarConvocados;

		private JTextField textBuscarNombre;
		private JTextField textBuscarApellido;
		private JTextField textGeneracion;
		
		/**
		 * @wbp.parser.entryPoint
		 */
		public void inicializar() {
			usuarioRemote = new EJBUsuarioRemoto();
			//Para el datatable
			listEventos = new ArrayList<Evento>();
			listEstudiantes= new ArrayList<Estudiante>();
			listHabilitados= new ArrayList<ConvocatoriaAsistenciaEventoEstudiante>();
			
			eventoSeleccionado= new Evento();
			estudianteSeleccionado= new Estudiante();
			
			frame = new JFrame("Convocatoria de estudiantes a eventos");

			JPanel panel = new JPanel();
			// definimos un layout

			panel.setPreferredSize(new Dimension(1200, 800));
			frame.getContentPane().add(panel, BorderLayout.SOUTH);
			panel.setLayout(null);
//			frame.getContentPane().add(panel, BorderLayout.SOUTH);
//			frame.getContentPane().setLayout(new BorderLayout());
//			frame.getContentPane().add(panel, BorderLayout.CENTER);
//			frame.pack();
//			frame.setVisible(true);
			
			modelo = new DefaultTableModel();
			modeloEstudiante = new DefaultTableModel();
			modeloHabilitados= new DefaultTableModel();
			
			// se crea la Tabla con el modelo DefaultTableModel
			table = new JTable(modelo);
			table.setDefaultEditor(Object.class, null);
			table.setCellSelectionEnabled(false);
			table.setRowSelectionAllowed(true);
			table.setForeground(Color.GREEN);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.setBackground(Color.BLACK);
			// crea un array que contiene los nombre de las columnas
			final String[] columnNames = {"Id", "Titulo", "Tipo de evento", "Modalidad del evento", "Localizacion", "Inicio", "Fin" };

			// insertamos las columnas
			for (int column = 0; column < columnNames.length; column++) {
				// agrega las columnas a la tabla
				modelo.addColumn(columnNames[column]);
			}

			// Se crea un array que será una de las filas de la tabla.
			fila = new Object[columnNames.length];
		
			// se define el tamaño de la tabla
			table.setBounds(93, 215, 100, 100);

				//EVENTOS
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
				@Override
		        public void valueChanged(ListSelectionEvent event) {
					if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
						 Integer selec=Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
						 eventoSeleccionado= usuarioRemote.obtenerEvento(selec);
						 String datosEvento=new String();
						 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
						 datosEvento="Id: "+ eventoSeleccionado.getId()+" "
						 + "Titulo: "+ eventoSeleccionado.getTitulo()+"        "
						 + "Tipo: "+ eventoSeleccionado.getTipo().getTipo()+"        "
						 + "Modalidad: "+ eventoSeleccionado.getModalidad().getModalidad()+"\n"
						 + "Localizacion: "+ eventoSeleccionado.getLocalizacion()+"        "
						 + "Inicio: "+ formatter.format(eventoSeleccionado.getInicio()) +"        "
						 + "Fin: "+ formatter.format(eventoSeleccionado.getFin())+" ";
						 lblDatosEvento.setText(datosEvento);
						 listHabilitados.clear();
						 cargarTablaHabilitados(listHabilitados);
						 listHabilitados= usuarioRemote.listarConvocatoriaEventEstuPorEvento(eventoSeleccionado);
						 cargarTablaHabilitados(listHabilitados);
						 
					}
		        }
		    });
			// Creamos un JscrollPane y le agregamos la JTable
			JScrollPane scrollPaneEvento = new JScrollPane(table);
			scrollPaneEvento.setBounds(10, 103, 687, 368);
			// definimos un layout
			// Agregamos el JScrollPane al contenedor
			panel.add(scrollPaneEvento);
			
			
	// ESTUDIANTE TABLE
			// se crea la Tabla con el modelo DefaultTableModel
					tableEstudiante = new JTable(modeloEstudiante);
					tableEstudiante.setDefaultEditor(Object.class, null);
					tableEstudiante.setCellSelectionEnabled(false);
					tableEstudiante.setRowSelectionAllowed(true);
					tableEstudiante.setForeground(Color.GREEN);
					tableEstudiante.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
					tableEstudiante.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					tableEstudiante.setBackground(Color.BLACK);
					// crea un array que contiene los nombre de las columnas
					final String[] columnNamesEstudiante = { "Id", "CI","Nombre", "Apellido"};

					// insertamos las columnas
					for (int column = 0; column < columnNamesEstudiante.length; column++) {
						// agrega las columnas a la tabla
						modeloEstudiante.addColumn(columnNamesEstudiante[column]);
					}

					// Se crea un array que será una de las filas de la tabla.
					filaEstudiante = new Object[columnNamesEstudiante.length];

					// se define el tamaño de la tabla
					tableEstudiante.setBounds(93, 215, 100, 100);

					tableEstudiante.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
						@Override
				        public void valueChanged(ListSelectionEvent event) {
							if (!event.getValueIsAdjusting() && tableEstudiante.getSelectedRow() != -1) {
								System.out.println("CLICK TABLA ESTUDIANTES");
							}
				        }
				    });
					// Creamos un JscrollPane y le agregamos la JTable
					JScrollPane scrollPaneEstudiante = new JScrollPane(tableEstudiante);
					scrollPaneEstudiante.setBounds(707, 103, 483, 368);
					panel.add(scrollPaneEstudiante);
			///FINNNNNNNNNNNNNNN
			
					
					//HABILITADOOOS
					tableHabilitados = new JTable(modeloHabilitados);
					tableHabilitados.setDefaultEditor(Object.class, null);
					tableHabilitados.setCellSelectionEnabled(false);
					tableHabilitados.setRowSelectionAllowed(true);
					tableHabilitados.setForeground(Color.GREEN);
//					tableHabilitados.setColumnSelectionAllowed(false);
					tableHabilitados.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					tableHabilitados.setBackground(Color.BLACK);
					// crea un array que contiene los nombre de las columnas
					final String[] columnNamesHabilitados = { "Id", "Evento","Nombre", "Asistencia","Calificacion" };

					// insertamos las columnas
					for (int column = 0; column < columnNamesHabilitados.length; column++) {
						// agrega las columnas a la tabla
						modeloHabilitados.addColumn(columnNamesHabilitados[column]);
					}

					// Se crea un array que será una de las filas de la tabla.
					filaHabilitados = new Object[columnNamesHabilitados.length];

					// se define el tamaño de la tabla
					tableHabilitados.setBounds(93, 215, 100, 100);

					tableHabilitados.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
						@Override
				        public void valueChanged(ListSelectionEvent event) {
							if (!event.getValueIsAdjusting() && tableHabilitados.getSelectedRow() != -1) {
//								 Integer selec=Integer.valueOf(tableEstudiante.getValueAt(tableEstudiante.getSelectedRow(), 0).toString());
//								 estudianteSeleccionado= usuarioRemote.buscarEstudiantePorId(selec);
//								 String datosEstudiante=new String();
								System.out.println("CLICK EN TABLA HABILITADOS");
							}
				        }
				    });
					// Creamos un JscrollPane y le agregamos la JTable
					JScrollPane scrollPaneHabilitados = new JScrollPane(tableHabilitados);
					scrollPaneHabilitados.setBounds(10, 520, 1180, 224);
					panel.add(scrollPaneHabilitados);
					
					//FIN HABILITADOS
			
			btnConfirmarConvocados = new JButton("Confirmar convocados");
			btnConfirmarConvocados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(validar()) {
						confirmarConvocados(listHabilitados);
						JOptionPane.showMessageDialog(null, "Su solicitud fue procesada", "Ok",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			btnConfirmarConvocados.setBounds(910, 754, 290, 21);
			panel.add(btnConfirmarConvocados);
			
			JLabel lblNewLabel_1_1 = new JLabel("Nombre evento");
			lblNewLabel_1_1.setBounds(72, 49, 102, 13);
			panel.add(lblNewLabel_1_1);
			
			JLabel lblNewLabel_2_1 = new JLabel("Documento");
			lblNewLabel_2_1.setBounds(707, 10, 113, 13);
			panel.add(lblNewLabel_2_1);
			
			textBuscarCI = new JTextField();
			textBuscarCI.setColumns(10);
			textBuscarCI.setBounds(707, 23, 188, 19);
			textBuscarCI.setText("");
			panel.add(textBuscarCI);
			
			textBuscarTitulo = new JTextField();
			textBuscarTitulo.setColumns(10);
			textBuscarTitulo.setBounds(72, 62, 188, 19);
			textBuscarCI.setText("");
			panel.add(textBuscarTitulo);
			
			JLabel lblNewLabel_1_1_1 = new JLabel("Id");
			lblNewLabel_1_1_1.setBounds(10, 49, 74, 13);
			panel.add(lblNewLabel_1_1_1);
			
			textBuscarId = new JTextField();
			textBuscarId.setColumns(10);
			textBuscarId.setBounds(10, 62, 52, 19);
			textBuscarId.setText("");
			panel.add(textBuscarId);
			
			JButton btnBuscarEstudiantes = new JButton("Buscar");
			btnBuscarEstudiantes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String itr="";
					if(comboBoxITR.getSelectedItem()!=null ||comboBoxITR.getSelectedIndex()!= -1  ) {
						itr =comboBoxITR.getSelectedItem().toString();
					}
					buscarEstudiantePor(textBuscarCI.getText() ,textBuscarNombre.getText(),textBuscarApellido.getText(),textGeneracion.getText(),itr);
				}
			});
			btnBuscarEstudiantes.setBounds(1116, 60, 74, 21);
			panel.add(btnBuscarEstudiantes);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(10, 90, 1180, 3);
			panel.add(separator);
			
			JLabel lblNewLabel = new JLabel("Filtros de busqueda");
			lblNewLabel.setBounds(10, 10, 113, 13);
			panel.add(lblNewLabel);
			
			JButton btnQuitar = new JButton("Quitar seleccionados");
			btnQuitar.setBounds(569, 754, 290, 21);
			panel.add(btnQuitar);
			
			JButton btnBuscarEventos = new JButton("Buscar");
			btnBuscarEventos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarEventosPor(textBuscarId.getText(), textBuscarTitulo.getText());
				}
			});
			btnBuscarEventos.setBounds(512, 61, 113, 21);
			panel.add(btnBuscarEventos);
			
			JLabel lblNewLabel_2_1_1 = new JLabel("Nombre");
			lblNewLabel_2_1_1.setBounds(707, 48, 113, 13);
			panel.add(lblNewLabel_2_1_1);
			
			textBuscarNombre = new JTextField();
			textBuscarNombre.setText("");
			textBuscarNombre.setColumns(10);
			textBuscarNombre.setBounds(707, 61, 148, 19);
			panel.add(textBuscarNombre);
			
			JLabel lblNewLabel_2_1_1_1 = new JLabel("Apellido");
			lblNewLabel_2_1_1_1.setBounds(865, 48, 113, 13);
			panel.add(lblNewLabel_2_1_1_1);
			
			textBuscarApellido = new JTextField();
			textBuscarApellido.setText("");
			textBuscarApellido.setColumns(10);
			textBuscarApellido.setBounds(865, 61, 148, 19);
			panel.add(textBuscarApellido);
			
			JLabel lblHabilitados = new JLabel("Estudiantes Habilitados: ");
			lblHabilitados.setBounds(10, 481, 156, 13);
			panel.add(lblHabilitados);
			
			lblDatosEvento = new JTextArea("------");
			lblDatosEvento.setLineWrap(true);
			lblDatosEvento.setForeground(Color.BLACK);
			lblDatosEvento.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblDatosEvento.setEditable(false);
			lblDatosEvento.setBackground(SystemColor.menu);
			lblDatosEvento.setBounds(176, 470, 714, 50);
			panel.add(lblDatosEvento);
			

			
			JButton btnNewButton = new JButton("\u25BC\u25BC\u25BC");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//tiene que estar seleccionado el evento!
					if(eventoSeleccionado!= null && eventoSeleccionado.getId()!=null) {
						if(validarEventoParaAgregarEstudiantes()) {
							int[] selectedRows=tableEstudiante.getSelectedRows();
							if(selectedRows.length>0) {
							for (int i = 0; i < selectedRows.length; i++) {
								Integer selec=Integer.valueOf(tableEstudiante.getValueAt(selectedRows[i], 0).toString());
								ConvocatoriaAsistenciaEventoEstudiante conv = new ConvocatoriaAsistenciaEventoEstudiante();
								Estudiante estu = usuarioRemote.buscarEstudiantePorId(selec);
								conv.setEventoId(eventoSeleccionado);
								conv.setEstudianteId(estu);
								Boolean cargar=true;
								for (ConvocatoriaAsistenciaEventoEstudiante habilitados : listHabilitados) {
									if(habilitados.getEstudianteId().equals(estu)) {
										//el estudiante ya se encuentra en la lista no lo debo cargar
										cargar=false;
									}
								}
								if(cargar)listHabilitados.add(conv);	
							}
							
							cargarTablaHabilitados(listHabilitados);
							}else {
								JOptionPane.showMessageDialog(null, "Primero debe seleccionar como minimo 1 estudiante, haciendo click en el estudiante deseado",
										"Informacion", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, "Primero debe seleccionar un evento de la tabla de eventos, haciendo click en el evento deseado",
								"Informacion", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			btnNewButton.setBounds(910, 481, 86, 21);
			panel.add(btnNewButton);
			
			JDateChooser dateChooser = new JDateChooser();
			dateChooser.setBounds(270, 62, 98, 19);
			panel.add(dateChooser);
			
			JDateChooser dateChooser_1 = new JDateChooser();
			dateChooser_1.setBounds(378, 62, 102, 19);
			panel.add(dateChooser_1);
			
			JLabel lblNewLabel_1 = new JLabel("Inicio");
			lblNewLabel_1.setBounds(268, 49, 45, 13);
			panel.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Fin");
			lblNewLabel_2.setBounds(378, 49, 45, 13);
			panel.add(lblNewLabel_2);
			
			comboBoxITR = new JComboBox<Itr>();
			comboBoxITR.setBounds(910, 22, 174, 21);
			List<Itr> listItr = usuarioRemote.listarITR();
			for (Itr itrItem : listItr) {
				comboBoxITR.addItem(itrItem);
			}
			comboBoxITR.setEnabled(true);
			comboBoxITR.setSelectedIndex(-1);
			panel.add(comboBoxITR);
			
			
			JLabel lblNewLabel_3 = new JLabel("ITR");
			lblNewLabel_3.setBounds(910, 10, 45, 13);
			panel.add(lblNewLabel_3);
			
			textGeneracion = new JTextField();
			textGeneracion.addKeyListener(new KeyAdapter() {
			    public void keyTyped(KeyEvent e) {
			        char c = e.getKeyChar();
			        if (!((c >= '0') && (c <= '9') ||
			           (c == KeyEvent.VK_BACK_SPACE) ||
			           (c == KeyEvent.VK_DELETE))) {
			         
			          e.consume();
			        }
			        if(textGeneracion.getText().length()>=3) {
			        	String texto=textGeneracion.getText();
			        	textGeneracion.setText(texto.substring(0,3));
			        }
			      }
			    });
			textGeneracion.setBounds(1094, 23, 96, 19);
			textGeneracion.setColumns(10);
			panel.add(textGeneracion);
			
			
			JLabel lblNewLabel_4 = new JLabel("Generacion");
			lblNewLabel_4.setBounds(1092, 10, 98, 13);
			panel.add(lblNewLabel_4);
			
			

			btnQuitar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if(validarEventoParaQuitarEstudiantes()) {
						if(confirmarSiNo("Esta seguro que desea eliminar el/los estudiante/s seleccionado/s del evento?")) {
						int[] selectedRows=tableHabilitados.getSelectedRows();
						for (int i = 0; i < selectedRows.length; i++) {
							Integer selec=Integer.valueOf(tableHabilitados.getValueAt(selectedRows[i], 0).toString());
							System.out.println("Contenido de la seleccion "+selec);
							ConvocatoriaAsistenciaEventoEstudiante conv = usuarioRemote.obtenerDatosConvPorId(selec);
							listHabilitados.remove(conv);
	//						
						}
						cargarTablaHabilitados(listHabilitados);
						JOptionPane.showMessageDialog(null, "Para confirmar la eliminacion debera hacer click en el boton confirmar convocados", "Datos no validos",
								JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			
			listEstudiantes = usuarioRemote.buscarEstudiantePor("","","","","");
			actualizarListaEstudiante();
			
			listEventos = usuarioRemote.listarEventos();
			actualizarListaEvento();
			
			
//			listHabilitados = usuarioRemote.listarEventos();
//			actualizarListaHabilitados();
			
			frame.pack();
			frame.setVisible(true);

		}

		public boolean confirmarSiNo(String mensaje) {
//			msj.mostrarMensaje(Mensajes.BAJA);
			
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null, mensaje,"Warning",dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION){
				return true;
			}
			return false;
		}
		
		public void limpiarTablaEventos() {
			if (listEventos != null || !listEventos.isEmpty() || listEventos.size() > 0) {
				listEventos.clear();
				
			}
			modelo.getDataVector().removeAllElements();
			modelo.fireTableDataChanged();
		}
		public void limpiarTablaEstudiantes() {
//			if (listEstudiantes != null || !listEstudiantes.isEmpty() || listEstudiantes.size() > 0) {
//				listEstudiantes.clear();
//				
//			}
			modeloEstudiante.getDataVector().removeAllElements();
			modeloEstudiante.fireTableDataChanged();
		}
		
		public void limpiarTablaHabilitados() {
//			if (listHabilitados != null || !listHabilitados.isEmpty() || listHabilitados.size() > 0) {
//				listHabilitados.clear();
//				
//			}
			modeloHabilitados.getDataVector().removeAllElements();
			modeloHabilitados.fireTableDataChanged();
		}
		
		
		
		public void actualizarListaEvento() {
			limpiarTablaEventos();
			
			listEventos= usuarioRemote.listarEventos();
			cargarTablaEvento(listEventos);
		}
		
		public void actualizarListaEstudiante() {

			limpiarTablaEstudiantes();
			listEstudiantes = usuarioRemote.listarEstudiantes();
			cargarTablaEstudiante(listEstudiantes);
		}
		
		public void actualizarListaHabilitados() {
			limpiarTablaHabilitados();
			
//			listHabilitados= usuarioRemote.listarEventos();
			cargarTablaHabilitados(listHabilitados);
		}

		public void buscarEventosPor(String id,String titulo) {
			limpiarTablaEventos();
			listEventos = usuarioRemote.buscarEventosPor(id,titulo);
			if(listEventos != null) {
				// Se rellena cada posición del array con una de las columnas de la tabla en
			// base de datos.
				cargarTablaEvento(listEventos);
			}
		}
		
		public void buscarEstudiantePor(String ci, String nombre ,String apellido,String generacion,String itr ) {
			limpiarTablaEstudiantes();
			listEstudiantes = usuarioRemote.buscarEstudiantePor(ci,nombre,apellido,generacion,itr);
			if(listEstudiantes != null) {
				// Se rellena cada posición del array con una de las columnas de la tabla en
			// base de datos.
			cargarTablaEstudiante(listEstudiantes);
			
			}
		}
		
		public void limpiar() {
			textBuscarCI.setText("");
			textBuscarId.setText("");
			textBuscarTitulo.setText("");

			
		}
		
		public boolean validar() {
//			Date hoy = new Date(System.currentTimeMillis());
//			if(eventoSeleccionado.getInicio()>=hoy) {
//				JOptionPane.showMessageDialog(null, "Seleccione una calificacion de 1 a 5", "Datos no validos",
//						JOptionPane.INFORMATION_MESSAGE);
//				return false;	
//			}
//			if(estudianteSeleccionado==null || estudianteSeleccionado.getId()==null) {
//				JOptionPane.showMessageDialog(null, "Seleccione un Estudiante", "Datos no validos",
//						JOptionPane.INFORMATION_MESSAGE);
//				return false;
//			}
//			if(eventoSeleccionado==null || eventoSeleccionado.getId()==null) {
//				JOptionPane.showMessageDialog(null, "Seleccione un Evento", "Datos no validos",
//						JOptionPane.INFORMATION_MESSAGE);
//				return false;
//			}
//
//			if(comboBoxAsistencia.getSelectedItem().toString()=="") {
//				JOptionPane.showMessageDialog(null, "Seleccione asistencia si no", "Datos no validos",
//						JOptionPane.INFORMATION_MESSAGE);
//				return false;
//			}
//			if(comboBoxAsistencia.getSelectedItem().toString()=="No" && (Integer.valueOf(comboBoxNota.getSelectedItem().toString())>=1) ) {
//				JOptionPane.showMessageDialog(null, "Si el estudiante no concurrio al evento, la nota debe estar 'vacia', no puede ser mayor a 0", "Datos no validos",
//						JOptionPane.INFORMATION_MESSAGE);
//				return false;
//			}
//			
			return true;
		}
		public Boolean confirmarConvocados(List<ConvocatoriaAsistenciaEventoEstudiante> convList) {
			for (ConvocatoriaAsistenciaEventoEstudiante conv:convList) {
				if(conv.getId()==null) {
					conv = usuarioRemote.agregarEstudianteAEvento(conv);
					if(conv.getId()!=null) {
					System.out.println("Se ingreso el estudiante "+conv.getEstudianteId().getNombres() +" " +conv.getEstudianteId().getApellidos() );
					}
				}
				
			}
			listHabilitados= usuarioRemote.listarConvocatoriaEventEstuPorEvento(eventoSeleccionado);
			cargarTablaHabilitados(listHabilitados);
			return true;
		}
		
		
		 public void cargarTablaEvento(List<Evento> listPasada) {
			 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				for (Evento evt : listPasada) {
//					"Id", "Titulo", "Tipo de evento", "Modalidad del evento", "Localizacion", "Inicio", "Fin"
					fila[0] = evt.getId();
					fila[1] = evt.getTitulo();
					fila[2] = evt.getTipo().getTipo();
					fila[3] = evt.getModalidad().getModalidad();
					fila[4] = evt.getLocalizacion();
					fila[5] = formatter.format(evt.getInicio());
					fila[6] = formatter.format(evt.getFin());
					// Se añade al modelo la fila completa.
					modelo.addRow(fila);

				}
				autoAjustarTabla(table);
		 }
		 public void cargarTablaEstudiante(List<Estudiante> listPasada) {
				for (Estudiante est : listPasada) {
//					"Id", "CI","Nombre", "Apellido"
					filaEstudiante[0] = est.getId();
					filaEstudiante[1] = est.getDocumento().toString();
					filaEstudiante[2] = est.getNombres();
					filaEstudiante[3] = est.getApellidos();
					// Se añade al modelo la fila completa.
					modeloEstudiante.addRow(filaEstudiante);

				}
				autoAjustarTabla(tableEstudiante);
		 }
		
		 public void cargarTablaHabilitados(List<ConvocatoriaAsistenciaEventoEstudiante> listPasada) {
			 limpiarTablaHabilitados();
				for (ConvocatoriaAsistenciaEventoEstudiante habilitados : listPasada) {
//					
					filaHabilitados[0] = habilitados.getId();
					filaHabilitados[1] = habilitados.getEventoId().getTitulo();
					filaHabilitados[2] = habilitados.getEstudianteId().getNombres()+" "+habilitados.getEstudianteId().getApellidos();
					
					if(habilitados.getRegistroAsistencia()!=null) {
						filaHabilitados[3] = habilitados.getRegistroAsistencia().toString();
					}else {
						filaHabilitados[3] = "---";
					}
					if(habilitados.getCalificacion() != null) {
						filaHabilitados[4] = habilitados.getCalificacion();
					}else {
						filaHabilitados[4] ="---";
					}
					// Se añade al modelo la fila completa.
					modeloHabilitados.addRow(filaHabilitados);

				}
				autoAjustarTabla(tableHabilitados);
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
		 
		 
		public boolean validarEventoParaAgregarEstudiantes() {
			Date fechaActual =new Date(System.currentTimeMillis());
			if(eventoSeleccionado.getInicio().before(fechaActual)) {
				JOptionPane.showMessageDialog(null, "No es posible ingresar Estudiantes a un evento ya iniciado",
						"Informacion", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			
			
			return true;
		}
		
		public boolean validarEventoParaQuitarEstudiantes() {
			Date fechaActual =new Date(System.currentTimeMillis());
			if(eventoSeleccionado.getInicio().before(fechaActual)) {
				JOptionPane.showMessageDialog(null, "No es posible quitar Estudiantes de un evento ya iniciado",
						"Informacion", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			
			
			return true;
		} 
	}
