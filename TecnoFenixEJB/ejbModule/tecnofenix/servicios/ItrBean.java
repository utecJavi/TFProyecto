package tecnofenix.servicios;

import java.util.ArrayList;
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
		try {
			System.out.println(itr.getDepartamento());
			System.out.println(itr.getNombre());
			if(itr.getId()==null)System.out.println("itr.getId()==null");
			itr = em.merge(itr);
			em.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return itr;
	}

	@Override
	public Itr modificarItr(Itr itr) throws ServiciosException {
		try {
			em.merge(itr);
			em.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return itr;
	}

	@Override
	public Itr borrarItr(Itr itr) throws ServiciosException {
		try {
			itr.setActivo(false);
			em.merge(itr);
			em.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return itr;
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

	public Itr findById2(Integer id, EntityManager a) {
		TypedQuery<Itr> query = a.createNamedQuery("Itr.findById", Itr.class);
		Itr itr = query.setParameter("id", id).getSingleResult();

		if (itr == null) {
			throw new ItrNoEncontradoException("ITR no encontrado.");
		}

		return itr;
	}

	@Override
	public List<Itr> listarItr() throws ServiciosException {
		List<Itr> list = new ArrayList<Itr>();
		try {
		
			System.out.println("ItrBean listarItr()");
			TypedQuery<Itr> query = em.createNamedQuery("Itr.findAll", Itr.class);
//			query.setParameter("activo", activo);
			list = query.getResultList();
			
			} catch (Exception e) {
				// TODO: handle exception
			}
		if (list == null) {
			throw new ItrNoEncontradoException("Itrs no encontrados.");
		}
		System.out.println("ESTUDIANTEBEAN LUEGO DE LA QUERY listarItr");
		return list;
	}

	@Override
	public List<Itr> buscarPor(String id, String nombre, String depto) {
		String conditions = "";
		if (id != null && id != "") {
			conditions = conditions + " AND i.id = " + id;
		}
		if (nombre != null && nombre != "") {

			conditions = conditions + " AND i.nombre LIKE '%" + nombre + "%'";

		}
		if (depto != null && depto != "") {

			conditions = conditions + " AND i.departamento LIKE '%" + depto + "%'";

		}

		List<Itr> list = new ArrayList<Itr>();
		
		TypedQuery<Itr> query = em.createQuery("SELECT i FROM Itr i WHERE 1=1 " + conditions, Itr.class);
		list = query.getResultList();
		if (list == null) {
			throw new ItrNoEncontradoException("ITR no encontrado.");
		}

		return list;
	}

}