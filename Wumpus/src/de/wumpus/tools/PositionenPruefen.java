package de.wumpus.tools;

public class PositionenPruefen {

	public static String pruefePositionen(int[] positionen) {
		// System.out.println("Position von Agent:" + positionen[0] + "," + positionen[1]);
		// System.out.println("Position von Gold:" + positionen[2] + "," + positionen[3]);
		// System.out.println("Position von Wumpus:" + positionen[4] + "," + positionen[5]);
		// System.out.println("Position von Fallgrube:" + positionen[6] + "," + positionen[7]);
		for (int i = 0; i < positionen.length; i = i + 2) {
			// System.err.println("i:" + i);
			for (int j = i + 2; j < positionen.length; j = j + 2) {
				// System.err.println("j:" + j);
				if (positionen[i] == positionen[j] && positionen[i + 1] == positionen[j + 1]) {
					return ("Gleiche Position:" + whichCase(i) + " " + whichCase(j));
				}

			}
		}
		// TODO: es soll noch die Fehlerfenster erscheinen. d.h. die Abfrage korrekt aber nach 2-3 mal sie blockirt nicht das Eingabe
		if (nachligendePositionen(positionen) != "Nichts")
			return "Im nahligende Position vom Agenten darf " + nachligendePositionen(positionen) + " nicht sein";
		return "Nichts";
	}

	private static String whichCase(int count) {
		switch (count) {
		case 0: {
			return "Agent";
		}
		case 2: {
			return "Gold";
		}
		case 4: {
			return "Wumpus";
		}
		case 6: {
			return "Fallgrube";
		}
		}
		return "Nicht erkennbar";
	}

