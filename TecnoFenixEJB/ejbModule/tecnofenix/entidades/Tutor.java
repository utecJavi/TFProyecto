/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tecnofenix.entidades;


import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@DiscriminatorValue(value = Usuario.TIPO_TUTOR)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tutor.findAll", query = "SELECT t FROM Tutor t"),
    @NamedQuery(name = "Tutor.findById", query = "SELECT t FROM Tutor t WHERE t.id = :id"),
    @NamedQuery(name = "Tutor.findByTipo", query = "SELECT t FROM Tutor t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "Tutor.findByArea", query = "SELECT t FROM Tutor t WHERE t.area = :area")})
public class Tutor extends Usuario {
    private static final long serialVersionUID = 1L;

    @Column(name = "tipo")
    private Integer tipo;
    @Column(name = "area")
    private Integer area;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutorId")
    private Collection<TutorResponsableEvento> tutorResponsableEventoCollection;

    public Tutor() {
    }

    public Tutor(Integer documento, String usuario, String contrasenia, String apellidos, String nombres,
                 Date fechaNacimiento, String mail, String telefono, Itr itr, Integer tipo, Integer area) {
        super(documento, usuario, contrasenia, apellidos, nombres, fechaNacimiento, mail, telefono, itr);
        this.tipo = tipo;
        this.area = area;
    }

    public Tutor(Integer id, Integer documento, String usuario, String contrasenia, String apellidos, String nombres,
                 Date fechaNacimiento, String mail, String telefono, Itr itr, Integer tipo, Integer area) {
        super(id, documento, usuario, contrasenia, apellidos, nombres, fechaNacimiento, mail, telefono, itr);
        this.tipo = tipo;
        this.area = area;
    }


	public Tutor(Integer id) {
        super.setId(id);
    }


    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    @XmlTransient
    public Collection<TutorResponsableEvento> getTutorResponsableEventoCollection() {
        return tutorResponsableEventoCollection;
    }

    public void setTutorResponsableEventoCollection(Collection<TutorResponsableEvento> tutorResponsableEventoCollection) {
        this.tutorResponsableEventoCollection = tutorResponsableEventoCollection;
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
        if (!(object instanceof Tutor)) {
            return false;
        }
        Tutor other = (Tutor) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "tipo=" + tipo +
                ", area=" + area +
                "} " + super.toString();
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
