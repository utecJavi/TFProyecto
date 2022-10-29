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
	public Analista modificarAnalista(Analista analistaDb, Analista analista) throws ServiciosException {
		analistaDb.setDocumento(analista.getDocumento());
		analistaDb.setApellidos(analista.getApellidos());
		analistaDb.setNombres(analista.getNombres());
		analistaDb.setFechaNacimiento(analista.getFechaNacimiento());
		analistaDb.setMail(analista.getMail());
		analistaDb.setTelefono(analista.getTelefono());

		em.merge(analistaDb);
		em.flush();

		return analistaDb;
	}

	@Override
	public Analista modificarAnalistaPropio(Analista analista) throws ServiciosException {
		if (analista.getId() == null) {
			throw new UsuarioNoEncontradoException("Ha ocurrido un error al modificar el usuario.");
		}

		Analista analistaDb = (Analista) usuarioBean.encontrarUsuario(analista.getId());

		analistaDb.setContrasenia(analista.getContrasenia());

		return modificarAnalista(analistaDb, analista);
	}

	@Override
	public Analista modificarAnalistaAdmin(Analista analista) throws ServiciosException {
		if (analista.getId() == null) {
			throw new UsuarioNoEncontradoException("Ha ocurrido un error al modificar el usuario.");
		}

		Analista analistaDb = (Analista) usuarioBean.encontrarUsuario(analista.getId());

		return modificarAnalista(analistaDb, analista);
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