package regiao;

/**
 * Esta classe representa uma região a ser usada no MigrantMatcher
 * 
 * @author Joao Matos 56292
 * @author Joao Santos 57103
 *
 */
public class Regiao {

	/**
	 * O nome associado a regiao
	 */
	private String nome;

	/**
	 * Construtor da classe Regiao, associa a string dada ao objeto criado
	 * 
	 * @param nome - o nome a atribuir
	 */
	public Regiao(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o nome associado a regiao
	 * 
	 * @return - o nome associado a regiao
	 */
	public String getNome() {
		return nome;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (this == other)
			return true;
		if (other instanceof Regiao) {
			Regiao r = (Regiao) other;
			return this.nome.equals(r.nome);
		}
		return false;
	}
}
