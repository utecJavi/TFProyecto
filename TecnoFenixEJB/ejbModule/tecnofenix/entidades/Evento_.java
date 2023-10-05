package tecnofenix.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-10-02T14:49:41.611-0300")
@StaticMetamodel(Evento.class)
public class Evento_ {
	public static volatile SingularAttribute<Evento, Integer> id;
	public static volatile SingularAttribute<Evento, String> titulo;
	public static volatile SingularAttribute<Evento, TipoEvento> tipo;
	public static volatile SingularAttribute<Evento, ModalidadEvento> modalidad;
	public static volatile SingularAttribute<Evento, Date> inicio;
	public static volatile SingularAttribute<Evento, Date> fin;
	public static volatile SingularAttribute<Evento, String> localizacion;
	public static volatile SingularAttribute<Evento, Boolean> bajaLogica;
	public static volatile SingularAttribute<Evento, Itr> itr;
	public static volatile SingularAttribute<Evento, TipoEstadoEvento> tipoEstadoEvento;
	public static volatile CollectionAttribute<Evento, Justificacion> justificacionCollection;
	public static volatile CollectionAttribute<Evento, ConvocatoriaAsistenciaEventoEstudiante> convocatoriaAsistenciaEventoEstudianteCollection;
	public static volatile CollectionAttribute<Evento, Reclamo> reclamoCollection;
	public static volatile CollectionAttribute<Evento, Constancia> constanciaCollection;
	public static volatile CollectionAttribute<Evento, TutorResponsableEvento> tutorResponsableEventoCollection;
	public static volatile CollectionAttribute<Evento, GestionEventoAnalista> gestionEventoAnalistaCollection;
}
