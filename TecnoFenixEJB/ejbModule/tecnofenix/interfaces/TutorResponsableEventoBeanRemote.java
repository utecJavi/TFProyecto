package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.TutorResponsableEvento;
import tecnofenix.exception.ServiciosException;

@Remote
public interface TutorResponsableEventoBeanRemote {
	TutorResponsableEvento crearTutorResponsableEvento(TutorResponsableEvento tutorRespEve) throws ServiciosException;
	TutorResponsableEvento modificarTutorResponsableEvento(TutorResponsableEvento tutorRespEve) throws ServiciosException;
	TutorResponsableEvento borrarTutorResponsableEvento(TutorResponsableEvento tutorRespEve) throws ServiciosException;
	List<TutorResponsableEvento> obtenerTutorResponsableEventoPorAtributo(TutorResponsableEvento tutorRespEve);
	List<TutorResponsableEvento> obtenerTutorResponsableEventoPorId(Integer id)throws ServiciosException;
}