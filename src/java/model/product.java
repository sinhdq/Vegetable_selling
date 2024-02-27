/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author SINHDQ
 */
public class product {
    private int id;
    private int departID;
    private String productName;
    private double price;
    private int quantity;
    private String image;
    private String description;

    public product() {
    }

    public product(int departID, String productName, double price, int quantity, String image, String description) {
        this.departID = departID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
    }
    

    public product(int id, int departID, String productName, double price, int quantity, String image, String description) {
        this.id = id;
        this.departID = departID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getDepartID() {
        return departID;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDepartID(int departID) {
        this.departID = departID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "product{" + "id=" + id + ", departID=" + departID + ", productName=" + productName + ", price=" + price + ", quantity=" + quantity + ", image=" + image + ", description=" + description + '}';
    }

    
    
    
}
