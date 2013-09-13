package net.renecura.voting;

import net.renecura.voting.alternatives.Alternative;

public interface Utility {
	/**
	 * Implementación por defecto de la medición de utilidad en el contexto.
	 * @param alt Alternative Alternativa a evaluar.
	 * @return Utilidad de la alternativa.
	 */
	public double utility(Alternative alt);
}
