package tecnofenix.EJBRemotos;

import tecnofenix.entidades.Analista;
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Tutor;
import tecnofenix.entidades.Usuario;
import tecnofenix.exception.ServiciosException;
import tecnofenix.exception.UsuarioNoEncontradoException;
import tecnofenix.interfaces.AnalistaBeanRemote;
import tecnofenix.interfaces.EstudianteBeanRemote;
import tecnofenix.interfaces.TutorBeanRemote;
import tecnofenix.interfaces.UsuarioBeanRemote;
import tecnofenix.servicios.ConexionClienteJNDIRemote;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class EJBUsuarioRemoto {

    private static String CONEXION_CLIENTE_EJB = "ejb:/TecnoFenixEJB/ConexionClienteJNDI!tecnofenix.servicios.ConexionClienteJNDIRemote";
    private static String RUTA_USUARIO_EJB = "ejb:/TecnoFenixEJB/UsuarioBean!tecnofenix.interfaces.UsuarioBeanRemote";
    private static final String RUTA_ANALISTA_EJB = "ejb:/TecnoFenixEJB/AnalistaBean!tecnofenix.interfaces.AnalistaBeanRemote";
    private static final String RUTA_ESTUDIANTE_EJB = "ejb:/TecnoFenixEJB/EstudianteBean!tecnofenix.interfaces.EstudianteBeanRemote";
    private static final String RUTA_TUTOR_EJB = "ejb:/TecnoFenixEJB/TutorBean!tecnofenix.interfaces.TutorBeanRemote";

    @EJB
    ConexionClienteJNDIRemote claseRemota;
    @EJB
    UsuarioBeanRemote usuarioRemote;
    @EJB
    AnalistaBeanRemote analistaRemote;
    @EJB
    EstudianteBeanRemote estudianteRemote;
    @EJB
    TutorBeanRemote tutorRemote;


    public EJBUsuarioRemoto() {
        try {
            InitialContext ctx = new InitialContext();
            // Instanciamos las interfaces remotas con el lookup
            claseRemota = (ConexionClienteJNDIRemote) ctx.lookup(CONEXION_CLIENTE_EJB);
            usuarioRemote = (UsuarioBeanRemote) ctx.lookup(RUTA_USUARIO_EJB);
            analistaRemote = (AnalistaBeanRemote) ctx.lookup(RUTA_ANALISTA_EJB);
            estudianteRemote = (EstudianteBeanRemote) ctx.lookup(RUTA_ESTUDIANTE_EJB);
            tutorRemote = (TutorBeanRemote) ctx.lookup(RUTA_TUTOR_EJB);
            System.out.println("TESTEST");
            System.out.println(usuarioRemote.toString());
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void ejecutarMetodo() {
        System.out.println(claseRemota.levantando());

    }

    public Usuario login(String usu, String pass) {
        Estudiante logeado = new Estudiante();
        System.out.println("Verificando Login");
        try {
            logeado = (Estudiante) usuarioRemote.login(usu, pass);
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
            System.out.println("ESTUDIANTE 2: " + usuario);
            usuario = usuarioRemote.crearUsuario(usuario);
        } catch (Exception e) {
            System.out.println("DASDASDASDASDASDASDASD");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Usuario RETORNADO. " + usuario.toString());
        return usuario;
    }

    public Estudiante modificarEstudiantePropio(Estudiante estudiante) throws ServiciosException, UsuarioNoEncontradoException {
        return estudianteRemote.modificarEstudiantePropio(estudiante);
    }

    public Analista modificarAnalistaPropio(Analista analista) throws ServiciosException, UsuarioNoEncontradoException {
        return analistaRemote.modificarAnalistaPropio(analista);
    }

    public Tutor modificarTutorPropio(Tutor tutor) throws ServiciosException, UsuarioNoEncontradoException {
        return tutorRemote.modificarTutorPropio(tutor);
    }

    public Usuario encontrarUsuario(int id) throws UsuarioNoEncontradoException {
        return usuarioRemote.encontrarUsuario(id);
    }

}
