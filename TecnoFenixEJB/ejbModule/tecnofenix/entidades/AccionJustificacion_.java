package tecnofenix.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2024-07-19T21:03:04.275-0300")
@StaticMetamodel(AccionJustificacion.class)
public class AccionJustificacion_ {
	public static volatile SingularAttribute<AccionJustificacion, Integer> id;
	public static volatile SingularAttribute<AccionJustificacion, String> detalle;
	public static volatile SingularAttribute<AccionJustificacion, Date> fecha;
	public static volatile SingularAttribute<AccionJustificacion, Justificacion> justificacionId;
	public static volatile SingularAttribute<AccionJustificacion, Analista> analistaId;
	public static volatile SingularAttribute<AccionJustificacion, Boolean> activo;
}
