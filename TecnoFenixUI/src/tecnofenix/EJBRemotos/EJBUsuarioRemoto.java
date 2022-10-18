package tecnofenix.EJBRemotos;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tecnofenix.servicios.ConexionClienteJNDIRemote;



public class EJBUsuarioRemoto {
	private static String RUTA_USUARIO_EJB = "ejb:/TecnoFenixEJB/ConexionClienteJNDI!tecnofenix.servicios.ConexionClienteJNDIRemote";
	
	@EJB
	ConexionClienteJNDIRemote claseRemota;
	
	
	
	public EJBUsuarioRemoto() {
		try {
			InitialContext ctx = new InitialContext();
			// Instanciamos las interfaces remotas con el lookup
			claseRemota = (ConexionClienteJNDIRemote) ctx.lookup(RUTA_USUARIO_EJB);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ejecutarMetodo(){
	System.out.println(claseRemota.levantando());

	
	}
}
