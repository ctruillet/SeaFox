package eu.ctruillet.upssitech.sri.tp5;

import java.util.ArrayList;

public class CasePlateau {
	//Attributs
	private ArrayList<Navire> occupants;


	//Constructeur
	public CasePlateau(){
		//ToDo
	}

	//Méthodes
	public ArrayList<Navire> getOccupants(){
		return this.occupants;
	}

	public void addOccupant(Navire occupant){
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
		//ToDo
		return "";
	}

	public void removeOccupant(Navire n){
		this.occupants.remove(n);
	}


}
