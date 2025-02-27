package tecnofenix.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Evento;
import tecnofenix.entidades.ModalidadEvento;
import tecnofenix.entidades.TipoEstadoEvento;
import tecnofenix.entidades.TipoEvento;
import tecnofenix.entidades.Usuario;
import tecnofenix.exception.ServiciosException;

@Remote
public interface EventoBeanRemote {
	Evento crearEvento(Evento evento) throws ServiciosException;
	Evento modificarEvento(Evento evento) throws ServiciosException;
	Evento borrarEvento(Evento evento) throws ServiciosException;
	Evento obtenerEvento(Integer id);
	List<Evento> obtenerEventoPorAtributo(Evento evento);
	List<Evento> listarEventos()throws ServiciosException;
	List<Evento> listarEventosTutor(Usuario tutor)throws ServiciosException;
	
	List<Estudiante> obtenerEstudiantesConvocados(Evento evento);
	List<Evento> buscarEventosPor(String id,String titulo);
	List<Evento> buscarEventosPor(String id, String titulo,String localizacion,String modalidad,String tipoEvento,String itrNombre,Date inicioInicio, Date finInicio,Date inicioFin, Date finFin,Boolean activo);

	TipoEstadoEvento crearTipoEstadoEvento(TipoEstadoEvento tEE);
	 TipoEstadoEvento editarTipoEstadoEvento(TipoEstadoEvento tEE);
	 List<TipoEstadoEvento> listarTipoEstadoEvento();
	 List<TipoEstadoEvento> buscarTipoEstadoEventoPor(String id, String nombre);
	
	 List<ModalidadEvento> listarModalidadEvento();
	 List<TipoEvento> listarTipoEvento();
	
	
	
}
