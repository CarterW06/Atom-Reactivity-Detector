public class CovalentBond {
	private Atom[] atoms;

	public CovalentBond(Atom[] atoms) {
		this.atoms = atoms;
	}

	public Atom getCenterAtom() {
		if (atoms.length != 2) {
			for (Atom a : atoms) {
				if (a.getUnpairedElectrons() == atoms.length - 1) {
					return a;
				}
			}
		}
		return null;
	}
}
