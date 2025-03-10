package ACT_1_2;

public class Entrenador implements IEntrenador {

	private String nombre, nacionalidad;
	private int edad, tasaExitoEntrenamiento;
	private final int TASA_MAX_ENT=7;
	
	/**
	 * @param nombreEntrenador
	 * @param nacionalidadEntrenador
	 * @param edadEntrenador
	 * @param estadisticaEntrenamiento
	 */
	public Entrenador(String nombreEntrenador, String nacionalidadEntrenador, int edadEntrenador,
			int estadisticaEntrenamiento) {

		setNombre(nombreEntrenador);
		setNacionalidad(nacionalidadEntrenador);
		setEdad(edadEntrenador);
		setTasaExitoEntrenamiento(estadisticaEntrenamiento);

	}

	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	/**
	 * @param edad
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	/**
	 * @param nacionalidad
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public int getTasaExitoEntrenamiento() {
		return tasaExitoEntrenamiento;
	}

	/** En el caso de que la tasa de exito de entrenamiento es mayor a la tasa maxima del entrenamiento,
	 *  la TEE toma el valor de la tasa maxima del entrenamiento
	 * 
	 * @param tasaExitoEntrenamiento
	 */
	public void setTasaExitoEntrenamiento(int tasaExitoEntrenamiento) {

		if (this.tasaExitoEntrenamiento > TASA_MAX_ENT) {
			tasaExitoEntrenamiento = TASA_MAX_ENT;
		}

		this.tasaExitoEntrenamiento = tasaExitoEntrenamiento;
	}

	@Override
	public String toString() {
		return "Entrenador Nombre:" + getNombre() + " || Nacionalidad:" + getNacionalidad() + " || Edad:" + getEdad()
				+ " || TEE:" + getTasaExitoEntrenamiento() + "";
	}

	
}
