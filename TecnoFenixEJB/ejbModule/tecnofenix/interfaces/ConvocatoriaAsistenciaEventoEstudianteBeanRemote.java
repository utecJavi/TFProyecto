package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.ConvocatoriaAsistenciaEventoEstudiante;
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Evento;
import tecnofenix.exception.ServiciosException;

@Remote
public interface ConvocatoriaAsistenciaEventoEstudianteBeanRemote {
	
	ConvocatoriaAsistenciaEventoEstudiante crearClase(ConvocatoriaAsistenciaEventoEstudiante conAsisEventEstu) throws ServiciosException;
	
	ConvocatoriaAsistenciaEventoEstudiante modificarClase(ConvocatoriaAsistenciaEventoEstudiante conAsisEventEstu) throws ServiciosException;
	
	ConvocatoriaAsistenciaEventoEstudiante borrarClase(ConvocatoriaAsistenciaEventoEstudiante conAsisEventEstu) throws ServiciosException;
	
	List<ConvocatoriaAsistenciaEventoEstudiante> obtenerTodos() throws ServiciosException;

	List<ConvocatoriaAsistenciaEventoEstudiante> obtenerClasePorAtributo(
			ConvocatoriaAsistenciaEventoEstudiante conAsisEventEstu);
	
	List<ConvocatoriaAsistenciaEventoEstudiante> filtrarAsistEstuAEventosPor(String id, String tituloEvento,String nombre,String apellido ,Boolean asistencia)throws ServiciosException;
	void agregarEstudiante(Estudiante estudiante, Evento evento);
	void registrarAsistencia(Integer id);
	
}
