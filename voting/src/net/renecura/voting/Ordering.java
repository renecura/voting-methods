package net.renecura.voting;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

import net.renecura.voting.alternatives.Alternative;
import net.renecura.voting.alternatives.AlternativeSet;

public final class Ordering {
	
	private SortedSet<Double> utilities;
	private HashMap<Double, AlternativeSet> alternatives;
	
	private AlternativeSet alts;
	
	private Utility p;
	
	public Ordering(Utility p){
		alternatives = new HashMap<Double, AlternativeSet>();
		utilities = new ConcurrentSkipListSet<Double>();
		
		alts = new AlternativeSet(); 
		
		this.p = p;
	}

	public void addAlternative(Alternative alt) {
		alts.add(alt);		
	}
	
	public void addAlternatives(Collection<Alternative> c) {
		Iterator<Alternative> it = c.iterator();
		
		while(it.hasNext())
			this.addAlternative(it.next());		
	}
	
	/**
	 * Reordena las alternativas debido a que la utilidad de las preferencias puede cambiar en tiempo de ejecución.
	 * Este metodo debe ser invocado por todas las funciones de recupero.
	 */
	private void reorder(){
		
		if (this.alts.isEmpty()) return;
		
		Iterator<Alternative> aIt = this.alts.iterator();
		Alternative alt;
		AlternativeSet list;
		Double u;
		
		// Vacia los ordenamientos previos.
		alternatives.clear();
		utilities.clear();
		
		// Reconstruye el ordenamiento.
		while (aIt.hasNext()){
			
			alt = aIt.next();
			
			u = Double.valueOf(p.utility(alt));
			list = alternatives.get(u);
			
			if (list == null){
				list = new AlternativeSet();
				alternatives.put(u, list);
			}
			
			list.add(alt);
			utilities.add(u);			
		}
		
	}

	/**
	 * Retorna la lista de alternativas correspondientes a la posición relativa dentro del ordenamiento.
	 * @param index Posición en el ordenamiento.
	 * @return Lista de alternativas en la posición.
	 */
	public AlternativeSet position(int index){
		
		if (alts.isEmpty()) return null;
		if(index >= utilities.size()) throw new IndexOutOfBoundsException("Index value: "+index); 
		
		reorder();
		
		Double u[] = new Double[utilities.size()];
		utilities.toArray(u);
		
		return alternatives.get(u[index]);
	}	
	
	/**
	 * Recupera la lista de alternativas en la primera posición.
	 * @return Lista de alternativas en la primera posición.
	 */
	public AlternativeSet first(){
		if (alts.isEmpty()) return null;
		
		reorder();
		return alternatives.get(utilities.first());
	}
	
	/**
	 * Recupera la lista de alternativas en la última posición.
	 * @return Lista de alternativas en la última posición.
	 */
	public AlternativeSet last(){
		if (alts.isEmpty()) return null;
		
		reorder();
		return alternatives.get(utilities.last()); 
	}
	

	/**
	 * Remueve una alternativa de la lista de alternativas.
	 * @warning Implementación prototipo, usar bajo su propio riesgo.
	 * @param alt
	 */
	public void removeAlternative(Alternative alt) {
		this.alts.remove(alt);		
	}
	
	public String toString() {
		Iterator<Double> uIt = utilities.iterator();
		Iterator<Alternative> aIt;
		
		Double u;
		String s = "[";
		int i = 0;
		
		while(uIt.hasNext()){
			u = uIt.next();
			
			if (i > 0) s += " | ";
			
			s += i + " (" + u.doubleValue() + ") : ";
			
			aIt = alternatives.get(u).iterator();
			
			while(aIt.hasNext()){
				s += aIt.next() + "; ";
			}
			
			i++;
		}
		
		return s + "]";
	}
	
}
