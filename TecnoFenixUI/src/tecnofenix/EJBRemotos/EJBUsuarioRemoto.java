package tecnofenix.EJBRemotos;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tecnofenix.entidades.Usuario;
import tecnofenix.interfaces.UsuarioBeanRemote;
import tecnofenix.servicios.ConexionClienteJNDIRemote;



public class EJBUsuarioRemoto {
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
		Usuario logeado= new Usuario();
		logeado=usuarioRemote.login(usu,pass);
		System.out.println("Usuario RETORNADO");
		return logeado;
	}
	
}
