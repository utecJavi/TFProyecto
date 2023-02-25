package tecnofenix.servicios;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

import tecnofenix.entidades.EscolaridadDTO;
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Itr;
import tecnofenix.entidades.TipoEvento;
import tecnofenix.entidades.ModalidadEvento;
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

//        Estudiante estudianteDb = (Estudiante) usuarioBean.encontrarUsuario(estudiante.getId());

//        estudianteDb.setContrasenia(estudiante.getContrasenia());
//        estudianteDb.setGeneracion(estudiante.getGeneracion());

        return (Estudiante) usuarioBean.modificarUsuario( estudiante);
    }

    @Override
    public Estudiante modificarEstudianteAdmin(Estudiante estudiante) throws ServiciosException, UsuarioNoEncontradoException {
        if (estudiante.getId() == null) {
            throw new UsuarioNoEncontradoException("Ha ocurrido un error al modificar el usuario.");
        }

//        Estudiante estudianteDb = (Estudiante) usuarioBean.encontrarUsuario(estudiante.getId());
//        estudianteDb.setGeneracion(estudiante.getGeneracion());

        // TODO: RF001-03 faltan atributos de estado validado o no de usuario, aceptacion de su solicitud y modificar el tipo de usuario
        return (Estudiante) usuarioBean.modificarUsuario( estudiante);
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

	@Override
	public Estudiante buscarEstudiantePorId(Integer id) throws ServiciosException {
		TypedQuery<Estudiante> query = em.createNamedQuery("Estudiante.findById", Estudiante.class);
		Estudiante estudiante = query.setParameter("id", id).getSingleResult();
		return estudiante;
	}

	@Override
	public List<EscolaridadDTO> obtenerEscolaridad(Integer idEstudiante) throws ServiciosException {
		
		Query query = em.createQuery("SELECT evt.titulo, evt.tipo, evt.modalidad, evt.fin, ce.eventoId.itr.nombre, ce.calificacion "
				+ "FROM ConvocatoriaAsistenciaEventoEstudiante ce INNER JOIN ce.estudianteId est INNER JOIN ce.eventoId evt  WHERE est.id = :idEstudiante ");
		query.setParameter("idEstudiante", idEstudiante);
		
		List<Object[]> resultList = query.getResultList();
		
		List<EscolaridadDTO> dtos = new ArrayList<>(resultList.size());
        for (final Object[] row : resultList) {
            EscolaridadDTO dto = new EscolaridadDTO();
            dto.setEvento((String) row[0]);
            dto.setTipo(((TipoEvento)(row[1])).getTipo());
            dto.setModalidad(((ModalidadEvento)(row[2])).getModalidad());
            dto.setFecha((Date) row[3]);
            dto.setItr((String) row[4]);
            dto.setCalificacion((Integer) row[5]);
            dtos.add(dto);
        }
		
		return dtos;
	}
//	@Override
//	public List<Estudiante> buscarEstudiantePor(String ci, String nombre, String apellido) throws ServiciosException {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
