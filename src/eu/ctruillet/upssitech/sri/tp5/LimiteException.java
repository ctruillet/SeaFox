package eu.ctruillet.upssitech.sri.tp5;

class LimiteException extends Exception {
	public LimiteException(int i, int j){
		super("Case hors du plateau ["+i+","+j+"]");
	}
}
