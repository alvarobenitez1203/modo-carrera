package ACT_1_2;

import java.util.LinkedList;
import java.util.Random;

public class Equipo implements IEquipo {

	private static final int MINIMO_JUGADORES = 15;
	private static final int NUMERO_JUGADORES = 20;

	private LinkedList<Jugador> jugadores;
	private String nombreEquipo;
	private int presupuesto;
	private Entrenador entrenador;
	private int annioFundacion, entrenamientosSemana = 0;

	
	/**
	 * @param nombreEquipo
	 * @param annioFundacion
	 * @param presupuesto
	 * @param jugadores
	 * @param entrenador
	 */
	public Equipo(String nombreEquipo, int annioFundacion, int presupuesto, LinkedList<Jugador> jugadores,
			Entrenador entrenador) {

		this.jugadores = jugadores;
		this.annioFundacion = annioFundacion;
		this.nombreEquipo = nombreEquipo;
		this.presupuesto = presupuesto;
		this.entrenador = entrenador;
	}

	
	
	/**
	 * En el caso de que el presupuesto del equipo al comprar el jugador sea menor o igual a 0,
	 * el metodo lanza la excepcion indicando que el presupuesto del equipo es insuficiente.
	 * 
	 * En el caso de que la plantilla del equipo es mayor al maximo de jugadores que dispone un equipo,
	 * el metodo lanza la excepcion indicando que el equipo esta al completo.
	 * 
	 * En el caso de que el jugador ya exista en el equipo, 
	 * el metodo lanza la excepcion indicando que el jugador ya esta en el equipo
	 * 
	 * @param nuevoJugador
	 * @throws Exception
	 */
	@Override
	public void ficharJugador(Jugador nuevoJugador) throws Exception {
		if ((this.presupuesto - nuevoJugador.getPrecio()) <= 0) {
			throw new Exception("El presupuesto del " + getNombreEquipo() + " es insuficiente (Presupuesto actual:"
					+ getPresupuesto() + "€)");
		}
		if (this.jugadores.size() >= NUMERO_JUGADORES) {
			throw new Exception("El equipo esta al completo");
		}
		if (this.jugadores.contains(nuevoJugador)) {
			throw new Exception("El jugador ya esta en el equipo");
		}

		setPresupuesto(this.presupuesto - nuevoJugador.getPrecio());

		jugadores.add(nuevoJugador);
	}

	
	
	/**
	 * 
	 * En este metodo, vendemos el jugador que se le pasa por parametro.
	 * En el caso de que el equipo tenga ya el minimo de jugadores disponibles, 
	 * el metodo lanzara una excepcion donde informara de que el equipo no puede tener menos jugadores
	 * que los minimos disponibles de un equipo.
	 * 
	 * En el caso de que el jugador ya exista en el equipo, 
	 * el metodo lanza la excepcion indicando que el jugador ya esta en el equipo
	 * 
	 * @param jugadorVender
	 * @throws Exception
	 */
	@Override
	public void venderJugador(Jugador jugadorVender) throws Exception {

		if ((this.jugadores.size()) <= MINIMO_JUGADORES) {
			throw new Exception("El numero de jugadores de un equipo no puede ser inferior a " + MINIMO_JUGADORES+"\n");
		}
		if (!this.jugadores.contains(jugadorVender)) {
			throw new Exception("El jugador no esta en el equipo\n");
		}

		setPresupuesto(this.presupuesto + jugadorVender.getPrecio());

		jugadores.remove(jugadorVender);

	}

	/**
	 * Realiza el entrenamiento de todos los jugadores, en el caso de que el entrenamiento 
	 * del jugador sea exitoso, se suma la cantidad a entrenamientosExitosos
	 * 
	 * @throws Exception
	 */
	@Override
	public void entrenarEquipo() throws Exception {

		boolean entrenamiento;
		int entrenamientosExitosos = 0;

		if (this.entrenamientosSemana < 2) {

			for (int i = 0; i < this.jugadores.size(); i++) {

				entrenamiento = this.jugadores.get(i).entrenarJugador(entrenador.getTasaExitoEntrenamiento());

				if (entrenamiento == true) {
					entrenamientosExitosos++;
				}

			}

			if (entrenamientosExitosos >= (this.jugadores.size() / 2)) {

				this.entrenador.setTasaExitoEntrenamiento(this.entrenador.getTasaExitoEntrenamiento() + 1);

			}

			this.entrenamientosSemana++;

		} else {
			throw new Exception("No se puede entrenar mas de 2 veces por semana");
		}

	}

