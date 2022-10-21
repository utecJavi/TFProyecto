package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.Estudiante;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Estudiante modificarEstudiante(Estudiante estudiante) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
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