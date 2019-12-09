package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;


public class Plateau {
	//Attributs
	private PApplet sketch;
	private int taille;
	private CasePlateau[][] matrice;

	//Constructeur
	public Plateau(PApplet sketch, int taille){
		this.sketch = sketch;
		this.taille = taille;
		this.matrice = new CasePlateau[this.taille][this.taille];
		for(int i=0; i<this.taille; i++){
			for(int j=0; j<this.taille; j++){
				this.matrice[i][j] = new CasePlateau(this.sketch);
			}
		}
	}
	//Méthodes
	public void affichage(){
		for(int i=0; i<this.taille; i++){
			for(int j=0; j<this.taille; j++){
				System.out.println(this.matrice[i][j].affichage());
			}
			System.out.println("\n");
		}
	}

	public String toString(){
		//ToDO
		return "";
	}

	public int getTaille(){
		return this.taille;
	}

	public CasePlateau getCaseAt(int i, int j){
		return this.matrice[i][j];
	}

	public void draw(int x, int y){ //ToDO
		for(int i=0; i<this.taille; i++){
			for(int j=0; j<this.taille; j++){
				System.out.println(i+";"+j+"\n");
				this.matrice[i][j].draw(x + i*10,y+ j*10);

			}
		}
	}

}
