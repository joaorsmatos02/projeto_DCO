package utilizador;
/**
 * Esta classe representa um utilizador do MigrantMatcher
 * 
 * @author Jo�o Matos 56292
 * @author Jo�o Santos 57103
 *
 */
public class Utilizador {

	/**
	 * O contacto do utilizador
	 */
	protected String contacto;

	/**
	 * Construtor da classe utilizador
	 * 
	 * @param contacto - o contacto do utilizador
	 */
	public Utilizador(String contacto) {
		this.contacto = contacto;
	}

	/**
	 * Devolve o contacto associado ao utilizador
	 * 
	 * @return o contacto do utilizador
	 */
	public String getContacto() {
		return contacto;
	}

}
