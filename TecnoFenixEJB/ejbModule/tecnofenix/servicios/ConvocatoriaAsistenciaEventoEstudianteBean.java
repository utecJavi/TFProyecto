package tecnofenix.servicios;

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
import tecnofenix.exception.ServiciosException;
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
	public ConvocatoriaAsistenciaEventoEstudiante crearClase(ConvocatoriaAsistenciaEventoEstudiante conAsisEventEstu)
			throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
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
	public List<ConvocatoriaAsistenciaEventoEstudiante> obtetenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregarEstudiante(Estudiante estudiante, Evento evento) {
		ConvocatoriaAsistenciaEventoEstudiante convocatoriaAsistenciaEventoEstudiante = new ConvocatoriaAsistenciaEventoEstudiante(null, evento, estudiante);
		em.persist(convocatoriaAsistenciaEventoEstudiante);
		em.flush();
	}

	@Override
	public void registrarAsistencia(Integer id) {
		Query query = em.createQuery("UPDATE ConvocatoriaAsistenciaEventoEstudiante c SET asistencia = 1 WHERE id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

}
