import java.awt.*;

public class IonicWithThree extends IonicResultFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 988536625939754581L;
	private int[] rightCurrentPosition = new int[2];
	private ResultAtom[] others;
	private ResultAtom middleOne;
	protected IonicWithThree(Atom middleOne, Atom[] others) {
		super(others);
		rightCurrentPosition[0] = xMax / 4 * 3 + Resources.electronPos[5][0];
		this.others[0] = convertAtomToResultAtom(others[0], Resources.IONIC3, 1);
		this.others[1] = convertAtomToResultAtom(others[1], Resources.IONIC3, 0);
		this.middleOne = convertAtomToResultAtom(middleOne, Resources.IONIC3, 2);
	}

	public void paint(Graphics g) {
		setBackground(Color.BLACK);
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
				if (frame < xMax / 10 && others[0].getX() < xMax / 10 * 4) {
					others[0].setX(others[0].getX() + 4);
					others[1].setX(others[1].getX() - 4);
				}
			if (go) {
				leftCurrentPosition[0] += 3;
				rightCurrentPosition[0] -= 3;
			}
			System.out.println(leftCurrentPosition[0] + " dfdfdfdfd " + (others[1].getX() + Resources.electronPos[4][0]));
			if (leftCurrentPosition[0] >  - 125) {
				System.out.println("worked");
				go = false;
			}
			g2D.clearRect(0, 0, xMax, yMax);
			g2D.setColor(Color.RED);
			System.out.println();
			g2D.drawString(others[0].getName(), others[0].getX(), others[0].getY());
			g2D.setColor(Color.BLUE);
			g2D.drawString(others[1].getName(), others[1].getX(), others[1].getY());
			g2D.drawString(middleOne.getName(), xMax / 2, yMax / 2);
			g2D.setColor(Color.YELLOW);
			for (int i = 0; i < others[0].getValenceElectrons(); i++) {
				for (int i2 = 0; i2 < 8; i2++) {
					if (others[0].v[i2]) {
						g2D.fillOval(leftCurrentPosition[0] + Resources.electronPos[i][0],
								others[0].getY() + Resources.electronPos[i][1], 5, 5);
					}
				}
			}
			g2D.setColor(Color.GREEN);
			for (int i = 0; i < others[1].getValenceElectrons(); i++) {
				for (int i2 = 0; i2 < 8; i2++) {
					if (others[1].v[i2]) {
						g2D.fillOval(rightCurrentPosition[0] + Resources.electronPos[i][0],
								others[0].getY() + Resources.electronPos[i][1], 5, 5);
					}
				}
			}
			g2D.setColor(Color.GREEN);
			for (int i = 0; i < middleOne.getValenceElectrons(); i++) {
				for (int i2 = 0; i2 < 8; i2++) {
					if (others[1].v[i2]) {
						g2D.fillOval(xMax / 2 + Resources.electronPos[i][0],
								xMax / 2 + Resources.electronPos[i][1], 5, 5);
					}
				}
			}

		}
	}
}
