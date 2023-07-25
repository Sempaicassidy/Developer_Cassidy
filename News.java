package com.cassidy.agromarket.models;

public class News {
    private String title;
    private String date;

    private byte[] document;

    public News(String title, String date, byte[] document) {

        this.title = title;
        this.date = date;

        this.document = document;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }
}

