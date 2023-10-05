package tecnofenix.interfaces;

import javax.ejb.Remote;

@Remote
public interface ConexionClienteJNDIRemote {

	
	public String levantando();
	public Boolean ping();
}
