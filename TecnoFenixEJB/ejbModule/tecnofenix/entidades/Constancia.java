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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "constancia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Constancia.findAll", query = "SELECT c FROM Constancia c"),
    @NamedQuery(name = "Constancia.findById", query = "SELECT c FROM Constancia c WHERE c.id = :id"),
    @NamedQuery(name = "Constancia.findByDetalle", query = "SELECT c FROM Constancia c WHERE c.detalle = :detalle"),
    @NamedQuery(name = "Constancia.findByFecha", query = "SELECT c FROM Constancia c WHERE c.fecha = :fecha")})
public class Constancia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="constancia_seq")
    @SequenceGenerator(name="constancia_seq", sequenceName="constancia_seq", allocationSize=1)
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "constanciaId")
    private Collection<AccionConstancia> accionConstanciaCollection;
    
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    @ManyToOne
    private Evento eventoId;
    
    @JoinColumn(name = "estudiante_id", referencedColumnName = "id")
    @ManyToOne
    private Estudiante estudianteId;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoConstancia estado;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoConstancias tipoConstancia;

	public Constancia() {
    }

    public Constancia(Integer id) {
        this.id = id;
    }

    public Constancia(Integer id, String detalle, Date fecha) {
        this.id = id;
        this.detalle = detalle;
        this.fecha = fecha;
    }
    
    public Constancia(Integer id, String detalle, Date fecha, Evento eventoId, Estudiante estudianteId, EstadoConstancia estado, TipoConstancias tipoConstancia) {
        this.id = id;
        this.detalle = detalle;
        this.fecha = fecha;
        this.eventoId = eventoId;
        this.estudianteId = estudianteId;
        this.estado = estado;
        this.tipoConstancia = tipoConstancia;
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

    @XmlTransient
    public Collection<AccionConstancia> getAccionConstanciaCollection() {
        return accionConstanciaCollection;
    }

    public void setAccionConstanciaCollection(Collection<AccionConstancia> accionConstanciaCollection) {
        this.accionConstanciaCollection = accionConstanciaCollection;
    }

    public Evento getEventoId() {
        return this.eventoId;
    }

    public void setEventoId(Evento eventoId) {
        this.eventoId = eventoId;
    }

    public Estudiante getEstudianteId() {
        return this.estudianteId;
    }

    public void setEstudianteId(Estudiante estudianteId) {
        this.estudianteId = estudianteId;
    }
    
    public EstadoConstancia getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoConstancia estado) {
		this.estado = estado;
	}
	
	public TipoConstancias getTipoConstancia() {
		return this.tipoConstancia;
	}

	public void setTipoConstancia(TipoConstancias tipoConstancia) {
		this.tipoConstancia = tipoConstancia;
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
        if (!(object instanceof Constancia)) {
            return false;
        }
        Constancia other = (Constancia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Constancia id=" + id;
    }
    
    public enum EstadoConstancia {
    	INGRESADO,
    	EN_PROCESO,
    	FINALIZADO
    }
    
}
