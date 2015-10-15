import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.Arrays;
import java.awt.Font;

public class ResultFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3112259498249138475L;
	protected int frame = 0;
	protected ResultAtom[] atoms;
	// add atom dimensions
	protected int xMax;
	protected int yMax;
	protected int xSize;
	protected int ySize;

	private ActionListener al = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame++;
			repaint();
		}
	};
	private Timer t = new Timer(100, al);

	protected ResultFrame() {

	}

	public ResultFrame(Atom[] atoms) {
		super("Results");
		System.out.println("hi");
		int[] positions = {3, 2};
		this.atoms = convertAtomsToResultAtom(atoms, Resources.IONIC, positions);
		setSize(new Dimension(10, 10));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		System.out.println("Visible:  " + isVisible());
		t.start();
		xMax = Toolkit.getDefaultToolkit().getScreenSize().width;
		yMax = Toolkit.getDefaultToolkit().getScreenSize().height - 100;
		xSize = getWidth();
		ySize = getHeight();
		this.atoms[0].setX(xMax / 4);
		this.atoms[0].setY(yMax / 2);
		this.atoms[1].setX(xMax / 4 * 3);
		this.atoms[1].setY(yMax / 2);
	}

	protected ResultAtom[] convertAtomsToResultAtom(Atom[] a, int bondType,
			int[] quadrant) {
		ResultAtom[] resultAtom = new ResultAtom[a.length];
		for (int i = 0; i < a.length; i++) {
			for (int f : quadrant) {
				resultAtom[i] = new ResultAtom(a[i].getName(), 0, 0, bondType,
						i);
			}
		}
		return resultAtom;
	}

	protected ResultAtom convertAtomToResultAtom(Atom a, int bondType,
			int quadrant) {
		return new ResultAtom(a.getName(), 0, 0, bondType, quadrant);
	}
	
	private int[][] currentPosition = new int[atoms[0].getValenceElectrons()][2];

	@Override
	public void paint(Graphics g) {
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
			g2D.clearRect(0, 0, xMax, yMax);
			g2D.setColor(Color.RED);
			g2D.drawString(atoms[0].getName(), atoms[0].getX(), atoms[0].getY());
			g2D.setColor(Color.BLUE);
			g2D.drawString(atoms[1].getName(), atoms[1].getX(), atoms[1].getY());
			g2D.setColor(Color.RED);
			int oneToUse = 0;
			for (int i = 0; i < atoms[0].getValenceElectrons(); i++) {
				for (int i2 = 0; i2 < atoms[0].v.length; i2++) {
					if (atoms[0].v[i2]) {
						g2D.fillOval(currentPosition[oneToUse][0]
								+ Resources.electronPos[i][0], atoms[0].getY()
								+ Resources.electronPos[i][1], 5, 5);
						currentPosition[oneToUse][0]+= 2;
					}
				}
			}
			g2D.setColor(Color.BLUE);
			for (int i = 0; i < atoms[1].getValenceElectrons(); i++) {
				for (int i2 = 0; i < atoms[1].v.length; i++) {
					if (atoms[1].v[i2]) {
						g2D.fillOval(atoms[1].getX()
								+ Resources.electronPos[i][0], atoms[0].getY()
								+ Resources.electronPos[i][1], 5, 5);
					}
				}
			}

		}
	}
}