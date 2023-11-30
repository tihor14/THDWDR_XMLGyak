package hu.domparse.thdwdr;

public class Effective {
    private int diseaseID;
    private String medicineName;

    // Konstruktor
    public Effective() {
    }

    // Getterek és setterek
    public int getDiseaseID() {
        return diseaseID;
    }

    public void setDiseaseID(int diseaseID) {
        this.diseaseID = diseaseID;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    // toString metódus a könnyebb kiírás érdekében
    @Override
    public String toString() {
        return "Effective{" +
                "diseaseID=" + diseaseID +
                ", medicineName='" + medicineName + '\'' +
                '}';
    }
}
