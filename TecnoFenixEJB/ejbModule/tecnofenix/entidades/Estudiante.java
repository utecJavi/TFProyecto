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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e"),
    @NamedQuery(name = "Estudiante.findById", query = "SELECT e FROM Estudiante e WHERE e.id = :id"),
    @NamedQuery(name = "Estudiante.findByGeneracion", query = "SELECT e FROM Estudiante e WHERE e.generacion = :generacion")})
public class Estudiante extends Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "generacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date generacion;
    
    @OneToMany(mappedBy = "estudianteId")
    private Collection<Justificacion> justificacionCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudianteId")
    private Collection<ConvocatoriaAsistenciaEventoEstudiante> convocatoriaAsistenciaEventoEstudianteCollection;
    
    @OneToMany(mappedBy = "estudianteId")
    private Collection<Reclamo> reclamoCollection;
    
    @OneToMany(mappedBy = "estudianteId")
    private Collection<Constancia> constanciaCollection;
    

    public Estudiante() {
    }

    
    
    public Estudiante(Integer id, int documento, String usuario, String contrasenia, String apellidos, String nombres,
			Date fechaNacimiento, String mail, String telefono, Date generacion, Itr itr) {
		super(id, documento, usuario, contrasenia, apellidos, nombres, fechaNacimiento, mail, telefono);
		this.generacion=generacion;
		super.setIdItr(itr);
		//TODO Auto-generated constructor stub
	}



	public Estudiante(Date generacion) {
        this.generacion = generacion;
    }

    public Date getGeneracion() {
        return generacion;
    }

    public void setGeneracion(Date generacion) {
        this.generacion = generacion;
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

 

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tecnofenix.entidades.Estudiante[ id=" + this.getId() + " ]";
    }
    
}
