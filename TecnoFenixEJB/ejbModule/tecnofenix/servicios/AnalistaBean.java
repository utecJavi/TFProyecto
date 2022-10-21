package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.Analista;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.AnalistaBeanRemote;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class AnalistaBean implements AnalistaBeanRemote {
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public AnalistaBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Analista crearAnalista(Analista analista) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Analista modificarAnalista(Analista analista) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Analista borrarAnalista(Analista analista) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Analista> obtenerAnalistaPorAtributo(Analista analista) {
		// TODO Auto-generated method stub
		return null;
	}

}