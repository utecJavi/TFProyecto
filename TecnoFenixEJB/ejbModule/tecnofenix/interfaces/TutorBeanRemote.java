package tecnofenix.interfaces;

import tecnofenix.entidades.Tutor;
import tecnofenix.exception.ServiciosException;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface TutorBeanRemote {
	Tutor crearTutor(Tutor tutor) throws ServiciosException;
	Tutor modificarTutor(Tutor tutorDb, Tutor tutor) throws ServiciosException;
	Tutor modificarTutorPropio(Tutor tutor) throws ServiciosException;
	Tutor modificarTutorAdmin(Tutor tutor) throws ServiciosException;
	Tutor borrarTutor(Tutor tutor) throws ServiciosException;
	List<Tutor> obtenerTutorPorAtributo(Tutor tutor);
	
}