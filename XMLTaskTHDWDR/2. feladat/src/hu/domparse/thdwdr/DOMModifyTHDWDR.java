package hu.domparse.thdwdr;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.List;

public class DOMModifyTHDWDR {

    public static void main(String[] args) {
        try {
            Document doc = readXmlDocument("XMLTHDWDR.xml");

            // Módosítások
            modifyDoctors(doc);
            modifyPatientBirthYear(doc);
            addNewDoctor(doc);

            // Kiírás XML formában
            printDocument(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Document readXmlDocument(String fileName) throws Exception {
        File inputFile = new File(fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        return dBuilder.parse(inputFile);
    }

    private static void modifyDoctors(Document doc) {
        NodeList doctorList = doc.getElementsByTagName("Doctor");

        for (int i = 0; i < doctorList.getLength(); i++) {
            Node doctorNode = doctorList.item(i);
            if (doctorNode.getNodeType() == Node.ELEMENT_NODE) {
                Element doctorElement = (Element) doctorNode;
                String doctorId = doctorElement.getElementsByTagName("ID").item(0).getTextContent();

                // Módosítjuk az orvosok nevét, példa: Minden "Dr. " elõtagot eltávolítunk
                String doctorName = doctorElement.getElementsByTagName("Name").item(0).getTextContent();
                doctorElement.getElementsByTagName("Name").item(0).setTextContent(doctorName.replace("Dr. ", ""));
            }
        }
    }

    private static void modifyPatientBirthYear(Document doc) {
        NodeList patientList = doc.getElementsByTagName("Patient");

        for (int i = 0; i < patientList.getLength(); i++) {
            Node patientNode = patientList.item(i);
            if (patientNode.getNodeType() == Node.ELEMENT_NODE) {
                Element patientElement = (Element) patientNode;
                String patientId = patientElement.getElementsByTagName("ID").item(0).getTextContent();

                // Módosítjuk a 2 ID-jû patient születési évét, példa: Év hozzáadása
                if ("2".equals(patientId)) {
                    Element dateOfBirthElement = (Element) patientElement.getElementsByTagName("DateOfBirth").item(0);
                    LocalDate newDateOfBirth = LocalDate.parse(dateOfBirthElement.getTextContent()).plusYears(1);
                    dateOfBirthElement.setTextContent(newDateOfBirth.toString());
                }
            }
        }
    }

    private static void addNewDoctor(Document doc) {
        Element rootElement = doc.getDocumentElement();

        // Új Doctor elem hozzáadása
        Element newDoctorElement = doc.createElement("Doctor");
        Element idElement = doc.createElement("ID");
        idElement.appendChild(doc.createTextNode("105"));
        newDoctorElement.appendChild(idElement);

        Element nameElement = doc.createElement("Name");
        nameElement.appendChild(doc.createTextNode("Dr. New Doctor"));
        newDoctorElement.appendChild(nameElement);

        Element addressElement = doc.createElement("Address");
        addressElement.appendChild(doc.createTextNode("New Doctor Address"));
        newDoctorElement.appendChild(addressElement);

        Element phoneElement = doc.createElement("PhoneNumber");
        phoneElement.appendChild(doc.createTextNode("123456789"));
        newDoctorElement.appendChild(phoneElement);

        Element emailElement = doc.createElement("Email");
        emailElement.appendChild(doc.createTextNode("new.doctor@example.com"));
        newDoctorElement.appendChild(emailElement);

        rootElement.appendChild(newDoctorElement);
    }

    private static void printDocument(Document doc) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        String output = writer.getBuffer().toString();
        System.out.println(output);
    }
}
