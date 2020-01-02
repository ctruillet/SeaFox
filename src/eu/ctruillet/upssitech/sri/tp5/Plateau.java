package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

import java.util.Arrays;

/**
 * Classe du plateau regroupant l'integralité des cases
 * @see CasePlateau
 */
public class Plateau {
	//Attributs
	private PApplet sketch;
	private int taille;
	private int tailleCase;
	private CasePlateau[][] matrice;

	//Constructeur
	public Plateau(PApplet sketch, int taille) {
		this.sketch = sketch;
		this.taille = taille;
		this.tailleCase = (650 - 30) / taille;
		this.matrice = new CasePlateau[this.taille][this.taille];
		for (int i = 0; i < this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {
				this.matrice[i][j] = new CasePlateau(this.sketch, this.tailleCase,i,j);
			}
		}
	}

	//Méthodes
	public int getTaille() {
		return this.taille;
	}

	/**
	 * Renvoit la case de la matrice à la position i;j
	 * @param i
	 * @param j
	 * @return
	 */
	public CasePlateau getCaseAt(int i, int j) {
		return this.matrice[i][j];
	}

	/**
	 * Méthode loop de processing permettant d'afficher le plateau dans le jeu
	 * @see PApplet
	 * @param x
	 * @param y
	 */
	public void draw(int x, int y) {
		sketch.noFill();
		for (int i = 0; i < this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {
				this.matrice[i][j].draw(x + i * this.tailleCase, y + j * this.tailleCase);

			}
		}
	}

	/**
	 * Supprime les croix de toutes les cases barrées
	 */
	public void removeAllCross(){
		for (int i = 0; i < this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {
				if(this.getCaseAt(i,j).isCross()) this.getCaseAt(i,j).setCross(false);
			}
		}
	}

	@Override
	public String toString() {
		return "Plateau{" +
				"taille=" + taille +
				", tailleCase=" + tailleCase +
				", matrice=" + Arrays.toString(matrice) +
				'}';
	}

	/**
	 * Barre les cases qui sont pleines
	 * @param b
	 */
	public void setCrossAtCaseFull(boolean b){
		for (int i = 0; i < this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {
				if(this.getCaseAt(i,j).estPleine()) this.getCaseAt(i,j).setCross(b);
			}
		}
	}

	/**
	 * Affiche (b=true) une croix sur la case si elle ne peut pas acceuillir le navire n
	 * @param type
	 * @param b
	 */
	public void setCrossIfICantAdd(TypeNav type, boolean b){
		for (int i = 0; i < this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {
				if(!this.getCaseAt(i,j).canIAdd(type)) this.getCaseAt(i,j).setCross(b);
			}
		}
	}


	/**
	 * Renvoit true si x;y sont dans le plateau
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean onClick(int x, int y){
		return x >= 15 && x <= (15 + this.tailleCase * this.taille) && y >= 15 && y <= (15 + this.tailleCase * this.taille);
	}

	/**
	 * Renvoit l'indice i de la case à la position x dans la fenetre
	 * @param x
	 * @return
	 */
	public int getCaseXOnClick(int x){
		return (x - 15) / this.getTailleCase();
	}

	/**
	 * Renvoit l'indice j de la case à la position y dans la fenetre
	 * @param y
	 * @return
	 */
	public int getCaseYOnClick(int y){
		return (y - 15) / this.getTailleCase();
	}


	/**
	 * Renvoit la taille d'une case
	 * @return
	 */
	public int getTailleCase() {
		return tailleCase;
	}

}
