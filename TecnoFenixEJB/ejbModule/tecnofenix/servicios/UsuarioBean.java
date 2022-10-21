package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.Usuario;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.UsuarioBeanRemote;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class UsuarioBean implements UsuarioBeanRemote {
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public UsuarioBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Usuario crearUsuario(Usuario usuario) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario modificarUsuario(Usuario usuario) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario borrarUsuario(Usuario usuario) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> obtenerUsuarioPorAtributo(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario login(String usu, String pass) {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usu AND u.contrasenia = :pass",Usuario.class)
				.setParameter("usu", usu).setParameter("pass", pass); 
		
		return (Usuario) query.getResultStream();
	}

}