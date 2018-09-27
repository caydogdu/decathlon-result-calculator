package com.commons.writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.commons.bean.EventResult;
import com.commons.bean.Ranking;

public class DecathlonRankingsXMLWriter implements RankingsXMLWriter {

    private static final Logger LOGGER = Logger.getLogger(DecathlonRankingsXMLWriter.class.getName());

    private String fileName;

    private List<Ranking> rankings;

    public DecathlonRankingsXMLWriter(String fileName, List<Ranking> rankings) {
        this.fileName = fileName;
        this.rankings = rankings;
    }

    @Override
    public void writeRankingsXML() {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Create rankings root element
            Element rankingsRootElement = doc.createElement("rankings");
            doc.appendChild(rankingsRootElement);

            // Create ranking element
            for (Ranking ranking : rankings) {
                Element rankingElement = doc.createElement("ranking");

                Element numberElement = doc.createElement("number");
                numberElement.appendChild(doc.createTextNode(String.valueOf(ranking.getNumber())));
                rankingElement.appendChild(numberElement);

                Element nameElement = doc.createElement("name");
                nameElement.appendChild(doc.createTextNode(ranking.getAthlete().getName()));
                rankingElement.appendChild(nameElement);

                // Create events element
                Element eventsElement = doc.createElement("events");
                for (EventResult eventResult : ranking.getAthlete().getEventResults()) {
                    Element eventElement = doc.createElement("event");

                    Element eventNameElement = doc.createElement("eventName");
                    eventNameElement.appendChild(doc.createTextNode(eventResult.getEvent().getName()));
                    eventElement.appendChild(eventNameElement);

                    Element resultElement = doc.createElement("result");
                    resultElement.appendChild(doc.createTextNode(String.valueOf(eventResult.getOriginalResult())));
                    eventElement.appendChild(resultElement);

                    eventsElement.appendChild(eventElement);
                }
                rankingElement.appendChild(eventsElement);

                // Create total element
                Element totalElement = doc.createElement("total");
                totalElement.appendChild(doc.createTextNode(String.valueOf(ranking.getAthlete().getTotal())));
                rankingElement.appendChild(totalElement);

                rankingsRootElement.appendChild(rankingElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            OutputStream out = new FileOutputStream(new File("src/main/resources/" + fileName));
            StreamResult result = new StreamResult(out);

            transformer.transform(source, result);

            LOGGER.log(Level.INFO, "{0} successfully created in src/main/resources directory", fileName);

        } catch (ParserConfigurationException | TransformerException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Exception occur", e);
        }

    }

}
