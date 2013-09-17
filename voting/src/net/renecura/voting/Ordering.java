package net.renecura.voting;

import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import net.renecura.voting.alternatives.Alternative;
import net.renecura.voting.alternatives.AlternativeSet;

public final class Ordering {
	
	private AlternativeSet set;
	
	private Utility preference;
	
	public Ordering(AlternativeSet set, Utility preference){
		
		this.set = set;
		this.preference = preference;
		
		//this.alternatives = new HashMap<Double, AlternativeSet>();
		//this.utilities = new ConcurrentSkipListSet<Double>();
		
	}

	public Map<Double, AlternativeSet> utilityMap(){
		
		// Si el conjunto de alternativas estaá vacion retorna null.
		if (this.set.isEmpty()) return null;
		
		Map<Double, AlternativeSet> map = new ConcurrentHashMap<Double, AlternativeSet>();
		Iterator<Alternative> aIt = this.set.iterator();
		
		AlternativeSet subset;
		Alternative alt;
		Double u; // Utilidad
		
		// Construye el mapa de utilidades.
		while (aIt.hasNext()) {

			alt = aIt.next();

			u = preference.utility(alt);
			subset =  map.get(u);

			// Si el subset es null, lo crea.
			if (subset == null) {
				subset = new AlternativeSet();
				map.put(u, subset);
			}

			subset.add(alt);
		}
		
		return map;
	}
	
	public SortedSet<Double> utilitySet(Map<Double, AlternativeSet> map){
		SortedSet<Double> uSet = new ConcurrentSkipListSet<Double>(new UtilityComparator());
		
		uSet.addAll(map.keySet());
		
		return uSet;
	}
	
	/**
	 * Retorna la lista de alternativas correspondientes a la posición relativa dentro del ordenamiento.
	 * @param index Posición en el ordenamiento.
	 * @return Lista de alternativas en la posición.
	 */
	public AlternativeSet position(int index){
		
		if (set.isEmpty()) return null;
				
		Map<Double, AlternativeSet> uMap = utilityMap();
		SortedSet<Double> uSet = utilitySet(uMap);
		
		if(index >= uSet.size()) throw new IndexOutOfBoundsException("Index value: "+index); 
		
		Double u[] = new Double[uSet.size()];
		uSet.toArray(u);
		
		return uMap.get(u[index]);
	}	
	
	/**
	 * Recupera la lista de alternativas en la primera posición.
	 * @return Lista de alternativas en la primera posición.
	 */
	public AlternativeSet first(){
		if (set.isEmpty()) return null;
		
		Map<Double, AlternativeSet> uMap = utilityMap();
		SortedSet<Double> uSet = utilitySet(uMap);
		
		return uMap.get(uSet.first());
	}
	
	/**
	 * Recupera la lista de alternativas en la última posición.
	 * @return Lista de alternativas en la última posición.
	 */
	public AlternativeSet last(){
		
		if (set.isEmpty()) return null;
		
		Map<Double, AlternativeSet> uMap = utilityMap();
		SortedSet<Double> uSet = utilitySet(uMap);
		
		return uMap.get(uSet.last()); 
	}
	
	
	public String toString() {
		
		if (set.isEmpty()) return "[empty]";
		
		Map<Double, AlternativeSet> uMap = utilityMap();
		SortedSet<Double> uSet = utilitySet(uMap);
		
		Iterator<Double> uIt = uSet.iterator();
		Iterator<Alternative> aIt;
		
		Double u;
		String s = "[";
		int i = 0;
		
		while(uIt.hasNext()){
			u = uIt.next();
			
			if (i > 0) s += " | ";
			
			s += i + " (" + u.doubleValue() + ") : ";
			
			aIt = uMap.get(u).iterator();
			
			while(aIt.hasNext()){
				s += aIt.next() + "; ";
			}
			
			i++;
		}
		
		return s + "]";
	}

	public Utility getPreference() {
		return preference;
	}
	
}
