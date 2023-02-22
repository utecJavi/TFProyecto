package tecnofenix.entidades;

public enum TipoTutorArea {
	SIN_SELECCIONAR("",0),
	CIENCIAS_SOCIALES("Ciencias sociales",1),
	CIENCIAS_NATURALES("Ciencias naturales",2),
	INGENIERIA_Y_TECNOLOGIA("Ingeniería y tecnología",3),
	ARTES_Y_HUMANIDADES("Artes y humanidades",4),
	CIENCIAS_DE_LA_SALUD("Ciencias de la salud",5),
	EDUCACION("Educación",6),
	NEGOCIOS_Y_ADMINISTRACION("Negocios y administración",7),
	CIENCIAS_DE_LA_COMPUTACIÓN_E_INFORMATICA("Ciencias de la computación e informática",8),
	DERECHO("Derecho",9),
	AGRICULTURA_Y_CIENCIAS_AMBIENTALES("Agricultura y ciencias ambientales",10);

	private String area;
	private Integer idArea;

	private TipoTutorArea(String area ,Integer idArea) {
		this.area = area;
		this.idArea=idArea;
	}

	String verArea() {
		return area;
	}
	
	Integer verIdArea() {
		return idArea;
	}
	
	public static TipoTutorArea fromString(String text) {
		for (TipoTutorArea tt : TipoTutorArea.values()) {
			if (tt.area.equalsIgnoreCase(text)) {
				return tt;
			}
		}
		return null;
	}
	
	public static Integer getIdArea(String text) {
		for (TipoTutorArea tt : TipoTutorArea.values()) {
			if (tt.area.equalsIgnoreCase(text)) {
				return tt.idArea;
			}
		}
		return null;
	}
	
	public static TipoTutorArea getIdArea(Integer id) {
		for (TipoTutorArea g : TipoTutorArea.values()) {
			if (g.idArea.equals(id)) {
				return g;
			}
		}
		return null;
	}
	
	
	@Override
	public String toString() {
		return this.area;
	}
}
