package utilizador;

import java.util.ArrayList;
import java.util.List;

import ajuda.Ajuda;
import regiao.Regiao;

/**
 * Esta classe representa um migrante do MigrantMatcher
 * 
 * @author Joao Matos 56292
 * @author Joao Santos 57103
 *
 */
public class Migrante extends Utilizador {

	/**
	 * O nome do migrante
	 */
	private String nome;

	/**
	 * A regiao do migrante
	 */
	private Regiao regiao;

	/**
	 * A lista de membros associada ao migrante
	 */
	private Membro[] listaMembros;

	/**
	 * A lista de ajudas escolhidas pelo migrante
	 */
	private List<Ajuda> listaAjuda;

	/**
	 * booleano que representa a ativacao das notificacoes
	 */
	private boolean notificacao;

	/**
	 * Construtor da classe
	 * 
	 * @param contacto - O contacto a associar ao migrante
	 */
	public Migrante(String contacto) {
		super(contacto);
		listaAjuda = new ArrayList<>();
		notificacao = false;
	}

	/**
	 * Altera o nome e contacto associados ao migrante
	 * 
	 * @param nome     - O novo nome
	 * @param contacto - O nove contacto
	 */
	public void alteraNomeContacto(String nome, String contacto) {
		this.nome = nome;
		super.contacto = contacto;
	}

	/**
	 * Retorna o numero total de pessoas associadas ao migrante, incluindo o proprio
	 * 
	 * @return o numero total de pessoas
	 */
	public int getNumeroPessoas() {
		if (listaMembros == null)
			return 1;
		else
			return listaMembros.length + 1;
	}

	/**
	 * Inicializa a lista de membros associados ao migrante
	 * 
	 * @param nmr - o numero total de pessoas
	 */
	public void alteraNumPessoas(int nmr) {
		listaMembros = new Membro[nmr - 1];
	}

	/**
	 * Altera a regiao associada ao migrante
	 * 
	 * @param regiao - A nova regiao a associar
	 */
	public void alteraRegiao(Regiao regiao) {
		this.regiao = regiao;
	}

	/**
	 * Associa um novo membro ao migrante
	 * 
	 * @param nome - O nome do novo membro
	 */
	public void adicionaMembro(String nome) {
		boolean adicionado = false;
		for (int i = 0; i < listaMembros.length && !adicionado; i++) {
			if (listaMembros[i] == null) {
				listaMembros[i] = new Membro(nome);
				adicionado = true;
			}
		}
	}

	/**
	 * Ativa as notificacoes do migrante para a regiao escolhida
	 */
	public void ativaNotificacao() {
		notificacao = true;
	}

	/**
	 * Retorna a lista de ajudas escolhidas pelo migrante
	 * 
	 * @return a lista de ajudas associadas ao migrante
	 */
	public List<Ajuda> getListaAjudaMigrante() {
		return listaAjuda;
	}

	/**
	 * Associa a ajuda fornecida ao migrante
	 * 
	 * @param ajuda - a ajuda a associar
	 */
	public void associaAjuda(Ajuda ajuda) {
		listaAjuda.add(ajuda);
	}
}
