package persos;

import actions.*;

public class Sniper extends Personnage {
	public Sniper() {
		super();
		this.setEspritCombatif((EspritCombatif) new FusilSniper());
		this.setSoin(soin);
	}
	public Sniper(EspritCombatif esp, Deplacement depl, Soin soin) {
		this.espritCombatif = esp;
		this.soin = soin;
		this.dep = depl;
	}
	/*public String seDeplacer() {
		return super.seDeplacer();
	}
	public String combattre() {
		return super.combattre();
	}
	public String soigner() {
		return super.soigner();
	}*/
}
