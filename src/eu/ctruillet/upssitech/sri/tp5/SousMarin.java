package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

public class SousMarin extends Navire {
	//Attributs

	//Constructeur
	public SousMarin(PApplet sketch, int i, int n){
		super(sketch, i, TypeNav.SOUSMARIN, n);
		this.etat = 1;
	}

	//Méthodes

	public String toString(){
		//ToDo
		return "";
	}
}
