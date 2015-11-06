import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class IonicWithThree extends IonicResultFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 988536625939754581L;
	private int[] rightCurrentPosition = new int[2];
	private ResultAtom[] others = new ResultAtom[2];
	private ResultAtom middleOne;
	protected IonicWithThree(Atom middleOne, Atom[] others) {
		super(others);
		this.others[0] = convertAtomToResultAtom(others[0], Resources.IONIC3, 1);
		this.others[1] = convertAtomToResultAtom(others[1], Resources.IONIC3, 0);
		this.middleOne = convertAtomToResultAtom(middleOne, Resources.IONIC3, 2);
		setBackground(Color.BLACK);
		rightCurrentPosition[0] = xMax / 2 + 300;
		leftCurrentPosition[1] = xMax / 2 - 300;
 	}
	protected ActionListener al = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			frame++;
			System.out.println(frame);
			repaint();
		}
	};
	protected Timer t = new Timer(10, al);

	@Override
	public void paint(Graphics g) {
		t.start();
		Graphics2D g2D = (Graphics2D) g;
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
			// ???
			if (frame < xMax / 10 + xMax / 16)
				if (frame < xMax / 10 && atoms[0].getX() < xMax / 8 * 7) {
					atoms[0].setX(atoms[0].getX() + 2);
					atoms[1].setX(atoms[1].getX() - 2);
				}
			if (go) {
				leftCurrentPosition[0] += 2;
			}
			System.out.println(leftCurrentPosition[0] + " dfdfdfdfd " + (others[1].getX() + Resources.electronPos[4][0]));
			if (leftCurrentPosition[0] >  - 125) {
				System.out.println("worked");
				go = false;
			}
			g2D.clearRect(0, 0, xMax, yMax);
			g2D.setColor(Color.WHITE);
			g2D.drawString(middleOne.getName(), xMax / 2, yMax / 2);
			g2D.setColor(Color.RED);
			System.out.println();
			g2D.drawString(atoms[0].getName(), atoms[0].getX(), atoms[0].getY());
			g2D.setColor(Color.BLUE);
			g2D.drawString(atoms[1].getName(), atoms[1].getX(), atoms[1].getY());
			g2D.setColor(Color.GREEN);
			for (int i = 0; i < atoms[0].getValenceElectrons(); i++) {
				for (int i2 = 0; i2 < atoms[0].v.length; i2++) {
					if (atoms[0].v[i2]) {
						g2D.fillOval(leftCurrentPosition[0] + Resources.electronPos[i][0],
								atoms[0].getY() + Resources.electronPos[i][1], 5, 5);
					}
				}
			}
			g2D.setColor(Color.YELLOW);
			for (int i = 0; i < atoms[1].getValenceElectrons(); i++) {
				for (int i2 = 0; i2 < atoms[1].v.length; i2++) {
					if (atoms[1].v[i2]) {
						g2D.fillOval(atoms[1].getX() + Resources.electronPos[i][0],
								atoms[0].getY() + Resources.electronPos[i][1], 5, 5);
					}
				}
			}
			g2D.setColor(Color.WHITE);
			for (int i = 0; i < middleOne.getValenceElectrons(); i++) {
				for (int i2 = 0; i2 < 8; i2++) {
					if (others[1].v[i2]) {
						g2D.fillOval(xMax / 2 + Resources.electronPos[i][0],
								yMax / 2 + Resources.electronPos[i][1], 5, 5);
					}
				}
			}

		}
	}
}
