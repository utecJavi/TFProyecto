/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tecnofenix.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jasuaga
 */
@Entity
@Table(name = "reclamo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reclamo.findAll", query = "SELECT r FROM Reclamo r"),
    @NamedQuery(name = "Reclamo.findById", query = "SELECT r FROM Reclamo r WHERE r.id = :id"),
    @NamedQuery(name = "Reclamo.findByDetalle", query = "SELECT r FROM Reclamo r WHERE r.detalle = :detalle"),
    @NamedQuery(name = "Reclamo.findByFecha", query = "SELECT r FROM Reclamo r WHERE r.fecha = :fecha")})
public class Reclamo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="reclamo_seq")
    @SequenceGenerator(name="reclamo_seq", sequenceName="reclamo_seq", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "titulo")
    private String titulo;
    
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "detalle")
    private String detalle;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reclamoId")
    private Collection<AccionReclamo> accionReclamoCollection;
    
    @Basic(optional = true)
    @JoinColumn(name = "estado_reclamo_id", referencedColumnName = "id")
    @ManyToOne
    private TipoEstadoReclamo estadoReclamoId;
    
    @Basic(optional = true)
    @Column(name = "fecha_estado_reclamo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstadoReclamo;
    
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    @ManyToOne
    private Evento eventoId;
    
    @JoinColumn(name = "estudiante_id", referencedColumnName = "id")
    @ManyToOne
    private Estudiante estudianteId;
    
    @Column(name = "creditos")
    private Integer creditos;
    
    @Column(name = "semestre")
    private Integer semestre;
    
    @Column(name = "activo")
    private Boolean activo;

	
	public Reclamo() {
    }

    public Reclamo(Integer id) {
        this.id = id;
    }

    public Reclamo(Integer id, String detalle, Date fecha) {
        this.id = id;
        this.detalle = detalle;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public Collection<AccionReclamo> getAccionReclamoCollection() {
        return accionReclamoCollection;
    }

    public void setAccionReclamoCollection(Collection<AccionReclamo> accionReclamoCollection) {
        this.accionReclamoCollection = accionReclamoCollection;
    }

    public Evento getEventoId() {
        return eventoId;
    }

    public void setEventoId(Evento eventoId) {
        this.eventoId = eventoId;
    }

    public Estudiante getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Estudiante estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Integer getCreditos() {
		return creditos;
	}

	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}
	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}
	

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	
    public TipoEstadoReclamo getEstadoReclamoId() {
		return estadoReclamoId;
	}

	public void setEstadoReclamoId(TipoEstadoReclamo estadoReclamoId) {
		this.estadoReclamoId = estadoReclamoId;
	}

	public Date getFechaEstadoReclamo() {
		return fechaEstadoReclamo;
	}

	public void setFechaEstadoReclamo(Date fechaEstadoReclamo) {
		this.fechaEstadoReclamo = fechaEstadoReclamo;
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
        if (!(object instanceof Reclamo)) {
            return false;
        }
        Reclamo other = (Reclamo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "Reclamo [id=" + id + ", titulo=" + titulo + ", detalle=" + detalle + ", fecha=" + fecha
				+ ", accionReclamoCollection=" + accionReclamoCollection + ", estadoReclamoId=" + estadoReclamoId
				+ ", fechaEstadoReclamo=" + fechaEstadoReclamo + ", eventoId=" + eventoId + ", estudianteId="
				+ estudianteId + ", creditos=" + creditos + ", semestre=" + semestre + ", activo=" + activo + "]";
	}

//    @Override
//    public String toString() {
////        return "tecnofenix.entidades.Reclamo[ id=" + id + " ]";
//    	
//    }
    
}
