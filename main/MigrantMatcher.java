package main;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import ajuda.Ajuda;
import catalogos.CatalogoDeAjudas;
import catalogos.CatalogoDeRegioes;
import catalogos.CatalogoDeUtilizadores;
import regiao.Regiao;

/**
 * Esta classe representa a execucao principal do MigrantMatcher
 * 
 * @author Joao Matos 56292
 * @author Joao Santos 57103
 *
 */
public class MigrantMatcher {

	/**
	 * Handler do primeiro caso de uso
	 */
	static RegistarAjudaHandler registarAjudaHandler;

	/**
	 * Handler do Segundo caso de uso
	 */
	static ProcurarAjudaHandler procurarAjudaHandler;

	/**
	 * Catalogo das regioes do sistema
	 */
	static CatalogoDeRegioes catalogoDeRegioes;

	/**
	 * Catalogo das ajudas do sistema
	 */
	static CatalogoDeAjudas catalogoDeAjudas;

	/**
	 * Catalogo dos utilizadores do sistema
	 */
	static CatalogoDeUtilizadores catalogoDeUtilizadores;

	/**
	 * Scanner usado ao longo da execucao
	 */
	static Scanner sc;

	/**
	 * Ordenacao especificada no ficheiro de configuracoes
	 */
	static String ordenacao;

	/**
	 * Metodo principal do MigrantMatcher
	 * 
	 * @param args - nao usado
	 */
	public static void main(String[] args) {
		inicializar();
		System.out.println("Bem vindo ao MigrantMatcher!");
		sc = new Scanner(System.in);
		int escolhido = 0;
		do {
			System.out.println(
					"Se pretende oferecer ajuda, insira '1'. Se procura ajuda, insira '2'. Se pretender sair, insira '3'.");
			escolhido = sc.nextInt();
			sc.nextLine();
			switch (escolhido) {
			case 1:
				casoDeUso1();
				break;
			case 2:
				casoDeUso2();
				break;
			case 3:
				System.out.println("Encerrando MigrantMatcher...");
				break;
			default:
				System.out.println("Valor inserido invalido!");
			}
		} while (escolhido != 3);
		sc.close();
	}

