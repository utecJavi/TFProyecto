package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.GestionEventoAnalista;
import tecnofenix.exception.ServiciosException;

@Remote
public interface GestionEventoAnalistaBeanRemote {
	GestionEventoAnalista crearGestionEventoAnalista(GestionEventoAnalista gestionEventoAnalista) throws ServiciosException;
	GestionEventoAnalista modificarGestionEventoAnalista(GestionEventoAnalista gestionEventoAnalista) throws ServiciosException;
	GestionEventoAnalista borrarGestionEventoAnalista(GestionEventoAnalista gestionEventoAnalista) throws ServiciosException;
	List<GestionEventoAnalista> obtenerGestionEventoAnalistaPorAtributo(GestionEventoAnalista gestionEventoAnalista);
	
}