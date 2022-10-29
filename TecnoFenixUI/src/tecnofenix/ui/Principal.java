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
//					EJBUsuarioRemoto ejbusu = new EJBUsuarioRemoto();
//					ejbusu.ejecutarMetodo();

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
		
		
		JMenu usuario;
		usuario = new JMenu("Administracion Usuarios");
		JMenuItem usuAltDocente= new JMenuItem("Alta Docente");
		JMenuItem usuModDocente= new JMenuItem("Modificacion Docente");
		JMenuItem usuAltEstudiante= new JMenuItem("Alta Estudiante");
		usuAltEstudiante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIUsuario windowsUsuario = new UIUsuario();
				windowsUsuario.inicializar();
				windowsUsuario.frame.setVisible(true);
			}
		});
		JMenuItem usuModEstudiante= new JMenuItem("Modificacion Estudiante");
		usuario.add(usuAltDocente);
		usuario.add(usuModDocente);
		usuario.add(usuAltEstudiante);
		usuario.add(usuModEstudiante);
		menuBar.add(usuario);
		
		JMenu constancia;
		constancia = new JMenu("Constancias");
		JMenuItem constAlta= new JMenuItem("Alta Constancias");
		JMenuItem constModa= new JMenuItem("Modificacion Constancias");
		JMenuItem constDeletea= new JMenuItem("Borrar Constancias");
		constDeletea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bajaConstancia baj = new bajaConstancia(frame);
				baj.setVisible(true);
				
			}
		});
		constancia.add(constAlta);
		constancia.add(constModa);
		constancia.add(constDeletea);
		menuBar.add(constancia);
		
		JMenu evento;
		JMenuItem evenAlta= new JMenuItem("Alta Eventos");
		JMenuItem evenMod= new JMenuItem("Modificacion Eventos");
		JMenuItem evenDelete= new JMenuItem("Borrar Eventos");
		evento= new JMenu("Eventos");
		evento.add(evenAlta);
		evento.add(evenMod);
		evento.add(evenDelete);
		menuBar.add(evento);
		
		JMenu itr;
		JMenuItem itrAlta= new JMenuItem("Alta ITR");
		JMenuItem itrMod= new JMenuItem("Modificacion ITR");
		JMenuItem itrDelete= new JMenuItem("Borrar ITR");
		itr= new JMenu("ITR");
		itr.add(itrAlta);
		itr.add(itrMod);
		itr.add(itrDelete);
		menuBar.add(itr);
		
		JMenu escolaridad;
		JMenuItem escoAlta= new JMenuItem("Alta Escolaridad");
		JMenuItem escoMod= new JMenuItem("Modificacion Escolaridad");
		JMenuItem escoDelete= new JMenuItem("Borrar Escolaridad");
		escolaridad= new JMenu("Escolaridad");
		escolaridad.add(escoAlta);
		escolaridad.add(escoMod);
		escolaridad.add(escoDelete);
		menuBar.add(escolaridad);

		
		frame.setJMenuBar(menuBar);

		//Aca abrimos la ventana de usuario 
		
		usuario.addActionListener(new ActionListener() {
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
