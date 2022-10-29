package tecnofenix.interfaces;

import tecnofenix.entidades.Estudiante;
import tecnofenix.exception.ServiciosException;
import tecnofenix.exception.UsuarioNoEncontradoException;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface EstudianteBeanRemote {
	Estudiante crearEstudiante(Estudiante estudiante) throws ServiciosException;

	Estudiante modificarEstudiantePropio(Estudiante estudiante) throws ServiciosException, UsuarioNoEncontradoException;
	Estudiante modificarEstudianteAdmin(Estudiante estudiante) throws ServiciosException, UsuarioNoEncontradoException;
	Estudiante borrarEstudiante(Estudiante estudiante) throws ServiciosException;
	List<Estudiante> obtenerEstudiantePorAtributo(Estudiante estudiante);
	
}