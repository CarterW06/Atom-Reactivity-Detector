import java.util.*;

public class Atom {
	protected String atomName;
	protected int valenceElectrons = 0;

	public Atom(String atomName) {
		this.atomName = atomName;
		Set<Map.Entry<Integer, String[]>> set = Resources.sPBlock
				.entrySet();
		for (Map.Entry<Integer, String[]> entry : set) {
			for (String group : entry.getValue()) {
				if (group.equalsIgnoreCase(this.atomName)) {
					valenceElectrons = entry.getKey();
				}
			}
		}
	}

	public int getValenceElectrons() {
		return valenceElectrons;
	}

	public void setName(String s) {
		atomName = s;
		Set<Map.Entry<Integer, String[]>> set = Resources.sPBlock
				.entrySet();
		for (Map.Entry<Integer, String[]> entry : set) {
			for (String group : entry.getValue()) {
				if (group.equalsIgnoreCase(this.atomName)) {
					valenceElectrons = entry.getKey();
				}
			}
		}
	}

	public int getUnpairedElectrons() {
		boolean isSPBlock = false;
		Set<Map.Entry<Integer, String[]>> set = Resources.sPBlock
				.entrySet();
		for (Map.Entry<Integer, String[]> entry : set) {
			for (String group : entry.getValue()) {
				if (group.equalsIgnoreCase(atomName)) {
					isSPBlock = true;
				}
			}
		}
		if (isSPBlock) {
			switch (valenceElectrons) {
			case 1:
			case 2:
			case 3:
			case 4:
				return valenceElectrons;
			case 5:
			case 6:
			case 7:
			case 8:
				return 8 - valenceElectrons;
			default:
				return 0;
			}
		}
		return 0;
	}

	public String getName() {
		return atomName;
	}
}