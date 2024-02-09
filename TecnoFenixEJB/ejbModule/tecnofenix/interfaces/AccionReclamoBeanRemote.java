package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.AccionReclamo;
import tecnofenix.exception.ServiciosException;

@Remote
public interface AccionReclamoBeanRemote {
	
	AccionReclamo crearAccionReclamo(AccionReclamo accionReclamo) throws ServiciosException;
	
	AccionReclamo modificarAccionReclamo(AccionReclamo accionReclamo) throws ServiciosException;
	
	AccionReclamo borrarAccionReclamo(AccionReclamo accionReclamo) throws ServiciosException;
	
	List<AccionReclamo> obtenerAccionReclamoPorAtributo(AccionReclamo accionReclamo) throws ServiciosException;
	
	List<AccionReclamo> listAllAccionReclamoByReclamo(Integer reclamoID) throws ServiciosException;
	
}