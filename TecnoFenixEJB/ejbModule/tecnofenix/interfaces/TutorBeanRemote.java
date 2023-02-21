package tecnofenix.interfaces;

import tecnofenix.entidades.Tutor;
import tecnofenix.exception.ServiciosException;
import tecnofenix.exception.UsuarioNoEncontradoException;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface TutorBeanRemote {
	Tutor crearTutor(Tutor tutor) throws ServiciosException;
	Tutor modificarTutorPropio(Tutor tutor) throws ServiciosException, UsuarioNoEncontradoException;
	Tutor modificarTutorAdmin(Tutor tutor) throws ServiciosException, UsuarioNoEncontradoException;
	Tutor borrarTutor(Tutor tutor) throws ServiciosException;
	List<Tutor> obtenerTutorPorAtributo(Tutor tutor);
	Tutor obtenerTutorPorId(Integer tutorId);
	List<Tutor> listarTutores() throws ServiciosException;
	
}