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

            // modositasok v�grehajt�sa
            addOraadoToCoursesWithoutOraado(document);
            modifyLanguageAttribute(document);
            
            //  m�dos�tott dokumentum kiirasa a konzolra
            printDocument(document);

            // m�dos�tott dokumentum f�jlba �r�sa
            saveDocument(document, "kurzusfelvetelModify1THDWDR.xml");
            
            //3.feladat
            // �j kurzus hozz�ad�sa
            addNewCourse(document);

            // M�dos�tott dokumentum ki�r�sa a konzolra
            printDocument(document);

            // M�dos�tott dokumentum f�jlba �r�sa
            saveDocument(document, "kurzusfelvetelModify2THDWDR.xml");

        } catch (ParserConfigurationException | IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // M�dos�tja azoknak a kurzusoknak az �raad� elem�t, ahol nincs �raad�
    private static void addOraadoToCoursesWithoutOraado(Document document) {
        NodeList kurzusList = document.getElementsByTagName("kurzus");
        for (int i = 0; i < kurzusList.getLength(); i++) {
            Element kurzus = (Element) kurzusList.item(i);
            NodeList oraadoList = kurzus.getElementsByTagName("oraado");
            if (oraadoList.getLength() == 0) {
                // Ha nincs �raad�, hozz�adunk egyet
                Element newOraado = document.createElement("oraado");
                newOraado.appendChild(document.createTextNode("�j �raad�"));
                kurzus.appendChild(newOraado);
            }
        }
    }

    // M�dos�tja minden kurzus nyelv�t angol - n�metre
    private static void modifyLanguageAttribute(Document document) {
        NodeList kurzusList = document.getElementsByTagName("kurzus");
        for (int i = 0; i < kurzusList.getLength(); i++) {
            Element kurzus = (Element) kurzusList.item(i);
            kurzus.setAttribute("nyelv", "n�met");
        }
    }

    // Ki�rja a dokumentumot a konzolra
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

    // Ment�s
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
        newKurzus.setAttribute("nyelv", "n�met");

        Element kurzusnev = document.createElement("kurzusnev");
        kurzusnev.appendChild(document.createTextNode("Programoz�s fakult�ci�"));
        newKurzus.appendChild(kurzusnev);

        Element kredit = document.createElement("kredit");
        kredit.appendChild(document.createTextNode("10"));
        newKurzus.appendChild(kredit);

        Element hely = document.createElement("hely");
        hely.appendChild(document.createTextNode("E/4 koll�gium"));
        newKurzus.appendChild(hely);

        Element idopont = document.createElement("idopont");
        idopont.appendChild(document.createTextNode("P�ntek 8-10"));
        newKurzus.appendChild(idopont);

        Element oktato = document.createElement("oktato");
        oktato.appendChild(document.createTextNode("Teszt Elek"));
        newKurzus.appendChild(oktato);

        kurzusok.appendChild(newKurzus);
    }
}
