package tecnofenix.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-02-26T22:18:13.926-0300")
@StaticMetamodel(ConvocatoriaAsistenciaEventoEstudiante.class)
public class ConvocatoriaAsistenciaEventoEstudiante_ {
	public static volatile SingularAttribute<ConvocatoriaAsistenciaEventoEstudiante, Integer> id;
	public static volatile SingularAttribute<ConvocatoriaAsistenciaEventoEstudiante, Integer> calificacion;
	public static volatile SingularAttribute<ConvocatoriaAsistenciaEventoEstudiante, Boolean> asistencia;
	public static volatile SingularAttribute<ConvocatoriaAsistenciaEventoEstudiante, Evento> eventoId;
	public static volatile SingularAttribute<ConvocatoriaAsistenciaEventoEstudiante, Estudiante> estudianteId;
	public static volatile SingularAttribute<ConvocatoriaAsistenciaEventoEstudiante, RegistroAsistencia> registroAsistencia;
}
