package tecnofenix.entidades;

public enum Departamentos {

	SIN_SELECCIONAR(""),ARTIGAS("Artigas"), CANELONES("Canelones"), CERRO_LARGO("Cerro Largo"), COLONIA("Colonia"), DURAZNO("Durazno"),
	FLORES("Flores"), FLORIDA("Florida"), LAVALLEJA("Lavalleja"), MALDONADO("Maldonado"), MONTEVIDEO("Montevideo"),
	PAYSANDU("Paysand�"), RIO_NEGRO("R�o Negro"), RIVERA("Rivera"), ROCHA("Rocha"), SALTO("Salto"),
	SAN_JOSE("San Jos�"), TACUAREMBO("Tacuaremb�"), TREINTA_Y_TRES("Treinta y Tres");

	private String depto;

	private Departamentos(String depto) {
		this.depto = depto;
	}

	String verDepto() {
		return depto;
	}

	  public static Departamentos fromString(String text) {
	        for (Departamentos d : Departamentos.values()) {
	            if (d.depto.equalsIgnoreCase(text)) {
	                return d;
	            }
	        }
	        return null;
	    }
	
	@Override
	public String toString() {
		return this.depto;
	}
}
