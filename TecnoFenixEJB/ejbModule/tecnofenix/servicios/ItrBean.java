package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Itr;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.ItrBeanRemote;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class ItrBean implements ItrBeanRemote {
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ItrBean() {
        // TODO Auto-generated constructor stub
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
	public List<Itr> listarItr() throws ServiciosException {
		TypedQuery<Itr> query = em.createQuery("SELECT i FROM Itr i ",Itr.class);

		em.flush();
		System.out.println("ESTUDIANTEBEAN LUEGO DE LA QUERY listarItr");
		return query.getResultList();
	}

}