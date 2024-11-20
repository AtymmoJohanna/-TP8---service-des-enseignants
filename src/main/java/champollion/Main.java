package champollion;

import java.util.Date;

/**
 * Un exemple d'utilisation des classes
 */
public class Main {
    public static void main(String[] args) {
        Enseignant bastide = new Enseignant("Rémi Bastide", "Remi.Bastide@irit.fr");
        Enseignant lamine = new Enseignant("Elyes Lamine", "Elyes.Lamine@univ-jfc.fr");

        UE uml = new UE("Conception par objets avec UML");
        UE bd = new UE("Bases de données");
        UE web = new UE("Technologies web");

        bastide.ajouteEnseignement(uml, 12, 20, 20);
        bastide.ajouteEnseignement(web, 8, 20, 30);
        lamine.ajouteEnseignement(bd, 10, 20, 15);
        lamine.ajouteEnseignement(web, 15, 15, 25);
       System.out.printf("Mr. %s a un total de %d heures prévues%n", bastide.getNom(), bastide.heuresPrevues());
       System.out.printf("Mr. %s a un total de %d heures prévues%n", lamine.getNom(), lamine.heuresPrevues());
       System.out.printf("Mr. %s a un total de %d heures prévues dans l'UE %s %n",
                bastide.getNom(),
                bastide.heuresPrevuesPourUE(uml),
                uml.getIntitule());

       Salle s1 = new Salle("B101", 20);
        Salle s2 = new Salle("B106", 30);

       Intervention i1 = new Intervention(new Date(), 2, false,  8, s1, uml, TypeIntervention.CM, bastide );
        Intervention i2 = new Intervention(new Date(), 5, false, 10, s2, uml, TypeIntervention.CM, bastide );

        bastide.ajouteIntervention(i1);
        bastide.ajouteIntervention(i2);

        System.out.printf("Mr. %s a encore un total de %d heures prévues dans l'UE %s pour le type %s %n", bastide.getNom(), bastide.resteAplanifier(uml, TypeIntervention.CM), uml.getIntitule(), TypeIntervention.CM);
        System.out.printf("Mr. %s en sous service: %s", bastide.getNom(), bastide.enSousService());
    }

}
