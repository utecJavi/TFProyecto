package tecnofenix.entidades;

public enum TipoGenero {
	SIN_SELECCIONAR("","0"),
	MASCULINO("Masculino","M"),
	FEMENINO("Femenino","F");
	
	
	private String genero;
	private String simbol;
	private TipoGenero(String genero,String simbol) {
		this.genero = genero;
		this.simbol =simbol;
	}

	String verGenero() {
		return genero;
	}
	String verSimbolo() {
		return simbol;
	}

	   public static TipoGenero fromString(String text) {
	        for (TipoGenero g : TipoGenero.values()) {
	            if (g.genero.equalsIgnoreCase(text)) {
	                return g;
	            }
	        }
	        return null;
	    }
	
	
	
	@Override
	public String toString() {
		return this.genero;
	}
	
}
