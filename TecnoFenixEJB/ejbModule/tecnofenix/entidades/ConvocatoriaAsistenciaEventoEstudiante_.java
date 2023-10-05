package tecnofenix.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-10-02T14:49:41.607-0300")
@StaticMetamodel(ConvocatoriaAsistenciaEventoEstudiante.class)
public class ConvocatoriaAsistenciaEventoEstudiante_ {
	public static volatile SingularAttribute<ConvocatoriaAsistenciaEventoEstudiante, Integer> id;
	public static volatile SingularAttribute<ConvocatoriaAsistenciaEventoEstudiante, Integer> calificacion;
	public static volatile SingularAttribute<ConvocatoriaAsistenciaEventoEstudiante, Boolean> asistencia;
	public static volatile SingularAttribute<ConvocatoriaAsistenciaEventoEstudiante, RegistroAsistencia> registroAsistencia;
	public static volatile SingularAttribute<ConvocatoriaAsistenciaEventoEstudiante, Evento> eventoId;
	public static volatile SingularAttribute<ConvocatoriaAsistenciaEventoEstudiante, Estudiante> estudianteId;
}
