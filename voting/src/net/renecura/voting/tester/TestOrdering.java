package net.renecura.voting.tester;

import java.awt.Color;

import net.renecura.voting.Ordering;
import net.renecura.voting.alternatives.AlternativeSet;
import net.renecura.voting.alternatives.rgbcolor.RgbColorAlternative;
import net.renecura.voting.alternatives.rgbcolor.RgbColorDistancePreference;

public class TestOrdering {

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
		
		ord[0] = new Ordering(altSet, new RgbColorDistancePreference(Color.black));
		ord[1] = new Ordering(altSet, new RgbColorDistancePreference(Color.yellow));
		ord[2] = new Ordering(altSet, new RgbColorDistancePreference(Color.gray));
		ord[3] = new Ordering(altSet, new RgbColorDistancePreference(Color.darkGray));
		
		System.out.println("Alternative Set: "+altSet);
		
		int i;
		for(i = 0; i < ord.length; i++)
			System.out.println("Ordenamiento "+i+": "+ord[i]);

	}

}
