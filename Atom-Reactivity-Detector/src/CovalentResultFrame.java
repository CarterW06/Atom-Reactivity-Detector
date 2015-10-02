import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;



@SuppressWarnings("unused")
public class CovalentResultFrame extends ResultFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5099422727898981471L;
	int frame = 0;
	private Atom centerAtom;
	private Atom[] otherAtoms;
	Timer t = new Timer(100, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			frame++;
			repaint();
		}
	});

	public CovalentResultFrame(Atom centerAtom, Atom[] otherAtoms) {
		super();
		t.start();
		System.out.println(frame);
		//this.centerAtom = convertAtomToResultAtom(centerAtom, Resources.COVALENT);
		this.otherAtoms = convertAtomsToResultAtom(otherAtoms, Resources.COVALENT);
		setVisible(true);
		super.atoms[0].setX(xMax / 4);  super.atoms[0].setY(yMax / 2);
		super.atoms[1].setX(xMax / 4 * 3);  super.atoms[1].setY(yMax / 2);
	}
	
	int ySize = getHeight();
	int xMax = Toolkit.getDefaultToolkit().getScreenSize().width;
	int yMax = Toolkit.getDefaultToolkit().getScreenSize().height - 100;
	int xSize = getWidth();
	
	public void paint(Graphics g) {
		if (frame > 15) {
			xSize += 10;
			ySize += 10;

			if (xSize < xMax - 9) {
				setSize(xSize, ySize);
			}
			if (ySize < yMax - 9) {
				setSize(xSize, ySize);
			}
		}
		if (xSize >= xMax && ySize >= yMax) {
			System.out.println("Yippee!!!!");
			g.drawString(centerAtom.getName(), xMax / 2, yMax / 2);
			for(Atom a : otherAtoms) {
				
			}
		}
		System.out.println(ySize + " " + yMax);
	}
}
