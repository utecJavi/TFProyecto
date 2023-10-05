package tecnofenix.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-10-02T14:49:41.600-0300")
@StaticMetamodel(Analista.class)
public class Analista_ extends Usuario_ {
	public static volatile CollectionAttribute<Analista, AccionReclamo> accionReclamoCollection;
	public static volatile CollectionAttribute<Analista, AccionJustificacion> accionJustificacionCollection;
	public static volatile CollectionAttribute<Analista, AccionConstancia> accionConstanciaCollection;
	public static volatile CollectionAttribute<Analista, GestionEventoAnalista> gestionEventoAnalistaCollection;
}
