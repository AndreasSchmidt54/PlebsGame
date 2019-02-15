import javax.swing.JCheckBox;
import javax.swing.JLabel;

public abstract class Standort {
	
	JCheckBox box;
	JLabel bild;
	Client BebautVon;
	boolean istBebaut;
	boolean istBeBaubar;
	
	
	
	

	public class Weg extends Standort {
		
		public Weg () {		}
		
	}
	
}
