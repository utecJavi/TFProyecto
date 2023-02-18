package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.Constancia;
import tecnofenix.exception.ServiciosException;

@Remote
public interface ConstanciaBeanRemote {
	
	Constancia buscarConstancia(Integer idConstancia) throws ServiciosException;
	
	Constancia crearConstancia(Constancia constancia) throws ServiciosException;
	
	Constancia modificarConstancia(Constancia constancia) throws ServiciosException;
	
	void borrarConstancia(Constancia constancia) throws ServiciosException;
	
	List<Constancia> obtenerConstanciaPorAtributo(Constancia constancia);
	
	List<Constancia> listadoConstancias(String usuario, String estado) throws ServiciosException;
	
	TipoConstancia crearTipoConstancia(TipoConstancia tipoConstancia) throws ServiciosException;
	
	TipoConstancia modificarTipoConstancia(TipoConstancia tipoConstancia) throws ServiciosException;
	
	void bajaTipoConstancia(TipoConstancia tipoConstancia) throws ServiciosException;
	
	List<TipoConstancia> listadoTipoConstancia(Boolean baja) throws ServiciosException;
	
}