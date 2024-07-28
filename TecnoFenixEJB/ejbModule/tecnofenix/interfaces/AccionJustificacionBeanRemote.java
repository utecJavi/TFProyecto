package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.AccionJustificacion;
import tecnofenix.exception.ServiciosException;

@Remote
public interface AccionJustificacionBeanRemote {
	
	AccionJustificacion crearAccionJustificacion(AccionJustificacion accionJusti) throws ServiciosException;
	
	AccionJustificacion modificarAccionJustificacion(AccionJustificacion accionJusti) throws ServiciosException;
	
	AccionJustificacion borrarAccionJustificacion(AccionJustificacion accionJusti) throws ServiciosException;
	
	List<AccionJustificacion> obtenerAccionJustificacionPorAtributo(AccionJustificacion accionJusti);
	
	List<AccionJustificacion> obtenerTodos();
	
	
	List<AccionJustificacion> listAllAccionJustificacionByJustificacionID(Integer justificacionID) throws ServiciosException;
}