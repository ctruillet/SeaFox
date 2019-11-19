package eu.ctruillet.upssitech.sri.tp5;

//ToDo
public class Commande {
	//Attributs
	private int idNavire;
	private Action ActionChoisie;
	private Direction DirectionChoisie;
	private Equipe equipe;

	//Constructeur
	public Commande (Equipe eq, int id, int a, int d){
		//ToDo
	}
	public Commande(Equipe eq, String strldNav, String strAction, String strDirection){
		//ToDo
	}

	//Méthodes
	public Equipe getEquipe(){
		return this.equipe;
	}

	public int getIdNavire(){
		return this.idNavire;
	}

	public void setIdNavire(int idNavire){
		this.idNavire=idNavire;
	}

	public Action getActionChoisie() {
		return ActionChoisie;
	}

	public void setActionChoisie(Action actionChoisie) {
		ActionChoisie = actionChoisie;
	}

	public Direction getDirectionChoisie() {
		return DirectionChoisie;
	}

	public void setDirectionChoisie(Direction directionChoisie) {
		DirectionChoisie = directionChoisie;
	}

	public String toString(){
		//ToDo
		return "";
	}
}
