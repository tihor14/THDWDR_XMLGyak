package hu.domparse.thdwdr;

public class Disease {
    private String name;
    private String symptom;
    private String affectedOrgans;

    // Konstruktor
    public Disease() {
    }

    // Getterek és setterek
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getAffectedOrgans() {
        return affectedOrgans;
    }

    public void setAffectedOrgans(String affectedOrgans) {
        this.affectedOrgans = affectedOrgans;
    }

    // toString metódus a könnyebb kiírás érdekében
    @Override
    public String toString() {
        return "Disease{" +
                "name='" + name + '\'' +
                ", symptom='" + symptom + '\'' +
                ", affectedOrgans='" + affectedOrgans + '\'' +
                '}';
    }
}

