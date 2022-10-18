package tecnofenix.servicios;

import javax.ejb.Remote;

@Remote
public interface ConexionClienteJNDIRemote {

	
	public String levantando();
}
