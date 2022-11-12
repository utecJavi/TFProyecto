package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.Constancia;
import tecnofenix.exception.ServiciosException;

@Remote
public interface ConstanciaBeanRemote {
	
	Constancia crearConstancia(Constancia constancia) throws ServiciosException;
	
	Constancia modificarConstancia(Constancia constancia) throws ServiciosException;
	
	Constancia borrarConstancia(Constancia constancia) throws ServiciosException;
	
	List<Constancia> obtenerConstanciaPorAtributo(Constancia constancia);
	
}