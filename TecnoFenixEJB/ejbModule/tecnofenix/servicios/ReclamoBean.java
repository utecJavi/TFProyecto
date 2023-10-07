package tecnofenix.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.Reclamo;
import tecnofenix.exception.ReclamoNoEncontradoException;
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
		em.merge(reclamo);
		em.flush();
		return reclamo;
	}

	@Override
	public Reclamo modificarReclamo(Reclamo reclamo) throws ServiciosException {
		em.merge(reclamo);
		em.flush();
		return reclamo;
	}

	@Override
	public Reclamo borrarReclamo(Reclamo reclamo) throws ServiciosException {
		reclamo.setActivo(false);
		em.merge(reclamo);
		em.flush();
		return reclamo;
	}

	@Override
	public List<Reclamo> obtenerReclamoPorAtributo(Reclamo reclamo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Reclamo> listarReclamos() {
		List<Reclamo> listaReclamos = new ArrayList<Reclamo>();
		TypedQuery<Reclamo> query = em.createNamedQuery("Reclamo.findAll", Reclamo.class);
		listaReclamos = query.getResultList();
		if (listaReclamos.isEmpty()) {
			System.out.println("Reclamo no encontrado");;
		}
		return listaReclamos;
	}
	
	//Se agrego en esta segunda evolucion
	public Reclamo buscarReclamoPorId(Integer id) throws ReclamoNoEncontradoException {
		TypedQuery<Reclamo> query = em.createNamedQuery("Reclamo.findById", Reclamo.class);
		Reclamo reclamo = query.setParameter("id", id).getSingleResult();
		if (reclamo == null) {
			System.out.println("Reclamo no encontrado");;
		}

		return reclamo;
	}
}
