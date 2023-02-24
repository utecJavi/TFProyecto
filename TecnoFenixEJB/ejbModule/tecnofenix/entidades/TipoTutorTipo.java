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
@Table(name = "tipo_tutor_tipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoTutorTipo.findAll", query = "SELECT t FROM TipoTutorTipo t"),
    @NamedQuery(name = "TipoTutorTipo.findById", query = "SELECT t FROM TipoTutorTipo t WHERE t.id = :id"),
    @NamedQuery(name = "TipoTutorTipo.findByBajaLogica", query = "SELECT t FROM TipoTutorTipo t WHERE t.bajaLogica = :bajaLogica"),
    @NamedQuery(name = "TipoTutorTipo.findByNombre", query = "SELECT t FROM TipoTutorTipo t WHERE t.nombre = :nombre")})
public class TipoTutorTipo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipotutortipo_seq")
    @SequenceGenerator(name = "tipotutortipo_seq", sequenceName = "tipotutortipo_seq", allocationSize = 1)
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

    public TipoTutorTipo() {
    }

    public TipoTutorTipo(String nombre ,Boolean bajaLogica) {
        this.bajaLogica = bajaLogica;
        this.nombre = nombre;
    }
    public TipoTutorTipo(Integer id, String nombre ,Boolean bajaLogica) {
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
        if (!(object instanceof TipoTutorTipo)) {
            return false;
        }
        TipoTutorTipo other = (TipoTutorTipo) object;
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

