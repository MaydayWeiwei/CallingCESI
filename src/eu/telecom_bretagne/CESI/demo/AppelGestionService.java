package eu.telecom_bretagne.CESI.demo;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import eu.telecom_bretagne.CESI.data.model.Service;
import eu.telecom_bretagne.CESI.service.IGestionService;

public class AppelGestionService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		test_listeServices();


	}

	public static IGestionService getGestionService() {
		InitialContext ctx;
		IGestionService gestionService = null;
		try {
			ctx = new InitialContext();
			gestionService = (IGestionService) ctx.lookup(IGestionService.JNDI_NAME);
		} catch (NamingException e) {
			// Unable to retrieve the context or the service
			e.printStackTrace();
			System.exit(-1);
		}
		return gestionService;
	}

	public static void test_listeServices() {
		for (Service service : getGestionService().listeServices()) {
			System.out.println("Service id:" + service.getId() + " nom:"
					+ service.getNom());
		}
	}

	public static void test_creerService() {
		getGestionService().creerService("service1", 1);
	}


}
