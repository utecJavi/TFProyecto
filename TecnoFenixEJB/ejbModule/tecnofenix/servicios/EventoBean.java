package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.*;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.EventoBeanRemote;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class EventoBean implements EventoBeanRemote {
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public EventoBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Evento crearEvento(Evento evento) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Evento modificarEvento(Evento evento) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Evento borrarEvento(Evento evento) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evento> obtenerEventoPorAtributo(Evento evento) {
		// TODO Auto-generated method stub
		return null;
	}

}