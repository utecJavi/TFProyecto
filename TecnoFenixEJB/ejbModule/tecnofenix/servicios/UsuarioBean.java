package tecnofenix.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tecnofenix.entidades.Estudiante;
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
//		Usuario usuCreado = new Usuario();
//		System.out.println(usuario.getNombres());
//		System.out.println(usuario.getApellidos());
//		System.out.println(usuario.getDocumento());
//		System.out.println(usuario.getDepartamento());
//		System.out.println(usuario.getMail());
//		System.out.println(usuario.getTelefono());
//		System.out.println(usuario.toString());
		usuario=em.merge(usuario);
		em.flush();
		return usuario;
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
		
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u",Usuario.class); 
		return query.getResultList();
		
	}

	@Override
	public Usuario login(String usuario,String pass) {
//		Query query = em.createNamedQuery("Usuario.findAll");
		TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e WHERE e.usuario = :usu AND e.contrasenia = :pass",Estudiante.class)
				.setParameter("usu", usuario).setParameter("pass", pass);
		for (int i = 0; i < query.getResultList().size(); i++) {
			 System.out.println(query.getResultList().get(0).toString());
		}
		System.out.println(query.getResultList().get(0).getId());
		System.out.println(query.getResultList().get(0).getNombres());
		System.out.println(query.getResultList().get(0).getApellidos());
		System.out.println(query.getResultList().get(0).getDocumento());
		System.out.println(query.getResultList().get(0).getDepartamento());
		System.out.println(query.getResultList().get(0).getMail());
		System.out.println(query.getResultList().get(0).getTelefono());
		System.out.println(query.getResultList().get(0).toString());
		em.flush();
		System.out.println("USUARIOSBEAN LUEGO DE LA QUERY");
		Estudiante estu = new Estudiante();
		estu =query.getResultList().get(0);
//		System.out.println(query.getResultList().toString());
		return estu;
	}

}