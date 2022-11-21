package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.Rol;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.RolBeanRemote;

@Stateless
public class RolBean implements RolBeanRemote{
	@PersistenceContext
	private EntityManager em;

	
	public RolBean() {
	}
	
	
	@Override
	public Rol crearRol(Rol rol) throws ServiciosException {

		rol = em.merge(rol);
		em.flush();
		
		return rol;
	}

	@Override
	public Rol modificarRol(Rol rol) throws ServiciosException {
		rol = em.merge(rol);
		em.flush();
		
		return rol;
	}

	@Override
	public Rol borrarRol(Rol rol) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Rol findById(Integer id) {
		TypedQuery<Rol> query = em.createNamedQuery("Rol.findById", Rol.class);
		Rol rol = query.setParameter("id", id).getSingleResult();

		if (rol == null) {
			System.out.println("rol no encontrado.");
			System.out.println("rol no encontrado.");
			System.out.println("rol no encontrado.");
			System.out.println("rol no encontrado.");
		}

		return rol;
		
	}


	@Override
	public List<Rol> listarRoles() throws ServiciosException {
		TypedQuery<Rol> query = em.createNamedQuery("Rol.findAll", Rol.class);
		List<Rol> listado = query.getResultList();
		
		return listado;
	}
	
	
}
