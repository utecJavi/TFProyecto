package tecnofenix.entidades;

public enum TipoTutorEncargado {
	SIN_SELECCIONAR("",0),ENCARGADO("Encargado",1), TUTOR("Tutor",2);

	private String tutenc;
	private Integer idTutor;

	private TipoTutorEncargado(String tutenc ,Integer idTutor) {
		this.tutenc = tutenc;
		this.idTutor=idTutor;
	}

	String verTutenc() {
		return tutenc;
	}
	
	Integer verIdTutEnc() {
		return idTutor;
	}
	
	public static TipoTutorEncargado fromString(String text) {
		for (TipoTutorEncargado tt : TipoTutorEncargado.values()) {
			if (tt.tutenc.equalsIgnoreCase(text)) {
				return tt;
			}
		}
		return null;
	}
	
	public static Integer getIdTipo(String text) {
		for (TipoTutorEncargado tt : TipoTutorEncargado.values()) {
			if (tt.tutenc.equalsIgnoreCase(text)) {
				return tt.idTutor;
			}
		}
		return null;
	}
	
	public static TipoTutorEncargado getIdTipo(Integer id) {
		for (TipoTutorEncargado g : TipoTutorEncargado.values()) {
			if (g.idTutor.equals(id)) {
				return g;
			}
		}
		return null;
	}
	
	
	@Override
	public String toString() {
		return this.tutenc;
	}
}
