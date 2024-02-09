package tecnofenix.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.AccionReclamo;
import tecnofenix.entidades.Itr;
import tecnofenix.entidades.Usuario;
import tecnofenix.exception.ItrNoEncontradoException;
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
		try {
			System.out.println("Backend AccionReclamoBean crearAccionReclamo");

			if(accionReclamo.getId()==null)System.out.println("accionReclamo.getId()==null");
			accionReclamo = em.merge(accionReclamo);
			em.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return accionReclamo;
	}

	@Override
	public AccionReclamo modificarAccionReclamo(AccionReclamo accionReclamo) throws ServiciosException {
		try {
			em.merge(accionReclamo);
			em.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return accionReclamo;
	}

	@Override
	public AccionReclamo borrarAccionReclamo(AccionReclamo accionReclamo) throws ServiciosException {
		try {
			accionReclamo.setActivo(false);
			em.merge(accionReclamo);
			em.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return accionReclamo;
	}

	@Override
	public List<AccionReclamo> obtenerAccionReclamoPorAtributo(AccionReclamo accionReclamo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccionReclamo> listAllAccionReclamoByReclamo(Integer reclamoID) throws ServiciosException {
		List<AccionReclamo> list = new ArrayList<AccionReclamo>();
		try {
		
			String jpql = "SELECT a FROM AccionReclamo a INNER JOIN a.reclamoId rID WHERE rID=" + reclamoID;
			TypedQuery<AccionReclamo> query = em.createQuery(jpql, AccionReclamo.class);
			System.out.println(jpql);
			list = query.getResultList();
			
			} catch (Exception e) {
				// TODO: handle exception
			}
		if (list == null) {
			throw new ItrNoEncontradoException("AccionReclamo no encontrados para reclamo id "+reclamoID);
		}
		System.out.println("-----List<AccionReclamo> listAllAccionReclamoByReclamo------");
		return list;
	}

}