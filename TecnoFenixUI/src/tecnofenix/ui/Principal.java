package tecnofenix.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

//import tecnocanarios.dbm.*;
import javax.swing.JTextField;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.Analista;
import tecnofenix.entidades.Tutor;
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Usuario;
import tecnofenix.ui.UIConstancia;

//import tecnocanarios.dao.*;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Principal {

	public JFrame frame;



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();

					UILogin uiLogin = new UILogin();
					uiLogin.inicializar(window);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar(Usuario user ) {

		System.out.println("Programa inicializado");
		frame = new JFrame();
		frame.setBounds(100, 100, 676, 326);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		JMenuBar menuBar = new JMenuBar();
		
		
		JMenu usuario = new JMenu("Administracion Usuarios");
		
		
		JMenuItem adminDocentes= new JMenuItem("Administracion usuarios Docentes");
		

		JMenuItem adminAnalista= new JMenuItem("Administracion usuarios Analistas");
		

		JMenuItem adminEstudiantes= new JMenuItem("Administracion usuarios Estudiantes");
		adminEstudiantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIUsuario windowsUsuario = new UIUsuario();
				if(user instanceof Analista) {
					windowsUsuario.inicializar();
					windowsUsuario.frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Usuario sin permisos para acceder a este apartado",
							"Error", JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		
		JMenuItem pedirConstanciaEstudiante= new JMenuItem("Solicitar constancias");
		
		
		JMenuItem adminDatosPersonales= new JMenuItem("Editar datos cuenta");
		adminDatosPersonales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIUsuarioDatosPropios winUsuarioDP = new UIUsuarioDatosPropios();
				winUsuarioDP.inicializar(user);
				winUsuarioDP.frame.setVisible(true);
			}
		});
		
		

		
		JMenu constancia= new JMenu("Constancias");
		JMenuItem constAlta= new JMenuItem("Alta Constancias");
		JMenuItem constModa= new JMenuItem("Modificacion Constancias");
		JMenuItem constDeletea= new JMenuItem("Borrar Constancias");
		constDeletea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bajaConstancia baj = new bajaConstancia(frame);
				frame.pack();
				baj.setVisible(true);
				
			}
		});
		
		JMenuItem constListado = new JMenuItem("Listado Constancias");
		constListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UIConstancia windowsConstancia = new UIConstancia();
				windowsConstancia.inicializar(user);
				windowsConstancia.frame.setVisible(true);
				
			}
		});
		
		
		JMenu evento= new JMenu("Eventos");
		JMenuItem evenAlta= new JMenuItem("Alta Eventos");
		evenAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIEvento uiEvento = new UIEvento();
				uiEvento.inicializar();
				uiEvento.frame.setVisible(true);
			}
		});
		JMenuItem evenMod= new JMenuItem("Modificacion Eventos");
		JMenuItem evenDelete= new JMenuItem("Borrar Eventos");

		
		
		
		
		JMenu itr= new JMenu("ITR");
		JMenuItem itrAdministracion= new JMenuItem("Administrar ITR");
		itrAdministracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UIITR uiItr = new UIITR();
				uiItr.inicializar();
				uiItr.frame.setVisible(true);
			}
		});
		

		
		JMenu escolaridad= new JMenu("Escolaridad");
		JMenuItem escoAlta= new JMenuItem("Alta Escolaridad");
		JMenuItem escoMod= new JMenuItem("Modificacion Escolaridad");
		JMenuItem escoDelete= new JMenuItem("Borrar Escolaridad");
		
		
		
		
		usuario.add(adminDocentes);
		usuario.add(adminAnalista);
		usuario.add(adminEstudiantes);
		usuario.add(pedirConstanciaEstudiante);
		usuario.add(adminDatosPersonales);
		menuBar.add(usuario);
		
		
		 
		constancia.add(constAlta);
		constancia.add(constModa);
		constancia.add(constDeletea);
		constancia.add(constListado);
		menuBar.add(constancia);
		
		
		evento.add(evenAlta);
		evento.add(evenMod);
		evento.add(evenDelete);
		menuBar.add(evento);
		
		
		itr.add(itrAdministracion);
		menuBar.add(itr);
		
		
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

	}
}
