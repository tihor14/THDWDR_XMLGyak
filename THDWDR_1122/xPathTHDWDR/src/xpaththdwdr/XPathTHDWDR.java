package xpaththdwdr;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

public class XPathTHDWDR {

    public static void main(String[] args) throws Exception {
        // XML dokumentum beolvasása
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("studentTHDWDR.xml");

        // XPath objektum létrehozása
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        // 1) Válassza ki az összes student elemet, amely a class gyermekei!
        NodeList studentsOfClass = (NodeList) xPath.evaluate("/class/student", document, XPathConstants.NODESET);
        System.out.println("1) " + studentsOfClass.getLength() + " diák elem található a class elem alatt.");

        // 2) Válassza ki azt a student elemet, amely rendelkezik "id" attribútummal és értéke "2"!
        Node studentWithId2 = (Node) xPath.evaluate("/class/student[@id='2']", document, XPathConstants.NODE);
        System.out.println("2) Az id='2' értékû diák elem: " + studentWithId2.getTextContent());

        // 3) Válassza ki az összes student elemet, függetlenül attól, hogy hol vannak a dokumentumban!
        NodeList allStudents = (NodeList) xPath.evaluate("//student", document, XPathConstants.NODESET);
        System.out.println("3) Összesen " + allStudents.getLength() + " diák elem található.");

        // 4) Válassza ki a második student elemet, amely a class root element gyermeke!
        Node secondStudentInClass = (Node) xPath.evaluate("/class/student[2]", document, XPathConstants.NODE);
        System.out.println("4) A második diák a class elem alatt: " + secondStudentInClass.getTextContent());

        // 5) Válassza ki az utolsó student elemet, amely a class root element gyermeke!
        Node lastStudentInClass = (Node) xPath.evaluate("/class/student[last()]", document, XPathConstants.NODE);
        System.out.println("5) Az utolsó diák a class elem alatt: " + lastStudentInClass.getTextContent());

        // 6) Válassza ki az utolsó elõtti student elemet, amely a class root element gyermeke!
        Node secondToLastStudentInClass = (Node) xPath.evaluate("/class/student[last()-1]", document, XPathConstants.NODE);
        System.out.println("6) Az utolsó elõtti diák a class elem alatt: " + secondToLastStudentInClass.getTextContent());

        // 7) Válassza ki az elsõ két student elemet, amely a root element gyermekei!
        NodeList firstTwoStudents = (NodeList) xPath.evaluate("/class/student[position() <= 2]", document, XPathConstants.NODESET);
        System.out.println("7) Az elsõ két diák elem: ");
        for (int i = 0; i < firstTwoStudents.getLength(); i++) {
            System.out.println("   " + firstTwoStudents.item(i).getTextContent());
        }

        // 8) Válassza ki a class root element összes gyermek elemét!
        NodeList allClassChildren = (NodeList) xPath.evaluate("/class/*", document, XPathConstants.NODESET);
        System.out.println("8) A class elem összes gyermek eleme: ");
        for (int i = 0; i < allClassChildren.getLength(); i++) {
            System.out.println("   " + allClassChildren.item(i).getNodeName());
        }

        // 9) Válassza ki az összes student elemet, amely rendelkezik legalább egy bármilyen attribútummal!
        NodeList studentsWithAttributes = (NodeList) xPath.evaluate("//student[@*]", document, XPathConstants.NODESET);
        System.out.println("9) Összesen " + studentsWithAttributes.getLength() + " diák elem rendelkezik attribútummal.");

        // 10) Válassza ki a dokumentum összes elemét!
        NodeList allElements = (NodeList) xPath.evaluate("//*", document, XPathConstants.NODESET);
        System.out.println("10) Összesen " + allElements.getLength() + " elem található a dokumentumban.");

        // 11) Válassza ki a class root element összes student elemét, amelynél a kor>20!
        NodeList studentsOver20 = (NodeList) xPath.evaluate("/class/student[kor>20]", document, XPathConstants.NODESET);
        System.out.println("11) Azon diákok, akiknél a kor>20: ");
        for (int i = 0; i < studentsOver20.getLength(); i++) {
            System.out.println("   " + studentsOver20.item(i).getTextContent());
        }

        // 12) Válassza ki az összes student elem összes keresztnev vagy vezeteknev csomópontot!
        NodeList names = (NodeList) xPath.evaluate("//student/keresztnev | //student/vezeteknev", document, XPathConstants.NODESET);
        System.out.println("12) Az összes diák keresztnevének vagy vezetéknevét tartalmazó csomópontok: ");
        for (int i = 0; i < names.getLength(); i++) {
            System.out.println("   " + names.item(i).getTextContent());
        }
    }
}
