import java.util.ArrayList;

public class ResultAtom extends Atom {
	private int x, y;
	public ArrayList<Integer> electronX = new ArrayList<Integer>();
	public ArrayList<Integer> electronY = new ArrayList<Integer>();

	public ResultAtom(String atomName, int x, int y) {
		super(atomName);

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
