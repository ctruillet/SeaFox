package eu.ctruillet.upssitech.sri.tp5;

import java.util.ArrayList;

interface Joueur {
	Commande getCommande();
	void addNavire(Navire nav);
	ArrayList<Navire> getListeNavire();
	int getID();
}
