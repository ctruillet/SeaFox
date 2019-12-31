package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class CasePlateau {
	//Attributs
	private ArrayList<Navire> occupants = new ArrayList<>();
	private int tailleCase;
	private PApplet sketch;
	private int x;
	private int y;
	private boolean isCross;

	//Constructeur
	public CasePlateau(PApplet sketch, int tailleCase, int x, int y) {
		this.tailleCase = tailleCase;
		this.sketch = sketch;
		this.x=x;
		this.y=y;
		this.isCross = false;
		//ToDo
	}

	//Méthodes
	private ArrayList<Navire> getOccupants() {
		return this.occupants;
	}

	public void addOccupant(Navire occupant) {
		if(canIAdd(occupant.getType())){
			this.occupants.add(occupant);
			occupant.setPosition(x,y);
		}
	}

	public void attaque(){
		for(Navire n : this.getOccupants()){
			n.setDegat();
		}
	}

	public boolean estOccupee() {
		return !this.occupants.isEmpty();
	}

	public boolean estVide() {
		return this.occupants.isEmpty();
	}

	@Override
	public String toString() {
		return "CasePlateau{" +
				"occupants=" + occupants +
				", tailleCase=" + tailleCase +
				", sketch=" + sketch +
				", x=" + x +
				", y=" + y +
				", isCross=" + isCross +
				'}';
	}

	public boolean canIAdd(TypeNav type){
		for(Navire n: this.occupants){
			if(n.getType() == TypeNav.SOUSMARIN){
				if(type==TypeNav.SOUSMARIN) return false;
			}else{
				if(type==TypeNav.DESTROYER || type==TypeNav.CHALUTIER) return false;
			}
		}
		return true;
	}

	public boolean estPleine() {
		return this.occupants.size() == 2;
	}

	public String affichage() {
		StringBuilder s = new StringBuilder("[");
		for (Navire e : this.occupants) {
			s.append(e.toString()).append(";");
		}
		return s + "]";
	}

	public void removeOccupant(Navire n) {
		this.occupants.remove(n);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isCross() {
		return isCross;
	}

	public void setCross(boolean cross) {
		isCross = cross;
	}

	/**
	 * Update des occupants de la case
	 */
	public void update(){
		for(int i=0; i<this.getOccupants().size(); i++){
			if(this.getOccupants().get(i).position.getX() != this.x && this.getOccupants().get(i).position.getY() != this.y){
				this.removeOccupant(this.getOccupants().get(i));
			}
			if(this.getOccupants().get(i).estCoule()){
				this.removeOccupant(this.getOccupants().get(i));
			}
		}
	}

	public void draw(int x, int y) {
		this.update();
		sketch.stroke(181, 128, 87);
		sketch.square(x, y, this.tailleCase);
		for (Navire n : this.getOccupants()) {
			PImage img = sketch.loadImage("ressources/icons/default.png");
			if (n.getType() == TypeNav.SOUSMARIN) {
				img = sketch.loadImage("ressources/icons/sousmarin.png");
			}
			if (n.getType() == TypeNav.DESTROYER) {
				img = sketch.loadImage("ressources/icons/destroyer_"+ n.getEtat() + ".png");
			}
			if (n.getType() == TypeNav.CHALUTIER) {
				img = sketch.loadImage("ressources/icons/chalutier_" + n.getEtat() + ".png");
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

		if(this.isCross){
			PImage cross = sketch.loadImage("ressources/icons/cross.png");
			sketch.image(cross, this.x*this.tailleCase+15, this.y*this.tailleCase+15);
		}
	}


}
