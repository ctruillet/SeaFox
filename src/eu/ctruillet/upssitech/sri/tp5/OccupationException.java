package eu.ctruillet.upssitech.sri.tp5;

/**
 * Exception levée lors d'une tentative d'occupation illégale d'une classe
 */
public class OccupationException extends Exception {
	public OccupationException(int a, int o) {
		super("Conflit d'occupation dans la case [" + a + "," + o + "]");
	}
}
