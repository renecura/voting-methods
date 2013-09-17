package net.renecura.voting.tester;

import java.awt.Color;

import net.renecura.voting.Ordering;
import net.renecura.voting.VotingMethod;
import net.renecura.voting.alternatives.AlternativeSet;
import net.renecura.voting.alternatives.rgbcolor.RgbColorAlternative;
import net.renecura.voting.alternatives.rgbcolor.RgbColorDistancePreference;
import net.renecura.voting.methods.SequentialMajorityElectionsMethod;

public class TestSequentialMajorityElections {

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
		//altSet.add(new RgbColorAlternative("Gray", Color.gray));
		
		Ordering ord[] = new Ordering[4];
		RgbColorDistancePreference pref[] = new RgbColorDistancePreference[4];
		
		pref[0] = new RgbColorDistancePreference(Color.blue);
		pref[1] = new RgbColorDistancePreference(Color.yellow);
		pref[2] = new RgbColorDistancePreference(Color.gray);
		pref[3] = new RgbColorDistancePreference(Color.darkGray);
		
		System.out.println("Alternative Set: "+altSet+"\n");
		
		VotingMethod method = new SequentialMajorityElectionsMethod(altSet);
		
		int i;
		for(i = 0; i < ord.length; i++){
			ord[i] = new Ordering(altSet, pref[i]);
			System.out.println("Ordenamiento "+i+": "+ord[i]);
			method.addPreference(pref[i]);
		}
		
		System.out.println("\nEleccion: "+method.choose());
		System.out.println(method);
		
		Runtime runtime = Runtime.getRuntime();
	    // Run the garbage collector
	    //runtime.gc();
	    // Calculate the used memory
	    long memory = runtime.totalMemory() - runtime.freeMemory();
	    System.out.println("\nUsed memory in bytes: " + memory);
	    System.out.println("Used memory in kilobytes: "
	        + (memory/1024L));
	}

}
