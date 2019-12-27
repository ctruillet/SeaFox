package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

public class Chalutier extends Navire {
	//Attributs

	//Constructeur
	public Chalutier(PApplet sketch, int i, int n) {
		super(sketch, i, TypeNav.CHALUTIER, n);
		this.etat = 3;
		this.portee = 1;
	}

	//Méthodes
	public String toString() {
		//ToDO
		return "";
	}
}
