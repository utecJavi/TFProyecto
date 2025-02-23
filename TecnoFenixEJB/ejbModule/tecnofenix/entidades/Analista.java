/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tecnofenix.entidades;

import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jasuaga
 */
@Entity
@DiscriminatorValue(value = Usuario.TIPO_ANALISTA)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Analista.findAll", query = "SELECT a FROM Analista a"),
    @NamedQuery(name = "Analista.findById", query = "SELECT a FROM Analista a WHERE a.id = :id")})
public class Analista extends Usuario {
    private static final long serialVersionUID = 1L;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analistaId")
    private Collection<AccionReclamo> accionReclamoCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analistaId")
    private Collection<AccionJustificacion> accionJustificacionCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analistaId")
    private Collection<AccionConstancia> accionConstanciaCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analistaId")
    private Collection<GestionEventoAnalista> gestionEventoAnalistaCollection;
    


    public Analista() {
    }
    
    public Analista(Integer id) {
        super.setId(id);
    }
    
    public Analista(Integer documento, String usuario, String contrasenia, String apellidos, String nombres, Date fechaNacimiento, String departamento, String genero, String localidad, String mail, String mailPersonal,String telefono, Itr itr,Rol rol) {
    	super(documento, usuario, contrasenia, apellidos, nombres, fechaNacimiento, departamento, genero, localidad, mail,mailPersonal, telefono, itr, rol);
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


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Analista)) {
            return false;
        }
        Analista other = (Analista) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Analista{} " + super.toString();
    }

	@Override
	public void darDeBaja() {
		this.setActivo(false);
		
	}

	@Override
	public void reactivarUsuario() {
		this.setActivo(true);
		
	}
}
