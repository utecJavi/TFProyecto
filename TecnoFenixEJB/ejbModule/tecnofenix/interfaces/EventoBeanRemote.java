package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Evento;
import tecnofenix.exception.ServiciosException;

@Remote
public interface EventoBeanRemote {
	Evento crearEvento(Evento evento) throws ServiciosException;
	Evento modificarEvento(Evento evento) throws ServiciosException;
	Evento borrarEvento(Evento evento) throws ServiciosException;
	Evento obtenerEvento(Integer id);
	List<Evento> obtenerEventoPorAtributo(Evento evento);
	List<Evento> listarEventos()throws ServiciosException;
	List<Estudiante> obtenerEstudiantesConvocados(Evento evento);
	List<Evento> buscarEventosPor(String id,String titulo);
}
