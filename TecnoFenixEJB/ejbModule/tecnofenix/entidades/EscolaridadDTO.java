package tecnofenix.entidades;

import java.io.Serializable;
import java.util.Date;

public class EscolaridadDTO implements Serializable {

	private String evento;
	private String tipo;
	private String modalidad;
	private Date fecha;
	private String itr;
	private int calificacion;
	
	public EscolaridadDTO() {}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getItr() {
		return itr;
	}

	public void setItr(String itr) {
		this.itr = itr;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
}
