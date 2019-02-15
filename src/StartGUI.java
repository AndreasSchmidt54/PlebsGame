import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Die Static void main öffnet ein Frame mit Buttons. "Spiel starten" generiert eine Spielauswahl <--> hostoderjoin() wird ausgeführt

public class StartGUI implements ActionListener {
	
	private JFrame frame;
	private JLabel hintergrund1;
	private JButton schliessen;
	private JButton einstellung;
	private JButton info;
	private JButton ende;
	
	private Spielauswahl nextStage;
	
	// Static Void Main
	public static void main(String[] args){
		
		StartGUI gui = new StartGUI();
		gui.starting();
		
	}
	
	public void starting() {

		frame = new JFrame("Menü");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(358, 380);
		frame.setLocationRelativeTo(null);

		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

		schliessen = new JButton("Spiel starten");
		schliessen.setBounds(45, 120, 120, 35);
		schliessen.addActionListener(this);
		frame.add(schliessen);

		einstellung = new JButton("Einstellungen");
		einstellung.setBounds(200, 120, 120, 35);
		einstellung.addActionListener(this);
		frame.add(einstellung);

		info = new JButton("Info");
		info.setBounds(45, 200, 120, 35);
		info.addActionListener(this);
		frame.add(info);

		ende = new JButton("Beenden");
		ende.setBounds(200, 200, 120, 35);
		ende.addActionListener(this);
		frame.add(ende);

		hintergrund1 = new JLabel("");
		ImageIcon img = new ImageIcon(getClass().getResource("/Siedler2.jpg"));
		hintergrund1.setIcon(img);
		hintergrund1.setBounds(1, 1, 369, 350);
		frame.add(hintergrund1);

		frame.repaint();

	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////
	// ActionListener
	
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == schliessen) {
			frame.dispose();
			nextStage = new Spielauswahl(this);
			nextStage.hostoderjoin();
		}

		if (e.getSource() == info) {
			Object[] options = { "OK" };
			
			JOptionPane.showOptionDialog(null, "Programmiert von Andreas Schmidt !", "Information",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		}
		
		if (e.getSource() == ende){
			System.exit(0);
		}
		
		if (e.getSource() == einstellung){
			
		}

	}
} 

