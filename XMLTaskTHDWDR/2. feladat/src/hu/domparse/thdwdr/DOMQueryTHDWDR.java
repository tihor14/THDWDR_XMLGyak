package hu.domparse.thdwdr;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class DOMQueryTHDWDR {
    public static void main(String[] args) {
        try {
            File inputFile = new File("XMLTHDWDR.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Ide �rd meg a k�dot a specifikus adatok lek�rdez�s�hez az XML dokumentumb�l

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
