package de.wumpus.tools;

public class PositionenPruefen {

	public static String pruefePositionen(int[] positionen){
//		System.out.println("Position von Agent:" + positionen[0] + "," + positionen[1]);
//		System.out.println("Position von Gold:" + positionen[2] + "," + positionen[3]);
//		System.out.println("Position von Wumpus:" + positionen[4] + "," + positionen[5]);
//		System.out.println("Position von Fallgrube:" + positionen[6] + "," + positionen[7]);
		for(int i = 0; i < positionen.length; i=i+2){
//			System.err.println("i:" + i);
			for(int j = i+2; j < positionen.length; j=j+2){
//				System.err.println("j:" + j);
				if(positionen[i] == positionen[j] && positionen[i+1] == positionen[j+1]){
					return ("Gleiche Position:" + whichCase(i) + " " + whichCase(j));
				}
			}
		}
		return "Nichts";
	}
	
	private static String whichCase(int count){
		switch (count){
		case 0:{
			return "Agent";
		}case 2:{
			return "Gold";
		}case 4:{
			return "Wumpus";
		}case 6:{
			return "Pit";
		}
		}
		return "Nicht erkennbar";
	}
}
