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
import tecnofenix.interfaces.EstudianteBeanRemote;

/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class EstudianteBean implements EstudianteBeanRemote {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public EstudianteBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Estudiante crearEstudiante(Estudiante estudiante) throws ServiciosException {

		estudiante = em.merge(estudiante);

		return estudiante;
	}

	@Override
	public Estudiante modificarEstudiante(Estudiante estudiante) throws ServiciosException {
		if (estudiante != null) {
			estudiante = em.merge(estudiante);
		}
		return estudiante;
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

}