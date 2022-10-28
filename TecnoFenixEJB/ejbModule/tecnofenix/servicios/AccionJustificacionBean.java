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
        
    }

	@Override
	public AccionJustificacion crearAccionJustificacion(AccionJustificacion accionJusti) throws ServiciosException {

		try{
			AccionJustificacion accionJusti1=em.merge(accionJusti);
			em.flush();
			return accionJusti1;
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear la accion jutificacion");
		}

	}

	@Override
	public void modificarAccionJustificacion(AccionJustificacion accionJusti) throws ServiciosException {
		try{
			em.merge(accionJusti);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar laaccion jutificacion");
		}
	}

	@Override
	public void borrarAccionJustificacion(AccionJustificacion accionJusti) throws ServiciosException {
		
		try{
			AccionJustificacion accionJusti1 = em.find(AccionJustificacion.class, accionJusti.getId());
			em.remove(accionJusti1);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar la accion justificacion");
		}
	}

	@Override
	public List<AccionJustificacion> obtenerAccionJustificacionPorAtributo(AccionJustificacion accionJusti) {
		
		TypedQuery<AccionJustificacion> query = em.createQuery("SELECT a FROM AccionJustificacion a WHERE a.nombre = :id",AccionJustificacion.class)
				.setParameter("id", accionJusti.getId()); 
		return query.getResultList();
	}

	@Override
	public List<AccionJustificacion> obtenerTodos() {
		
		TypedQuery<AccionJustificacion> query = em.createQuery("SELECT m FROM AccionJustificacion m",AccionJustificacion.class);
		return query.getResultList();
	}

}