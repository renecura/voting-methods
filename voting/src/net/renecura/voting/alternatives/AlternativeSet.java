package net.renecura.voting.alternatives;

import java.util.concurrent.CopyOnWriteArraySet;

public class AlternativeSet extends CopyOnWriteArraySet<Alternative> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3257986007458567446L;

	
	public Alternative get(int index){
		return (Alternative)this.toArray()[index];
	}
}
