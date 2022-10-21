/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tecnofenix.entidades;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jasuaga
 */
@Entity
@Table(name = "gestion_evento_analista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GestionEventoAnalista.findAll", query = "SELECT g FROM GestionEventoAnalista g"),
    @NamedQuery(name = "GestionEventoAnalista.findById", query = "SELECT g FROM GestionEventoAnalista g WHERE g.id = :id")})
public class GestionEventoAnalista implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="gestion_evento_analista_seq")
    @SequenceGenerator(name="gestion_evento_analista_seq", sequenceName="gestion_evento_analista_seq", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Evento eventoId;
    @JoinColumn(name = "analista_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Analista analistaId;

    public GestionEventoAnalista() {
    }

    public GestionEventoAnalista(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Evento getEventoId() {
        return eventoId;
    }

    public void setEventoId(Evento eventoId) {
        this.eventoId = eventoId;
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
        if (!(object instanceof GestionEventoAnalista)) {
            return false;
        }
        GestionEventoAnalista other = (GestionEventoAnalista) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tecnofenix.entidades.GestionEventoAnalista[ id=" + id + " ]";
    }
    
}
