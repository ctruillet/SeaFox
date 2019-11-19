package eu.ctruillet.upssitech.sri.tp5;

import java.util.ArrayList;

public class Jeu {
	//Attribut
	private Plateau plateau;
	private ArrayList<Joueur> listeJoueur;
	private boolean fini;

	//Constructeur
	public Jeu(int taille){
		this.plateau = new Plateau(taille);
		this.listeJoueur = new ArrayList<Joueur>();
		this.fini = false;
	}

	//Méthodes
	public void jouer(){
		//ToDO
	}

	public void choixJoueurs(){
		//ToDO
	}

	public String toString(){
		//ToDO
		return "";
	}

	public void attributionNavire(){
		//ToDO
	}

	public void positionnementNavire(){
		//ToDO
	}

	private void majJeuAvCommande(Commande cmd){
		//ToDO
	}

	private void majJeuCasTir(Commande cmd){
		//ToDO
	}

	private void majJeuCasPeche(Commande cmd){
		//ToDO
	}

	private boolean majListeNavite(Navire n){
		//ToDO
		return true;
	}

	private void majJeuCasDeplacement(Commande cmd){
		//ToDO
	}

	public static void main(String[] args) {

	}

}
