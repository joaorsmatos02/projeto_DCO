package main;

import java.util.Random;

import ajuda.Ajuda;
import utilizador.Voluntario;

/**
 * Esta classe representa o caso de uso 1, em que um voluntario regista uma
 * ajuda
 * 
 * @author Joao Matos 56292
 * @author Joao Santos 57103
 *
 */
public class RegistarAjudaHandler {

	/**
	 * Voluntario corrente da sessao
	 */
	protected Voluntario voluntarioCorrente;

	/**
	 * Ajuda corrente da sessao
	 */
	protected Ajuda ajudaCorrente;

	/**
	 * Codigo a gerar para o envio do sms
	 */
	private String cod;

	/**
	 * Adaptador para o servico de sms
	 */
	private TelegramSMSAdapter smsAdapter;

	/**
	 * Construtor da classe
	 */
	public RegistarAjudaHandler() {
		this.smsAdapter = TelegramSMSAdapter.getTelegramSMSAdapter();
	}

	/**
	 * Definir voluntarioCorrente com o voluntario do respetivo contacto
	 * 
	 * @param contacto - contacto do voluntario
	 * @return true se existe voluntario com o contacto fornecido, false caso
	 *         contrario
	 */
	public boolean indicaContacto(String contacto) {
		voluntarioCorrente = MigrantMatcher.catalogoDeUtilizadores.getVoluntario(contacto);
		if (voluntarioCorrente == null)
			return false;
		return true;
	}

	/**
	 * Se tipo for 1 e criada uma estrategia de Alojamento, caso contrario um item
	 * 
	 * @param tipo - inteiro que indica tipo de ajuda a criar
	 */
	public RegistarAjudaHandler indicaAjuda(int tipo) {
		if (tipo == 1)
			return new RegistarAlojamentoStrategy(voluntarioCorrente);
		return new RegistarItemStrategy(voluntarioCorrente);
	}

	/**
	 * Envia um sms com um codigo para o voluntario atual da sessao
	 */
	protected void enviaSMS() {
		Random r = new Random();
		cod = String.valueOf(r.nextInt(89999) + 10000);
		smsAdapter.envia(voluntarioCorrente.getContacto(), cod);
	}

	/**
	 * Retorna o codigo gerado ao enviar o SMS de confirmacao. Apenas usado para
	 * testes junit
	 * 
	 * @return - o codigo gerado em envidaSMS
	 */
	public String getCod() {
		return cod;
	}

	/**
	 * Verifica se cod eh valido Se cod valido, adiciona a ajuda ao catalogo de
	 * ajudas do sistema
	 * 
	 * @param cod - codigo indicado pelo voluntario
	 * @return true se o codigo for valido, false caso contrario
	 */
	public boolean indicaCodigo(String cod) {
		if (this.cod.equals(cod)) {
			MigrantMatcher.catalogoDeAjudas.adicionarAjuda(ajudaCorrente);
			return true;
		}
		return false;
	}
}
