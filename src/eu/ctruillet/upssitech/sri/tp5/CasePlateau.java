package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class CasePlateau {
	//Attributs
	private ArrayList<Navire> occupants = new ArrayList<Navire>();
	private int tailleCase;
	private PApplet sketch;

	//Constructeur
	public CasePlateau(PApplet sketch, int tailleCase) {
		this.tailleCase = tailleCase;
		this.sketch = sketch;
		//ToDo
	}

	//Méthodes
	public ArrayList<Navire> getOccupants() {
		return this.occupants;
	}

	public void addOccupant(Navire occupant) {
		//ToDo
		this.occupants.add(occupant);
	}

	public String toString() {
		//ToDo
		return "";
	}

	public boolean estOccupee() {
		return !this.occupants.isEmpty();
	}

	public boolean estVide() {
		return this.occupants.isEmpty();
	}

	public boolean estPleine() {
		return this.occupants.size() == 2;
	}

	public String affichage() {
		String s = "[";
		for (Navire e : this.occupants) {
			s += e.toString() + ";";
		}
		return s + "]";
	}

	public void removeOccupant(Navire n) {
		this.occupants.remove(n);
	}

	public void draw(int x, int y) {
		sketch.stroke(181, 128, 87);
		sketch.square(x, y, this.tailleCase);
		for (Navire n : this.getOccupants()) {
			PImage img = sketch.loadImage("../doc/default.png");
			if (n.getType() == TypeNav.SOUSMARIN) {
				img = sketch.loadImage("../doc/sousmarin.png");
			}
			if (n.getType() == TypeNav.DESTROYER) {
				img = sketch.loadImage("../doc/destroyer.png");
			}
			if (n.getType() == TypeNav.CHALUTIER) {
				img = sketch.loadImage("../doc/chalutier.png");
			}

			//Teinte de couleur suivant l'équipe
			/*
			 * ROUGE : 0
			 * VERT  : 1
			 * BLEU  : 2
			 * JAUNE : 3
			 */
			sketch.tint((n.getNumEq() == 0 ? 255 : (n.getNumEq() == 3 ? 255 : 0)), (n.getNumEq() == 1 ? 255 : (n.getNumEq() == 3 ? 255 : (n.getNumEq() == 2 ? 100 : 0))), (n.getNumEq() == 2 ? 225 : 0));
			sketch.image(img, x, y);
			sketch.noTint();
		}
	}


}
