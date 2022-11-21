package tecnofenix.entidades;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "roles")
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT a FROM Rol a"),
    @NamedQuery(name = "Rol.findById", query = "SELECT a FROM Rol a WHERE a.id = :id")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "roles_seq", sequenceName = "roles_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;

    @ManyToMany()
    @JoinTable(name = "rol_funcion",
            joinColumns = @JoinColumn(name = "id_rol"),
            inverseJoinColumns = @JoinColumn(name = "id_funcionalidad")
    )
    Collection<Funcionalidad> funcionalidades = new ArrayList<Funcionalidad>();

    public Rol() {}

    public Rol(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
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

   
    public Collection<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(Collection<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	@Override
    public String toString() {
        return "Rol{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
