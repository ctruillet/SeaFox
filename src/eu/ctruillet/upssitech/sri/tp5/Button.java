package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

import java.awt.*;

public class Button {
	protected PApplet sketch;
	protected PVector[] forme = new PVector[4];
	protected int x, y; //Coordonnées
	protected int l; //Largeur
	protected String text;
	protected boolean isClicked;
	protected PShape b;
	protected Color colorH, colorT;


	public Button(PApplet sketch, int x, int y, int l, String texte){
		this.sketch=sketch;

		this.x=x;
		this.y=y;
		this.text=texte;
		this.isClicked=false;
		this.colorH = new Color(213, 253, 254,255); //Couleur de fond
		this.colorT = new Color(189, 74, 35);  //Couleur de la police
		this.colorH.getRGB();

		// Créer la forme
		//forme[0] = new PVector( (float)(l),0);
		forme[0] = new PVector((float) (l*Math.cos(Math.PI/6)), (float)(l*Math.sin(Math.PI/6)));
		forme[1] = new PVector((float) (-l*Math.cos(Math.PI/6)), (float)(l*Math.sin(Math.PI/6)));
		//forme[3] = new PVector((float)-l, 0);
		forme[2] = new PVector((float) (-l*Math.cos(Math.PI/6)), (float)(-l*Math.sin(Math.PI/6)));
		forme[3] = new PVector((float) (l*Math.cos(Math.PI/6)), (float)(-l*Math.sin(Math.PI/6)));
		//forme[6] = new PVector((float)(l*Math.cos(Math.PI/6)), (float)(l*Math.sin(Math.PI/6)));

		this.b=recreateShape(forme);
	}

	void update() {
		b.setFill(this.colorH.getRGB());
		sketch.shape(this.b,this.x,this.y);
		sketch.fill(this.colorT.getRGB());
		sketch.textAlign(sketch.CENTER,sketch.CENTER);
		sketch.text(this.text,this.x,this.y);
		sketch.fill(0);
	}

	void update(int x, int y) { // move the form at the current position
		this.x = x;
		this.y = y;
		this.update();
	}

	public boolean onClick(int x, int y){
		if(containsPoint(forme,x-this.x,y-this.y)) {
			this.isClicked=true;
			b.scale((float)0.8);
			return true;
		}
		return false;
	}

	public void onReleased(){
		if((containsPoint(forme,x-this.x,y-this.y)) && (this.isClicked)) {
			this.isClicked=false;
		}
		this.b = recreateShape(forme);
	}

	private PShape recreateShape(PVector[] _f){
		PShape h = sketch.createShape();
		h.beginShape();
		for(PVector v : _f) {
			h.vertex(v.x,v.y);
		}
		h.endShape(sketch.CLOSE);
		return(h);
	}

	private boolean containsPoint(PVector[] verts, float px, float py){
		int num = verts.length;
		int i, j = num - 1;
		boolean oddNodes = false;
		for (i = 0; i < num; i++) {
			PVector vi = verts[i];
			PVector vj = verts[j];
			if (vi.y < py && vj.y >= py || vj.y < py && vi.y >= py) {
				if (vi.x + (py - vi.y) / (vj.y - vi.y) * (vj.x - vi.x) < px) {
					oddNodes = !oddNodes;
				}
			}
			j = i;
		}
		return oddNodes;
	}

}

