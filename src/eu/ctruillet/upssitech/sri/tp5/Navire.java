package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

import java.awt.*;

public abstract class Navire {
	//Attributs
	protected PApplet sketch;
	protected int id;
	protected TypeNav myType;
	protected int numEq;
	protected int etat; // 0 = Coule
	protected int portee;
	protected Point position;
	protected boolean peutAttaquer = true;
	protected boolean peutSeDeplacer = true;

	//Constructeur
	public Navire(PApplet sketch, int id, TypeNav t, int numEq) {
		this.sketch = sketch;
		this.id = id;
		this.myType = t;
		this.numEq = numEq;
		this.position = new Point();
	}

	//Méthodes
	public boolean isPeutAttaquer() {
		return peutAttaquer;
	}

	public void setPeutAttaquer(boolean peutAttaquer) {
		this.peutAttaquer = peutAttaquer;
	}

	public boolean isPeutSeDeplacer() {
		return peutSeDeplacer;
	}

	public void setPeutSeDeplacer(boolean peutSeDeplacer) {
		this.peutSeDeplacer = peutSeDeplacer;
	}

	public int getId() {
		return id;
	}

	public int getNumEq() {
		return numEq;
	}

	public Point getPosition() {
		return this.position;
	}

	public void setPosition(int x, int y) {
		this.position.setLocation(x,y);
	}

	public TypeNav getType() {
		return this.myType;
	}

	public int getEtat() {
		return etat;
	}

	public void setDegat(){
		this.etat=(this.etat-1<0 ? 0 : this.etat-1);
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public boolean estValide() {
		return this.etat != 0;
	}

	public boolean estCoule() {
		return this.etat == 0;
	}

	public void seDeplacer(int x, int y) {
		//ToDo
	}

	public int getPortee() {
		return portee;
	}

	public void setCoule() {
		this.etat=0;
	}

	@Override
	public String toString() {
		return this.getType() +
				"{" +
				"id=" + id +
				", myType=" + myType +
				", numEq=" + numEq +
				", etat=" + etat +
				", portee=" + portee +
				", position=(" + (int)position.getX() + ";" + (int)position.getY() +")" +
				"}\n\t\t\t\t\t\t\t\t\t\t ";
	}

	public abstract void afficher();

	public void update(){
		this.setPeutAttaquer(true);
		this.setPeutSeDeplacer(true);
	}
}
