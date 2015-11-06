import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IonicResultFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ResultAtom[] atoms = new ResultAtom[2];
	protected int xMax = Toolkit.getDefaultToolkit().getScreenSize().width;
	protected int yMax = Toolkit.getDefaultToolkit().getScreenSize().height - 10;
	protected int xSize;
	protected int ySize;
	protected int frame = 0;
	protected boolean go;
	private ActionListener al = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			frame++;
			System.out.println(frame);
			repaint();
		}
	};
	protected Timer t = new Timer(100, al);
	protected int[] leftCurrentPosition = new int[2];

	protected IonicResultFrame(Atom[] atoms) {
		super("Results");
		System.out.println("made it");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		go = true;
		setSize(new Dimension(10, 10));
		System.out.println(convertAtomToResultAtom(atoms[0], Resources.IONIC, 0).getName());
		this.atoms[0] = convertAtomToResultAtom(atoms[0], Resources.IONIC, 0);
		this.atoms[1] = convertAtomToResultAtom(atoms[1], Resources.IONIC, 0);
		xSize = getWidth();
		ySize = getHeight();
		leftCurrentPosition = new int[atoms[0].getValenceElectrons()];
		for (int i = 0; i < leftCurrentPosition.length; i++) {
			leftCurrentPosition[i] = xMax / 4 + Resources.electronPos[i][0];
		}
		this.atoms[0].setX(xMax / 4);
		this.atoms[0].setY(yMax / 2);
		this.atoms[1].setX(xMax / 4 * 3);
		this.atoms[1].setY(yMax / 2);

		t.start();
	}

	protected ResultAtom[] convertAtomsToResultAtom(Atom[] a, int bondType, int[] quadrant) {
		ResultAtom[] resultAtom = new ResultAtom[a.length];
		for (int i = 0; i < a.length; i++) {
			for (int x = 0; x < quadrant.length; x++) {
				resultAtom[i] = new ResultAtom(a[i].getName(), 0, 0, bondType, i);
			}
		}
		return resultAtom;
	}

	protected ResultAtom convertAtomToResultAtom(Atom a, int bondType, int quadrant) {
		return new ResultAtom(a.getName(), 0, 0, bondType, quadrant);
	}

	@Override
	public void paint(Graphics g) {
		setBackground(Color.BLACK);
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
				if (frame < xMax / 10 && atoms[0].getX() < xMax / 10 * 4) {
					atoms[0].setX(atoms[0].getX() + 4);
					atoms[1].setX(atoms[1].getX() - 4);
				}
			if (go) {
				leftCurrentPosition[0] += 3;
			}
			System.out.println(leftCurrentPosition[0] + " dfdfdfdfd " + (atoms[1].getX() + Resources.electronPos[4][0]));
			if (leftCurrentPosition[0] > atoms[1].getX() - 125) {
				System.out.println("worked");
				go = false;
			}
			g2D.clearRect(0, 0, xMax, yMax);
			g2D.setColor(Color.RED);
			System.out.println();
			g2D.drawString(atoms[0].getName(), atoms[0].getX(), atoms[0].getY());
			g2D.setColor(Color.BLUE);
			g2D.drawString(atoms[1].getName(), atoms[1].getX(), atoms[1].getY());
			g2D.setColor(Color.YELLOW);
			for (int i = 0; i < atoms[0].getValenceElectrons(); i++) {
				for (int i2 = 0; i2 < atoms[0].v.length; i2++) {
					if (atoms[0].v[i2]) {
						g2D.fillOval(leftCurrentPosition[0] + Resources.electronPos[i][0],
								atoms[0].getY() + Resources.electronPos[i][1], 5, 5);
					}
				}
			}
			g2D.setColor(Color.GREEN);
			for (int i = 0; i < atoms[1].getValenceElectrons(); i++) {
				for (int i2 = 0; i2 < atoms[1].v.length; i2++) {
					if (atoms[1].v[i2]) {
						g2D.fillOval(atoms[1].getX() + Resources.electronPos[i][0],
								atoms[0].getY() + Resources.electronPos[i][1], 5, 5);
					}
				}
			}

		}
	}

}