package tecnofenix.servicios;

import tecnofenix.entidades.Usuario;
import tecnofenix.exception.ItrNoEncontradoException;
import tecnofenix.exception.ServiciosException;
import tecnofenix.exception.UsuarioNoEncontradoException;
import tecnofenix.interfaces.UsuarioBeanRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
		usuario.setItr(itrBean.findById2(usuario.getItr().getId(), em));
		System.out.println("TEST1");
		em.merge(usuario);
		System.out.println("TEST2");
		em.flush();
		System.out.println("TEST3");
		return usuario;
	}

	@Override
	public Usuario modificarUsuario( Usuario usuario)
			throws ServiciosException, UsuarioNoEncontradoException {
//		usuarioDb.setDocumento(usuario.getDocumento());
//		usuarioDb.setApellidos(usuario.getApellidos());
//		usuarioDb.setNombres(usuario.getNombres());
//		usuarioDb.setFechaNacimiento(usuario.getFechaNacimiento());
//		usuarioDb.setMail(usuario.getMail());
//		usuarioDb.setTelefono(usuario.getTelefono());

		em.merge(usuario);
		em.flush();

		return usuario;
	}

	@Override
	public Usuario borrarUsuario(Usuario usuario) throws ServiciosException {
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.logicalDelete", Usuario.class);
		query.setParameter("id", usuario.getId()).executeUpdate();
		em.getTransaction().commit();
		Usuario usr=encontrarUsuario(usuario.getId());
		return usr;
	}

	@Override
	public List<Usuario> obtenerUsuarioPorAtributo(Usuario usuario) {
		// TODO:EL METODO NO ESTA RECIBIENDO NINGUN PARAMETRO
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
		return query.getResultList();

	}

	@Override
	public Usuario login(String usuario, String pass) {

		TypedQuery<Usuario> query = em
				.createQuery("SELECT e FROM Usuario e WHERE e.usuario = :usu AND e.contrasenia = :pass", Usuario.class)
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

	@Override
	public List<Usuario> buscarUsuarioPor(String tipo, String id, String depto, String doc, String nombre,
			String apellido, String mail, String usuario, String itrNombre, String generacion, Boolean validado,
			Boolean activo, Boolean todos,String localidad,String telefono, Boolean noValidados ,Boolean noActivos) throws UsuarioNoEncontradoException {

		String conditions = "";
		String joinJPQL = "";
		if (id != null && id != "") {
			conditions = conditions + " AND u.id = " + id;
		}
		if (nombre != null && nombre != "") {

			conditions = conditions + " AND u.nombre LIKE '" + nombre + "'";

		}
		if (depto != null && depto != "") {

			conditions = conditions + " AND u.departamento LIKE '" + depto + "'";

		}
		if (telefono != null && telefono != "") {

			conditions = conditions + " AND u.telefono LIKE '" + telefono + "'";

		}
		
		if (localidad != null && localidad != "") {

			conditions = conditions + " AND u.localidad LIKE '" + localidad + "'";

		}

		if (doc != null && doc != "") {

			conditions = conditions + " AND u.documento LIKE '" + doc + "'";

		}

		if (apellido != null && apellido != "") {

			conditions = conditions + " AND u.apellidos LIKE '" + apellido + "'";

		}

		if (mail != null && mail != "") {

			conditions = conditions + " AND u.mail LIKE '" + mail + "'";

		}

		if (!todos) {
			
			if(validado) {
			conditions = conditions + " AND u.validado = " + validado;
			}
			if(noValidados) {
				conditions = conditions + " AND u.validado = " + !noValidados;
			}
			if(activo) {
			conditions = conditions + " AND u.activo = " + activo;
			}
			if(noActivos) {
			conditions = conditions + " AND u.activo = " + !noActivos;
			}
			
		}
		
		
		if (usuario != null && usuario != "") {

			conditions = conditions + " AND u.usuario LIKE '" + usuario + "'";

		}

		if (itrNombre != null && itrNombre != "") {

			joinJPQL = " INNER JOIN u.itr i ";
			conditions = conditions + " AND i.nombre LIKE '" + itrNombre + "'";

		}

		if (tipo != null && tipo != "") {

			conditions = conditions + " AND u.uTipo LIKE '" + tipo + "'";

			if (tipo == "ESTUDIANTE") {
				if (generacion != null && generacion != "") {

					conditions = conditions + " AND u.generacion = " + generacion;

				}
			}
		}
		String jpql = "SELECT u FROM Usuario u " + joinJPQL + " WHERE 1=1 " + conditions;
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		System.out.println(jpql);
		List<Usuario> list = query.getResultList();
		if (list == null) {
			throw new ItrNoEncontradoException("Usuario no encontrado.");
		}

		return list;

	}

	@Override
	public Usuario validarUsuario(Usuario usuario) throws UsuarioNoEncontradoException {
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.validarUsuario", Usuario.class);
		Usuario usr = query.setParameter("id", usuario.getId()).getSingleResult();

		return null;
	}

}