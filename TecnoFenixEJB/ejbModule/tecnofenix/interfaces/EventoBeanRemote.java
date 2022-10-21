package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.Evento;
import tecnofenix.exception.ServiciosException;

@Remote
public interface EventoBeanRemote {
	Evento crearEvento(Evento evento) throws ServiciosException;
	Evento modificarEvento(Evento evento) throws ServiciosException;
	Evento borrarEvento(Evento evento) throws ServiciosException;
	List<Evento> obtenerEventoPorAtributo(Evento evento);
	
}