	/**
	 * En este metodo, devolvemos la probabilidad de ganar el partido.
	 * Donde, mediante un bucle, obtenemos y sumamos la valoracion general de cada jugador y lo
	 * almacenamos en una variable valoracionFinal, donde se obtiene el porcentaje 
	 * mediante la valoracion media de todos los jugadores.
	 */
	@Override
	public int getProbabilidadesGanar() {

		int valoracionesTotales = 0, valoracionFinal;

		for (int i = 0; i < this.jugadores.size(); i++) {

			valoracionesTotales = +this.jugadores.get(i).getValoracionGeneral();

		}

		valoracionFinal = (valoracionesTotales / this.jugadores.size() / 100);

		return valoracionFinal;

	}

	/**
	 * @param presupuesto
	 */
	private void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}

	public LinkedList<Jugador> getJugadores() {
		return jugadores;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}

	/**
	 * @param entrenamientosSemana
	 */
	public void setEntrenamientosSemana(int entrenamientosSemana) {
		this.entrenamientosSemana = entrenamientosSemana;
	}

	/**
	 * En este metodo comprobamos que el jugador existe y que tienes el minimo de jugadores disponible.
	 * Si cumple ambas funciones el jugador se vendera y se sumara el dinero de su valor al presupuesto del
	 * equipo.
	 * 
	 * @param jugadorTransferir
	 * @param equipoTransferir
	 * @throws Exception
	 */
	@Override
	public void transeferirJugador(Jugador jugadorTransferir, Equipo equipoTransferir) throws Exception {

		if (!this.jugadores.contains(jugadorTransferir)) {

			throw new Exception("No existe tal jugador");

		}

		if (this.jugadores.size() <= MINIMO_JUGADORES) {
			throw new Exception("No se puede transeferir al jugador");
		}

		equipoTransferir.ficharJugador(jugadorTransferir);

		setPresupuesto(jugadorTransferir.getPrecio());

		this.jugadores.remove(jugadorTransferir);

	}

	/**
	 * En este metodo mostramos la plantilla del equipo. Mostramos el presupuesto, entrenador y jugadores.
	 * En caso de que no haya jugadores o entrenadores, tambien se mostrara.
	 * @throws Exception
	 */
	public String listarPlantilla() throws Exception {
		StringBuilder listaJugadores = new StringBuilder();

		if (this.jugadores.size() == 0) {
			throw new Exception("No hay jugadores");
		}

		if (this.entrenador == null) {

			throw new Exception("No hay entrenador");

		}

		listaJugadores.append("\nPresupuesto: " + getPresupuesto() + "€ \n\n");
		listaJugadores.append(this.entrenador + "\n\n");

		for (int i = 0; i < jugadores.size(); i++) {
			listaJugadores.append(this.jugadores.get(i).toString() + "\n");
		}
		return listaJugadores.toString();
	}

	/**
	 * En este metodo, se juega el partido contra el equipoContrincante.
	 * Se obtiene las probabilidades de ganar de dos equipos.
	 * Luego se elige un numero aleatoriamente de entre dicho sumatorio.
	 * Por ultimo, se comprobara que el numero elegido sea mayor a las probabilidades
	 * de ganar del equipo del usuario. Si lo es, la variable pasaRonda cambiara su valor
	 * a true, lo que indicara que el usuario pasa de ronda. De lo contrario el usuario no
	 * pasara de ronda.
	 * 
	 * @param equipoContrincante
	 * @return
	 */
	public boolean jugarPartido(Equipo equipoContrincante) {

		Random rand = new Random();
		int numeroAleatorio;
		boolean pasaRonda = false;

		numeroAleatorio = rand
				.nextInt((this.getProbabilidadesGanar() + equipoContrincante.getProbabilidadesGanar()) + 1);

		if (numeroAleatorio > this.getProbabilidadesGanar()) {

			pasaRonda = true;

		}

		return pasaRonda;

	}

	@Override
	public String toString() {
		return "Equipo [jugadores=" + jugadores + ", nombreEquipo=" + nombreEquipo + ", presupuesto=" + presupuesto
				+ "€, entrenador=" + entrenador + ", annioFundacion=" + annioFundacion + "";
	}

}
