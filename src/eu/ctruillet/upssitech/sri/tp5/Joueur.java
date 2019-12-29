package eu.ctruillet.upssitech.sri.tp5;

import java.util.ArrayList;

interface Joueur {
	Commande getCommande();
	Statut getStatut();
	void addNavire(Navire nav);
	ArrayList<Navire> getListeNavire();
	int getID();
}
