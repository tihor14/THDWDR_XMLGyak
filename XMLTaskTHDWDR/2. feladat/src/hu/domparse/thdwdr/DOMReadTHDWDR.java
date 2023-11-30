package hu.domparse.thdwdr;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DOMReadTHDWDR {
    public static void main(String[] args) {
        try {
            File inputFile = new File("XMLTHDWDR.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Adatolvasás kezdete
            List<Patient> patients = readPatient(doc);
            List<Doctor> doctors = readDoctor(doc);
            // Most itt lehet dolgozni az adatokkal
            for (Patient patient : patients) {
                System.out.println(patient);
            }
            for (Doctor doctor : doctors) {
                System.out.println(doctor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Patient> readPatient(Document doc) {
        List<Patient> patients = new ArrayList<>();
        NodeList patientList = doc.getElementsByTagName("Patient");
        for (int temp = 0; temp < patientList.getLength(); temp++) {
            Node patientNode = patientList.item(temp);
            if (patientNode.getNodeType() == Node.ELEMENT_NODE) {
                Element patientElement = (Element) patientNode;
                Patient patient = new Patient();

                // Betöltés a Patient entitásból
                patient.setId(patientElement.getElementsByTagName("ID").item(0).getTextContent());
                patient.setName(patientElement.getElementsByTagName("Name").item(0).getTextContent());
                patient.setDateOfBirth(patientElement.getElementsByTagName("DateOfBirth").item(0).getTextContent());
                patient.setAge(patientElement.getElementsByTagName("Age").item(0).getTextContent());

                // Betöltés az Address entitásból
                Element addressElement = (Element) patientElement.getElementsByTagName("Address").item(0);
                patient.setPostalCode(addressElement.getElementsByTagName("PostalCode").item(0).getTextContent());
                patient.setCity(addressElement.getElementsByTagName("City").item(0).getTextContent());
                patient.setStreet(addressElement.getElementsByTagName("Street").item(0).getTextContent());
                patient.setHouseNumber(addressElement.getElementsByTagName("HouseNumber").item(0).getTextContent());

                patient.setTaj(patientElement.getElementsByTagName("TAJ").item(0).getTextContent());

                // Telefonok betöltése
                NodeList phoneNumberList = patientElement.getElementsByTagName("PhoneNumber");
                List<String> phoneNumbers = new ArrayList<>();
                for (int i = 0; i < phoneNumberList.getLength(); i++) {
                    String phoneNumber = phoneNumberList.item(i).getTextContent();
                    phoneNumbers.add(phoneNumber);
                }
                patient.setPhoneNumbers(phoneNumbers);

                patient.setEmail(patientElement.getElementsByTagName("Email").item(0).getTextContent());

                patients.add(patient);
            }
        }
        return patients;
    }
    private static List<Doctor> readDoctor(Document doc) {
        List<Doctor> doctors = new ArrayList<>();
        NodeList doctorList = doc.getElementsByTagName("Doctor");
        for (int temp = 0; temp < doctorList.getLength(); temp++) {
            Node doctorNode = doctorList.item(temp);
            if (doctorNode.getNodeType() == Node.ELEMENT_NODE) {
                Element doctorElement = (Element) doctorNode;
                Doctor doctor = new Doctor();

                // Betöltés a Doctor entitásból
                doctor.setId(doctorElement.getElementsByTagName("ID").item(0).getTextContent());
                doctor.setName(doctorElement.getElementsByTagName("Name").item(0).getTextContent());
                doctor.setAddress(doctorElement.getElementsByTagName("Address").item(0).getTextContent());

                // Telefonok betöltése
                NodeList phoneNumberList = doctorElement.getElementsByTagName("PhoneNumber");
                List<String> phoneNumbers = new ArrayList<>();
                for (int i = 0; i < phoneNumberList.getLength(); i++) {
                    String phoneNumber = phoneNumberList.item(i).getTextContent();
                    phoneNumbers.add(phoneNumber);
                }
                doctor.setPhoneNumbers(phoneNumbers);

                doctor.setEmail(doctorElement.getElementsByTagName("Email").item(0).getTextContent());

                doctors.add(doctor);
            }
        }
        return doctors;
    }
    private static List<Department> readDepartment(Document doc) {
        List<Department> departments = new ArrayList<>();
        NodeList departmentList = doc.getElementsByTagName("Department");
        for (int temp = 0; temp < departmentList.getLength(); temp++) {
            Node departmentNode = departmentList.item(temp);
            if (departmentNode.getNodeType() == Node.ELEMENT_NODE) {
                Element departmentElement = (Element) departmentNode;
                Department department = new Department();

                // Betöltés a Department entitásból
                department.setId(departmentElement.getElementsByTagName("ID").item(0).getTextContent());
                department.setName(departmentElement.getElementsByTagName("Name").item(0).getTextContent());
                department.setLocation(departmentElement.getElementsByTagName("Location").item(0).getTextContent());

                departments.add(department);
            }
        }
        return departments;
    }
    private static List<Medicine> readMedicine(Document doc) {
        List<Medicine> medicines = new ArrayList<>();
        NodeList medicineList = doc.getElementsByTagName("Medicine");
        for (int temp = 0; temp < medicineList.getLength(); temp++) {
            Node medicineNode = medicineList.item(temp);
            if (medicineNode.getNodeType() == Node.ELEMENT_NODE) {
                Element medicineElement = (Element) medicineNode;
                Medicine medicine = new Medicine();

                // Betöltés a Medicine entitásból
                medicine.setName(medicineElement.getElementsByTagName("Name").item(0).getTextContent());
                medicine.setDescription(medicineElement.getElementsByTagName("Description").item(0).getTextContent());

                medicines.add(medicine);
            }
        }
        return medicines;
    }
    private static List<Disease> readDisease(Document doc) {
        List<Disease> diseases = new ArrayList<>();
        NodeList diseaseList = doc.getElementsByTagName("Disease");
        for (int temp = 0; temp < diseaseList.getLength(); temp++) {
            Node diseaseNode = diseaseList.item(temp);
            if (diseaseNode.getNodeType() == Node.ELEMENT_NODE) {
                Element diseaseElement = (Element) diseaseNode;
                Disease disease = new Disease();

                // Betöltés a Disease entitásból
                disease.setName(diseaseElement.getElementsByTagName("Name").item(0).getTextContent());
                disease.setSymptom(diseaseElement.getElementsByTagName("Symptom").item(0).getTextContent());
                disease.setAffectedOrgans(diseaseElement.getElementsByTagName("AffectedOrgans").item(0).getTextContent());

                diseases.add(disease);
            }
        }
        return diseases;
    }
    private static List<Suffering> readSuffering(Document doc) {
        List<Suffering> sufferings = new ArrayList<>();
        NodeList sufferingList = doc.getElementsByTagName("Suffering");
        for (int temp = 0; temp < sufferingList.getLength(); temp++) {
            Node sufferingNode = sufferingList.item(temp);
            if (sufferingNode.getNodeType() == Node.ELEMENT_NODE) {
                Element sufferingElement = (Element) sufferingNode;
                Suffering suffering = new Suffering();

                // Betöltés a Suffering entitásból
                suffering.setDiseaseID(sufferingElement.getElementsByTagName("DiseaseID").item(0).getTextContent());
                suffering.setPatientID(sufferingElement.getElementsByTagName("PatientID").item(0).getTextContent());
                suffering.setFromWhen(sufferingElement.getElementsByTagName("FromWhen").item(0).getTextContent());

                sufferings.add(suffering);
            }
        }
        return sufferings;
    }
}
