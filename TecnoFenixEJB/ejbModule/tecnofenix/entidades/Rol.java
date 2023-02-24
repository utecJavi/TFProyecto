package tecnofenix.entidades;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



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
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;

    @Basic(optional = true)
    @Column(name = "activo")
    private Boolean activo;
    
    @ManyToMany(fetch =FetchType.EAGER)
    @JoinTable(name = "rol_funcion",
            joinColumns = @JoinColumn(name = "id_rol"),
            inverseJoinColumns = @JoinColumn(name = "id_funcionalidad"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_rol", "id_funcionalidad"})
    )
    List<Funcionalidad> funcionalidades = new ArrayList<Funcionalidad>();

    public Rol() {}

    public Rol(Integer id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
	
    public List<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	@Override
    public String toString() {
        return this.nombre;
    }
}
