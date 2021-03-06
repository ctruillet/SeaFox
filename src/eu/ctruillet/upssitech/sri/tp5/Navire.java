package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

import java.awt.*;

/**
 * Classe abstraite Navire
 */
public abstract class Navire {
	//Attributs
	protected PApplet sketch;
	protected int id;
	protected TypeNav myType;
	protected int numEq;
	protected int etat; // 0 = Coule
	protected int portee;
	protected int vitesse;
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

	public boolean estValide() {
		return this.etat != 0;
	}

	public boolean estCoule() {
		return this.etat == 0;
	}

	/**
	 * Retoune true si le navire peut attaquer sur la case x;y
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isTirIsOK(int x, int y){
		int d = (int)Math.sqrt(Math.pow(this.position.getX() - x,2) + Math.pow(this.position.getY() - y,2));
		//System.out.println("Distance = " + d + "\tPortee = " + this.getPortee());
		return d <= this.getPortee() && !(x == (int) this.getPosition().getX() && y == (int) this.getPosition().getY());
	}

	/**
	 * Retoune true si la navire peut se deplacer sur la case x;y
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isDeplacementIsOK(int x, int y){
		if(x<0 || x>9 || y<0 || y>9) return false;
		int d = (int)Math.sqrt(Math.pow(this.position.getX() - x,2) + Math.pow(this.position.getY() - y,2));
		//System.out.println("Distance = " + d + "\tPortee = " + this.getPortee());
		return d <= this.getVitesse() && MainClass.j.getPlateau().getCaseAt(x, y).canIAdd(this.getType());
	}

	public int getPortee() {
		return portee;
	}

	public void setCoule() {
		this.etat=0;
	}

	public int getVitesse() {
		return vitesse;
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
				", vitesse=" + vitesse +
				", position=(" + (int)position.getX() + ";" + (int)position.getY() +")" +
				"}\n\t\t\t\t\t\t\t\t\t\t ";
	}

	/**
	 * Méthode abstraite d'affichage
	 * @see Chalutier
	 * @see Destroyer
	 * @see SousMarin
	 */
	public abstract void afficher();

	public void update(){
		this.setPeutAttaquer(true);
		this.setPeutSeDeplacer(true);
	}
}
