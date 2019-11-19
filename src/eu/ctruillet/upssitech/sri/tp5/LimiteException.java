package eu.ctruillet.upssitech.sri.tp5;

public class LimiteException extends Exception {
	public LimiteException(int i, int j){
		super("Case hors du plateau ["+i+","+j+"]");
	}
}
