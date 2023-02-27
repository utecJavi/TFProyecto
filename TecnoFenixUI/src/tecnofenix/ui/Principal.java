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
		
		
			

		JMenuItem adminUsuarios= new JMenuItem("Administracion de usuarios");
		adminUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIUsuario windowsUsuario = new UIUsuario();
				if(user instanceof Analista ||user instanceof Tutor) {
					windowsUsuario.inicializar(user);
					windowsUsuario.frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Usuario sin permisos para acceder a este apartado",
							"Error", JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		
		JMenuItem adminTiposTutores= new JMenuItem("Administracion tipos de tutores");
		adminTiposTutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UITipoTutor uiTipoTutor = new UITipoTutor();
				if(user instanceof Analista) {
					uiTipoTutor.inicializar(user);
					uiTipoTutor.frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Usuario sin permisos para acceder a este apartado",
							"Error", JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		JMenuItem adminTiposAreas= new JMenuItem("Administracion tipos de areas tutores");
		adminTiposAreas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UITipoTutorArea uiTipoTutorArea = new UITipoTutorArea();
				if(user instanceof Analista) {
					uiTipoTutorArea.inicializar(user);
					uiTipoTutorArea.frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Usuario sin permisos para acceder a este apartado",
							"Error", JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		
//		JMenuItem pedirConstanciaEstudiante= new JMenuItem("Solicitar constancias");
		
		
		JMenuItem adminDatosPersonales= new JMenuItem("Editar datos cuenta");
		adminDatosPersonales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIUsuarioDatosPropios winUsuarioDP = new UIUsuarioDatosPropios();
				winUsuarioDP.inicializar(user);
				winUsuarioDP.frame.setVisible(true);
			}
		});
		
		

		
		JMenu constancia= new JMenu("Constancias");
		JMenuItem constListado = new JMenuItem("Listado Constancias");
		constListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UIConstancia windowsConstancia = new UIConstancia();
				windowsConstancia.inicializar(user);
				windowsConstancia.frame.setVisible(true);
				
			}
		});
		
		
		JMenu evento = new JMenu("Eventos");
		JMenuItem evenAlta = new JMenuItem("Alta Eventos");
		JMenuItem evenMod = new JMenuItem("Asistencia a Eventos");
		evenMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIEventoAsistenciaEstudiante uiEventoAsistenciaEstudiante = new UIEventoAsistenciaEstudiante();
				uiEventoAsistenciaEstudiante.inicializar();
				uiEventoAsistenciaEstudiante.frame.setVisible(true);
			}
		});
		
		JMenuItem evenConvocados = new JMenuItem("Convocados a Eventos");
		evenConvocados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIHabilitarEstudianteEvento uiHabEstEvnt = new UIHabilitarEstudianteEvento();
				uiHabEstEvnt.inicializar();
				uiHabEstEvnt.frame.setVisible(true);
			}
		});
//		JMenuItem evenDelete = new JMenuItem("Borrar Eventos");
		JMenuItem evenList = new JMenuItem("Listar Eventos");
		evenList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIEvento uiEvento = new UIEvento();
				uiEvento.inicializar();
				uiEvento.frame.setVisible(true);
			}
		});
		evenAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIEventoNuevo uiEventoNuevo = new UIEventoNuevo();
				uiEventoNuevo.inicializar();
				uiEventoNuevo.frame.setVisible(true);
			}
		});
		
		
		
		
		JMenu itr= new JMenu("ITR");
		JMenuItem itrAdministracion= new JMenuItem("Administrar ITR");
		itrAdministracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UIITR uiItr = new UIITR();
				uiItr.inicializar();
				uiItr.frame.setVisible(true);
			}
		});
		

		/*
		JMenu escolaridad= new JMenu("Escolaridad");
		JMenuItem escoAlta= new JMenuItem("Alta Escolaridad");
		JMenuItem escoMod= new JMenuItem("Modificacion Escolaridad");
		JMenuItem escoDelete= new JMenuItem("Borrar Escolaridad");
		*/
		JMenu funcionalidad= new JMenu("Funcionalidad");
		JMenuItem funAdministracion= new JMenuItem("Administrar Funcionalidades");
		funAdministracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UIFuncionalidad uiFuncionalidad = new UIFuncionalidad();
				uiFuncionalidad.inicializar(user);
				uiFuncionalidad.frame.setVisible(true);
			}
		});
		JMenuItem rolAdministrar= new JMenuItem("Administrar Rol");
		rolAdministrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UIRol uirol = new UIRol();
				uirol.inicializar(user);
				uirol.frame.setVisible(true);
			}
		});
		JMenuItem rolAsignarFunARol= new JMenuItem("Asignar funcionalidades a Rol");
		rolAsignarFunARol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UIRolHasFuncionalidad uiRolHasFun = new UIRolHasFuncionalidad();
				uiRolHasFun.inicializar(user);
				uiRolHasFun.frame.setVisible(true);
			}
		});
//		usuario.add(adminDocentes);
		
		usuario.add(adminUsuarios);
		usuario.add(adminTiposTutores);
		usuario.add(adminTiposAreas);
//		usuario.add(pedirConstanciaEstudiante);
		usuario.add(adminDatosPersonales);
		menuBar.add(usuario);
		
		
		constancia.add(constListado);
		
		menuBar.add(constancia);
		
		
		evento.add(evenAlta);
		evento.add(evenConvocados);
		evento.add(evenMod);
//		evento.add(evenDelete);
		evento.add(evenList);
		menuBar.add(evento);
		
		
		itr.add(itrAdministracion);
		menuBar.add(itr);
		
		if (user instanceof Analista) {
			funcionalidad.add(funAdministracion);
			funcionalidad.add(rolAdministrar);
			funcionalidad.add(rolAsignarFunARol);
			
			menuBar.add(funcionalidad);
			
			
		}
		
		
		/*
		escolaridad.add(escoAlta);
		escolaridad.add(escoMod);
		escolaridad.add(escoDelete);
		menuBar.add(escolaridad);
		*/
		JMenu reportes= new JMenu("Reportes");
		JMenuItem reportesAsistenciaEstudiantes= new JMenuItem("Asistencia de estudiantes a eventos");
		reportesAsistenciaEstudiantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UIReportes uiReporte = new UIReportes();
				uiReporte.inicializar();
				uiReporte.frame.setVisible(true);
			}
		});
		reportes.add(reportesAsistenciaEstudiantes);
		
		JMenuItem escolaridad = new JMenuItem("Escolaridad");
		escolaridad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (user instanceof Estudiante) {
					UIEscolaridad verEscolaridad = new UIEscolaridad();
					verEscolaridad.inicializar((Estudiante)user);
					verEscolaridad.frame.setVisible(true);
				} else {
					UIListaEstudianteEscolaridad listadoEstudiantesEsc = new UIListaEstudianteEscolaridad();
					listadoEstudiantesEsc.inicializar();
					listadoEstudiantesEsc.frame.setVisible(true);
				}
			}
		});
		reportes.add(escolaridad);
		
		menuBar.add(reportes);
		

		
		frame.setJMenuBar(menuBar);

		//Aca abrimos la ventana de usuario 
		
		usuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Abriendo ventana usuarios");

			}
		});

	}
}
