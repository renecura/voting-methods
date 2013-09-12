package net.renecura.voting;

import java.util.ArrayList;

import net.renecura.voting.alternatives.Alternative;
import net.renecura.voting.alternatives.AlternativeSet;

public abstract class VotingMethod {

	protected AlternativeSet alternatives;
	protected ArrayList<Ordering> individualOrd;
	
	protected BallotBox ballot;
	protected Ordering welfare;
	
	public VotingMethod(AlternativeSet set){		
		this.individualOrd = new ArrayList<Ordering>();
		this.alternatives = set;
		
		this.ballot = new BallotBox();
		this.welfare = new Ordering(this.alternatives, this.ballot);
	}
	
	public void addPreference(Utility preference){
		Ordering ord = new Ordering(this.alternatives, preference);
		this.individualOrd.add(ord);
	}
	
	abstract public AlternativeSet choose();

	abstract public Alternative chooseOne();

}
