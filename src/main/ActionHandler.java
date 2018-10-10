package main;
import persos.*;
import actions.*;

public class ActionHandler {
	ActionHandler(Personnage p, Personnage t, Actions a){
		if (a==Actions.Soigner) {
			System.out.println(p + " essaie de soigner "+ t+ " pour "+p.getSoin().getValue()+ " pv");
			t.setVie(t.getVie() + p.getSoin().getValue());
		} else if (a==Actions.Attaquer) {
			System.out.println(p + " essaie d'attaquer  "+ t+ " pour "+p.getEspritCombatif().getDmg()+ " pv");
			t.setVie(t.getVie() - p.getEspritCombatif().getDmg());
		}
		
	}
}
