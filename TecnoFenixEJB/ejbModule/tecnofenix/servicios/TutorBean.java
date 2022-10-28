package tecnofenix.servicios;

import tecnofenix.entidades.Tutor;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.TutorBeanRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


/**
 * Session Bean implementation class CarrerasBean
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
		System.out.println("ANALISTA MODIFICADO 1 !");
		Tutor tutorDb = (Tutor) usuarioBean.encontrarUsuario(tutor.getId());
		System.out.println("ANALISTA MODIFICADO 2 !");
		tutorDb.setDocumento(tutor.getDocumento());
		tutorDb.setContrasenia(tutor.getContrasenia());
		tutorDb.setApellidos(tutor.getApellidos());
		tutorDb.setNombres(tutor.getNombres());
		tutorDb.setFechaNacimiento(tutor.getFechaNacimiento());
		tutorDb.setMail(tutor.getMail());
		tutorDb.setTelefono(tutor.getTelefono());

		System.out.println("1 MODIFICADO 3 !");

		em.merge(tutorDb);
		em.flush();

		System.out.println("ANALISTA MODIFICADO 4 !");

		return tutorDb;
	}

	@Override
	public Tutor modificarTutor(Tutor tutor) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
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

}