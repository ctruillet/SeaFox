package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

public class SousMarin extends Navire {
	//Attributs

	//Constructeur
	public SousMarin(PApplet sketch, int i, int n){
		super(sketch, i, TypeNav.SOUSMARIN, n);
		this.etat = 1;
		this.portee=3;
	}

	//Méthodes

	@Override
	public void afficher() {
		sketch.tint((this.getNumEq() == 0 ? 255 : (this.getNumEq() == 3 ? 255 : 0)), (this.getNumEq() == 1 ? 255 : (this.getNumEq() == 3 ? 255 : (this.getNumEq() == 2 ? 100 : 0))), (this.getNumEq() == 2 ? 225 : 0));
		sketch.image(sketch.loadImage("ressources/icons/sousmarin_32x32.png"), 665, 473);

		sketch.textAlign(sketch.LEFT);
		sketch.textSize(20);

		sketch.text("Sous-Marin (" + this.etat + " vie" + (this.etat>1 ? "s" : "") +")",705,500);
		sketch.textAlign(sketch.CENTER);
		sketch.noTint();
		sketch.textSize(14);
	}
}
