package tecnofenix.entidades;

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
@Table(name = "tipo_estado_justificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEstadoJustificacion.findAll", query = "SELECT i FROM TipoEstadoJustificacion i "),
    @NamedQuery(name = "TipoEstadoJustificacion.findById", query = "SELECT i FROM TipoEstadoJustificacion i WHERE i.id = :id"),
    @NamedQuery(name = "TipoEstadoJustificacion.findByNombre", query = "SELECT i FROM TipoEstadoJustificacion i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "TipoEstadoJustificacion.findByActivo", query = "SELECT i FROM TipoEstadoJustificacion i WHERE i.activo = :activo")})
public class TipoEstadoJustificacion implements Serializable {
	private static final long serialVersionUID = 1L;
	//CREATE SEQUENCE  "PROYECTO"."TIPO_ESTADO_RECL_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tipo_estado_just_seq")
    @SequenceGenerator(name="tipo_estado_just_seq", sequenceName="tipo_estado_just_seq", allocationSize=1)
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

    public TipoEstadoJustificacion() {
    	
    }

	public TipoEstadoJustificacion(Integer id, String nombre ,Boolean activo) {
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
	        if (!(object instanceof TipoEstadoJustificacion)) {
	            return false;
	        }
	        TipoEstadoJustificacion other = (TipoEstadoJustificacion) object;
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
