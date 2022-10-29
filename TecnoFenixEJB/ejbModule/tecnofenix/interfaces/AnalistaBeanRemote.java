package tecnofenix.interfaces;

import tecnofenix.entidades.Analista;
import tecnofenix.exception.ServiciosException;
import tecnofenix.exception.UsuarioNoEncontradoException;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface AnalistaBeanRemote {
	Analista crearAnalista(Analista analista) throws ServiciosException;
	Analista modificarAnalistaPropio(Analista analista) throws ServiciosException, UsuarioNoEncontradoException;
	Analista modificarAnalistaAdmin(Analista analista) throws ServiciosException, UsuarioNoEncontradoException;
	Analista borrarAnalista(Analista analista) throws ServiciosException;
	List<Analista> obtenerAnalistaPorAtributo(Analista analista);
	
}