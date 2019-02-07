import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

// Zweite geöffnete Klasse -->

public class Spielauswahl implements ActionListener {
	
	public Spielauswahl(StartGUI gui) {
		this.gui = gui;
	}
	
	private StartGUI gui;
	
	private JFrame auswahl;
	private JButton pickhost;
	private JButton pickjoin;
	private JButton zurueck;
	private JLabel hintergrund1;
	
	private JFrame host;
	private JButton spieler1;
	private JButton spieler2;
	private JButton spieler3;
	private JButton spieler4;
	private JButton zurueckhost;
	private JLabel hintergrund2;
	
	private JFrame join;
	private JTextField ipadresseangeben;
	private JButton ipsuche;
	private JButton zurueckjoin;
	private JLabel hintergrund4;

	
	public void hostoderjoin() {

		auswahl = new JFrame("Auswahl");
		auswahl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		auswahl.setSize(358, 380);
		auswahl.setLocationRelativeTo(null);
		auswahl.setLayout(null);
		auswahl.setResizable(false);
		auswahl.setVisible(true);

		pickhost = new JButton("Hosten");
		pickhost.setBounds(115, 130, 120, 35);
		pickhost.addActionListener(this);
		auswahl.add(pickhost);

		pickjoin = new JButton("Beitreten");
		pickjoin.setBounds(115, 185, 120, 35);
		pickjoin.addActionListener(this);
		auswahl.add(pickjoin);

		zurueck = new JButton("Abbrechen");
		zurueck.setBounds(115, 240, 120, 35);
		zurueck.addActionListener(this);
		auswahl.add(zurueck);

		hintergrund1 = new JLabel("");
		ImageIcon img = new ImageIcon(getClass().getResource("/Siedler2.jpg"));
		hintergrund1.setIcon(img);
		hintergrund1.setBounds(1, 1, 369, 350);
		auswahl.add(hintergrund1);
		
		auswahl.repaint();
	}
	
	public void host() {

		host = new JFrame("Auswahl");
		host.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		host.setSize(358, 380);
		host.setVisible(true);
		host.setLayout(null);
		host.setResizable(false);
		host.setLocationRelativeTo(null);

		spieler1 = new JButton("1-Spieler");
		spieler1.setBounds(45, 120, 120, 35);
		host.getContentPane().add(spieler1);
		spieler1.addActionListener(this);

		spieler2 = new JButton("2-Spieler");
		spieler2.setBounds(200, 120, 120, 35);
		host.getContentPane().add(spieler2);
		spieler2.addActionListener(this);

		spieler3 = new JButton("3-Spieler");
		spieler3.setBounds(45, 175, 120, 35);
		host.getContentPane().add(spieler3);
		spieler3.addActionListener(this);

		spieler4 = new JButton("4-Spieler");
		spieler4.setBounds(200, 175, 120, 35);
		host.getContentPane().add(spieler4);
		spieler4.addActionListener(this);
		
		zurueckhost = new JButton("Abbrechen");
		zurueckhost.setBounds(115, 240, 120, 35);
		zurueckhost.addActionListener(this);
		host.add(zurueckhost);

		hintergrund2 = new JLabel("");
		ImageIcon img = new ImageIcon(getClass().getResource("/Siedler2.jpg"));
		hintergrund2.setIcon(img);
		hintergrund2.setBounds(1, 1, 369, 350);
		host.add(hintergrund2);
		
		host.repaint();
	}
	
	public void join() {
		
		join = new JFrame("Auswahl");
		join.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		join.setSize(358, 380);
		join.setLocationRelativeTo(null);
		join.setLayout(null);
		join.setResizable(false);
		join.setVisible(true);

		ipsuche = new JButton("Joinen");
		ipsuche.setBounds(115, 185, 120, 35);
		ipsuche.addActionListener(this);
		join.add(ipsuche);

		zurueckjoin = new JButton("Abbrechen");
		zurueckjoin.setBounds(115, 240, 120, 35);
		zurueckjoin.addActionListener(this);
		join.add(zurueckjoin);

		ipadresseangeben = new JTextField("");
		ipadresseangeben.setBounds(60, 130, 240, 35);
		ipadresseangeben.setHorizontalAlignment(JTextField.CENTER);
		join.add(ipadresseangeben);

		hintergrund4 = new JLabel("");
		ImageIcon img = new ImageIcon(getClass().getResource("/Siedler2.jpg"));
		hintergrund4.setIcon(img);
		hintergrund4.setBounds(1, 1, 369, 350);
		join.add(hintergrund4);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == zurueck) {
			auswahl.dispose();
			gui.starting();
		}
		if (e.getSource() == pickhost) {
			auswahl.dispose();
			host();
		}
		if (e.getSource() == pickjoin) {
			auswahl.dispose();
			join();
		}
		if (e.getSource() == zurueckhost) {
			auswahl.dispose();
			hostoderjoin();
		}
		if (e.getSource() == zurueckjoin) {
			join.dispose();
			hostoderjoin();
		}
		
		
		if (e.getSource() == spieler1) {
			host.dispose();
			Thread server = new Thread(new Server(1));
			server.start();
			Thread clienthost = new Thread(new Client("localhost"));
			clienthost.start();
		}
		if (e.getSource() == spieler2) {
			host.dispose();
			Thread server = new Thread(new Server(2));
			server.start();
			Thread clienthost = new Thread(new Client("localhost"));
			clienthost.start();
		}
		if (e.getSource() == spieler3) {
			host.dispose();
			Thread server = new Thread(new Server(3));
			server.start();
			Thread clienthost = new Thread(new Client("localhost"));
			clienthost.start();
		}
		if (e.getSource() == spieler4) {
			host.dispose();
			Thread server = new Thread(new Server(4));
			server.start();
			Thread clienthost = new Thread(new Client("localhost"));
			clienthost.start();
		}
		
		if (e.getSource() == ipsuche){
			join.dispose();
			Client c = new Client(ipadresseangeben.getText());
			c.starting();
		}
	}
}
