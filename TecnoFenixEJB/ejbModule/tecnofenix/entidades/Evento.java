/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tecnofenix.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jasuaga
 */
@Entity
@Table(name = "evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
    @NamedQuery(name = "Evento.findById", query = "SELECT e FROM Evento e WHERE e.id = :id"),
    @NamedQuery(name = "Evento.findByInicio", query = "SELECT e FROM Evento e WHERE e.inicio = :inicio"),
    @NamedQuery(name = "Evento.findByFin", query = "SELECT e FROM Evento e WHERE e.fin = :fin")})
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="evento_seq")
    @SequenceGenerator(name="evento_seq", sequenceName="evento_seq", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "titulo")
    @Basic(optional = false)
    @NotNull
    private String titulo;
    
    @Enumerated
    @NotNull
    private TipoEvento tipo;
    
    @Enumerated
    @NotNull
    private ModalidadEvento modalidad;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicio;
    
    @Column(name = "fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fin;
    
    @Column(nullable = true)
    @Basic(optional = true)
    private String localizacion;
    
    @ManyToOne
    @JoinColumn(name = "id_itr", nullable = false)
    private Itr itr;
    
    @OneToMany(mappedBy = "eventoId")
    private Collection<Justificacion> justificacionCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventoId")
    private Collection<ConvocatoriaAsistenciaEventoEstudiante> convocatoriaAsistenciaEventoEstudianteCollection;
    
    @OneToMany(mappedBy = "eventoId")
    private Collection<Reclamo> reclamoCollection;
    
    @OneToMany(mappedBy = "eventoId")
    private Collection<Constancia> constanciaCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventoId")
    private Collection<TutorResponsableEvento> tutorResponsableEventoCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventoId")
    private Collection<GestionEventoAnalista> gestionEventoAnalistaCollection;

    public Evento() {
    }

    public Evento(Integer id) {
        this.id = id;
    }

    public Evento(Integer id, Date inicio) {
        this.id = id;
        this.inicio = inicio;
    }
    
    public Evento(Integer id, Date inicio, String titulo, TipoEvento tipo, ModalidadEvento modalidad, String localizacion, Itr itr) {
        this.id = id;
        this.inicio = inicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    @XmlTransient
    public Collection<Justificacion> getJustificacionCollection() {
        return justificacionCollection;
    }

    public void setJustificacionCollection(Collection<Justificacion> justificacionCollection) {
        this.justificacionCollection = justificacionCollection;
    }

    @XmlTransient
    public Collection<ConvocatoriaAsistenciaEventoEstudiante> getConvocatoriaAsistenciaEventoEstudianteCollection() {
        return convocatoriaAsistenciaEventoEstudianteCollection;
    }

    public void setConvocatoriaAsistenciaEventoEstudianteCollection(Collection<ConvocatoriaAsistenciaEventoEstudiante> convocatoriaAsistenciaEventoEstudianteCollection) {
        this.convocatoriaAsistenciaEventoEstudianteCollection = convocatoriaAsistenciaEventoEstudianteCollection;
    }

    @XmlTransient
    public Collection<Reclamo> getReclamoCollection() {
        return reclamoCollection;
    }

    public void setReclamoCollection(Collection<Reclamo> reclamoCollection) {
        this.reclamoCollection = reclamoCollection;
    }

    @XmlTransient
    public Collection<Constancia> getConstanciaCollection() {
        return constanciaCollection;
    }

    public void setConstanciaCollection(Collection<Constancia> constanciaCollection) {
        this.constanciaCollection = constanciaCollection;
    }

    @XmlTransient
    public Collection<TutorResponsableEvento> getTutorResponsableEventoCollection() {
        return tutorResponsableEventoCollection;
    }

    public void setTutorResponsableEventoCollection(Collection<TutorResponsableEvento> tutorResponsableEventoCollection) {
        this.tutorResponsableEventoCollection = tutorResponsableEventoCollection;
    }

    @XmlTransient
    public Collection<GestionEventoAnalista> getGestionEventoAnalistaCollection() {
        return gestionEventoAnalistaCollection;
    }

    public void setGestionEventoAnalistaCollection(Collection<GestionEventoAnalista> gestionEventoAnalistaCollection) {
        this.gestionEventoAnalistaCollection = gestionEventoAnalistaCollection;
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
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + " " + titulo;
    }
    
}
