package ajuda;

import utilizador.Voluntario;

/**
 * Esta classe representa uma ajuda a ser usada pelo MigrantMatcher
 * 
 * @author Joao Matos 56292
 * @author Joao Santos 57103
 *
 */
public abstract class Ajuda {

	/**
	 * O voluntario associado a esta ajuda
	 */
	private Voluntario voluntarioAssociado;

	/**
	 * Construtor da classe ajuda
	 * 
	 * @param voluntario - o voluntario a associar a esta ajuda
	 */
	public Ajuda(Voluntario voluntario) {
		this.voluntarioAssociado = voluntario;
	}

	/**
	 * Obtem o voluntario associado a esta ajuda
	 * 
	 * @return o voluntario associado
	 */
	public Voluntario getVoluntario() {
		return voluntarioAssociado;
	}

	/**
	 * Metodo abstrato toString para forçar uma implementação nas classes filhas
	 */
	public abstract String toString();
}
