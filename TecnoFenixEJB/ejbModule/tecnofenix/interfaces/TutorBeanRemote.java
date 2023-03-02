package tecnofenix.interfaces;


import tecnofenix.entidades.TipoArea;
import tecnofenix.entidades.TipoTutorTipo;
import tecnofenix.entidades.Tutor;
import tecnofenix.exception.ServiciosException;
import tecnofenix.exception.UsuarioNoEncontradoException;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface TutorBeanRemote {
	Tutor crearTutor(Tutor tutor) throws ServiciosException;
	Tutor modificarTutorPropio(Tutor tutor) throws ServiciosException, UsuarioNoEncontradoException;
	Tutor modificarTutorAdmin(Tutor tutor) throws ServiciosException, UsuarioNoEncontradoException;
	Tutor borrarTutor(Tutor tutor) throws ServiciosException;
	List<Tutor> obtenerTutorPorAtributo(Tutor tutor);
	Tutor obtenerTutorPorId(Integer tutorId);
	List<Tutor> listarTutores() throws ServiciosException;
	
    TipoTutorTipo crearTipoTutorTipo(TipoTutorTipo tipoConstancia) throws ServiciosException;
	
	TipoTutorTipo modificarTipoTutorTipo(TipoTutorTipo tipoConstancia) throws ServiciosException;
	
	void bajaTipoTutorTipo(TipoTutorTipo tipoConstancia) throws ServiciosException;
	
	List<TipoTutorTipo> listadoTipoTutorTipo() throws ServiciosException;
	
	List<TipoTutorTipo> buscarTipoTutorTipoPor(String id, String nombre) throws ServiciosException;

	TipoArea crearTipoArea(TipoArea tipoConstancia) throws ServiciosException;
	
	TipoArea modificarTipoArea(TipoArea tipoConstancia) throws ServiciosException;
	
	void bajaTipoArea(TipoArea tipoConstancia) throws ServiciosException;
	
	List<TipoArea> listadoTipoArea() throws ServiciosException;
	
	List<TipoArea> buscarTipoAreaPor(String id, String nombre) throws ServiciosException;
}