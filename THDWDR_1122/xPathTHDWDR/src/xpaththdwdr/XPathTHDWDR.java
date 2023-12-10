package xpaththdwdr;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

public class XPathTHDWDR {

    public static void main(String[] args) throws Exception {
        // XML dokumentum beolvas�sa
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("studentTHDWDR.xml");

        // XPath objektum l�trehoz�sa
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        // 1) V�lassza ki az �sszes student elemet, amely a class gyermekei!
        NodeList studentsOfClass = (NodeList) xPath.evaluate("/class/student", document, XPathConstants.NODESET);
        System.out.println("1) " + studentsOfClass.getLength() + " di�k elem tal�lhat� a class elem alatt.");

        // 2) V�lassza ki azt a student elemet, amely rendelkezik "id" attrib�tummal �s �rt�ke "2"!
        Node studentWithId2 = (Node) xPath.evaluate("/class/student[@id='2']", document, XPathConstants.NODE);
        System.out.println("2) Az id='2' �rt�k� di�k elem: " + studentWithId2.getTextContent());

        // 3) V�lassza ki az �sszes student elemet, f�ggetlen�l att�l, hogy hol vannak a dokumentumban!
        NodeList allStudents = (NodeList) xPath.evaluate("//student", document, XPathConstants.NODESET);
        System.out.println("3) �sszesen " + allStudents.getLength() + " di�k elem tal�lhat�.");

        // 4) V�lassza ki a m�sodik student elemet, amely a class root element gyermeke!
        Node secondStudentInClass = (Node) xPath.evaluate("/class/student[2]", document, XPathConstants.NODE);
        System.out.println("4) A m�sodik di�k a class elem alatt: " + secondStudentInClass.getTextContent());

        // 5) V�lassza ki az utols� student elemet, amely a class root element gyermeke!
        Node lastStudentInClass = (Node) xPath.evaluate("/class/student[last()]", document, XPathConstants.NODE);
        System.out.println("5) Az utols� di�k a class elem alatt: " + lastStudentInClass.getTextContent());

        // 6) V�lassza ki az utols� el�tti student elemet, amely a class root element gyermeke!
        Node secondToLastStudentInClass = (Node) xPath.evaluate("/class/student[last()-1]", document, XPathConstants.NODE);
        System.out.println("6) Az utols� el�tti di�k a class elem alatt: " + secondToLastStudentInClass.getTextContent());

        // 7) V�lassza ki az els� k�t student elemet, amely a root element gyermekei!
        NodeList firstTwoStudents = (NodeList) xPath.evaluate("/class/student[position() <= 2]", document, XPathConstants.NODESET);
        System.out.println("7) Az els� k�t di�k elem: ");
        for (int i = 0; i < firstTwoStudents.getLength(); i++) {
            System.out.println("   " + firstTwoStudents.item(i).getTextContent());
        }

        // 8) V�lassza ki a class root element �sszes gyermek elem�t!
        NodeList allClassChildren = (NodeList) xPath.evaluate("/class/*", document, XPathConstants.NODESET);
        System.out.println("8) A class elem �sszes gyermek eleme: ");
        for (int i = 0; i < allClassChildren.getLength(); i++) {
            System.out.println("   " + allClassChildren.item(i).getNodeName());
        }

        // 9) V�lassza ki az �sszes student elemet, amely rendelkezik legal�bb egy b�rmilyen attrib�tummal!
        NodeList studentsWithAttributes = (NodeList) xPath.evaluate("//student[@*]", document, XPathConstants.NODESET);
        System.out.println("9) �sszesen " + studentsWithAttributes.getLength() + " di�k elem rendelkezik attrib�tummal.");

        // 10) V�lassza ki a dokumentum �sszes elem�t!
        NodeList allElements = (NodeList) xPath.evaluate("//*", document, XPathConstants.NODESET);
        System.out.println("10) �sszesen " + allElements.getLength() + " elem tal�lhat� a dokumentumban.");

        // 11) V�lassza ki a class root element �sszes student elem�t, amelyn�l a kor>20!
        NodeList studentsOver20 = (NodeList) xPath.evaluate("/class/student[kor>20]", document, XPathConstants.NODESET);
        System.out.println("11) Azon di�kok, akikn�l a kor>20: ");
        for (int i = 0; i < studentsOver20.getLength(); i++) {
            System.out.println("   " + studentsOver20.item(i).getTextContent());
        }

        // 12) V�lassza ki az �sszes student elem �sszes keresztnev vagy vezeteknev csom�pontot!
        NodeList names = (NodeList) xPath.evaluate("//student/keresztnev | //student/vezeteknev", document, XPathConstants.NODESET);
        System.out.println("12) Az �sszes di�k keresztnev�nek vagy vezet�knev�t tartalmaz� csom�pontok: ");
        for (int i = 0; i < names.getLength(); i++) {
            System.out.println("   " + names.item(i).getTextContent());
        }
    }
}
