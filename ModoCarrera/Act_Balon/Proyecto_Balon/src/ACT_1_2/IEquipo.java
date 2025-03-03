package ACT_1_2;

public interface IEquipo {
	public void ficharJugador(Jugador nuevoJugador) throws Exception;

	/**
	 * @param jugadorVender
	 * @throws Exception
	 */
	public void venderJugador(Jugador jugadorVender) throws Exception;

	/**
	 * @return
	 * @throws Exception
	 */
	public String listarPlantilla() throws Exception;

	/**
	 * @throws Exception
	 */
	public void entrenarEquipo() throws Exception;

	/**
	 * @return
	 */
	public int getProbabilidadesGanar();

	/**
	 * @param jugadorTransferir
	 * @param equipoTransferir
	 * @throws Exception
	 */
	public void transeferirJugador(Jugador jugadorTransferir, Equipo equipoTransferir) throws Exception;
}
