package hu.domparse.thdwdr;

public class Working {
    private int doctorID;
    private int departmentID;
    private boolean leads;

    // Konstruktor
    public Working() {
    }

    // Getterek és setterek
    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public boolean isLeads() {
        return leads;
    }

    public void setLeads(boolean leads) {
        this.leads = leads;
    }

    // toString metódus a könnyebb kiírás érdekében
    @Override
    public String toString() {
        return "Working{" +
                "doctorID=" + doctorID +
                ", departmentID=" + departmentID +
                ", leads=" + leads +
                '}';
    }
}
