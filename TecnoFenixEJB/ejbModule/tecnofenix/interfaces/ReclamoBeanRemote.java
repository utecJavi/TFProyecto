package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.Reclamo;
import tecnofenix.exception.ServiciosException;

@Remote
public interface ReclamoBeanRemote {
	Reclamo crearReclamo(Reclamo reclamo) throws ServiciosException;
	Reclamo modificarReclamo(Reclamo reclamo) throws ServiciosException;
	Reclamo borrarReclamo(Reclamo reclamo) throws ServiciosException;
	List<Reclamo> obtenerReclamoPorAtributo(Reclamo reclamo);
	
}