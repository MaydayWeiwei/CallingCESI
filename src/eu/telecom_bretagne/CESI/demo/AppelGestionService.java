package eu.telecom_bretagne.CESI.demo;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import eu.telecom_bretagne.CESI.data.model.Employe;
import eu.telecom_bretagne.CESI.service.IGestionEmploye;

public class AppelGestionService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		test_supprimerEmploye(1);
		test_listeEmployes();


	}

	public static IGestionEmploye getGestionEmploye() {
		InitialContext ctx;
		IGestionEmploye gestionEmploye = null;
		try {
			ctx = new InitialContext();
			gestionEmploye = (IGestionEmploye) ctx.lookup(IGestionEmploye.JNDI_NAME);
		} catch (NamingException e) {
			// Unable to retrieve the context or the service
			e.printStackTrace();
			System.exit(-1);
		}
		return gestionEmploye;
	}

	public static void test_listeEmployes() {
		for (Employe employe : getGestionEmploye().listeEmployes()) {
			System.out.println("Agent id:" + employe.getId() + " nom:"
					+ employe.getNom());
		}
	}

	public static void test_lireEmploye() {
		Employe employe = getGestionEmploye().lireEmploye(2);
		System.out.println("Agent id=2 nom:" + employe.getNom());
	}

	public static void test_modifierEmploye() {
		getGestionEmploye().modifierEmploye(2, "nouveau nom agent 2");
	}

	public static void test_creerEmploye() {
		getGestionEmploye().creerEmploye("Nouvel agent", 1);
	}

	public static void test_supprimerEmploye(int id) {
		getGestionEmploye().supprimerEmploye(id);
	}

}
