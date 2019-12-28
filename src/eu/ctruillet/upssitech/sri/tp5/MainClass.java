package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import java.util.ArrayList;

public class MainClass extends PApplet {
	//Attributs
	public static PApplet processing;
	public static WindowsRules wr = new WindowsRules();
	protected final static String ICON = "../doc/icon.png";
	protected final static String TITLE = "SeaFox";


	protected int caseX = -1, caseY = -1;
	protected FSM state;
	protected Jeu j;
	protected ArrayList<Button> Buttons_ChoixNbJoueur = new ArrayList<>();
	protected ArrayList<Button> Buttons_Actions = new ArrayList<>();
	private PImage baniere;
	private PImage map;

	//Methodes
	public static void main(String[] args) {
		PApplet.main("eu.ctruillet.upssitech.sri.tp5.MainClass", args);
	}

	public void setup() {
		//Initialisation
		processing = this;
		changeAppTitle(TITLE);
		changeAppIcon(loadImage(ICON));
		surface.setResizable(false);


		this.state = FSM.INIT;

		createButtons_ChoixNbJoueur();
		createButtons_Actions();

		this.j = new Jeu(this, 10);

		this.state = FSM.CHOIX_NB_JOUEUR;

		j.addNewJoueur(Nature.HUMAIN);
	}

	public void settings() {
		baniere = loadImage("../doc/baniere.png");
		map = loadImage("../doc/map.png");
		size(1000, 800);
	}

	private void changeAppTitle(String title){
		surface.setTitle(title);
	}

	private void changeAppIcon(PImage img) {
		surface.setIcon(img);
	}

	public void draw() {
		textAlign(CENTER);
		this.background(35, 109, 122);

		//Affichage de la carte
		image(map, 5, 5);

		//Affichage du jeu
		if (this.state != FSM.INIT && this.state != FSM.CHOIX_NB_JOUEUR) {
			j.draw();
		}

		fill(119, 224, 255);
		noStroke();

		//Menu
		rect(650, 0, 350, 800);

		//Affichage de la baniere
		baniere.resize(1000 - 650, 175);
		image(baniere, 650, 0);
		stroke(0);
		line(650, 0, 650, height);
		noFill();
		fill(0);


		switch (this.state) {
			case INIT:
				break;

			case CHOIX_NB_JOUEUR:
				displayChoixNbJoueur();
				break;

			case PLACEMENT_BATEAU:

				break;

			case TOUR:
				displayActionButtons();
				break;

			case FIN:

				break;

			default:
				break;
		}



		if (caseX != -1 && caseY != -1) text("Case " + caseX + ";" + caseY, 825, 187);


	}

	public void mousePressed() {
		//Mise à jour de la case où on est
		if ((mouseX - 15) / j.getPlateau().getTailleCase() <= 9 && (mouseX - 15) >= 0 && (mouseY - 15) / j.getPlateau().getTailleCase() <= 9 && (mouseY - 15) >= 0) {
			caseX = (mouseX - 15) / j.getPlateau().getTailleCase();
			caseY = (mouseY - 15) / j.getPlateau().getTailleCase();
		}

		for (Button b : Buttons_ChoixNbJoueur) {
			if (b.onClick(mouseX, mouseY)) {
				if(b.getText().equals("1 Joueur")){ this.j.setNbJoueur(2); }
				if(b.getText().equals("2 Joueurs")){ this.j.setNbJoueur(3); }
				if(b.getText().equals("3 Joueurs")){ this.j.setNbJoueur(4); }

				System.out.println(this.j.getNbJoueur() + " Joueurs");

				this.state = FSM.PLACEMENT_BATEAU;
			}
		}

		for (Button b : Buttons_Actions) {
			if (b.onClick(mouseX, mouseY)) {
				System.out.println("\"" + b.text + "\"" + " est pressé");
				if(b.getText().equals("Regles")){ this.wr.setVisible(); }
				//this.state = FSM.PLACEMENT_BATEAU;
			}
		}


	}

	public void mouseReleased() {
		for (Button b : Buttons_ChoixNbJoueur) {
			b.onReleased();
		}

		for (Button b : Buttons_Actions) {
			b.onReleased();

		}
	}

	protected void createButtons_ChoixNbJoueur(){
		Button b;

		b = new Button(this, 737, 220, 45, "1 Joueur");
		Buttons_ChoixNbJoueur.add(b);

		b = new Button(this, 825, 220, 45, "2 Joueurs");
		Buttons_ChoixNbJoueur.add(b);

		b = new Button(this, 913, 220, 45, "3 Joueurs");
		Buttons_ChoixNbJoueur.add(b);
	}

	protected void createButtons_Actions(){
		Button b;

		//Actions du Chalutier
		b = new Button(this, 760, 300, 60, "Attaquer");
		Buttons_Actions.add(b);
		b = new Button(this, 890, 300, 60, "Deplacer");
		Buttons_Actions.add(b);

		//Actions du Destroyer
		b = new Button(this, 760, 420, 60, "Attaquer");
		Buttons_Actions.add(b);
		b = new Button(this, 890, 420, 60, "Deplacer");
		Buttons_Actions.add(b);

		//Actions du SousMarin
		b = new Button(this, 760, 540, 60, "Attaquer");
		Buttons_Actions.add(b);
		b = new Button(this, 890, 540, 60, "Deplacer");
		Buttons_Actions.add(b);

		//Actions Génerales
		b = new Button(this, 325, 720, 90, "Fin de Tour");
		Buttons_Actions.add(b);
		b = new Button(this, 50, 770, 40, "Regles");
		Buttons_Actions.add(b);

	}

	protected void displayChoixNbJoueur(){
		for (Button b : Buttons_ChoixNbJoueur) {
			b.update();
		}
	}

	protected void displayActionButtons(){
		for (Button b : Buttons_Actions) {
			b.update();
		}
	}



}
