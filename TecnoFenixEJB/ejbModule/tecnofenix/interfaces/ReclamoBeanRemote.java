package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.Reclamo;
import tecnofenix.entidades.TipoEstadoReclamo;
import tecnofenix.exception.ReclamoNoEncontradoException;
import tecnofenix.exception.ServiciosException;


@Remote
public interface ReclamoBeanRemote {
	Reclamo crearReclamo(Reclamo reclamo) throws ServiciosException;
	Reclamo modificarReclamo(Reclamo reclamo) throws ServiciosException;
	Reclamo borrarReclamo(Reclamo reclamo) throws ServiciosException;
	List<Reclamo> obtenerReclamoPorAtributo(Reclamo reclamo);
	List<Reclamo> listarReclamos();
	//Se agrego en esta segunda evolucion
	Reclamo buscarReclamoPorId(Integer id) throws ReclamoNoEncontradoException;
	List<Reclamo> buscarReclamosEstudiante(Integer id) throws ReclamoNoEncontradoException;
	
	TipoEstadoReclamo crearTipoEstadoReclamo(TipoEstadoReclamo tEE);
	TipoEstadoReclamo editarTipoEstadoReclamo(TipoEstadoReclamo tEE);
	List<TipoEstadoReclamo> listarTipoEstadoReclamo();
	List<TipoEstadoReclamo> buscarTipoEstadoReclamoPor(String id, String nombre);
	
}
