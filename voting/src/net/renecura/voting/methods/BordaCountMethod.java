package net.renecura.voting.methods;

import java.util.Iterator;
import java.util.SortedSet;

import net.renecura.voting.Ordering;
import net.renecura.voting.VotingMethod;
import net.renecura.voting.alternatives.Alternative;
import net.renecura.voting.alternatives.AlternativeSet;

public class BordaCountMethod extends VotingMethod {
	
	public BordaCountMethod(AlternativeSet set) {
		super(set);
	}
	
	@Override
	public AlternativeSet choose() {
		int i, uSize;
		Ordering ord;
		Iterator<Ordering> ordIt = this.individualOrd.iterator();
		Iterator<Alternative> altIt;
		
		while(ordIt.hasNext()){
			ord = ordIt.next();
			
			SortedSet<Double> uSet = ord.utilitySet(ord.utilityMap());
			uSize = uSet.size();
			
			for(i = 0; i < uSize; i++){
				altIt = ord.position(i).iterator();
				
				while(altIt.hasNext()){
					this.ballot.vote(altIt.next(), uSize - i - 1);
				}
			}			
		}
		
		return this.welfare.first();
	}

	@Override
	public Alternative chooseOne() {
		return this.choose().get(0);
	}
	
}
