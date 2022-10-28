package tecnofenix.EJBRemotos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tecnofenix.entidades.*;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.*;
import tecnofenix.servicios.ConexionClienteJNDIRemote;



public class EJBUsuarioRemoto{

	private static String CONEXION_CLIENTE_EJB = "ejb:/TecnoFenixEJB/ConexionClienteJNDI!tecnofenix.servicios.ConexionClienteJNDIRemote";
	private static String RUTA_USUARIO_EJB = "ejb:/TecnoFenixEJB/UsuarioBean!tecnofenix.interfaces.UsuarioBeanRemote";
	private static String RUTA_USUARIO_ESTUDIANTE_EJB = "ejb:/TecnoFenixEJB/EstudianteBean!tecnofenix.interfaces.EstudianteBeanRemote";
	private static String RUTA_ITR_EJB = "ejb:/TecnoFenixEJB/ItrBean!tecnofenix.interfaces.ItrBeanRemote";
	
	
	@EJB
	ConexionClienteJNDIRemote claseRemota;
	@EJB
	UsuarioBeanRemote usuarioRemote;
	@EJB
	EstudianteBeanRemote estudianteRemote;
	@EJB
	ItrBeanRemote itrRemote;
	
	public EJBUsuarioRemoto() {
		try {
			InitialContext ctx = new InitialContext();
			// Instanciamos las interfaces remotas con el lookup
			claseRemota = (ConexionClienteJNDIRemote) ctx.lookup(CONEXION_CLIENTE_EJB);
			usuarioRemote= (UsuarioBeanRemote) ctx.lookup(RUTA_USUARIO_EJB);
			estudianteRemote=(EstudianteBeanRemote) ctx.lookup(RUTA_USUARIO_ESTUDIANTE_EJB);
			itrRemote=(ItrBeanRemote) ctx.lookup(RUTA_ITR_EJB);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ejecutarMetodo(){
	System.out.println(claseRemota.levantando());

	}
	
	public Usuario login(String usu,String pass) {
		Estudiante logeado= new Estudiante();
		System.out.println("Verificando Login");
		try {
			logeado=(Estudiante) usuarioRemote.login(usu,pass);
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
			usuario=usuarioRemote.crearUsuario(usuario);

		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Usuario RETORNADO");
		return usuario;
	}
	
	/*
	 * METODOS ESTUDIANTES REMOTOS
	 * */
	public Estudiante crearEstudiante(Estudiante estudiante) {
		
		try {
			estudiante=estudianteRemote.crearEstudiante(null);
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estudiante;
		
	}
	public List<Estudiante> listarEstudiantes() {
		List<Estudiante> lista = new ArrayList<Estudiante>();
		try {
			lista=estudianteRemote.listarEstudiantes();
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
		
	}
	/*
	 * METODOS ESTUDIANTES REMOTOS FIN
	 * */
	/*
	 * METODOS ITR REMOTOS
	 * */

	public Itr crearITR(Itr itr) {
		Itr itrDevueto = new Itr();
		try {
			itrDevueto=itrRemote.crearItr(itr);
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itrDevueto;
		
	}
	public List<Itr> listarITR() {
		List<Itr> lista = new ArrayList<Itr>();
		try {
			lista=itrRemote.listarItr();
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
		
	}
	
	
	/*
	 * METODOS ITR REMOTOS FIN
	 * */
	
	
	
}
