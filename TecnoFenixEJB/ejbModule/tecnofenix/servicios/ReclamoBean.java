package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.Reclamo;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.ReclamoBeanRemote;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class ReclamoBean implements ReclamoBeanRemote {
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ReclamoBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Reclamo crearReclamo(Reclamo reclamo) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reclamo modificarReclamo(Reclamo reclamo) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reclamo borrarReclamo(Reclamo reclamo) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reclamo> obtenerReclamoPorAtributo(Reclamo reclamo) {
		// TODO Auto-generated method stub
		return null;
	}

}