import java.awt.Graphics;

import javax.swing.*;
@SuppressWarnings("serial")
public class OtherResultFrame extends JFrame {
	public OtherResultFrame() {
		super("Other Result Frame");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawString("Test", 300, 300);
	}
}
