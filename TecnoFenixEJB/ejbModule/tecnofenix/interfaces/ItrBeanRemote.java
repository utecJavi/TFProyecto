package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.Itr;
import tecnofenix.exception.ServiciosException;

@Remote
public interface ItrBeanRemote {
	Itr crearItr(Itr itr) throws ServiciosException;
	Itr modificarItr(Itr itr) throws ServiciosException;
	Itr borrarItr(Itr itr) throws ServiciosException;
	List<Itr> obtenerItrPorAtributo(Itr itr);
	Itr findById(Integer id);
}