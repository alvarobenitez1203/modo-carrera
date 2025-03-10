package ACT_1_2;

import java.util.Random;

public class Jugador implements IJugador {

	private static final int AUMENTO_ENTRENAMIENTO_BASE = 2;
	private static final int AUMENTO_ESTADISTICAS = 2;

	private String nombre, nacionalidad;
	private int edad, valoracionGeneral, precio;
	
	/**
	 * @param nombre
	 * @param nacionalidad
	 * @param edad
	 * @param valoracionGeneral
	 * @param precio
	 * @throws Exception
	 */
	public Jugador(String nombre, String nacionalidad, int edad, int valoracionGeneral, int precio)
			throws Exception {

		setNombre(nombre);
		setNacionalidad(nacionalidad);
		setEdad(edad);
		setValoracionGeneral(valoracionGeneral);
		setPrecio(precio);
	}

	// METODOS IMPLICITOS DE LA CLASE

	/**
	 * En este metodo se entrenara al jugador dependiendo de la estadistica que tenga su entrenador para aumentar las estadisticas.
	 * El entrenamiento puede salir bien o mal y a partir de esto se sumara o restara valor en las estadistica de los jugadores.
	 * @param aumentoEntrenador
	 * @return
	 * @throws Exception
	 */
	public boolean entrenarJugador(int aumentoEntrenador) throws Exception {

		boolean resultadoEntrenamiento = false;
		Random rand = new Random();
		int numeroAleatorio;

		numeroAleatorio = rand.nextInt(10 + 1);

		if ((aumentoEntrenador + AUMENTO_ENTRENAMIENTO_BASE) >= numeroAleatorio) {

			resultadoEntrenamiento = true;
			setValoracionGeneral(this.valoracionGeneral + AUMENTO_ESTADISTICAS);

		} else {

			setValoracionGeneral(this.valoracionGeneral - AUMENTO_ESTADISTICAS);

		}

		return resultadoEntrenamiento;
	}
	
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 */
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	/**
	 * @param nacionalidad
	 */
	private void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public int getEdad() {
		return edad;
	}

	/**
	 * En este metodo comprobamos que la edad del jugador no este fuera de los parametros señalizados.
	 * @param edad
	 * @throws Exception
	 */
	private void setEdad(int edad) throws Exception {

		if (edad <= 1 || edad > 100) {
			throw new Exception("Edad fuera de parámetros");
		}

		this.edad = edad;
	}

	public int getValoracionGeneral() {
		return valoracionGeneral;
	}

	/**
	 * En este metodo comprobamos que la valoracion general del jugador no este fuera de los parametros señalizados.
	 * @param valoracionGeneral
	 * @throws Exception
	 */
	public void setValoracionGeneral(int valoracionGeneral) throws Exception {

		if (valoracionGeneral <= 0) {
			throw new Exception("Valoracion fuera de parámetros");

		}

		if (valoracionGeneral > 99) {
			valoracionGeneral = 99;
		}

		this.valoracionGeneral = valoracionGeneral;
	}
	
	/**
	 * @param precio
	 */
	private void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getPrecio() {
		return precio;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + " || Nacionalidad: " + nacionalidad + " || Edad: " + edad
				+ " || Valoracion General: " + valoracionGeneral + " || Precio de mercado: " + precio + "\n";
	}

}