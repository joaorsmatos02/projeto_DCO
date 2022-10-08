package main;

/**
 * Esta interface deve ser utilizada de forma a criar adaptadores entre as
 * classes de sms e o sistema
 * 
 * @author Joao Matos 56292
 * @author Joao Santos 57103
 *
 */
public interface SMSSender {

	/**
	 * Este metodo e responsavel por enviar uma mensagem atraves do servico de SMS
	 * escolhido
	 * 
	 * @param destino  - o contacto de destino
	 * @param mensagem - a mensagem a enviar
	 */
	public void envia(String destino, String mensagem);

}
