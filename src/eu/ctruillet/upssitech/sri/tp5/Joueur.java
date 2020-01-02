package eu.ctruillet.upssitech.sri.tp5;

import java.util.ArrayList;

/**
 * Interface Joueur regroupant les actions spécifique d'un joueur
 */
interface Joueur {
	int getEtat();
	void addNavire(Navire nav);
	ArrayList<Navire> getListeNavire();
	void majEtat();
	int getID();
}
