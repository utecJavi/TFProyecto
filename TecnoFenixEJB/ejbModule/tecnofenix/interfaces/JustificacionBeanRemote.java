package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.Justificacion;
import tecnofenix.exception.ServiciosException;

@Remote
public interface JustificacionBeanRemote {
	Justificacion crearJustificacion(Justificacion justificacion) throws ServiciosException;
	Justificacion modificarJustificacion(Justificacion justificacion) throws ServiciosException;
	Justificacion borrarJustificacion(Justificacion justificacion) throws ServiciosException;
	List<Justificacion> obtenerJustificacionPorAtributo(Justificacion justificacion);
	
}