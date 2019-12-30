package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

public class Destroyer extends Navire {
	//Attributs

	//Constructeur
	public Destroyer(PApplet sketch, int i, int n) {
		super(sketch, i, TypeNav.DESTROYER, n);
		this.etat = 2;
		this.portee=2;
	}

	//Méthodes

	@Override
	public void afficher() {
		sketch.tint((this.getNumEq() == 0 ? 255 : (this.getNumEq() == 3 ? 255 : 0)), (this.getNumEq() == 1 ? 255 : (this.getNumEq() == 3 ? 255 : (this.getNumEq() == 2 ? 100 : 0))), (this.getNumEq() == 2 ? 225 : 0));
		sketch.image(sketch.loadImage("../doc/destroyer_32x32.png"), 665, 353);

		sketch.textAlign(sketch.LEFT);
		sketch.textSize(20);

		sketch.text("Destroyer (" + this.etat + " vie" + (this.etat>1 ? "s" : "") +")",705,380);
		sketch.textAlign(sketch.CENTER);
		sketch.noTint();
		sketch.textSize(14);

	}
}
