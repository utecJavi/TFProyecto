package tecnofenix.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import tecnofenix.entidades.TipoArea;
import tecnofenix.entidades.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UITipoTutorArea {
  
    	public JFrame frame;
    	public JPanel panel;
        private JTextField nameField;
        private JCheckBox activeCheckBox;
        private JButton createButton;
        private JTable table;
        private DefaultTableModel tableModel;
        private JTextField editNameField;
        private JCheckBox editActiveCheckBox;
        private JButton editButton;
        private JButton deleteButton;

        private ArrayList<TipoArea> tipoArea;


    	/**
    	 * @wbp.parser.entryPoint
    	 */
        public void inicializar(Usuario user) {
        	
        	frame = new JFrame("Administracion tipo de areas");
  
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
            table = new JTable();
            tableModel = new DefaultTableModel(new Object[]{"Name", "Active"}, 0);
            table.setModel(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(57, 57, 452, 265);
            panel.add(scrollPane);

            // create the output components
            editNameField = new JTextField();
            editActiveCheckBox = new JCheckBox();
            editActiveCheckBox.setText("Baja logica");
            editButton = new JButton("Edit");
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
            tipoArea = new ArrayList<>();

            // add action listeners to the buttons
            createButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nombre = nameField.getText();
                    boolean bajaLogica = activeCheckBox.isSelected();
                    TipoArea item = new TipoArea(nombre, bajaLogica);
                    tipoArea.add(item);
                    tableModel.addRow(new Object[]{nombre, bajaLogica});
                    nameField.setText("");
                    activeCheckBox.setSelected(false);
                }
            });

            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        String nombre = editNameField.getText();
                        boolean bajaLogica = editActiveCheckBox.isSelected();
                        TipoArea tipoTutor = tipoArea.get(row);
                        tipoTutor.setNombre(nombre);
                        tipoTutor.setBajaLogica(bajaLogica);
                        tableModel.setValueAt(nombre, row, 0);
                        tableModel.setValueAt(bajaLogica, row, 1);
                    }
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        tipoArea.remove(row);
                        tableModel.removeRow(row);
                        editNameField.setText("");
                        editActiveCheckBox.setSelected(false);
                    }
                }
            });
            
            
            
            frame.pack();
    		frame.setVisible(true);
        }


    }