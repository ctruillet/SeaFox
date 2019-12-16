package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;

public abstract class Navire {
	//Attributs
	private PApplet sketch;
	protected int id;
	protected TypeNav myType;
	protected String strAffichage;
	protected int numEq;
	protected int etat; // 0 = Coule
	protected int portee;
	protected int vitesse;
	protected Point position;

	//Constructeur
	public Navire(PApplet sketch, int i, TypeNav t, int n){
		this.sketch = sketch;
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
		return this.etat!=0;
	}

	public boolean estCoule(){
		return this.etat==0;
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
		return (this.id+";"+this.position+";"+this.numEq+";"+this.myType);
	}

	public int getVitesse(){
		return this.vitesse;
	}

}
