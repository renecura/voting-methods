package net.renecura.voting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

public final class Ordering {
	
	private SortedSet<Double> utilities;
	private HashMap<Double, ArrayList<Alternative>> alternatives;
	
	private ArrayList<Alternative> alts;
	
	private Preference p;
	
	public Ordering(Preference p){
		alternatives = new HashMap<Double, ArrayList<Alternative>>();
		utilities = new ConcurrentSkipListSet<Double>();
		
		alts = new ArrayList<Alternative>(); 
		
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
		ArrayList<Alternative> list;
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
				list = new ArrayList<Alternative>();
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
	public ArrayList<Alternative> position(int index){
		
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
	public ArrayList<Alternative> first(){
		if (alts.isEmpty()) return null;
		
		reorder();
		return alternatives.get(utilities.first());
	}
	
	/**
	 * Recupera la lista de alternativas en la última posición.
	 * @return Lista de alternativas en la última posición.
	 */
	public ArrayList<Alternative> last(){
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
