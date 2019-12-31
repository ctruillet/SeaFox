package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

import java.util.ArrayList;

public class Jeu {
	//Attribut
	private PApplet sketch;
	private Plateau plateau;
	protected ArrayList<Equipe> listeJoueur;
	protected boolean fini = false;
	private String message;
	private int tourJoueur;
	int nbTour;
	private int nbJoueur;

	//Constructeur
	public Jeu(PApplet sketch, int taille) {
		this.sketch = sketch;
		this.plateau = new Plateau(this.sketch, taille);
		this.listeJoueur = new ArrayList<>();
		this.tourJoueur = 0; //On va du Joueur 0 à Joueur n-1 (n maximum 4)
		this.nbTour = 0;
		this.message = "Joueur rouge, c'est à vous!";

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
		return "Jeu{" +
				"fini=" + fini +
				", tourJoueur=" + tourJoueur +
				", nbTour=" + nbTour +
				", nbJoueur=" + nbJoueur +
				'}';
	}

	public void attributionNavire() {
		//ToDO
	}

	/**
	 * Crée les joueurs
	 */
	public void creationJoueurs() {
		if (this.nbJoueur != this.listeJoueur.size()) {
			Equipe e;
			//Joueurs Humains
			for (int i = 0; i < this.nbJoueur - 1; i++) {
				addNewJoueur(Nature.HUMAIN,i);
			}
			//Joueur IA
			addNewJoueur(Nature.IA,this.nbJoueur - 1);
		}
	}

	/**
	 * Ajoute un nouveau joueur
	 * @param n
	 * @param id
	 */
	private void addNewJoueur(Nature n, int id) {
		Equipe e = new Equipe(this.sketch, n, id);
		this.listeJoueur.add(e);
		System.out.println("Ajout du joueur n°" + id + " (" + n + ")");
		System.out.println(this.listeJoueur);
	}

	private void majJeuAvCommande(Commande cmd) {
		//ToDO
	}

	protected void majPlateauCasTir(Navire n, boolean b) {

		for (int i = 0; i < this.getPlateau().getTaille(); i++) {
			for (int j = 0; j < this.getPlateau().getTaille(); j++) {
				if(!n.isTirIsOK(i,j)){
					this.getPlateau().getCaseAt(i,j).setCross(b);
				}
			}
		}
	}

	public void attaque(int x, int y){
		this.getPlateau().getCaseAt(x,y).attaque();
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

	private boolean addNewBateauAt(Navire n, int caseX, int caseY) {
		if (this.plateau.getCaseAt(caseX, caseY).canIAdd(n.getType())) {
			this.listeJoueur.get(n.getNumEq()).addNavire(n);
			this.plateau.getCaseAt(caseX, caseY).addOccupant(n);
			System.out.println("Le " + n.getType() + " du Joueur " + n.getNumEq() + " a été placé sur la case " + caseX + ";" + caseY);

			return true;
		}
		return false;
	}

	/**
	 * Ajoute un nouveau bateau à l'équipe nbEq à la case caseX,caseY
	 * @param nbEq
	 * @param caseX
	 * @param caseY
	 */
	public void addNewBateau(int nbEq, int caseX, int caseY) {
		Navire n;

		//La case est pleine
		if (this.plateau.getCaseAt(caseX, caseY).estPleine()) return;


		if (nbEq == this.nbJoueur - 1) { //Placements bateaux de l'IA
			n = new Chalutier(sketch, 0, nbEq);
			while (!addNewBateauAt(n, (int) (Math.random() * (10)), (int) (Math.random() * (10)))) ;

			n = new Destroyer(sketch, 1, nbEq);
			while (!addNewBateauAt(n, (int) (Math.random() * (10)), (int) (Math.random() * (10)))) ;

			n = new SousMarin(sketch, 2, nbEq);
			while (!addNewBateauAt(n, (int) (Math.random() * (10)), (int) (Math.random() * (10)))) ;

			this.getPlateau().setCrossIfICantAdd(TypeNav.CHALUTIER, false);
			this.getPlateau().setCrossIfICantAdd(TypeNav.DESTROYER, false);
			this.getPlateau().setCrossIfICantAdd(TypeNav.SOUSMARIN, false);
			this.nextTurn();


		} else {
			if (this.listeJoueur.get(nbEq).getListeNavire().size() == 0) {            //Ajout Chalutier

				if (!this.plateau.getCaseAt(caseX, caseY).canIAdd(TypeNav.CHALUTIER)) return;

				this.getPlateau().setCrossIfICantAdd(TypeNav.CHALUTIER, false);

				n = new Chalutier(sketch, 0, nbEq);
				addNewBateauAt(n, caseX, caseY);

				this.getPlateau().setCrossIfICantAdd(TypeNav.DESTROYER, true);

			} else if (this.listeJoueur.get(nbEq).getListeNavire().size() == 1) {    //Ajout Destroyer

				if (!this.plateau.getCaseAt(caseX, caseY).canIAdd(TypeNav.DESTROYER)) return;

				this.getPlateau().setCrossIfICantAdd(TypeNav.DESTROYER, false);

				n = new Destroyer(sketch, 1, nbEq);
				addNewBateauAt(n, caseX, caseY);

				this.getPlateau().setCrossIfICantAdd(TypeNav.SOUSMARIN, true);

			} else {    //Ajout Sous Marin

				if (!this.plateau.getCaseAt(caseX, caseY).canIAdd(TypeNav.SOUSMARIN)) return;

				this.getPlateau().setCrossIfICantAdd(TypeNav.SOUSMARIN, false);

				n = new SousMarin(sketch, 2, nbEq);
				addNewBateauAt(n, caseX, caseY);

				this.getPlateau().setCrossIfICantAdd(TypeNav.CHALUTIER, true);

				//On passe au joueur suivant
				this.nextTurn();

				//si le prochain joueur est l'IA
				if (nbEq == this.nbJoueur - 2) this.addNewBateau(this.getTourJoueur(), (caseX+1)%this.getPlateau().getTaille(), (caseY+1)%this.getPlateau().getTaille());

			}

		}
	}

	protected void nextTurn() {
		for(Navire n : this.listeJoueur.get(this.getTourJoueur()).getListeNavire()){
			n.update();
		}

		if (this.tourJoueur + 1 >= this.nbJoueur) {
			this.nbTour++;
		}
		this.tourJoueur = (this.tourJoueur + 1) % this.nbJoueur;

		this.setMessage("Joueur "+ (this.tourJoueur == 0 ? "rouge" : (this.tourJoueur == 1 ? "vert" : (this.tourJoueur == 2 ? "bleu" : (this.tourJoueur == 3 ? "jaune" : "???")))) +", c'est à vous !");
		System.out.println(this.listeJoueur.get(this.getTourJoueur()).toString());

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

	public String getMessage(){
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
