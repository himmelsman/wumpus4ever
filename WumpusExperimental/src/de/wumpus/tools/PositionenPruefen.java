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
			return "Pit";
		}
		}
		return "Nicht erkennbar";
	}

	private static String nachligendePositionen(int[] positionen) {
		int agent = 0, i = 0;
		// TODO: ÜBERARBEITEN!!!!!!!!! Agent 1|1 alle anderen felder erst bei 4|4 gültig
		System.err.println("test");

		if (positionen[agent] == positionen[i + 2]) {
			//if (!(positionen[agent + 1] + 1 < positionen[i + 3]) && positionen[agent + 1] - 1 > positionen[i + 3]) {
			//TODO: Bisher kann nur eine der if bedingungen als richtig durchlaufen werden, und die andere als falsch trotz richtiger koordinaten
			//d.h. fall 1: engabe 1.3 für if ist richtig und für else if ist falsch. und 3.1 ist umgekehrt
			if(!(positionen[agent+1] +1 < positionen[i+3] && !(positionen[agent+1] -1 > positionen[i+3]))){
				System.err.println("Gold y=y" + "Agent: " + agent + " Position[agent]: " + positionen[agent] + " Position[agent+1]: " + positionen[agent+1] + " Position[agent+1]+1: " + (positionen[agent+1]+1));
				return "Gold";
			} else if (!(!(positionen[agent + 1] + 1 < positionen[i + 3]) && positionen[agent+1] -1 > positionen[i+3])) {
				System.err.println("Gold y=y");
				return "Gold";
			}
		} else if (positionen[agent + 1] == positionen[i + 3]) {
			// if (!(positionen[agent] + 1 < positionen[i + 2]) || !(positionen[agent] + 1 > positionen[i + 2])) {
			if (!(positionen[agent] + 1 > positionen[i + 2]) || !(positionen[agent] + 1 < positionen[i + 2])) {
				System.err.println("Gold x=x");
				return "Gold";
			}
		}
		i = i + 2;
		if ((positionen[agent] == positionen[i + 2] && (!(positionen[agent + 1] + 1 < positionen[i + 3]) || !(positionen[agent + 1] - 1 > positionen[i + 3])))
				|| (positionen[agent + 1] == positionen[i + 3] && (!(positionen[agent] + 1 < positionen[i + 2]) || !(positionen[agent] + 1 > positionen[i + 2])))) {
			System.err.println("Wumpus");
			return "Wumpus";
		}
		i = i + 2;
		if ((positionen[agent] == positionen[i + 2] && (!(positionen[agent + 1] + 1 < positionen[i + 3]) || !(positionen[agent + 1] - 1 > positionen[i + 3])))
				|| (positionen[agent + 1] == positionen[i + 3] && (!(positionen[agent] + 1 < positionen[i + 2]) || !(positionen[agent] + 1 > positionen[i + 2])))) {
			System.err.println("Fallgrube");
			return "Fallgrube";
		}
		return "Nichts";
	}
}
