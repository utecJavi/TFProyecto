package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

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
//    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TecnoFenixEJB");
//    	em = emf.createEntityManager();
    }

	@Override
	public TutorResponsableEvento crearTutorResponsableEvento(TutorResponsableEvento tutorRespEve)throws ServiciosException {
		System.out.println("crearTutorResponsableEvento antes del persist");
		if (tutorRespEve.getId() == null)System.out.println("tutorRespEve.getId() =null");
		if (tutorRespEve.getEventoId() == null) {
			System.out.println("tutorRespEve.getEventoId() =null");
		}else {
			System.out.println("tutorRespEve.getEventoId() ="+tutorRespEve.getEventoId());
		}
		if (tutorRespEve.getTutorId() == null) {
			System.out.println("tutorRespEve.getTutorId() =null");
		}else {
			System.out.println("tutorRespEve.getTutorId() ="+tutorRespEve.getTutorId());
		}
		
		if(em==null) {
			System.out.println(" ENTITY MANAGER ES NULL");

	    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TecnoFenixEJB");
	    	em = emf.createEntityManager();
		}
		try {
			em.merge(tutorRespEve);
			em.flush();
		} catch (Exception e ) {
			if(e instanceof ConstraintViolationException) {
				System.out.println(e);
				System.out.println("Todo bien pero no se puede agregar algo que ya esta en la base?");
			}
		}
		
		System.out.println("Se creo el TutorResponsableEvento nuevo con id " +tutorRespEve.getId());
		return tutorRespEve;
	}

	@Override
	public TutorResponsableEvento modificarTutorResponsableEvento(TutorResponsableEvento tutorRespEve) throws ServiciosException {
		System.out.println("modificarTutorResponsableEvento ");
		 
		if (tutorRespEve.getId() == null) {
			System.out.println("El tutor responsable es nuevo se manda a crear ");
			tutorRespEve=crearTutorResponsableEvento(tutorRespEve);
		}else {
			tutorRespEve=em.merge(tutorRespEve);
			em.flush();
		}
		return tutorRespEve;
	}

	@Override
	public TutorResponsableEvento borrarTutorResponsableEvento(TutorResponsableEvento tutorRespEve){

	        TutorResponsableEvento tutorResponsableEvento = em.find(TutorResponsableEvento.class, tutorRespEve.getId());
	        if (tutorResponsableEvento != null) {
	        	em.remove(tutorResponsableEvento);
	            em.flush(); // Asegura que la transacción se completa y los cambios se reflejan en la base de datos.
	        } else {
	            throw new TutorResponsableEventoException("El TutorResponsableEvento con id " + tutorRespEve.getId()+ " no existe y no puede ser borrado.");
	        }
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