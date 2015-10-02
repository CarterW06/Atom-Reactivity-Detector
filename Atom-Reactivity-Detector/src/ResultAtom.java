import java.util.ArrayList;

public class ResultAtom extends Atom {
	private int x, y;
	public ArrayList<Integer> electronX = new ArrayList<Integer>();
	public ArrayList<Integer> electronY = new ArrayList<Integer>();
	protected boolean[] v = new boolean[8];

	public ResultAtom(String atomName, int x, int y, int whatBond, int quadrant) {
		super(atomName);
		switch(whatBond) {
		case Resources.IONIC:
			switch(valenceElectrons) {
			case 1:
				v[0] = true;
			case 2:
				v[0] = true;
				v[1] = true;
			case 6:
				v[0] = true;
				v[1] = true;
				v[2] = true;
				v[3] = true;
				v[6] = true;
				v[7] = true;
			case 7:
				v[0] = true;
				v[1] = true;
				v[2] = true;
				v[3] = true;
				v[4] = true;
				v[6] = true;
				v[7] = true;
			}
		case Resources.COVALENT:
			switch(valenceElectrons) {
			case 1:
				
			}
		}
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
