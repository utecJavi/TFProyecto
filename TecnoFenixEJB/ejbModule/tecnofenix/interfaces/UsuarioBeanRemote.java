package tecnofenix.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tecnofenix.entidades.Usuario;
import tecnofenix.exception.ServiciosException;

@Remote
public interface UsuarioBeanRemote {
	Usuario crearUsuario(Usuario usuario) throws ServiciosException;
	Usuario modificarUsuario(Usuario usuario) throws ServiciosException;
	Usuario borrarUsuario(Usuario usuario) throws ServiciosException;
	List<Usuario> obtenerUsuarioPorAtributo(Usuario usuario);
	public Usuario login(String usu, String pass);
	
}