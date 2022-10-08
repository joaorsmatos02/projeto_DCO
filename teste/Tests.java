package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import ajuda.Ajuda;
import main.MigrantMatcher;
import main.ProcurarAjudaHandler;
import main.RegistarAjudaHandler;
import main.RegistarAlojamentoStrategy;
import main.RegistarItemStrategy;
import regiao.Regiao;

/**
 * Esta classe contem os testes a serem feitos ao MigrantMatcher
 * 
 * @author Joao Matos 56292
 * @author Joao Santos 57103
 *
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Tests {

	/**
	 * Handler do primeiro caso de uso
	 */
	static RegistarAjudaHandler registarAjudaHandler;

	/**
	 * Handler do segundo caso de uso
	 */
	static ProcurarAjudaHandler procurarAjudaHandler;

	/**
	 * Estrategia de adicao de alojamentos
	 */
	static RegistarAlojamentoStrategy ra;

	/**
	 * Estrategia de adicao de items
	 */
	static RegistarItemStrategy ri;

	/**
	 * Metodo que inicializa o programa de forma a permitir a execucao de testes
	 */
	@BeforeAll
	static void start() {
		MigrantMatcher.inicializar(); // o MigrantMatcher e inicializado para permitir o acesso aos seus catalogos
		registarAjudaHandler = new RegistarAjudaHandler();
		procurarAjudaHandler = ProcurarAjudaHandler.getProcurarAjudaHandler();
	}

	/**
	 * Testa a implementacao do Singleton em ProcurarAjudaHandler
	 */
	@Test
	@Order(1)
	void testGetProcurarAjudaHandler() {
		assertNotNull(procurarAjudaHandler);
		assertEquals(ProcurarAjudaHandler.getProcurarAjudaHandler(), procurarAjudaHandler);
	}

	/**
	 * Testa o login do voluntario
	 */
	@Test
	@Order(2)
	void testIndicaContacto() {
		assertFalse(registarAjudaHandler.indicaContacto("drgdxhh"));
		assertFalse(registarAjudaHandler.indicaContacto("113456789"));
		assertTrue(registarAjudaHandler.indicaContacto("987654321"));
		assertTrue(registarAjudaHandler.indicaContacto("123456789"));
	}

	/**
	 * Testa se ao ser indicado o numero de pessoas o metodo retorna corretamente a
	 * lista de regioes
	 */
	@Test
	@Order(3)
	void testIndicaNumPessoas() {
		ra = (RegistarAlojamentoStrategy) registarAjudaHandler.indicaAjuda(1);
		List<Regiao> lr = ra.indicaNumPessoas(2);
		assertNotNull(lr);
	}

	/**
	 * Testa se a adicao de um alojamento e efetuada corretamente
	 */
	@Test
	@Order(4)
	void testAdicionarAlojamento() {
		ra.indicaRegiao(new Regiao("Acores"));
		boolean res = ra.indicaCodigo(ra.getCod());
		assertTrue(res);
	}

	/**
	 * Testa o login de um utilizador diferente
	 */
	@Test
	@Order(5)
	void testIndicaContacto2() {
		assertTrue(registarAjudaHandler.indicaContacto("987654321"));
	}

	/**
	 * Testa a adicao de um item por parte do novo utilizador
	 */
	@Test
	@Order(6)
	void testAdicionarItem() {
		ri = (RegistarItemStrategy) registarAjudaHandler.indicaAjuda(2);
		ri.indicaDescricaoItem("a");
		boolean res = ri.indicaCodigo(ri.getCod());
		assertTrue(res);
	}

	/**
	 * Testa o retorno de indicaCodigo ao ser fornecido um codigo incorreto
	 */
	@Test
	@Order(7)
	void testCodErrado() {
		ri = (RegistarItemStrategy) registarAjudaHandler.indicaAjuda(2);
		ri.indicaDescricaoItem("a");
		boolean res = ri.indicaCodigo("");
		assertFalse(res);
	}

	/**
	 * Testa a adicao de um migrante coletivo e as respetivas ajudas compativeis
	 */
	@Test
	@Order(8)
	void testAdicionaMigrante1() {
		procurarAjudaHandler.iniciaRegisto();
		procurarAjudaHandler.indicaNomeContacto("Joao", "87898789");
		procurarAjudaHandler.indicaNumPessoas(3);
		procurarAjudaHandler.indicaMembro("Maria");
		procurarAjudaHandler.indicaMembro("Ze");
		procurarAjudaHandler.confirmaMigrante();
		List<Ajuda> la = procurarAjudaHandler.indicaRegiao(new Regiao("Acores"));
		assertEquals(la.size(), 1);
		procurarAjudaHandler.confirmaAjuda();
	}

	/**
	 * Testa a adicao de um migrante coletivo e as respetivas ajudas compativeis
	 * numa regiao distinta e a requisicao de uma ajuda
	 */
	@Test
	@Order(9)
	void testAdicionaMigrante2() {
		procurarAjudaHandler.iniciaRegisto();
		procurarAjudaHandler.indicaNomeContacto("Joao", "345432345");
		procurarAjudaHandler.indicaNumPessoas(3);
		procurarAjudaHandler.indicaMembro("Maria");
		procurarAjudaHandler.indicaMembro("Ze");
		procurarAjudaHandler.confirmaMigrante();
		List<Ajuda> la = procurarAjudaHandler.indicaRegiao(new Regiao("Minho"));
		assertEquals(la.size(), 1);
		procurarAjudaHandler.indicaAjuda(la.get(0));
		procurarAjudaHandler.confirmaAjuda();
	}

	/**
	 * Testa a adicao de um migrante individual e as respetivas ajudas compativeis e
	 * a requisicao de uma ajuda
	 */
	@Test
	@Order(10)
	void testAdicionaMigrante3() {
		procurarAjudaHandler.iniciaRegisto();
		procurarAjudaHandler.indicaNomeContacto("Joao", "8765456764");
		procurarAjudaHandler.indicaNumPessoas(1);
		procurarAjudaHandler.confirmaMigrante();
		List<Ajuda> la = procurarAjudaHandler.indicaRegiao(new Regiao("Acores"));
		assertEquals(la.size(), 1);
		procurarAjudaHandler.indicaAjuda(la.get(0));
		procurarAjudaHandler.confirmaAjuda();
	}

	/**
	 * Testa a adicao de um migrante individual e as respetivas ajudas compativeis
	 * numa regiao distinta
	 */
	@Test
	@Order(11)
	void testAdicionaMigrante4() {
		procurarAjudaHandler.iniciaRegisto();
		procurarAjudaHandler.indicaNomeContacto("Joao", "76545667");
		procurarAjudaHandler.indicaNumPessoas(1);
		procurarAjudaHandler.confirmaMigrante();
		List<Ajuda> la = procurarAjudaHandler.indicaRegiao(new Regiao("Alentejo"));
		assertEquals(la.size(), 0);
		procurarAjudaHandler.confirmaAjuda();
	}
}
