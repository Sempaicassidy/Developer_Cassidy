package com.cassidy.agromarket.models;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class OrderProducts implements Parcelable {

    private String productName;
    private int price;
    private String sellerName;
    private byte[] image;
    private int orderId;
    private int productQuantity;

    public OrderProducts(String productName, int price, String sellerName, byte[] image, int orderId, int productQuantity) {
        this.productName = productName;
        this.price = price;
        this.sellerName = sellerName;
        this.image = image;
        this.orderId = orderId;
        this.productQuantity = productQuantity;
    }

    protected OrderProducts(Parcel in) {
        productName = in.readString();
        price = in.readInt();
        sellerName = in.readString();
        image = in.createByteArray();
        orderId = in.readInt();
        productQuantity = in.readInt();
    }

    public static final Creator<OrderProducts> CREATOR = new Creator<OrderProducts>() {
        @Override
        public OrderProducts createFromParcel(Parcel in) {
            return new OrderProducts(in);
        }

        @Override
        public OrderProducts[] newArray(int size) {
            return new OrderProducts[size];
        }
    };

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(){this.price = price;}



    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    // Other methods and getters/setters as needed

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeInt(price);
        dest.writeString(sellerName);
        dest.writeByteArray(image);
        dest.writeInt(orderId);
        dest.writeInt(productQuantity);
    }
}
