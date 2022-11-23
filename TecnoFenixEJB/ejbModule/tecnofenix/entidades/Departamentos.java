package tecnofenix.entidades;

public enum Departamentos {

	SIN_SELECCIONAR(""),ARTIGAS("Artigas"), CANELONES("Canelones"), CERRO_LARGO("Cerro Largo"), COLONIA("Colonia"), DURAZNO("Durazno"),
	FLORES("Flores"), FLORIDA("Florida"), LAVALLEJA("Lavalleja"), MALDONADO("Maldonado"), MONTEVIDEO("Montevideo"),
	PAYSAND�("Paysand�"), R�O_NEGRO("R�o Negro"), RIVERA("Rivera"), ROCHA("Rocha"), SALTO("Salto"),
	SAN_JOS�("San Jos�"), TACUAREMB�("Tacuaremb�"), TREINTA_Y_TRES("Treinta y Tres");

	private String depto;

	private Departamentos(String depto) {
		this.depto = depto;
	}

	String verDepto() {
		return depto;
	}

	@Override
	public String toString() {
		return this.depto;
	}
}
