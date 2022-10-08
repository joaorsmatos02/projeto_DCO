package catalogos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.PidgeonSMSAdapter;
import main.SMSSender;
import utilizador.Migrante;
import utilizador.Voluntario;

/**
 * Esta classe representa o catalogo de utilizadores a ser usado pelo
 * MigrantMatcher
 * 
 * @author Joao Matos 56292
 * @author Joao Santos 57103
 *
 */
public class CatalogoDeUtilizadores {

	/**
	 * Mapa de pares contacto/voluntario
	 */
	private Map<String, Voluntario> voluntarios;

	/**
	 * Mapa de pares contacto/migrante
	 */
	private Map<String, Migrante> migrantes;

	/**
	 * Adaptador para o servico de sms
	 */
	private SMSSender smsAdapter;

	/**
	 * Instancia unica da classe
	 */
	private static CatalogoDeUtilizadores instance;

	/**
	 * Construtor privado
	 */
	private CatalogoDeUtilizadores() {
		voluntarios = new HashMap<>();
		migrantes = new HashMap<>();
		this.smsAdapter = PidgeonSMSAdapter.getPidgeonSMSAdapter();
	}

	/**
	 * Este metodo retorna a instancia atual de CatalogoDeUtilizadores ou cria uma
	 * nova caso nao exista nenhuma
	 * 
	 * @return uma instancia de CatalogoDeUtilizadores
	 */
	public static CatalogoDeUtilizadores getCatalogoDeUtilizadores() {
		if (instance == null)
			instance = new CatalogoDeUtilizadores();
		return instance;
	}

	/**
	 * Obtem o voluntario associado ao contacto fornecido
	 * 
	 * @param contacto - o contacto a pesquisar
	 * @return o voluntario associado a contacto
	 */
	public Voluntario getVoluntario(String contacto) {
		return voluntarios.get(contacto);
	}

	/**
	 * Adiciona migranteCorrente ao mapa de migrantes, com a chave contacto
	 * 
	 * @param contacto         - a chave a ser utilizada
	 * @param migranteCorrente - o migrante a adicionar
	 */
	public void adicionarMigrante(String contacto, Migrante migranteCorrente) {
		migrantes.put(contacto, migranteCorrente);
	}

	/**
	 * Envia um sms a todos os voluntarios pertencentes a lv
	 * 
	 * @param lv - lista de voluntarios
	 */
	public void enviaSmsVoluntario(List<Voluntario> lv) {

		for (Voluntario v : lv) {
			smsAdapter.envia(v.getContacto(), "Uma ajuda que registou foi atribuida a um migrante.");
		}
	}

	public void adicionaVoluntario(String contacto) {
		voluntarios.put(contacto, new Voluntario(contacto));
	}
}
