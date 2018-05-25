package com.home.javaplayground.services;

import com.home.javaplayground.models.SimpleWikiDoc;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jzr1991 on 25/05/2018.
 */
public class SimpleWikiXmlParser {

    private static InputStream input;
    private static XMLStreamReader reader;

    public SimpleWikiXmlParser(String filePath) throws FileNotFoundException, XMLStreamException {
        input = new BufferedInputStream(new FileInputStream(filePath));
        reader = XMLInputFactory.newInstance().createXMLStreamReader(input);
    }

    public boolean hasNext() throws XMLStreamException {
        return reader.hasNext();
    }

    public void close() throws XMLStreamException {
        reader.close();
    }

    public SimpleWikiDoc getNextPage() throws XMLStreamException, SimpleWikiXmlParserException {

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if (elementName.equals(SimpleWikiDoc.getWikiParentNode()))
                        return getPageXml();
                    break;
            }
        }
        return null;
    }

    private SimpleWikiDoc getPageXml() throws XMLStreamException, SimpleWikiXmlParserException {

        List<String> nodes = new ArrayList<>();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();

                    for (String s : SimpleWikiDoc.getWikiChildNodes()) {
                        if (elementName.equals(s)) {
                            nodes.add(readXmlStringContent());
                        }
                    }
            }

            if (nodes.size() == SimpleWikiDoc.getWikiChildNodes().size()) {
                return new SimpleWikiDoc(nodes);
            }
        }

        throw new SimpleWikiXmlParserException("StreamReader did not find expected fields under the parent XML node.");
    }

    private String readXmlStringContent() throws XMLStreamException {
        StringBuilder result = new StringBuilder();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.CHARACTERS:
                case XMLStreamReader.CDATA:
                    result.append(reader.getText());
                    break;
                case XMLStreamReader.END_ELEMENT:
                    return result.toString();
            }
        }
        throw new XMLStreamException("Something went wonky");
    }

}
