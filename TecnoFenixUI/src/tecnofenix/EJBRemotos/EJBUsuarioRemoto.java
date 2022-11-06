package tecnofenix.EJBRemotos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tecnofenix.entidades.*;
import tecnofenix.entidades.Analista;
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Tutor;
import tecnofenix.entidades.Usuario;
import tecnofenix.exception.ServiciosException;
import tecnofenix.exception.UsuarioNoEncontradoException;
import tecnofenix.interfaces.*;
import tecnofenix.servicios.ConexionClienteJNDIRemote;


public class EJBUsuarioRemoto {

	private static String CONEXION_CLIENTE_EJB = "ejb:/TecnoFenixEJB/ConexionClienteJNDI!tecnofenix.servicios.ConexionClienteJNDIRemote";
	private static String RUTA_USUARIO_EJB = "ejb:/TecnoFenixEJB/UsuarioBean!tecnofenix.interfaces.UsuarioBeanRemote";
	private static String RUTA_USUARIO_ESTUDIANTE_EJB = "ejb:/TecnoFenixEJB/EstudianteBean!tecnofenix.interfaces.EstudianteBeanRemote";
	private static String RUTA_USUARIO_TUTOR_EJB = "ejb:/TecnoFenixEJB/TutorBean!tecnofenix.interfaces.TutorBeanRemote";
	private static String RUTA_USUARIO_ANALISTA_EJB = "ejb:/TecnoFenixEJB/AnalistaBean!tecnofenix.interfaces.AnalistaBeanRemote";
	private static String RUTA_ITR_EJB = "ejb:/TecnoFenixEJB/ItrBean!tecnofenix.interfaces.ItrBeanRemote";
	private static String RUTA_ACCION_CONSTANCIA_EJB = "ejb:/TecnoFenixEJB/AccionConstanciaBean!tecnofenix.interfaces.AccionConstanciaBeanRemote";
	private static String RUTA_ACCION_JUSTIFICACION_EJB = "ejb:/TecnoFenixEJB/AccionJustificacionBean!tecnofenix.interfaces.AccionJustificacionBeanRemote";
	private static String RUTA_ACCION_RECLAMO_EJB = "ejb:/TecnoFenixEJB/AccionReclamoBean!tecnofenix.interfaces.AccionReclamoBeanRemote";
	private static String RUTA_CONSTANCIA_EJB = "ejb:/TecnoFenixEJB/ConstanciaBean!tecnofenix.interfaces.ConstanciaBeanRemote";
	private static String RUTA_CONVOC_ASIST_EVEN_ESTUDIANTE_EJB = "ejb:/TecnoFenixEJB/ConvocatoriaAsistenciaEventoEstudianteBean!tecnofenix.interfaces.ConvocatoriaAsistenciaEventoEstudianteBeanRemote";
	private static String RUTA_EVENTO_EJB = "ejb:/TecnoFenixEJB/EventoBean!tecnofenix.interfaces.EventoBeanRemote";
	private static String RUTA_GESTION_EVENTO_ANALISTA_EJB = "ejb:/TecnoFenixEJB/GestionEventoAnalistaBean!tecnofenix.interfaces.GestionEventoAnalistaBeanRemote";
	private static String RUTA_JUSTIFICACION_EJB = "ejb:/TecnoFenixEJB/JustificacionBean!tecnofenix.interfaces.JustificacionBeanRemote";
	private static String RUTA_RECLAMO_EJB = "ejb:/TecnoFenixEJB/ReclamoBean!tecnofenix.interfaces.ReclamoBeanRemote";
	private static String RUTA_TUTOR_RESPONSABLE_EVENTO_EJB = "ejb:/TecnoFenixEJB/TutorResponsableEventoBean!tecnofenix.interfaces.TutorResponsableEventoBeanRemote";

	@EJB
	ConexionClienteJNDIRemote claseRemota;
	@EJB
	UsuarioBeanRemote usuarioRemote;
	@EJB
	EstudianteBeanRemote estudianteRemote;
	@EJB
	ItrBeanRemote itrRemote;
	@EJB
	AccionConstanciaBeanRemote accionConstanciaBeanRemote;
	@EJB
	AccionJustificacionBeanRemote accionJustificacionBeanRemote;
	@EJB
	AccionReclamoBeanRemote accionReclamoBeanRemote;
	@EJB
	AnalistaBeanRemote analistaBeanRemote;
	@EJB
	ConstanciaBeanRemote constanciaBeanRemote;
	@EJB
	ConvocatoriaAsistenciaEventoEstudianteBeanRemote convocatoriaAsistenciaEventoEstudianteBeanRemote;
	@EJB
	EventoBeanRemote eventoBeanRemote;
	@EJB
	GestionEventoAnalistaBeanRemote gestionEventoAnalistaBeanRemote;
	@EJB
	JustificacionBeanRemote justificacionBeanRemote;
	@EJB
	ReclamoBeanRemote reclamoBeanRemote;
	@EJB
	TutorBeanRemote tutorBeanRemote;
	@EJB
	TutorResponsableEventoBeanRemote tutorResponsableEventoBeanRemote;

