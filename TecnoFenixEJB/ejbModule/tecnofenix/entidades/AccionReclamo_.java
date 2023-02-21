package tecnofenix.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-02-21T14:48:38.796-0300")
@StaticMetamodel(AccionReclamo.class)
public class AccionReclamo_ {
	public static volatile SingularAttribute<AccionReclamo, Integer> id;
	public static volatile SingularAttribute<AccionReclamo, String> detalle;
	public static volatile SingularAttribute<AccionReclamo, Date> fecha;
	public static volatile SingularAttribute<AccionReclamo, Reclamo> reclamoId;
	public static volatile SingularAttribute<AccionReclamo, Analista> analistaId;
}
