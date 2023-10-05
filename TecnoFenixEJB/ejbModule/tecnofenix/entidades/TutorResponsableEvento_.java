package tecnofenix.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-10-04T15:28:39.071-0300")
@StaticMetamodel(TutorResponsableEvento.class)
public class TutorResponsableEvento_ {
	public static volatile SingularAttribute<TutorResponsableEvento, Integer> id;
	public static volatile SingularAttribute<TutorResponsableEvento, Tutor> tutorId;
	public static volatile SingularAttribute<TutorResponsableEvento, Evento> eventoId;
}
