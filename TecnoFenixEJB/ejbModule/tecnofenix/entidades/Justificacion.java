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
 * @author jasuaga
 */
@Entity
@Table(name = "justificacion")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Justificacion.findAll", query = "SELECT j FROM Justificacion j"), @NamedQuery(name = "Justificacion.findById", query = "SELECT j FROM Justificacion j WHERE j.id = :id"), @NamedQuery(name = "Justificacion.findByDetalle", query = "SELECT j FROM Justificacion j WHERE j.detalle = :detalle"), @NamedQuery(name = "Justificacion.findByFecha", query = "SELECT j FROM Justificacion j WHERE j.fecha = :fecha")})
public class Justificacion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "justificacion_seq")
    @SequenceGenerator(name = "justificacion_seq", sequenceName = "justificacion_seq", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

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

    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    @ManyToOne
    private Evento eventoId;

    @JoinColumn(name = "estudiante_id", referencedColumnName = "id")
    @ManyToOne
    private Estudiante estudianteId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "justificacionId")
    private Collection<AccionJustificacion> accionJustificacionCollection;

    
    @Basic(optional = true)
    @JoinColumn(name = "estado_justificacion_id", referencedColumnName = "id")
    @ManyToOne
    private TipoEstadoJustificacion estadoJustificacionId;
    
    @Basic(optional = true)
    @Column(name = "fecha_estado_justificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstadoJustificacion;
    
    @Column(name = "activo")
    private Boolean activo;
    
    public Justificacion() {
    }

    public Justificacion(Integer id) {
        this.id = id;
    }

    public Justificacion(Integer id, String detalle, Date fecha) {
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

    @XmlTransient
    public Collection<AccionJustificacion> getAccionJustificacionCollection() {
        return accionJustificacionCollection;
    }

    public void setAccionJustificacionCollection(Collection<AccionJustificacion> accionJustificacionCollection) {
        this.accionJustificacionCollection = accionJustificacionCollection;
    }  
    
    public TipoEstadoJustificacion getEstadoJustificacionId() {
		return estadoJustificacionId;
	}

	public void setEstadoJustificacionId(TipoEstadoJustificacion estadoJustificacionId) {
		this.estadoJustificacionId = estadoJustificacionId;
	}
	
	public Date getFechaEstadoJustificacion() {
		return fechaEstadoJustificacion;
	}

	public void setFechaEstadoJustificacion(Date fechaEstadoJustificacion) {
		this.fechaEstadoJustificacion = fechaEstadoJustificacion;
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
        if (!(object instanceof Justificacion)) {
            return false;
        }
        Justificacion other = (Justificacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tecnofenix.entidades.Justificacion[ id=" + id + " ]";
    }

}
