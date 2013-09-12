package net.renecura.voting.test;

import net.renecura.voting.VotingMethod;
import net.renecura.voting.alternatives.AlternativeSet;
import net.renecura.voting.methods.PluralityMethod;

public class TestPlurality {

	public static void main(String[] args) {
		AlternativeSet set = new AlternativeSet();
		
		VotingMethod method = new PluralityMethod(set);
		
		method.choose();
		
	}

}
