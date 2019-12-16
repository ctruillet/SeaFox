package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

import java.util.ArrayList;

public interface Joueur {
	public Commande getCommande();
	public Statut getStatut();
	public void addNavire(Navire nav);
	public ArrayList<Navire> getListeNavire();
	public int getID();
}
