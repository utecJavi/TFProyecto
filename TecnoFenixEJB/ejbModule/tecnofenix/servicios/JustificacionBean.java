package tecnofenix.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.Justificacion;
import tecnofenix.entidades.TipoEstadoJustificacion;
import tecnofenix.exception.JustificacionNoEncontradaException;
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
		em.merge(justificacion);
		em.flush();
		return justificacion;
	}

	@Override
	public Justificacion modificarJustificacion(Justificacion justificacion) throws ServiciosException {
		// TODO Auto-generated method stub
		System.out.println(" MODIFICAR JUSFITICACION" );
		System.out.println(justificacion.toString());
		em.merge(justificacion);
		em.flush();
		return justificacion;
	}

	@Override
	public Justificacion borrarJustificacion(Justificacion justificacion) throws ServiciosException {
		// TODO Auto-generated method stub
		justificacion.setActivo(false);
		em.merge(justificacion);
		em.flush();
		return justificacion;
	}

	@Override
	public List<Justificacion> obtenerJustificacionPorAtributo(Justificacion justificacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Justificacion> listarJustificacion() {
		List<Justificacion> listaJustificacion = new ArrayList<Justificacion>();
		TypedQuery<Justificacion> query = em.createNamedQuery("Justificacion.findAll", Justificacion.class);
		listaJustificacion = query.getResultList();
		if (listaJustificacion.isEmpty()) {
			System.out.println("Justificacion no encontrada");;
		}
		return listaJustificacion;
	}


	//Se agrego en esta segunda evolucion
	@Override
	public Justificacion buscarJustificacionPorId(Integer id) throws JustificacionNoEncontradaException {
		if(id !=null) {
			TypedQuery<Justificacion> query = em.createNamedQuery("Justificacion.findById", Justificacion.class);
			Justificacion justificacion = query.setParameter("id", id).getSingleResult();
			if (justificacion == null) {
				System.out.println("Justificacion no encontrada");;
			}
			return justificacion;
			}
			return null;
	}

	@Override
	public List<Justificacion> buscarJustificacionsEstudiante(Integer id) throws JustificacionNoEncontradaException {
		List<Justificacion> list = new ArrayList<Justificacion>();
		
		TypedQuery<Justificacion> query = em.createQuery("SELECT r FROM Justificacion r INNER JOIN r.estudianteId est WHERE est.id="+id, Justificacion.class);
		list = query.getResultList();
		if (list == null) {
			throw new JustificacionNoEncontradaException("Justificaciones no encontradas.");
		}

		return list;
	}

	@Override
	public TipoEstadoJustificacion crearTipoEstadoJustificacion(TipoEstadoJustificacion tEJ) {
		try {
			tEJ=em.merge(tEJ);
			em.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return tEJ;
	}

	@Override
	public TipoEstadoJustificacion editarTipoEstadoJustificacion(TipoEstadoJustificacion tEJ) {
		try {
			tEJ=em.merge(tEJ);
			em.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return tEJ;
	}

	@Override
	public List<TipoEstadoJustificacion> listarTipoEstadoJustificacion() {
		List<TipoEstadoJustificacion> list = new ArrayList<TipoEstadoJustificacion>();
		try {
		
			System.out.println("TipoEstadoJustificacion listarTipoEstadoJustificacion()");
			TypedQuery<TipoEstadoJustificacion> query = em.createNamedQuery("TipoEstadoJustificacion.findAll", TipoEstadoJustificacion.class);
//			query.setParameter("activo", activo);
			list = query.getResultList();
			
			} catch (Exception e) {
				// TODO: handle exception
			}
		if (list == null) {
			throw new JustificacionNoEncontradaException("TipoEstadoJustificacion no encontradas.");
		}
		System.out.println("TipoEstadoJustificacion LUEGO DE LA QUERY TipoEstadoJustificacion");
		return list;
	}

	@Override
	public List<TipoEstadoJustificacion> buscarTipoEstadoJustificacionPor(String id, String nombre) {
		String conditions = "";
		if (id != null && id != "") {
			conditions = conditions + " AND i.id = " + id;
		}
		if (nombre != null && nombre != "") {

			conditions = conditions + " AND i.nombre LIKE '%" + nombre + "%'";

		}

		List<TipoEstadoJustificacion> list = new ArrayList<TipoEstadoJustificacion>();
		
		TypedQuery<TipoEstadoJustificacion> query = em.createQuery("SELECT i FROM TipoEstadoJustificacion i WHERE 1=1 " + conditions, TipoEstadoJustificacion.class);
		list = query.getResultList();
		if (list == null) {
			throw new JustificacionNoEncontradaException("TipoEstadoJustificacion no encontradas.");
		}

		return list;
	}


}