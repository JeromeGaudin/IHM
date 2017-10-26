import javax.swing.*;
import java.awt.event.*;

public class ControleurFenetre implements WindowListener{

	public ControleurFenetre() {
		super();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		BaseDeDonnee.getInstance().fermerConnexion();
	}

	public void windowActivated(WindowEvent e) {;}
	public void windowClosed(WindowEvent e) {;}
	public void windowDeactivated(WindowEvent e) {;}
	public void windowDeiconified(WindowEvent e) {;}
	public void windowIconified(WindowEvent e) {;}
	public void windowOpened(WindowEvent e) {;}
}