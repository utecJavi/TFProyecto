package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.Itr;
import tecnofenix.entidades.Usuario;
import tecnofenix.exception.ItrNoEncontradoException;
import tecnofenix.exception.ServiciosException;
import tecnofenix.exception.UsuarioNoEncontradoException;
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
		em.flush();
		return itr;
	}

	@Override
	public Itr modificarItr(Itr itr) throws ServiciosException {
		itr=em.merge(itr);
		em.flush();
		return itr;
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

	@Override
	public List<Itr> listarItr() throws ServiciosException {
//		TypedQuery<Itr> query = em.createQuery("SELECT i FROM Itr i ",Itr.class);
		System.out.println("ItrBean listarItr()");
		 TypedQuery<Itr> query = em.createNamedQuery("Itr.findAll", Itr.class);
		 List<Itr> itr = query.getResultList();

	        if (itr == null) {
	            throw new ItrNoEncontradoException("Itrs no encontrados.");
	        }
		System.out.println("ESTUDIANTEBEAN LUEGO DE LA QUERY listarItr");
		return query.getResultList();
	}

	@Override
	public List<Itr> buscarPor(String id, String nombre, String depto) {
		String conditions="";
		if (id != null && id !="") {
			conditions=conditions+" i.id = "+id;
		}
		if (nombre != null && nombre !="" ) {
			if(conditions=="") {
				conditions=conditions+" i.nombre = '"+nombre+"'";
			}else {
				conditions=conditions+"AND i.nombre = '"+nombre+"'";
			}
			
		}
		if (depto != null&& depto !="") {
			if(conditions=="") {
				conditions=conditions+" i.departamento = '"+depto+"'";
			}else {
				conditions=conditions+"AND i.departamento = '"+depto+"'";
			}
			
		}
	
		TypedQuery<Itr> query = em.createQuery("SELECT i FROM Itr i WHERE "+conditions, Itr.class);
		List<Itr> list = query.getResultList();
		if (list == null) {
			throw new ItrNoEncontradoException("ITR no encontrado.");
		}

		return list;
	}

}