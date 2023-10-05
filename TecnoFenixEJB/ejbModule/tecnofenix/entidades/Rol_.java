package tecnofenix.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-10-02T14:49:41.624-0300")
@StaticMetamodel(Rol.class)
public class Rol_ {
	public static volatile SingularAttribute<Rol, Integer> id;
	public static volatile SingularAttribute<Rol, String> nombre;
	public static volatile SingularAttribute<Rol, String> descripcion;
	public static volatile SingularAttribute<Rol, Boolean> activo;
	public static volatile ListAttribute<Rol, Funcionalidad> funcionalidades;
}
