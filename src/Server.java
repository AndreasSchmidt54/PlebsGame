import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//// Server Class

public class Server implements Runnable {

	private ServerSocket server;
	private ArrayList<PrintWriter> list_clientWriter;

	private int spieleranzahl;
	private int bereit = 0;

	ExecutorService executor = Executors.newFixedThreadPool(30);

	// Constructor Server, passt Spieleranzahl des ausgewählten Modus an
	public Server(int spieleranzahl) {
		this.spieleranzahl = spieleranzahl;
	}

	@Override
	public void run() {

		// Überprüft ob Server gestartet wurde und läuft oder ein Fehler auftritt
		if (runServer()) {
			listenToClients();
		} else {
			// Do nothing
		}
	}

	public boolean runServer() {
		try {
			server = new ServerSocket(5555);
			list_clientWriter = new ArrayList<PrintWriter>();
			return true;
		} catch (IOException e) {
			System.out.println("Server konnte nicht gestartet werden!");
			e.printStackTrace();
			return false;
		}
	}

	// Erstellt Printwriter für jeden Client und fügt diesen in ArrayList
	public void listenToClients() {
		int spielernummer = 1;

		while (list_clientWriter.size() < spieleranzahl) {
			Socket client;
			try {
				client = server.accept();
				System.out.println("Spieler" + spielernummer + " ist connected");

				PrintWriter writer = new PrintWriter(client.getOutputStream());
				list_clientWriter.add(writer);

				// sendet jedem Client seine Spielernummer
				writer.println("spieler" + spielernummer);
				writer.flush();

				// sendet jedem verbundenen Client die zugelassene Spieleranzahl
				writer.println("anzahl" + spieleranzahl);
				writer.flush();

				// sendet jedem verbundenen Client die Anzahl an verbundenen Spielern
				sendToAllClients("connected" + spielernummer);

				sendToAllClients("bereit" + bereit);

				// erhöht die Spielernummer für den nächsten verbundenen Client
				spielernummer++;

				// erstellt für jeden verbundenen Client einen Handler Thread. Dieser empfängt
				// und verarbeitet die Nachrichten der Clients
				Thread clientThread = new Thread(new Handler(client));
				clientThread.start();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Sendet die Nachricht an Jeden
	public void sendToAllClients(String message) {
		Iterator it = list_clientWriter.iterator();

		while (it.hasNext()) {
			PrintWriter writer = (PrintWriter) it.next();
			writer.println(message);
			writer.flush();
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// Handler Class

	public class Handler implements Runnable {

		private Socket client;
		private BufferedReader reader;

		private String namespieler1;
		private String namespieler2;
		private String namespieler3;
		private String namespieler4;

		private ArrayList<Rohstoffkarte> rohkarten = new ArrayList<Rohstoffkarte>();

		private Rohstoffkarte karte1 = new Rohstoffkarte("kartea");
		private Rohstoffkarte karte2 = new Rohstoffkarte("karteb");
		private Rohstoffkarte karte3 = new Rohstoffkarte("kartec");
		private Rohstoffkarte karte4 = new Rohstoffkarte("karted");
		private Rohstoffkarte karte5 = new Rohstoffkarte("kartee");
		private Rohstoffkarte karte6 = new Rohstoffkarte("kartef");
		private Rohstoffkarte karte7 = new Rohstoffkarte("karteg");
		private Rohstoffkarte karte8 = new Rohstoffkarte("karteh");
		private Rohstoffkarte karte9 = new Rohstoffkarte("kartei");
		private Rohstoffkarte karte10 = new Rohstoffkarte("kartej");
		private Rohstoffkarte karte11 = new Rohstoffkarte("kartek");
		private Rohstoffkarte karte12 = new Rohstoffkarte("kartel");
		private Rohstoffkarte karte13 = new Rohstoffkarte("kartem");
		private Rohstoffkarte karte14 = new Rohstoffkarte("karten");
		private Rohstoffkarte karte15 = new Rohstoffkarte("karteo");
		private Rohstoffkarte karte16 = new Rohstoffkarte("kartep");
		private Rohstoffkarte karte17 = new Rohstoffkarte("karteq");
		private Rohstoffkarte karte18 = new Rohstoffkarte("karter");
		private Rohstoffkarte karte19 = new Rohstoffkarte("kartes");

		// Constructor Handler
		public Handler(Socket client) {
			try {
				this.client = client;
				reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		int zwei = 0, drei = 0, vier = 0, fuenf = 0, sechs = 0, sieben = 0, acht = 0, neun = 0, zehn = 0, elf = 0,
				zwoelf = 0;
		int wald = 0, wiese = 0, lehmgrube = 0, getreidefeld = 0, gebirge = 0;

		// Überprüft ob alle Spieler bereit sind und würfelt dann die Rohstoffkarten aus
		public void checkSpielstart() {

			rohkarten.add(karte1);
			rohkarten.add(karte2);
			rohkarten.add(karte3);
			rohkarten.add(karte4);
			rohkarten.add(karte5);
			rohkarten.add(karte6);
			rohkarten.add(karte7);
			rohkarten.add(karte8);
			rohkarten.add(karte9);
			rohkarten.add(karte10);
			rohkarten.add(karte11);
			rohkarten.add(karte12);
			rohkarten.add(karte13);
			rohkarten.add(karte14);
			rohkarten.add(karte15);
			rohkarten.add(karte16);
			rohkarten.add(karte17);
			rohkarten.add(karte18);
			rohkarten.add(karte19);

			if (bereit == spieleranzahl) {

				// Auswürfeln der Rohstoffkarten
				Wüste wüste = new Wüste();
				for (Rohstoffkarte karte : rohkarten) {
					int k = 0;
					do {
						int randomzahl = (int) (Math.random() * 12) + 1;
						if (randomzahl == 2 && zwei != 1) {
							karte.rohstoffAusgebZahl = 2;
							sendToAllClients(karte.kartennummer + "z" + 2);
							zwei++;
							k = 1;
						}
						if (randomzahl == 3 && drei != 2) {
							karte.rohstoffAusgebZahl = 3;
							sendToAllClients(karte.kartennummer + "z" + 3);
							drei++;
							k = 1;
						}
						if (randomzahl == 4 && vier != 2) {
							karte.rohstoffAusgebZahl = 4;
							sendToAllClients(karte.kartennummer + "z" + 4);
							vier++;
							k = 1;
						}
						if (randomzahl == 5 && fuenf != 2) {
							karte.rohstoffAusgebZahl = 5;
							sendToAllClients(karte.kartennummer + "z" + 5);
							fuenf++;
							k = 1;
						}
						if (randomzahl == 6 && sechs != 2) {
							karte.rohstoffAusgebZahl = 6;
							sendToAllClients(karte.kartennummer + "z" + 6);
							sechs++;
							k = 1;
						}
						if (randomzahl == 7 && sieben != 1) {
							karte.rohstoffAusgebZahl = 7;
							sendToAllClients(karte.kartennummer + "z" + 7);
							sendToAllClients(karte.kartennummer + "rwuste");
							karte.setRohstoff(wüste);
							sieben++;
							k = 1;
						}
						if (randomzahl == 8 && acht != 2) {
							karte.rohstoffAusgebZahl = 8;
							sendToAllClients(karte.kartennummer + "z" + 8);
							acht++;
							k = 1;
						}
						if (randomzahl == 9 && neun != 2) {
							karte.rohstoffAusgebZahl = 9;
							sendToAllClients(karte.kartennummer + "z" + 9);
							neun++;
							k = 1;
						}
						if (randomzahl == 10 && zehn != 2) {
							karte.rohstoffAusgebZahl = 10;
							sendToAllClients(karte.kartennummer + "z" + 10);
							zehn++;
							k = 1;
						}
						if (randomzahl == 11 && elf != 2) {
							karte.rohstoffAusgebZahl = 11;
							sendToAllClients(karte.kartennummer + "z" + 11);
							elf++;
							k = 1;
						}
						if (randomzahl == 12 && zwoelf != 1) {
							karte.rohstoffAusgebZahl = 12;
							sendToAllClients(karte.kartennummer + "z" + 12);
							zwoelf++;
							k = 1;
						}
					} while (k == 0);
				}

				for (Rohstoffkarte karte : rohkarten) {
					int k = 0;
					do {
						int randomzahl = (int) (Math.random() * 6);
						
						if (karte.rohstoff == wüste) {
							k = 1;
						} else {
							if (randomzahl == 1 && wald != 4) {
								Holz holz = new Holz();
								karte.rohstoff = holz;
								wald++;
								sendToAllClients(karte.kartennummer + "rholz");
								k = 1;
							}
							if (randomzahl == 2 && lehmgrube != 3) {
								Lehm lehm = new Lehm();
								karte.rohstoff = lehm;
								lehmgrube++;
								sendToAllClients(karte.kartennummer + "rlehm");
								k = 1;
							}
							if (randomzahl == 3 && gebirge != 3) {
								Stein stein = new Stein();
								karte.rohstoff = stein;
								gebirge++;
								sendToAllClients(karte.kartennummer + "rstein");
								k = 1;
							}
							if (randomzahl == 4 && getreidefeld != 4) {
								Getreide getreide = new Getreide();
								karte.rohstoff = getreide;
								getreidefeld++;
								sendToAllClients(karte.kartennummer + "rgetreide");
								k = 1;
							}
							if (randomzahl == 5 && wiese != 3) {
								Schaf schaf = new Schaf();
								karte.rohstoff = schaf;
								wiese++;
								sendToAllClients(karte.kartennummer + "rschaf");
								k = 1;
							}
						}
					} while (k == 0);
				}
			}
		}

		@Override
		public void run() {

			String nachricht;

			try {
				// Empfängt und verarbeitet die Nachrichten der Clients
				while ((nachricht = reader.readLine()) != null) {

					System.out.println("Vom Client " + nachricht);

					if (nachricht.startsWith("bereit")) {
						bereit++;
						if (Integer.parseInt(nachricht.substring(6, 7)) == 1) {
							namespieler1 = nachricht.substring(7, nachricht.length());
							sendToAllClients("spbereit");
							checkSpielstart();
						}

						if (Integer.parseInt(nachricht.substring(6, 7)) == 2) {
							namespieler2 = nachricht.substring(7, nachricht.length());
							sendToAllClients("spbereit");
							checkSpielstart();
						}

						if (Integer.parseInt(nachricht.substring(6, 7)) == 3) {
							namespieler3 = nachricht.substring(7, nachricht.length());
							sendToAllClients("spbereit");
							checkSpielstart();
						}

						if (Integer.parseInt(nachricht.substring(6, 7)) == 4) {
							namespieler4 = nachricht.substring(7, nachricht.length());
							sendToAllClients("spbereit");
							checkSpielstart();
						}

					} else {
						sendToAllClients(nachricht);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
