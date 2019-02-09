import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Werfängtan implements Runnable, ActionListener {

	private Client client;
	private JFrame anfänger1;
	private JButton würfeln;
	private JLabel hintergrund1;

	private JFrame anfänger2;
	public JTextField wurfspieler1;
	public JTextField wurfspieler2;
	public JTextField wurfspieler3;
	public JTextField wurfspieler4;
	private JButton start;
	
	private int wuerfel;
	public int spielerzahl;
	public int spieleranzahl = 1;
	private boolean hatgewurfelt = false;

	public Werfängtan(Client client) {
		this.client = client;
	}

	@Override
	public void run() {
		
		würfelframe();
	}

	public void würfelframe() {

		anfänger2 = new JFrame("Würfelergebnis");
		anfänger2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		anfänger2.setSize(358, 380);
		anfänger2.setLayout(null);
		anfänger2.setResizable(false);
		anfänger2.setLocationRelativeTo(null);

		wurfspieler1 = new JTextField(client.name1 +" hat noch nicht gewürfelt");
		wurfspieler1.setBounds(60, 120, 240, 35);
		wurfspieler1.setEditable(false);
		wurfspieler1.setHorizontalAlignment(JTextField.CENTER);
		anfänger2.add(wurfspieler1);
		
		wurfspieler2 = new JTextField("Spieler 2 ist nicht im Spiel");
		if (spieleranzahl > 1) {
			wurfspieler2.setText(client.name2 +" hat noch nicht gewürferlt");}
		wurfspieler2.setBounds(60, 160, 240, 35);
		wurfspieler2.setEditable(false);
		wurfspieler2.setHorizontalAlignment(JTextField.CENTER);
		anfänger2.add(wurfspieler2);
		
		wurfspieler3 = new JTextField("Spieler 3 ist nicht im Spiel");
		if (spieleranzahl > 2) {
			wurfspieler3.setText(client.name3 +" hat noch nicht gewürfelt");}
		wurfspieler3.setBounds(60, 200, 240, 35);
		wurfspieler3.setEditable(false);
		wurfspieler3.setHorizontalAlignment(JTextField.CENTER);
		anfänger2.add(wurfspieler3);
		
		wurfspieler4 = new JTextField("Spieler 4 ist nicht im Spiel");
		if (spieleranzahl > 3) {
			wurfspieler4.setText(client.name4 +" hat noch nicht gewürfelt");}
		wurfspieler4.setBounds(60, 240, 240, 35);
		wurfspieler4.setEditable(false);
		wurfspieler4.setHorizontalAlignment(JTextField.CENTER);
		anfänger2.add(wurfspieler4);
		
		start = new JButton("Würfeln");
		start.setBounds(115, 280, 120, 35);
		start.addActionListener(this);
		anfänger2.add(start);
		
		hintergrund1 = new JLabel("");
		ImageIcon img = new ImageIcon(getClass().getResource("/Siedler2.jpg"));
		hintergrund1.setIcon(img);
		hintergrund1.setBounds(1, 1, 369, 350);
		anfänger2.add(hintergrund1);

		anfänger2.repaint();
		anfänger2.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start && !hatgewurfelt) {
			wuerfel = (int) (Math.random() * 6) + 1;
			hatgewurfelt = true;
			if (spielerzahl == 1) {
				client.sendMessageToServer("071"+wuerfel);
				start.setEnabled(false); start.setText("Gewinner");}
			else if (spielerzahl == 2) {
				client.sendMessageToServer("072"+wuerfel);
				start.setEnabled(false); start.setText("Gewinner");} 
			else if (spielerzahl == 2) {
				client.sendMessageToServer("073"+wuerfel);
				start.setEnabled(false); start.setText("Gewinner");}
			else if (spielerzahl == 4) {
				client.sendMessageToServer("074"+wuerfel);
				start.setEnabled(false); start.setText("Gewinner");}
		}
		//später weiter hier
		if (e.getSource() == null) {
			client.spielbrett.setVisible(true);
		}
	}
}
