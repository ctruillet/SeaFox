package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

import java.util.ArrayList;

public class CasePlateau {
	//Attributs
	private ArrayList<Navire> occupants = new ArrayList<Navire>();
	private PApplet sketch;

	//Constructeur
	public CasePlateau(PApplet sketch){
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
		System.out.println("Debut dessin à"+x+";"+y+"\n");
		sketch.square(x,y,10);
	}


}
