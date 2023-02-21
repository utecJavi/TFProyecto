package tecnofenix.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-02-21T14:48:38.748-0300")
@StaticMetamodel(AccionConstancia.class)
public class AccionConstancia_ {
	public static volatile SingularAttribute<AccionConstancia, Integer> id;
	public static volatile SingularAttribute<AccionConstancia, String> detalle;
	public static volatile SingularAttribute<AccionConstancia, Date> fecha;
	public static volatile SingularAttribute<AccionConstancia, Constancia> constanciaId;
	public static volatile SingularAttribute<AccionConstancia, Analista> analistaId;
}
