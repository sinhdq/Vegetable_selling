/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author SINHDQ
 */
public class billItem {
    private int itemID;
    private int billID;
    private int productID;
    private int quantity;
    private double totalPrice;

    public billItem() {
    }

    public billItem(int itemID, int billID, int productID, int quantity, double totalPrice) {
        this.itemID = itemID;
        this.billID = billID;
        this.productID = productID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getItemID() {
        return itemID;
    }

    public int getBillID() {
        return billID;
    }

    public int getProductID() {
        return productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "billItem{" + "itemID=" + itemID + ", billID=" + billID + ", productID=" + productID + ", quantity=" + quantity + ", totalPrice=" + totalPrice + '}';
    }
    
    
}
