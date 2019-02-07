import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Client implements Runnable {
	
	public Thread brett;
	public JFrame spielbrett;
	public JLabel hintergrundbrett;
	public JButton dorfbauen;
	public JButton stadtbauen;
	public JButton ritterbauen;
	public JButton ritteraktivieren;
	public JButton strassebauen;
	public JButton karteaktivieren;
	public JButton forschen;
	public JButton handelanbieten;
	public JButton wuerfeln;
	public JButton zugbeenden;
	public JTextField wuerfel1;
	public JTextField wuerfel2;
	public JTextField wuerfel3;
	public JTextField aktionen;
	public JTextField spielertext;
	public JTextField aktuellerspielertext;
	public KartenZahlen kartenzahlen = new KartenZahlen();
	
	public JTextField holz;
	public JTextField stein;
	public JTextField lehm;
	public JTextField getreide;
	public JTextField wolle;
	public JTextField seide;
	public JTextField muenzen;
	public JTextField papier;
	public Client clientübergabe = this;
	
	public JFrame benutzerauswahl;
	public JButton farbauswahlgetroffen;
	public JTextField spieler1;
	public JTextField spieler2;
	public JTextField spieler3;
	public JTextField spieler4;
	public String name1;
	public String name2;
	public String name3;
	public String name4;
	public boolean ready1;
	public boolean ready2;
	public boolean ready3;
	public boolean ready4;	
	public String [] farbenauswahl = {"blau","grün","rot","gelb","lila","grau","orange"};
	public JComboBox cmbfarbenauswahl1;
	public JComboBox cmbfarbenauswahl2;
	public JComboBox cmbfarbenauswahl3;
	public JComboBox cmbfarbenauswahl4;
	
	public String fspieler1 = "rot";
	public String fspieler2 = "blau";
	public String fspieler3 = "grün";
	public String fspieler4 = "gelb";
	
	public int anfangswurf1 = 0;
	public int anfangswurf2 = 0;
	public int anfangswurf3 = 6;
	public int anfangswurf4 = 6;
	public int anfangswurfgetätigt = 0;
	
	private JFrame frame;
	private JLabel hintergrund1;
	private JTextField connectedvon;
	private JTextField spieleranzeige;
	private Wuerfelzahlen wuerfelzahlen = new Wuerfelzahlen();
	private CheckBoxen boxen = new CheckBoxen(wuerfelzahlen);
	private boolean auswahldorf = true;
	private boolean auswahlritter = true;
	private boolean auswahlstadt = true;
	private boolean auswahlweg = true;
	
	ImageIcon gruenRitter = new ImageIcon(getClass().getResource("/grün_Ritter.png"));
	ImageIcon gruenStadt = new ImageIcon(getClass().getResource("/grün_Stadt.png"));
	ImageIcon gruenDorf = new ImageIcon(getClass().getResource("/grün_Dorf.png"));
	ImageIcon gruenWeg = new ImageIcon(getClass().getResource("/grün_Weg.png"));
	
	ImageIcon rotRitter = new ImageIcon(getClass().getResource("/rot_Ritter.png"));
	ImageIcon rotStadt = new ImageIcon(getClass().getResource("/rot_Stadt.png"));
	ImageIcon rotDorf = new ImageIcon(getClass().getResource("/rot_Dorf.png"));
	ImageIcon rotWeg = new ImageIcon(getClass().getResource("/rot_Weg.png"));
	
	ImageIcon blauRitter = new ImageIcon(getClass().getResource("/blau_Ritter.png"));
	ImageIcon blauStadt = new ImageIcon(getClass().getResource("/blau_Stadt.png"));
	ImageIcon blauDorf = new ImageIcon(getClass().getResource("/blau_Dorf.png"));
	ImageIcon blauWeg = new ImageIcon(getClass().getResource("/blau_Weg.png"));
	
	
	
	public class Wuerfelzahlen {
		
		int zahl1;
		int zahl2;
		int zahl3;
		int zahl4;
		int zahl5;
		int zahl6;
		int zahl7;
		int zahl8;
		int zahl9;
		int zahl10;
		int zahl11;
		int zahl12;
		int zahl13;
		int zahl14;
		int zahl15;
		int zahl16;
		int zahl17;
		int zahl18;
		int zahl19;
		int zahl20;
		int zahl21;
		int zahl22;
		int zahl23;
		int zahl24;
		int zahl25;
		int zahl26;
		int zahl27;
		int zahl28;
		int zahl29;
		
	}

	private Socket client;
	private PrintWriter writer;
	private BufferedReader reader;
	String ip;
	Spieler spieler;
	int aktuellerspieler = 1;
	int dieserspieler = 0;
	int spieleranzahl = 1;
	int connected = 0;
	boolean hatgewuerfelt = false;
	boolean anfangsphaserum = false;
	boolean auswahlphaserum = false;
	boolean wurfelphaserum = false;
	
	Werfängtan anfangen = new Werfängtan(clientübergabe);
	
	
	public Client(String ip) {
		this.ip = ip;
	}

	@Override
	public void run() {
		starting();
	}
	
	//Connected Feld
	public void starting() {

		frame = new JFrame("Menü");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(358, 380);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

		spieleranzeige = new JTextField("");
		spieleranzeige.setBounds(60, 140, 240, 35);
		spieleranzeige.setHorizontalAlignment(JTextField.CENTER);
		spieleranzeige.setEditable(false);
		spieleranzeige.setOpaque(false);
		frame.add(spieleranzeige);

		connectedvon = new JTextField("");
		connectedvon.setBounds(60, 170, 240, 35);
		connectedvon.setHorizontalAlignment(JTextField.CENTER);
		connectedvon.setEditable(false);
		connectedvon.setOpaque(false);
		frame.add(connectedvon);

		hintergrund1 = new JLabel("");
		ImageIcon img = new ImageIcon(getClass().getResource("/Siedler2.jpg"));
		hintergrund1.setIcon(img);
		hintergrund1.setBounds(1, 1, 369, 350);
		frame.add(hintergrund1);

		frame.repaint();

		if (!connectToServer()) {

		}

		Thread t = new Thread(new MessagesFromServerListener());
		t.start();

	}

	public void sendMessageToServer(String message) {
		writer.println(message);
		writer.flush();
	}

	public boolean connectToServer() {

		try {

			client = new Socket(ip, 5555);
			System.out.println("Client gestartet!");

			// Streams
			writer = new PrintWriter(client.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			// -----------------------------------

			return true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	public class MessagesFromServerListener implements Runnable {
		
		public void werhatgewonnen() {
			
				if (anfangswurf4 < anfangswurf3 || anfangswurf4 < anfangswurf2 || anfangswurf4 < anfangswurf1 || anfangswurf4 == 0) {
						System.out.println("Spieler 4 hat nicht gewonnen!"); }  
				else if (anfangswurf4 == anfangswurf3 || anfangswurf4 == anfangswurf2 || anfangswurf4 == anfangswurf1) {
						System.out.println("Spieler 4 hat die gleiche Zahl"); } 
				else { 	System.out.println("Spieler 4 hat gewonnen"); }
				
				if (anfangswurf3 < anfangswurf4 || anfangswurf3 < anfangswurf2 || anfangswurf3 < anfangswurf1 || anfangswurf3 == 0) {
						System.out.println("Spieler 3 hat nicht gewonnen!"); }
				else if (anfangswurf3 == anfangswurf4 || anfangswurf3 == anfangswurf2 || anfangswurf3 == anfangswurf1) {
						System.out.println("Spieler 3 hat die gleiche Zahl"); } 
				else { 	System.out.println("Spieler 3 hat gewonnen"); }
						 
				if (anfangswurf2 < anfangswurf4 || anfangswurf2 < anfangswurf3 || anfangswurf2 < anfangswurf1 || anfangswurf2 == 0) {
						System.out.println("Spieler 2 hat nicht gewonnen!"); }
				else if (anfangswurf2 == anfangswurf4 || anfangswurf2 == anfangswurf3 || anfangswurf2 == anfangswurf1) {
						System.out.println("Spieler 2 hat die gleiche Zahl"); } 
				else { 	System.out.println("Spieler 2 hat gewonnen");}
								
				if (anfangswurf1 < anfangswurf4 || anfangswurf1 < anfangswurf3 || anfangswurf1 < anfangswurf2) {
								System.out.println("Spieler 1 hat nicht gewonnen!"); } 
				else if (anfangswurf1 == anfangswurf4 || anfangswurf1 == anfangswurf3 || anfangswurf1 == anfangswurf2) {
								System.out.println("Spieler 1 hat die gleiche Zahl"); } 
				else { System.out.println("Spieler 1 hat gewonnen");}}

		@Override
		public void run() {
			String message;
			try {
				while ((message = reader.readLine()) != null) {
					System.out.println("Von Server erhalten: " + message);
					
					String ohnezahlen = message.replaceAll("[0-9]" , "");
					String ohnebuchstaben = message.replaceAll("[a-z]", "");
					
					
					// Würfel aktualisieren
					if (message.startsWith("111")) {
						if(Integer.parseInt(message.substring(3,4)) > 3) {
							wuerfel1.setBackground(Color.BLACK);
						} else {
							switch (Integer.parseInt(message.substring(3,4))){
							case 1:
								wuerfel1.setBackground(Color.green.darker());
								break;
							case 2:
								wuerfel1.setBackground(Color.lightGray.darker());
								break;
							case 3:
								wuerfel1.setBackground(Color.yellow);
								break;
							}
						}
					}
					if (message.startsWith("112")) {
						wuerfel2.setText(message.substring(3,4));
					}
					if (message.startsWith("113")) {
						wuerfel3.setText(message.substring(3,4));
					}
					
					
					
					// Würfel aktualisieren
					/*String zahlen = "0";
					int k = 0;
					int i = 0;
					Pattern p = Pattern.compile("[+-]?[0-9]+");
					Matcher m = p.matcher(message);
					while(m.find()){
						zahlen = message.substring(m.start(), m.end());
					}
					k = Integer.parseInt(zahlen);
					i = k/100;
					if (i != 0){
						int a = k/100;
						if (a > 3) {
							wuerfel1.setBackground(Color.BLACK);
						} else {
							switch (a) {
							case 1:
								wuerfel1.setBackground(Color.green.darker());
								break;
							case 2:
								wuerfel1.setBackground(Color.lightGray.darker());
								break;
							case 3:
								wuerfel1.setBackground(Color.yellow);
								break;
							}
						}
						k %= 100;
						int b = k/10;
						wuerfel2.setText("" + b);
						k %= 10;
						int c = k;
						wuerfel3.setText("" + c);
					}*/
					
					
					// für erste frame
					if (message.equals("anzahl" + 1)) {	spieleranzahl = 1; ready1=false;ready2=true;ready3=true;ready4=true; }
					if (message.equals("anzahl" + 2)) {	spieleranzahl = 2; ready1=false;ready2=false;ready3=true;ready4=true; }
					if (message.equals("anzahl" + 3)) {	spieleranzahl = 3; ready1=false;ready2=false;ready3=false;ready4=true; }
					if (message.equals("anzahl" + 4)) {	spieleranzahl = 4; ready1=false;ready2=false;ready3=false;ready4=false;}

					if (message.equals("connected" + 1)) { connectedvon.setText("Connected 1 von " + spieleranzahl); connected = 1; checkSpielstart(); }
					if (message.equals("connected" + 2)) { connectedvon.setText("Connected 2 von " + spieleranzahl); connected = 2; checkSpielstart(); }	
					if (message.equals("connected" + 3)) { connectedvon.setText("Connected 3 von " + spieleranzahl); connected = 3; checkSpielstart(); }
					if (message.equals("connected" + 4)) { connectedvon.setText("Connected 4 von " + spieleranzahl); connected = 4; checkSpielstart(); }
					
					// Zahlen auf Rohstofffeldern matchen 
					if (ohnezahlen.equals("kartea")){
						kartenzahlen.zahl1.rohstoffzahl = Integer.parseInt(ohnebuchstaben);
						wuerfelzahlen.zahl1 = Integer.parseInt(ohnebuchstaben);}
					if (ohnezahlen.equals("karteb")){
						kartenzahlen.zahl2.rohstoffzahl = Integer.parseInt(ohnebuchstaben);
						wuerfelzahlen.zahl2 = Integer.parseInt(ohnebuchstaben);}
					if (ohnezahlen.equals("kartec")){
						kartenzahlen.zahl3.rohstoffzahl = Integer.parseInt(ohnebuchstaben);
						wuerfelzahlen.zahl3 = Integer.parseInt(ohnebuchstaben);}
					if (ohnezahlen.equals("karted")){
						kartenzahlen.zahl4.rohstoffzahl = Integer.parseInt(ohnebuchstaben);
						wuerfelzahlen.zahl4 = Integer.parseInt(ohnebuchstaben);}
					if (ohnezahlen.equals("kartee")){
						kartenzahlen.zahl5.rohstoffzahl = Integer.parseInt(ohnebuchstaben);
						wuerfelzahlen.zahl5 = Integer.parseInt(ohnebuchstaben);}
					if (ohnezahlen.equals("kartef")){
						kartenzahlen.zahl6.rohstoffzahl = Integer.parseInt(ohnebuchstaben);
						wuerfelzahlen.zahl6 = Integer.parseInt(ohnebuchstaben);}
					if (ohnezahlen.equals("karteg")){
						kartenzahlen.zahl7.rohstoffzahl = Integer.parseInt(ohnebuchstaben);
						wuerfelzahlen.zahl7 = Integer.parseInt(ohnebuchstaben);}
					if (ohnezahlen.equals("karteh")){
						kartenzahlen.zahl8.rohstoffzahl = Integer.parseInt(ohnebuchstaben);
						wuerfelzahlen.zahl8 = Integer.parseInt(ohnebuchstaben);}
					if (ohnezahlen.equals("kartei")){
						kartenzahlen.zahl9.rohstoffzahl = Integer.parseInt(ohnebuchstaben);
						wuerfelzahlen.zahl9 = Integer.parseInt(ohnebuchstaben);}
					if (ohnezahlen.equals("kartej")){
						kartenzahlen.zahl10.rohstoffzahl = Integer.parseInt(ohnebuchstaben);
						wuerfelzahlen.zahl10 = Integer.parseInt(ohnebuchstaben);}
					if (ohnezahlen.equals("kartek")){
						kartenzahlen.zahl11.rohstoffzahl = Integer.parseInt(ohnebuchstaben);
						wuerfelzahlen.zahl11 = Integer.parseInt(ohnebuchstaben);}
					if (ohnezahlen.equals("kartel")){
						kartenzahlen.zahl12.rohstoffzahl = Integer.parseInt(ohnebuchstaben);
						wuerfelzahlen.zahl12 = Integer.parseInt(ohnebuchstaben);}
					if (ohnezahlen.equals("kartem")){
						kartenzahlen.zahl13.rohstoffzahl = Integer.parseInt(ohnebuchstaben);
						wuerfelzahlen.zahl13 = Integer.parseInt(ohnebuchstaben);}
					if (ohnezahlen.equals("karten")){
						kartenzahlen.zahl14.rohstoffzahl = Integer.parseInt(ohnebuchstaben);
						wuerfelzahlen.zahl14 = Integer.parseInt(ohnebuchstaben);}
					if (ohnezahlen.equals("karteo")){
						kartenzahlen.zahl15.rohstoffzahl = Integer.parseInt(ohnebuchstaben);
						wuerfelzahlen.zahl15 = Integer.parseInt(ohnebuchstaben);}
					if (ohnezahlen.equals("kartep")){
						kartenzahlen.zahl16.rohstoffzahl = Integer.parseInt(ohnebuchstaben);
						wuerfelzahlen.zahl16 = Integer.parseInt(ohnebuchstaben);}
					if (ohnezahlen.equals("karteq")){
						kartenzahlen.zahl17.rohstoffzahl = Integer.parseInt(ohnebuchstaben);
						wuerfelzahlen.zahl17 = Integer.parseInt(ohnebuchstaben);}
					if (ohnezahlen.equals("karter")){
						kartenzahlen.zahl18.rohstoffzahl = Integer.parseInt(ohnebuchstaben);
						wuerfelzahlen.zahl18 = Integer.parseInt(ohnebuchstaben);}
				
					// für Spielverlauf
					if (message.equals("spieler" + 1)) {
						spieleranzeige.setText("Spieler 1");
						dieserspieler = 1;
						anfangen.spielerzahl = 1;
						spieler = new Spieler(1);
						spieler.spielerfarbeanpassen();}
					if (message.equals("spieler" + 2)) {
						spieleranzeige.setText("Spieler 2");
						dieserspieler = 2;
						anfangen.spielerzahl = 2;
						spieler = new Spieler(2);
						spieler.spielerfarbeanpassen();}
					if (message.equals("spieler" + 3)) {
						spieleranzeige.setText("Spieler 3");
						dieserspieler = 3;
						anfangen.spielerzahl = 3;
						spieler = new Spieler(3);
						spieler.spielerfarbeanpassen();}
					if (message.equals("spieler" + 4)) {
						spieleranzeige.setText("Spieler 4");
						dieserspieler = 4;
						anfangen.spielerzahl = 4;
						spieler = new Spieler(4);
						spieler.spielerfarbeanpassen();}
					
				
					//Farbauswahl aktualisieren
					if (message.equals("spieler1rot")) { spieler1.setBackground(Color.red); fspieler1 = "rot"; if(dieserspieler == 1) {spieler.spielerfarbe = "rot";}}
					if (message.equals("spieler1blau")) { spieler1.setBackground(Color.CYAN); fspieler1 = "blau"; if(dieserspieler == 1) {spieler.spielerfarbe = "blau";}}
					if (message.equals("spieler1grau")) { spieler1.setBackground(Color.gray); fspieler1 = "grau"; if(dieserspieler == 1) {spieler.spielerfarbe = "grau";}}
					if (message.equals("spieler1lila")) { spieler1.setBackground(Color.magenta); fspieler1 = "lila"; if(dieserspieler == 1) {spieler.spielerfarbe = "lila";}}
					if (message.equals("spieler1gelb")) { spieler1.setBackground(Color.yellow); fspieler1 = "gelb"; if(dieserspieler == 1) {spieler.spielerfarbe = "gelb";}}
					if (message.equals("spieler1orange")) { spieler1.setBackground(Color.orange); fspieler1 = "orange"; if(dieserspieler == 1) {spieler.spielerfarbe = "orange";}}
					if (message.equals("spieler1grün")) { spieler1.setBackground(Color.green); fspieler1 = "grün"; if(dieserspieler == 1) {spieler.spielerfarbe = "grün";}}
					
					if (message.equals("spieler2rot")) { spieler2.setBackground(Color.red); fspieler2 = "rot"; if(dieserspieler == 2) {spieler.spielerfarbe = "rot";}} 
					if (message.equals("spieler2blau")) { spieler2.setBackground(Color.CYAN); fspieler2 = "blau"; if(dieserspieler == 2) {spieler.spielerfarbe = "blau";}}
					if (message.equals("spieler2grau")) { spieler2.setBackground(Color.gray); fspieler2 = "grau"; if(dieserspieler == 2) {spieler.spielerfarbe = "grau";}}
					if (message.equals("spieler2lila")) { spieler2.setBackground(Color.magenta); fspieler2 = "lila"; if(dieserspieler == 2) {spieler.spielerfarbe = "lila";}}
					if (message.equals("spieler2gelb")) { spieler2.setBackground(Color.yellow); fspieler2 = "gelb"; if(dieserspieler == 2) {spieler.spielerfarbe = "gelb";}}
					if (message.equals("spieler2orange")) { spieler2.setBackground(Color.orange); fspieler2 = "orange"; if(dieserspieler == 2) {spieler.spielerfarbe = "orange";}}
					if (message.equals("spieler2grün")) { spieler2.setBackground(Color.green); fspieler2 = "grün"; if(dieserspieler == 2) {spieler.spielerfarbe = "grün";}}
					
					if (message.equals("spieler3rot")) { spieler3.setBackground(Color.red); fspieler3 = "rot"; if(dieserspieler == 3) {spieler.spielerfarbe = "rot";}}
					if (message.equals("spieler3blau")) { spieler3.setBackground(Color.CYAN); fspieler3 = "blau"; if(dieserspieler == 3) {spieler.spielerfarbe = "blau";}}
					if (message.equals("spieler3grau")) { spieler3.setBackground(Color.gray); fspieler3 = "grau"; if(dieserspieler == 3) {spieler.spielerfarbe = "grau";}}
					if (message.equals("spieler3lila")) { spieler3.setBackground(Color.magenta); fspieler3 = "lila"; if(dieserspieler == 3) {spieler.spielerfarbe = "lila";}}
					if (message.equals("spieler3gelb")) { spieler3.setBackground(Color.yellow); fspieler3 = "gelb"; if(dieserspieler == 3) {spieler.spielerfarbe = "gelb";}}
					if (message.equals("spieler3orange")) { spieler3.setBackground(Color.orange); fspieler3 = "orange"; if(dieserspieler == 3) {spieler.spielerfarbe = "orange";}}
					if (message.equals("spieler3grün")) { spieler3.setBackground(Color.green); fspieler3 = "grün"; if(dieserspieler == 3) {spieler.spielerfarbe = "grün";}}
					
					if (message.equals("spieler4rot")) { spieler4.setBackground(Color.red); fspieler4 = "rot"; if(dieserspieler == 4) {spieler.spielerfarbe = "rot";}}
					if (message.equals("spieler4blau")) { spieler4.setBackground(Color.CYAN); fspieler4 = "blau"; if(dieserspieler == 4) {spieler.spielerfarbe = "blau";}}
					if (message.equals("spieler4grau")) { spieler4.setBackground(Color.gray); fspieler4 = "grau"; if(dieserspieler == 4) {spieler.spielerfarbe = "grau";}}
					if (message.equals("spieler4lila")) { spieler4.setBackground(Color.magenta); fspieler4 = "lila"; if(dieserspieler == 4) {spieler.spielerfarbe = "lila";}}
					if (message.equals("spieler4gelb")) { spieler4.setBackground(Color.yellow); fspieler4 = "gelb"; if(dieserspieler == 4) {spieler.spielerfarbe = "gelb";}}
					if (message.equals("spieler4orange")) { spieler4.setBackground(Color.orange); fspieler4 = "orange"; if(dieserspieler == 4) {spieler.spielerfarbe = "orange";}}
					if (message.equals("spieler4grün")) { spieler4.setBackground(Color.green); fspieler4 = "grün"; if(dieserspieler == 4) {spieler.spielerfarbe = "grün";}}
				
					// Farbauswahlfenster Namen aktualisieren
					if(message.startsWith("05")) {
						if(message.substring(2,3).equals("1")) {
							spieler1.setText(message.substring(3, message.length()));
							name1 = message.substring(3, message.length());
							if(dieserspieler == 1) { spielertext.setText(message.substring(3, message.length()));}}
						if(message.substring(2,3).equals("2")) {
							spieler2.setText(message.substring(3, message.length()));
							name2 = message.substring(3, message.length());
							if(dieserspieler == 2) { spielertext.setText(message.substring(3, message.length()));}}
						if(message.substring(2,3).equals("3")) {
							spieler3.setText(message.substring(3, message.length()));
							name3 = message.substring(3, message.length());
							if(dieserspieler == 3) { spielertext.setText(message.substring(3, message.length()));}}
						if(message.substring(2,3).equals("4")) {
							spieler4.setText(message.substring(3, message.length()));
							name4 = message.substring(3, message.length());
							if(dieserspieler == 4) { spielertext.setText(message.substring(3, message.length()));}}
						}
					
					// Alle haben Farbauswahl beendet
					if (ohnezahlen.equals("apr")) {
						if(message.substring(3,4).equals("1")) {
							ready1 = true;
							if(ready1&&ready2&&ready3&&ready4) {
								auswahlphaserum = true;
								benutzerauswahl.dispose();
								anfangen.spieleranzahl = spieleranzahl;
								Thread werwürfeltbesser = new Thread(anfangen);
								werwürfeltbesser.start();
								aktuellerspielertext.setText("Aktueller Spieler: " + name1);
								/*spielbrett.setVisible(true);*/}}
						if(message.substring(3,4).equals("2")) {
							ready2 = true;
							if(ready1&&ready2&&ready3&&ready4) {
								auswahlphaserum = true;
								benutzerauswahl.dispose();
								anfangen.spieleranzahl = spieleranzahl;
								Thread werwürfeltbesser = new Thread(anfangen);
								werwürfeltbesser.start();
								aktuellerspielertext.setText("Aktueller Spieler: " + name1);
								/*spielbrett.setVisible(true);*/}}
						if(message.substring(3,4).equals("3")) {
							ready3 = true;
							if(ready1&&ready2&&ready3&&ready4) {
								auswahlphaserum = true;
								benutzerauswahl.dispose();
								anfangen.spieleranzahl = spieleranzahl;
								Thread werwürfeltbesser = new Thread(anfangen);
								werwürfeltbesser.start();
								aktuellerspielertext.setText("Aktueller Spieler: " + name1);
								/*spielbrett.setVisible(true);*/}}
						if(message.substring(3,4).equals("4")) {
							ready4 = true;
							if(ready1&&ready2&&ready3&&ready4) {
								auswahlphaserum = true;
								benutzerauswahl.dispose();
								anfangen.spieleranzahl = spieleranzahl;
								Thread werwürfeltbesser = new Thread(anfangen);
								werwürfeltbesser.start();
								aktuellerspielertext.setText("Aktueller Spieler: " + name1);
								/*spielbrett.setVisible(true);*/}}
					}
					
					if (message.startsWith("07")) {
						if (message.substring(2,3).equals("1")) {
							anfangen.wurfspieler1.setText(name1 + " hat eine "+ message.substring(3,4) + " gewürfelt");
							anfangswurf1 = Integer.parseInt(message.substring(3,4));
							anfangswurfgetätigt++;
							if (anfangswurfgetätigt == spieleranzahl) {
								werhatgewonnen();}}
						if (message.substring(2,3).equals("2")) {
							anfangen.wurfspieler2.setText(name2 + " hat eine "+ message.substring(3,4) + " gewürfelt");
							anfangswurf2 = Integer.parseInt(message.substring(3,4));
							anfangswurfgetätigt++;
							if (anfangswurfgetätigt == spieleranzahl) {
								werhatgewonnen();}}
						if (message.substring(2,3).equals("3")) {
							anfangen.wurfspieler3.setText(name3 + " hat eine "+ message.substring(3,4) + " gewürfelt");
							anfangswurf3 = Integer.parseInt(message.substring(3,4));
							anfangswurfgetätigt++;
							if (anfangswurfgetätigt == spieleranzahl) {
								werhatgewonnen();}}
						if (message.substring(2,3).equals("4")) {
							anfangen.wurfspieler4.setText(name4 + " hat eine "+ message.substring(3,4) + " gewürfelt");
							anfangswurf4 = Integer.parseInt(message.substring(3,4));
							anfangswurfgetätigt++;
							if (anfangswurfgetätigt == spieleranzahl) {
								werhatgewonnen();}}
					}
					
					// Aktueller Spieler aktualisieren
					if (message.equals("beenden")){
						if(aktuellerspieler == spieleranzahl){
							aktuellerspieler = 1;
							hatgewuerfelt = false;
							aktuellerspielertext.setText("Aktueller Spieler: " + name1);
						} else {
							aktuellerspieler++;
							hatgewuerfelt = false;
							if (aktuellerspieler == 2) { aktuellerspielertext.setText("Aktueller Spieler: "+name2); }
							if (aktuellerspieler == 3) { aktuellerspielertext.setText("Aktueller Spieler: "+name3); }
							if (aktuellerspieler == 4) { aktuellerspielertext.setText("Aktueller Spieler: "+name4); }
						}
					}
					
					//dorfbau übertragen
					if (ohnezahlen.equals("dorfbauen")){
						  if (aktuellerspieler == 1 && aktuellerspieler != dieserspieler) {
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).bild.setIcon(rotDorf);
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).bild.setVisible(true);
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).istbebaut = true;
							aktualisieren();
						} if (aktuellerspieler == 2 && aktuellerspieler != dieserspieler) {
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).bild.setIcon(blauDorf);
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).bild.setVisible(true);
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).istbebaut = true;
							aktualisieren();
						} if (aktuellerspieler == 3 && aktuellerspieler != dieserspieler) {
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).bild.setIcon(gruenDorf);
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).bild.setVisible(true);
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).istbebaut = true;
							aktualisieren();
						}
						
					}
					
					//Stadtbau übertragen
					if (ohnezahlen.equals("stadtbauen")){
						  if (aktuellerspieler == 1 && aktuellerspieler != dieserspieler) {
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).bild.setIcon(rotStadt);
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).bild.setVisible(true);
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).istbebaut = true;
							aktualisieren();
						} if (aktuellerspieler == 2 && aktuellerspieler != dieserspieler) {
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).bild.setIcon(blauStadt);
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).bild.setVisible(true);
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).istbebaut = true;
							aktualisieren();
						} if (aktuellerspieler == 3 && aktuellerspieler != dieserspieler) {
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).bild.setIcon(gruenStadt);
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).bild.setVisible(true);
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).istbebaut = true;
							aktualisieren();
						}
						
					}
					
					//Ritterbau übertragen
					if (ohnezahlen.equals("ritterbauen")){
						  if (aktuellerspieler == 1 && aktuellerspieler != dieserspieler) {
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).bild.setIcon(rotRitter);
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).bild.setVisible(true);
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).istbebaut = true;
							aktualisieren();
						} if (aktuellerspieler == 2 && aktuellerspieler != dieserspieler) {
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).bild.setIcon(blauRitter);
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).bild.setVisible(true);
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).istbebaut = true;
							aktualisieren();
						} if (aktuellerspieler == 3 && aktuellerspieler != dieserspieler) {
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).bild.setIcon(gruenRitter);
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).bild.setVisible(true);
							boxen.kreuz.get(Integer.parseInt(ohnebuchstaben)-1).istbebaut = true;
							aktualisieren();
						}
					}
				}
			} catch (IOException e) {
				System.out.println("Nachricht konnte nicht empfangen werden!");
				e.printStackTrace();
			}
		}
	}
	
	public void checkSpielstart(){
		
		if(connected == spieleranzahl) {
			frame.dispose();
			brett = new Thread(new Spielbrett());
			brett.start();
			Thread anfangsauswahl = new Thread(new Anfangsauswahl());
			anfangsauswahl.start();
			if(dieserspieler == 1) {
				Thread schicken = new Thread(new Kartenschicken());
				schicken.start();
			}
		}
	}

	public class Anfangsauswahl implements Runnable, ActionListener {
		
		private ItemHandler handler = new ItemHandler(clientübergabe);

		@Override
		public void run() {
			benutzerauswahl();
		}
		
		public void benutzerauswahl() {
			
			benutzerauswahl = new JFrame("Auswahl");
			benutzerauswahl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			benutzerauswahl.setSize(300, 360);
			benutzerauswahl.setLocationRelativeTo(null);
			benutzerauswahl.setLayout(null);
			benutzerauswahl.setResizable(false);
			benutzerauswahl.setVisible(true);
			
			spieler1 = new JTextField("Spieler 1");
			spieler1.setBounds(30,50,70,35);
			if(dieserspieler != 1) {
				spieler1.setEditable(false);}
			spieler1.setBackground(Color.red);
			spieler1.setVisible(true);
			benutzerauswahl.add(spieler1);
			
			spieler2 = new JTextField("Spieler 2");
			spieler2.setBounds(30,100,70,35);
			if(dieserspieler != 2) {
				spieler2.setEditable(false);}
			spieler2.setBackground(Color.CYAN);
			spieler2.setVisible(true);
			benutzerauswahl.add(spieler2);
			
			spieler3 = new JTextField("Spieler 3");
			spieler3.setBounds(30,150,70,35);
			if(dieserspieler != 3) {
				spieler3.setEditable(false);}
			spieler3.setBackground(Color.green);
			spieler3.setVisible(true);
			benutzerauswahl.add(spieler3);
			
			spieler4 = new JTextField("Spieler 4");
			spieler4.setBounds(30,200,70,35);
			if(dieserspieler != 4) {
				spieler4.setEditable(false);}
			spieler4.setBackground(Color.yellow);
			spieler4.setVisible(true);
			benutzerauswahl.add(spieler4);

			cmbfarbenauswahl1 = new JComboBox(farbenauswahl);
			cmbfarbenauswahl1.setBounds(220, 50, 40, 35);
			cmbfarbenauswahl1.addActionListener(this);
			cmbfarbenauswahl1.setVisible(true);
			cmbfarbenauswahl1.setSelectedItem("rot");
			cmbfarbenauswahl1.setEnabled(false);
			if(dieserspieler == 1){
				cmbfarbenauswahl1.setEnabled(true);}
			cmbfarbenauswahl1.addItemListener(handler);
			benutzerauswahl.add(cmbfarbenauswahl1);
			
			cmbfarbenauswahl2 = new JComboBox(farbenauswahl);
			cmbfarbenauswahl2.setBounds(220, 100, 40, 35);
			cmbfarbenauswahl2.addActionListener(this);
			cmbfarbenauswahl2.setVisible(true);
			cmbfarbenauswahl2.setSelectedItem("blau");
			cmbfarbenauswahl2.setEnabled(false);
			if(dieserspieler == 2){
				cmbfarbenauswahl2.setEnabled(true);}
			cmbfarbenauswahl2.addItemListener(handler);
			benutzerauswahl.add(cmbfarbenauswahl2);
			
			cmbfarbenauswahl3 = new JComboBox(farbenauswahl);
			cmbfarbenauswahl3.setBounds(220, 150, 40, 35);
			cmbfarbenauswahl3.addActionListener(this);
			cmbfarbenauswahl3.setVisible(true);
			cmbfarbenauswahl3.setSelectedItem("grün");
			cmbfarbenauswahl3.setEnabled(false);
			if(dieserspieler == 3){
				cmbfarbenauswahl3.setEnabled(true);}
			cmbfarbenauswahl3.addItemListener(handler);
			benutzerauswahl.add(cmbfarbenauswahl3);
			
			cmbfarbenauswahl4 = new JComboBox(farbenauswahl);
			cmbfarbenauswahl4.setBounds(220, 200, 40, 35);
			cmbfarbenauswahl4.addActionListener(this);
			cmbfarbenauswahl4.setVisible(true);
			cmbfarbenauswahl4.setSelectedItem("gelb");
			cmbfarbenauswahl4.setEnabled(false);
			if(dieserspieler == 4){
				cmbfarbenauswahl4.setEnabled(true);}
			cmbfarbenauswahl4.addItemListener(handler);
			benutzerauswahl.add(cmbfarbenauswahl4);
			
			farbauswahlgetroffen = new JButton("Let's Go!!");
			farbauswahlgetroffen.setBounds(85, 260, 120, 35);
			farbauswahlgetroffen.addActionListener(this);
			benutzerauswahl.add(farbauswahlgetroffen);

			benutzerauswahl.repaint();
			
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(arg0.getSource() == farbauswahlgetroffen) {
			//// Noch offen

				if (dieserspieler == 1) {
					cmbfarbenauswahl1.setEnabled(false);
					sendMessageToServer("051"+ spieler1.getText());
					sendMessageToServer("apr1");
				}
				if (dieserspieler == 2) {
					cmbfarbenauswahl2.setEnabled(false);
					sendMessageToServer("052"+ spieler2.getText());
					sendMessageToServer("apr2");
				}
				if (dieserspieler == 3) {
					cmbfarbenauswahl3.setEnabled(false);
					sendMessageToServer("053"+ spieler3.getText());
					sendMessageToServer("apr3");
				}
				if (dieserspieler == 4) {
					cmbfarbenauswahl4.setEnabled(false);
					sendMessageToServer("054"+ spieler4.getText());
					sendMessageToServer("apr4");
				}
			}
		}
	}
	
	
	//Listener für Combobox, schickt ausgewählte Farbe an Server, der wiederrum an alle Clients
