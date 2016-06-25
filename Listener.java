import java.awt.event.*;
import javax.swing.*;

public class Listener implements KeyListener, ActionListener{
	public Listener(String name) {
		super(name);
	}
	/** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
        displayInfo(e, "KEY TYPED: ");
    }
}
