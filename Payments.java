package com.cassidy.agromarket.models;

public class Payments {
    private int total_items;
    private int delivery_charges;
    private int productPrice;
    private String total_amount_payable;
    private String payment_gateway;

    public Payments(int total_items, int delivery_charges, int productPrice, String total_amount_payable, String payment_gateway) {
        this.total_items = total_items;
        this.delivery_charges = delivery_charges;
        this.productPrice = productPrice;
        this.total_amount_payable = total_amount_payable;
        this.payment_gateway = payment_gateway;
    }


    public int getTotal_items() {
        return total_items;
    }

    public void setTotal_items(int total_items) {
        this.total_items = total_items;
    }

    public int getDelivery_charges() {
        return delivery_charges;
    }

    public void setDelivery_charges(int delivery_charges) {
        this.delivery_charges = delivery_charges;
    }

    public String getTotal_amount_payable() {
        return total_amount_payable;
    }

    public void setTotal_amount_payable(String total_amount_payable) {
        this.total_amount_payable = total_amount_payable;
    }

    public String getPayment_gateway() {
        return payment_gateway;
    }

    public void setPayment_gateway(String payment_gateway) {
        this.payment_gateway = payment_gateway;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}
