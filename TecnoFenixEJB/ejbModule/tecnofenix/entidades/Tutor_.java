package tecnofenix.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-02-21T14:48:38.825-0300")
@StaticMetamodel(Tutor.class)
public class Tutor_ extends Usuario_ {
	public static volatile SingularAttribute<Tutor, Integer> tipo;
	public static volatile SingularAttribute<Tutor, Integer> area;
	public static volatile CollectionAttribute<Tutor, TutorResponsableEvento> tutorResponsableEventoCollection;
}
