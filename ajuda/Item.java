package ajuda;

import utilizador.Voluntario;

/**
 * Esta classe representa um item a ser usado pelo MigrantMatcher. Esta classe
 * extende a classe Ajuda
 * 
 * @author Joao Matos 56292
 * @author Joao Santos 57103
 *
 */
public class Item extends Ajuda {

	/**
	 * A descricao do item
	 */
	private String descricao;

	/**
	 * Construtor da classe
	 * 
	 * @param voluntario
	 */
	public Item(Voluntario voluntario) {
		super(voluntario);
	}

	/**
	 * Altera a descricao do item
	 * 
	 * @param descricao - a nova descricao
	 */
	public void alteraDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return ("Item com o codigo " + hashCode() + " com a descricao " + descricao);
	}
}
