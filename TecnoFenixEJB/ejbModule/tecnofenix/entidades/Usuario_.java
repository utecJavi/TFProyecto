package tecnofenix.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-02-21T14:48:38.833-0300")
@StaticMetamodel(Usuario.class)
public class Usuario_ {
	public static volatile SingularAttribute<Usuario, Integer> id;
	public static volatile SingularAttribute<Usuario, Integer> documento;
	public static volatile SingularAttribute<Usuario, String> usuario;
	public static volatile SingularAttribute<Usuario, String> contrasenia;
	public static volatile SingularAttribute<Usuario, String> apellidos;
	public static volatile SingularAttribute<Usuario, String> nombres;
	public static volatile SingularAttribute<Usuario, Date> fechaNacimiento;
	public static volatile SingularAttribute<Usuario, String> departamento;
	public static volatile SingularAttribute<Usuario, String> genero;
	public static volatile SingularAttribute<Usuario, String> localidad;
	public static volatile SingularAttribute<Usuario, String> mail;
	public static volatile SingularAttribute<Usuario, String> mailPersonal;
	public static volatile SingularAttribute<Usuario, String> telefono;
	public static volatile SingularAttribute<Usuario, Itr> itr;
	public static volatile SingularAttribute<Usuario, String> uTipo;
	public static volatile SingularAttribute<Usuario, Boolean> validado;
	public static volatile SingularAttribute<Usuario, Boolean> activo;
	public static volatile SingularAttribute<Usuario, Rol> rol;
}
