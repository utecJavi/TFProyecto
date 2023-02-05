package tecnofenix.entidades;

public enum TipoConstancias {
	SIN_SELECCIONAR(""),
	PRESENCIAL_COMUN("Constancia asistencia presencial comun"),
	PRESENCIAL_PRUEBA("Constancia asistencia presencial prueba"),
	TRANSPORTE("Constancia de transporte"),
	ESTUDIANTE_ACTIVO("Constancia de estudiante activo"),
	EXAMENES("Constancia de examenes"),
	CREDITO_VME("Constancia de creditos VME"),
	CREDITO_UTECINNOVA("Constancia de creditos UTEC innova"),
	CREDITO_OPTATIVAS("Constancia de creditos optativas");
	
	
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
