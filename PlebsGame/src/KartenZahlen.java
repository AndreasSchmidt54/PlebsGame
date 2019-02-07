import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class KartenZahlen {

	public ArrayList<Zahlen> karten = new ArrayList<Zahlen>();

	private JTextField card1;
	private JTextField card2;
	private JTextField card3;
	private JTextField card4;
	private JTextField card5;
	private JTextField card6;
	private JTextField card7;
	private JTextField card8;
	private JTextField card9;
	private JTextField card10;
	private JTextField card11;
	private JTextField card12;
	private JTextField card13;
	private JTextField card14;
	private JTextField card15;
	private JTextField card16;
	private JTextField card17;
	private JTextField card18;
	private JTextField card19;

	public Zahlen zahl1 = new Zahlen();
	public Zahlen zahl2 = new Zahlen();
	public Zahlen zahl3 = new Zahlen();
	public Zahlen zahl4 = new Zahlen();
	public Zahlen zahl5 = new Zahlen();
	public Zahlen zahl6 = new Zahlen();
	public Zahlen zahl7 = new Zahlen();
	public Zahlen zahl8 = new Zahlen();
	public Zahlen zahl9 = new Zahlen();
	public Zahlen zahl10 = new Zahlen();
	public Zahlen zahl11 = new Zahlen();
	public Zahlen zahl12 = new Zahlen();
	public Zahlen zahl13 = new Zahlen();
	public Zahlen zahl14 = new Zahlen();
	public Zahlen zahl15 = new Zahlen();
	public Zahlen zahl16 = new Zahlen();
	public Zahlen zahl17 = new Zahlen();
	public Zahlen zahl18 = new Zahlen();
	
	
	int zwei = 0, drei = 0, vier = 0, fuenf = 0, sechs = 0, acht = 0, neun = 0, zehn = 0, elf = 0, zwoelf = 0;
	
	public KartenZahlen() {
		
		karten.add(zahl1);
		karten.add(zahl2);
		karten.add(zahl3);
		karten.add(zahl4);
		karten.add(zahl5);
		karten.add(zahl6);
		karten.add(zahl7);
		karten.add(zahl8);
		karten.add(zahl9);
		karten.add(zahl10);
		karten.add(zahl11);
		karten.add(zahl12);
		karten.add(zahl13);
		karten.add(zahl14);
		karten.add(zahl15);
		karten.add(zahl16);
		karten.add(zahl17);
		karten.add(zahl18);
	}
	
	public void zahlenerstellen() {
			
			for(Zahlen karte : karten) {
				
				int k = 0;
				
				do {
					
					int randomzahl = (int)(Math.random()* 12) + 1;
				
					if (randomzahl == 2 && zwei != 1) {
						karte.rohstoffzahl = 2;
						zwei++;
						k = 1;
					} if (randomzahl == 3 && drei != 2) {
						karte.rohstoffzahl = 3;
						drei++;
						k = 1;
					} if (randomzahl == 4 && vier != 2){
						karte.rohstoffzahl = 4;
						vier++;
						k = 1;
					} if (randomzahl == 5 && fuenf != 2){
						karte.rohstoffzahl = 5;
						fuenf++;
						k = 1;
					} if (randomzahl == 6 && sechs != 2){
						karte.rohstoffzahl = 6;
						sechs++;
						k = 1;
					} if (randomzahl == 7){
						continue;
					} if (randomzahl == 8 && acht != 2){
						karte.rohstoffzahl = 8;
						acht++;
						k = 1;
					} if (randomzahl == 9 && neun != 2){
						karte.rohstoffzahl = 9;
						neun++;
						k = 1;
					} if (randomzahl == 10 && zehn != 2){
						karte.rohstoffzahl = 10;
						zehn++;
						k = 1;
					} if (randomzahl == 11 && elf != 2){
						karte.rohstoffzahl = 11;
						elf++;
						k = 1;
					} if (randomzahl == 12 && zwoelf != 1){
						karte.rohstoffzahl = 12;
						zwoelf++;
						k = 1;
					} 
				} while (k == 0);
		}
	}

	public void zahlenladen(JFrame spielbrett) {

		card1 = new JTextField("" + zahl1.rohstoffzahl);
		card1.setBounds(582, 187, 50, 50);
		card1.setHorizontalAlignment(JTextField.CENTER);
		card1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card1.setBorder(null);
		card1.setOpaque(false);
		card1.setEditable(false);
		spielbrett.getContentPane().add(card1);

		card2 = new JTextField("" + zahl2.rohstoffzahl);
		card2.setBounds(727, 187, 50, 50);
		card2.setHorizontalAlignment(JTextField.CENTER);
		card2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card2.setBorder(null);
		card2.setOpaque(false);
		card2.setEditable(false);
		spielbrett.getContentPane().add(card2);

		card3 = new JTextField("" + zahl3.rohstoffzahl);
		card3.setBounds(872, 187, 50, 50);
		card3.setHorizontalAlignment(JTextField.CENTER);
		card3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card3.setBorder(null);
		card3.setOpaque(false);
		card3.setEditable(false);
		spielbrett.getContentPane().add(card3);

		card4 = new JTextField("" + zahl4.rohstoffzahl);
		card4.setBounds(509, 312, 50, 50);
		card4.setHorizontalAlignment(JTextField.CENTER);
		card4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card4.setBorder(null);
		card4.setOpaque(false);
		card4.setEditable(false);
		spielbrett.getContentPane().add(card4);

		card5 = new JTextField("" + zahl5.rohstoffzahl);
		card5.setBounds(654, 312, 50, 50);
		card5.setHorizontalAlignment(JTextField.CENTER);
		card5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card5.setBorder(null);
		card5.setOpaque(false);
		card5.setEditable(false);
		spielbrett.getContentPane().add(card5);

		card6 = new JTextField("" + zahl6.rohstoffzahl);
		card6.setBounds(799, 312, 50, 50);
		card6.setHorizontalAlignment(JTextField.CENTER);
		card6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card6.setBorder(null);
		card6.setOpaque(false);
		card6.setEditable(false);
		spielbrett.getContentPane().add(card6);

		card7 = new JTextField("" + zahl7.rohstoffzahl);
		card7.setBounds(944, 312, 50, 50);
		card7.setHorizontalAlignment(JTextField.CENTER);
		card7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card7.setBorder(null);
		card7.setOpaque(false);
		card7.setEditable(false);
		spielbrett.getContentPane().add(card7);

		card8 = new JTextField("" + zahl8.rohstoffzahl);
		card8.setBounds(437, 437, 50, 50);
		card8.setHorizontalAlignment(JTextField.CENTER);
		card8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card8.setBorder(null);
		card8.setOpaque(false);
		card8.setEditable(false);
		spielbrett.getContentPane().add(card8);

		card9 = new JTextField("" + zahl9.rohstoffzahl);
		card9.setBounds(582, 437, 50, 50);
		card9.setHorizontalAlignment(JTextField.CENTER);
		card9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card9.setBorder(null);
		card9.setOpaque(false);
		card9.setEditable(false);
		spielbrett.getContentPane().add(card9);

		card10 = new JTextField("7");
		card10.setBounds(727, 437, 50, 50);
		card10.setHorizontalAlignment(JTextField.CENTER);
		card10.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card10.setBorder(null);
		card10.setOpaque(false);
		card10.setEditable(false);
		spielbrett.getContentPane().add(card10);

		card11 = new JTextField("" + zahl10.rohstoffzahl);
		card11.setBounds(872, 437, 50, 50);
		card11.setHorizontalAlignment(JTextField.CENTER);
		card11.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card11.setBorder(null);
		card11.setOpaque(false);
		card11.setEditable(false);
		spielbrett.getContentPane().add(card11);

		card12 = new JTextField("" + zahl11.rohstoffzahl);
		card12.setBounds(1017, 437, 50, 50);
		card12.setHorizontalAlignment(JTextField.CENTER);
		card12.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card12.setBorder(null);
		card12.setOpaque(false);
		card12.setEditable(false);
		spielbrett.getContentPane().add(card12);

		card13 = new JTextField("" + zahl12.rohstoffzahl);
		card13.setBounds(509, 562, 50, 50);
		card13.setHorizontalAlignment(JTextField.CENTER);
		card13.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card13.setBorder(null);
		card13.setOpaque(false);
		card13.setEditable(false);
		spielbrett.getContentPane().add(card13);

		card14 = new JTextField("" + zahl13.rohstoffzahl);
		card14.setBounds(654, 562, 50, 50);
		card14.setHorizontalAlignment(JTextField.CENTER);
		card14.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card14.setBorder(null);
		card14.setOpaque(false);
		card14.setEditable(false);
		spielbrett.getContentPane().add(card14);

		card15 = new JTextField("" + zahl14.rohstoffzahl);
		card15.setBounds(799, 562, 50, 50);
		card15.setHorizontalAlignment(JTextField.CENTER);
		card15.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card15.setBorder(null);
		card15.setOpaque(false);
		card15.setEditable(false);
		spielbrett.getContentPane().add(card15);

		card16 = new JTextField("" + zahl15.rohstoffzahl);
		card16.setBounds(944, 562, 50, 50);
		card16.setHorizontalAlignment(JTextField.CENTER);
		card16.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card16.setBorder(null);
		card16.setOpaque(false);
		card16.setEditable(false);
		spielbrett.getContentPane().add(card16);

		card17 = new JTextField("" + zahl16.rohstoffzahl);
		card17.setBounds(582, 687, 50, 50);
		card17.setHorizontalAlignment(JTextField.CENTER);
		card17.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card17.setBorder(null);
		card17.setOpaque(false);
		card17.setEditable(false);
		spielbrett.getContentPane().add(card17);

		card18 = new JTextField("" + zahl17.rohstoffzahl);
		card18.setBounds(727, 687, 50, 50);
		card18.setHorizontalAlignment(JTextField.CENTER);
		card18.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card18.setBorder(null);
		card18.setOpaque(false);
		card18.setEditable(false);
		spielbrett.getContentPane().add(card18);

		card19 = new JTextField("" + zahl18.rohstoffzahl);
		card19.setBounds(872, 687, 50, 50);
		card19.setHorizontalAlignment(JTextField.CENTER);
		card19.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card19.setBorder(null);
		card19.setOpaque(false);
		card19.setEditable(false);
		spielbrett.getContentPane().add(card19);
	}
	
	public class Zahlen {
		int rohstoffzahl = 0;
	}
}
