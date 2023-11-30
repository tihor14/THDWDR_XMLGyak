package hu.domparse.thdwdr;

public class Department {
    private String id;
    private String name;
    private String location;

    // Konstruktor
    public Department() {
    }

    // Getterek �s setterek
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // toString met�dus a k�nnyebb ki�r�s �rdek�ben
    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}

