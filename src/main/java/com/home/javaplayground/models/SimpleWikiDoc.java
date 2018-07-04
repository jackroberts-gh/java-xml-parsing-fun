package com.home.javaplayground.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jzr1991 on 22/05/2018.
 */

public class SimpleWikiDoc<T> implements IDoc {

    private final static String WIKI_PARENT_NODE = "page";
    private final static List<String> WIKI_CHILD_NODES = new ArrayList<>(Arrays.asList("title"));

    private List<T> fields = new ArrayList<>();

    public SimpleWikiDoc(List<T> args) {
        for (T item: args) {
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

        for (T s : fields) {
            sb.append(s);
            sb.append("\t");
        }

        return sb.toString();
    }
}
