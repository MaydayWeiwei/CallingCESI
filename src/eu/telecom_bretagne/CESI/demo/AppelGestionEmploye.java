package eu.telecom_bretagne.CESI.demo;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import eu.telecom_bretagne.CESI.data.model.Employe;
import eu.telecom_bretagne.CESI.service.IGestionEmploye;

public class AppelGestionEmploye {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

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
			System.out.println("Employé id:" + employe.getId() + " nom:"
					+ employe.getNom()  + " prénom:" + employe.getPrenom());
		}
	}

	public static void test_creerEmploye() {
		getGestionEmploye().creerEmploye("nom1", "prenom1",  1);
	}

}
