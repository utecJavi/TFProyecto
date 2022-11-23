package tecnofenix.entidades;

public enum TipoConstancias {
	SIN_SELECCIONAR(""),
	PRESENCIAL_COMUN("Constancia asistencia presencial común"),
	PRESENCIAL_PRUEBA("Constancia asistencia presencial prueba"),
	TRANSPORTE("Constancia de transporte"),
	ESTUDIANTE_ACTIVO("Constancia de estudiante activo"),
	EXAMENES("Constancia de exámenes"),
	CREDITO_VME("Constancia de créditos VME"),
	CREDITO_UTECinnova("Constancia de créditos UTEC innova"),
	CREDITO_Optativas("Constancia de créditos optativas");
	
	
	private String constancia;

	private TipoConstancias(String constancia) {
		this.constancia = constancia;
	}

	String verConstancia() {
		return constancia;
	}

	@Override
	public String toString() {
		return this.constancia;
	}
	
	
}
