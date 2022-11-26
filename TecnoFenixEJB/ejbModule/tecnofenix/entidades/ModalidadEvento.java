package tecnofenix.entidades;

public enum ModalidadEvento {
	
	VIRTUAL("Virtual"), PRESENCIAL("Presencial"), SEMI_PRESENCIAL("Semipresencial");
	
	private String modalidad;
	
	private ModalidadEvento(String modalidad) {
		this.setModalidad(modalidad);
	}

	public String getModalidad() {
		return modalidad;
		
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
		
	}
}
