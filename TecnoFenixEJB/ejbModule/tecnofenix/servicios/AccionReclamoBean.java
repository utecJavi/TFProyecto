package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.AccionReclamo;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.AccionReclamoBeanRemote;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class AccionReclamoBean implements AccionReclamoBeanRemote {
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public AccionReclamoBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public AccionReclamo crearAccionReclamo(AccionReclamo accionReclamo) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccionReclamo modificarAccionReclamo(AccionReclamo accionReclamo) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccionReclamo borrarAccionReclamo(AccionReclamo accionReclamo) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccionReclamo> obtenerAccionReclamoPorAtributo(AccionReclamo accionReclamo) {
		// TODO Auto-generated method stub
		return null;
	}

}