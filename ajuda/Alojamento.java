package ajuda;

import regiao.Regiao;
import utilizador.Voluntario;

/**
 * Esta classe representa um alojamento a ser usado pelo MigrantMatcher. Esta
 * classe extende a classe Ajuda
 * 
 * @author Joao Matos 56292
 * @author Joao Santos 57103
 *
 */
public class Alojamento extends Ajuda {

	/**
	 * A capacidade do alojamento
	 */
	private int capacidade;

	/**
	 * A regiao associada ao alojamento
	 */
	private Regiao regiao;

	/**
	 * Construtor da classe Alojamento
	 * 
	 * @param voluntario - o voluntario a associar a esta ajuda
	 */
	public Alojamento(Voluntario voluntario) {
		super(voluntario);
	}

	/**
	 * Altera a capacidade do alojamento
	 * 
	 * @param nmr - a nova capacidade
	 */
	public void alteraCapacidade(int nmr) {
		this.capacidade = nmr;
	}

	/**
	 * Altera a regiao do alojamento
	 * 
	 * @param regiao - a nova regiao
	 */
	public void alteraRegiao(Regiao regiao) {
		this.regiao = regiao;
	}

	/**
	 * Compara a regiao fornecida com a regiao do alojamento
	 * 
	 * @param regiao - a regiao a comparar
	 * @return true se as regioes forem iguais, false caso contrario
	 */
	public boolean verificaRegiao(Regiao regiao) {
		return this.regiao.equals(regiao);
	}

	/**
	 * Retorna a capacidade do alojamento
	 * 
	 * @return a capacidade do alojamento
	 */
	public int getCapacidade() {
		return capacidade;
	}

	@Override
	public String toString() {
		return String.valueOf("Alojamento com o codigo " + hashCode() + " de capacidade " + capacidade);
	}
}
