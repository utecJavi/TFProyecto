/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tecnofenix.entidades;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jasuaga
 */
@Entity
@Table(name = "analista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Analista.findAll", query = "SELECT a FROM Analista a"),
    @NamedQuery(name = "Analista.findById", query = "SELECT a FROM Analista a WHERE a.id = :id")})
public class Analista implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="analista_seq")
    @SequenceGenerator(name="analista_seq", sequenceName="analista_seq", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analistaId")
    private Collection<AccionReclamo> accionReclamoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analistaId")
    private Collection<AccionJustificacion> accionJustificacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analistaId")
    private Collection<AccionConstancia> accionConstanciaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analistaId")
    private Collection<GestionEventoAnalista> gestionEventoAnalistaCollection;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne
    private Usuario idUsuario;

    public Analista() {
    }

    public Analista(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public Collection<AccionReclamo> getAccionReclamoCollection() {
        return accionReclamoCollection;
    }

    public void setAccionReclamoCollection(Collection<AccionReclamo> accionReclamoCollection) {
        this.accionReclamoCollection = accionReclamoCollection;
    }

    @XmlTransient
    public Collection<AccionJustificacion> getAccionJustificacionCollection() {
        return accionJustificacionCollection;
    }

    public void setAccionJustificacionCollection(Collection<AccionJustificacion> accionJustificacionCollection) {
        this.accionJustificacionCollection = accionJustificacionCollection;
    }

    @XmlTransient
    public Collection<AccionConstancia> getAccionConstanciaCollection() {
        return accionConstanciaCollection;
    }

    public void setAccionConstanciaCollection(Collection<AccionConstancia> accionConstanciaCollection) {
        this.accionConstanciaCollection = accionConstanciaCollection;
    }

    @XmlTransient
    public Collection<GestionEventoAnalista> getGestionEventoAnalistaCollection() {
        return gestionEventoAnalistaCollection;
    }

    public void setGestionEventoAnalistaCollection(Collection<GestionEventoAnalista> gestionEventoAnalistaCollection) {
        this.gestionEventoAnalistaCollection = gestionEventoAnalistaCollection;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
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
        if (!(object instanceof Analista)) {
            return false;
        }
        Analista other = (Analista) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tecnofenix.entidades.Analista[ id=" + id + " ]";
    }
    
}
