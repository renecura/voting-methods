package net.renecura.voting.methods;

import java.util.Random;

import net.renecura.voting.alternatives.Alternative;
import net.renecura.voting.alternatives.AlternativeSet;

public class PluralityRandomTieBreakingMethod extends PluralityMethod {
	
	Random random;
	
	public PluralityRandomTieBreakingMethod(AlternativeSet set) {
		super(set);
		this.random = new Random();
	}

	@Override
	public Alternative chooseOne() {
		AlternativeSet chosenSet = this.choose(); 
		return chosenSet.get(this.random.nextInt(chosenSet.size()));
	}
	
}
