package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Classe Jeu communicant avec MainClass pour l'affichage et les autres classes pour l'execution
 * @see MainClass
 */
public class Jeu {
	//Attribut
	private PApplet sketch;
	private Plateau plateau;
	ArrayList<Equipe> listeJoueur;
	private boolean fini = false;
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

	/**
	 * Méthode loop de processing permettant d'afficher le plateau de jeu
	 */
	public void draw() {
		this.plateau.draw(15, 15);
	}

	/**
	 *
	 * @return
	 */
	public String toString() {
		return "Jeu{" +
				"fini=" + fini +
				", tourJoueur=" + tourJoueur +
				", nbTour=" + nbTour +
				", nbJoueur=" + nbJoueur +
				'}';
	}

	/**
	 * Retoune true si le jeu est fini
	 * @return
	 */
	public boolean isFini() {
		int nbJoueurAlive = this.getNbJoueur();
		for(Equipe e : this.listeJoueur){
			e.majEtat();
			if(e.getEtat()==0) nbJoueurAlive--;
		}
		System.out.println("Nombre de joueurs en vie : " + nbJoueurAlive);
		return (nbJoueurAlive<=1);
	}

	/**
	 * Retoune la couleur du joueur vainqueur
	 * @return
	 */
	public String getWinner(){
		String s = "???";
		for(Equipe e : this.listeJoueur){
			if(e.getEtat()!=0){
				if(s.equals("???")){
					s = (e.getID() == 0 ? "rouge" : (e.getID() == 1 ? "vert" : (e.getID() == 2 ? "bleu" : "jaune")));
				}else{ //il y a au moins un autre vainqueur
					try {
						throw new Exception("Trop de vainqueurs");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
		return s;
	}

	/**
	 * Crée les joueurs
	 */
	public void creationJoueurs() {
		if (this.nbJoueur != this.listeJoueur.size()) {
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
		Equipe e = new Equipe(n, id);
		this.listeJoueur.add(e);
		System.out.println("Ajout du joueur n°" + id + " (" + n + ")");
		System.out.println(this.listeJoueur);
	}

	/**
	 * Mise à jour de l'affichage du plateau suivant les positions où on peut tirer
	 * @param n
	 * @param b
	 */
	void majPlateauCasTir(Navire n, boolean b) {
		for (int i = 0; i < this.getPlateau().getTaille(); i++) {
			for (int j = 0; j < this.getPlateau().getTaille(); j++) {
				if(!n.isTirIsOK(i,j)){
					this.getPlateau().getCaseAt(i,j).setCross(b);
				}
			}
		}
	}

	/**
	 * Méthode d'attaque vers la case x;y
	 * @param x
	 * @param y
	 */
	public void attaque(int x, int y){
		this.getPlateau().getCaseAt(x,y).attaque();
	}

	/**
	 * Deplacement du navire vers la case x;y
	 * @param n
	 * @param x
	 * @param y
	 */
	public void deplacer(Navire n, int x, int y){
		if(x<0 || x>9 || y<0 || y>9){
			try {
				throw new LimiteException(x,y);
			} catch (LimiteException e) {
				e.printStackTrace();
			}
		}
		this.getPlateau().getCaseAt((int)n.getPosition().getX(),(int)n.getPosition().getY()).removeOccupant(n);
		this.getPlateau().getCaseAt(x,y).addOccupant((n));
	}

	/**
	 * Affichage de croix sur la cases où le navire ne peut pas se deplacer
	 * @param n
	 * @param b
	 */
	void majJeuCasDeplacement(Navire n, boolean b) {
			for (int i = 0; i < this.getPlateau().getTaille(); i++) {
				for (int j = 0; j < this.getPlateau().getTaille(); j++) {
					if(b){
						if(this.getPlateau().getCaseAt(i,j).estPleine() || !n.isDeplacementIsOK(i,j)){
							this.getPlateau().getCaseAt(i,j).setCross(true);
						}
					}else{
						if(this.getPlateau().getCaseAt(i,j).isCross()) this.getPlateau().getCaseAt(i,j).setCross(false);
					}

				}
			}
	}


	public Plateau getPlateau() {
		return this.plateau;
	}

	/**
	 * Retoune true et ajoute un bateau à la case caseX;caseY
	 * Retoune false sinon
	 * @param n
	 * @param caseX
	 * @param caseY
	 * @return
	 */
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

	/**
	 * Passe au tour suivant
	 */
	void nextTurn() {
		for(Navire n : this.listeJoueur.get(this.getTourJoueur()).getListeNavire()){
			n.update();
		}

		System.out.println(this.listeJoueur.get(this.getTourJoueur()).toString());

		if (this.tourJoueur + 1 >= this.nbJoueur) {
			this.nbTour++;
		}
		this.tourJoueur = (this.tourJoueur + 1) % this.nbJoueur;

		if(this.nbTour>=1){
			if(isFini()) return ;
			if(this.listeJoueur.get(this.getTourJoueur()).getMyNature()==Nature.IA){ //Tour de l'IA
				this.setMessage("Tour de l'IA");

				//on joue
				this.jouerIA();

				//Tour suivant
				this.nextTurn();

			}else{
				if(this.listeJoueur.get(this.getTourJoueur()).getEtat()==0){
					this.nextTurn();
				}

				this.setMessage("Joueur "+ (this.tourJoueur == 0 ? "rouge" : (this.tourJoueur == 1 ? "vert" : (this.tourJoueur == 2 ? "bleu" : "jaune"))) +", c'est à vous !");
			}
		}

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

	private void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Fait jouer l'IA
	 */
	private void jouerIA(){
		double random;
		int x=-1, y=-1;

		for(Navire n : this.listeJoueur.get(this.getTourJoueur()).getListeNavire()){
			if(n.estValide()){
				random = Math.random()*100;

				if(random<=49){ //On attaque
					System.out.println("Tentative d'attaque du "+n.getType());
					attaqueIA(n);

				}else if(random<=99){ //On Bouge
					System.out.println("Tentative de deplacement du "+n.getType());
					deplacementIA(n);


				}else{ //on saborde
					System.out.println("Suicide du "+n.getType()+" sur la case "+x+";"+y);
					n.setCoule();
				}
			}

		}
	}

	/**
	 * Fait attaquer le navire n de l'IA sur une case possible au hasard
	 * @param n
	 */
	private void attaqueIA(Navire n){
		double random;
		for(int i=0; i<this.getPlateau().getTaille(); i++){
			for(int j=0; j<this.getPlateau().getTaille(); j++){
				if(n.isTirIsOK(i,j) && this.getPlateau().getCaseAt(i,j).estOccupee() && i!=(int)n.getPosition().getX() && j!=(int)n.getPosition().getY()){
					random = Math.random()*2;
					if(random<=55){
						this.attaque(i,j);
						System.out.println("\tAttaque du "+n.getType()+" sur la case "+i+";"+j);

						return;
					}
				}
			}
		}
	}

	/**
	 * Fait se deplacer le navire n de l'IA sur une case possible au hasard
	 * @param n
	 */
	private void deplacementIA(Navire n){
		int x, y;

		for(int i=0; i<10; i++){
			x = (int)(Math.random()*9);
			y = (int)(Math.random()*9);
			System.out.println("\t\tCase "+x+";"+y);
			if(n.isDeplacementIsOK(x,y)){
				this.deplacer(n,x,y);
				System.out.println("\tDeplacement du "+n.getType()+" sur la case "+x+";"+y);
				return ;
			}
		}
	}

}
