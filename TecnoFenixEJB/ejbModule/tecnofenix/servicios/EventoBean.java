package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
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
	
	@Override
	public Evento obtenerEvento(Integer id) {
		TypedQuery<Evento> query = em.createNamedQuery("Evento.findById", Evento.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public List<Estudiante> obtenerEstudiantesConvocados(Evento evento) {
		evento = obtenerEvento(evento.getId());
//		Query query = em.createQuery("SELECT es FROM Evento ev INNER JOIN FETCH ev.convocatoriaAsistenciaEventoEstudianteCollection c INNER JOIN FETCH c.estudianteId es WHERE ev.id = :id");
		TypedQuery<Estudiante> query = em.createQuery("SELECT es FROM Evento ev INNER JOIN ev.convocatoriaAsistenciaEventoEstudianteCollection c INNER JOIN c.estudianteId es WHERE ev.id = :id", Estudiante.class);
		query.setParameter("id", evento.getId());
		return query.getResultList();
	}

	@Override
	public List<Evento> obtenerEventos() {
		TypedQuery<Evento> query = em.createNamedQuery("Evento.findAll", Evento.class);
		return query.getResultList();
	}
	
	

}
