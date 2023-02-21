package tecnofenix.entidades;

public enum Departamentos {

	SIN_SELECCIONAR(""),ARTIGAS("Artigas"), CANELONES("Canelones"), CERRO_LARGO("Cerro Largo"), COLONIA("Colonia"), DURAZNO("Durazno"),
	FLORES("Flores"), FLORIDA("Florida"), LAVALLEJA("Lavalleja"), MALDONADO("Maldonado"), MONTEVIDEO("Montevideo"),
	PAYSANDÚ("Paysandú"), RÍO_NEGRO("Río Negro"), RIVERA("Rivera"), ROCHA("Rocha"), SALTO("Salto"),
	SAN_JOSÉ("San José"), TACUAREMBÓ("Tacuarembó"), TREINTA_Y_TRES("Treinta y Tres"), SORIANO("Soriano");

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
