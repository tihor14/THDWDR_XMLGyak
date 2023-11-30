package hu.domparse.thdwdr;

public class Disease {
    private String name;
    private String symptom;
    private String affectedOrgans;

    // Konstruktor
    public Disease() {
    }

    // Getterek �s setterek
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

    // toString met�dus a k�nnyebb ki�r�s �rdek�ben
    @Override
    public String toString() {
        return "Disease{" +
                "name='" + name + '\'' +
                ", symptom='" + symptom + '\'' +
                ", affectedOrgans='" + affectedOrgans + '\'' +
                '}';
    }
}

