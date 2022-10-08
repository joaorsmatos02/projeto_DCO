package main;

import java.util.List;

import ajuda.Alojamento;
import regiao.Regiao;
import utilizador.Voluntario;

/**
 * Esta classe representa o caso de uso 1, em que um voluntario regista um
 * alojamento
 * 
 * @author Joao Matos 56292
 * @author Joao Santos 57103
 *
 */
public class RegistarAlojamentoStrategy extends RegistarAjudaHandler {

	/**
	 * Alojamento corrente da sessao
	 */
	private Alojamento alojamentoCorrente;

	/**
	 * Construtor da classe
	 * 
	 * @param voluntarioCorrente - o voluntario associado
	 */
	public RegistarAlojamentoStrategy(Voluntario voluntarioCorrente) {
		super.voluntarioCorrente = voluntarioCorrente;
		this.alojamentoCorrente = new Alojamento(voluntarioCorrente);
		super.ajudaCorrente = this.alojamentoCorrente;
	}

	/**
	 * Cria uma lista representativa do catalogo de regioes do sistema. Define a
	 * capacidade do alojamento
	 * 
	 * @param nmr - capacidade do alojamento
	 * @return lista de regioes composta pelas regioes do sistema
	 */
	public List<Regiao> indicaNumPessoas(int nmr) {
		List<Regiao> lr = MigrantMatcher.catalogoDeRegioes.getListaRegioes();
		alojamentoCorrente.alteraCapacidade(nmr);
		return lr;
	}

	/**
	 * Define a regiao do alojamento
	 * 
	 * @param regiao - regiao associada ao alojamento
	 */
	public void indicaRegiao(Regiao regiao) {
		alojamentoCorrente.alteraRegiao(regiao);
		super.enviaSMS();
	}
}
