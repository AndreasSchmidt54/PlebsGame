import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;

public class ItemHandler implements ItemListener {
	
	Client client;
	
	//Listener für Combobox, schickt ausgewählte Farbe an Server, der wiederrum an alle Clients
	public ItemHandler(Client client) {
		this.client = client;
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		
		if(event.getStateChange() == 1) {
			
			//Spieler 1 Auswahl
			if(event.getSource() == client.cmbfarbenauswahl1) {
				
				if(event.getItem().equals("rot")) {
					if(client.fspieler1!="rot" && client.fspieler2!="rot" && client.fspieler3!="rot" && client.fspieler4!="rot") {
						client.sendMessageToServer("spieler1rot");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
				
				if(event.getItem().equals("blau")) {
					if(client.fspieler1!="blau" && client.fspieler2!="blau" && client.fspieler3!="blau" && client.fspieler4!="blau") {
						client.sendMessageToServer("spieler1blau");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
				
				if(event.getItem().equals("orange")) {
					if(client.fspieler1!="orange" && client.fspieler2!="orange" && client.fspieler3!="orange" && client.fspieler4!="orange") {
						client.sendMessageToServer("spieler1orange");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
				
				if(event.getItem().equals("grün")) {
					if(client.fspieler1!="grün" && client.fspieler2!="grün" && client.fspieler3!="grün" && client.fspieler4!="grün") {
						client.sendMessageToServer("spieler1grün");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
				
				if(event.getItem().equals("gelb")) {
					if(client.fspieler1!="gelb" && client.fspieler2!="gelb" && client.fspieler3!="gelb" && client.fspieler4!="gelb") {
						client.sendMessageToServer("spieler1gelb");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
				
				if(event.getItem().equals("lila")) {
					if(client.fspieler1!="lila" && client.fspieler2!="lila" && client.fspieler3!="lila" && client.fspieler4!="lila") {
						client.sendMessageToServer("spieler1lila");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
				
				if(event.getItem().equals("grau")) {
					if(client.fspieler1!="grau" && client.fspieler2!="grau" && client.fspieler3!="grau" && client.fspieler4!="grau") {
						client.sendMessageToServer("spieler1grau");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}}
			
			//Spieler 2 Auswahl
			if(event.getSource() == client.cmbfarbenauswahl2) {
				
				if(event.getItem().equals("rot")) {
					if(client.fspieler1!="rot" && client.fspieler2!="rot" && client.fspieler3!="rot" && client.fspieler4!="rot") {
						client.sendMessageToServer("spieler2rot");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
				
				if(event.getItem().equals("blau")) {
					if(client.fspieler1!="blau" && client.fspieler2!="blau" && client.fspieler3!="blau" && client.fspieler4!="blau") {
						client.sendMessageToServer("spieler2blau");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
				
				if(event.getItem().equals("orange")) {
					if(client.fspieler1!="orange" && client.fspieler2!="orange" && client.fspieler3!="orange" && client.fspieler4!="orange") {
						client.sendMessageToServer("spieler2orange");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
				
				if(event.getItem().equals("grün")) {
					if(client.fspieler1!="grün" && client.fspieler2!="grün" && client.fspieler3!="grün" && client.fspieler4!="grün") {
						client.sendMessageToServer("spieler2grün");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
				
				if(event.getItem().equals("gelb")) {
					if(client.fspieler1!="gelb" && client.fspieler2!="gelb" && client.fspieler3!="gelb" && client.fspieler4!="gelb") {
						client.sendMessageToServer("spieler2gelb");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
				
				if(event.getItem().equals("lila")) {
					if(client.fspieler1!="lila" && client.fspieler2!="lila" && client.fspieler3!="lila" && client.fspieler4!="lila") {
						client.sendMessageToServer("spieler2lila");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
				
				if(event.getItem().equals("grau")) {
					if(client.fspieler1!="grau" && client.fspieler2!="grau" && client.fspieler3!="grau" && client.fspieler4!="grau") {
						client.sendMessageToServer("spieler2grau");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}}
			
			//Spieler 3 Auswahl
			if(event.getSource() == client.cmbfarbenauswahl3) {
				
				if(event.getItem().equals("rot")) {
					if(client.fspieler1!="rot" && client.fspieler2!="rot" && client.fspieler3!="rot" && client.fspieler4!="rot") {
						client.sendMessageToServer("spieler3rot");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
				
				if(event.getItem().equals("blau")) {
					if(client.fspieler1!="blau" && client.fspieler2!="blau" && client.fspieler3!="blau" && client.fspieler4!="blau") {
						client.sendMessageToServer("spieler3blau");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
				
				if(event.getItem().equals("orange")) {
					if(client.fspieler1!="orange" && client.fspieler2!="orange" && client.fspieler3!="orange" && client.fspieler4!="orange") {
						client.sendMessageToServer("spieler3orange");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
				
				if(event.getItem().equals("grün")) {
					if(client.fspieler1!="grün" && client.fspieler2!="grün" && client.fspieler3!="grün" && client.fspieler4!="grün") {
						client.sendMessageToServer("spieler3grün");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
				
				if(event.getItem().equals("gelb")) {
					if(client.fspieler1!="gelb" && client.fspieler2!="gelb" && client.fspieler3!="gelb" && client.fspieler4!="gelb") {
						client.sendMessageToServer("spieler3gelb");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
				
				if(event.getItem().equals("lila")) {
					if(client.fspieler1!="lila" && client.fspieler2!="lila" && client.fspieler3!="lila" && client.fspieler4!="lila") {
						client.sendMessageToServer("spieler3lila");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}
				
				if(event.getItem().equals("grau")) {
					if(client.fspieler1!="grau" && client.fspieler2!="grau" && client.fspieler3!="grau" && client.fspieler4!="grau") {
						client.sendMessageToServer("spieler3grau");
					} else {	Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "Diese Farbe wird schon verwendet !", "Achtung!!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);}}}
			
			/* Spieler 4 Auswahl
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
			}*/
		}
	}
}