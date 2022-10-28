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
		try{
			AccionConstancia nuevaConstancia=em.merge(acciConstancia);
			em.flush();
			return nuevaConstancia;
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear la accion constancia");
		}
	}

	@Override
	public void modificarClase(AccionConstancia acciConstancia) throws ServiciosException {
		
		try{
			em.merge(acciConstancia);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar la accion constancia");
		}
	}

	@Override
	public void borrarClase(AccionConstancia acciConstancia) throws ServiciosException {
		try{
			AccionConstancia accionConstancia = em.find(AccionConstancia.class, acciConstancia.getId()
					
					);
			em.remove(accionConstancia);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar la acccion constancia");
		}
	}

	@Override
	public List<AccionConstancia> obtenerClasePorAtributo(AccionConstancia acciConstancia) {
		TypedQuery<AccionConstancia> query = em.createQuery("SELECT a FROM AccionConstancia a WHERE a.id = :id",AccionConstancia.class)
				.setParameter("nombre", acciConstancia.getId().toString()); 
		return query.getResultList();
	}

	@Override
	public List<AccionConstancia> listarTodas() {
		TypedQuery<AccionConstancia> query = em.createQuery("SELECT a FROM AccionConstancia a",AccionConstancia.class); 
		return query.getResultList();
	}

}