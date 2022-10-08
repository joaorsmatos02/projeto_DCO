package main;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Esta classe serve como adaptador entre a classe PidgeonSMSSender fornecida e
 * o MigrantMatcher
 * 
 * @author Joao Matos 56292
 * @author Joao Santos 57103
 *
 */
public class PidgeonSMSAdapter implements SMSSender {

	/**
	 * Objeto da classe responsavel pelo serviço de SMS
	 */
	private Object sms;

	/**
	 * Objeto que representa o metodo relevante de SMS
	 */
	private Method smsSender;

	/**
	 * Instancia unica da classe
	 */
	private static PidgeonSMSAdapter instance;

	private PidgeonSMSAdapter() {
		// inicializar SmsSender
		try {
			String pathToJar = "smsproviders.jar";
			JarFile jarFile = new JarFile(pathToJar);
			Enumeration<JarEntry> e = jarFile.entries();

			URL[] urls = { new URL("jar:file:" + pathToJar + "!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);

			while (sms == null) {
				JarEntry je = e.nextElement();
				if (je.getName().endsWith("PidgeonSMSSender.class")) {
					// -6 devido ao .class
					String className = je.getName().substring(0, je.getName().length() - 6);
					className = className.replace('/', '.');
					Class<?> c = cl.loadClass(className);
					sms = c.getDeclaredConstructor().newInstance();
					smsSender = c.getDeclaredMethod("send", String.class, String.class);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Nao foi possivel inicalizar o servico de SMS.");
			System.exit(0);
		}
	}

	/**
	 * Getter publico da classe
	 * 
	 * @return - uma instancia da classe PidgeonSMSAdapter
	 */
	public static PidgeonSMSAdapter getPidgeonSMSAdapter() {
		if (instance == null)
			instance = new PidgeonSMSAdapter();
		return instance;
	}

	@Override
	public void envia(String destino, String mensagem) {
		try {
			smsSender.invoke(sms, destino, mensagem);
		} catch (Exception e) {
			System.out.println("Falha no envio de SMS");
		}
	}

}
