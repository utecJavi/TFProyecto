package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.Justificacion;
import tecnofenix.entidades.TipoEstadoJustificacion;
import tecnofenix.exception.JustificacionNoEncontradaException;
import tecnofenix.exception.ServiciosException;

@Remote
public interface JustificacionBeanRemote {
	Justificacion crearJustificacion(Justificacion justificacion) throws ServiciosException;
	Justificacion modificarJustificacion(Justificacion justificacion) throws ServiciosException;
	Justificacion borrarJustificacion(Justificacion justificacion) throws ServiciosException;
	List<Justificacion> obtenerJustificacionPorAtributo(Justificacion justificacion);
	List<Justificacion> listarJustificacion();
	//Se agrego en esta segunda evolucion
		Justificacion buscarJustificacionPorId(Integer id) throws JustificacionNoEncontradaException;
		List<Justificacion> buscarJustificacionsEstudiante(Integer id) throws JustificacionNoEncontradaException;
		
		TipoEstadoJustificacion crearTipoEstadoJustificacion(TipoEstadoJustificacion tEE);
		TipoEstadoJustificacion editarTipoEstadoJustificacion(TipoEstadoJustificacion tEE);
		List<TipoEstadoJustificacion> listarTipoEstadoJustificacion();
		List<TipoEstadoJustificacion> buscarTipoEstadoJustificacionPor(String id, String nombre);
}