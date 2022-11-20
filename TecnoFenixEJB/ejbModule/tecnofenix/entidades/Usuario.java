/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tecnofenix.entidades;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import tecnofenix.interfaces.BajaLogica;

/**
 *
 * @author jasuaga
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "u_tipo",
        discriminatorType = DiscriminatorType.STRING
)
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByDocumento", query = "SELECT u FROM Usuario u WHERE u.documento = :documento"),
    @NamedQuery(name = "Usuario.findByUsuario", query = "SELECT u FROM Usuario u WHERE u.usuario = :usuario"),
    @NamedQuery(name = "Usuario.findByContrasenia", query = "SELECT u FROM Usuario u WHERE u.contrasenia = :contrasenia"),
    @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuario.findByFechaNacimiento", query = "SELECT u FROM Usuario u WHERE u.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Usuario.findByDepartamento", query = "SELECT u FROM Usuario u WHERE u.departamento = :departamento"),
    @NamedQuery(name = "Usuario.findByGenero", query = "SELECT u FROM Usuario u WHERE u.genero = :genero"),
    @NamedQuery(name = "Usuario.findByLocalidad", query = "SELECT u FROM Usuario u WHERE u.localidad = :localidad"),
    @NamedQuery(name = "Usuario.findByMail", query = "SELECT u FROM Usuario u WHERE u.mail = :mail"),
    @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono")})
public abstract class Usuario extends Activo implements Serializable ,BajaLogica {
    private static final long serialVersionUID = 1L;

    public static final String TIPO_ANALISTA = "ANALISTA";
    public static final String TIPO_ESTUDIANTE = "ESTUDIANTE";
    public static final String TIPO_TUTOR = "TUTOR";

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usuario_seq")
    @SequenceGenerator(name="usuario_seq", sequenceName="usuario_seq", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "documento")
    private Integer documento;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usuario")
    private String usuario;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "contrasenia")
    private String contrasenia;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "apellidos")
    private String apellidos;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombres")
    private String nombres;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;

    @Size(max = 45)
    @Column(name = "departamento")
    private String departamento;

    @Size(max = 1)
    @Column(name = "genero")
    private String genero;

    @Size(max = 45)
    @Column(name = "localidad")
    private String localidad;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "mail")
    private String mail;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "telefono")
    private String telefono;

    @JoinColumn(name = "id_itr", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Itr itr;
    
    @Column(name = "u_tipo", insertable=false, updatable=false)
    private String uTipo;
    
    @Basic(optional = false)
    @Column(name = "validado")
    private Boolean validado;

    @ManyToOne()
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer documento, String usuario, String contrasenia, String apellidos, String nombres, Date fechaNacimiento, String departamento, String genero, String localidad, String mail, String telefono, Itr itr) {
        this.documento = documento;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
        this.departamento = departamento;
        this.genero = genero;
        this.localidad = localidad;
        this.mail = mail;
        this.telefono = telefono;
        this.itr = itr;
    }

    public Usuario(Integer documento, String usuario, String contrasenia, String apellidos, String nombres, Date fechaNacimiento, String mail, String telefono) {
        this.documento = documento;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
        this.mail = mail;
        this.telefono = telefono;
    }

    public Usuario(Integer documento, String usuario, String contrasenia, String apellidos, String nombres, Date fechaNacimiento, String mail, String telefono, Itr itr, Rol rol) {
        this.documento = documento;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
        this.mail = mail;
        this.telefono = telefono;
        this.itr = itr;
        this.rol = rol;
    }
    public Usuario(Integer id, Integer documento, String usuario, String contrasenia, String apellidos, String nombres, Date fechaNacimiento, String mail, String telefono, Itr itr) {
        this.id = id;
        this.documento = documento;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
        this.mail = mail;
        this.telefono = telefono;
        this.itr = itr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Itr getItr() {
        return itr;
    }

    public void setItr(Itr itr) {
        this.itr = itr;
    }
    
    
    public String getUTtipo() {
		return uTipo;
	}

	public void setUTipoo(String uTipo) {
		this.uTipo = uTipo;
	}
	
    public Boolean getValidado() {
		return validado;
	}

	public void setValidado(Boolean validado) {
		this.validado = validado;
	}
//
//    @XmlTransient
//    public Collection<Estudiante> getEstudianteCollection() {
//        return estudianteCollection;
//    }
//
//    public void setEstudianteCollection(Collection<Estudiante> estudianteCollection) {
//        this.estudianteCollection = estudianteCollection;
//    }
//
//    @XmlTransient
//    public Collection<Tutor> getTutorCollection() {
//        return tutorCollection;
//    }
//
//    public void setTutorCollection(Collection<Tutor> tutorCollection) {
//        this.tutorCollection = tutorCollection;
//    }
//
//    @XmlTransient
//    public Collection<Analista> getAnalistaCollection() {
//        return analistaCollection;
//    }
//
//    public void setAnalistaCollection(Collection<Analista> analistaCollection) {
//        this.analistaCollection = analistaCollection;
//    }

    @Transient
    public String getDecriminatorValue() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", documento=" + documento +
                ", usuario='" + usuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nombres='" + nombres + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", departamento='" + departamento + '\'' +
                ", genero='" + genero + '\'' +
                ", localidad='" + localidad + '\'' +
                ", mail='" + mail + '\'' +
                ", telefono='" + telefono + '\'' +
                ", itr=" + itr +
                '}';
    }
    
}
