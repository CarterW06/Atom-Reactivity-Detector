import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Toolkit;

@SuppressWarnings("unused")
public class Resources {
	public static HashMap<Integer, String[]> sPBlock = new HashMap<Integer, String[]>();
	public static ArrayList<String> metals = new ArrayList<String>();
	public static int[][] electronPos = new int[8][2];
	public static final int IONIC = 0;
	public static final int COVALENT = 1;
	public static final int METALLIC = 2;
	public static final int QUADRANT0 = 0;
	public static final int QUADRANT1 = 1;
	public static final int QUADRANT2 = 2;
	public static final int QUADRANT3 = 3;
	public static final int QUADRANT4 = 4;
	public static final HashMap<Integer, Dimension> covalentAtomPositions = new HashMap<Integer, Dimension>();
	private static String[] group1 = { "hydrogen", "lithium", "sodium",
			"potassium", "rubidium", "cesium", "francium" };
	private static String[] group2 = { "helium", "beryllium", "magnesium",
			"calcium", "strontium", "barium", "radium" };
	private static String[] group13 = { "boron", "aluminum", "gallium",
			"indium", "thallium" };
	private static String[] group14 = { "carbon", "silicon", "germanium",
			"tin", "lead" };
	private static String[] group15 = { "nitrogen", "phosphorus", "arsenic",
			"antimony", "bismuth" };
	private static String[] group16 = { "oxygen", "sulfur", "selenium",
			"tellurium", "polonium" };
	private static String[] group17 = { "fluorine", "chlorine", "bromine",
			"iodine", "astatine" };
	private static String[] group18 = { "neon", "argon", "krypton", "xenon",
			"radon" };

	public static void generateHashMap() {
		electronPos[0][0] = 90;
		electronPos[0][1] = 15;
		electronPos[1][0] = 90;
		electronPos[1][1] = 0;
		electronPos[2][0] = 70;
		electronPos[2][1] = -15;
		electronPos[3][0] = 30;
		electronPos[3][1] = -15;
		electronPos[4][0] = 5;
		electronPos[4][1] = -5;
		electronPos[5][0] = -15;
		electronPos[5][1] = 30;
		sPBlock.put(1, group1);
		sPBlock.put(2, group2);
		sPBlock.put(3, group13);
		sPBlock.put(4, group14);
		sPBlock.put(5, group15);
		sPBlock.put(6, group16);
		sPBlock.put(7, group17);
		sPBlock.put(8, group18);
	}
}
