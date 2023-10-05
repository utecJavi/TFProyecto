package tecnofenix.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-10-02T14:49:41.614-0300")
@StaticMetamodel(GestionEventoAnalista.class)
public class GestionEventoAnalista_ {
	public static volatile SingularAttribute<GestionEventoAnalista, Integer> id;
	public static volatile SingularAttribute<GestionEventoAnalista, Evento> eventoId;
	public static volatile SingularAttribute<GestionEventoAnalista, Analista> analistaId;
}
