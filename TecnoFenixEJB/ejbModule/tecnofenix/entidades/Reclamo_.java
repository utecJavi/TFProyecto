package tecnofenix.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-10-02T14:49:41.621-0300")
@StaticMetamodel(Reclamo.class)
public class Reclamo_ {
	public static volatile SingularAttribute<Reclamo, Integer> id;
	public static volatile SingularAttribute<Reclamo, String> titulo;
	public static volatile SingularAttribute<Reclamo, String> detalle;
	public static volatile SingularAttribute<Reclamo, Date> fecha;
	public static volatile CollectionAttribute<Reclamo, AccionReclamo> accionReclamoCollection;
	public static volatile SingularAttribute<Reclamo, Evento> eventoId;
	public static volatile SingularAttribute<Reclamo, Estudiante> estudianteId;
	public static volatile SingularAttribute<Reclamo, Integer> creditos;
	public static volatile SingularAttribute<Reclamo, Integer> semestre;
}
