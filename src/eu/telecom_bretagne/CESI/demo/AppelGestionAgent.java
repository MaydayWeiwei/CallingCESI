package eu.telecom_bretagne.CESI.demo;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import eu.telecom_bretagne.CESI.data.model.Agent;
import eu.telecom_bretagne.CESI.service.IGestionAgent;

public class AppelGestionAgent {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		test_supprimerAgent(1);
		test_listeAgents();


	}

	public static IGestionAgent getGestionAgent() {
		InitialContext ctx;
		IGestionAgent gestionAgent = null;
		try {
			ctx = new InitialContext();
			gestionAgent = (IGestionAgent) ctx.lookup(IGestionAgent.JNDI_NAME);
		} catch (NamingException e) {
			// Unable to retrieve the context or the service
			e.printStackTrace();
			System.exit(-1);
		}
		return gestionAgent;
	}

	public static void test_listeAgents() {
		for (Agent agent : getGestionAgent().listeAgents()) {
			System.out.println("Agent id:" + agent.getId() + " nom:"
					+ agent.getNom());
		}
	}

	public static void test_lireAgent() {
		Agent agent = getGestionAgent().lireAgent(2);
		System.out.println("Agent id=2 nom:" + agent.getNom());
	}

	public static void test_modifierAgent() {
		getGestionAgent().modifierAgent(2, "nouveau nom agent 2");
	}

	public static void test_creerAgent() {
		getGestionAgent().creerAgent("Nouvel agent", 1);
	}

	public static void test_supprimerAgent(int id) {
		getGestionAgent().supprimerAgent(id);
	}

}
