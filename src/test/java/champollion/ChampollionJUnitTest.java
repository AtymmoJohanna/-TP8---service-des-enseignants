package champollion;

import org.junit.jupiter.api.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;
	Intervention inter1, inter2, inter3;
	Salle s1;
		
	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");
		s1 = new Salle("B102", 20);
		inter1 = new Intervention(new Date(), 2, false,  8, s1, uml, TypeIntervention.TD, untel);
		inter2 = new Intervention(new Date(), 50, false,  8, s1, uml, TypeIntervention.TD, untel);
		inter3 = new Intervention(new Date(), 50, false,  8, s1, java, TypeIntervention.TD, untel);
	}
	

	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
                        "Un nouvel enseignant doit avoir 0 heures prévues");
	}
	
	@Test
	public void testAjouteHeures() {
                // 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		assertEquals(10, untel.heuresPrevuesPourUE(uml),
                        "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

		// 20h TD pour UML
        untel.ajouteEnseignement(uml, 0, 20, 0);
                
		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                         "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");		
		
	}

	@Test
	public void testHeuresPrevues() {
		// 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		untel.ajouteEnseignement(uml, 0, 20, 0);

		assertEquals(10 + 20, untel.heuresPrevues(),
				"L'enseignant doit avoir 30 heures prévues au total");

	}


	@Test
	public void testEnSousServiceQuandService() {
		// 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		untel.ajouteEnseignement(uml, 0, 20, 0);

		assertEquals(true, untel.enSousService(),
				"L'enseignant doit etre en sous service");

	}



	@Test
	public void testResteAplanifier() {
		// 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		untel.ajouteEnseignement(uml, 0, 20, 0);

		untel.ajouteIntervention(inter1);

		assertEquals(8, untel.resteAplanifier(uml, TypeIntervention.TD),
				"Le reste d'heure à planifier ne correspond pas");

	}




	@Test
	public void testAjouteInterventionSiPlusHeure() throws Exception {
		// 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		untel.ajouteEnseignement(uml, 0, 20, 0);

		try {
			untel.resteAplanifier(uml, TypeIntervention.TP); // Que doit-il se passer ?
			// Si on arrive ici, il n'y a pas eu d'exception, échec

		} catch (IllegalStateException e) {
			fail("On ne peut pas ajouter cette intervention");
			// Si on arrive ici, il y a eu une exception, c'est ce qui est attendu
		}

	}

}
