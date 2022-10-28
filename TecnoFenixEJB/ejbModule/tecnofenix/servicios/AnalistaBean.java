package tecnofenix.servicios;

import tecnofenix.entidades.Analista;
import tecnofenix.exception.ServiciosException;
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
	public Analista modificarAnalista(Analista analista) throws ServiciosException {
		System.out.println("ANALISTA MODIFICADO 1 !");
		Analista analistaDb = (Analista) usuarioBean.encontrarUsuario(analista.getId());
		System.out.println("ANALISTA MODIFICADO 2 !");
		analistaDb.setDocumento(analista.getDocumento());
		analistaDb.setContrasenia(analista.getContrasenia());
		analistaDb.setApellidos(analista.getApellidos());
		analistaDb.setNombres(analista.getNombres());
		analistaDb.setFechaNacimiento(analista.getFechaNacimiento());
		analistaDb.setMail(analista.getMail());
		analistaDb.setTelefono(analista.getTelefono());

		System.out.println("ANALISTA MODIFICADO 3 !");

		em.merge(analistaDb);
		em.flush();

		System.out.println("ANALISTA MODIFICADO 4 !");

		return analistaDb;
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