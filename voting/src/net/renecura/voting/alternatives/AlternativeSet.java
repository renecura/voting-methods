package net.renecura.voting.alternatives;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;

public class AlternativeSet extends CopyOnWriteArraySet<Alternative> {

	private Random random;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3257986007458567446L;

	
	public Alternative get(int index){
		return (Alternative)this.toArray()[index];
	}
	
	public Alternative getAtRandom(){
		
		if (this.random == null) {
			this.random = new Random();
		}
		
		return (Alternative)this.toArray()[this.random.nextInt(this.size())];
	}
}
