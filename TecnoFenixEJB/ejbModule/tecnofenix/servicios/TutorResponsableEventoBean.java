package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.TutorResponsableEvento;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.TutorResponsableEventoBeanRemote;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class TutorResponsableEventoBean implements TutorResponsableEventoBeanRemote {
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public TutorResponsableEventoBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public TutorResponsableEvento crearTutorResponsableEvento(TutorResponsableEvento tutorRespEve)
			throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TutorResponsableEvento modificarTutorResponsableEvento(TutorResponsableEvento tutorRespEve)
			throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TutorResponsableEvento borrarTutorResponsableEvento(TutorResponsableEvento tutorRespEve)
			throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TutorResponsableEvento> obtenerTutorResponsableEventoPorAtributo(TutorResponsableEvento tutorRespEve) {
		// TODO Auto-generated method stub
		return null;
	}

}