package net.renecura.voting;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.renecura.voting.alternatives.Alternative;

public class BallotBox implements Utility {

	Map<Alternative, Integer> votes;
	
    public BallotBox() {
        this.votes = new ConcurrentHashMap<Alternative, Integer>();
    }
  
    public int vote(Alternative alt){
    	return vote(alt, 1);
    }
    
    public int vote(Alternative alt, int votes){

    	// Si no contiene la alternativa la crea y le asigna los votos.
    	if(!this.votes.containsKey(alt)){
    		this.votes.put(alt, votes);
    		return votes;
    	}
    	
    	// Si contiene la alternativa acumula los votos.
    	votes += this.votes.get(alt).intValue();
    	this.votes.put(alt, votes);
    	return votes;
    }
    
	@Override
	public double utility(Alternative alt) {
		return (this.votes.containsKey(alt))? this.votes.get(alt).intValue(): 0;
	}

}
