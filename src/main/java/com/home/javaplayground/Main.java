package com.home.javaplayground;

import javax.xml.stream.*;
import java.io.*;

/**
 * Created by jzr1991 on 22/05/2018.
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {

        InputStream in = new BufferedInputStream(new FileInputStream(args[0]));
        XMLInputFactory xmf = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmf.createXMLStreamReader(in);

        while(xmlr.hasNext()) {
            int eventType = xmlr.next();
            
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = xmlr.getLocalName();
                    if (elementName.equals("page"))
                        readPageXml(xmlr);
                    break;
            }
        }
        xmlr.close();
    }

    private static void readPageXml(XMLStreamReader reader) throws XMLStreamException {

        String title = "";
        String content = "";

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    
                    if (elementName.equals("title")) {
                        title = readXmlStringContent(reader);
                    }
                    if (elementName.equals("text")) {
                        content = readXmlStringContent(reader);
                    }
            }

            if (!title.isEmpty() && !content.isEmpty()) {
                System.out.println(new Entry(title, content));
                break;
            }
        }
    }

    private static String readXmlStringContent(XMLStreamReader reader) throws XMLStreamException {
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
