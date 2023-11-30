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

            // Ide �rd meg a k�dot az XML strukt�ra l�trehoz�s�hoz �s az adatokkal val� felt�lt�shez

            // Ment�s az �j XML f�jlba
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("XMLTHDWDR1.xml"));
            transformer.transform(source, result);

            // Az XML strukt�ra ki�r�sa a konzolra
            transformer.transform(source, new StreamResult(System.out));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