	public EJBUsuarioRemoto() {
		try {
			InitialContext ctx = new InitialContext();
			// Instanciamos las interfaces remotas con el lookup
			claseRemota = (ConexionClienteJNDIRemote) ctx.lookup(CONEXION_CLIENTE_EJB);
			usuarioRemote = (UsuarioBeanRemote) ctx.lookup(RUTA_USUARIO_EJB);
			estudianteRemote = (EstudianteBeanRemote) ctx.lookup(RUTA_USUARIO_ESTUDIANTE_EJB);
			tutorBeanRemote = (TutorBeanRemote) ctx.lookup(RUTA_USUARIO_TUTOR_EJB);
			analistaBeanRemote = (AnalistaBeanRemote) ctx.lookup(RUTA_USUARIO_ANALISTA_EJB);
			itrRemote = (ItrBeanRemote) ctx.lookup(RUTA_ITR_EJB);
			accionConstanciaBeanRemote = (AccionConstanciaBeanRemote) ctx.lookup(RUTA_ACCION_CONSTANCIA_EJB);
			accionJustificacionBeanRemote = (AccionJustificacionBeanRemote) ctx.lookup(RUTA_ACCION_JUSTIFICACION_EJB);
			accionReclamoBeanRemote = (AccionReclamoBeanRemote) ctx.lookup(RUTA_ACCION_RECLAMO_EJB);
			constanciaBeanRemote = (ConstanciaBeanRemote) ctx.lookup(RUTA_CONSTANCIA_EJB);
			convocatoriaAsistenciaEventoEstudianteBeanRemote = (ConvocatoriaAsistenciaEventoEstudianteBeanRemote) ctx.lookup(RUTA_CONVOC_ASIST_EVEN_ESTUDIANTE_EJB);
			eventoBeanRemote = (EventoBeanRemote) ctx.lookup(RUTA_EVENTO_EJB);
			gestionEventoAnalistaBeanRemote = (GestionEventoAnalistaBeanRemote) ctx.lookup(RUTA_GESTION_EVENTO_ANALISTA_EJB);
			justificacionBeanRemote = (JustificacionBeanRemote) ctx.lookup(RUTA_JUSTIFICACION_EJB);
			reclamoBeanRemote = (ReclamoBeanRemote) ctx.lookup(RUTA_RECLAMO_EJB);
			tutorResponsableEventoBeanRemote = (TutorResponsableEventoBeanRemote) ctx.lookup(RUTA_TUTOR_RESPONSABLE_EVENTO_EJB);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ejecutarMetodo() {
		System.out.println(claseRemota.levantando());

	}

	public Usuario login(String usu, String pass) {
		Usuario logeado = null;
		System.out.println("Verificando Login");
		try {
			logeado =  usuarioRemote.login(usu, pass);
		} catch (Exception e) {
			System.err.println(e);
		}

		System.out.println("Usuario RETORNADO");
		return logeado;
	}

	public Usuario login(Usuario usuario) {

		System.out.println("Verificando Login");
		try {
//			usuario=usuarioRemote.login(usuario);
		} catch (Exception e) {
			System.err.println(e);
		}

		System.out.println("Usuario RETORNADO");
		return usuario;
	}

	public Usuario crearUsuario(Usuario usuario) {

		try {
			System.out.println("LEVANTANDOOO USUARIO");
			usuario = usuarioRemote.crearUsuario(usuario);

		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Usuario RETORNADO");
		return usuario;
	}

	/*
	 * METODOS ESTUDIANTES REMOTOS
	 */
	public Estudiante crearEstudiante(Estudiante estudiante) {

		try {
			estudiante = estudianteRemote.crearEstudiante(null);
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estudiante;

	}

	public List<Estudiante> listarEstudiantes() {
		List<Estudiante> lista = new ArrayList<Estudiante>();
		try {
			lista = estudianteRemote.listarEstudiantes();
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;

	}
	/*
	 * METODOS ESTUDIANTES REMOTOS FIN
	 */
	/*
	 * METODOS ITR REMOTOS
	 */

	public Itr crearITR(Itr itr) {
		Itr itrDevueto = new Itr();
		try {
			itrDevueto = itrRemote.crearItr(itr);
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itrDevueto;

	}
	public Itr editarITR(Itr itr) {
		Itr itrDevueto = new Itr();
		try {
			itrDevueto = itrRemote.modificarItr(itr);
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itrDevueto;

	}

	public List<Itr> listarITR() {
		List<Itr> lista = new ArrayList<Itr>();
		try {
			lista = itrRemote.listarItr();
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;

	}
	public List<Itr> buscarItrPor(String id, String nombre ,String depto) {
		List<Itr> lista = new ArrayList<Itr>();
		try {
			lista = itrRemote.buscarPor(id,nombre,depto);
		} catch (Exception e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}
		return lista;
		
	}
	/*
	 * METODOS ITR REMOTOS FIN
	 */

	public Estudiante modificarEstudiantePropio(Estudiante estudiante) throws ServiciosException, UsuarioNoEncontradoException {
		return estudianteRemote.modificarEstudiantePropio(estudiante);
	}

	public Analista modificarAnalistaPropio(Analista analista) throws ServiciosException, UsuarioNoEncontradoException {
		return analistaBeanRemote.modificarAnalistaPropio(analista);
	}

	public Tutor modificarTutorPropio(Tutor tutor) throws ServiciosException, UsuarioNoEncontradoException {
		return tutorBeanRemote.modificarTutorPropio(tutor);
	}

	public Usuario encontrarUsuario(int id) throws UsuarioNoEncontradoException {
		return usuarioRemote.encontrarUsuario(id);
	}
	
	/*
	 * METODOS ANALISTA REMOTOS
	 */
	public List<Usuario> listarUsuarios() {
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			lista = usuarioRemote.listarUsuariosGeneral();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;

	}
	
	

	/*
	 * METODOS ANALISTA REMOTOS FIN
	 */
}
