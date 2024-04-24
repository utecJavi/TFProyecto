package tecnofenix.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.Reclamo;
import tecnofenix.entidades.TipoEstadoEvento;
import tecnofenix.entidades.TipoEstadoReclamo;
import tecnofenix.exception.ItrNoEncontradoException;
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
		System.out.println(" MODIFICAR RECLAMO" );
		System.out.println(reclamo.toString());
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
		if(id !=null) {
		TypedQuery<Reclamo> query = em.createNamedQuery("Reclamo.findById", Reclamo.class);
		Reclamo reclamo = query.setParameter("id", id).getSingleResult();
		if (reclamo == null) {
			System.out.println("Reclamo no encontrado");;
		}
		return reclamo;
		}
		return null;
	}

	public List<Reclamo> buscarReclamosEstudiante(Integer id) throws ReclamoNoEncontradoException{
		
		List<Reclamo> list = new ArrayList<Reclamo>();
		
		TypedQuery<Reclamo> query = em.createQuery("SELECT r FROM Reclamo r INNER JOIN r.estudianteId est WHERE est.id="+id, Reclamo.class);
		list = query.getResultList();
		if (list == null) {
			throw new ItrNoEncontradoException("Reclamo no encontrado.");
		}

		return list;
	
	}
	
	//TIPO ESTADO RECLAMO
	@Override
	public TipoEstadoReclamo crearTipoEstadoReclamo(TipoEstadoReclamo tER) {
		try {
			tER=em.merge(tER);
			em.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return tER;
	}

	@Override
	public TipoEstadoReclamo editarTipoEstadoReclamo(TipoEstadoReclamo tER) {
		try {
			tER=em.merge(tER);
			em.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return tER;
	}

	@Override
	public List<TipoEstadoReclamo> listarTipoEstadoReclamo() {
		List<TipoEstadoReclamo> list = new ArrayList<TipoEstadoReclamo>();
		try {
		
			System.out.println("TipoEstadoReclamo listarTipoEstadoReclamo()");
			TypedQuery<TipoEstadoReclamo> query = em.createNamedQuery("TipoEstadoReclamo.findAll", TipoEstadoReclamo.class);
//			query.setParameter("activo", activo);
			list = query.getResultList();
			
			} catch (Exception e) {
				// TODO: handle exception
			}
		if (list == null) {
			throw new ItrNoEncontradoException("TipoEstadoReclamo no encontrados.");
		}
		System.out.println("TipoEstadoReclamo LUEGO DE LA QUERY TipoEstadoReclamo");
		return list;
	}

	@Override
	public List<TipoEstadoReclamo> buscarTipoEstadoReclamoPor(String id, String nombre) {
		String conditions = "";
		if (id != null && id != "") {
			conditions = conditions + " AND i.id = " + id;
		}
		if (nombre != null && nombre != "") {

			conditions = conditions + " AND i.nombre LIKE '%" + nombre + "%'";

		}

		List<TipoEstadoReclamo> list = new ArrayList<TipoEstadoReclamo>();
		
		TypedQuery<TipoEstadoReclamo> query = em.createQuery("SELECT i FROM TipoEstadoReclamo i WHERE 1=1 " + conditions, TipoEstadoReclamo.class);
		list = query.getResultList();
		if (list == null) {
			throw new ItrNoEncontradoException("TipoEstadoReclamo no encontrado.");
		}

		return list;
	}
}
