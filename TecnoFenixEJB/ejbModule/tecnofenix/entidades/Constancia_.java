package tecnofenix.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tecnofenix.entidades.Constancia.EstadoConstancia;

@Generated(value="Dali", date="2023-02-21T14:48:38.802-0300")
@StaticMetamodel(Constancia.class)
public class Constancia_ {
	public static volatile SingularAttribute<Constancia, Integer> id;
	public static volatile SingularAttribute<Constancia, String> detalle;
	public static volatile SingularAttribute<Constancia, Date> fecha;
	public static volatile CollectionAttribute<Constancia, AccionConstancia> accionConstanciaCollection;
	public static volatile SingularAttribute<Constancia, Evento> eventoId;
	public static volatile SingularAttribute<Constancia, Estudiante> estudianteId;
	public static volatile SingularAttribute<Constancia, EstadoConstancia> estado;
	public static volatile SingularAttribute<Constancia, TipoConstancia> tipoConstancia;
}
