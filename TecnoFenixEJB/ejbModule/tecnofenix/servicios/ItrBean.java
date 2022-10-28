package tecnofenix.servicios;

import tecnofenix.entidades.Itr;
import tecnofenix.exception.ItrNoEncontradoException;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.ItrBeanRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class ItrBean implements ItrBeanRemote {
	//@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ItrBean() {
    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TecnoFenixEJB");
    	em = entityManagerFactory.createEntityManager();
    }

	@Override
	public Itr crearItr(Itr itr) throws ServiciosException {
		itr=em.merge(itr);
		return itr;
	}

	@Override
	public Itr modificarItr(Itr itr) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Itr borrarItr(Itr itr) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Itr> obtenerItrPorAtributo(Itr itr) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Itr findById(Integer id) {
		TypedQuery<Itr> query = em.createNamedQuery("Itr.findById", Itr.class);
		Itr itr = query.setParameter("id", id).getSingleResult();

		if (itr == null) {
			throw new ItrNoEncontradoException("ITR no encontrado.");
		}

		return itr;
	}

}