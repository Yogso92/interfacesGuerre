package actions;

public class PetiteTrousse implements Soin {
	private static String str = "je soigne avec une petite trousse";
	int value = 25;

	public String soigne() {
		return str;
	}
	public int getValue() { return value;}
}
