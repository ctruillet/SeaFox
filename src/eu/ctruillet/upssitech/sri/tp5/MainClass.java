package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.awt.*;
import java.util.ArrayList;

public class MainClass extends PApplet {
	//Attributs
	public static PApplet processing;
	public static WindowsRules wr = new WindowsRules();
	protected final static String ICON = "ressources/icons/icon.png";
	protected final static String TITLE = "SeaFox";
	protected PFont fontNormal, fontBold;
	private PImage baniere;
	private PImage map;

	protected FSM state;
	protected int caseX = -1, caseY = -1;

	protected static Jeu j;
	protected ArrayList<Button> Buttons_ChoixNbJoueur = new ArrayList<>();
	protected ArrayList<Button> Buttons_Actions = new ArrayList<>();
	protected String message = "";
	protected Action actionEnCours = Action.ATTENTE;


	//Methodes
	public static void main(String[] args) {
		PApplet.main("eu.ctruillet.upssitech.sri.tp5.MainClass", args);
	}

	public void setup() {
		//Initialisation
		processing = this;
		changeAppTitle();
		changeAppIcon(loadImage(ICON));
		surface.setResizable(false);

		fontNormal = createFont("Arial", 14);
		fontBold   = createFont("Arial Bold", 20);


		this.state = FSM.INIT;

		createButtons_ChoixNbJoueur();
		createButtons_Actions();

		this.j = new Jeu(this, 10);

		this.state = FSM.CHOIX_NB_JOUEUR;
	}

	public void settings() {
		baniere = loadImage("ressources/icons/baniere.png");
		map = loadImage("ressources/icons/map.png");
		size(1000, 800);
	}

	private void changeAppTitle(){
		surface.setTitle(MainClass.TITLE);
	}

	private void changeAppIcon(PImage img) {
		surface.setIcon(img);
	}

	public void draw() {
		textAlign(CENTER);
		this.background(35, 109, 122);

		//Affichage de la carte
		image(map, 5, 5);

		//Affichage du jeu
		if (this.state != FSM.INIT && this.state != FSM.CHOIX_NB_JOUEUR) {
			j.draw();
		}

		fill(119, 224, 255);
		noStroke();

		//Menu
		rect(650, 0, 350, 800);

		//Affichage de la baniere
		baniere.resize(1000 - 650, 175);
		image(baniere, 650, 0);
		stroke(0);
		line(650, 0, 650, height);
		noFill();
		fill(0);

		fill((new Color(189, 74, 35)).getRGB());
		textSize(20);
		this.message = j.getMessage();
		if(this.state!=FSM.CHOIX_NB_JOUEUR) text(this.message,825,210);
		fill(0);
		textSize(14);


		switch (this.state) {
			case INIT:
				break;

			case CHOIX_NB_JOUEUR:
				displayChoixNbJoueur();
				break;

			case PLACEMENT_BATEAU:
				j.creationJoueurs();
				j.getPlateau().setCrossAtCaseFull(true);

				textFont(fontBold);
				fill((new Color(119, 223, 254)).getRGB());
				text("PLACEMENT DES BATEAUX\nChalutier -> Destroyer -> Sous-Marin",325,700);
				fill(0);
				textFont(fontNormal);

				//Fin placement bateau ?
				if(this.j.nbTour==1){
					this.state=FSM.TOUR;
					j.getPlateau().setCrossAtCaseFull(false);
				}
				break;

			case TOUR:
				displayActionButtons();
				for(Navire n : j.listeJoueur.get(j.getTourJoueur()).getListeNavire()){
					if(n.estValide()){
						n.afficher();
						displayActionButtons(n);
					}
				}

				break;

			case FIN:

				break;

			default:
				break;
		}

		textAlign(CENTER,CENTER);
		if (caseX != -1 && caseY != -1 && this.state!=FSM.INIT && this.state!=FSM.CHOIX_NB_JOUEUR) text("Case " + caseX + ";" + caseY, 600, 770);


	}

