package tecnofenix.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-02-21T14:48:38.806-0300")
@StaticMetamodel(Estudiante.class)
public class Estudiante_ extends Usuario_ {
	public static volatile SingularAttribute<Estudiante, Integer> generacion;
	public static volatile CollectionAttribute<Estudiante, Justificacion> justificacionCollection;
	public static volatile CollectionAttribute<Estudiante, ConvocatoriaAsistenciaEventoEstudiante> convocatoriaAsistenciaEventoEstudianteCollection;
	public static volatile CollectionAttribute<Estudiante, Reclamo> reclamoCollection;
	public static volatile CollectionAttribute<Estudiante, Constancia> constanciaCollection;
}
