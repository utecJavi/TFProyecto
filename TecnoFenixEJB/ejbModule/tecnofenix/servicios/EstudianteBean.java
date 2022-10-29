package tecnofenix.servicios;

import tecnofenix.entidades.Estudiante;
import tecnofenix.exception.ServiciosException;
import tecnofenix.exception.UsuarioNoEncontradoException;
import tecnofenix.interfaces.EstudianteBeanRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


/**
 * Session Bean implementation class CarrerasBean
 */
@Stateless
public class EstudianteBean implements EstudianteBeanRemote {
    //	@PersistenceContext
    private EntityManager em;

    private UsuarioBean usuarioBean;

    /**
     * Default constructor.
     */
    public EstudianteBean() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TecnoFenixEJB");
        em = entityManagerFactory.createEntityManager();
        usuarioBean = new UsuarioBean();
    }

    @Override
    public Estudiante crearEstudiante(Estudiante estudiante) throws ServiciosException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Estudiante modificarEstudiante(Estudiante estudianteDb, Estudiante estudiante) throws ServiciosException, UsuarioNoEncontradoException {
        estudianteDb.setDocumento(estudiante.getDocumento());
        estudianteDb.setApellidos(estudiante.getApellidos());
        estudianteDb.setNombres(estudiante.getNombres());
        estudianteDb.setFechaNacimiento(estudiante.getFechaNacimiento());
        estudianteDb.setMail(estudiante.getMail());
        estudianteDb.setTelefono(estudiante.getTelefono());

        em.merge(estudianteDb);
        em.flush();

        return estudianteDb;
    }

    @Override
    public Estudiante modificarEstudiantePropio(Estudiante estudiante) throws ServiciosException, UsuarioNoEncontradoException {
        if (estudiante.getId() == null) {
            throw new UsuarioNoEncontradoException("Ha ocurrido un error al modificar el usuario.");
        }

        Estudiante estudianteDb = (Estudiante) usuarioBean.encontrarUsuario(estudiante.getId());

        estudianteDb.setContrasenia(estudiante.getContrasenia());
        estudianteDb.setGeneracion(estudiante.getGeneracion());

        return modificarEstudiante(estudianteDb, estudiante);
    }

    @Override
    public Estudiante modificarEstudianteAdmin(Estudiante estudiante) throws ServiciosException, UsuarioNoEncontradoException {
        if (estudiante.getId() == null) {
            throw new UsuarioNoEncontradoException("Ha ocurrido un error al modificar el usuario.");
        }

        Estudiante estudianteDb = (Estudiante) usuarioBean.encontrarUsuario(estudiante.getId());
        estudianteDb.setGeneracion(estudiante.getGeneracion());

        // TODO: RF001-03 faltan atributos de estado validado o no de usuario, aceptacion de su solicitud y modificar el tipo de usuario
        return modificarEstudiante(estudianteDb, estudiante);
    }

    @Override
    public Estudiante borrarEstudiante(Estudiante estudiante) throws ServiciosException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Estudiante> obtenerEstudiantePorAtributo(Estudiante estudiante) {
        // TODO Auto-generated method stub
        return null;
    }

}