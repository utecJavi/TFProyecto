package tecnofenix.servicios;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import tecnofenix.interfaces.ConexionClienteJNDIRemote;

/**
 * Session Bean implementation class ConexionClienteJNDI
 */
@Stateless
@LocalBean
public class ConexionClienteJNDI implements ConexionClienteJNDIRemote {

    /**
     * Default constructor. 
     */
    public ConexionClienteJNDI() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String levantando() {
		
		return "Correcto!";
	}

	@Override
	public Boolean ping() {

		return true;
	}

	
}
