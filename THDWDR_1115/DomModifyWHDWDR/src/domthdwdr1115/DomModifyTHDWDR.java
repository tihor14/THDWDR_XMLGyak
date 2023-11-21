package domthdwdr1115;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomModifyTHDWDR {

    public static void main(String[] args) {
        try {
            //  dokumentum beolvasasa
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("kurzusfelvetelTHDWDR.xml"));

            // modositasok végrehajtása
            addOraadoToCoursesWithoutOraado(document);
            modifyLanguageAttribute(document);
            
            //  módosított dokumentum kiirasa a konzolra
            printDocument(document);

            // módosított dokumentum fájlba írása
            saveDocument(document, "kurzusfelvetelModify1THDWDR.xml");
            
            //3.feladat
            // Új kurzus hozzáadása
            addNewCourse(document);

            // Módosított dokumentum kiírása a konzolra
            printDocument(document);

            // Módosított dokumentum fájlba írása
            saveDocument(document, "kurzusfelvetelModify2THDWDR.xml");

        } catch (ParserConfigurationException | IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Módosítja azoknak a kurzusoknak az óraadó elemét, ahol nincs óraadó
    private static void addOraadoToCoursesWithoutOraado(Document document) {
        NodeList kurzusList = document.getElementsByTagName("kurzus");
        for (int i = 0; i < kurzusList.getLength(); i++) {
            Element kurzus = (Element) kurzusList.item(i);
            NodeList oraadoList = kurzus.getElementsByTagName("oraado");
            if (oraadoList.getLength() == 0) {
                // Ha nincs óraadó, hozzáadunk egyet
                Element newOraado = document.createElement("oraado");
                newOraado.appendChild(document.createTextNode("Új óraadó"));
                kurzus.appendChild(newOraado);
            }
        }
    }

    // Módosítja minden kurzus nyelvét angol - németre
    private static void modifyLanguageAttribute(Document document) {
        NodeList kurzusList = document.getElementsByTagName("kurzus");
        for (int i = 0; i < kurzusList.getLength(); i++) {
            Element kurzus = (Element) kurzusList.item(i);
            kurzus.setAttribute("nyelv", "német");
        }
    }

    // Kiírja a dokumentumot a konzolra
    private static void printDocument(Document document) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Mentés
    private static void saveDocument(Document document, String fileName) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult fileResult = new StreamResult(new File(fileName));
            transformer.transform(source, fileResult);
            System.out.println("Dokumentum sikeresen mentve: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //uj kurzus letrehozasa
    public static void addNewCourse(Document document) {
        Element kurzusok = (Element) document.getElementsByTagName("kurzusok").item(0);

        Element newKurzus = document.createElement("kurzus");
        newKurzus.setAttribute("id", "THDWDR");
        newKurzus.setAttribute("nyelv", "német");

        Element kurzusnev = document.createElement("kurzusnev");
        kurzusnev.appendChild(document.createTextNode("Programozás fakultáció"));
        newKurzus.appendChild(kurzusnev);

        Element kredit = document.createElement("kredit");
        kredit.appendChild(document.createTextNode("10"));
        newKurzus.appendChild(kredit);

        Element hely = document.createElement("hely");
        hely.appendChild(document.createTextNode("E/4 kollégium"));
        newKurzus.appendChild(hely);

        Element idopont = document.createElement("idopont");
        idopont.appendChild(document.createTextNode("Péntek 8-10"));
        newKurzus.appendChild(idopont);

        Element oktato = document.createElement("oktato");
        oktato.appendChild(document.createTextNode("Teszt Elek"));
        newKurzus.appendChild(oktato);

        kurzusok.appendChild(newKurzus);
    }
}
