import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Client1 implements Runnable, ActionListener {
	
	private Socket client;
	private PrintWriter writer;
	private BufferedReader reader;
	private String ip;
	private JFrame frame;
	private JLabel hintergrund1;
	private JTextField connectedvon;
	private JTextField spieleranzeige;
	private JTextField bereitvon;
	private JButton start;
	
	private ArrayList<Rohstoffkarte> rohkartenlist = new ArrayList<Rohstoffkarte>();
	
	private int spielernummer = 1;
	private int spieleranzahl;
	private int connected;
	private int bereit = 0;

	private int aktuellerspieler = 1;
	
	public Client1 (String ip) {
		this.ip = ip;
	}

	@Override
	public void run() {
		
		starting ();
		
		String message;
		try {
			while ((message = reader.readLine()) != null) {
				System.out.println("Von Server erhalten: " + message);

				String ohnezahlen = message.replaceAll("[0-9]", "");
				String ohnebuchstaben = message.replaceAll("[a-z]", "");
				
				// Hier wird dem Client eine Spielernummer gegeben
				if (message.equals("spieler" + 2)) { spieleranzeige.setText("Spieler 2"); spielernummer = 2; }
				if (message.equals("spieler" + 3)) { spieleranzeige.setText("Spieler 3"); spielernummer = 3; }
				if (message.equals("spieler" + 4)) { spieleranzeige.setText("Spieler 4"); spielernummer = 4; }
				
				// Die Gesamtanzahl an Spielern wird hier aktualisiert & Text für bereit festgelegt
				if (message.equals("anzahl" + 1)) {	spieleranzahl = 1; bereitvon.setText("0 von " + spieleranzahl + " Spieler bereit"); }
				if (message.equals("anzahl" + 2)) {	spieleranzahl = 2; bereitvon.setText("0 von " + spieleranzahl + " Spieler bereit"); }
				if (message.equals("anzahl" + 3)) {	spieleranzahl = 3; bereitvon.setText("0 von " + spieleranzahl + " Spieler bereit"); }
				if (message.equals("anzahl" + 4)) {	spieleranzahl = 4; bereitvon.setText("0 von " + spieleranzahl + " Spieler bereit"); }

				// Die verbundenen Spieler werden hier aktualisiert
				if (message.equals("connected" + 1)) { connectedvon.setText("Connected 1 von " + spieleranzahl); connected = 1; }
				if (message.equals("connected" + 2)) { connectedvon.setText("Connected 2 von " + spieleranzahl); connected = 2; }	
				if (message.equals("connected" + 3)) { connectedvon.setText("Connected 3 von " + spieleranzahl); connected = 3; }
				if (message.equals("connected" + 4)) { connectedvon.setText("Connected 4 von " + spieleranzahl); connected = 4; }
				
				// Die Spieler, die bereit sind werden hier aktualisiert
				if (message.equals("spbereit")) { 
					bereit++;
					bereitvon.setText(bereit + " von " + spieleranzahl + " Spieler bereit");
				}
				// Falls schon Spieler bereit sind, aber noch nicht alle connected haben
				if (message.startsWith("bereit")) {
					bereit = (Integer.parseInt(message.substring(6,7)));
					bereitvon.setText(bereit + " von " + spieleranzahl + " Spieler bereit");
				}
				
				// Für die Rohstoffkarten werden die Zahlen festgelegt
				if (message.startsWith("karte") && message.substring(6,7).equals("z")) {
					
					Rohstoffkarte karte = new Rohstoffkarte("karte"+ message.substring(5,6));
					karte.rohstoffAusgebZahl = Integer.parseInt(message.substring(7, message.length()));
					rohkartenlist.add(karte);
					
				}
				
				// Für die ROhstoffkarten werden die Rohstoffe festgelegt
				if (message.startsWith("karte") && message.substring(6,7).equals("r")) {
					for (Rohstoffkarte karte : rohkartenlist) {
						if (message.substring(0, 6).equals(karte.kartennummer)) {
							if (message.substring(7, message.length()).equals("holz")) {
								Holz holz = new Holz ();
								karte.rohstoff = holz;
							}
							if (message.substring(7, message.length()).equals("lehm")) {
								Lehm lehm = new Lehm ();
								karte.rohstoff = lehm;
							}
							if (message.substring(7, message.length()).equals("getreide")) {
								Getreide getreide = new Getreide ();
								karte.rohstoff = getreide;
							}
							if (message.substring(7, message.length()).equals("stein")) {
								Stein stein = new Stein ();
								karte.rohstoff = stein;
							}
							if (message.substring(7, message.length()).equals("schaf")) {
								Schaf schaf = new Schaf ();
								karte.rohstoff = schaf;
							}
							if (message.substring(7, message.length()).equals("wuste")) {
								Wüste wuste = new Wüste ();
								karte.rohstoff = wuste;
							}
						}
					}
				}
				
			}

		} catch (IOException e) {
			System.out.println("Nachricht konnte nicht empfangen werden!");
			e.printStackTrace();
		}
		
	}
	
	public void starting () {
		
		frame = new JFrame("Menü");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(358, 380);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

		connectedvon = new JTextField("");
		connectedvon.setBounds(60, 120, 240, 35);
		connectedvon.setHorizontalAlignment(JTextField.CENTER);
		connectedvon.setEditable(false);
		connectedvon.setOpaque(false);
		frame.add(connectedvon);

		spieleranzeige = new JTextField("");
		spieleranzeige.setBounds(60, 150, 240, 35);
		spieleranzeige.setHorizontalAlignment(JTextField.CENTER);
		spieleranzeige.setEditable(true);
		spieleranzeige.setOpaque(false);
		frame.add(spieleranzeige);
		
		bereitvon = new JTextField();
		bereitvon.setBounds(60, 180, 240, 35);
		bereitvon.setHorizontalAlignment(JTextField.CENTER);
		bereitvon.setEditable(false);
		bereitvon.setOpaque(false);
		frame.add(bereitvon);
		
		start = new JButton ("Start");
		start.setBounds(120, 240, 120, 34);
		start.addActionListener(this);
		frame.add(start);

		hintergrund1 = new JLabel("");
		ImageIcon img = new ImageIcon(getClass().getResource("/Siedler2.jpg"));
		hintergrund1.setIcon(img);
		hintergrund1.setBounds(1, 1, 369, 350);
		frame.add(hintergrund1);

		frame.repaint();

		if (!connectToServer()) {}
		
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
	
	public void sendMessageToServer(String message) {
		writer.println(message);
		writer.flush();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start) {
			start.setEnabled(false);
			spieleranzeige.setEditable(false);
			sendMessageToServer("bereit1" + spieleranzeige.getText());
		}	
	}
}
