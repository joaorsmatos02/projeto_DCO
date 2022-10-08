package catalogos;

import java.util.ArrayList;
import java.util.List;

import ajuda.Ajuda;
import ajuda.Alojamento;
import ajuda.Item;
import regiao.Regiao;
import utilizador.Voluntario;

/**
 * Esta classe representa o catalogo de ajudas a ser usado pelo MigrantMatcher
 * 
 * @author Joao Matos 56292
 * @author Joao Santos 57103
 *
 */
public class CatalogoDeAjudas {

	/**
	 * Lista que agrega todas as ajudas disponiveis no MigrantMatcher
	 */
	private List<Ajuda> listaAjudas;

	/**
	 * Instancia unica da classe
	 */
	private static CatalogoDeAjudas instance;

	/**
	 * Construtor privado
	 */
	private CatalogoDeAjudas() {
		listaAjudas = new ArrayList<>();
	}

	/**
	 * Este metodo retorna a instancia atual de CatalogoDeAjudas ou cria uma nova
	 * caso nao exista nenhuma
	 * 
	 * @return uma instancia de CatalogoDeAjudas
	 */
	public static CatalogoDeAjudas getCatalogoDeAjudas() {
		if (instance == null)
			instance = new CatalogoDeAjudas();
		return instance;
	}

	/**
	 * Adiciona a ajuda fornecida a lista de ajudas
	 * 
	 * @param ajuda - a ajuda a ser adicionada
	 */
	public void adicionarAjuda(Ajuda ajuda) {
		listaAjudas.add(ajuda);
	}

	/**
	 * Devolve a lista de ajudas
	 * 
	 * @return a lista de ajudas atual
	 */
	public List<Ajuda> getListaAjudas() {
		return listaAjudas;
	}

	/**
	 * Cria e devolve uma lista contendo todas as ajudas do catalogo disponiveis na
	 * regiao escolhida
	 * 
	 * @param regiao - a regiao escolhida
	 * @return uma lista contendo todas as ajudas em regiao
	 */
	public List<Ajuda> criaLista(Regiao regiao) {
		List<Ajuda> listaRegiao = new ArrayList<>();
		for (Ajuda a : listaAjudas) {
			if (a instanceof Item)
				listaRegiao.add(a);
			else if (a instanceof Alojamento) {
				Alojamento al = (Alojamento) a;
				if (al.verificaRegiao(regiao))
					listaRegiao.add(a);
			}
		}
		return listaRegiao;
	}

	/**
	 * Remove da lista de ajudas todas as ajudas contidas em lam e devolve uma lista
	 * dos respetivos voluntarios associados
	 * 
	 * @param lam - a lista de ajudas a remover
	 * @return a lista de voluntarios associados a cada ajuda de lam
	 */
	public List<Voluntario> removeAjudas(List<Ajuda> lam) {
		List<Voluntario> lv = new ArrayList<>();
		for (Ajuda a : lam) {
			lv.add(a.getVoluntario());
			listaAjudas.remove(a);
		}
		return lv;
	}
}
