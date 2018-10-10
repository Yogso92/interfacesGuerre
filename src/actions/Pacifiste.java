package actions;

public class Pacifiste implements EspritCombatif{
	private static String str = "je ne me bats pas";
	private int dmg = 0;
	public String combat() {
		return str;
	}

	public int getDmg() {return dmg;}
	public String getArme() {
		return "Pacifiste (" +dmg+")";
	}
}
