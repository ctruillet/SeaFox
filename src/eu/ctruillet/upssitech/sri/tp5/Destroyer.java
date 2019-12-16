package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

public class Destroyer extends Navire {
	//Attributs

	//Constructeur
	public Destroyer(PApplet sketch, int i, int n){
		super(sketch, i, TypeNav.DESTROYER, n);
		this.etat = 2;
	}

	//Méthodes
	public String toString(){
		//ToDo
		return "";
	}
}
