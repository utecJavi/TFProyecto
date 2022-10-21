package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.AccionJustificacion;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.AccionJustificacionBeanRemote;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class AccionJustificacionBean implements AccionJustificacionBeanRemote {
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public AccionJustificacionBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public AccionJustificacion crearAccionJustificacion(AccionJustificacion accionJusti) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccionJustificacion modificarAccionJustificacion(AccionJustificacion accionJusti) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccionJustificacion borrarAccionJustificacion(AccionJustificacion accionJusti) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccionJustificacion> obtenerAccionJustificacionPorAtributo(AccionJustificacion accionJusti) {
		// TODO Auto-generated method stub
		return null;
	}

}