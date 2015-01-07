package eu.telecom_bretagne.CESI.demo;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import eu.telecom_bretagne.CESI.data.dto.AgentDepartement;
import eu.telecom_bretagne.CESI.service.IGestionAgentDepartement;

public class AppelGestionAgentDepartement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test_lire_modifier();


	}

	public static IGestionAgentDepartement getGestionAgentDepartement() {
		InitialContext ctx;
		IGestionAgentDepartement gestionAgentDepartement = null;
		try {
			ctx = new InitialContext();
			gestionAgentDepartement = (IGestionAgentDepartement) ctx
					.lookup(IGestionAgentDepartement.JNDI_NAME);
		} catch (NamingException e) {
			// Unable to retrieve the context or the service
			e.printStackTrace();
			System.exit(-1);
		}
		return gestionAgentDepartement;
	}

	private static void printAgentDepartement(AgentDepartement a) {
		System.out.println("AgentDepartement id: " + a.getAgent_id() + " nom: "
				+ a.getAgent_nom() + " departement:" + a.getDepartement_id()
				+ ":" + a.getDepartement_intitule());
	}

	public static void test_lire_modifier() {
		final int id = 3;
		AgentDepartement agtDept = getGestionAgentDepartement()
				.lireAgentDepartement(id);
		printAgentDepartement(agtDept);
		agtDept.setAgent_nom(agtDept.getAgent_nom() + "x");
		getGestionAgentDepartement().modifierAgentDepartement(agtDept);
		agtDept = getGestionAgentDepartement().lireAgentDepartement(id);
		printAgentDepartement(agtDept);
	}

}
