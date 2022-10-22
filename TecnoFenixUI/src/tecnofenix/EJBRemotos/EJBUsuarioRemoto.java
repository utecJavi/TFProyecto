package tecnofenix.EJBRemotos;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Usuario;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.UsuarioBeanRemote;
import tecnofenix.servicios.ConexionClienteJNDIRemote;



public class EJBUsuarioRemoto{

	private static String CONEXION_CLIENTE_EJB = "ejb:/TecnoFenixEJB/ConexionClienteJNDI!tecnofenix.servicios.ConexionClienteJNDIRemote";
	private static String RUTA_USUARIO_EJB = "ejb:/TecnoFenixEJB/UsuarioBean!tecnofenix.interfaces.UsuarioBeanRemote";
	
	@EJB
	ConexionClienteJNDIRemote claseRemota;
	@EJB
	UsuarioBeanRemote usuarioRemote;
	
	
	public EJBUsuarioRemoto() {
		try {
			InitialContext ctx = new InitialContext();
			// Instanciamos las interfaces remotas con el lookup
			claseRemota = (ConexionClienteJNDIRemote) ctx.lookup(CONEXION_CLIENTE_EJB);
			usuarioRemote= (UsuarioBeanRemote) ctx.lookup(RUTA_USUARIO_EJB);
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
		Estudiante usuDevuelto= new Estudiante();
		try {
			System.out.println("LEVANTANDOOO USUARIO");
			usuDevuelto=(Estudiante) usuarioRemote.crearUsuario(usuario);
		} catch (ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Usuario RETORNADO");
		return usuDevuelto;
	}
	
}