/*	private class ItemHandler implements ItemListener { 
		
		@Override
		public void itemStateChanged(ItemEvent event) {
			
			if(event.getStateChange() == 1) {
				
				//Spieler 1 Auswahl
				if(event.getSource() == cmbfarbenauswahl1) {
					
					if(event.getItem().equals("rot")) {
						if(fspieler1!="rot" && fspieler2!="rot" && fspieler3!="rot" && fspieler4!="rot") {
							sendMessageToServer("spieler1rot");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("blau")) {
						if(fspieler1!="blau" && fspieler2!="blau" && fspieler3!="blau" && fspieler4!="blau") {
							sendMessageToServer("spieler1blau");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("orange")) {
						if(fspieler1!="orange" && fspieler2!="orange" && fspieler3!="orange" && fspieler4!="orange") {
							sendMessageToServer("spieler1orange");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("grün")) {
						if(fspieler1!="grün" && fspieler2!="grün" && fspieler3!="grün" && fspieler4!="grün") {
							sendMessageToServer("spieler1grün");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("gelb")) {
						if(fspieler1!="gelb" && fspieler2!="gelb" && fspieler3!="gelb" && fspieler4!="gelb") {
							sendMessageToServer("spieler1gelb");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("lila")) {
						if(fspieler1!="lila" && fspieler2!="lila" && fspieler3!="lila" && fspieler4!="lila") {
							sendMessageToServer("spieler1lila");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("grau")) {
						if(fspieler1!="grau" && fspieler2!="grau" && fspieler3!="grau" && fspieler4!="grau") {
							sendMessageToServer("spieler1grau");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}}
				
				//Spieler 2 Auswahl
				if(event.getSource() == cmbfarbenauswahl2) {
					
					if(event.getItem().equals("rot")) {
						if(fspieler1!="rot" && fspieler2!="rot" && fspieler3!="rot" && fspieler4!="rot") {
							sendMessageToServer("spieler2rot");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("blau")) {
						if(fspieler1!="blau" && fspieler2!="blau" && fspieler3!="blau" && fspieler4!="blau") {
							sendMessageToServer("spieler2blau");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("orange")) {
						if(fspieler1!="orange" && fspieler2!="orange" && fspieler3!="orange" && fspieler4!="orange") {
							sendMessageToServer("spieler2orange");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("grün")) {
						if(fspieler1!="grün" && fspieler2!="grün" && fspieler3!="grün" && fspieler4!="grün") {
							sendMessageToServer("spieler2grün");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("gelb")) {
						if(fspieler1!="gelb" && fspieler2!="gelb" && fspieler3!="gelb" && fspieler4!="gelb") {
							sendMessageToServer("spieler2gelb");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("lila")) {
						if(fspieler1!="lila" && fspieler2!="lila" && fspieler3!="lila" && fspieler4!="lila") {
							sendMessageToServer("spieler2lila");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("grau")) {
						if(fspieler1!="grau" && fspieler2!="grau" && fspieler3!="grau" && fspieler4!="grau") {
							sendMessageToServer("spieler2grau");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}}
				
				//Spieler 3 Auswahl
				if(event.getSource() == cmbfarbenauswahl3) {
					
					if(event.getItem().equals("rot")) {
						if(fspieler1!="rot" && fspieler2!="rot" && fspieler3!="rot" && fspieler4!="rot") {
							sendMessageToServer("spieler3rot");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("blau")) {
						if(fspieler1!="blau" && fspieler2!="blau" && fspieler3!="blau" && fspieler4!="blau") {
							sendMessageToServer("spieler3blau");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("orange")) {
						if(fspieler1!="orange" && fspieler2!="orange" && fspieler3!="orange" && fspieler4!="orange") {
							sendMessageToServer("spieler3orange");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("grün")) {
						if(fspieler1!="grün" && fspieler2!="grün" && fspieler3!="grün" && fspieler4!="grün") {
							sendMessageToServer("spieler3grün");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("gelb")) {
						if(fspieler1!="gelb" && fspieler2!="gelb" && fspieler3!="gelb" && fspieler4!="gelb") {
							sendMessageToServer("spieler3gelb");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("lila")) {
						if(fspieler1!="lila" && fspieler2!="lila" && fspieler3!="lila" && fspieler4!="lila") {
							sendMessageToServer("spieler3lila");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("grau")) {
						if(fspieler1!="grau" && fspieler2!="grau" && fspieler3!="grau" && fspieler4!="grau") {
							sendMessageToServer("spieler3grau");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}}
				
				// Spieler 4 Auswahl
				if(event.getSource() == cmbfarbenauswahl4) {
					
					if(event.getItem().equals("rot")) {
						if(fspieler1!="rot" && fspieler2!="rot" && fspieler3!="rot" && fspieler4!="rot") {
							sendMessageToServer("spieler4rot");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("blau")) {
						if(fspieler1!="blau" && fspieler2!="blau" && fspieler3!="blau" && fspieler4!="blau") {
							sendMessageToServer("spieler4blau");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("orange")) {
						if(fspieler1!="orange" && fspieler2!="orange" && fspieler3!="orange" && fspieler4!="orange") {
							sendMessageToServer("spieler4orange");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("grün")) {
						if(fspieler1!="grün" && fspieler2!="grün" && fspieler3!="grün" && fspieler4!="grün") {
							sendMessageToServer("spieler4grün");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("gelb")) {
						if(fspieler1!="gelb" && fspieler2!="gelb" && fspieler3!="gelb" && fspieler4!="gelb") {
							sendMessageToServer("spieler4gelb");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("lila")) {
						if(fspieler1!="lila" && fspieler2!="lila" && fspieler3!="lila" && fspieler4!="lila") {
							sendMessageToServer("spieler4lila");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
					
					if(event.getItem().equals("grau")) {
						if(fspieler1!="grau" && fspieler2!="grau" && fspieler3!="grau" && fspieler4!="grau") {
							sendMessageToServer("spieler4grau");
						} else {	Object[] options = { "OK" };
						JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
				}
			}
		}
	} */
	
	//Spieler 1 erstellt Zahlen und schick sie an Server weiter, der wiederrum an alle Clients, die sie übernehmen
	public class Kartenschicken implements Runnable {

		@Override
		public void run() {
			kartenzahlen.zahlenerstellen();
			sendMessageToServer("kartea" + kartenzahlen.zahl1.rohstoffzahl);
			sendMessageToServer("karteb" + kartenzahlen.zahl2.rohstoffzahl);
			sendMessageToServer("kartec" + kartenzahlen.zahl3.rohstoffzahl);
			sendMessageToServer("karted" + kartenzahlen.zahl4.rohstoffzahl);
			sendMessageToServer("kartee" + kartenzahlen.zahl5.rohstoffzahl);
			sendMessageToServer("kartef" + kartenzahlen.zahl6.rohstoffzahl);
			sendMessageToServer("karteg" + kartenzahlen.zahl7.rohstoffzahl);
			sendMessageToServer("karteh" + kartenzahlen.zahl8.rohstoffzahl);
			sendMessageToServer("kartei" + kartenzahlen.zahl9.rohstoffzahl);
			sendMessageToServer("kartej" + kartenzahlen.zahl10.rohstoffzahl);
			sendMessageToServer("kartek" + kartenzahlen.zahl11.rohstoffzahl);
			sendMessageToServer("kartel" + kartenzahlen.zahl12.rohstoffzahl);
			sendMessageToServer("kartem" + kartenzahlen.zahl13.rohstoffzahl);
			sendMessageToServer("karten" + kartenzahlen.zahl14.rohstoffzahl);
			sendMessageToServer("karteo" + kartenzahlen.zahl15.rohstoffzahl);
			sendMessageToServer("kartep" + kartenzahlen.zahl16.rohstoffzahl);
			sendMessageToServer("karteq" + kartenzahlen.zahl17.rohstoffzahl);
			sendMessageToServer("karter" + kartenzahlen.zahl18.rohstoffzahl);
		}
		
	}
	
	
	//Beinhaltet die eigenen Ressourcen sowie seine Spielernummer
	public class Spieler {
		
		String spielerfarbe;
		int spielernummer = 0;
		int holz = 2, getreide = 2, schafe = 2, stein = 2, lehm = 3, papier = 0, muenzen = 0, seide = 0;

		
		public Spieler(int spielernummer) {
			this.spielernummer = spielernummer;
		}
		
		// Gibt dem Spieler seine Grundfarbe
		public void spielerfarbeanpassen () {
			if (spielernummer == 1) {
				spielerfarbe = "rot";
			} else if (spielernummer == 2) {
				spielerfarbe = "blau";
			} else if (spielernummer == 3) {
				spielerfarbe = "grün";
			} else if (spielernummer == 4) {
				spielerfarbe = "gelb";
			}
		}
	}
	
	
	public class Spielbrett implements Runnable, ActionListener {
		
		@Override
		public void run() {
			spielbrettladen();
		}
		
		//Kartenladen laden;

		public void spielbrettladen() {

			spielbrett = new JFrame("Die Siedler von Catan ");
			spielbrett.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			spielbrett.setSize(1500, 1000);
			spielbrett.setLayout(null);
			spielbrett.setResizable(false);
			spielbrett.setLocationRelativeTo(null);

			aktionen = new JTextField("mögliche Aktionen:");
			aktionen.setBounds(5, 876, 158, 35);
			aktionen.setFont(new Font("Tahoma", Font.PLAIN, 18));
			aktionen.setBackground(Color.yellow);
			aktionen.setEditable(false);
			spielbrett.getContentPane().add(aktionen);

			strassebauen = new JButton("Straße errichten");
			strassebauen.setBounds(220, 876, 150, 35);
			strassebauen.setBackground(Color.orange);
			spielbrett.getContentPane().add(strassebauen);
			strassebauen.addActionListener(this);

			dorfbauen = new JButton("Dorf errichten");
			dorfbauen.setBounds(400, 876, 150, 35);
			dorfbauen.setBackground(Color.orange);
			spielbrett.getContentPane().add(dorfbauen);
			dorfbauen.addActionListener(this);

			stadtbauen = new JButton("Stadt errichten");
			stadtbauen.setBounds(580, 876, 150, 35);
			stadtbauen.setBackground(Color.orange);
			spielbrett.getContentPane().add(stadtbauen);
			stadtbauen.addActionListener(this);

			ritterbauen = new JButton("Ritter bauen");
			ritterbauen.setBounds(760, 876, 150, 35);
			ritterbauen.setBackground(Color.orange);
			spielbrett.getContentPane().add(ritterbauen);
			ritterbauen.addActionListener(this);

			ritteraktivieren = new JButton("Ritter aktivieren");
			ritteraktivieren.setBounds(940, 876, 150, 35);
			ritteraktivieren.setBackground(Color.orange);
			spielbrett.getContentPane().add(ritteraktivieren);
			ritteraktivieren.addActionListener(this);

			forschen = new JButton("Forschen");
			forschen.setBounds(1120, 876, 150, 35);
			forschen.setBackground(Color.orange);
			spielbrett.getContentPane().add(forschen);
			forschen.addActionListener(this);

			karteaktivieren = new JButton("Ereignis aktivieren");
			karteaktivieren.setBounds(1300, 876, 150, 35);
			karteaktivieren.setBackground(Color.orange);
			spielbrett.getContentPane().add(karteaktivieren);
			karteaktivieren.addActionListener(this);
			
			handelanbieten = new JButton("Handelsangebot abgeben");
			handelanbieten.setBounds(35, 95, 250, 35);
			handelanbieten.setBackground(Color.pink);
			spielbrett.getContentPane().add(handelanbieten);
			handelanbieten.addActionListener(this);

			wuerfeln = new JButton("Würfeln");
			wuerfeln.setBounds(1094, 730, 150, 35);
			wuerfeln.setFont(new Font("Tahoma", Font.PLAIN, 12));
			wuerfeln.addActionListener(this);
			spielbrett.getContentPane().add(wuerfeln);
			
			zugbeenden = new JButton("Zug Beenden");
			zugbeenden.setBounds(1094, 800, 150, 35);
			zugbeenden.setFont(new Font("Tahoma", Font.PLAIN, 12));
			zugbeenden.addActionListener(this);
			spielbrett.getContentPane().add(zugbeenden);

			wuerfel1 = new JTextField("");
			wuerfel1.setBounds(1147, 96, 50, 50);
			wuerfel1.setHorizontalAlignment(JTextField.CENTER);
			wuerfel1.setEditable(false);
			wuerfel1.setBackground(Color.white);
			spielbrett.getContentPane().add(wuerfel1);

			wuerfel2 = new JTextField("");
			wuerfel2.setHorizontalAlignment(JTextField.CENTER);
			wuerfel2.setBounds(1147, 166, 50, 50);
			wuerfel2.setBackground(Color.red.brighter());
			wuerfel2.setEditable(false);
			spielbrett.getContentPane().add(wuerfel2);

			wuerfel3 = new JTextField("");
			wuerfel3.setHorizontalAlignment(JTextField.CENTER);
			wuerfel3.setBounds(1147, 236, 50, 50);
			wuerfel3.setEditable(false);
			spielbrett.add(wuerfel3);

			spielertext = new JTextField("Spieler" + dieserspieler);
			spielertext.setHorizontalAlignment(JTextField.CENTER);
			spielertext.setFont(new Font("Tahoma", Font.PLAIN, 20));
			spielertext.setBounds(22, 7, 120, 48);
			spielertext.setEditable(false);
			spielertext.setOpaque(false);
			spielbrett.add(spielertext);
			
			aktuellerspielertext = new JTextField("Aktueller Spieler: Spieler " + aktuellerspieler);
			aktuellerspielertext.setBounds(1270, 72, 200, 35);
			aktuellerspielertext.setHorizontalAlignment(JTextField.CENTER);
			aktuellerspielertext.setFont(new Font("Tahoma", Font.PLAIN, 16));
			aktuellerspielertext.setEditable(false);
			aktuellerspielertext.setOpaque(false);
			spielbrett.getContentPane().add(aktuellerspielertext);
			
			holz = new JTextField("Holz: " + spieler.holz);
			holz.setBounds(186, 12, 130, 40);
			holz.setHorizontalAlignment(JTextField.CENTER);
			holz.setFont(new Font("Tahoma", Font.PLAIN, 16));
			holz.setEditable(false);
			holz.setOpaque(false);
			spielbrett.getContentPane().add(holz);
			
			getreide = new JTextField("Getreide: " + spieler.getreide);
			getreide.setBounds(353, 12, 130, 40);
			getreide.setHorizontalAlignment(JTextField.CENTER);
			getreide.setFont(new Font("Tahoma", Font.PLAIN, 16));
			getreide.setEditable(false);
			getreide.setOpaque(false);
			spielbrett.getContentPane().add(getreide);
			
			lehm = new JTextField("Lehm: " + spieler.lehm);
			lehm.setBounds(520, 12, 130, 40);
			lehm.setHorizontalAlignment(JTextField.CENTER);
			lehm.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lehm.setEditable(false);
			lehm.setOpaque(false);
			spielbrett.getContentPane().add(lehm);
			
			stein = new JTextField("Stein: " + spieler.stein);
			stein.setBounds(854, 12, 130, 40);
			stein.setHorizontalAlignment(JTextField.CENTER);
			stein.setFont(new Font("Tahoma", Font.PLAIN, 16));
			stein.setEditable(false);
			stein.setOpaque(false);
			spielbrett.getContentPane().add(stein);
			
			wolle = new JTextField("Wolle: " + spieler.schafe);
			wolle.setBounds(687, 12, 130, 40);
			wolle.setHorizontalAlignment(JTextField.CENTER);
			wolle.setFont(new Font("Tahoma", Font.PLAIN, 16));
			wolle.setEditable(false);
			wolle.setOpaque(false);
			spielbrett.getContentPane().add(wolle);
			
			papier = new JTextField("Papier: " + spieler.papier);
			papier.setBounds(1021, 12, 130, 40);
			papier.setHorizontalAlignment(JTextField.CENTER);
			papier.setFont(new Font("Tahoma", Font.PLAIN, 16));
			papier.setEditable(false);
			papier.setOpaque(false);
			spielbrett.getContentPane().add(papier);
			
			muenzen = new JTextField("Münzen: " + spieler.muenzen);
			muenzen.setBounds(1188, 12, 130, 40);
			muenzen.setHorizontalAlignment(JTextField.CENTER);
			muenzen.setFont(new Font("Tahoma", Font.PLAIN, 16));
			muenzen.setEditable(false);
			muenzen.setOpaque(false);
			spielbrett.getContentPane().add(muenzen);
			
			seide = new JTextField("Seide: " + spieler.seide);
			seide.setBounds(1355, 12, 130, 40);
			seide.setHorizontalAlignment(JTextField.CENTER);
			seide.setFont(new Font("Tahoma", Font.PLAIN, 16));
			seide.setEditable(false);
			seide.setOpaque(false);
			spielbrett.getContentPane().add(seide);
			
			spielbrett.repaint();
			
			//ausgewürfelte Rohstoffzahlen, JBoxen fürs Bauen werden ins Spielbrett geladen
			boxen.wegboxladen(spielbrett);
			boxen.boxenladen(spielbrett);
			kartenzahlen.zahlenladen(spielbrett);
			boxen.kreuzungenerstellen();
			boxen.wegeerstellen();
			
			hintergrundbrett = new JLabel("");
			ImageIcon img = new ImageIcon(getClass().getResource("/JavaSiedler1x.jpg"));
			hintergrundbrett.setIcon(img);
			hintergrundbrett.setBounds(0, 0, 1500, 978);
			spielbrett.add(hintergrundbrett);
			
			spielbrett.setVisible(false);
		}
		
		public boolean amZug(){
			if(aktuellerspieler == dieserspieler) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == wuerfeln) {
				if (amZug() && !hatgewuerfelt && anfangsphaserum) {

					int wurfel1;
					wurfel1 = (int) (Math.random() * 6) + 1;
					if (wurfel1 > 3) {
						wuerfel1.setBackground(Color.BLACK);
					} else {
						switch (wurfel1) {
						case 1:
							wuerfel1.setBackground(Color.green.darker());
							break;
						case 2:
							wuerfel1.setBackground(Color.lightGray.darker());
							break;
						case 3:
							wuerfel1.setBackground(Color.yellow);
							break;
						}
					}
					
					int wurfel2;
					wurfel2 = (int) (Math.random() * 6) + 1;
					wuerfel2.setText("" + wurfel2);

					int wurfel3;
					wurfel3 = (int) (Math.random() * 6) + 1;
					wuerfel3.setText("" + wurfel3);
					
					hatgewuerfelt = true;
					sendMessageToServer("111"+wurfel1);
					sendMessageToServer("112"+wurfel2);
					sendMessageToServer("113"+wurfel3);
				}
			}
			
			if(e.getSource() == zugbeenden) {
				if(amZug() && hatgewuerfelt) {
					sendMessageToServer("beenden");
				}
			}
			if(e.getSource() == strassebauen) {
				if(dieserspieler == aktuellerspieler && hatgewuerfelt){
					if(auswahlweg) {
						for(CheckBoxen.Weg weg : boxen.weg) {
							if(!weg.istbebaut && ((weg.anfang.istbebaut && (weg.anfang.bebautvon == "Dorf" || weg.anfang.bebautvon == "Stadt")) ||
									(weg.ende.istbebaut && (weg.ende.bebautvon == "Dorf" || weg.ende.bebautvon == "Stadt")))){
								weg.box.setVisible(true);
							}
						}
						auswahlweg = false;
					} else {
						for(CheckBoxen.Weg weg : boxen.weg) {
							if(spieler.lehm > 0 && spieler.holz > 0 && !weg.istbebaut) {
								if(weg.box.isSelected()) {
									  if(spieler.spielernummer == 1){
										weg.bild.setIcon(rotWeg);
										weg.bild.setVisible(true);
										spieler.holz--;
										spieler.lehm--;
										sendMessageToServer("wegbauen"+weg.wegzahl);
									} if(spieler.spielernummer == 2){
										weg.bild.setIcon(blauWeg);
										weg.bild.setVisible(true);
										spieler.holz--;
										spieler.lehm--;
										sendMessageToServer("wegbauen"+weg.wegzahl);
									} if(spieler.spielernummer == 3){
										weg.bild.setIcon(gruenWeg);
										weg.bild.setVisible(true);
										spieler.holz--;
										spieler.lehm--;
										sendMessageToServer("wegbauen"+weg.wegzahl);
									}
									weg.istbebaut = true;
									weg.box.setSelected(false);
								}
							}
						}
						for(CheckBoxen.Weg weg : boxen.weg) {
							weg.box.setVisible(false);
						}
						auswahlweg = true;
					}
					aktualisieren();
				}
			}
			
			if(e.getSource() == dorfbauen) {
				if(dieserspieler == aktuellerspieler && hatgewuerfelt){
						if(auswahldorf){
							for(CheckBoxen.Kreuzung kreuz : boxen.kreuz){
								if(!kreuz.istbebaut && !kreuz.istausgeschaltet){
									kreuz.box.setVisible(true);
								}
							}
							auswahldorf = false;
						} else {
							for(CheckBoxen.Kreuzung kreuz : boxen.kreuz){
								if(kreuz.box.isSelected()){
									if(spieler.getreide > 0 && spieler.holz > 0 && spieler.schafe > 0 && spieler.lehm > 0 && !kreuz.istbebaut && !kreuz.istausgeschaltet){
									  if(spieler.spielernummer == 1){
										kreuz.bild.setIcon(rotDorf);
										kreuz.bild.setVisible(true);
										kreuz.bebautvon = "Dorf";
										spieler.getreide--;
										spieler.holz--;
										spieler.lehm--;
										spieler.schafe--;
										sendMessageToServer("dorfbauen"+kreuz.kreuzungszahl);
									} if(spieler.spielernummer == 2){
										kreuz.bild.setIcon(blauDorf);
										kreuz.bild.setVisible(true);
										kreuz.bebautvon = "Dorf";
										spieler.getreide--;
										spieler.holz--;
										spieler.lehm--;
										spieler.schafe--;
										sendMessageToServer("dorfbauen"+kreuz.kreuzungszahl);
									} if(spieler.spielernummer == 3){
										kreuz.bild.setIcon(gruenDorf);
										kreuz.bild.setVisible(true);
										kreuz.bebautvon = "Dorf";
										spieler.getreide--;
										spieler.holz--;
										spieler.lehm--;
										spieler.schafe--;
										sendMessageToServer("dorfbauen"+kreuz.kreuzungszahl);
									}
									kreuz.istbebaut = true;
									kreuz.box.setSelected(false);
									}
								}
							}
							for(CheckBoxen.Kreuzung kreuz : boxen.kreuz){
								kreuz.box.setVisible(false);
							}
						auswahldorf = true;
						}
						aktualisieren();
					}
			}
			
			if(e.getSource() == stadtbauen) {
				if(dieserspieler == aktuellerspieler && hatgewuerfelt){
					if(auswahlstadt){
						for(CheckBoxen.Kreuzung kreuz : boxen.kreuz){
							if(kreuz.bebautvon == "Dorf" && !kreuz.istausgeschaltet){
								kreuz.box.setVisible(true);
							}
						}
						auswahlstadt = false;
					} else {
						for(CheckBoxen.Kreuzung kreuz : boxen.kreuz){
							if(kreuz.box.isSelected()){
								if(spieler.getreide > 1 && spieler.stein > 2 && !kreuz.istausgeschaltet){
								  if(spieler.spielernummer == 1){
									kreuz.bild.setIcon(rotDorf);
									kreuz.bild.setVisible(true);
									kreuz.bebautvon = "Stadt";
									spieler.getreide--;
									spieler.getreide--;
									spieler.stein--;
									spieler.stein--;
									spieler.stein--;
									sendMessageToServer("stadtbauen"+kreuz.kreuzungszahl);
								} if(spieler.spielernummer == 2){
									kreuz.bild.setIcon(blauDorf);
									kreuz.bild.setVisible(true);
									kreuz.bebautvon = "Stadt";
									spieler.getreide--;
									spieler.getreide--;
									spieler.stein--;
									spieler.stein--;
									spieler.stein--;
									sendMessageToServer("stadtbauen"+kreuz.kreuzungszahl);
								} if(spieler.spielernummer == 3){
									kreuz.bild.setIcon(gruenDorf);
									kreuz.bild.setVisible(true);
									kreuz.bebautvon = "Stadt";
									spieler.getreide--;
									spieler.getreide--;
									spieler.stein--;
									spieler.stein--;
									spieler.stein--;
									sendMessageToServer("stadtbauen"+kreuz.kreuzungszahl);
								}
								kreuz.istbebaut = true;
								kreuz.box.setSelected(false);
								}
							}
						}
						for(CheckBoxen.Kreuzung kreuz : boxen.kreuz){
							kreuz.box.setVisible(false);
						}
					auswahlstadt = true;
					}
					aktualisieren();
				}
			}
			if(e.getSource() == ritterbauen) {
				if(dieserspieler == aktuellerspieler && hatgewuerfelt){
					if(auswahlritter){
						for(CheckBoxen.Kreuzung kreuz : boxen.kreuz){
							if(!kreuz.istbebaut && !kreuz.istausgeschaltet){
								kreuz.box.setVisible(true);
							}
						}
						auswahlritter = false;
					} else {
						for(CheckBoxen.Kreuzung kreuz : boxen.kreuz){
						if(kreuz.box.isSelected()){
								if(spieler.schafe > 0 && spieler.stein > 0 && !kreuz.istbebaut && !kreuz.istausgeschaltet){
								  if(spieler.spielernummer == 1){
									kreuz.bild.setIcon(rotRitter);
									kreuz.bild.setVisible(true);
									kreuz.bebautvon = "Ritter";
									spieler.stein--;
									spieler.schafe--;
									sendMessageToServer("ritterbauen"+kreuz.kreuzungszahl);
								} if(spieler.spielernummer == 2){
									kreuz.bild.setIcon(blauRitter);
									kreuz.bild.setVisible(true);
									kreuz.bebautvon = "Ritter";
									spieler.stein--;
									spieler.schafe--;
									sendMessageToServer("ritterbauen"+kreuz.kreuzungszahl);
								} if(spieler.spielernummer == 3){
									kreuz.bild.setIcon(gruenRitter);
									kreuz.bild.setVisible(true);
									kreuz.bebautvon = "Ritter";
									spieler.stein--;
									spieler.schafe--;
									sendMessageToServer("ritterbauen"+kreuz.kreuzungszahl);
								}
								kreuz.istbebaut = true;
								kreuz.box.setSelected(false);
								}
							}
						}
						for(CheckBoxen.Kreuzung kreuz : boxen.kreuz){
							kreuz.box.setVisible(false);
						}
					auswahlritter = true;
					}
					aktualisieren();
				}
			}
			if(e.getSource() == ritteraktivieren) {
				
			}
			if(e.getSource() == forschen) {
				
			}
		}
	}
	
	public void aktualisieren() {
		for(CheckBoxen.Weg weg : boxen.weg) {
			if(weg.anfang.istbebaut) {
				weg.ende.istausgeschaltet = true;
			}
			if(weg.ende.istbebaut) {
				weg.anfang.istausgeschaltet = true;
			}
		}
		
		holz.setText("Holz: "+spieler.holz);
		wolle.setText("Wolle: "+spieler.schafe);
		getreide.setText("Getreide: "+spieler.getreide);
		lehm.setText("Lehm: "+spieler.lehm);
		stein.setText("Stein: "+spieler.stein);
		seide.setText("Seide: "+spieler.seide);
		muenzen.setText("Münzen: "+spieler.muenzen);
		papier.setText("Papier: "+spieler.papier);
		
	}
	
	public class Wege {
		
		boolean istbebaut;
		Spieler spieler;
		Kreuzung anfang;
		Kreuzung ende;
		JCheckBox box;
		
		public Wege(Kreuzung anfang, Kreuzung ende, JCheckBox box){
			
			this.anfang = anfang;
			this.ende = ende;
			this.box = box;
		}

	}
	
	public class Kreuzung {
		
		String rohstoff1;
		int wuerfelzahl1;
		String rohstoff2;
		int wuerfelzahl2;
		String rohstoff3;
		int wuerfelzahl3;
		int strasse1;
		int strasse2;
		int strasse3;
		JCheckBox box;
		boolean istbebaut;
		boolean istausgeschaltet;
		Spieler spieler;
		
		public Kreuzung(String rohstoff1, int wuerfelzahl1,JCheckBox box){
			this.rohstoff1 = rohstoff1;
			this.wuerfelzahl1 = wuerfelzahl1;
			this.box = box;
			istbebaut = false;
			istausgeschaltet = false;
		}
		
		public Kreuzung(String rohstoff1, int wuerfelzahl1, String rohstoff2, int wuerfelzahl2,JCheckBox box){
			this.rohstoff1 = rohstoff1;
			this.wuerfelzahl1 = wuerfelzahl1;
			this.rohstoff2 = rohstoff2;
			this.wuerfelzahl2 = wuerfelzahl2;
			this.box = box;
			istbebaut = false;
			istausgeschaltet = false;
		}
		
		public Kreuzung(String rohstoff1, int wuerfelzahl1, String rohstoff2, int wuerfelzahl2, String rohstoff3, int wuerfelzahl3,JCheckBox box){
			this.rohstoff1 = rohstoff1;
			this.wuerfelzahl1 = wuerfelzahl1;
			this.rohstoff2 = rohstoff2;
			this.wuerfelzahl2 = wuerfelzahl2;
			this.rohstoff3 = rohstoff3;
			this.wuerfelzahl3 = wuerfelzahl3;
			this.box = box;
			istbebaut = false;
			istausgeschaltet = false;
		}

		@Override
		public String toString() {
			return "Kreuzung [rohstoff1=" + rohstoff1 + ", wuerfelzahl1=" + wuerfelzahl1 + ", rohstoff2=" + rohstoff2
					+ ", wuerfelzahl2=" + wuerfelzahl2 + ", rohstoff3=" + rohstoff3 + ", wuerfelzahl3=" + wuerfelzahl3
					+ ", strasse1=" + strasse1 + ", strasse2=" + strasse2 + ", strasse3=" + strasse3 + "]";
		}

	}
	
	
}
