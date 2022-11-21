package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.Rol;
import tecnofenix.exception.ServiciosException;


@Remote
public interface RolBeanRemote {
	Rol crearRol(Rol rol) throws ServiciosException;

	Rol modificarRol(Rol rol) throws ServiciosException;

	Rol borrarRol(Rol rol) throws ServiciosException;
	Rol findById(Integer id);
	List<Rol> listarRoles()throws ServiciosException;
}
