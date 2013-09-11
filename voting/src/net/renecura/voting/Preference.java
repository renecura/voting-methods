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
	 * Implementaci�n por defecto de la funci�n de utilidad que describe la preferencia.
	 * @param alt Alternative Alternativa a evaluar.
	 * @return Utilidad de la alternativa.
	 */
	public double utility(Alternative alt){
		return Double.longBitsToDouble(alt.getValue());
	}
	
	
}
