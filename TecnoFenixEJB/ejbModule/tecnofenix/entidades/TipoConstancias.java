package tecnofenix.entidades;

public enum TipoConstancias {
	SIN_SELECCIONAR(""),
	PRESENCIAL_COMUN("Constancia asistencia presencial com�n"),
	PRESENCIAL_PRUEBA("Constancia asistencia presencial prueba"),
	TRANSPORTE("Constancia de transporte"),
	ESTUDIANTE_ACTIVO("Constancia de estudiante activo"),
	EXAMENES("Constancia de ex�menes"),
	CREDITO_VME("Constancia de cr�ditos VME"),
	CREDITO_UTECinnova("Constancia de cr�ditos UTEC innova"),
	CREDITO_Optativas("Constancia de cr�ditos optativas");
	
	
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
