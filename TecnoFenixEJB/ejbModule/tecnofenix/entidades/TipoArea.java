package tecnofenix.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author jasuaga
 */
@Entity
@Table(name = "tipo_tutor_area")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoArea.findAll", query = "SELECT t FROM TipoArea t"),
    @NamedQuery(name = "TipoArea.findById", query = "SELECT t FROM TipoArea t WHERE t.id = :id"),
    @NamedQuery(name = "TipoArea.findByBajaLogica", query = "SELECT t FROM TipoArea t WHERE t.bajaLogica = :bajaLogica"),
    @NamedQuery(name = "TipoArea.findByNombre", query = "SELECT t FROM TipoArea t WHERE t.nombre = :nombre")})
public class TipoArea implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_area_seq")
    @SequenceGenerator(name = "tipo_area_seq", sequenceName = "tipo_area_seq", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @NotNull
    @Column(name = "baja_logica")
    private Boolean bajaLogica;

    public TipoArea() {
    }

    public TipoArea(  String nombre,Boolean bajaLogica) {
        this.bajaLogica = bajaLogica;
        this.nombre = nombre;
    }
    
    public TipoArea(Integer id,String nombre,Boolean bajaLogica) {
        this.id = id;
        this.bajaLogica = bajaLogica;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getBajaLogica() {
        return bajaLogica;
    }

    public void setBajaLogica(Boolean bajaLogica) {
        this.bajaLogica = bajaLogica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoArea)) {
            return false;
        }
        TipoArea other = (TipoArea) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

