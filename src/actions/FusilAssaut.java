package actions;

public class FusilAssaut implements EspritCombatif{
	private String str = "un coup de Scar pour d�blayer tout �a";
	private int dmg = 32;
	public String combat() {
		return str;
	}
	public int getDmg() {return dmg;}
	public String getArme() {
		return "Fusil D'assaut (" +dmg+")";
	}
}
