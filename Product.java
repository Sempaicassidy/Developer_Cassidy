package com.cassidy.agromarket.models;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class Product implements Parcelable {
        private int id;
        private String productName;
        private String sellerName;
        private String productLocation;
        private String productCategory;
        private String productDescription;
        private String productPrice;
        private byte[] imageBytes;
    private String quantity;

    public Product(int id, byte[]imageBytes, String productName, String productPrice, String productDescription, String productLocation,
                    String productCategory, String sellerName ) {
        this.id = id;
        this.imageBytes = imageBytes;
        this.productName = productName;
        this.sellerName = sellerName;
        this.productLocation = productLocation;
        this.productCategory = productCategory;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    protected Product(Parcel in) {
        id = in.readInt();
        productName = in.readString();
        sellerName = in.readString();
        productLocation = in.readString();
        productCategory = in.readString();
        productDescription = in.readString();
        productPrice = in.readString();
        imageBytes = in.readBlob();
        quantity = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(String productLocation) {
        this.productLocation = productLocation;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(productName);
        dest.writeString(sellerName);
        dest.writeString(productLocation);
        dest.writeString(productCategory);
        dest.writeString(productDescription);
        dest.writeString(productPrice);
        dest.writeBlob(imageBytes);
        dest.writeString(String.valueOf(quantity));
    }
}

