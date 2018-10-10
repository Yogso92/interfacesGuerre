package actions;

public class FusilSniper implements EspritCombatif {
	private String str = "Je mets un 360 no scope EZ";
	private int dmg = 100;
	public String combat() {
		return str;
	}
	public int getDmg() { return dmg;}
	public String getArme() {
		return "Fusil Sniper (" +dmg+")";
	}
}
