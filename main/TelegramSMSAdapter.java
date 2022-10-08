package main;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Esta classe serve como adaptador entre a classe TelegramSMSSender fornecida e
 * o MigrantMatcher
 * 
 * @author Joao Matos 56292
 * @author Joao Santos 57103
 *
 */
public class TelegramSMSAdapter implements SMSSender {

	/**
	 * Objeto da classe responsavel pelo servico de SMS
	 */
	private Object sms;

	/**
	 * Objeto que representa o metodo que define o texto do sms
	 */
	private Method setText;

	/**
	 * Objeto que representa o metodo que define o numero correspondente ao
	 * destinatario do sms
	 */
	private Method setNumber;

	/**
	 * Objeto que representa o metodo relevante de SMS
	 */
	private Method smsSend;

	/**
	 * Instancia unica da classe
	 */
	private static TelegramSMSAdapter instance;

	/**
	 * Inicializa o adaptador, carregando a classe TelegramSMSSender
	 */
	private TelegramSMSAdapter() {
		try {
			String pathToJar = "smsproviders.jar";
			JarFile jarFile = new JarFile(pathToJar);
			Enumeration<JarEntry> e = jarFile.entries();

			URL[] urls = { new URL("jar:file:" + pathToJar + "!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);

			while (sms == null) {
				JarEntry je = e.nextElement();
				if (je.getName().endsWith("TelegramSMSSender.class")) {
					// -6 devido ao .class
					String className = je.getName().substring(0, je.getName().length() - 6);
					className = className.replace('/', '.');
					Class<?> c = cl.loadClass(className);
					sms = c.getDeclaredConstructor().newInstance();
					setText = c.getDeclaredMethod("setText", String.class);
					setNumber = c.getDeclaredMethod("setNumber", String.class);
					smsSend = c.getDeclaredMethod("send");
				}
			}
		} catch (Exception e) {
			System.out.println("Nao foi possivel inicalizar o servico de SMS.");
			System.exit(0);
		}
	}

	/**
	 * Getter publico da classe
	 * 
	 * @return - uma instancia da classe TelegramSMSAdapter
	 */
	public static TelegramSMSAdapter getTelegramSMSAdapter() {
		if (instance == null)
			instance = new TelegramSMSAdapter();
		return instance;
	}

	@Override
	public void envia(String destino, String mensagem) {
		try {
			setNumber.invoke(sms, destino);
			setText.invoke(sms, mensagem);
			smsSend.invoke(sms);
		} catch (Exception e) {
			System.out.println("Falha no envio do SMS");
		}
	}

}
