package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.GestionEventoAnalista;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.GestionEventoAnalistaBeanRemote;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class GestionEventoAnalistaBean implements GestionEventoAnalistaBeanRemote {
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public GestionEventoAnalistaBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public GestionEventoAnalista crearGestionEventoAnalista(GestionEventoAnalista gestionEventoAnalista)
			throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GestionEventoAnalista modificarGestionEventoAnalista(GestionEventoAnalista gestionEventoAnalista)
			throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GestionEventoAnalista borrarGestionEventoAnalista(GestionEventoAnalista gestionEventoAnalista)
			throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GestionEventoAnalista> obtenerGestionEventoAnalistaPorAtributo(
			GestionEventoAnalista gestionEventoAnalista) {
		// TODO Auto-generated method stub
		return null;
	}

}