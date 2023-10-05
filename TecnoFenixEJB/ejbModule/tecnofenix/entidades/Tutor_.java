package tecnofenix.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-10-04T15:28:47.627-0300")
@StaticMetamodel(Tutor.class)
public class Tutor_ extends Usuario_ {
	public static volatile SingularAttribute<Tutor, TipoTutorTipo> tipo;
	public static volatile SingularAttribute<Tutor, TipoArea> area;
	public static volatile CollectionAttribute<Tutor, TutorResponsableEvento> tutorResponsableEventoCollection;
}
