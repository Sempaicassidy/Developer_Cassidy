package com.cassidy.agromarket.models;

public class Analysis {
    private  String Demand;
    private String Supply;
    private String MarketName;

    public byte[] getDocument() {
        return Document;
    }

    public void setDocument(byte[] document) {
        Document = document;
    }

    private byte [] Document;

    public Analysis(String demand, String supply, String marketName, byte[] document) {
        Demand = demand;
        Supply = supply;
        MarketName = marketName;
        Document = document;
    }
    public String getDemand() {
        return Demand;
    }

    public void setDemand(String demand) {
        Demand = demand;
    }

    public String getSupply() {
        return Supply;
    }

    public void setSupply(String supply) {
        Supply = supply;
    }

    public String getMarketName() {
        return MarketName;
    }

    public void setMarketName(String marketName) {
        MarketName = marketName;
    }

}
