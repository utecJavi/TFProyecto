package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.EscolaridadDTO;
import tecnofenix.exception.ServiciosException;
import tecnofenix.exception.UsuarioNoEncontradoException;


@Remote
public interface EstudianteBeanRemote {
	Estudiante crearEstudiante(Estudiante estudiante) throws ServiciosException;

	Estudiante modificarEstudiantePropio(Estudiante estudiante) throws ServiciosException, UsuarioNoEncontradoException;
	Estudiante modificarEstudianteAdmin(Estudiante estudiante) throws ServiciosException, UsuarioNoEncontradoException;
	Estudiante borrarEstudiante(Estudiante estudiante) throws ServiciosException;
	Estudiante obtenerEstudiantePorAtributo(String documento)throws ServiciosException;
	Estudiante buscarEstudiantePorId(Integer id)throws ServiciosException;
	List<Estudiante> listarEstudiantes() throws ServiciosException;
	List<Estudiante> listarAsistenciasAEventos(Integer eventoId)throws ServiciosException;
	List<Estudiante> listarJustificaciones(Integer justificacionId)throws ServiciosException;
	List<Estudiante> lisatEstudiantePorReclamos(Integer reclamoId)throws ServiciosException;
	List<Estudiante> listarConstancias(Integer constanciaId) throws ServiciosException;
	List<EscolaridadDTO> obtenerEscolaridad(Integer idEstudiante) throws ServiciosException;
	
}