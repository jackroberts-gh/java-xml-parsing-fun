package com.home.javaplayground.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by jzr1991 on 22/05/2018.
 */

public class SimpleWikiDoc {

    private final static String WIKI_PARENT_NODE = "page";
    private final static List<String> WIKI_CHILD_NODES = new ArrayList<>(Arrays.asList("title"));

    private List<String> fields = new ArrayList<>();

    public SimpleWikiDoc(List<String> args) {
        for (String item: args) {
            this.fields.add(item);
        }
    }

    public static String getWikiParentNode() { return WIKI_PARENT_NODE; };

    public static List<String> getWikiChildNodes() {
        return WIKI_CHILD_NODES;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String s : fields) {
            sb.append(s);
            sb.append("\t");
        }

        return sb.toString();
    }
}
