package tecnofenix.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

//import tecnocanarios.dbm.*;
import javax.swing.JTextField;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;

//import tecnocanarios.dao.*;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Principal {

	public JFrame frame;
//	private JButton btnUIPersona;
//	private JButton btnUIFUncionalidad;
//	private JButton btnUIRol;
//	private JButton btnAdministracionDeRoles;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();

					UILogin uiLogin = new UILogin();
					uiLogin.inicializar(window);
//					window.frame.setVisible(true);
					EJBUsuarioRemoto ejbusu = new EJBUsuarioRemoto();
					ejbusu.ejecutarMetodo();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public Principal() {
		inicializar();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {

		System.out.println("Programa inicializado");
		frame = new JFrame();
		frame.setBounds(100, 100, 676, 326);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu jmenu, submenu;
		JMenuItem menuUsuario, i2, i3, i4, i5;
		jmenu = new JMenu("Menu");
		submenu = new JMenu("Sub Menu");
		menuUsuario = new JMenuItem("Usuario");

		i2 = new JMenuItem("Item 2");
		i3 = new JMenuItem("Item 3");
		i4 = new JMenuItem("Item 4");
		i5 = new JMenuItem("Item 5");
		jmenu.add(menuUsuario);
		jmenu.add(i2);
		jmenu.add(i3);
		submenu.add(i4);
		submenu.add(i5);
		jmenu.add(submenu);
		menuBar.add(jmenu);
		frame.setJMenuBar(menuBar);

		//Aca abrimos la ventana de usuario 
		menuUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Abriendo ventana usuarios");
			}
		});
		
		
		
//		btnUIPersona = new JButton("Personas");
//		btnUIPersona.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				UIPersona uiPersona = new UIPersona();
//				uiPersona.inicializar();
//			}
//		});
//		btnUIPersona.setBounds(57, 42, 238, 21);
//		frame.getContentPane().add(btnUIPersona);
//		
//		btnUIRol = new JButton("Roles");
//		btnUIRol.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				UIRol uiRol = new UIRol();
//				uiRol.inicializar();
//			}
//		});
//		btnUIRol.setBounds(57, 73, 238, 21);
//		frame.getContentPane().add(btnUIRol);
//		
//		btnUIFUncionalidad = new JButton("Funcionalidades");
//		btnUIFUncionalidad.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				UIFuncionalidad uiFuncionalidad = new UIFuncionalidad();
//				uiFuncionalidad.inicializar();
//			}
//		});
//		btnUIFUncionalidad.setBounds(57, 104, 238, 21);
//		frame.getContentPane().add(btnUIFUncionalidad);
//		
//		btnAdministracionDeRoles = new JButton("Administracion de Roles");
//		btnAdministracionDeRoles.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				UIRolHasFuncionalidad uiRolHasFun = new UIRolHasFuncionalidad();
//				uiRolHasFun.inicializar();
//			}
//		});
//		btnAdministracionDeRoles.setBounds(57, 139, 238, 21);
//		frame.getContentPane().add(btnAdministracionDeRoles);

	}
}
