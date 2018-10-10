package actions;

public interface Soin {
	String str = "Je soigne";
	public default String soigne() {
		return str;
	}
	public int getValue();
}
