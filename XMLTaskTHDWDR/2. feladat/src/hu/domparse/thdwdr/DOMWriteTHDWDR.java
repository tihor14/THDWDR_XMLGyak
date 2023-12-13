package hu.domparse.thdwdr;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DOMWriteTHDWDR {

    public static void main(String[] args) {
        try {
            // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Create root element
            Element rootElement = doc.createElement("THDWDR_kórház");
            doc.appendChild(rootElement);

            // Create and append Patient elements
            createAndAppendPatient(doc, rootElement, "1", "Kiss Pista", "1985-05-10", "38",
                    "1111", "Budapest", "Kossuth utca", "5", "123456789", Arrays.asList("123-456-7890", "987-654-3210"),
                    "kiss.pista@example.com");

            createAndAppendPatient(doc, rootElement, "2", "Magyari Ádám", "1992-09-22", "30",
                    "2222", "Debrecen", "Petõfi utca", "12", "987654321", Arrays.asList("111-222-3333", "444-555-6666"),
                    "magyari.adam@example.com");

            createAndAppendPatient(doc, rootElement, "3", "Pácza Laura", "1980-03-15", "42",
                    "3333", "Szeged", "Arany János utca", "8", "111222333", Arrays.asList("555-666-7777"),
                    "pacza.laura@example.com");

            // Create and append Doctor elements
            createAndAppendDoctor(doc, rootElement, "101", "Dr. Kovács Tamás", "Szakrendelõ",
                    Arrays.asList("111-222-3333", "444-555-6666"), "dr.kovacs.tamas@example.com");

            createAndAppendDoctor(doc, rootElement, "102", "Dr. Nagy Éva", "Kórház",
                    Arrays.asList("777-888-9999"), "dr.nagy.eva@example.com");

            createAndAppendDoctor(doc, rootElement, "103", "Dr. Balogh Gábor", "Rendelõintézet",
                    Arrays.asList("333-444-5555"), "dr.balogh.gabor@example.com");

            // Create and append Department elements
            createAndAppendDepartment(doc, rootElement, "201", "Belgyógyászat", "2. emelet");
            createAndAppendDepartment(doc, rootElement, "202", "Radiológia", "4. emelet");
            createAndAppendDepartment(doc, rootElement, "203", "Sebészet", "1. emelet");

            // Create and append Medicine elements
            createAndAppendMedicine(doc, rootElement, "Paracetamol", "Fájdalomcsillapító");
            createAndAppendMedicine(doc, rootElement, "Antibiotikum", "Gyulladáscsökkentõ");
            createAndAppendMedicine(doc, rootElement, "Vitamin C", "Immunerõsítõ");

            // Create and append Disease elements
            createAndAppendDisease(doc, rootElement, "Influenza", "Láz, H köhögés", "Légzõrendszer");
            createAndAppendDisease(doc, rootElement, "Magas vérnyomás", "Fejfájás, Szédülés", "Szív-érrendszer");
            createAndAppendDisease(doc, rootElement, "Cukorbetegség", "Fáradtság, Szomjúság", "Endokrin rendszer");

            // Create and append Relationships elements
            createAndAppendSuffering(doc, rootElement, "1", "1", "2023-02-15");
            createAndAppendSuffering(doc, rootElement, "2", "2", "2023-03-20");
            createAndAppendSuffering(doc, rootElement, "3", "3", "2023-01-10");

            createAndAppendEffective(doc, rootElement, "1", "Paracetamol");
            createAndAppendEffective(doc, rootElement, "2", "Antibiotikum");
            createAndAppendEffective(doc, rootElement, "3", "Vitamin C");

            createAndAppendTreats(doc, rootElement, "101", "1", "2023-02-15", "10 nap");
            createAndAppendTreats(doc, rootElement, "102", "2", "2023-03-20", "14 nap");
            createAndAppendTreats(doc, rootElement, "103", "3", "2023-01-10", "7 nap");

            createAndAppendWorking(doc, rootElement, "101", "201", "true");
            createAndAppendWorking(doc, rootElement, "102", "202", "false");
            createAndAppendWorking(doc, rootElement, "103", "203", "true");

            // Print to console
            printToConsole(doc);

            // Write to XML file
            writeToXmlFile(doc, "XMLTHDWDR1.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 // XML dokumentum kiírása a konzolra
    private static void printToConsole(Document doc) {
        try {
            // Formázó beállítások, beleértve az UTF-8 karakterkódolást
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");  // Ez beállítja az UTF-8 karakterkódolást
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            // XML kód kiírása a konzolra
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Helper methods to create and append elements

    private static void createAndAppendPatient(Document doc, Element rootElement, String id, String name,
            String dateOfBirth, String age, String postalCode, String city, String street, String houseNumber,
            String taj, List<String> phoneNumbers, String email) {

        // TODO: Implement the actual logic to create and append Patient elements
        // Create Patient element
        Element patientElement = doc.createElement("Patient");

        // TODO: Add logic to create and append child elements of Patient

        // Append Patient element to root element
        rootElement.appendChild(patientElement);
    }

    // Write the XML document to a file
    private static void writeToXmlFile(Document doc, String filename) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(new File(filename)));

            transformer.transform(source, result);
            System.out.println("XML written to " + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void createAndAppendDoctor(Document doc, Element rootElement, String id, String name, String address,
            List<String> phoneNumbers, String email) {

        // Create Doctor element
        Element doctorElement = doc.createElement("Doctor");

        // Create and append child elements of Doctor
        appendChildElement(doc, doctorElement, "ID", id);
        appendChildElement(doc, doctorElement, "Name", name);
        appendChildElement(doc, doctorElement, "Address", address);

        // Create and append multiple PhoneNumber elements
        for (String phoneNumber : phoneNumbers) {
            appendChildElement(doc, doctorElement, "PhoneNumber", phoneNumber);
        }

        appendChildElement(doc, doctorElement, "Email", email);

        // Append Doctor element to root element
        rootElement.appendChild(doctorElement);
    }
    private static void appendChildElement(Document doc, Element parentElement, String childName, String childValue) {
        Element childElement = doc.createElement(childName);
        childElement.appendChild(doc.createTextNode(childValue));
        parentElement.appendChild(childElement);
    }
    private static void createAndAppendDepartment(Document doc, Element rootElement, String id, String name, String location) {
        // Create Department element
        Element departmentElement = doc.createElement("Department");

        // Create and append child elements of Department
        appendChildElement(doc, departmentElement, "ID", id);
        appendChildElement(doc, departmentElement, "Name", name);
        appendChildElement(doc, departmentElement, "Location", location);

        // Append Department element to root element
        rootElement.appendChild(departmentElement);
    }
    private static void createAndAppendMedicine(Document doc, Element rootElement, String name, String description) {
        // Create Medicine element
        Element medicineElement = doc.createElement("Medicine");

        // Create and append child elements of Medicine
        appendChildElement(doc, medicineElement, "Name", name);
        appendChildElement(doc, medicineElement, "Description", description);

        // Append Medicine element to root element
        rootElement.appendChild(medicineElement);
    }
    private static void createAndAppendDisease(Document doc, Element rootElement, String name, String symptom, String affectedOrgans) {
        // Create Disease element
        Element diseaseElement = doc.createElement("Disease");

        // Create and append child elements of Disease
        appendChildElement(doc, diseaseElement, "Name", name);
        appendChildElement(doc, diseaseElement, "Symptom", symptom);
        appendChildElement(doc, diseaseElement, "AffectedOrgans", affectedOrgans);

        // Append Disease element to root element
        rootElement.appendChild(diseaseElement);
    }
    private static void createAndAppendSuffering(Document doc, Element rootElement, String diseaseID, String patientID, String fromWhen) {
        // Create Suffering element
        Element sufferingElement = doc.createElement("Suffering");

        // Create and append child elements of Suffering
        appendChildElement(doc, sufferingElement, "DiseaseID", diseaseID);
        appendChildElement(doc, sufferingElement, "PatientID", patientID);
        appendChildElement(doc, sufferingElement, "FromWhen", fromWhen);

        // Append Suffering element to root element
        rootElement.appendChild(sufferingElement);
    }
    private static void createAndAppendEffective(Document doc, Element rootElement, String diseaseID, String medicineName) {
        // Create Effective element
        Element effectiveElement = doc.createElement("Effective");

        // Create and append child elements of Effective
        appendChildElement(doc, effectiveElement, "DiseaseID", diseaseID);
        appendChildElement(doc, effectiveElement, "MedicineName", medicineName);

        // Append Effective element to root element
        rootElement.appendChild(effectiveElement);
    }
    private static void createAndAppendTreats(Document doc, Element rootElement, String doctorID, String patientID, String fromWhen, String howLong) {
        // Create Treats element
        Element treatsElement = doc.createElement("Treats");

        // Create and append child elements of Treats
        appendChildElement(doc, treatsElement, "DoctorID", doctorID);
        appendChildElement(doc, treatsElement, "PatientID", patientID);
        appendChildElement(doc, treatsElement, "FromWhen", fromWhen);
        appendChildElement(doc, treatsElement, "HowLong", howLong);

        // Append Treats element to root element
        rootElement.appendChild(treatsElement);
    }
    private static void createAndAppendWorking(Document doc, Element rootElement, String doctorID, String departmentID, String leads) {
        // Create Working element
        Element workingElement = doc.createElement("Working");

        // Create and append child elements of Working
        appendChildElement(doc, workingElement, "DoctorID", doctorID);
        appendChildElement(doc, workingElement, "DepartmentID", departmentID);
        appendChildElement(doc, workingElement, "Leads", leads);

        // Append Working element to root element
        rootElement.appendChild(workingElement);
    }

}
