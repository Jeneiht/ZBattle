package com.trongthien.zBattle.GameMap;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class XMLReader {
    //singleton
    private static XMLReader instance;
    private XMLReader() {
    }
    public static XMLReader getInstance() {
        if (instance == null) {
            instance = new XMLReader();
        }
        return instance;
    }
    public ArrayList<String> readMap(String gameMapPath) {
        ArrayList<String> layers = new ArrayList<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(gameMapPath);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("data");
            for (int i = 0; i < nodeList.getLength(); i++) {
                layers.add(nodeList.item(i).getTextContent());
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        return layers;
    }
}
