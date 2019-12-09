package eu.ctruillet.upssitech.sri.tp5;

public class Chalutier extends Navire{
	//Attributs

	//Constructeur
	public Chalutier(int i, int n){
		super(i, TypeNav.CHALUTIER, n);
		this.etat = 3;
		this.portee = 1;
	}
	//Méthodes
	public String toString(){
		//ToDO
		return "";
	}
}
