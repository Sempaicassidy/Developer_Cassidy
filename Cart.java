package com.cassidy.agromarket.models;

public class Cart {
    private Integer CartId;
    private String productName;
    private String price;
    private String description;
    private byte [] image;
    private String category;

    public Cart(int cartId, String productName, String price, String description, byte [] image, String category, String location, String seller) {
        CartId = cartId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.image = image;
        this.category = category;
        this.location = location;
    }
    private String location;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getCartId() {
        return CartId;
    }

    public void setCartId(Integer cartId) {
        CartId = cartId;
    }
}