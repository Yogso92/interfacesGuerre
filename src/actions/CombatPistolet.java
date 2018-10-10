package actions;

public class CombatPistolet implements EspritCombatif {
	private static String str = "je tire une balle de pistolet";
	private int dmg = 12;
	public String combat() {
		return str;
	}
	public int getDmg() {return dmg;}
	public String getArme() {
		return "Pistolet (" +dmg+")";
	}
}