	/**
	 * Inicializa os atributos da classe MigrantMatcher. Este metodo foi colocado
	 * publico de forma a permitir a realizacao de testes junit
	 * 
	 * Dado que nao faz parte do projeto a adicao de novos voluntarios, os contactos
	 * default sao 123456789 e 987654321
	 */
	public static void inicializar() {
		catalogoDeRegioes = CatalogoDeRegioes.getCatalogoDeRegioes();
		catalogoDeRegioes.adicionaRegiao("Acores");
		catalogoDeRegioes.adicionaRegiao("Madeira");
		catalogoDeRegioes.adicionaRegiao("Algarve");
		catalogoDeRegioes.adicionaRegiao("Alentejo");
		catalogoDeRegioes.adicionaRegiao("Lisboa/Setubal");
		catalogoDeRegioes.adicionaRegiao("Estremadura/Ribatejo");
		catalogoDeRegioes.adicionaRegiao("Beira");
		catalogoDeRegioes.adicionaRegiao("Tras-os-Montes");
		catalogoDeRegioes.adicionaRegiao("Minho");

		catalogoDeAjudas = CatalogoDeAjudas.getCatalogoDeAjudas();

		catalogoDeUtilizadores = CatalogoDeUtilizadores.getCatalogoDeUtilizadores();
		catalogoDeUtilizadores.adicionaVoluntario("987654321");
		catalogoDeUtilizadores.adicionaVoluntario("123456789");

		registarAjudaHandler = new RegistarAjudaHandler();
		procurarAjudaHandler = ProcurarAjudaHandler.getProcurarAjudaHandler();

		Properties prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("config.properties");
			prop.load(ip);
			ordenacao = prop.getProperty("ordenacao");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metodo que assegura a correta ordem de invocacao de metodos durante o
	 * primeiro caso de uso
	 */
	private static void casoDeUso1() {
		boolean b = false;
		do {
			System.out.println("Insira o seu contacto:");
			String contacto = sc.nextLine();
			if (!registarAjudaHandler.indicaContacto(contacto))
				System.out.println("Contacto nao encontrado, veja o README para contactos pre-definidos.");
			else
				b = true;
		} while (!b);
		int escolhido = 0;
		do {
			System.out.println(
					"Se pretender oferecer um alojamento, insira '1'. Se pretender oferecer um item, insira '2'.");
			escolhido = sc.nextInt();
			sc.nextLine();
			switch (escolhido) {
			case 1:
				registarAjudaHandler = altAlojamento();
				break;
			case 2:
				registarAjudaHandler = altItem();
				break;
			default:
				System.out.println("Valor inserido invalido!");
			}
		} while (escolhido != 1 && escolhido != 2);
		System.out.println("Foi enviado um SMS para o seu contacto com um codigo de confirmacao.");
		System.out.println("Insira o codigo que lhe foi enviado:");
		if (registarAjudaHandler.indicaCodigo(sc.nextLine()))
			System.out.println("Operacao confirmada! Obrigado pela sua colaboracao!");
		else
			System.out.println("Codigo invalido! A operacao foi cancelada!");
	}

	/**
	 * Metodo que assegura a correta concepcao de um alojamento
	 */
	private static RegistarAlojamentoStrategy altAlojamento() {
		RegistarAlojamentoStrategy ra = (RegistarAlojamentoStrategy) registarAjudaHandler.indicaAjuda(1);
		int nmr = 0;
		do {
			System.out.println("Insira a capacidade do seu alojamento:");
			nmr = sc.nextInt();
			sc.nextLine();
			if (nmr < 1)
				System.out.println("Capacidade invalida!");
		} while (nmr < 1);
		List<Regiao> lr = ra.indicaNumPessoas(nmr);
		System.out.println("As regioes disponiveis sao:");
		for (Regiao r : lr)
			System.out.println(r.getNome());
		String regiao = "";
		do {
			System.out.println("Indique a regiao que pretende escolher:");
			regiao = sc.nextLine();
			Regiao r = new Regiao(regiao);
			if (!lr.contains(r)) {
				regiao = "";
				System.out.println("Regiao invalida!");
			}
		} while (regiao.equals(""));
		ra.indicaRegiao(new Regiao(regiao));
		return ra;
	}

	/**
	 * Metodo que assegura a correta concepcao de um item
	 */
	private static RegistarItemStrategy altItem() {
		RegistarItemStrategy ri = (RegistarItemStrategy) registarAjudaHandler.indicaAjuda(2);
		System.out.println("Insira uma descricao para o item:");
		ri.indicaDescricaoItem(sc.nextLine());
		return ri;
	}

	/**
	 * Metodo que assegura a correta ordem de invocacao de metodos durante o Segundo
	 * caso de uso
	 */
	private static void casoDeUso2() {
		int escolhido = 0;
		do {
			procurarAjudaHandler.iniciaRegisto();
			System.out.println(
					"Se procura ajuda a nivel individual, insira '1'. Se procura ajuda a nivel coletivo, insira '2'.");
			escolhido = sc.nextInt();
			sc.nextLine();
			switch (escolhido) {
			case 1:
				altIndividual();
				break;
			case 2:
				altColetivo();
				break;
			default:
				System.out.println("Valor inserido invalido!");
			}
		} while (escolhido != 1 && escolhido != 2);

		List<Regiao> lr = procurarAjudaHandler.confirmaMigrante();
		System.out.println("As regioes disponiveis sao:");
		for (Regiao r : lr)
			System.out.println(r.getNome());
		String regiao = "";
		do {
			System.out.println("Indique a regiao que pretende escolher:");
			regiao = sc.nextLine();
			Regiao r = new Regiao(regiao);
			if (!lr.contains(r)) {
				regiao = "";
				System.out.println("Regiao invalida!");
			}
		} while (regiao.equals(""));

		List<Ajuda> lac = procurarAjudaHandler.indicaRegiao(new Regiao(regiao));

		if (lac.isEmpty())
			extensao();
		else {
			List<String> lacNome = new ArrayList<>();
			System.out.println("Lista de ajuda compativeis:");
			if (ordenacao.equals("alojamentoDepoisItem")) {
				for (Ajuda a : lac) {
					if (a instanceof ajuda.Alojamento) {
						System.out.println(a.toString());
					}
					lacNome.add(String.valueOf(a.hashCode()));
				}

				for (Ajuda a : lac) {
					if (a instanceof ajuda.Item)
						System.out.println(a.toString());
				}
			} else {
				for (Ajuda a : lac) {
					System.out.println(a.toString());
					lacNome.add(String.valueOf(a.hashCode()));
				}
			}

			String ajudaEscolhida = "";
			boolean stop = false;
			while (!stop) {
				do {
					System.out.println(
							"Indique o codigo associado ah ajuda pretendida. Quando quiser sair insira 'sair'.");
					ajudaEscolhida = sc.nextLine();
					if (ajudaEscolhida.equals("sair"))
						stop = true;
					else if (!lacNome.contains(ajudaEscolhida)) {
						ajudaEscolhida = "";
						System.out.println("Ajuda invalida!");
					} else {
						procurarAjudaHandler.indicaAjuda(lac.get(lacNome.indexOf(ajudaEscolhida)));
					}
				} while (ajudaEscolhida.equals(""));
				if (!stop) {
					lac.remove(lacNome.indexOf(ajudaEscolhida));
					lacNome.remove(ajudaEscolhida);
					System.out.println("Lista de ajudas compativeis disponiveis:");
					for (Ajuda a : lac)
						System.out.println(a.toString());
					if (lac.size() == 0) {
						stop = true;
						System.out.println("Nao existem mais ajudas disponiveis na regiao escolhida.");
					}
				}
			}
			procurarAjudaHandler.confirmaAjuda();
		}
	}

	/**
	 * Metodo que assegura a correta concepcao de um migrante coletivo
	 */
	private static void altColetivo() {
		int nmrPessoasValido = 0;
		do {
			System.out.println("Indique o numero de pessoas do seu grupo (contando consigo):");
			nmrPessoasValido = sc.nextInt();
			sc.nextLine();
			if (nmrPessoasValido < 1)
				System.out.println(
						"Valor inserido invalido. Indique o numero de pessoas do seu grupo (contando consigo):");
		} while (nmrPessoasValido < 1);
		procurarAjudaHandler.indicaNumPessoas(nmrPessoasValido);

		System.out.println("Insira o seu nome:");
		String nome = sc.nextLine();
		System.out.println("Insira o seu contacto:");
		String contacto = sc.nextLine();
		procurarAjudaHandler.indicaNomeContacto(nome, contacto);

		System.out.println("Insira os nomes dos " + (nmrPessoasValido - 1) + " membros associados.");
		for (int i = 0; i < nmrPessoasValido - 1; i++) {
			procurarAjudaHandler.indicaMembro(sc.nextLine());
		}
	}

	/**
	 * Metodo que assegura a correta concepcao de um migrante individual
	 */
	private static void altIndividual() {
		System.out.println("Insira o seu nome:");
		String nome = sc.nextLine();
		System.out.println("Insira o seu contacto:");
		String contacto = sc.nextLine();
		procurarAjudaHandler.indicaNomeContacto(nome, contacto);
	}

	/**
	 * Metodo que assegura a correta execucao da extensao do caso de uso 2
	 */
	private static void extensao() {
		System.out.println("Nao existem ajudas na regiao escolhida.");
		int notificacao;
		do {
			System.out.println(
					"Se pretender receber uma notificacao quando ficarem disponiveis ajudas na sua regiao, insira '1', caso contrario, insira '2'.");
			notificacao = sc.nextInt();
			if (notificacao == 1) {
				procurarAjudaHandler.ativaNotificacao(true);
				System.out.println("Sera notificado quando existirem ajudas na sua regiao.");
			} else if (notificacao != 1 && notificacao != 2) {
				System.out.println("O valor inserido eh invalido.");
			}
		} while (notificacao != 1 && notificacao != 2);
	}
}
