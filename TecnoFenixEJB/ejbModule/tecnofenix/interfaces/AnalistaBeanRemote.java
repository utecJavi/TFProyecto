package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.Analista;
import tecnofenix.exception.ServiciosException;

@Remote
public interface AnalistaBeanRemote {
	Analista crearAnalista(Analista analista) throws ServiciosException;
	Analista modificarAnalista(Analista analista) throws ServiciosException;
	Analista borrarAnalista(Analista analista) throws ServiciosException;
	List<Analista> obtenerAnalistaPorAtributo(Analista analista);
	
}