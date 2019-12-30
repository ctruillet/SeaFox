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

	public void afficher(){
		sketch.tint((this.getNumEq() == 0 ? 255 : (this.getNumEq() == 3 ? 255 : 0)), (this.getNumEq() == 1 ? 255 : (this.getNumEq() == 3 ? 255 : (this.getNumEq() == 2 ? 100 : 0))), (this.getNumEq() == 2 ? 225 : 0));
		sketch.image(sketch.loadImage("icons/chalutier_32x32.png"), 665, 233);

		sketch.textAlign(sketch.LEFT);
		sketch.textSize(20);

		sketch.text("Chalutier (" + this.etat + " vie" + (this.etat>1 ? "s" : "") +")",705,260);
		sketch.textAlign(sketch.CENTER);
		sketch.noTint();
		sketch.textSize(14);
	}
}
