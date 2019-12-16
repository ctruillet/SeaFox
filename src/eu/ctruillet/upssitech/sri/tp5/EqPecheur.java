package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

public class EqPecheur extends Equipe{
	public EqPecheur(PApplet sketch, int idEq, Nature n){
		super(sketch, n);
		this.id=idEq;
	}
}
