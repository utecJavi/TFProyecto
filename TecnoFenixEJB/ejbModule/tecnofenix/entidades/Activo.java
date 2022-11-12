package tecnofenix.entidades;

import javax.persistence.Column;

public class Activo {

	
	@Column(name = "activo" )
	private Boolean activo;
	
	public Activo() {
		//TODO Auto-generated constructor stub
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	
}
