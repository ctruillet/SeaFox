package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

import java.util.ArrayList;

public class Jeu {
	//Attribut
	private PApplet sketch;
	private Plateau plateau;
	private ArrayList<Joueur> listeJoueur;
	private boolean fini;

	//Constructeur
	public Jeu(PApplet sketch, int taille){
		this.sketch = sketch;
		this.plateau = new Plateau(this.sketch, taille);
		this.listeJoueur = new ArrayList<Joueur>();
		this.fini = false;
	}

	//Méthodes
	public void jouer(){
		this.plateau.affichage();
		//ToDO
	}

	public void draw(){
		this.plateau.draw(15,15);
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

	public Plateau getPlateau(){
		return this.plateau;
	}

	public void addNewJoueur(Nature n){
		Equipe e = new Equipe(this.sketch,n);
		Navire nav = new SousMarin(sketch,0,0);
		e.addNavire(nav);
		this.listeJoueur.add(e);
		this.plateau.getCaseAt(3,2).addOccupant(nav);

	}

}
