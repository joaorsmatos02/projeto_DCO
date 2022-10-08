package main;

import java.util.ArrayList;
import java.util.List;

import ajuda.Ajuda;
import ajuda.Alojamento;
import regiao.Regiao;
import utilizador.Migrante;
import utilizador.Voluntario;

/**
 * Esta classe representa o caso de uso 2, em que um migrante se regista e
 * escolhe ajudas
 * 
 * @author Joao Matos 56292
 * @author Joao Santos 57103
 *
 */
public class ProcurarAjudaHandler {

	/**
	 * O migrante atual
	 */
	private Migrante migranteCorrente;

	/**
	 * Instancia unica da classe
	 */
	private static ProcurarAjudaHandler instance;

	/**
	 * Construtor privado
	 */
	private ProcurarAjudaHandler() {

	}

	/**
	 * Este metodo retorna a instancia atual de ProcurarAjudaHandler ou cria uma
	 * nova caso nao exista nenhuma
	 * 
	 * @return uma instancia de ProcurarAjudaHandler
	 */
	public static ProcurarAjudaHandler getProcurarAjudaHandler() {
		if (instance == null)
			instance = new ProcurarAjudaHandler();
		return instance;
	}

	/**
	 * Cria um novo migrante
	 */
	public void iniciaRegisto() {
		migranteCorrente = new Migrante(null);
	}

	/**
	 * Altera o nome e contacto associados ao migrante
	 * 
	 * @param nome     - O novo nome
	 * @param contacto - O nove contacto
	 */
	public void indicaNomeContacto(String nome, String contacto) {
		migranteCorrente.alteraNomeContacto(nome, contacto);
	}

	/**
	 * Altera o numero de pessoas associado ao migrante
	 * 
	 * @param nmr - o numero total de pessoas
	 */
	public void indicaNumPessoas(int nmr) {
		migranteCorrente.alteraNumPessoas(nmr);
	}

	/**
	 * Associa um novo membro ao migrante
	 * 
	 * @param nome - O nome do novo membro
	 */
	public void indicaMembro(String nome) {
		migranteCorrente.adicionaMembro(nome);
	}

	/**
	 * Adiciona o migrante corrente ao Catalogo de Utilizadores
	 * 
	 * @return o Catalogo de regioes
	 */
	public List<Regiao> confirmaMigrante() {
		List<Regiao> lr = MigrantMatcher.catalogoDeRegioes.getListaRegioes();
		String contacto = migranteCorrente.getContacto();

		MigrantMatcher.catalogoDeUtilizadores.adicionarMigrante(contacto, migranteCorrente);
		return lr;
	}

	/**
	 * Define-se a regiao do migrante corrente e criada a lista de ajudas
	 * compativeis consoante a regiao do migrante corrente
	 * 
	 * @param regiao - regiao a associar ao migrante corrente
	 * @return lista de ajudas compativeis com a regiao do migrante
	 */
	public List<Ajuda> indicaRegiao(Regiao regiao) {
		migranteCorrente.alteraRegiao(regiao);

		List<Ajuda> listaAjudas = MigrantMatcher.catalogoDeAjudas.getListaAjudas();
		int numeroPessoas = migranteCorrente.getNumeroPessoas();

		List<Ajuda> lac = new ArrayList<>();

		for (Ajuda a : listaAjudas) {
			if (a instanceof Alojamento) {
				Alojamento al = (Alojamento) a;
				if (al.verificaRegiao(regiao) && al.getCapacidade() >= numeroPessoas)
					lac.add(al);
			} else
				lac.add(a);
		}
		return lac;
	}

	/**
	 * Associa a ajuda fornecida ao migrante
	 * 
	 * @param ajuda - a ajuda a associar
	 */
	public void indicaAjuda(Ajuda ajuda) {
		migranteCorrente.associaAjuda(ajuda);
	}

	public void confirmaAjuda() {
		List<Ajuda> lam = migranteCorrente.getListaAjudaMigrante();
		List<Voluntario> lv = MigrantMatcher.catalogoDeAjudas.removeAjudas(lam);

		MigrantMatcher.catalogoDeUtilizadores.enviaSmsVoluntario(lv);
	}

	/**
	 * Se bool == true as notificacoes do migrante sao ativadas
	 * 
	 * @param bool - booleano que indica se o migrante pretende ativar as
	 *             notificacoes
	 */
	public void ativaNotificacao(boolean bool) {
		if (bool)
			migranteCorrente.ativaNotificacao();
	}
}
