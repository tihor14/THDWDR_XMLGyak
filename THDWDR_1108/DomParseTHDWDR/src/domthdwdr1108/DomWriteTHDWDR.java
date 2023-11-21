package domthdwdr1108;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class DomWriteTHDWDR {

    public static void main(String[] args) {
        try {
            // meglevo XML fajl feldolgozasa
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("kurzusfelvetelTHDWDR.xml"));

            // megjelenites konzolon
            displayXmlContent(document);

            // DOM struktura fajlba irasa
            writeXmlToFile(document, "kurzufelvetel1THDWDR.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void displayXmlContent(Document document) {
        // Megjeleniti az XML fajl tartalmat a konzolon
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));
            System.out.println(writer.toString());
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void writeXmlToFile(Document document, String fileName) {
        try {
            // DOM struktura fajlba irasa
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(fileName));

            transformer.transform(source, result);

            System.out.println("DOM structure has been written to " + fileName);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
