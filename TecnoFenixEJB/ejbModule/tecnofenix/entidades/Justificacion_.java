package tecnofenix.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-02-21T14:48:38.817-0300")
@StaticMetamodel(Justificacion.class)
public class Justificacion_ {
	public static volatile SingularAttribute<Justificacion, Integer> id;
	public static volatile SingularAttribute<Justificacion, String> detalle;
	public static volatile SingularAttribute<Justificacion, Date> fecha;
	public static volatile SingularAttribute<Justificacion, Evento> eventoId;
	public static volatile SingularAttribute<Justificacion, Estudiante> estudianteId;
	public static volatile CollectionAttribute<Justificacion, AccionJustificacion> accionJustificacionCollection;
}
