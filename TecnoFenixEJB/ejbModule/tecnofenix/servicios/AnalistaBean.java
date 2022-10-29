package tecnofenix.servicios;

import tecnofenix.entidades.Analista;
import tecnofenix.exception.ServiciosException;
import tecnofenix.exception.UsuarioNoEncontradoException;
import tecnofenix.interfaces.AnalistaBeanRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class AnalistaBean implements AnalistaBeanRemote {
//	@PersistenceContext
	private EntityManager em;

	private UsuarioBean usuarioBean;
	
    /**
     * Default constructor. 
     */
    public AnalistaBean() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TecnoFenixEJB");
		em = entityManagerFactory.createEntityManager();
		usuarioBean = new UsuarioBean();
    }

	@Override
	public Analista crearAnalista(Analista analista) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Analista modificarAnalistaPropio(Analista analista) throws ServiciosException, UsuarioNoEncontradoException {
		if (analista.getId() == null) {
			throw new UsuarioNoEncontradoException("Ha ocurrido un error al modificar el usuario.");
		}

		Analista analistaDb = (Analista) usuarioBean.encontrarUsuario(analista.getId());

		analistaDb.setContrasenia(analista.getContrasenia());

		return (Analista) usuarioBean.modificarUsuario(analistaDb, analista);
	}

	@Override
	public Analista modificarAnalistaAdmin(Analista analista) throws ServiciosException, UsuarioNoEncontradoException {
		if (analista.getId() == null) {
			throw new UsuarioNoEncontradoException("Ha ocurrido un error al modificar el usuario.");
		}

		Analista analistaDb = (Analista) usuarioBean.encontrarUsuario(analista.getId());

		return (Analista) usuarioBean.modificarUsuario(analistaDb, analista);
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