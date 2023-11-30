package hu.domparse.thdwdr;

import java.time.LocalDate;

public class Treats {
    private int doctorID;
    private int patientID;
    private LocalDate fromWhen;
    private String howLong;

    // Konstruktor
    public Treats() {
    }

    // Getterek és setterek
    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
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

    public String getHowLong() {
        return howLong;
    }

    public void setHowLong(String howLong) {
        this.howLong = howLong;
    }

    // toString metódus a könnyebb kiírás érdekében
    @Override
    public String toString() {
        return "Treats{" +
                "doctorID=" + doctorID +
                ", patientID=" + patientID +
                ", fromWhen=" + fromWhen +
                ", howLong='" + howLong + '\'' +
                '}';
    }
}
