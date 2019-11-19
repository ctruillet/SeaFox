package eu.ctruillet.upssitech.sri.tp5;

public class OccupationException extends Exception {
	public OccupationException(int a, int o){
		super("Conflit d'occupation dans la case ["+a+","+o+"]");
	}
}
