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
	public CasePlateau(PApplet sketch, int tailleCase){
		this.tailleCase = tailleCase;
		this.sketch = sketch;
		//ToDo
	}

	//Méthodes
	public ArrayList<Navire> getOccupants(){
		return this.occupants;
	}

	public void addOccupant(Navire occupant){
		//ToDo
		this.occupants.add(occupant);
	}

	public String toString(){
		//ToDo
		return "";
	}

	public boolean estOccupee(){
		return !this.occupants.isEmpty();
	}

	public boolean estVide(){
		return this.occupants.isEmpty();
	}

	public boolean estPleine(){
		//ToDo
		return true;
	}

	public String affichage(){
		String s = "[";
		for (Navire e : this.occupants){
			s+=e.toString() + ";";
		}
		return s + "]";
	}

	public void removeOccupant(Navire n){
		this.occupants.remove(n);
	}

	public void draw (int x, int y){
		sketch.stroke(181,128,87);
		sketch.square(x,y,this.tailleCase);
		for(Navire n : this.getOccupants()){
			if(n.getType() == TypeNav.SOUSMARIN){
				PImage img = sketch.loadImage("../doc/sousmarin.png");
				sketch.tint((n.getNumEq() == 0 ? 255 : (n.getNumEq() == 3 ? 255 : 0)),(n.getNumEq() == 1 ? 255 : (n.getNumEq() == 3 ? 255 : 0)),(n.getNumEq() == 2 ? 200 : 0));
				sketch.image(img,x,y);
				sketch.noTint();
			}
		}
	}




}
