package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

import java.util.ArrayList;

public class Equipe implements JHumain, JIA {
	//Attributs
	private PApplet sketch;
	private Nature myNature;
	int id;
	private Commande myCommande;
	private ArrayList<Navire> listeNavire = new ArrayList<>();
	protected int etat;

	//Constructeur
	public Equipe(PApplet sketch, Nature n, int id) {
		this.sketch = sketch;
		this.myNature = n;
		this.id = id;
	}

	//Méthodes
	public int getEtat() {
		return etat;
	}

	@Override
	public void interrogationParClavier() {
		//ToDo
	}

	@Override
	public void tirageAleatoire() {
		//ToDo
	}

	@Override
	public String toString() {
		return "Equipe{" +
				"myNature=" + myNature +
				", id=" + id +
				", listeNavire=" + listeNavire +
				'}';
	}

	@Override
	public Commande getCommande() {
		return this.myCommande;
	}

	@Override
	public void addNavire(Navire nav) {
		this.listeNavire.add(nav);
		this.etat+=nav.getEtat();
	}

	public void majEtat(){
		this.etat = 0;
		for(Navire n : this.getListeNavire()){
			this.etat+=n.getEtat();
		}
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
