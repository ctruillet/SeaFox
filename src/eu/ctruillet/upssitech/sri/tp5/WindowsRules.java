package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

public class WindowsRules extends PApplet {
	private boolean isVisible;

	public WindowsRules(){
		super();
		PApplet.runSketch(new String[] {"SeaFox - Règles"}, this);
		noLoop();
		surface.setVisible(false);
		this.isVisible = false;
	}

	public void settings(){
		size(320,640);

	}

	public void setup(){
		surface.setIcon(loadImage(MainClass.ICON));
		surface.setTitle("SeaFox - Règles");
		surface.setResizable(true);
	}

	public void draw(){
		this.image(this.loadImage("ressources/icons/rules.png"),0,0);
	}

	public void setVisible() {
		if (!this.isVisible) {
			surface.setVisible(true);
			this.isVisible = true;
			System.out.println("Affichage des Regles");
		}
	}

	public void exit(){
		this.isVisible = false;
		surface.setVisible(false);

	}
}
