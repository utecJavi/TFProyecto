package tecnofenix.servicios;

import tecnofenix.entidades.Usuario;
import tecnofenix.exception.ServiciosException;
import tecnofenix.exception.UsuarioNoEncontradoException;
import tecnofenix.interfaces.UsuarioBeanRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class UsuarioBean implements UsuarioBeanRemote {
//    @PersistenceContext
    private EntityManager em;

    private ItrBean itrBean;

    /**
     * Default constructor.
     */
    public UsuarioBean() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TecnoFenixEJB");
        em = entityManagerFactory.createEntityManager();
        itrBean = new ItrBean();
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) throws ServiciosException {
//		if(usuario.getIdItr().getId() == null) {
//			Itr itr =usuario.getIdItr();
//			itr=itrBean.crearItr(itr);
//			usuario.setIdItr(itr);
//		}
        System.out.println("HERNAN USUARIOOOO: " + em);
        System.out.println(usuario.getItr());
        usuario.setItr(itrBean.findById(usuario.getItr().getId()));
        System.out.println("TEST1");
        em.persist(usuario);
        System.out.println("TEST2");
        em.flush();
        System.out.println("TEST3");
        return usuario;
    }


    @Override
    public int printTest() {
        System.out.println("COSITAS!!!!");
        return 4;
    }

    @Override
    public Usuario modificarUsuario(Usuario usuario) throws ServiciosException, UsuarioNoEncontradoException {
        if (usuario.getId() == null) {
            throw new UsuarioNoEncontradoException("Ha ocurrido un error al modificar el usuario.");
        }

        Usuario usuarioDb = encontrarUsuario(usuario.getId());
        usuarioDb.setDocumento(usuario.getDocumento());
        usuarioDb.setContrasenia(usuario.getContrasenia());
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
        System.out.println("ENCONTRANDO USUARIO ID: " + id);
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findById", Usuario.class);
        Usuario usuario = query.setParameter("id", id).getSingleResult();

        System.out.println("SIGUIO HASTA ACA!");

        if (usuario == null) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado.");
        }

        System.out.println("USUARIO ENCONTRADO: " + usuario.getNombres());

        return usuario;
    }

}