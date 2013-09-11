package net.renecura.voting;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class VotingMethod {

	protected ArrayList<Preference> preferences;
	protected ArrayList<Alternative> alternatives;
	
	public VotingMethod(ArrayList<Alternative> alternatives, ArrayList<Preference> preferences){
		this.preferences = preferences;
		this.alternatives = alternatives;
		
		Iterator<Preference> pIt = preferences.iterator();
		
		while(pIt.hasNext()){
			pIt.next().getOrdering().addAlternatives(this.alternatives);
		}
		
	}
	
	abstract public ArrayList<Alternative> choose();

	abstract public Alternative chooseOne();

}
