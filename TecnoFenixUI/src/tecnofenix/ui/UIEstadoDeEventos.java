package tecnofenix.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UIEstadoDeEventos {

	
	public JFrame frame;
	private JTable tabla;
	private EJBUsuarioRemoto ejb;


	    
		/**
		 * @wbp.parser.entryPoint
		 */
		public void inicializar() {
	        System.out.println("Gestión de Estados de Eventos");
			
	        ejb = new EJBUsuarioRemoto();
			frame = new JFrame("Eventos");

			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(600, 400));
			frame.getContentPane().add(panel, BorderLayout.NORTH);
			panel.setLayout(null);
		}
	}

	   
