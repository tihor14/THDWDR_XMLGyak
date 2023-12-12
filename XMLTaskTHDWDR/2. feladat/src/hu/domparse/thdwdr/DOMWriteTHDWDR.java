package hu.domparse.thdwdr;

import org.w3c.dom.Document;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DOMWriteTHDWDR {
    public static void main(String[] args) {
        try {
            // Beolvasás a DOMReadTHDWDR osztály segítségével
            Document doc = readXmlDocument("XMLTHDWDR.xml");

            // Mentés az új XML fájlba
            saveToXmlFile(doc, "XMLTHDWDR1.xml");

            // Az XML struktúra kiírása a konzolra
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
    private static void saveToXmlFile(Document document, String fileName) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(fileName);
            transformer.transform(source, result);
            System.out.println("XML fájl mentve: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printDocument(Document document) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
