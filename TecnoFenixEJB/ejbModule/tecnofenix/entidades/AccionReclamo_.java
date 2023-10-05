package tecnofenix.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-10-02T14:49:41.598-0300")
@StaticMetamodel(AccionReclamo.class)
public class AccionReclamo_ {
	public static volatile SingularAttribute<AccionReclamo, Integer> id;
	public static volatile SingularAttribute<AccionReclamo, String> detalle;
	public static volatile SingularAttribute<AccionReclamo, Date> fecha;
	public static volatile SingularAttribute<AccionReclamo, Reclamo> reclamoId;
	public static volatile SingularAttribute<AccionReclamo, Analista> analistaId;
}
