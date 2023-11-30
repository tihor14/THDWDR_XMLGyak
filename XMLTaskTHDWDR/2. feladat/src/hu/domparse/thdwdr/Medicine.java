package hu.domparse.thdwdr;

import java.util.List;

public class Medicine {
    private String name;
    private String description;

    // Konstruktor
    public Medicine() {
    }

    // Getterek �s setterek
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString met�dus a k�nnyebb ki�r�s �rdek�ben
    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
