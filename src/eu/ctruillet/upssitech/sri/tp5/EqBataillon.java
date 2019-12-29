package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

class EqBataillon extends Equipe {
	public EqBataillon(PApplet sketch, int idEq, Nature n){
		super(sketch, n,idEq);
		this.id=idEq;
	}
}
