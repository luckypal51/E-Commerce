package com.example.bazaar.Model;

public class Product {
    String Image;
    String Name;
    String  Price;

    public Product() {
    }

    public Product(String image, String name, String  price) {
        Image = image;
        Name = name;
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String  getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
