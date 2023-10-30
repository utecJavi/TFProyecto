package tecnofenix.entidades;
//SIN_SELECCIONAR(""),VIRTUAL("Virtual"), PRESENCIAL("Presencial"), SEMI_PRESENCIAL("Semipresencial"); 

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "modalidad_evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModalidadEvento.findAll", query = "SELECT i FROM ModalidadEvento i "),
    @NamedQuery(name = "ModalidadEvento.findById", query = "SELECT i FROM ModalidadEvento i WHERE i.id = :id"),
    @NamedQuery(name = "ModalidadEvento.findByNombre", query = "SELECT i FROM ModalidadEvento i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "ModalidadEvento.findByActivo", query = "SELECT i FROM ModalidadEvento i WHERE i.activo = :activo")})
public class ModalidadEvento implements Serializable {
	private static final long serialVersionUID = 1L;
//	SIN_SELECCIONAR(""),VIRTUAL("Virtual"), PRESENCIAL("Presencial"), SEMI_PRESENCIAL("Semipresencial");
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="modalidad_evento_seq")
    @SequenceGenerator(name="modalidad_evento_seq", sequenceName="modalidad_evento_seq", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "activo")
    private Boolean activo;

    public ModalidadEvento() {
    	
    }

	public ModalidadEvento(Integer id, String nombre ,Boolean activo) {
			this.id = id;
			this.nombre = nombre;
			this.activo = activo;
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

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	  @Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (id != null ? id.hashCode() : 0);
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof ModalidadEvento)) {
	            return false;
	        }
	        ModalidadEvento other = (ModalidadEvento) object;
	        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	            return false;
	        }
	        return true;
	    }
	@Override
	public String toString() {
		return this.nombre;
	}
}
