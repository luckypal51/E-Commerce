package com.example.bazaar.Model;

public class Order {
    String Address;
    String Phone_no;
    String Product_Name;
    String Product_price;

    public Order() {
    }

    public Order(String address, String phone_no, String product_Name, String product_price) {
        Address = address;
        Phone_no = phone_no;
        Product_Name = product_Name;
        Product_price = product_price;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone_no() {
        return Phone_no;
    }

    public void setPhone_no(String phone_no) {
        Phone_no = phone_no;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public void setProduct_Name(String product_Name) {
        Product_Name = product_Name;
    }

    public String getProduct_price() {
        return Product_price;
    }

    public void setProduct_price(String product_price) {
        Product_price = product_price;
    }
}
