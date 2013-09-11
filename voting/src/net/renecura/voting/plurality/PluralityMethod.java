package net.renecura.voting.plurality;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.renecura.voting.Alternative;
import net.renecura.voting.Preference;
import net.renecura.voting.VotingMethod;

public class PluralityMethod extends VotingMethod {

	public PluralityMethod(ArrayList<Alternative> alternatives,
			ArrayList<Preference> preferences) {
		super(alternatives, preferences);
	}
	
	@Override
	public ArrayList<Alternative> choose() {
		
		Preference p;
		Alternative alt;
		Iterator<Alternative> altIt;
		int i, size = preferences.size();
		Integer v;
		
		Map<Alternative, Integer> votes = new ConcurrentHashMap<Alternative, Integer>();
		
		for (i = 0; i < size; i++){	
			p = preferences.get(i);			
			altIt = p.getOrdering().first().iterator();
			
			// Recupera el conjunto cabecera de las preferencias y suma un voto por cada uno.
			while(altIt.hasNext()){
				alt = altIt.next();
				
				v = votes.get(alt);
				if (v == null) v = Integer.valueOf(0);
				
				votes.put(alt, Integer.valueOf(v.intValue() + 1));
				
			}
			
			votes.keySet().iterator()
		}
		
		return null;
	}

	@Override
	public Alternative chooseOne() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
