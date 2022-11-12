package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import ejbModule.tecnofenix.interfaces.String;
import tecnofenix.entidades.Constancia;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.ConstanciaBeanRemote;



/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class ConstanciaBean implements ConstanciaBeanRemote {
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ConstanciaBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Constancia crearConstancia(Constancia constancia) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Constancia modificarConstancia(Constancia constancia) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Constancia borrarConstancia(Constancia constancia) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Constancia> obtenerConstanciaPorAtributo(Constancia constancia) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	List<Constancia> listadoConstancias(String usuario) throws ServiciosException {
		try {
			String consulta = "SELECT c FROM Constancia c";
			if (usuario != null) {
				consulta = consulta + " WHERE c.estudianteId.usuario = :usuario";
			}
			
			TypedQuery<Constancia> query = em.createQuery(consulta, Constancia.class);
			if (usuario != null) {
				query.setParameter("usuario", usuario);
			}
			return query.getResultList();
		} catch (PersistenceException pe) {
			throw new ServiciosException("Ocurrió un error al consultar constancias: " + pe.getMessage());
		}
	}

}