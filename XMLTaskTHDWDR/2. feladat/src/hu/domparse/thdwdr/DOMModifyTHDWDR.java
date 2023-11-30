package hu.domparse.thdwdr;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class DOMModifyTHDWDR {
    public static void main(String[] args) {
        try {
            File inputFile = new File("XMLTHDWDR.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Ide �rd meg a k�dot az adatok m�dos�t�s�hoz az XML dokumentumban

            // Ment�s vissza az XML f�jlba
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("XMLTHDWDR.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
