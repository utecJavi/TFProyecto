package tecnofenix.interfaces;

import tecnofenix.entidades.Analista;
import tecnofenix.exception.ServiciosException;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface AnalistaBeanRemote {
	Analista crearAnalista(Analista analista) throws ServiciosException;
	Analista modificarAnalista(Analista analistaDb, Analista analista) throws ServiciosException;
	Analista modificarAnalistaPropio(Analista analista) throws ServiciosException;
	Analista modificarAnalistaAdmin(Analista analista) throws ServiciosException;
	Analista borrarAnalista(Analista analista) throws ServiciosException;
	List<Analista> obtenerAnalistaPorAtributo(Analista analista);
	
}