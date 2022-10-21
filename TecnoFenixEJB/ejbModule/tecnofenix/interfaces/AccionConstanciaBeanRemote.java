package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.AccionConstancia;
import tecnofenix.exception.ServiciosException;

@Remote
public interface AccionConstanciaBeanRemote {
	AccionConstancia crearClase(AccionConstancia acciConstancia) throws ServiciosException;
	AccionConstancia modificarClase(AccionConstancia acciConstancia) throws ServiciosException;
	AccionConstancia borrarClase(AccionConstancia acciConstancia) throws ServiciosException;
	List<AccionConstancia> obtenerClasePorAtributo(AccionConstancia acciConstancia);
	
}