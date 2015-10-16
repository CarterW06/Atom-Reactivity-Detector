
import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class StartupScreen extends JFrame implements ActionListener, KeyListener {
	private static final long serialVersionUID = -104229691199606680L;
	private JTextField input = new JTextField(20);
	private JButton next = new JButton("Next \u2192");
	public ResultFrame rf;
	public JFrame jf = new JFrame("Atom Reactivity Detector");

	private StartupScreen() {
		// TEMP
		JPanel panel = new JPanel();
		JLabel info = new JLabel(
				"Welcome to Atom Reactivity Detector!  Enter the number of atoms that you want to be computed and then press the button.");
		panel.add(info);
		panel.add(input);
		panel.add(next);
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		next.addActionListener(this);
		input.addKeyListener(this);
		setVisible(true);
	}

	public static void main(String[] args) {
		new StartupScreen();
		Resources.generateHashMap();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		computeInput();
	}

	private void computeInput() {
		next.removeKeyListener(this);
		if (input.getText().equals("exit")) {
			System.exit(0);
		}
		try {
			if (Integer.parseInt(input.getText()) > 5) {
				JOptionPane.showMessageDialog(this,
						"If you want to use more than 5 atoms, you will not be trying to compute one atom.  It will automatically generate unit cells for you if you want it to.  Metallic bonds will also automatically be computed.");
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
				if (covalent) {
					JOptionPane.showMessageDialog(this, "covalent");
					rf = new CovalentResultFrame(cb.getCenterAtom(), atomsNotInCenter);
				}
			}
			if (sum == 8 && atoms.length == 2) {
				for (Atom a : atoms) {
					if (covalent == false) {
						if (a.getValenceElectrons() == 3 || a.getValenceElectrons() == 4 || a.getValenceElectrons() == 5) {
							System.exit(0);
						}
					}
				}
				if(atoms[1].getValenceElectrons() < atoms[0].getValenceElectrons()) {
					Atom smallAtom = atoms[1]; Atom bigAtom = atoms[0];
					atoms[0] = smallAtom; atoms[1] = bigAtom;
				}
				//JOptionPane.showMessageDialog(this, "ionic bond");
				System.out.println("ionic bond");
				rf = new ResultFrame();
				rf.setVisible(true);
				System.out.println("123");
			}

		} catch (Exception a) {
		}
		next.addKeyListener(this);
		// Ionic bond logic
		// Not included yet - reference Atom.java to see how it works
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			computeInput();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			computeInput();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			computeInput();
		}
	}
}