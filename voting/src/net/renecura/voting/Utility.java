package net.renecura.voting;

import net.renecura.voting.alternatives.Alternative;

public interface Utility {
	/**
	 * Implementaci�n por defecto de la medici�n de utilidad en el contexto.
	 * @param alt Alternative Alternativa a evaluar.
	 * @return Utilidad de la alternativa.
	 */
	public double utility(Alternative alt);
}
