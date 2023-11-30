package hu.domparse.thdwdr;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class DOMWriteTHDWDR {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // Ide írd meg a kódot az XML struktúra létrehozásához és az adatokkal való feltöltéshez

            // Mentés az új XML fájlba
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("XMLTHDWDR1.xml"));
            transformer.transform(source, result);

            // Az XML struktúra kiírása a konzolra
            transformer.transform(source, new StreamResult(System.out));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
