package tecnofenix.servicios;

import tecnofenix.entidades.TipoArea;
import tecnofenix.entidades.TipoTutorTipo;
import tecnofenix.entidades.Tutor;
import tecnofenix.entidades.Usuario;
import tecnofenix.exception.ServiciosException;
import tecnofenix.exception.UsuarioNoEncontradoException;
import tecnofenix.interfaces.TutorBeanRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;


/**
 * Session Bean implementation class CarrerasBean
 * en su lugar se usa la clase de usuario
 * pero dejamos esta para meter los tipos de tutor y las areas
 * asi no creamos mas entidaes remotes
 */
@Stateless
public class TutorBean implements TutorBeanRemote  {
//	@PersistenceContext
	private EntityManager em;

	private UsuarioBean usuarioBean;
	
    /**
     * Default constructor. 
     */
    public TutorBean() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TecnoFenixEJB");
		em = entityManagerFactory.createEntityManager();
		usuarioBean = new UsuarioBean();
    }

	@Override
	public Tutor crearTutor(Tutor tutor) throws ServiciosException {
		Tutor tutorDb = (Tutor) usuarioBean.encontrarUsuario(tutor.getId());
		tutorDb.setDocumento(tutor.getDocumento());
		tutorDb.setContrasenia(tutor.getContrasenia());
		tutorDb.setApellidos(tutor.getApellidos());
		tutorDb.setNombres(tutor.getNombres());
		tutorDb.setFechaNacimiento(tutor.getFechaNacimiento());
		tutorDb.setMail(tutor.getMail());
		tutorDb.setTelefono(tutor.getTelefono());
		em.merge(tutorDb);
		em.flush();

		return tutorDb;
	}

	@Override
	public Tutor modificarTutorPropio(Tutor tutor) throws ServiciosException, UsuarioNoEncontradoException {
		if (tutor.getId() == null) {
			throw new UsuarioNoEncontradoException("Ha ocurrido un error al modificar el usuario.");
		}else {
			em.merge(tutor);
			em.flush();
		}

//		Tutor tutorDb = (Tutor) usuarioBean.encontrarUsuario(tutor.getId());
//		tutorDb.setContrasenia(tutor.getContrasenia());
//		tutorDb.setArea(tutor.getArea());
//		tutorDb.setTipo(tutor.getTipo());

		return (Tutor) usuarioBean.modificarUsuario( tutor);
	}

	@Override
	public Tutor modificarTutorAdmin(Tutor tutor) throws ServiciosException, UsuarioNoEncontradoException {
		if (tutor.getId() == null) {
			throw new UsuarioNoEncontradoException("Ha ocurrido un error al modificar el usuario.");
		}

//		Tutor tutorDb = (Tutor) usuarioBean.encontrarUsuario(tutor.getId());
//		tutorDb.setArea(tutor.getArea());
//		tutorDb.setTipo(tutor.getTipo());

		return (Tutor) usuarioBean.modificarUsuario( tutor);
	}

	@Override
	public Tutor borrarTutor(Tutor tutor) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tutor> obtenerTutorPorAtributo(Tutor tutor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tutor> listarTutores() throws ServiciosException {
		List<Tutor> tutores = new ArrayList<Tutor>();
		try {
		TypedQuery<Tutor> query = em.createNamedQuery("Tutor.findAll", Tutor.class);
		tutores = query.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		}
		if (tutores == null) {
			throw new ServiciosException("Tutores no encontrados.");
		}
		return tutores;
	}

	@Override
	public Tutor obtenerTutorPorId(Integer tutorId) {
		System.out.println("obtenerTutorPorId id= "+tutorId);
		TypedQuery<Tutor> query = em.createNamedQuery("Tutor.findById", Tutor.class);
		Tutor tutor = query.setParameter("id", tutorId).getSingleResult();
		
		if (tutor == null) {
			throw new UsuarioNoEncontradoException("Tutor no encontrados.");
		}else {
			System.out.println("Tutor " +tutor.getId());
			System.out.println("Tutor nombre: " +tutor.getNombres() + " "+ tutor.getApellidos());
		}
		return tutor;
	}

	@Override
	public TipoTutorTipo crearTipoTutorTipo(TipoTutorTipo tipoTutorTipo) throws ServiciosException {
		try {
			if (tipoTutorTipo.getId() != null) {
				tipoTutorTipo = em.merge(tipoTutorTipo);
				em.flush();
				return tipoTutorTipo;
			} else {
				throw new ServiciosException("Se quiere modificar un tipo de constancia que no existente.");
			}
		} catch (PersistenceException pe) {
			throw new ServiciosException("Ocurrio un error al crear TipoArea: " + pe.getMessage());
		}
	}

	@Override
	public TipoTutorTipo modificarTipoTutorTipo(TipoTutorTipo tipoTutorTipo) throws ServiciosException {
		try {
			tipoTutorTipo = em.merge(tipoTutorTipo);
			em.flush();
			return tipoTutorTipo;
		} catch (PersistenceException pe) {
			throw new ServiciosException("Ocurrio³ un error al modficar TipoArea: " + pe.getMessage());
		}
	}

	@Override
	public void bajaTipoTutorTipo(TipoTutorTipo tipoTutorTipo) throws ServiciosException {
		try {
			tipoTutorTipo.setBajaLogica(true);
			tipoTutorTipo = em.merge(tipoTutorTipo);
			em.flush();
			
		} catch (PersistenceException pe) {
			throw new ServiciosException("Ocurrio³ un error al modficar TipoArea: " + pe.getMessage());
		}
		
	}

	@Override
	public List<TipoTutorTipo> listadoTipoTutorTipo(Boolean baja) throws ServiciosException {
		List<TipoTutorTipo> list =new ArrayList<TipoTutorTipo>();
		try {
			TypedQuery<TipoTutorTipo> query = em.createNamedQuery("TipoTutorTipo.findByBajaLogica", TipoTutorTipo.class);
			query.setParameter("bajaLogica", baja).executeUpdate();
			list= query.getResultList();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	@Override
	public TipoArea crearTipoArea(TipoArea tipoArea) throws ServiciosException {
		try {
			if (tipoArea.getId() != null) {
				tipoArea = em.merge(tipoArea);
				em.flush();
				return tipoArea;
			} else {
				throw new ServiciosException("Se quiere modificar un tipo de constancia que no existente.");
			}
		} catch (PersistenceException pe) {
			throw new ServiciosException("Ocurrio un error al crear TipoArea: " + pe.getMessage());
		}
	}

	@Override
	public TipoArea modificarTipoArea(TipoArea tipoArea) throws ServiciosException {
		try {
			tipoArea = em.merge(tipoArea);
			em.flush();
			return tipoArea;
		} catch (PersistenceException pe) {
			throw new ServiciosException("Ocurrio³ un error al modficar TipoArea: " + pe.getMessage());
		}
	}

	@Override
	public void bajaTipoArea(TipoArea tipoArea) throws ServiciosException {
		try {
			tipoArea.setBajaLogica(true);
			tipoArea = em.merge(tipoArea);
			em.flush();
			
		} catch (PersistenceException pe) {
			throw new ServiciosException("Ocurrio³ un error al modficar TipoArea: " + pe.getMessage());
		}
		
	}

	@Override
	public List<TipoArea> listadoTipoArea(Boolean baja) throws ServiciosException {
		List<TipoArea> list =new ArrayList<TipoArea>();
		try {
			TypedQuery<TipoArea> query = em.createNamedQuery("TipoArea.findByBajaLogica", TipoArea.class);
			query.setParameter("bajaLogica", baja).executeUpdate();
			list= query.getResultList();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

}