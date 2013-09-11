package net.renecura.voting;

public class Preference {

	private Ordering ordering;
	
	public Preference(){
		this.ordering = new Ordering(this);
	}
	
	public Ordering getOrdering(){
		return this.ordering;
	}
	
	/**
	 * Implementación por defecto de la función de utilidad que describe la preferencia.
	 * @param alt Alternative Alternativa a evaluar.
	 * @return Utilidad de la alternativa.
	 */
	public double utility(Alternative alt){
		return Double.longBitsToDouble(alt.getValue());
	}
	
	
}
