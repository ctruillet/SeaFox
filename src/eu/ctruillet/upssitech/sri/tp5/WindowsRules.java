package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

/** Classe d'affichage du bouton de regles
 * Utilise principalement des méthodes de processing
 */
public class WindowsRules extends PApplet {
	//Attributs
	private boolean isVisible;

	//Constructeur
	public WindowsRules(){
		super();
		PApplet.runSketch(new String[] {"SeaFox - Règles"}, this);
		noLoop();
		surface.setVisible(false);
		this.isVisible = false;
	}

	//Méthodes

	/**
	 * Spécifie les parametres de la fenetre avant sa construction
	 */
	public void settings(){
		size(320,640);
	}

	/**
	 * Specifie les parametres de la fenetre au moment de sa construction
	 */
	public void setup(){
		surface.setIcon(loadImage(MainClass.ICON));
		surface.setTitle("SeaFox - Règles");
		surface.setResizable(true);
	}

	/**
	 * Méthode loop qui dessine l'image des regles toutes les x fois par seconde
	 */
	public void draw(){
		this.image(this.loadImage("ressources/icons/rules.png"),0,0);
	}

	/**
	 * Affichage de la fenetre
	 */
	public void setVisible() {
		if (!this.isVisible) {
			surface.setVisible(true);
			this.isVisible = true;
			System.out.println("Affichage des Regles");
		}
	}

	/**
	 * Surcharge de la méthode exit de PApplet
	 */
	public void exit(){
		this.isVisible = false;
		surface.setVisible(false);

	}
}
