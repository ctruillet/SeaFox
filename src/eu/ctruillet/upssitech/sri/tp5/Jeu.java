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
	int nbTour;
	int nbJoueur;

	//Constructeur
	public Jeu(PApplet sketch, int taille) {
		this.sketch = sketch;
		this.plateau = new Plateau(this.sketch, taille);
		this.listeJoueur = new ArrayList<Joueur>();
		this.fini = false;
		this.tourJoueur=0; //On va du Joueur 0 à Joueur n-1 (n maximum 4)
		this.nbTour=0;
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
		if(this.nbJoueur!=this.listeJoueur.size()){
			Equipe e;
			//Joueurs Humains
			for(int i=0; i<this.nbJoueur-1; i++){
				e = new Equipe(this.sketch, Nature.HUMAIN, i);
				this.listeJoueur.add(e);
				System.out.println("Ajout du joueur n°" + i + " (HUMAIN)");
			}
			//Joueur IA
			e = new Equipe(this.sketch, Nature.HUMAIN, this.nbJoueur);
			this.listeJoueur.add(e);
			System.out.println("Ajout du joueur n°" + this.nbJoueur + " (IA)");
		}
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

	public void addNewJoueur(Nature n, int id) {

	}

	public void addNewBateau(int nbEq, int caseX, int caseY){
		Navire n;

		if(this.plateau.getCaseAt(caseX,caseY).estPleine()) return;

		if(this.listeJoueur.get(nbEq).getListeNavire().size()==0){ 			//Ajout Chalutier
			n = new Chalutier(sketch,0,nbEq);
			this.listeJoueur.get(nbEq).addNavire(n);
			this.plateau.getCaseAt(caseX,caseY).addOccupant(n);

		}else if (this.listeJoueur.get(nbEq).getListeNavire().size()==1){	//Ajout Destroyer
			n = new Destroyer(sketch,1,nbEq);
			this.listeJoueur.get(nbEq).addNavire(n);
			this.plateau.getCaseAt(caseX,caseY).addOccupant(n);

		}else if (this.listeJoueur.get(nbEq).getListeNavire().size()==2){	//Ajout Sous Marin
			n = new SousMarin(sketch,2,nbEq);
			this.listeJoueur.get(nbEq).addNavire(n);
			this.plateau.getCaseAt(caseX,caseY).addOccupant(n);

		}else if (this.listeJoueur.get(nbEq).getListeNavire().size()==3){ 	//On passe au joueur suivant
			this.nextTurn();
			if(this.nbTour!=1) this.addNewBateau(this.getTourJoueur(),caseX,caseY);

		}
	}

	public void nextTurn(){
		if(this.tourJoueur+1>=this.nbJoueur) this.nbTour++;
		this.tourJoueur=(this.tourJoueur+1)%this.nbJoueur;

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
