package main;

import ajuda.Item;
import utilizador.Voluntario;

/**
 * Esta classe representa o caso de uso 1, em que um voluntario regista um item
 * 
 * @author Joao Matos 56292
 * @author Joao Santos 57103
 *
 */
public class RegistarItemStrategy extends RegistarAjudaHandler {

	/**
	 * Item corrente da sessao
	 */
	private Item itemCorrente;

	/**
	 * Construtor da classe
	 * 
	 * @param voluntarioCorrente - o voluntario a associar
	 */
	public RegistarItemStrategy(Voluntario voluntarioCorrente) {
		super.voluntarioCorrente = voluntarioCorrente;
		this.itemCorrente = new Item(voluntarioCorrente);
		super.ajudaCorrente = this.itemCorrente;
	}

	/**
	 * Define a descricao do item
	 * 
	 * @param descricao - descricao a associar ao item
	 */
	public void indicaDescricaoItem(String descricao) {
		itemCorrente.alteraDescricao(descricao);
		super.enviaSMS();
	}
}
