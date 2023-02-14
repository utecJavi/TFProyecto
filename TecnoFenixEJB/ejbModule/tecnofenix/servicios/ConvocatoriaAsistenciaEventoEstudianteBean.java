package tecnofenix.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.ConvocatoriaAsistenciaEventoEstudiante;
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Evento;
import tecnofenix.entidades.Itr;
import tecnofenix.entidades.Usuario;
import tecnofenix.exception.ItrNoEncontradoException;
import tecnofenix.exception.ServiciosException;
import tecnofenix.exception.UsuarioNoEncontradoException;
import tecnofenix.interfaces.ConvocatoriaAsistenciaEventoEstudianteBeanRemote;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class ConvocatoriaAsistenciaEventoEstudianteBean  implements ConvocatoriaAsistenciaEventoEstudianteBeanRemote {
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ConvocatoriaAsistenciaEventoEstudianteBean() {
        // TODO Auto-generated constructor stub
    }


	@Override
	public ConvocatoriaAsistenciaEventoEstudiante modificarClase(
			ConvocatoriaAsistenciaEventoEstudiante conAsisEventEstu) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConvocatoriaAsistenciaEventoEstudiante borrarClase(ConvocatoriaAsistenciaEventoEstudiante conAsisEventEstu)
			throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConvocatoriaAsistenciaEventoEstudiante> obtenerClasePorAtributo(
			ConvocatoriaAsistenciaEventoEstudiante conAsisEventEstu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConvocatoriaAsistenciaEventoEstudiante> obtenerTodos() {
		List<ConvocatoriaAsistenciaEventoEstudiante> list = new ArrayList<ConvocatoriaAsistenciaEventoEstudiante>();
		TypedQuery<ConvocatoriaAsistenciaEventoEstudiante> query = em.createNamedQuery("ConvocatoriaAsistenciaEventoEstudiante.findAll", ConvocatoriaAsistenciaEventoEstudiante.class);
		list = query.getResultList();

		if (list == null) {
			System.out.println("ConvocatoriaAsistenciaEventoEstudiante ... lista vacia");	
		}
		System.out.println("ConvocatoriaAsistenciaEventoEstudiante ... retornando lista");
		
		return list;
	}


	@Override
	public void registrarAsistencia(Integer id) {
		Query query = em.createQuery("UPDATE ConvocatoriaAsistenciaEventoEstudiante c SET asistencia = 1 WHERE id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	@Override
	public List<ConvocatoriaAsistenciaEventoEstudiante> filtrarAsistEstuAEventosPor(String id, String tituloEvento,
			String nombre, String apellido,String documento ,String valorLogico,String calificacion,Boolean asistencia) throws ServiciosException {

		String conditions = "";
		String joinJPQL = "";
		if (id != null && id != "") {
			conditions = conditions + " AND conv.id = " + id;
		}
		
		if (calificacion != null && calificacion != "") {
			//valorLogico es iguala (>,<,=)
			conditions = conditions + " AND conv.calificacion " +valorLogico+" "+  calificacion;
		}
		
		if(nombre != null && nombre != "" ||apellido != null && apellido != "" ||documento != null && documento != "") {
			joinJPQL = " INNER JOIN conv.estudianteId estu ";
		
		
		if (nombre != null && nombre != "") {

			conditions = conditions + " AND estu.nombres LIKE '" + nombre + "%'";

		}
		if (apellido != null && apellido != "") {

			conditions = conditions + " AND estu.apellidos LIKE '" + apellido + "%'";

		}
		if (documento != null && documento != "") {

			conditions = conditions + " AND estu.documento LIKE '" + documento + "'";

		}
		}
		if (asistencia != null ) {

			conditions = conditions + " AND conv.asistencia LIKE " + asistencia ;

		}

		if (tituloEvento != null && tituloEvento != "") {
			joinJPQL =joinJPQL+ " INNER JOIN conv.eventoId event ";
			conditions = conditions + " AND event.titulo LIKE '%" + tituloEvento + "%'";

		}

		String jpql = "SELECT conv FROM ConvocatoriaAsistenciaEventoEstudiante conv " + joinJPQL + " WHERE 1=1 " + conditions;
		TypedQuery<ConvocatoriaAsistenciaEventoEstudiante> query = em.createQuery(jpql, ConvocatoriaAsistenciaEventoEstudiante.class);
		System.out.println(jpql);
		List<ConvocatoriaAsistenciaEventoEstudiante> list = query.getResultList();
		if (list == null) {
			throw new ServiciosException("ConvocatoriaAsistenciaEventoEstudiante no encontrado.");
		}

		return list;

	}

	@Override
	public ConvocatoriaAsistenciaEventoEstudiante crearClase(ConvocatoriaAsistenciaEventoEstudiante conAsisEventEstu)
			throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConvocatoriaAsistenciaEventoEstudiante agregarEstudianteAEvento(
			ConvocatoriaAsistenciaEventoEstudiante convAsistEventEstu) {
		
		em.persist(convAsistEventEstu);
		em.flush();
		return convAsistEventEstu;
	}


	@Override
	public ConvocatoriaAsistenciaEventoEstudiante obtenerDatosConvPorId(Integer id) throws ServiciosException {
		TypedQuery<ConvocatoriaAsistenciaEventoEstudiante> query = em.createNamedQuery("ConvocatoriaAsistenciaEventoEstudiante.findById", ConvocatoriaAsistenciaEventoEstudiante.class);
		ConvocatoriaAsistenciaEventoEstudiante conv = query.setParameter("id", id).getSingleResult();

		if (conv == null) {
			throw new ServiciosException("ConvocatoriaAsistenciaEventoEstudiante no encontrado.");
		}

		return conv;
		

	}


}
