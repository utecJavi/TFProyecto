package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.AccionConstancia;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.AccionConstanciaBeanRemote;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class AccionConstanciaBean implements AccionConstanciaBeanRemote {
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public AccionConstanciaBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public AccionConstancia crearClase(AccionConstancia acciConstancia) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccionConstancia modificarClase(AccionConstancia acciConstancia) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccionConstancia borrarClase(AccionConstancia acciConstancia) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccionConstancia> obtenerClasePorAtributo(AccionConstancia acciConstancia) {
		// TODO Auto-generated method stub
		return null;
	}

}