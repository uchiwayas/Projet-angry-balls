package exodecorateur_angryballs.maladroit;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class BoutonObservable extends Observable implements ActionListener {
	
	public BoutonObservable(Button btn) {
		btn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		setChanged();
	    notifyObservers();
	}
}
