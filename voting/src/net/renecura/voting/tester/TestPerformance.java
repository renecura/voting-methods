package net.renecura.voting.tester;

import java.awt.Color;

import net.renecura.voting.Ordering;
import net.renecura.voting.Utility;
import net.renecura.voting.VotingMethod;
import net.renecura.voting.alternatives.AlternativeSet;
import net.renecura.voting.alternatives.rgbcolor.RgbColorAlternative;
import net.renecura.voting.alternatives.rgbcolor.RgbColorDistancePreference;
import net.renecura.voting.methods.BordaCountMethod;

public class TestPerformance {

	private static final int SIZE = 1000000;
	
	public static void main(String[] args) {
		AlternativeSet altSet = new AlternativeSet();
		
		altSet.add(new RgbColorAlternative("Black", Color.black));
		altSet.add(new RgbColorAlternative("Red", Color.red));
		altSet.add(new RgbColorAlternative("Green", Color.green));
		altSet.add(new RgbColorAlternative("Blue", Color.blue));
		altSet.add(new RgbColorAlternative("Yellow", Color.yellow));
		altSet.add(new RgbColorAlternative("Magenta", Color.magenta));
		altSet.add(new RgbColorAlternative("Cyan", Color.cyan));
		altSet.add(new RgbColorAlternative("White", Color.white));
		
		
		Ordering ord[] = new Ordering[SIZE];
		Utility pref[] = new Utility[SIZE];
		
		System.out.println("Alternative Set: "+altSet+"\n");
		
		VotingMethod method = new BordaCountMethod(altSet);
		
		int i;
		RgbColorAlternative alt;
		for(i = 0; i < ord.length; i++){
			
			alt = (RgbColorAlternative)altSet.getAtRandom();
			
			pref[i] = new RgbColorDistancePreference(alt.getColor());
			ord[i] = new Ordering(altSet, pref[i]);
			//System.out.println("Ordenamiento "+i+": "+ord[i]);
			method.addPreference(pref[i]);
		}
		
		long startTime = System.currentTimeMillis();
		
		System.out.println("\nEleccion: "+method.choose());
		System.out.println(method);
		System.out.println();
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Election time: "+elapsedTime+"ms");
		
		Runtime runtime = Runtime.getRuntime();
	    // Run the garbage collector
	    //runtime.gc();
	    // Calculate the used memory
	    long memory = runtime.totalMemory() - runtime.freeMemory();
	    System.out.println("Used memory in bytes: " + (memory/(1024L*1024L)) + "MB / " + memory + " bytes");
	}

}
