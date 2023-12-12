package hu.domparse.thdwdr;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class DOMQueryTHDWDR {
    public static void main(String[] args) {
    	try {
            File inputFile = new File("XMLTHDWDR.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // a) Orvosok neveinek lekérdezése
            NodeList doctorList = doc.getElementsByTagName("Doctor");
            System.out.println("Orvosok nevei:");
            for (int i = 0; i < doctorList.getLength(); i++) {
                Node node = doctorList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element doctor = (Element) node;
                    System.out.println(doctor.getElementsByTagName("Name").item(0).getTextContent());
                }
            }

            // b) 3 ID-jû patient emailcímének lekérdezése
            String patientIdToQuery = "3";
            NodeList patientList = doc.getElementsByTagName("Patient");
            for (int i = 0; i < patientList.getLength(); i++) {
                Node node = patientList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element patient = (Element) node;
                    if (patient.getElementsByTagName("ID").item(0).getTextContent().equals(patientIdToQuery)) {
                        System.out.println("3 ID-jû patient emailcíme: " +
                                patient.getElementsByTagName("Email").item(0).getTextContent());
                        break;
                    }
                }
            }

            // c) 2 ID-jû patient születési éve
            String patientIdToQueryBirthYear = "2";
            for (int i = 0; i < patientList.getLength(); i++) {
                Node node = patientList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element patient = (Element) node;
                    if (patient.getElementsByTagName("ID").item(0).getTextContent().equals(patientIdToQueryBirthYear)) {
                        System.out.println("2 ID-jû patient születési éve: " +
                                patient.getElementsByTagName("DateOfBirth").item(0).getTextContent());
                        break;
                    }
                }
            }

            // d) 1 ID-jû patient címadatai
            String patientIdToQueryAddress = "1";
            for (int i = 0; i < patientList.getLength(); i++) {
                Node node = patientList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element patient = (Element) node;
                    if (patient.getElementsByTagName("ID").item(0).getTextContent().equals(patientIdToQueryAddress)) {
                        Element address = (Element) patient.getElementsByTagName("Address").item(0);
                        System.out.println("1 ID-jû patient címadatai:");
                        System.out.println("PostalCode: " + address.getElementsByTagName("PostalCode").item(0).getTextContent());
                        System.out.println("City: " + address.getElementsByTagName("City").item(0).getTextContent());
                        System.out.println("Street: " + address.getElementsByTagName("Street").item(0).getTextContent());
                        System.out.println("HouseNumber: " + address.getElementsByTagName("HouseNumber").item(0).getTextContent());
                        break;
                    }
                }
            }

            // e) 103 doctor email címe
            String doctorIdToQueryEmail = "103";
            NodeList doctorListForEmail = doc.getElementsByTagName("Doctor");
            for (int i = 0; i < doctorListForEmail.getLength(); i++) {
                Node node = doctorListForEmail.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element doctor = (Element) node;
                    if (doctor.getElementsByTagName("ID").item(0).getTextContent().equals(doctorIdToQueryEmail)) {
                        System.out.println("103 ID-jû doctor email címe: " +
                                doctor.getElementsByTagName("Email").item(0).getTextContent());
                        break;
                    }
                }
            }

            // f) Az 1-es beteg mióta (FromWhen) szenved betegségben?
            String patientIdToQuerySuffering = "1";
            NodeList sufferingList = doc.getElementsByTagName("Suffering");
            for (int i = 0; i < sufferingList.getLength(); i++) {
                Node node = sufferingList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element suffering = (Element) node;
                    if (suffering.getElementsByTagName("PatientID").item(0).getTextContent().equals(patientIdToQuerySuffering)) {
                        System.out.println("Az 1-es beteg mióta szenved betegségben (FromWhen): " +
                                suffering.getElementsByTagName("FromWhen").item(0).getTextContent());
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}