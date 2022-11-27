package tecnofenix.ui;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.*;

public class UIEvento {


	public JFrame frame;
	private JTable tablaEventos;
	private EJBUsuarioRemoto ejb;

	private JTextField textId;
	private JTextField textTitulo;
	private JComboBox<TipoEvento> comboBoxTipoEvento;
	private JComboBox<ModalidadEvento> comboBoxModalidadEvento;
	// este quizas sea un JList
	private JComboBox<Set<Tutor>> comboBoxTutores;
	// este quizas sea un JList
	private JComboBox<Set<Analista>> comboBoxAnalistas;
	private JDateChooser dateFechaInicio;
	private JDateChooser dateFechaFin;
	private JTextField textLocalizacion;
	private JComboBox<Itr> itrEventoComboBox;

	private JTextField editarTextId;
	private JTextField editarTextTitulo;
	private JComboBox<TipoEvento> editarComboBoxTipoEvento;
	private JComboBox<ModalidadEvento> editarComboBoxModalidadEvento;
	// este quizas sea un JList
	private JComboBox<Set<Tutor>> editarComboBoxTutores;
	// este quizas sea un JList
	private JComboBox<Set<Analista>> editarComboBoxAnalistas;
	private JDateChooser editarDateFechaInicio;
	private JDateChooser editarDateFechaFin;
	private JTextField editarTextLocalizacion;
	private JComboBox<Itr> editarItrEventoComboBox;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {


		ejb = new EJBUsuarioRemoto();
		frame = new JFrame("Eventos");

		JPanel panel = new JPanel();

		panel.setPreferredSize(new Dimension(800, 800));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
	
		panel.setLayout(null);
		
		DefaultTableModel tableModel = new DefaultTableModel(new String[] {"Id", "Titulo", "Tipo de evento", "Modalidad del evento", "Localizacion", "Inicio", "Fin"}, 0) {
			 @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			 }
		};
//		generateRows(tableModel);
		tablaEventos = new JTable();
		tablaEventos.setModel(tableModel);
		tablaEventos.setCellSelectionEnabled(false);
		tablaEventos.setRowSelectionAllowed(true);
		tablaEventos.setForeground(Color.GREEN);
		tablaEventos.setColumnSelectionAllowed(false);
		tablaEventos.setBackground(Color.BLACK);
	
		tablaEventos.setBounds(93, 215, 100, 100);	

//		panel.add(tablaUsuarios);
//
//		frame.pack();
//		table.setDefaultEditor(Object.class, null);
	
		tablaEventos.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
	        public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && tablaEventos.getSelectedRow() != -1) {
				System.out.println("ACTUALIZANDO CLICK");
//	            textEditarId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
//	            textEditarNom.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
//	            textEditarDepto.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				}
	        }
	    });
		// Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(tablaEventos);
		scrollPane.setBounds(10, 103, 780, 299);
		// definimos un layout
		// Agregamos el JScrollPane al contenedor
		panel.add(scrollPane);

		JButton crearEventoBtn = new JButton("Crear evento");
		crearEventoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Evento evento = new Evento(
						null,
						Date.valueOf(LocalDate.now()),
						textTitulo.getText(),
						comboBoxTipoEvento.getItemAt(comboBoxTipoEvento.getSelectedIndex()),
						comboBoxModalidadEvento.getItemAt(comboBoxModalidadEvento.getSelectedIndex()),
						textLocalizacion.getText(), 
						ejb.buscarItrPor(((Itr) Objects.requireNonNull(itrEventoComboBox.getSelectedItem())).getId().toString(), null, null).get(0));
				ejb.crearEvento(evento);
			}
		});
		crearEventoBtn.setBounds(349, 520, 113, 21);
		panel.add(crearEventoBtn);

		textTitulo = new JTextField();
		textTitulo.setBounds(10, 449, 202, 19);
		textTitulo.setColumns(10);
		panel.add(textTitulo);

		textLocalizacion = new JTextField();
		textLocalizacion.setBounds(425, 449, 188, 19);
		textLocalizacion.setColumns(10);
		panel.add(textLocalizacion);

		comboBoxTipoEvento = new JComboBox<>();
		comboBoxTipoEvento.setModel(new DefaultComboBoxModel<TipoEvento>(TipoEvento.values()));
		comboBoxTipoEvento.setBounds(227, 449, 188, 19);
		panel.add(comboBoxTipoEvento);

		comboBoxModalidadEvento = new JComboBox<>();
		comboBoxModalidadEvento.setModel(new DefaultComboBoxModel<ModalidadEvento>(ModalidadEvento.values()));
		comboBoxModalidadEvento.setBounds(227, 493, 188, 19);
		panel.add(comboBoxModalidadEvento);

		dateFechaInicio = new JDateChooser();
		dateFechaInicio.setBounds(10, 493, 96, 19);
		panel.add(dateFechaInicio);

		dateFechaFin = new JDateChooser();
		dateFechaFin.setBounds(116, 492, 96, 19);
		panel.add(dateFechaFin);



		// todo: rehacer esto de buscar
