package eu.ctruillet.upssitech.sri.tp5;

import java.awt.*;

public abstract class Navire {
	//Attributs
	protected int id;
	protected TypeNav myType;
	protected String strAffichage;
	protected int numEq;
	protected int etat;
	protected int portee;
	protected int vitesse;
	protected Point position;

	//Constructeur
	public Navire(int i, TypeNav t, int n){
		this.id = i;
		this.myType = t;
		this.numEq = n;
	}
	//Méthodes
	public int getId() {
		return id;
	}

	public int getNumEq() {
		return numEq;
	}

	public Point getPosition(){
		return this.position;
	}

	public void setPosition(Point p) {
		this.position = p;
	}

	public TypeNav getType() {
		return this.myType;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public boolean estValide(){
		//ToDo
		return true;
	}

	public boolean estCoule(){
		//ToDo
		return true;
	}
	public void seDeplacer(Point p){
		//ToDo
	}

	public int getPortee() {
		return portee;
	}

	public void setCoule() {
		//ToDO
	}

	public String toString(){
		//ToDo
		return "";
	}

	public int getVitesse(){
		return this.vitesse;
	}

}
