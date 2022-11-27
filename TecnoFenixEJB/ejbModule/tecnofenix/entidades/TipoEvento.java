package tecnofenix.entidades;

public enum TipoEvento {
	
	JORNADA_PRESENCIAL("Jornada presencial"), PRUEBA_FINAL("Prueba final"), EXAMEN("Examen"), DEFENSA_PROYECTO("Defensa de Proyecto");
	
	private String tipo;
	
	TipoEvento(String tipo) {
		this.setTipo(tipo);
	}

	public String getTipo() {
		return tipo;
		
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
		
	}
}