//		textBuscarDept = new JTextField();
//		textBuscarDept.setColumns(10);
//		textBuscarDept.setBounds(406, 61, 188, 19);
//		textBuscarDept.setText("");
//		panel.add(textBuscarDept);
//
//		textBuscarNomb = new JTextField();
//		textBuscarNomb.setColumns(10);
//		textBuscarNomb.setBounds(208, 61, 188, 19);
//		textBuscarDept.setText("");
//		panel.add(textBuscarNomb);
//
//		JLabel lblNewLabel_1_1_1 = new JLabel("Id");
//		lblNewLabel_1_1_1.setBounds(10, 48, 74, 13);
//		panel.add(lblNewLabel_1_1_1);
//
//		textBuscarId = new JTextField();
//		textBuscarId.setColumns(10);
//		textBuscarId.setBounds(10, 61, 188, 19);
//		textBuscarId.setText("");
//		panel.add(textBuscarId);
//
//		JButton btnBuscar = new JButton("Buscar");
//		btnBuscar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				buscarPor(textBuscarId.getText() ,textBuscarNomb.getText(),textBuscarDept.getText());
//			}
//		});
//		btnBuscar.setBounds(677, 60, 113, 21);
//		panel.add(btnBuscar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 91, 790, 2);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Filtros de busqueda");
		lblNewLabel.setBounds(10, 10, 113, 13);
		panel.add(lblNewLabel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 426, 780, 2);
		panel.add(separator_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Id");
		lblNewLabel_1_1_1_1_1.setBounds(10, 569, 74, 13);
		panel.add(lblNewLabel_1_1_1_1_1);


		// campos de editar
		editarTextId = new JTextField();
		editarTextId.setText("");
		editarTextId.setEnabled(false);
		editarTextId.setColumns(10);
		editarTextId.setBounds(10, 582, 188, 19);
		panel.add(editarTextId);

		editarTextTitulo = new JTextField();
		editarTextTitulo.setColumns(10);
		editarTextTitulo.setBounds(10, 621, 188, 19);
		panel.add(editarTextTitulo);
		
		editarComboBoxTipoEvento = new JComboBox<>();
		editarComboBoxTipoEvento.setModel(new DefaultComboBoxModel<TipoEvento>());
		editarComboBoxTipoEvento.setBounds(227, 582, 188, 19);
		panel.add(editarComboBoxTipoEvento);

		editarComboBoxModalidadEvento = new JComboBox<>();
		editarComboBoxModalidadEvento.setModel(new DefaultComboBoxModel<ModalidadEvento>());
		editarComboBoxModalidadEvento.setBounds(227, 621, 188, 19);
		panel.add(editarComboBoxModalidadEvento);

		editarTextLocalizacion = new JTextField();
		editarTextLocalizacion.setBounds(227, 657, 188, 19);
		editarTextLocalizacion.setColumns(10);
		panel.add(editarTextLocalizacion);

		editarDateFechaInicio = new JDateChooser();
		editarDateFechaInicio.setBounds(10, 657, 96, 19);
		panel.add(editarDateFechaInicio);

		editarDateFechaFin = new JDateChooser();
		editarDateFechaFin.setBounds(116, 657, 96, 19);
		panel.add(editarDateFechaFin);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				editar(textEditarId.getText() ,textEditarNom.getText(),textEditarDepto.getText());
				
			}
		});
		btnEditar.setBounds(374, 718, 113, 21);
		panel.add(btnEditar);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 551, 767, 2);
		panel.add(separator_2);
		
		JLabel tituloEventoLabel = new JLabel("Titulo");
		tituloEventoLabel.setBounds(10, 438, 87, 13);
		panel.add(tituloEventoLabel);
		
		JLabel fechaInicioEventoLabel = new JLabel("Fecha de inicio");
		fechaInicioEventoLabel.setBounds(10, 478, 96, 13);
		panel.add(fechaInicioEventoLabel);
		
		JLabel fechaFinEventoLabel = new JLabel("Fecha de finalizacion");
		fechaFinEventoLabel.setBounds(117, 478, 95, 13);
		panel.add(fechaFinEventoLabel);
		
		JLabel tipoEventoLabel = new JLabel("Tipo de evento");
		tipoEventoLabel.setBounds(227, 438, 188, 13);
		panel.add(tipoEventoLabel);
		
		JLabel modalidadEventoLabel = new JLabel("Modalidad del evento");
		modalidadEventoLabel.setBounds(227, 478, 188, 13);
		panel.add(modalidadEventoLabel);
		
		JLabel localizacionEventoLabel = new JLabel("Localizacion del evento");
		localizacionEventoLabel.setBounds(425, 438, 188, 13);
		panel.add(localizacionEventoLabel);
		
		JLabel editarTituloLabel = new JLabel("Titulo");
		editarTituloLabel.setBounds(10, 611, 45, 13);
		panel.add(editarTituloLabel);
		
		JLabel editarFechaInicioLabel = new JLabel("Fecha de inicio");
		editarFechaInicioLabel.setBounds(10, 645, 96, 13);
		panel.add(editarFechaInicioLabel);
		
		JLabel editarFechaFinLabel = new JLabel("Fecha de finalizacion");
		editarFechaFinLabel.setBounds(115, 645, 97, 13);
		panel.add(editarFechaFinLabel);
		
		JLabel editarTipoEventoLabel = new JLabel("Tipo de evento");
		editarTipoEventoLabel.setBounds(227, 569, 188, 13);
		panel.add(editarTipoEventoLabel);
		
		JLabel editarModalidadEventoLabel = new JLabel("Modalidad del evento");
		editarModalidadEventoLabel.setBounds(227, 611, 188, 13);
		panel.add(editarModalidadEventoLabel);
		
		JLabel editarLocalizacionEventoLabel = new JLabel("Localizacion del evento");
		editarLocalizacionEventoLabel.setBounds(227, 645, 188, 13);
		panel.add(editarLocalizacionEventoLabel);
		
		itrEventoComboBox = new JComboBox<Itr>();
		itrEventoComboBox.setModel(generateItrEventoComboBoxData());
		itrEventoComboBox.setBounds(425, 492, 188, 21);
		panel.add(itrEventoComboBox);
		
		JLabel itrEventoComboBoxLabel = new JLabel("ITR");
		itrEventoComboBoxLabel.setBounds(425, 478, 45, 13);
		panel.add(itrEventoComboBoxLabel);
		
		editarItrEventoComboBox = new JComboBox<Itr>();
		editarItrEventoComboBox.setModel(generateItrEventoComboBoxData());
		editarItrEventoComboBox.setBounds(425, 581, 188, 21);
		panel.add(editarItrEventoComboBox);
		
		JLabel editarItrEventoComboBoxLabel = new JLabel("ITR");
		editarItrEventoComboBoxLabel.setBounds(425, 569, 45, 13);
		panel.add(editarItrEventoComboBoxLabel);

		frame.pack();
//		frame.setVisible(true);
		
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
	
	private DefaultComboBoxModel<Itr> generateItrEventoComboBoxData() {
		DefaultComboBoxModel<Itr> comboBoxModel = new DefaultComboBoxModel<>();
		ejb.listarITR().forEach(comboBoxModel::addElement);
		return comboBoxModel;
	}
}
