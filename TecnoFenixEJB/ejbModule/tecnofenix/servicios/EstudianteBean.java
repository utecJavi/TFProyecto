package tecnofenix.servicios;

import tecnofenix.entidades.Estudiante;
import tecnofenix.exception.ServiciosException;
import tecnofenix.exception.UsuarioNoEncontradoException;
import tecnofenix.interfaces.EstudianteBeanRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class EstudianteBean implements EstudianteBeanRemote {
//	@PersistenceContext
	private EntityManager em;

	private UsuarioBean usuarioBean;
	
    /**
     * Default constructor. 
     */
    public EstudianteBean() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TecnoFenixEJB");
		em = entityManagerFactory.createEntityManager();
		usuarioBean = new UsuarioBean();
    }

	@Override
	public Estudiante crearEstudiante(Estudiante estudiante) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Estudiante modificarEstudiante(Estudiante estudiante) throws ServiciosException, UsuarioNoEncontradoException {
		System.out.println("ESTUDIANTE MODIFICADO 1 !");
		Estudiante estudianteDb = (Estudiante) usuarioBean.encontrarUsuario(estudiante.getId());
		System.out.println("ESTUDIANTE MODIFICADO 2 !");
		estudianteDb.setDocumento(estudiante.getDocumento());
		estudianteDb.setContrasenia(estudiante.getContrasenia());
		estudianteDb.setApellidos(estudiante.getApellidos());
		estudianteDb.setNombres(estudiante.getNombres());
		estudianteDb.setFechaNacimiento(estudiante.getFechaNacimiento());
		estudianteDb.setMail(estudiante.getMail());
		estudianteDb.setTelefono(estudiante.getTelefono());
		estudianteDb.setGeneracion(estudiante.getGeneracion());

		System.out.println("ESTUDIANTE MODIFICADO 3 !");

		em.merge(estudianteDb);
		em.flush();

		System.out.println("ESTUDIANTE MODIFICADO 4 !");

		return estudianteDb;
	}

	@Override
	public Estudiante borrarEstudiante(Estudiante estudiante) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estudiante> obtenerEstudiantePorAtributo(Estudiante estudiante) {
		// TODO Auto-generated method stub
		return null;
	}

}