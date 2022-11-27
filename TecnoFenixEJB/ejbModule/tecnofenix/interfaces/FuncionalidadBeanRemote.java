package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.Funcionalidad;
import tecnofenix.exception.ServiciosException;

@Remote
public interface FuncionalidadBeanRemote {
	
	Funcionalidad crearFuncionalidad(Funcionalidad fun) throws ServiciosException;
	
	Funcionalidad modificarFuncionalidad(Funcionalidad fun) throws ServiciosException;
	
	Funcionalidad borrarFuncionalidad(Funcionalidad fun) throws ServiciosException;
	
	List<Funcionalidad> obtenerFuncionalidadPorAtributo(Funcionalidad fun);
	Funcionalidad findById(Integer id);
	List<Funcionalidad> listarFuncionalidad()throws ServiciosException;
	List<Funcionalidad> buscarPor(String id, String nombre, String depto);
}