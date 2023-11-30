package hu.domparse.thdwdr;
import java.time.LocalDate;

public class Suffering {
    private int diseaseID;
    private int patientID;
    private LocalDate fromWhen;

    // Konstruktor
    public Suffering() {
    }

    // Getterek �s setterek
    public int getDiseaseID() {
        return diseaseID;
    }

    public void setDiseaseID(int diseaseID) {
        this.diseaseID = diseaseID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public LocalDate getFromWhen() {
        return fromWhen;
    }

    public void setFromWhen(LocalDate fromWhen) {
        this.fromWhen = fromWhen;
    }

    // toString met�dus a k�nnyebb ki�r�s �rdek�ben
    @Override
    public String toString() {
        return "Suffering{" +
                "diseaseID=" + diseaseID +
                ", patientID=" + patientID +
                ", fromWhen=" + fromWhen +
                '}';
    }
}
