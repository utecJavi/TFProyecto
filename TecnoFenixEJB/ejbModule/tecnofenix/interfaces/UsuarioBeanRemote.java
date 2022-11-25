package tecnofenix.interfaces;

import tecnofenix.entidades.Usuario;
import tecnofenix.exception.ServiciosException;
import tecnofenix.exception.UsuarioNoEncontradoException;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface UsuarioBeanRemote {
	Usuario crearUsuario(Usuario usuario) throws ServiciosException;

	Usuario modificarUsuario(Usuario usuario) throws ServiciosException, UsuarioNoEncontradoException;

	Usuario borrarUsuario(Usuario usuario) throws ServiciosException;
	Usuario login(String usuario,String pass) throws ServiciosException;
	Usuario encontrarUsuario(Integer id) throws UsuarioNoEncontradoException;
	Usuario validarUsuario(Usuario usuario) throws UsuarioNoEncontradoException;
	List<Usuario> obtenerUsuarioPorAtributo(Usuario usuario);
	List<Usuario> listarUsuariosGeneral() throws UsuarioNoEncontradoException;
	List<Usuario> buscarUsuarioPor(String tipo, String id ,String depto,String doc,String nombre,String apellido
			,String mail,String usuario,String itrNombre,String generacion, Boolean validado ,Boolean activo,Boolean todos,String localidad,String telefono,Boolean noValidados ,Boolean noActivos) throws UsuarioNoEncontradoException;
	
}