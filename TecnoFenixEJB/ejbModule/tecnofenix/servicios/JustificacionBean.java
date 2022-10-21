package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.Justificacion;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.JustificacionBeanRemote;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class JustificacionBean implements JustificacionBeanRemote {
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public JustificacionBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Justificacion crearJustificacion(Justificacion justificacion) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Justificacion modificarJustificacion(Justificacion justificacion) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Justificacion borrarJustificacion(Justificacion justificacion) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Justificacion> obtenerJustificacionPorAtributo(Justificacion justificacion) {
		// TODO Auto-generated method stub
		return null;
	}

}