package net.renecura.voting.methods;

import java.util.Iterator;

import net.renecura.voting.Ordering;
import net.renecura.voting.VotingMethod;
import net.renecura.voting.alternatives.Alternative;
import net.renecura.voting.alternatives.AlternativeSet;

public class PluralityMethod extends VotingMethod {
	
	public PluralityMethod(AlternativeSet set) {
		super(set);
	}
	
	@Override
	public AlternativeSet choose() {
		
		Ordering ord;
		Iterator<Ordering> ordIt = this.individualOrd.iterator();
		Iterator<Alternative> altIt;
		
		while(ordIt.hasNext()){
			ord = ordIt.next();
			
			altIt = ord.first().iterator();
			
			while(altIt.hasNext()){
				this.ballot.vote(altIt.next());
			}
		}
		
		return this.welfare.first();
	}

	@Override
	public Alternative chooseOne() {
		return this.choose().get(0);
	}
	
}
