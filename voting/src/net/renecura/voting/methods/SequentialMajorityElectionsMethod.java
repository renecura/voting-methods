package net.renecura.voting.methods;

import java.util.Iterator;

import net.renecura.voting.Ordering;
import net.renecura.voting.VotingMethod;
import net.renecura.voting.alternatives.Alternative;
import net.renecura.voting.alternatives.AlternativeSet;

public class SequentialMajorityElectionsMethod extends VotingMethod {

	public SequentialMajorityElectionsMethod(AlternativeSet set) {
		super(set);
	}

	@Override
	public AlternativeSet choose() {
		AlternativeSet set = new AlternativeSet();		
		set.add(this.chooseOne());		
		return set;
	}

	@Override
	public Alternative chooseOne() {
		int size = alternatives.size();
		
		if ( size == 0 ) return null;
		
		Alternative best = alternatives.get(0);
		
		if ( size == 1 ){
			return best;
		}
		
		int i;
		AlternativeSet agenda = new AlternativeSet();
		
		PluralityMethod p = new PluralityMethod(agenda);
		Iterator<Ordering> oIt = individualOrd.iterator();
		
		while(oIt.hasNext())
			p.addPreference(oIt.next().getPreference());
		
		for(i = 1; i < size; i++){
			
			ballot.vote(best);
			
			agenda.clear(); 
			agenda.add(best);
			agenda.add(alternatives.get(i));
			
			best = p.chooseOne();
		}
		
		ballot.vote(best);
		
		return best;
	}

}
