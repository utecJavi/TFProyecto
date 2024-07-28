package tecnofenix.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.AccionJustificacion;
import tecnofenix.entidades.AccionReclamo;
import tecnofenix.exception.ItrNoEncontradoException;
import tecnofenix.exception.JustificacionNoEncontradaException;
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
		try {
			System.out.println("Backend AccionJustificacion crearAccionJustificacion");

			if(accionJusti.getId()==null)System.out.println("accionJusti.getId()==null");
			accionJusti = em.merge(accionJusti);
			em.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return accionJusti;

	}

	@Override
	public AccionJustificacion modificarAccionJustificacion(AccionJustificacion accionJusti) throws ServiciosException {
		try {
			accionJusti.setActivo(false);
			em.merge(accionJusti);
			em.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return accionJusti;
	}

	@Override
	public AccionJustificacion borrarAccionJustificacion(AccionJustificacion accionJusti) throws ServiciosException {
		
		try{
			AccionJustificacion accionJusti1 = em.find(AccionJustificacion.class, accionJusti.getId());
			em.remove(accionJusti1);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar la accion justificacion");
		}
		return accionJusti;
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
	
	@Override
	public List<AccionJustificacion> listAllAccionJustificacionByJustificacionID(Integer justificacionID) throws ServiciosException {
		List<AccionJustificacion> list = new ArrayList<AccionJustificacion>();
		try {
		
			String jpql = "SELECT a FROM AccionJustificacion a INNER JOIN a.justificacionId rID WHERE rID=" + justificacionID;
			TypedQuery<AccionJustificacion> query = em.createQuery(jpql, AccionJustificacion.class);
			System.out.println(jpql);
			list = query.getResultList();
			
			} catch (Exception e) {
				// TODO: handle exception
			}
		if (list == null) {
			throw new JustificacionNoEncontradaException("AccionJustificacion no encontrados para justificacion id "+justificacionID);
		}
		System.out.println("-----List<AccionJustificacion> listAllAccionJustificacionByJustificacionID------");
		return list;
	}

}