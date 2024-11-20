package champollion;

public class ServicePrevu {
	// TODO : implémenter cette classe

    private final UE ueP;
    private final Enseignant ensgnt;
    private int  volumeCM;
    private int  volumeTD;
    private int  volumeTP;

    public ServicePrevu(UE ueP, Enseignant ensgnt, int volumeCM, int volumeTD, int volumeTP) {
        this.ueP = ueP;
        this.ensgnt = ensgnt;
        this.volumeCM = volumeCM;
        this.volumeTD = volumeTD;
        this.volumeTP = volumeTP;
    }
    public int getVolumeCM() {return volumeCM;}
    public int getVolumeTD() {return volumeTD;}
    public int getVolumeTP() {return volumeTP;}
    public UE getUEP() {return ueP;}
}
