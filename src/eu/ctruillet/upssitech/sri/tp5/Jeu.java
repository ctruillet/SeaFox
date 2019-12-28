package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

import java.util.ArrayList;

public class Jeu {
	//Attribut
	private PApplet sketch;
	private Plateau plateau;
	private ArrayList<Joueur> listeJoueur;
	private boolean fini;
	int tourJoueur;
	int nbJoueur;

	//Constructeur
	public Jeu(PApplet sketch, int taille) {
		this.sketch = sketch;
		this.plateau = new Plateau(this.sketch, taille);
		this.listeJoueur = new ArrayList<Joueur>();
		this.fini = false;
		this.tourJoueur=0; //On va du Joueur 0 à Joueur n-1 (n maximum 4)
	}

	//Méthodes
	public void jouer() {
		this.plateau.affichage();
		//ToDO
	}

	public void draw() {
		this.plateau.draw(15, 15);
	}

	public void choixJoueurs() {

		//ToDO
	}

	public String toString() {
		//ToDO
		return "";
	}

	public void attributionNavire() {
		//ToDO
	}

	public void positionnementNavire() {
		for(int i=0; i<this.nbJoueur; i++){
			MainClass.processing.text("Placement des bateaux du joueur " + i,825,200+20*i);
		}
		//ToDO
	}

	private void majJeuAvCommande(Commande cmd) {
		//ToDO
	}

	private void majJeuCasTir(Commande cmd) {
		//ToDO
	}

	private void majJeuCasPeche(Commande cmd) {
		//ToDO
	}

	private boolean majListeNavire(Navire n) {
		//ToDO
		return true;
	}

	private void majJeuCasDeplacement(Commande cmd) {
		//ToDO
	}

	public Plateau getPlateau() {
		return this.plateau;
	}

	public void addNewJoueur(Nature n) {
		Equipe e = new Equipe(this.sketch, n);
		Navire nav = new SousMarin(sketch, 0, 0);
		e.addNavire(nav);
		this.plateau.getCaseAt(3, 0).addOccupant(nav);

		nav = new Destroyer(sketch, 1, 2);
		e.addNavire(nav);
		this.plateau.getCaseAt(3, 1).addOccupant(nav);

		nav = new Chalutier(sketch, 1, 3);
		e.addNavire(nav);
		this.plateau.getCaseAt(5, 3).addOccupant(nav);

		this.listeJoueur.add(e);


	}

	public void nextTurn(){
		this.tourJoueur=(this.tourJoueur+1)%this.listeJoueur.size();
	}

	public int getTourJoueur() {
		return tourJoueur;
	}

	public int getNbJoueur() {
		return nbJoueur;
	}

	public void setNbJoueur(int nbJoueur) {
		this.nbJoueur = nbJoueur;
	}
}
