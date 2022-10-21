package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.Estudiante;
import tecnofenix.exception.ServiciosException;

@Remote
public interface EstudianteBeanRemote {
	Estudiante crearEstudiante(Estudiante estudiante) throws ServiciosException;
	Estudiante modificarEstudiante(Estudiante estudiante) throws ServiciosException;
	Estudiante borrarEstudiante(Estudiante estudiante) throws ServiciosException;
	List<Estudiante> obtenerEstudiantePorAtributo(Estudiante estudiante);
	
}