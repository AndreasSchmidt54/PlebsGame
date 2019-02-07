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

public class Server implements Runnable{
	
	ServerSocket server;
	ArrayList<PrintWriter> list_clientWriter;
	
	int spieleranzahl;
	
	ExecutorService executor = Executors.newFixedThreadPool(30);
	
	public Server(int spieleranzahl){
		this.spieleranzahl = spieleranzahl;
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
	
	public void listenToClients(){
		int spielernummer = 1;
		
		while(list_clientWriter.size() < spieleranzahl){
			Socket client;
			try {
				client = server.accept();
				System.out.println("Spieler" + spielernummer + " ist connected");
				
				PrintWriter writer = new PrintWriter(client.getOutputStream());
				list_clientWriter.add(writer);
				
				//für spielverlauf
				writer.println("spieler" + spielernummer);
				writer.flush();
				
				//für erste Frame
				writer.println("anzahl" + spieleranzahl);
				writer.flush();
				sendToAllClients("connected" + spielernummer);
				
				spielernummer++;
				
				Thread clientThread = new Thread(new Handler(client));
				clientThread.start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
	public void sendToAllClients(String message) {
		Iterator it = list_clientWriter.iterator();
		
		while(it.hasNext()){
			PrintWriter writer = (PrintWriter) it.next();
			writer.println(message);
			writer.flush();
		}
	}
	
	public class Handler implements Runnable {

		Socket client;
		BufferedReader reader;

		public Handler(Socket client) {
			try {
				this.client = client;
				reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			String nachricht;

			try {

				while ((nachricht = reader.readLine()) != null) {
					System.out.println("Vom Client " + nachricht);
					sendToAllClients(nachricht);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		
		if(runServer()){
			listenToClients();
		} else {
//			Do nothing
		}
		
	}
}
