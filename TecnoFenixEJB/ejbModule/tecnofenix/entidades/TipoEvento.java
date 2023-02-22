package tecnofenix.entidades;

public enum TipoEvento {
	
	SIN_SELECCIONAR(""),JORNADA_PRESENCIAL("Jornada presencial"), PRUEBA_FINAL("Prueba final"), EXAMEN("Examen"), DEFENSA_PROYECTO("Defensa de Proyecto");
	
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
	
	public static TipoEvento fromString(String text) {
		for (TipoEvento te : TipoEvento.values()) {
			if (te.tipo.equalsIgnoreCase(text)) {
				return te;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return this.tipo;
	}
}
