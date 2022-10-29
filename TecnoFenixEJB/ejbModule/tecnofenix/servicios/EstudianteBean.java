package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Usuario;
import tecnofenix.exception.ServiciosException;
import tecnofenix.exception.UsuarioNoEncontradoException;
import tecnofenix.interfaces.EstudianteBeanRemote;
/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class EstudianteBean implements EstudianteBeanRemote {
	@PersistenceContext
	private EntityManager em;

	private UsuarioBean usuarioBean;

	/**
	 * Default constructor.
	 */
	public EstudianteBean() {
		usuarioBean = new UsuarioBean();
	}

	@Override
	public Estudiante crearEstudiante(Estudiante estudiante) throws ServiciosException {

		estudiante = em.merge(estudiante);

		return estudiante;
	}

    @Override
    public Estudiante modificarEstudiantePropio(Estudiante estudiante) throws ServiciosException, UsuarioNoEncontradoException {
        if (estudiante.getId() == null) {
            throw new UsuarioNoEncontradoException("Ha ocurrido un error al modificar el usuario.");
        }

        Estudiante estudianteDb = (Estudiante) usuarioBean.encontrarUsuario(estudiante.getId());

        estudianteDb.setContrasenia(estudiante.getContrasenia());
        estudianteDb.setGeneracion(estudiante.getGeneracion());

        return (Estudiante) usuarioBean.modificarUsuario(estudianteDb, estudiante);
    }

    @Override
    public Estudiante modificarEstudianteAdmin(Estudiante estudiante) throws ServiciosException, UsuarioNoEncontradoException {
        if (estudiante.getId() == null) {
            throw new UsuarioNoEncontradoException("Ha ocurrido un error al modificar el usuario.");
        }

        Estudiante estudianteDb = (Estudiante) usuarioBean.encontrarUsuario(estudiante.getId());
        estudianteDb.setGeneracion(estudiante.getGeneracion());

        // TODO: RF001-03 faltan atributos de estado validado o no de usuario, aceptacion de su solicitud y modificar el tipo de usuario
        return (Estudiante) usuarioBean.modificarUsuario(estudianteDb, estudiante);
    }

	@Override
	public Estudiante borrarEstudiante(Estudiante estudiante) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Estudiante obtenerEstudiantePorAtributo(String documento) {

		TypedQuery<Estudiante> query = em
				.createQuery("SELECT e FROM Estudiante e WHERE e.documento = :documento", Estudiante.class)
				.setParameter("documento", documento);
		em.flush();
		System.out.println("ESTUDIANTEBEAN LUEGO DE LA QUERY obtenerEstudiantePorAtributo");

		return query.getSingleResult();

	}

	@Override
	public List<Estudiante> listarAsistenciasAEventos(Integer eventoId) throws ServiciosException {
		TypedQuery<Estudiante> query = em.createQuery(
				"SELECT e FROM Estudiante e INNER JOIN e.convocatoriaAsistenciaEventoEstudianteCollection event WHERE event.eventoId = :eventoId",
				Estudiante.class).setParameter("eventoId", eventoId);

		for (int i = 0; i < query.getResultList().size(); i++) {
			System.out.println(query.getResultList().get(0).toString());
		}
//		System.out.println(query.getResultList().get(0).getId());
//		System.out.println(query.getResultList().get(0).getNombres());
//		System.out.println(query.getResultList().get(0).getApellidos());
//		System.out.println(query.getResultList().get(0).getDocumento());
		em.flush();
		System.out.println("ESTUDIANTEBEAN LUEGO DE LA QUERY listarAsistenciasAEventos");
		return query.getResultList();
	}

	@Override
	public List<Estudiante> listarJustificaciones(Integer justificacionId) throws ServiciosException {
		TypedQuery<Estudiante> query = em.createQuery(
				"SELECT e FROM Estudiante e INNER JOIN e.justificacionCollection event WHERE event.eventoId = :eventoId",
				Estudiante.class).setParameter("eventoId", justificacionId);

		for (int i = 0; i < query.getResultList().size(); i++) {
			System.out.println(query.getResultList().get(0).toString());
		}
//		System.out.println(query.getResultList().get(0).getId());
//		System.out.println(query.getResultList().get(0).getNombres());
//		System.out.println(query.getResultList().get(0).getApellidos());
//		System.out.println(query.getResultList().get(0).getDocumento());
		em.flush();
		System.out.println("ESTUDIANTEBEAN LUEGO DE LA QUERY listarJustificaciones");
		return query.getResultList();
	}

	@Override
	public List<Estudiante> lisatEstudiantePorReclamos(Integer reclamoId) throws ServiciosException {
		TypedQuery<Estudiante> query = em.createQuery(
				"SELECT e FROM Estudiante e INNER JOIN e.reclamoCollection event WHERE event.eventoId = :eventoId",
				Estudiante.class).setParameter("eventoId", reclamoId);

		for (int i = 0; i < query.getResultList().size(); i++) {
			System.out.println(query.getResultList().get(0).toString());
		}
//		System.out.println(query.getResultList().get(0).getId());
//		System.out.println(query.getResultList().get(0).getNombres());
//		System.out.println(query.getResultList().get(0).getApellidos());
//		System.out.println(query.getResultList().get(0).getDocumento());
		em.flush();
		System.out.println("ESTUDIANTEBEAN LUEGO DE LA QUERY lisatEstudiantePorReclamos");
		return query.getResultList();
	}

	@Override
	public List<Estudiante> listarConstancias(Integer constanciaId) throws ServiciosException {
		TypedQuery<Estudiante> query = em.createQuery(
				"SELECT e FROM Estudiante e INNER JOIN e.constanciaCollection event WHERE event.eventoId = :eventoId",
				Estudiante.class).setParameter("eventoId", constanciaId);

		for (int i = 0; i < query.getResultList().size(); i++) {
			System.out.println(query.getResultList().get(0).toString());
		}
//		System.out.println(query.getResultList().get(0).getId());
//		System.out.println(query.getResultList().get(0).getNombres());
//		System.out.println(query.getResultList().get(0).getApellidos());
//		System.out.println(query.getResultList().get(0).getDocumento());
		em.flush();
		System.out.println("ESTUDIANTEBEAN LUEGO DE LA QUERY listarConstancias");
		return query.getResultList();
	}

	@Override
	public List<Estudiante> listarEstudiantes() throws ServiciosException {
		TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e ",Estudiante.class);

		em.flush();
		System.out.println("ESTUDIANTEBEAN LUEGO DE LA QUERY listarEstudiantes");
		return query.getResultList();

	}

}