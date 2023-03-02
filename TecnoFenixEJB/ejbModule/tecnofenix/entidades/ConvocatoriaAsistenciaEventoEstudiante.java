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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jasuaga
 */
@Entity
@Table(name = "convocatoria_asistencia_evento_estudiante", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"evento_id", "estudiante_id"})
})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConvocatoriaAsistenciaEventoEstudiante.findAll", query = "SELECT c FROM ConvocatoriaAsistenciaEventoEstudiante c"),
    @NamedQuery(name = "ConvocatoriaAsistenciaEventoEstudiante.findById", query = "SELECT c FROM ConvocatoriaAsistenciaEventoEstudiante c WHERE c.id = :id"),
    @NamedQuery(name = "ConvocatoriaAsistenciaEventoEstudiante.findByCalificacion", query = "SELECT c FROM ConvocatoriaAsistenciaEventoEstudiante c WHERE c.calificacion = :calificacion"),
    @NamedQuery(name = "ConvocatoriaAsistenciaEventoEstudiante.findByAsistencia", query = "SELECT c FROM ConvocatoriaAsistenciaEventoEstudiante c WHERE c.asistencia = :asistencia")})
public class ConvocatoriaAsistenciaEventoEstudiante implements Serializable {
    private static final long serialVersionUID = 1L;
 
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="conv_asist_even_estu_seq")
    @SequenceGenerator(name="conv_asist_even_estu_seq", sequenceName="conv_asist_even_estu_seq", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "calificacion", nullable = true)
    private Integer calificacion;

    @Basic(optional = false)
    @Column(name = "asistencia" , nullable = true)
    private Boolean asistencia;
    
    @Enumerated(EnumType.STRING)
    private RegistroAsistencia registroAsistencia;

    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @NotNull
    private Evento eventoId;

    @JoinColumn(name = "estudiante_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @NotNull
    private Estudiante estudianteId;

    public ConvocatoriaAsistenciaEventoEstudiante() {
    }

    public ConvocatoriaAsistenciaEventoEstudiante(Integer id) {
        this.id = id;
    }

    public ConvocatoriaAsistenciaEventoEstudiante(Integer id, int calificacion, Boolean asistencia) {
        this.id = id;
        this.calificacion = calificacion;
        this.asistencia = asistencia;
    }
    
    public ConvocatoriaAsistenciaEventoEstudiante(Integer id, Evento evento, Estudiante estudiante) {
        this.id = id;
        this.eventoId = evento;
        this.estudianteId= estudiante;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public Boolean getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Boolean asistencia) {
        this.asistencia = asistencia;
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

	public RegistroAsistencia getRegistroAsistencia() {
		return registroAsistencia;
	}


	public void setRegistroAsistencia(RegistroAsistencia registroAsistencia) {
		this.registroAsistencia = registroAsistencia;
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
        if (!(object instanceof ConvocatoriaAsistenciaEventoEstudiante)) {
            return false;
        }
        ConvocatoriaAsistenciaEventoEstudiante other = (ConvocatoriaAsistenciaEventoEstudiante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tecnofenix.entidades.ConvocatoriaAsistenciaEventoEstudiante[ id=" + id + " ]";
    }
    
}
