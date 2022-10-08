package catalogos;

import java.util.ArrayList;
import java.util.List;

import regiao.Regiao;

/**
 * Esta classe representa o catalogo de regioes a ser usado pelo MigrantMatcher
 * 
 * @author Joao Matos 56292
 * @author Joao Santos 57103
 *
 */
public class CatalogoDeRegioes {

	/**
	 * Lista que agrega todas as regioes disponiveis no MigrantMatcher
	 */
	private List<Regiao> listaRegioes;

	/**
	 * Instancia unica da classe
	 */
	private static CatalogoDeRegioes instance;

	/**
	 * Construtor privado
	 */
	private CatalogoDeRegioes() {
		listaRegioes = new ArrayList<>();
	}

	/**
	 * Este metodo retorna a instancia atual de CatalogoDeRegioes ou cria uma nova
	 * caso nao exista nenhuma
	 * 
	 * @return uma instancia de CatalogoDeRegioes
	 */
	public static CatalogoDeRegioes getCatalogoDeRegioes() {
		if (instance == null)
			instance = new CatalogoDeRegioes();
		return instance;
	}

	/**
	 * Devolve a lista de todas as regioes disponiveis
	 * 
	 * @return uma lista contendo todas as regioes
	 */
	public List<Regiao> getListaRegioes() {
		return listaRegioes;
	}

	/**
	 * Cria e adiciona uma nova regiao ao catalogo com o nome fornecido
	 * 
	 * @param nome - o nome da regiao a adicionar
	 */
	public void adicionaRegiao(String nomeRegiao) {
		listaRegioes.add(new Regiao(nomeRegiao));
	}
}
