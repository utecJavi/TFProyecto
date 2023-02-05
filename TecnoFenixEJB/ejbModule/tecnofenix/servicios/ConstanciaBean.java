package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

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
    public Constancia buscarConstancia(Integer idConstancia) throws ServiciosException {
    	try {
			return em.find(Constancia.class, idConstancia);
		} catch (PersistenceException pe) {
			throw new ServiciosException("Ocurrió un error al buscar constancia: " + pe.getMessage());
		}
    }
    
	@Override
	public Constancia crearConstancia(Constancia constancia) throws ServiciosException {
		try {
			constancia = em.merge(constancia);
			em.flush();
			return constancia;
		} catch (PersistenceException pe) {
			throw new ServiciosException("Ocurrió un error al crear constancia: " + pe.getMessage());
		}
	}

	@Override
	public Constancia modificarConstancia(Constancia constancia) throws ServiciosException {
		try {
			if (constancia.getId() != null) {
				constancia = em.merge(constancia);
				em.flush();
				return constancia;
			} else {
				throw new ServiciosException("Se quiere modificar una constancia no existente.");
			}
		} catch (PersistenceException pe) {
			throw new ServiciosException("Ocurrió un error al modficar constancia: " + pe.getMessage());
		}
	}

	@Override
	public void borrarConstancia(Constancia constancia) throws ServiciosException {
		try {
			em.remove(constancia);
			em.flush();
		} catch (PersistenceException pe) {
			throw new ServiciosException("Ocurrió un error al borrar constancia: " + pe.getMessage());
		}
	}

	@Override
	public List<Constancia> obtenerConstanciaPorAtributo(Constancia constancia) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public	List<Constancia> listadoConstancias(String usuario, String estado) throws ServiciosException {
		try {
			String consulta = "SELECT c FROM Constancia c WHERE 1=1 ";
			if (usuario != null) {
				consulta = consulta + " AND c.estudianteId.usuario = :usuario";
			}
			
			if (estado != null) {
				consulta = consulta + " AND c.estado = :estado";
			}
			
			TypedQuery<Constancia> query = em.createQuery(consulta, Constancia.class);
			if (usuario != null) {
				query.setParameter("usuario", usuario);
			}
			if (estado != null) {
				query.setParameter("estado", estado);
			}
			return query.getResultList();
		} catch (PersistenceException pe) {
			throw new ServiciosException("Ocurrió un error al consultar constancias: " + pe.getMessage());
		}
	}

}