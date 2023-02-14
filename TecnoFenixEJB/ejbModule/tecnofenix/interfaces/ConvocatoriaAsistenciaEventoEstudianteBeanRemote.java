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
	ConvocatoriaAsistenciaEventoEstudiante obtenerDatosConvPorId(Integer id)throws ServiciosException;
	List<ConvocatoriaAsistenciaEventoEstudiante> filtrarAsistEstuAEventosPor(String id, String tituloEvento,
			String nombre, String apellido,String documento ,String valorLogico,String calificacion,Boolean asistencia) throws ServiciosException;
	ConvocatoriaAsistenciaEventoEstudiante agregarEstudianteAEvento(ConvocatoriaAsistenciaEventoEstudiante convAsistEventEstu);
	void registrarAsistencia(Integer id);
	
}