	public void mousePressed() {
		//Mise à jour de la case où on est
		if(j.getPlateau().onClick(mouseX,mouseY)){
			caseX = j.getPlateau().getCaseXOnClick(mouseX);
			caseY = j.getPlateau().getCaseYOnClick(mouseY);

			if(this.state==FSM.PLACEMENT_BATEAU){
				this.j.addNewBateau(this.j.getTourJoueur(),caseX,caseY);

				//Fin placement bateau ?
				if(this.j.nbTour==1){
					this.state=FSM.TOUR;
					j.getPlateau().setCrossAtCaseFull(false);
				}

			}else if(this.state==FSM.TOUR){
				switch (this.getActionEnCours()){
					case ATTENTE:
						break;

					case CHALUTIER_ATTAQUE:
						if(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(0).isTirIsOK(caseX,caseY)){

							//Attaque
							this.j.attaque(caseX,caseY);

							//Action d'attaque indisponible pour ce tour
							this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(0).setPeutAttaquer(false);

							//mise à jour de l'affichage
							this.j.majPlateauCasTir(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(0), false);

							//Attente de la prochaine action
							this.setActionEnCours(Action.ATTENTE);
							this.Buttons_Actions.get(0).setText("Attaquer");
						}
						break;

					case CHALUTIER_DEPLACE:
						if(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(0).isDeplacementIsOK(caseX,caseY)){

							//Deplacement
							this.j.deplacer(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(0),caseX, caseY);

							//Action d'attaque indisponible pour ce tour
							this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(0).setPeutSeDeplacer(false);

							//mise à jour de l'affichage
							this.j.majJeuCasDeplacement(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(0), false);

							//Attente de la prochaine action
							this.setActionEnCours(Action.ATTENTE);
							this.Buttons_Actions.get(1).setText("Se Deplacer");
						}

						break;

					case DESTROYER_ATTAQUE:
						if(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(1).isTirIsOK(caseX,caseY)){

							//Attaque
							this.j.attaque(caseX,caseY);

							//Action d'attaque indisponible pour ce tour
							this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(1).setPeutAttaquer(false);

							//mise à jour de l'affichage
							this.j.majPlateauCasTir(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(1), false);

							//Attente de la prochaine action
							this.setActionEnCours(Action.ATTENTE);
							this.Buttons_Actions.get(3).setText("Attaquer");
						}
						break;

					case DESTROYER_DEPLACE:
						if(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(1).isDeplacementIsOK(caseX,caseY)){

							//Deplacement
							this.j.deplacer(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(1),caseX, caseY);

							//Action d'attaque indisponible pour ce tour
							this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(1).setPeutSeDeplacer(false);
							//mise à jour de l'affichage
							this.j.majJeuCasDeplacement(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(1), false);

							//Attente de la prochaine action
							this.setActionEnCours(Action.ATTENTE);
							this.Buttons_Actions.get(4).setText("Se Deplacer");
						}
						break;

					case SOUSMARIN_ATTAQUE:
						if(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(2).isTirIsOK(caseX,caseY)){
							//Attaque
							this.j.attaque(caseX,caseY);

							//Action d'attaque et deplacement indisponible pour ce tour
							this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(2).setPeutSeDeplacer(false);
							this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(2).setPeutAttaquer(false);

							//mise à jour de l'affichage
							this.j.majPlateauCasTir(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(2), false);

							//Attente de la prochaine action
							this.setActionEnCours(Action.ATTENTE);
							this.Buttons_Actions.get(6).setText("Attaquer");
						}
						break;

					case SOUSMARIN_DEPLACE:
						if(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(2).isDeplacementIsOK(caseX,caseY)){

							//Deplacement
							this.j.deplacer(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(2),caseX, caseY);

							//Action d'attaque et deplacement indisponible pour ce tour
							this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(2).setPeutSeDeplacer(false);
							this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(2).setPeutAttaquer(false);

							//mise à jour de l'affichage
							this.j.majJeuCasDeplacement(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(2), false);

							//Attente de la prochaine action
							this.setActionEnCours(Action.ATTENTE);
							this.Buttons_Actions.get(7).setText("Se Deplacer");
						}
						break;

					default:
						break;
				}

			}
		}

		if(this.state==FSM.CHOIX_NB_JOUEUR) {
			for (Button b : Buttons_ChoixNbJoueur) {
				if (b.onClick(mouseX, mouseY)) {
					if (b.getText().equals("1 Joueur")) {
						this.j.setNbJoueur(2);
					}
					if (b.getText().equals("2 Joueurs")) {
						this.j.setNbJoueur(3);
					}
					if (b.getText().equals("3 Joueurs")) {
						this.j.setNbJoueur(4);
					}

					System.out.println(this.j.getNbJoueur() + " Joueurs");

					this.state = FSM.PLACEMENT_BATEAU;
				}
			}
		}
		if(this.state==FSM.TOUR) {
			for(int i=0; i<this.Buttons_Actions.size(); i++){
				if(this.Buttons_Actions.get(i).onClick(mouseX,mouseY)) {
					System.out.println("\"" + this.Buttons_Actions.get(i).text + "\"(" + i + ") est pressé");

					switch (i){
						case 0: //Attaquer - CHALUTIER
							if(this.getActionEnCours()==Action.ATTENTE && this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(0).isPeutAttaquer()){

								//On change l'action en cours
								this.setActionEnCours(Action.CHALUTIER_ATTAQUE);

								//MaJ de l'affichage
								this.j.majPlateauCasTir(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(0), true);

								//Changement de l'affichage du bouton
								this.Buttons_Actions.get(i).setText("Annuler");

							}else if(this.getActionEnCours()== Action.CHALUTIER_ATTAQUE){

								//On change l'action en cours
								this.setActionEnCours(Action.ATTENTE);

								//Changement de l'affichage du bouton
								this.Buttons_Actions.get(i).setText("Attaquer");

								//MaJ de l'affichage
								this.j.majPlateauCasTir(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(0), false);
							}

							break;

						case 1: //Se Deplacer - CHALUTIER
							if(this.getActionEnCours()==Action.ATTENTE  && this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(0).isPeutSeDeplacer()){
								this.setActionEnCours(Action.CHALUTIER_DEPLACE);
								this.Buttons_Actions.get(i).setText("Annuler");

								//mise à jour de l'affichage
								this.j.majJeuCasDeplacement(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(0), true);

							}else if(this.getActionEnCours()== Action.CHALUTIER_DEPLACE){

								this.setActionEnCours(Action.ATTENTE);
								this.Buttons_Actions.get(i).setText("Se Deplacer");

								//mise à jour de l'affichage
								this.j.majJeuCasDeplacement(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(0), false);
							}
							break;

						case 2: //Saborder - CHALUTIER
							this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(0).setCoule();
							break;

						case 3:	//Attaquer - DESTROYER
							if(this.getActionEnCours()==Action.ATTENTE  && this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(1).isPeutAttaquer()){

								this.setActionEnCours(Action.DESTROYER_ATTAQUE);
								this.Buttons_Actions.get(i).setText("Annuler");
								this.j.majPlateauCasTir(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(1), true);

							}else if(this.getActionEnCours()== Action.DESTROYER_ATTAQUE){

								this.setActionEnCours(Action.ATTENTE);
								this.Buttons_Actions.get(i).setText("Attaquer");
								this.j.majPlateauCasTir(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(1), false);

							}
							break;

						case 4:	//Se Deplacer - DESTROYER
							if(this.getActionEnCours()==Action.ATTENTE  && this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(1).isPeutSeDeplacer()){

								this.setActionEnCours(Action.DESTROYER_DEPLACE);
								this.Buttons_Actions.get(i).setText("Annuler");

								//mise à jour de l'affichage
								this.j.majJeuCasDeplacement(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(1), true);

							}else if(this.getActionEnCours()== Action.DESTROYER_DEPLACE){

								this.setActionEnCours(Action.ATTENTE);
								this.Buttons_Actions.get(i).setText("Se Deplacer");

								//mise à jour de l'affichage
								this.j.majJeuCasDeplacement(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(1), false);
							}
							break;

						case 5: //Saborder - DESTROYER
							this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(1).setCoule();
							break;

						case 6: //Attaquer - SOUSMARIN
							if(this.getActionEnCours()==Action.ATTENTE  && this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(2).isPeutAttaquer()){

								this.setActionEnCours(Action.SOUSMARIN_ATTAQUE);
								this.Buttons_Actions.get(i).setText("Annuler");

								this.j.majPlateauCasTir(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(2), true);

							}else if(this.getActionEnCours()== Action.SOUSMARIN_ATTAQUE){

								this.setActionEnCours(Action.ATTENTE);
								this.Buttons_Actions.get(i).setText("Attaquer");
								this.j.majPlateauCasTir(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(2), false);

							}
							break;

						case 7: //Se Deplacer - SOUSMARIN
							if(this.getActionEnCours()==Action.ATTENTE  && this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(2).isPeutSeDeplacer()){

								this.setActionEnCours(Action.SOUSMARIN_DEPLACE);
								this.Buttons_Actions.get(i).setText("Annuler");

								//mise à jour de l'affichage
								this.j.majJeuCasDeplacement(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(2), true);

							}else if(this.getActionEnCours()== Action.SOUSMARIN_DEPLACE){

								this.setActionEnCours(Action.ATTENTE);
								this.Buttons_Actions.get(i).setText("Se Deplacer");

								//mise à jour de l'affichage
								this.j.majJeuCasDeplacement(this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(2), false);
							}
							break;

						case 8: //Saborder - SOUSMARIN
							this.j.listeJoueur.get(this.j.getTourJoueur()).getListeNavire().get(2).setCoule();
							break;

						case 9: //FIN DE TOUR
							this.setActionEnCours(Action.ATTENTE);
							this.j.getPlateau().removeAllCross();
							//TODO : Remettre les boutons à leurs état d'origine
							if(this.j.isFini()) this.state = FSM.FIN;
							j.nextTurn();
							break;

						case 10: //Regles
							wr.setVisible();
							break;


					}
				}
			}
		}


	}

