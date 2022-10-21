package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.ConvocatoriaAsistenciaEventoEstudiante;
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

}