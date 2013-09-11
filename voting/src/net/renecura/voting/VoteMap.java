package net.renecura.voting;

import java.util.Comparator;

public class VoteMap extends implements Comparator<Alternative> {

	Map<String, Integer> base;
    public ValueComparator(Map<String, Double> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.    
    public int compare(String a, String b) {
     
    }
	
	@Override
	public int compare(Alternative alt1, Alternative alt2) {
		   if (base.get(a) >= base.get(b)) {
	            return -1;
	        } else {
	            return 1;
	}

}
