package eu.ctruillet.upssitech.sri.tp5;

/**
 * Exception levée lors d'une tentative d'accés à une case hors du plateau
 */
public class LimiteException extends Exception {
	public LimiteException(int i, int j){
		super("Case hors du plateau ["+i+","+j+"]");
	}
}
