package com.example.bazaar.Model;

public class Category {
    String Image;
    String Name;

    public Category() {
    }

    public Category(String image, String name) {
        Image = image;
        Name = name;
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
}
