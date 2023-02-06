package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.TutorResponsableEvento;
import tecnofenix.entidades.Usuario;
import tecnofenix.exception.ServiciosException;
import tecnofenix.exception.TutorResponsableEventoException;
import tecnofenix.exception.UsuarioNoEncontradoException;
import tecnofenix.interfaces.TutorResponsableEventoBeanRemote;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class TutorResponsableEventoBean implements TutorResponsableEventoBeanRemote {
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public TutorResponsableEventoBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public TutorResponsableEvento crearTutorResponsableEvento(TutorResponsableEvento tutorRespEve)throws ServiciosException {
			
		em.persist(tutorRespEve);
		em.flush();
		System.out.println("Se creo el TutorResponsableEvento nuevo con id " +tutorRespEve.getId());
		return tutorRespEve;
	}

	@Override
	public TutorResponsableEvento modificarTutorResponsableEvento(TutorResponsableEvento tutorRespEve) throws ServiciosException {
	if (tutorRespEve.getId() == null) {
		crearTutorResponsableEvento(tutorRespEve);
	}else {
		em.merge(tutorRespEve);
		em.flush();
	}
		return tutorRespEve;
	}

	@Override
	public TutorResponsableEvento borrarTutorResponsableEvento(TutorResponsableEvento tutorRespEve)
			throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TutorResponsableEvento> obtenerTutorResponsableEventoPorAtributo(TutorResponsableEvento tutorRespEve) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TutorResponsableEvento> obtenerTutorResponsableEventoPorId(Integer id) throws ServiciosException {
		System.out.println("BUSINESS obtenerTutorResponsableEventoPorId ");
//		TypedQuery<TutorResponsableEvento> query = em.createNamedQuery("TutorResponsableEvento.findByEventoId", TutorResponsableEvento.class);
//		List<TutorResponsableEvento> tre = query.setParameter("eventoId", id).getResultList();
		String jpql = "SELECT t FROM TutorResponsableEvento t INNER JOIN t.eventoId eId WHERE eId=" + id;
		TypedQuery<TutorResponsableEvento> query = em.createQuery(jpql, TutorResponsableEvento.class);
		System.out.println(jpql);
		List<TutorResponsableEvento> tre = query.getResultList();
		
		
		
		if (tre == null) {
			throw new TutorResponsableEventoException("Listado de tutores responsables del evento con id:"+id+" no encontrado.");
		}else {
			System.out.println(tre.toString());
		}
		

		return tre;
	}

}