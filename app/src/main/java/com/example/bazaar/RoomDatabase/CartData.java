package com.example.bazaar.RoomDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Cart")
public class CartData {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "image")
    private String Image;

    @ColumnInfo(name = "product_name")
    private String productName;

    @ColumnInfo(name = "quantity")
    private int quantity;

    @ColumnInfo(name = "price")
    private double price;

    // Constructors, getters, and setters...



// Constructor, Getters and Setters

    public CartData() {
    }

    public CartData(String image, String productName, int quantity, double price) {
        this.Image = image;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }


//    public CartData(int id, String productName, int quantity, double price) {
//        this.id = id;
//        this.productName = productName;
//        this.quantity = quantity;
//        this.price = price;
//    }

    public String toString() {
        return "CartData{" +
                "name='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
