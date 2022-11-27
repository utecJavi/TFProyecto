package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.TypedQuery;

import tecnofenix.entidades.Funcionalidad;

import tecnofenix.exception.FuncionalidadNoEncontradoException;
import tecnofenix.exception.ServiciosException;

import tecnofenix.interfaces.FuncionalidadBeanRemote;

/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class FuncionalidadBean implements FuncionalidadBeanRemote {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public FuncionalidadBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Funcionalidad crearFuncionalidad(Funcionalidad func) throws ServiciosException {
		func = em.merge(func);
		em.flush();
		return func;
	}

	@Override
	public Funcionalidad modificarFuncionalidad(Funcionalidad func) throws ServiciosException {
		func = em.merge(func);
		em.flush();
		return func;
	}

	@Override
	public Funcionalidad borrarFuncionalidad(Funcionalidad func) throws ServiciosException {
		func.setActivo(false);
		func = em.merge(func);
		em.flush();
		return null;
	}

	@Override
	public List<Funcionalidad> obtenerFuncionalidadPorAtributo(Funcionalidad func) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Funcionalidad findById(Integer id) {
		TypedQuery<Funcionalidad> query = em.createNamedQuery("Funcionalidad.findById", Funcionalidad.class);
		Funcionalidad func = query.setParameter("id", id).getSingleResult();

		if (func == null) {
			throw new FuncionalidadNoEncontradoException("FUNCIONALIDAD no encontrado.");
		}

		return func;
	}

	public Funcionalidad findById2(Integer id, EntityManager a) {
		TypedQuery<Funcionalidad> query = a.createNamedQuery("Funcionalidad.findById", Funcionalidad.class);
		Funcionalidad func = query.setParameter("id", id).getSingleResult();

		if (func == null) {
			throw new FuncionalidadNoEncontradoException("FUNCIONALIDAD no encontrado.");
		}

		return func;
	}

	@Override
	public List<Funcionalidad> listarFuncionalidad() throws ServiciosException {
//		TypedQuery<Funcionalidad> query = em.createQuery("SELECT i FROM Funcionalidad i ",Funcionalidad.class);
		System.out.println("FuncionalidadBean listarFuncionalidad()");
		TypedQuery<Funcionalidad> query = em.createNamedQuery("Funcionalidad.findAll", Funcionalidad.class);
		List<Funcionalidad> func = query.getResultList();

		if (func == null) {
			throw new FuncionalidadNoEncontradoException("Funcionalidads no encontrados.");
		}
		System.out.println("ESTUDIANTEBEAN LUEGO DE LA QUERY listarFuncionalidad");
		return query.getResultList();
	}

	@Override
	public List<Funcionalidad> buscarPor(String id, String nombre, String depto) {
		String conditions = "";
		if (id != null && id != "") {
			conditions = conditions + " AND i.id = " + id;
		}
		if (nombre != null && nombre != "") {

			conditions = conditions + " AND i.nombre LIKE '" + nombre + "'";

		}
		if (depto != null && depto != "") {

			conditions = conditions + " AND i.descripcion LIKE '" + depto + "'";

		}

		TypedQuery<Funcionalidad> query = em.createQuery("SELECT i FROM Funcionalidad i WHERE 1=1 " + conditions, Funcionalidad.class);
		List<Funcionalidad> list = query.getResultList();
		if (list == null) {
			throw new FuncionalidadNoEncontradoException("FUNCIONALIDAD no encontrado.");
		}

		return list;
	}

}