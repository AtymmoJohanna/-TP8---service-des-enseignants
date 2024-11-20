package champollion;

import java.util.Date;

public class Intervention {
    private Date debut;
    private int duree;
    private boolean annulee;
    private int heuredebut;
    private TypeIntervention typeIntervention;
    private Salle salle;
    private UE ue;
    private Enseignant enseignant;

    public Intervention( Date debut, int duree, boolean annulee, int heuredebut, Salle salle, UE ue, TypeIntervention typeIntervention, Enseignant enseignant ) {
        this.debut = debut;
        this.duree = duree;
        this.annulee = annulee;
        this.heuredebut = heuredebut;
        this.salle = salle;
        this.ue = ue;
        this.typeIntervention = typeIntervention;
        this.enseignant = enseignant;
    }

    public UE getUe() {return ue;}
    public int getDuree() {return duree;}
    public TypeIntervention getTypeIntervention() {return typeIntervention;}
    public int getHeuredebut() {return heuredebut;}
    public Date getDebut() {return debut;}
}
