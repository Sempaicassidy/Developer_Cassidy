package com.cassidy.agromarket.models;

public class Transport {
    private String transport_company;
    private String transport_name;
    private String transport_type;
    private byte [] transport_image;
    private String transport_address;

    public Transport(String transport_company, String transport_name, String transport_type, byte[] transport_image, String transport_address) {
        this.transport_company = transport_company;
        this.transport_name = transport_name;
        this.transport_type = transport_type;
        this.transport_image = transport_image;
        this.transport_address = transport_address;
    }

    public String getTransport_company() {
        return transport_company;
    }

    public void setTransport_company(String transport_company) {
        this.transport_company = transport_company;
    }

    public String getTransport_name() {
        return transport_name;
    }

    public void setTransport_name(String transport_name) {
        this.transport_name = transport_name;
    }

    public String getTransport_type() {
        return transport_type;
    }

    public void setTransport_type(String transport_type) {
        this.transport_type = transport_type;
    }

    public byte[] getTransport_image() {
        return transport_image;
    }

    public void setTransport_image(byte[] transport_image) {
        this.transport_image = transport_image;
    }

    public String getTransport_address() {
        return transport_address;
    }

    public void setTransport_address(String transport_address) {
        this.transport_address = transport_address;
    }
}
