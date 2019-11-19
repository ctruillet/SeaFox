package eu.ctruillet.upssitech.sri.tp5;

import java.util.ArrayList;

public class Equipe implements JHumain,JIA {
	//Attributs
	protected Statut myStatut;
	protected Nature myNature;
	protected int id;
	protected Commande myCommande;
	protected ArrayList<Navire> listeNavire;

	//Constructeur
	public Equipe(Nature n){
		this.myNature=n;
	}

	//Méthodes
	@Override
	public void interrogationParClavier() {
		//ToDo
	}

	@Override
	public void tirageAleatoire() {
		//ToDo
	}

	@Override
	public Commande getCommande() {
		return this.myCommande;
	}

	@Override
	public Statut getStatut() {
		return this.myStatut;
	}

	@Override
	public void addNavire(Navire nav) {
		this.listeNavire.add(nav);
	}

	@Override
	public ArrayList<Navire> getListeNavire() {
		return this.listeNavire;
	}

	@Override
	public int getID() {
		return this.id;
	}
}
