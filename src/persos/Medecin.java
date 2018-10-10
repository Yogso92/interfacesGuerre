package persos;

import actions.*;

public class Medecin extends Personnage{
	public Medecin() {
		this.setDeplacement((Deplacement) (new Courir()));
		this.setEspritCombatif((EspritCombatif) new CombatPistolet());
		this.setSoin((Soin) new Operation() );
	}
	public Medecin(String n, EspritCombatif esp, Deplacement depl, Soin soin) {
		
		super(n, esp, depl, soin);
	}
	
}
