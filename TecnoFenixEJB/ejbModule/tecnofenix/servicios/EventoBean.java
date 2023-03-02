package tecnofenix.servicios;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.*;
import tecnofenix.exception.ItrNoEncontradoException;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.EventoBeanRemote;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class EventoBean implements EventoBeanRemote {
	@PersistenceContext
	private EntityManager em;
	
	private ItrBean itrBean;
	private TutorResponsableEventoBean treBean;
	
    /**
     * Default constructor. 
     */
    public EventoBean() {
        // TODO Auto-generated constructor stub
    	itrBean = new ItrBean();
    	treBean= new TutorResponsableEventoBean();
    }

	@Override
	public Evento crearEvento(Evento evento) throws ServiciosException {
		System.out.println("CREAR EVENTO");
		System.out.println(evento.toString());
		System.out.println(itrBean);
		evento.setItr(itrBean.findById2(evento.getItr().getId(), em));
		em.persist(evento);
		em.flush();
		System.out.println(evento.toString());
		// TODO Auto-generated method stub
		return evento;
	}

	@Override
	public Evento modificarEvento(Evento evento) throws ServiciosException {
//		System.out.println("Antes de modificar el evento se manda a guardar los tutores responsables");
//		for(TutorResponsableEvento tre : evento.getTutorResponsableEventoCollection()) {
//			treBean.modificarTutorResponsableEvento(tre);
//		}
//		System.out.println("Se guardaron los tutores responsables, se pasa a guardar el evento");
		em.merge(evento);
		em.flush();
		System.out.println("Se guardo el evento...");
		return evento;
	}

	@Override
	public Evento borrarEvento(Evento evento) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evento> obtenerEventoPorAtributo(Evento evento) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Evento obtenerEvento(Integer id) {
		TypedQuery<Evento> query = em.createNamedQuery("Evento.findById", Evento.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public List<Estudiante> obtenerEstudiantesConvocados(Evento evento) {
		evento = obtenerEvento(evento.getId());
//		Query query = em.createQuery("SELECT es FROM Evento ev INNER JOIN FETCH ev.convocatoriaAsistenciaEventoEstudianteCollection c INNER JOIN FETCH c.estudianteId es WHERE ev.id = :id");
		TypedQuery<Estudiante> query = em.createQuery("SELECT es FROM Evento ev INNER JOIN ev.convocatoriaAsistenciaEventoEstudianteCollection c INNER JOIN c.estudianteId es WHERE ev.id = :id", Estudiante.class);
		query.setParameter("id", evento.getId());
		return query.getResultList();
	}

	@Override
	public List<Evento> listarEventos() {
		
		List<Evento> list = new ArrayList<Evento>();
		TypedQuery<Evento> query = em.createNamedQuery("Evento.findAll", Evento.class);
		list=query.getResultList();
		
		return list;
	}
	
	@Override
	public List<Evento> listarEventosTutor(Usuario tutor) {
		
		String jpql = "SELECT e FROM Evento e INNER JOIN e.tutorResponsableEventoCollection c WHERE c.tutorId = :tutorId";
		TypedQuery<Evento> query = em.createQuery(jpql, Evento.class);
		query.setParameter("tutorId", tutor);
		System.out.println(jpql);
		List<Evento> list = query.getResultList();
		if (list == null) {
			throw new ItrNoEncontradoException("Eventos no encontrado.");
		}
		return list;
	}
	
	@Override
	public List<Evento> buscarEventosPor(String id, String titulo) {
		String conditions = "";
		
		if (id != null && id != "") {
			conditions = conditions + " AND e.id = " + id;
		}
		if (titulo != null && titulo != "") {

			conditions = conditions + " AND e.titulo LIKE '%" + titulo + "%'";

		}
		
		String jpql = "SELECT e FROM Evento e WHERE 1=1 " + conditions;
		TypedQuery<Evento> query = em.createQuery(jpql, Evento.class);
		System.out.println(jpql);
		List<Evento> list = query.getResultList();
		if (list == null) {
			throw new ItrNoEncontradoException("Eventos no encontrado.");
		}

		return list;
	}
	
	
	@Override
	public List<Evento> buscarEventosPor(String id, String titulo,String localizacion,String modalidad,String tipoEvento,String itrNombre,Date inicio1, Date fin1,Date inicio2, Date fin2,Boolean activo) {
//		 DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH24:MI:SS.FF");

		
		 
		System.out.println("----------------------------------------");
		System.out.println("titulo: " + (titulo != null ? titulo : "null"));
		System.out.println("localizacion: " + (localizacion != null ? localizacion : "null"));
		System.out.println("modalidad: " + (modalidad != null ? modalidad : "null"));
		System.out.println("tipoEvento: " + (tipoEvento != null ? tipoEvento : "null"));
		System.out.println("itrNombre: " + (itrNombre != null ? itrNombre : "null"));
		System.out.println("inicioInicio: " + (inicio1 != null ? inicio1.getTime() : "null"));
		System.out.println("finInicio: " + (fin1 != null ? fin1.getTime() : "null"));
		System.out.println("inicioFin: " + (inicio2 != null ? inicio2.getTime() : "null"));
		System.out.println("finFin: " + (fin2 != null ? fin2.getTime() : "null"));
		System.out.println("activo: " + (activo != null ? activo : "null"));
		System.out.println("----------------------------------------");
		
		String conditions = "";
		String joinJPQL = "";
		
//		DD-MON-YYYY HH24:MI:SS
		if (id != null && id != "") {
			conditions = conditions + " AND e.id = " + id;
		}
		if (titulo != null && titulo != "") {

			conditions = conditions + " AND e.titulo LIKE '%" + titulo + "%'";

		}
		if (localizacion != null && localizacion != "") {

			conditions = conditions + " AND e.localizacion LIKE '%" + localizacion + "%'";

		}
		
		if (tipoEvento != null && tipoEvento != "") {

			conditions = conditions + " AND e.tipo LIKE '%" + tipoEvento + "%'";

		}
		
		if (modalidad != null && modalidad != "") {

			conditions = conditions + " AND e.modalidad LIKE '%" + modalidad + "%'";

		}
		
		if (inicio1 != null && fin1 != null) {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		    String inicio1Str = dateFormat.format(inicio1);
		    String fin1Str = dateFormat.format(fin1);
		    conditions = conditions + " AND (e.inicio BETWEEN '"+inicio1Str+"' AND '"+fin1Str+"') ";
		}
		

		if (inicio2 != null && fin2 != null ) {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		    String inicio2Str = dateFormat.format(inicio2);
		    String fin2Str = dateFormat.format(fin2);
		    conditions = conditions + " AND (e.fin BETWEEN '"+inicio2Str+"' AND '"+fin2Str+"') ";
		}
				
		if (itrNombre != null && itrNombre != "") {

			joinJPQL = " INNER JOIN e.itr i ";
			conditions = conditions + " AND i.nombre LIKE '" + itrNombre + "'";

		}
		
		List<Evento> list =null;
		try {
			String jpql = "SELECT e FROM Evento e  " + joinJPQL + " WHERE 1=1 " + conditions;
			TypedQuery<Evento> query = em.createQuery(jpql, Evento.class);
//			if (inicioInicio != null && finInicio != null) {
//				query.setParameter("inicioInicio", inicioInicio);
//				query.setParameter("finInicio", finInicio);
//			}
//			
//			if (inicioFin != null && finFin != null) {
//				query.setParameter("inicioFin", inicioFin);
//				query.setParameter("finFin", inicioFin);
//			}
			
			
			System.out.println(jpql);
			list = query.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		if (list == null) {
			throw new ItrNoEncontradoException("Eventos no encontrado.");
		}

		return list;
	}

	@Override
	public TipoEstadoEvento crearTipoEstadoEvento(TipoEstadoEvento tEE) {
		try {
			tEE=em.merge(tEE);
			em.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return tEE;
	}

	@Override
	public TipoEstadoEvento editarTipoEstadoEvento(TipoEstadoEvento tEE) {
		try {
			tEE=em.merge(tEE);
			em.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return tEE;
	}

	@Override
	public List<TipoEstadoEvento> listarTipoEstadoEvento() {
		List<TipoEstadoEvento> list = new ArrayList<TipoEstadoEvento>();
		try {
		
			System.out.println("TipoEstadoEvento listarTipoEstadoEvento()");
			TypedQuery<TipoEstadoEvento> query = em.createNamedQuery("TipoEstadoEvento.findAll", TipoEstadoEvento.class);
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
	public List<TipoEstadoEvento> buscarTipoEstadoEventoPor(String id, String nombre) {
		String conditions = "";
		if (id != null && id != "") {
			conditions = conditions + " AND i.id = " + id;
		}
		if (nombre != null && nombre != "") {

			conditions = conditions + " AND i.nombre LIKE '%" + nombre + "%'";

		}

		List<TipoEstadoEvento> list = new ArrayList<TipoEstadoEvento>();
		
		TypedQuery<TipoEstadoEvento> query = em.createQuery("SELECT i FROM TipoEstadoEvento i WHERE 1=1 " + conditions, TipoEstadoEvento.class);
		list = query.getResultList();
		if (list == null) {
			throw new ItrNoEncontradoException("TipoEstadoEvento no encontrado.");
		}

		return list;

	}
	

}
