
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class StartupScreen extends JFrame implements ActionListener, KeyListener {
	private static final long serialVersionUID = -104229691199606680L;
	private JTextField input = new JTextField(20);
	private JButton next = new JButton("Next \u2192");
	public IonicResultFrame irf;

	protected StartupScreen() {
		// TEMP
		JPanel panel = new JPanel();
		JLabel info = new JLabel(
				"Welcome to Atom Reactivity Detector!  Enter the number of atoms that you want to be computed and then press the button.  If there is an ionic bond, the dots represent electrons.  Covalent bonds only work with hydrogen.");
		panel.add(info);
		panel.add(input);
		panel.add(next);
		add(panel);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		next.addActionListener(this);
		input.addKeyListener(this);
		setVisible(true);
	}

	public static void main(String[] args) {
		new StartupScreen();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		computeInput();
	}

	private int computeInput() {
		Resources r = new Resources();
		r.generateCollections();
		next.removeKeyListener(this);
		if (input.getText().equals("exit")) {
			System.exit(0);
		}
		System.out.println("i'm here" + input.getText());
		if (Integer.parseInt(input.getText()) > 5) {
			System.exit(0);
		}
		String[] elements = new String[Integer.parseInt(input.getText())];
		input.setText("");
		for (int i = 0; i < elements.length; i++) {
			elements[i] = JOptionPane.showInputDialog(this, "What is the full name for element #" + (i + 1));
		}
		Set<Map.Entry<Integer, String[]>> set = Resources.sPBlock.entrySet();
		int[] valenceElectrons = new int[elements.length];
		for (int i = 0; i < elements.length; i++) {
			for (Map.Entry<Integer, String[]> entry : set) {
				for (String current : entry.getValue()) {
					if (current.equalsIgnoreCase(elements[i])) {
						valenceElectrons[i] = entry.getKey();
						break;
					}
				}
			}
		}
		Atom[] atoms = new Atom[elements.length];
		boolean covalent = false;
		for (int i = 0; i < atoms.length; i++) {
			atoms[i] = new Atom(elements[i]);
		}
		CovalentBond cb = new CovalentBond(atoms);
		int sum = 0;
		for (int num = 0; num < atoms.length; num++) {
			sum += atoms[num].getValenceElectrons();
		}
		boolean setTrue = true;
		// If there is a center atom:
		if (cb.getCenterAtom() != null) {
			Atom[] atomsNotInCenter = new Atom[atoms.length - 1];
			int i = 0;
			for (Atom a : atoms) {
				if (a != cb.getCenterAtom()) {
					atomsNotInCenter[i] = a;
					i++;
				}
			}
			for (Atom a : atomsNotInCenter) {
				if (a.getUnpairedElectrons() == 1) {
					if (setTrue) {
						covalent = true;
					}
				} else {
					covalent = false;
					setTrue = false;
				}
			}
			boolean hasH = false;
			for (Atom a : atoms) {
				if (a.getName().equalsIgnoreCase("hydrogen") && covalent == true) {
					hasH = true;
				}
			}
			if (covalent && hasH) {
				JOptionPane.showMessageDialog(this, "covalent bond");
				System.exit(0);
			}
		}
		boolean metallic;
		if(atoms.length == 3) {
			metallic = true;
			for(Atom a : atoms) {
				if(r.metals.contains(a) == true) {
					System.out.println("not true");
					metallic = false;
				}
			}
			if(metallic) {
				JOptionPane.showMessageDialog(this, "metallic bond");
			}
		}
		return isIonicBond(atoms, covalent, sum);

		// Ionic bond logic
		// Not included yet - reference Atom.java to see how it works
	}

	private int isIonicBond(Atom[] atoms, boolean covalent, int sum) {
		if (sum == 8 && atoms.length == 2) {
			for (Atom a : atoms) {
				if (covalent == false) {
					if (a.getValenceElectrons() == 3 || a.getValenceElectrons() == 4 || a.getValenceElectrons() == 5) {
						System.exit(0);
					}
					if(a.getName().equalsIgnoreCase("hydrogen")) {
						JOptionPane.showMessageDialog(this, "covalent bond");
						System.exit(0);
					}
				}
			}
			if (atoms[1].getValenceElectrons() < atoms[0].getValenceElectrons()) {
				Atom smallAtom = atoms[1];
				Atom bigAtom = atoms[0];
				atoms[0] = smallAtom;
				atoms[1] = bigAtom;
			}
			// JOptionPane.showMessageDialog(this, "ionic bond");
			System.out.println("ionic bond");
			irf = new IonicResultFrame(atoms);
			irf.setVisible(true);
			return 538;
		} else if (sum == 8 && atoms.length == 3) {
			System.out.println("ionic bond");
			for (Atom a : atoms) {
				if (a.getName().equalsIgnoreCase("hydrogen")) {
					JOptionPane.showMessageDialog(this, "covalent bond");
					System.exit(0);
				}
			}
			java.util.List<Atom> al = new ArrayList<Atom>();
			Atom[] otherAtoms = new Atom[2];
			Atom bigOne = new Atom("ERROR");
			for (Atom a : atoms) {
				if (a.getValenceElectrons() == 1) {
					al.add(a);
				} else if (a.getValenceElectrons() == 6) {
					bigOne = a;
				}
			}
			int i = 0;
			while (i < 2) {
				for (Atom a : al) {
					otherAtoms[i] = a;
					i++;
				}
			}
			if (covalent == false) {
				irf = new IonicWithThree(bigOne, otherAtoms);
			}
		}
		next.addKeyListener(this);
		return 3;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			computeInput();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}