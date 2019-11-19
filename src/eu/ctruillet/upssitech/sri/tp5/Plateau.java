package eu.ctruillet.upssitech.sri.tp5;

public class Plateau {
	//Attributs
	private int taille;
	private CasePlateau[][] matrice;

	//Constructeur
	public Plateau(int taille){
		this.taille = taille;
		this.matrice = new CasePlateau[this.taille][this.taille];
	}
	//Méthodes
	public void affichage(){
		//ToDO
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

}
