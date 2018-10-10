package persos;

import actions.*;

public class Guerrier extends Personnage{
	public Guerrier() {
		super();
		this.setDeplacement((Deplacement) (new Courir()));
		this.setEspritCombatif((EspritCombatif) new FusilAssaut());
		this.setSoin((Soin) new PetiteTrousse() );
	}
	public Guerrier(String n, EspritCombatif esp, Deplacement depl, Soin soin) {
		super(n, esp, depl, soin);
	}
}
