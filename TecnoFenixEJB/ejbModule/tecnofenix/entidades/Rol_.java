package tecnofenix.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-02-21T14:48:38.820-0300")
@StaticMetamodel(Rol.class)
public class Rol_ {
	public static volatile SingularAttribute<Rol, Long> id;
	public static volatile SingularAttribute<Rol, String> nombre;
	public static volatile SingularAttribute<Rol, String> descripcion;
	public static volatile SingularAttribute<Rol, Boolean> activo;
	public static volatile CollectionAttribute<Rol, Funcionalidad> funcionalidades;
}
