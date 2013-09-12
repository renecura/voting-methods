package net.renecura.voting.alternatives;

import net.renecura.voting.Utility;

public class Preference implements Utility{

	public Preference(){
	}
	
	/**
	 * Implementación por defecto de la función de utilidad que describe la preferencia.
	 * @param alt Alternative Alternativa a evaluar.
	 * @return Utilidad de la alternativa.
	 */
	@Override
	public double utility(Alternative alt){
		return 0;
	}
	
	
}
