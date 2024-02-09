package tecnofenix.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2024-02-07T19:07:31.185-0300")
@StaticMetamodel(AccionReclamo.class)
public class AccionReclamo_ {
	public static volatile SingularAttribute<AccionReclamo, Integer> id;
	public static volatile SingularAttribute<AccionReclamo, String> detalle;
	public static volatile SingularAttribute<AccionReclamo, Date> fecha;
	public static volatile SingularAttribute<AccionReclamo, Reclamo> reclamoId;
	public static volatile SingularAttribute<AccionReclamo, Analista> analistaId;
	public static volatile SingularAttribute<AccionReclamo, Boolean> activo;
}
