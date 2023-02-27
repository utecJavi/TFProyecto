package tecnofenix.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.TipoTutorArea;
import tecnofenix.entidades.TipoTutorEncargado;
import tecnofenix.entidades.TipoTutorTipo;
import tecnofenix.entidades.Tutor;
import tecnofenix.entidades.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UITipoTutor {
	
	public JFrame frame;
	public JPanel panel;
    private JTextField nameField;
    private JCheckBox activeCheckBox;
    private JButton createButton;
    
    private JTable tabla;
    private DefaultTableModel modelo;
	private Object[] filaTipoTutoEditable;
    
    
    private JTextField editNameField;
    private JCheckBox editActiveCheckBox;
    private JButton editButton;
    private JButton deleteButton;

    private List<TipoTutorTipo> tipoTutorTipo;
    private EJBUsuarioRemoto usuarioRemoto;


	/**
	 * @wbp.parser.entryPoint
	 */
    public void inicializar(Usuario user) {
    	
    	usuarioRemoto= new EJBUsuarioRemoto();
    	
    	frame = new JFrame("Listado de tutores");
    	frame.setTitle("Administracion de tipo tutores");

		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(800, 800));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        // create the panel and set its layout

        // create the input components
        nameField = new JTextField();
        activeCheckBox = new JCheckBox();
        activeCheckBox.setText("Baja logica");
        createButton = new JButton("Create");

        // add the input components to the panel
        JPanel inputPanel = new JPanel(new GridLayout(1, 3));
        inputPanel.setBounds(69, 25, 426, 21);
        inputPanel.add(nameField);
        inputPanel.add(activeCheckBox);
        inputPanel.add(createButton);
        panel.add(inputPanel);

        // create the table and set its model
        tabla = new JTable();
        modelo = new DefaultTableModel(new Object[]{"Id","Nombre", "Activo"}, 0);
        final String[] columnNames = { "Id", "CI","Nombre", "Apellido","Tipo","Area" };

		// insertamos las columnas
		for (int column = 0; column < columnNames.length; column++) {
			// agrega las columnas a la tabla
			modelo.addColumn(columnNames[column]);
		}

		// Se crea un array que será una de las filas de la tabla.
		filaTipoTutoEditable = new Object[columnNames.length];
		
        tabla.setModel(modelo);
        
        cargarListadoTiposDeTutor();
        
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(57, 57, 452, 265);
        panel.add(scrollPane);

        // create the output components
        editNameField = new JTextField();
        editActiveCheckBox = new JCheckBox();
        editActiveCheckBox.setText("Baja logica");
        editButton = new JButton("Editar");
        deleteButton = new JButton("Borrar");

        // add the output components to the panel
        JPanel outputPanel = new JPanel(new GridLayout(1, 4));
        outputPanel.setBounds(126, 332, 310, 21);
        outputPanel.add(editNameField);
        outputPanel.add(editActiveCheckBox);
        outputPanel.add(editButton);
        outputPanel.add(deleteButton);
        panel.add(outputPanel);

        // initialize the tipoTutorTipo list
        tipoTutorTipo = new ArrayList<>();

        // add action listeners to the buttons
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nameField.getText();
                boolean bajaLogica = activeCheckBox.isSelected();
                TipoTutorTipo item = new TipoTutorTipo(nombre, bajaLogica);
                tipoTutorTipo.add(item);
                modelo.addRow(new Object[]{"Id",nombre, bajaLogica});
                nameField.setText("");
                activeCheckBox.setSelected(false);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                int row = table.getSelectedRow();
//                if (row != -1) {
//                    String nombre = editNameField.getText();
//                    boolean bajaLogica = editActiveCheckBox.isSelected();
//                    TipoTutorTipo tipoTutor = tipoTutorTipo.get(row);
//                    tipoTutor.setNombre(nombre);
//                    tipoTutor.setBajaLogica(bajaLogica);
//                    tableModel.setValueAt(nombre, row, 0);
//                    tableModel.setValueAt(bajaLogica, row, 1);
//                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                int row = table.getSelectedRow();
//                if (row != -1) {
//                    tipoTutorTipo.remove(row);
//                    tableModel.removeRow(row);
//                    editNameField.setText("");
//                    editActiveCheckBox.setSelected(false);
//                }
            }
        });
        
        
        frame.pack();
		frame.setVisible(true);
    }

    

	public void limpiarTabla() {

		modelo.getDataVector().removeAllElements();
		modelo.fireTableDataChanged();

	}
    
    public void actualizarLista(List<TipoTutorTipo> listarTipoTutSel) {
		System.out.println("Entrando a cargar la tabla de TipoTutorTipo");
		limpiarTabla();
		this.tipoTutorTipo = listarTipoTutSel;
		// Se rellena cada posición del array con una de las columnas de la tabla en
		// base de datos.
		for (TipoTutorTipo tt : tipoTutorTipo) {
//			"Id", "CI","Nombre", "Apellido","Tipo","Area"
			filaTipoTutoEditable[0] = tt.getId();
			filaTipoTutoEditable[1] = tt.getNombre();
			filaTipoTutoEditable[2] = tt.getBajaLogica();

			// Se añade al modelo la fila completa.
			modelo.addRow(filaTipoTutoEditable);

		}
	} 
    
    public void cargarListadoTiposDeTutor() {
    	 try {
    	tipoTutorTipo = usuarioRemoto.listadoTipoTutorTipo(true);
    	actualizarLista(tipoTutorTipo);
	} catch (Exception e) {
		System.out.println(e);
	}
    }
   
    
    
}
