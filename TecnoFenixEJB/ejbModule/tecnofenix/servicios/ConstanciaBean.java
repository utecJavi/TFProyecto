package tecnofenix.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

//import ejbModule.tecnofenix.interfaces.Boolean;
import tecnofenix.entidades.Constancia;
import tecnofenix.entidades.TipoConstancia;
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
				throw new ServiciosException("Se quiere modificar una constancia que no existente.");
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
			if (usuario != null && usuario != "") {
				consulta = consulta + " AND c.estudianteId.usuario = :usuario";
			}
			
			if (estado != null && estado != "") {
				consulta = consulta + " AND c.estado = :estado";
			}
			
			TypedQuery<Constancia> query = em.createQuery(consulta, Constancia.class);
			if (usuario != null && usuario != "") {
				query.setParameter("usuario", usuario);
			}
			if (estado != null && estado != "") {
				query.setParameter("estado", estado);
			}
			return query.getResultList();
		} catch (PersistenceException pe) {
			throw new ServiciosException("Ocurrio un error al consultar constancias: " + pe.getMessage());
		}
	}
	
	@Override
	public TipoConstancia crearTipoConstancia(TipoConstancia tipoConstancia) throws ServiciosException {
		try {
			tipoConstancia = em.merge(tipoConstancia);
			em.flush();
			return tipoConstancia;
		} catch (PersistenceException pe) {
			throw new ServiciosException("Ocurrió un error al crear TipoConstancia: " + pe.getMessage());
		}
	}

	@Override
	public TipoConstancia modificarTipoConstancia(TipoConstancia tipoConstancia) throws ServiciosException {
		try {
			if (tipoConstancia.getId() != null) {
				tipoConstancia = em.merge(tipoConstancia);
				em.flush();
				return tipoConstancia;
			} else {
				throw new ServiciosException("Se quiere modificar un tipo de constancia que no existente.");
			}
		} catch (PersistenceException pe) {
			throw new ServiciosException("Ocurrió un error al modficar TipoConstancia: " + pe.getMessage());
		}
	}

	@Override
	public void bajaTipoConstancia(TipoConstancia tipoConstancia) throws ServiciosException {
		try {
			tipoConstancia.setBaja(true);
			em.merge(tipoConstancia);
			em.flush();
		} catch (PersistenceException pe) {
			throw new ServiciosException("Ocurrió un error al borrar TipoConstancia: " + pe.getMessage());
		}
	}
	
	@Override
	public	List<TipoConstancia> listadoTipoConstancia(Boolean baja) throws ServiciosException {
		try {
			String consulta = "SELECT tc FROM TipoConstancia tc WHERE tc.baja = :baja";
			TypedQuery<TipoConstancia> query = em.createQuery(consulta, TipoConstancia.class);
			query.setParameter("baja", baja);
			return query.getResultList();
		} catch (PersistenceException pe) {
			throw new ServiciosException("Ocurrio un error al consultar constancias: " + pe.getMessage());
		}
		
	}

}
