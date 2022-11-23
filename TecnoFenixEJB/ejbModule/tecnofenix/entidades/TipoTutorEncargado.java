package tecnofenix.entidades;

public enum TipoTutorEncargado {
	SIN_SELECCIONAR(""),ENCARGADO("Encargado"), TUTOR("Tutor");

	private String tutenc;

	private TipoTutorEncargado(String tutenc) {
		this.tutenc = tutenc;
	}

	String verTutenc() {
		return tutenc;
	}

	@Override
	public String toString() {
		return this.tutenc;
	}
}