	private static String nachligendePositionen(int[] positionen) {
		int agent = 0, i = 0;
		// gleiche y
		if (positionen[agent] == positionen[i + 2] && ((positionen[agent + 1] + 1) < (positionen[i + 3]) || (positionen[agent + 1] - 1) > (positionen[i + 3]))) {// Gold pruefen y1==y2, x1!=x2
			if (positionen[agent] == positionen[i + 4] && ((positionen[agent + 1] + 1) < (positionen[i + 5]) || (positionen[agent + 1] - 1) > (positionen[i + 5]))) {// Wumpus pruefen y1==y3, x1!=x3
				if (positionen[agent] == positionen[i + 6] && ((positionen[agent + 1] + 1) < (positionen[i + 7]) || (positionen[agent + 1] - 1) > (positionen[i + 7]))) {// Pit pruefen y1==yPit, x1!=xPit
					return "Nichts";
				} else if (positionen[agent + 1] == positionen[i + 7] && ((positionen[agent] + 1) < (positionen[i + 6]) || (positionen[agent] - 1) > (positionen[i + 6]))) {// Pit pruefen x1==xPit, y1!=yPit
					return "Nichts";
				} else if (positionen[agent] != positionen[i + 6] && positionen[agent + 1] != positionen[i + 7]) {// Pit pruefen y1!=y2, x1!=x2
					return "Nichts";
				} else
					return "Fallgrube";
			} else if (positionen[agent + 1] == positionen[i + 5] && ((positionen[agent] + 1) < (positionen[i + 4]) || (positionen[agent] - 1) > (positionen[i + 4]))) {// Wumpus pruefen x1==x3, y1!=y3
				if (positionen[agent] == positionen[i + 6] && ((positionen[agent + 1] + 1) < (positionen[i + 7]) || (positionen[agent + 1] - 1) > (positionen[i + 7]))) {// Pit pruefen y1==yPit, x1!=xPit
					return "Nichts";
				} else if (positionen[agent + 1] == positionen[i + 7] && ((positionen[agent] + 1) < (positionen[i + 6]) || (positionen[agent] - 1) > (positionen[i + 6]))) {// Pit pruefen x1==xPit, y1!=yPit
					return "Nichts";
				} else if (positionen[agent] != positionen[i + 6] && positionen[agent + 1] != positionen[i + 7]) {// Pit pruefen y1!=y2, x1!=x2
					return "Nichts";
				} else
					return "Fallgrube";
			} else if(positionen[agent] != positionen[i + 4] && positionen[agent + 1] != positionen[i + 5]) {// Wumpus pruefen y1!=y3, x1!=x3
				if (positionen[agent] == positionen[i + 6] && ((positionen[agent + 1] + 1) < (positionen[i + 7]) || (positionen[agent + 1] - 1) > (positionen[i + 7]))) {// Pit pruefen y1==yPit, x1!=xPit
					return "Nichts";
				} else if (positionen[agent + 1] == positionen[i + 7] && ((positionen[agent] + 1) < (positionen[i + 6]) || (positionen[agent] - 1) > (positionen[i + 6]))) {// Pit pruefen x1==xPit, y1!=yPit
					return "Nichts";
				} else if (positionen[agent] != positionen[i + 6] && positionen[agent + 1] != positionen[i + 7]) {// Pit pruefen y1!=y2, x1!=x2
					return "Nichts";
				} else
					return "Fallgrube";
			} else
				return "Wumpus";
			// gleiche x
		} else if (positionen[agent + 1] == positionen[i + 3] && ((positionen[agent] + 1) < (positionen[i + 2]) || (positionen[agent] - 1) > (positionen[i + 2]))) {// Gold pruefen x1==x2, y1!=y2
			if (positionen[agent + 1] == positionen[i + 5] && ((positionen[agent] + 1) < (positionen[i + 4]) || (positionen[agent] - 1) > (positionen[i + 4]))) {// Wumpus pruefen x1==x3, y1!=y3
				if (positionen[agent] == positionen[i + 6] && ((positionen[agent + 1] + 1) < (positionen[i + 7]) || (positionen[agent + 1] - 1) > (positionen[i + 7]))) {// Pit pruefen y1==yPit, x1!=xPit
					return "Nichts";
				} else if (positionen[agent + 1] == positionen[i + 7] && ((positionen[agent] + 1) < (positionen[i + 6]) || (positionen[agent] - 1) > (positionen[i + 6]))) {// Pit pruefen x1==xPit, y1!=yPit
					return "Nichts";
				} else if (positionen[agent] != positionen[i + 6] && positionen[agent + 1] != positionen[i + 7]) {// Pit pruefen y1!=y2, x1!=x2
					return "Nichts";
				} else
					return "Fallgrube";
			} else if (positionen[agent] == positionen[i + 4] && ((positionen[agent + 1] + 1) < (positionen[i + 5]) || (positionen[agent + 1] - 1) > (positionen[i + 5]))) {// Wumpus pruefen y1==y3, x1!=x3
				if (positionen[agent] == positionen[i + 6] && ((positionen[agent + 1] + 1) < (positionen[i + 7]) || (positionen[agent + 1] - 1) > (positionen[i + 7]))) {// Pit pruefen y1==yPit, x1!=xPit
					return "Nichts";
				} else if (positionen[agent + 1] == positionen[i + 7] && ((positionen[agent] + 1) < (positionen[i + 6]) || (positionen[agent] - 1) > (positionen[i + 6]))) {// Pit pruefen x1==xPit, y1!=yPit
					return "Nichts";
				} else if (positionen[agent] != positionen[i + 6] && positionen[agent + 1] != positionen[i + 7]) {// Pit pruefen y1!=y2, x1!=x2
					return "Nichts";
				} else
					return "Fallgrube";
			} else if(positionen[agent] != positionen[i + 4] && positionen[agent + 1] != positionen[i + 5]) {// Wumpus pruefen y1!=y3, x1!=x3
				if (positionen[agent] == positionen[i + 6] && ((positionen[agent + 1] + 1) < (positionen[i + 7]) || (positionen[agent + 1] - 1) > (positionen[i + 7]))) {// Pit pruefen y1==yPit, x1!=xPit
					return "Nichts";
				} else if (positionen[agent + 1] == positionen[i + 7] && ((positionen[agent] + 1) < (positionen[i + 6]) || (positionen[agent] - 1) > (positionen[i + 6]))) {// Pit pruefen x1==xPit, y1!=yPit
					return "Nichts";
				} else if (positionen[agent] != positionen[i + 6] && positionen[agent + 1] != positionen[i + 7]) {// Pit pruefen y1!=y2, x1!=x2
					return "Nichts";
				} else
					return "Fallgrube";
			} else
				return "Wumpus";
			// ungleiche y und x
		} else if (positionen[agent] != positionen[i + 2] && positionen[agent + 1] != positionen[i + 3]) {// Gold pruefen y1!=y2, x1!=x2
			if (positionen[agent] == positionen[i + 4] && ((positionen[agent + 1] + 1) < (positionen[i + 5]) || (positionen[agent + 1] - 1) > (positionen[i + 5]))) {// Wumpus pruefen y1==y3, x1!=x3
				if (positionen[agent] == positionen[i + 6] && ((positionen[agent + 1] + 1) < (positionen[i + 7]) || (positionen[agent + 1] - 1) > (positionen[i + 7]))) {// Pit pruefen y1==yPit, x1!=xPit
					return "Nichts";
				} else if (positionen[agent + 1] == positionen[i + 7] && ((positionen[agent] + 1) < (positionen[i + 6]) || (positionen[agent] - 1) > (positionen[i + 6]))) {// Pit pruefen x1==xPit, y1!=yPit
					return "Nichts";
				} else if (positionen[agent] != positionen[i + 6] && positionen[agent + 1] != positionen[i + 7]) {// Pit pruefen y1!=y2, x1!=x2
					return "Nichts";
				} else
					return "Fallgrube";
			} else if (positionen[agent + 1] == positionen[i + 5] && ((positionen[agent] + 1) < (positionen[i + 4]) || (positionen[agent] - 1) > (positionen[i + 4]))) {// Wumpus pruefen x1==x3, y1!=y3
				if (positionen[agent] == positionen[i + 6] && ((positionen[agent + 1] + 1) < (positionen[i + 7]) || (positionen[agent + 1] - 1) > (positionen[i + 7]))) {// Pit pruefen y1==yPit, x1!=xPit
					return "Nichts";
				} else if (positionen[agent + 1] == positionen[i + 7] && ((positionen[agent] + 1) < (positionen[i + 6]) || (positionen[agent] - 1) > (positionen[i + 6]))) {// Pit pruefen x1==xPit, y1!=yPit
					return "Nichts";
				} else if (positionen[agent] != positionen[i + 6] && positionen[agent + 1] != positionen[i + 7]) {// Pit pruefen y1!=y2, x1!=x2
					return "Nichts";
				} else
					return "Fallgrube";
			} else if (positionen[agent] != positionen[i + 4] && positionen[agent + 1] != positionen[i + 5]) {// Wumpus pruefen y1!=y2, x1!=x2
				if (positionen[agent] == positionen[i + 6] && ((positionen[agent + 1] + 1) < (positionen[i + 7]) || (positionen[agent + 1] - 1) > (positionen[i + 7]))) {// Pit pruefen y1==yPit, x1!=xPit
					return "Nichts";
				} else if (positionen[agent + 1] == positionen[i + 7] && ((positionen[agent] + 1) < (positionen[i + 6]) || (positionen[agent] - 1) > (positionen[i + 6]))) {// Pit pruefen x1==xPit, y1!=yPit
					return "Nichts";
				} else if (positionen[agent] != positionen[i + 6] && positionen[agent + 1] != positionen[i + 7]) {// Pit pruefen y1!=y2, x1!=x2
					return "Nichts";
				} else
					return "Fallgrube";
			} else
				return "Wumpus";
		} else
			return "Gold";		
	}
}
