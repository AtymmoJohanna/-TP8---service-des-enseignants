package champollion;


import java.util.LinkedList;

/**
 * Un enseignant est caractérisé par les informations suivantes : son nom, son adresse email, et son service prévu,
 * et son emploi du temps.
 */
public class Enseignant extends Personne {

    // TODO : rajouter les autres méthodes présentes dans le diagramme UML

    private final LinkedList<ServicePrevu> services = new LinkedList<>();
    private final LinkedList<Intervention> interventions = new LinkedList<>();

    public Enseignant(String nom, String email) {
        super(nom, email);
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        // TODO: Implémenter cette méthode
        double retour = 0;
        for (ServicePrevu service : services) {
            retour = retour + 1.5* service.getVolumeCM() + service.getVolumeTD() + 0.75* service.getVolumeTP();
        }
        return (int) Math.round(retour);
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        // TODO: Implémenter cette méthode
        double retour = 0;
        for (ServicePrevu service : services) {
            if (service.getUEP().equals(ue)){
                retour = retour + 1.5* service.getVolumeCM() + service.getVolumeTD() + 0.75* service.getVolumeTP();
            }
        }
        return (int) Math.round(retour);
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magistral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        // TODO: Implémenter cette méthode
        ServicePrevu sp = new ServicePrevu( ue, this, volumeCM, volumeTD, volumeTP);
        services.add(sp);
    }

    /**
     * Ajoute une intervention à cet enseignant
     *
     * @param inter la nouvelle intervention
     */
    public void ajouteIntervention(Intervention inter) {
        // TODO: Implémenter cette méthode
        ServicePrevu servP = null;
        int compt=0;
        for (ServicePrevu service : services) {
            if (service.getUEP() != inter.getUe()) {
                compt++;
            }
            else{
                servP = service;
                break;
            }
        }
        if (compt==services.size()){
            throw new UnsupportedOperationException("ce prof n'a pas de service prevu dans cette ue pour faire une intervention");
        }

        TypeIntervention typeInter = inter.getTypeIntervention();

        int durSer; //c'est le volume horaire total du service prevue pour l'ue de cette intervention et pour ce type d'untervention

        if (typeInter.equals(TypeIntervention.CM)){
            durSer = servP.getVolumeCM();
        } else if (typeInter.equals(TypeIntervention.TD)) {
            durSer = servP.getVolumeTD();
        }else {
            durSer = servP.getVolumeTP();
        }

        if (!interventions.isEmpty()){
            double retour=0;
            for (Intervention intervention : interventions) {
                // recuperons le nombre total d'heure deja fait pour cette ue de l'intervention et ce type d'intervention
                if (intervention.getUe().equals(inter.getUe()) && intervention.getTypeIntervention().equals(inter.getTypeIntervention())) {
                    retour = retour + intervention.getDuree();
                }
            }
            if (retour + inter.getDuree() > durSer){
                throw new UnsupportedOperationException("avec cette intervention ce prof va depasser son nombre d'heure pour ce type d'intervention dans cette ue");
            }
        }
        interventions.add(inter);
    }

    /**
     * donne la différence de volume horaire entre le service prévu et les interventions planifiées pour cet enseignant, avec l'UE et le type d’intervention fournis en paramètre.
     *
     * @param ue l'UE concernée
     * @param type le type d’intervention concerné
     */
    public int resteAplanifier(UE ue, TypeIntervention type) {
        // TODO: Implémenter cette méthode
        int retour=0 ;

        for (ServicePrevu service : services) {
            if (service.getUEP().equals(ue) ) {
                if (type.equals(TypeIntervention.CM)){
                    retour = service.getVolumeCM();
                } else if (type.equals(TypeIntervention.TD)) {
                    retour = service.getVolumeTD();
                }else {
                    retour = service.getVolumeTP();
                }
                break;
            }
        }
        for (Intervention intervention : interventions) {
            // recuperons le nombre total d'heure deja fait pour cette ue de l'intervention et ce type d'intervention
            if (intervention.getUe().equals(ue) && intervention.getTypeIntervention().equals(type)) {
                retour = retour - intervention.getDuree();
            }
        }

        return retour;
    }

    /**
     * dit si l'enseignant est en sous-service (si son nombre d’heures prévues est inférieur à 192)
     */
    public boolean enSousService() {
        // TODO: Implémenter cette méthode
        boolean retour = false;
        if (this.heuresPrevues()<192){
            retour = true;
        }
        return retour;
    }


}
