/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tecnofenix.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jasuaga
 */
@Entity
@Table(name = "accion_justificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccionJustificacion.findAll", query = "SELECT a FROM AccionJustificacion a"),
    @NamedQuery(name = "AccionJustificacion.findById", query = "SELECT a FROM AccionJustificacion a WHERE a.id = :id"),
    @NamedQuery(name = "AccionJustificacion.findByDetalle", query = "SELECT a FROM AccionJustificacion a WHERE a.detalle = :detalle"),
    @NamedQuery(name = "AccionJustificacion.findByFecha", query = "SELECT a FROM AccionJustificacion a WHERE a.fecha = :fecha")})
public class AccionJustificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JoinColumn(name = "justificacion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Justificacion justificacionId;
    @JoinColumn(name = "analista_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Analista analistaId;

    public AccionJustificacion() {
    }

    public AccionJustificacion(Integer id) {
        this.id = id;
    }

    public AccionJustificacion(Integer id, String detalle, Date fecha) {
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

    public Justificacion getJustificacionId() {
        return justificacionId;
    }

    public void setJustificacionId(Justificacion justificacionId) {
        this.justificacionId = justificacionId;
    }

    public Analista getAnalistaId() {
        return analistaId;
    }

    public void setAnalistaId(Analista analistaId) {
        this.analistaId = analistaId;
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
        if (!(object instanceof AccionJustificacion)) {
            return false;
        }
        AccionJustificacion other = (AccionJustificacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tecnofenix.entidades.AccionJustificacion[ id=" + id + " ]";
    }
    
}
