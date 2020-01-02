package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

/**
 * Classe d'une case du plateau
 */
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
	}

	//Méthodes

	/**
	 * Retoune les occupants de la case
	 * @return
	 */
	private ArrayList<Navire> getOccupants() {
		return this.occupants;
	}

	/**
	 * Ajoute un occupant à la case
	 * @param occupant
	 */
	public void addOccupant(Navire occupant) {
		if(canIAdd(occupant.getType())){
			if(this.estPleine()){
				try {
					throw new OccupationException(this.x, this.y);
				} catch (OccupationException e) {
					e.printStackTrace();
				}
			}
			this.occupants.add(occupant);
			occupant.setPosition(x,y);
		}
	}

	/**
	 *
	 */
	public void attaque(){
		for(Navire n : this.getOccupants()){
			n.setDegat();
		}
	}

	/**
	 * Retourne true si la case est occupee par au moins 1 navire
	 * @return
	 */
	public boolean estOccupee() {
		return !this.occupants.isEmpty();
	}

	/**
	 * Retoune true si la case est vide
	 * @return
	 */
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

	/**
	 * Retoune vrai si on peut ajouter un bateau de type 'type' sur la case
	 * @param type
	 * @return
	 */
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

	/**
	 * Retoune true si la case est pleine
	 * @return
	 */
	public boolean estPleine() {
		return this.occupants.size() == 2;
	}

	/**
	 * Enleve un occupant de la case
	 * @param n
	 */
	public void removeOccupant(Navire n) {
		this.occupants.remove(n);
	}

	/**
	 * Retourne true si la case est barrée
	 * @return
	 */
	public boolean isCross() {
		return isCross;
	}

	/**
	 * barre la case si cross=true
	 * @param cross
	 */
	public void setCross(boolean cross) {
		isCross = cross;
	}

	/**
	 * Update des occupants de la case
	 */
	private void update(){
		for(int i=0; i<this.getOccupants().size(); i++){
			if(this.getOccupants().get(i).position.getX() != this.x && this.getOccupants().get(i).position.getY() != this.y){
				this.removeOccupant(this.getOccupants().get(i));
			}
			if(this.getOccupants().get(i).estCoule()){
				this.removeOccupant(this.getOccupants().get(i));
			}
		}
	}

	/**
	 * Méthode loop de processing permettant de dessiner la case sur la fenetre
	 * @see MainClass
	 * @param x
	 * @param y
	 */
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
				img = sketch.loadImage("ressources/icons/destroyer.png");
				sketch.image(sketch.loadImage("ressources/icons/"+n.getEtat()+"_heart.png"), x, y);
			}
			if (n.getType() == TypeNav.CHALUTIER) {
				img = sketch.loadImage("ressources/icons/chalutier.png");
				sketch.image(sketch.loadImage("ressources/icons/"+n.getEtat()+"_heart.png"), x, y);
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
