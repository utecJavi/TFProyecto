package tecnofenix.servicios;

import tecnofenix.entidades.Usuario;
import tecnofenix.exception.ServiciosException;
import tecnofenix.exception.UsuarioNoEncontradoException;
import tecnofenix.interfaces.UsuarioBeanRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class UsuarioBean implements UsuarioBeanRemote {
    @PersistenceContext
    private EntityManager em;

    private ItrBean itrBean;

    /**
     * Default constructor.
     */
    public UsuarioBean() {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TecnoFenixEJB");
//        em = entityManagerFactory.createEntityManager();
        itrBean = new ItrBean();
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) throws ServiciosException {
//		if(usuario.getIdItr().getId() == null) {
//			Itr itr =usuario.getIdItr();
//			itr=itrBean.crearItr(itr);
//			usuario.setIdItr(itr);
//		}
    	System.out.println("COSITAS PA");
        System.out.println("HERNAN USUARIOOOO 2: " + usuario);
        System.out.println(itrBean);
        usuario.setItr(itrBean.findById(usuario.getItr().getId()));
        System.out.println("TEST1");
        em.persist(usuario);
        System.out.println("TEST2");
        em.flush();
        System.out.println("TEST3");
        return usuario;
    }

    @Override
    public Usuario modificarUsuario(Usuario usuarioDb, Usuario usuario) throws ServiciosException, UsuarioNoEncontradoException {
        usuarioDb.setDocumento(usuario.getDocumento());
        usuarioDb.setApellidos(usuario.getApellidos());
        usuarioDb.setNombres(usuario.getNombres());
        usuarioDb.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioDb.setMail(usuario.getMail());
        usuarioDb.setTelefono(usuario.getTelefono());

        em.merge(usuarioDb);
        em.flush();

        return usuarioDb;
    }

    @Override
    public Usuario borrarUsuario(Usuario usuario) throws ServiciosException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Usuario> obtenerUsuarioPorAtributo(Usuario usuario) {
    	//TODO:EL METODO NO ESTA RECIBIENDO NINGUN PARAMETRO
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        return query.getResultList();

    }

    @Override
    public Usuario login(String usuario, String pass) {


        TypedQuery<Usuario> query = em.createQuery("SELECT e FROM Usuario e WHERE e.usuario = :usu AND e.contrasenia = :pass", Usuario.class)
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
//		Estudiante estu = new Estudiante();
//		estu =query.getResultList().get(0);
//		System.out.println(query.getResultList().toString());
        return query.getResultList().get(0);
    }

    @Override


    public Usuario encontrarUsuario(Integer id) throws UsuarioNoEncontradoException {
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findById", Usuario.class);
        Usuario usuario = query.setParameter("id", id).getSingleResult();

        if (usuario == null) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado.");
        }

        return usuario;
    }

	@Override
	public List<Usuario> listarUsuariosGeneral() throws UsuarioNoEncontradoException {
		 TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findAll", Usuario.class);
		 List<Usuario> usuarios = query.getResultList();

	        if (usuarios == null) {
	            throw new UsuarioNoEncontradoException("Usuarios no encontrados.");
	        }

	        return usuarios;
	}

}