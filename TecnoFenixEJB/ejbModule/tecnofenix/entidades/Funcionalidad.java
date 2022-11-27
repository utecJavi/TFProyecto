package tecnofenix.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "funcionalidades")
@NamedQueries({
    @NamedQuery(name = "Funcionalidad.findAll", query = "SELECT i FROM Funcionalidad i"),
    @NamedQuery(name = "Funcionalidad.findById", query = "SELECT i FROM Funcionalidad i WHERE i.id = :id"),
    @NamedQuery(name = "Funcionalidad.findByDescripcion", query = "SELECT i FROM Funcionalidad i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "Funcionalidad.findByNombre", query = "SELECT i FROM Funcionalidad i WHERE i.nombre = :nombre")})
public class Funcionalidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "funcionalidades_seq", sequenceName = "funcionalidades_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionalidades_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "activo")
    private Boolean activo;
    
    public Funcionalidad() {}

    public Funcionalidad(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Funcionalidad(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id=id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
    @Override
    public String toString() {
        return this.nombre;
    }
}
