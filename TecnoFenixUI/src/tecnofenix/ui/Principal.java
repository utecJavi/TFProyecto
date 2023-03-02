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
				if(user instanceof Analista) {
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
					uiTipoTutor.inicializar();
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
					uiTipoTutorArea.inicializar();
					uiTipoTutorArea.frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Usuario sin permisos para acceder a este apartado",
							"Error", JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		JMenuItem adminTiposEstadoEvento= new JMenuItem("Administracion tipos de estados evento");
		adminTiposEstadoEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIEstadoDeEventos uiEstadoDeEventos = new UIEstadoDeEventos();
				if(user instanceof Analista) {
					uiEstadoDeEventos.inicializar();
					uiEstadoDeEventos.frame.setVisible(true);
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
				if(user instanceof Analista||user instanceof Tutor) {
				UIEventoAsistenciaEstudiante uiEventoAsistenciaEstudiante = new UIEventoAsistenciaEstudiante();
				uiEventoAsistenciaEstudiante.inicializar(user);
				uiEventoAsistenciaEstudiante.frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Usuario sin permisos para acceder a este apartado",
							"Error", JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		
		JMenuItem evenConvocados = new JMenuItem("Convocados a Eventos");
		evenConvocados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user instanceof Analista) {
				UIHabilitarEstudianteEvento uiHabEstEvnt = new UIHabilitarEstudianteEvento();
				uiHabEstEvnt.inicializar();
				uiHabEstEvnt.frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Usuario sin permisos para acceder a este apartado",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
//		JMenuItem evenDelete = new JMenuItem("Borrar Eventos");
		JMenuItem evenList = new JMenuItem("Listar Eventos");
		evenList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user instanceof Analista) {
				UIEvento uiEvento = new UIEvento();
				uiEvento.inicializar();
				uiEvento.frame.setVisible(true);
				}
				if(user instanceof Tutor) {
				UIEventoTutor uiEventoTutor = new UIEventoTutor();
				uiEventoTutor.inicializar((Tutor)user);
				uiEventoTutor.frame.setVisible(true);
				}
				if(user instanceof Estudiante)  {
				JOptionPane.showMessageDialog(null, "Usuario sin permisos para acceder a este apartado",
						"Error", JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		evenAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user instanceof Analista) {
				UIEventoNuevo uiEventoNuevo = new UIEventoNuevo();
				uiEventoNuevo.inicializar();
				uiEventoNuevo.frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Usuario sin permisos para acceder a este apartado",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		
		
		JMenu itr= new JMenu("ITR");
		JMenuItem itrAdministracion= new JMenuItem("Administrar ITR");
		itrAdministracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user instanceof Analista) {
				UIITR uiItr = new UIITR();
				uiItr.inicializar();
				uiItr.frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Usuario sin permisos para acceder a este apartado",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
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
				if(user instanceof Analista) {
				UIFuncionalidad uiFuncionalidad = new UIFuncionalidad();
				uiFuncionalidad.inicializar(user);
				uiFuncionalidad.frame.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "Usuario sin permisos para acceder a este apartado",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			}
		});
		JMenuItem rolAdministrar= new JMenuItem("Administrar Rol");
		rolAdministrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user instanceof Analista) {
				UIRol uirol = new UIRol();
				uirol.inicializar(user);
				uirol.frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Usuario sin permisos para acceder a este apartado",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		JMenuItem rolAsignarFunARol= new JMenuItem("Asignar funcionalidades a Rol");
		rolAsignarFunARol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user instanceof Analista) {
				UIRolHasFuncionalidad uiRolHasFun = new UIRolHasFuncionalidad();
				uiRolHasFun.inicializar(user);
				uiRolHasFun.frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Usuario sin permisos para acceder a este apartado",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
//		usuario.add(adminDocentes);
		
		usuario.add(adminUsuarios);
		usuario.add(adminTiposTutores);
		usuario.add(adminTiposAreas);
		usuario.add(adminTiposEstadoEvento);
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
				if(user instanceof Analista) {
				UIReportes uiReporte = new UIReportes();
				uiReporte.inicializar();
				uiReporte.frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Usuario sin permisos para acceder a este apartado",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
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
