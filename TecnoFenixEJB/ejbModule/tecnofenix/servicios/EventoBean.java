package tecnofenix.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.*;
import tecnofenix.exception.ItrNoEncontradoException;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.EventoBeanRemote;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class EventoBean implements EventoBeanRemote {
	@PersistenceContext
	private EntityManager em;
	
	private ItrBean itrBean;
	private TutorResponsableEventoBean treBean;
	
    /**
     * Default constructor. 
     */
    public EventoBean() {
        // TODO Auto-generated constructor stub
    	itrBean = new ItrBean();
    	treBean= new TutorResponsableEventoBean();
    }

	@Override
	public Evento crearEvento(Evento evento) throws ServiciosException {
		System.out.println("CREAR EVENTO");
		System.out.println(evento.toString());
		System.out.println(itrBean);
		evento.setItr(itrBean.findById2(evento.getItr().getId(), em));
		em.persist(evento);
		em.flush();
		System.out.println(evento.toString());
		// TODO Auto-generated method stub
		return evento;
	}

	@Override
	public Evento modificarEvento(Evento evento) throws ServiciosException {
		for(TutorResponsableEvento tre : evento.getTutorResponsableEventoCollection()) {
			treBean.modificarTutorResponsableEvento(tre);
		}
		
		em.merge(evento);
		em.flush();
		return evento;
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
	public List<Evento> listarEventos() {
		
		List<Evento> list = new ArrayList<Evento>();
		TypedQuery<Evento> query = em.createNamedQuery("Evento.findAll", Evento.class);
		list=query.getResultList();
		
		return list;
	}

	@Override
	public List<Evento> buscarEventosPor(String id, String titulo) {
		String conditions = "";
		
		if (id != null && id != "") {
			conditions = conditions + " AND e.id = " + id;
		}
		if (titulo != null && titulo != "") {

			conditions = conditions + " AND e.titulo LIKE '%" + titulo + "%'";

		}
		
		String jpql = "SELECT e FROM Evento e WHERE 1=1 " + conditions;
		TypedQuery<Evento> query = em.createQuery(jpql, Evento.class);
		System.out.println(jpql);
		List<Evento> list = query.getResultList();
		if (list == null) {
			throw new ItrNoEncontradoException("Eventos no encontrado.");
		}

		return list;
	}
	
	

}
