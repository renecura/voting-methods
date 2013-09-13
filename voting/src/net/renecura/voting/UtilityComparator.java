package net.renecura.voting;

import java.util.Comparator;

public class UtilityComparator implements Comparator<Double> {

	@Override
	public int compare(Double arg0, Double arg1) {		
		return Double.compare(arg1, arg0);
	}

}
