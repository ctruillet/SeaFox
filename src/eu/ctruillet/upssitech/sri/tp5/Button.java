package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

import java.awt.*;

/** Classe Button
 * Classe utilisant principalement les méthodes de Processing
 */
class Button {
	//Attributs
	private PApplet sketch;
	private PVector[] forme = new PVector[4];
	private int x;
	private int y; //Coordonnées
	private int l; //Largeur
	String text;
	private boolean isClicked;
	private PShape b;
	private Color colorH;
	private Color colorT;
	private int textSize;

	//Constructeur
	public Button(PApplet sketch, int x, int y, int l, String texte) {
		this.sketch = sketch;

		this.x = x;
		this.y = y;
		this.text = texte;
		this.textSize = PApplet.max(14,2*l/9);
		this.isClicked = false;
		this.colorH = new Color(213, 253, 254, 255); //Couleur de fond
		this.colorT = new Color(189, 74, 35);  //Couleur de la police
		this.colorH.getRGB();

		// Créer la forme
		forme[0] = new PVector((float) (l * Math.cos(Math.PI / 6)), (float) (l * Math.sin(Math.PI / 6)));
		forme[1] = new PVector((float) (-l * Math.cos(Math.PI / 6)), (float) (l * Math.sin(Math.PI / 6)));
		forme[2] = new PVector((float) (-l * Math.cos(Math.PI / 6)), (float) (-l * Math.sin(Math.PI / 6)));
		forme[3] = new PVector((float) (l * Math.cos(Math.PI / 6)), (float) (-l * Math.sin(Math.PI / 6)));

		this.b = recreateShape(forme);
	}

	//méthodes

	/**
	 *
	 */
	void update() {
		b.setFill(this.colorH.getRGB());
		sketch.shape(this.b, this.x, this.y);
		sketch.fill(this.colorT.getRGB());
		sketch.textAlign(sketch.CENTER, sketch.CENTER);
		sketch.textSize(this.textSize);
		sketch.text(this.text, this.x, this.y);
		sketch.fill(0);
	}

	@Override
	public String toString() {
		return "Button{" +
				"x=" + x +
				", y=" + y +
				", l=" + l +
				", text='" + text + '\'' +
				", isClicked=" + isClicked +
				'}';
	}

	/**
	 * Retourne True si le bouton vient d'être cliqué
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean onClick(int x, int y) {
		if (containsPoint(forme, x - this.x, y - this.y)) {
			this.isClicked = true;
			b.scale((float) 0.9);
			return true;
		}
		return false;
	}

	/**
	 *
	 */
	public void onReleased() {
		if ((containsPoint(forme, 0, 0)) && (this.isClicked)) {
			this.isClicked = false;
		}
		this.b = recreateShape(forme);
	}

	/**
	 *
	 * @param _f
	 * @return
	 */
	private PShape recreateShape(PVector[] _f) {
		PShape h = sketch.createShape();
		h.beginShape();
		for (PVector v : _f) {
			h.vertex(v.x, v.y);
		}
		h.endShape(sketch.CLOSE);
		return (h);
	}

	/**
	 * Retourne True si le point px;py est dans le bouton
	 * @param verts
	 * @param px
	 * @param py
	 * @return
	 */
	private boolean containsPoint(PVector[] verts, float px, float py) {
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

	/**
	 * Retourne le texte du bouton
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * Change le texte du bouton
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}
}

