package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.Tutor;
import tecnofenix.exception.ServiciosException;

@Remote
public interface TutorBeanRemote {
	Tutor crearTutor(Tutor tutor) throws ServiciosException;
	Tutor modificarTutor(Tutor tutor) throws ServiciosException;
	Tutor borrarTutor(Tutor tutor) throws ServiciosException;
	List<Tutor> obtenerTutorPorAtributo(Tutor tutor);
	
}