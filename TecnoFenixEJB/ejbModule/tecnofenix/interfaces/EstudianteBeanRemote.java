package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.Constancia;
import tecnofenix.entidades.ConvocatoriaAsistenciaEventoEstudiante;
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Justificacion;
import tecnofenix.entidades.Reclamo;
import tecnofenix.exception.ServiciosException;

@Remote
public interface EstudianteBeanRemote {
	Estudiante crearEstudiante(Estudiante estudiante) throws ServiciosException;
	Estudiante modificarEstudiante(Estudiante estudiante) throws ServiciosException;
	Estudiante borrarEstudiante(Estudiante estudiante) throws ServiciosException;
	Estudiante obtenerEstudiantePorAtributo(String documento)throws ServiciosException;
	List<Estudiante> listarAsistenciasAEventos(Integer eventoId)throws ServiciosException;
	List<Estudiante> listarJustificaciones(Integer justificacionId)throws ServiciosException;
	List<Estudiante> lisatEstudiantePorReclamos(Integer reclamoId)throws ServiciosException;
	List<Estudiante> listarConstancias(Integer constanciaId)throws ServiciosException;
}