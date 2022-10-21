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
@Table(name = "tutor_responsable_evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TutorResponsableEvento.findAll", query = "SELECT t FROM TutorResponsableEvento t"),
    @NamedQuery(name = "TutorResponsableEvento.findById", query = "SELECT t FROM TutorResponsableEvento t WHERE t.id = :id")})
public class TutorResponsableEvento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tutor_responsable_evento_seq")
    @SequenceGenerator(name="tutor_responsable_evento_seq", sequenceName="tutor_responsable_evento_seq", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "tutor_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tutor tutorId;
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Evento eventoId;

    public TutorResponsableEvento() {
    }

    public TutorResponsableEvento(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tutor getTutorId() {
        return tutorId;
    }

    public void setTutorId(Tutor tutorId) {
        this.tutorId = tutorId;
    }

    public Evento getEventoId() {
        return eventoId;
    }

    public void setEventoId(Evento eventoId) {
        this.eventoId = eventoId;
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
        if (!(object instanceof TutorResponsableEvento)) {
            return false;
        }
        TutorResponsableEvento other = (TutorResponsableEvento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tecnofenix.entidades.TutorResponsableEvento[ id=" + id + " ]";
    }
    
}
