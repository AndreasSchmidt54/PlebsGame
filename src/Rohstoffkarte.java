
public class Rohstoffkarte extends Rohstoff{

	int rohstoffAusgebZahl;
	Rohstoff rohstoff;
	String kartennummer;
	
	public Rohstoffkarte (String kartenbenennung) {
		kartennummer = kartenbenennung;
	}
	
	public int getRohstoffZahl () {
		return rohstoffAusgebZahl;
	}
	
	public void setRohstoff (Rohstoff stoff) {
		rohstoff = stoff;
	}
	
	public Rohstoff getRohstoff () {
		return rohstoff;
	}
	
}
