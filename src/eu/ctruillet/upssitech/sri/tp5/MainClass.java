package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;
import processing.core.PImage;

public class MainClass extends PApplet {
	public static PApplet processing;
	public PImage baniere;
	public PImage map;
	public int caseX=-1, caseY=-1;
	private Jeu j;
	public static void main(String[] args) {
		PApplet.main("eu.ctruillet.upssitech.sri.tp5.MainClass", args);
	}

	public void setup(){
		processing = this;
		this.j = new Jeu(this, 10);
	}

	public void settings(){
		baniere = loadImage("../doc/baniere.png");
		map = loadImage("../doc/map.png");

		size(1000,800);
	}

	public void draw(){
		textAlign(CENTER);
		this.background(35,109,122);

		//Affichage de la carte
		image(map,5,5);

		//Affichage du jeu
		j.draw();

		fill(119,224,255);
		noStroke();

		//Meny
		rect(650,0,350,800);

		//Affichage de la baniere
		baniere.resize(1000-650,175);
		image(baniere,650,0);
		stroke(0);
		line(650,0,650,height);
		noFill();
		fill(0);

		if(caseX!=-1 && caseY!=-1)	text("Case " + caseX + ";"+caseY,825,200);


	}

	public void mousePressed(){
		//Mise à jour de la case où on est
		if((mouseX-15)/j.getPlateau().getTailleCase() <= 9 && (mouseX-15)>=0 && (mouseY-15)/j.getPlateau().getTailleCase() <= 9 && (mouseY-15)>= 0){
			caseX = (mouseX-15)/j.getPlateau().getTailleCase();
			caseY = (mouseY-15)/j.getPlateau().getTailleCase();
		}
	}

}
