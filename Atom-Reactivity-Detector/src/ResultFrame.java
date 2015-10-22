import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResultFrame extends JFrame {
	private ResultAtom[] atoms;
	private int xMax = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int yMax = Toolkit.getDefaultToolkit().getScreenSize().height - 10;
	private int xSize;
	private int ySize;
	private int frame = 0;
	private ActionListener al = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			frame++;
		}
	};
	private Timer t = new Timer(100, al);
	protected ResultFrame(Atom[] atoms) {
		super();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(10, 10));
		this.atoms[0] = convertAtomToResultAtom(atoms[0], Resources.IONIC, 0);  this.atoms[1] = convertAtomToResultAtom(atoms[1], Resources.IONIC, 0);
		xSize = getWidth();  ySize = getHeight();
		t.start();
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
		super.paint(g);
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