	public void mouseReleased() {
		for (Button b : Buttons_ChoixNbJoueur) {
			b.onReleased();
		}

		for (Button b : Buttons_Actions) {
			b.onReleased();

		}
	}

	protected void createButtons_ChoixNbJoueur(){
		Button b;

		b = new Button(this, 737, 220, 45, "1 Joueur");
		Buttons_ChoixNbJoueur.add(b);

		b = new Button(this, 825, 220, 45, "2 Joueurs");
		Buttons_ChoixNbJoueur.add(b);

		b = new Button(this, 913, 220, 45, "3 Joueurs");
		Buttons_ChoixNbJoueur.add(b);
	}

	protected void createButtons_Actions(){
		Button b;

		//Actions du Chalutier
		b = new Button(this, 730, 292, 50, "Attaquer");
		Buttons_Actions.add(b);
		b = new Button(this, 825, 292, 50, "Deplacer");
		Buttons_Actions.add(b);
		b = new Button(this, 920, 292, 50, "Saborder");
		Buttons_Actions.add(b);

		//Actions du Destroyer
		b = new Button(this, 730, 412, 50, "Attaquer");
		Buttons_Actions.add(b);
		b = new Button(this, 825, 412, 50, "Deplacer");
		Buttons_Actions.add(b);
		b = new Button(this, 920, 412, 50, "Saborder");
		Buttons_Actions.add(b);

		//Actions du SousMarin
		b = new Button(this, 730, 532, 50, "Attaquer");
		Buttons_Actions.add(b);
		b = new Button(this, 825, 532, 50, "Deplacer");
		Buttons_Actions.add(b);
		b = new Button(this, 920, 532, 50, "Saborder");
		Buttons_Actions.add(b);

		//Actions Génerales
		b = new Button(this, 325, 720, 90, "Fin de Tour");
		Buttons_Actions.add(b);
		b = new Button(this, 50, 770, 40, "Regles");
		Buttons_Actions.add(b);

	}

