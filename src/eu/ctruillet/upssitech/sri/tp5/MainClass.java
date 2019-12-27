package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import java.util.ArrayList;

public class MainClass extends PApplet {
	public static PApplet processing;
	private PImage baniere;
	private PImage map;
	private final static String ICON = "../doc/icon.png";
	private final static String TITLE = "SeaFox";

	public int caseX = -1, caseY = -1;

	public FSM state;
	public Jeu j;

	public ArrayList<Button> Buttons = new ArrayList<>();

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

		Button b = new Button(this, 737, 220, 45, "1 Joueur");
		Buttons.add(b);
		b = new Button(this, 825, 220, 45, "2 Joueurs");
		Buttons.add(b);
		b = new Button(this, 913, 220, 45, "3 Joueurs");
		Buttons.add(b);


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


		//System.out.println(this.mouseX + "," + this.mouseY );

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
				break;

			case PLACEMENT_BATEAU:

				break;

			case TOUR:

				break;

			case FIN:

				break;

			default:
				break;
		}

		for (Button b : Buttons) {
			b.update();
		}

		if (caseX != -1 && caseY != -1) text("Case " + caseX + ";" + caseY, 825, 187);


	}

	public void mousePressed() {
		//Mise à jour de la case où on est
		if ((mouseX - 15) / j.getPlateau().getTailleCase() <= 9 && (mouseX - 15) >= 0 && (mouseY - 15) / j.getPlateau().getTailleCase() <= 9 && (mouseY - 15) >= 0) {
			caseX = (mouseX - 15) / j.getPlateau().getTailleCase();
			caseY = (mouseY - 15) / j.getPlateau().getTailleCase();
		}

		for (Button b : Buttons) {
			if (b.onClick(mouseX, mouseY)) {
				System.out.println(b.text + " est pressé");
				this.state = FSM.PLACEMENT_BATEAU;
			}
		}


	}

	public void mouseReleased() {
		for (Button b : Buttons) {
			b.onReleased();
		}
	}

}
