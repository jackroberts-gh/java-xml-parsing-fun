package com.home.javaplayground;

/**
 * Created by Jack on 22/05/2018.
 */
public class Entry {

    private String title;
    private String content;

    public Entry(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return this.title + this.content;
    }
}