	protected void displayChoixNbJoueur(){
		for (Button b : Buttons_ChoixNbJoueur) {
			b.update();
		}
	}

	protected void displayActionButtons(){
		Buttons_Actions.get(9).update();
		Buttons_Actions.get(10).update();
	}

	protected void displayActionButtons(Navire n){
		//Si le navire n'a plus de vie
		if(n.getEtat()<=0) return;

		switch(n.getType()){
			case CHALUTIER:
				if(n.isPeutAttaquer() && (this.getActionEnCours()==Action.CHALUTIER_ATTAQUE || this.getActionEnCours()==Action.ATTENTE )) Buttons_Actions.get(0).update();
				if(n.isPeutSeDeplacer() && (this.getActionEnCours()==Action.CHALUTIER_DEPLACE || this.getActionEnCours()==Action.ATTENTE )) Buttons_Actions.get(1).update();
				if(n.estValide() && (this.getActionEnCours()==Action.ATTENTE )) Buttons_Actions.get(2).update();
				break;

			case DESTROYER:
				if(n.isPeutAttaquer() && (this.getActionEnCours()==Action.DESTROYER_ATTAQUE || this.getActionEnCours()==Action.ATTENTE )) Buttons_Actions.get(3).update();
				if(n.isPeutSeDeplacer() && (this.getActionEnCours()==Action.DESTROYER_DEPLACE || this.getActionEnCours()==Action.ATTENTE )) Buttons_Actions.get(4).update();
				if(n.estValide() && (this.getActionEnCours()==Action.ATTENTE )) Buttons_Actions.get(5).update();
				break;
			case SOUSMARIN:
				if(n.isPeutAttaquer() && (this.getActionEnCours()==Action.SOUSMARIN_ATTAQUE || this.getActionEnCours()==Action.ATTENTE )) Buttons_Actions.get(6).update();
				if(n.isPeutSeDeplacer() && (this.getActionEnCours()==Action.SOUSMARIN_DEPLACE || this.getActionEnCours()==Action.ATTENTE )) Buttons_Actions.get(7).update();
				if(n.estValide() && (this.getActionEnCours()==Action.ATTENTE )) Buttons_Actions.get(8).update();
				break;

		}
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Action getActionEnCours() {
		return actionEnCours;
	}

	public void setActionEnCours(Action actionEnCours) {
		this.actionEnCours = actionEnCours;
	}
}
