package com.home.javaplayground;

import com.home.javaplayground.models.IDoc;
import com.home.javaplayground.models.SimpleWikiDoc;
import com.home.javaplayground.services.SimpleWikiXmlParser;
import com.home.javaplayground.services.SimpleWikiXmlParserException;

import javax.xml.stream.*;
import java.io.*;

/**
 * Created by jzr1991 on 22/05/2018.
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException, SimpleWikiXmlParserException {

        SimpleWikiXmlParser parser = new SimpleWikiXmlParser(args[0]);
        SimpleWikiDoc<String> doc;

        while (parser.hasNext()) {
            doc = parser.getNextPage();
            printDocToConsole(doc);
        }

        parser.close();
    }

    private static <T> void printDocToConsole(T doc) {
        if (doc != null)
            System.out.println(doc.toString());
    }
}
