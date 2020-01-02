package eu.ctruillet.upssitech.sri.tp5;


import java.util.ArrayList;

/**
 * Classe d'une Equipe
 */
public class Equipe implements Joueur {
	private Nature myNature;
	private int id;
	private ArrayList<Navire> listeNavire = new ArrayList<>();
	private int etat;

	//Constructeur
	public Equipe(Nature n, int id) {
		//Attributs
		this.myNature = n;
		this.id = id;
	}

	//Méthodes
	@Override
	public int getEtat() {
		return etat;
	}

	@Override
	public String toString() {
		return "Equipe{" +
				"myNature=" + (myNature==Nature.HUMAIN ? "HUMAIN" : "IA    ") +
				", id=" + id +
				", listeNavire=" + listeNavire +
				'}';
	}

	@Override
	public void addNavire(Navire nav) {
		this.listeNavire.add(nav);
		this.etat+=nav.getEtat();
	}

	@Override
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

	public Nature getMyNature() {
		return myNature;
	}
}
