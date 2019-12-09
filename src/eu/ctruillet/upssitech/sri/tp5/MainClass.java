package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

public class MainClass extends PApplet {
	public static PApplet processing;
	private Jeu j;
	public static void main(String[] args) {
		PApplet.main("eu.ctruillet.upssitech.sri.tp5.MainClass", args);
	}

	public void setup(){
		processing = this;
		this.j = new Jeu(this, 5);
	}

	public void settings(){

		size(1000,800);
	}

	public void draw(){
		j.draw();
		line(650,0,650,height);
	}

}
