package actions;

public class CombatCouteau implements EspritCombatif{
	private static String str = "je donne un coup de couteau";
	private int dmg = 50;
	public String combat() {
		return str;
	}
	public int getDmg() {return dmg;}
	public String getArme() {
		return "Couteau (" +dmg+")";
	}
}
