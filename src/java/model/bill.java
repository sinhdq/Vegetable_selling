/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author SINHDQ
 */
public class bill {
    private int id;
    private int customerID;
    private double total;
    private String createDate;
    private String requiredDate;
    private int status;

    public bill() {
    }

    public bill(int id, int customerID, double total, String createDate, String requiredDate, int status) {
        this.id = id;
        this.customerID = customerID;
        this.total = total;
        this.createDate = createDate;
        this.requiredDate = requiredDate;
        this.status = status;
    }

    public bill(int customerID, double total, String createDate, String requiredDate, int status) {
        this.customerID = customerID;
        this.total = total;
        this.createDate = createDate;
        this.requiredDate = requiredDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public double getTotal() {
        return total;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getRequiredDate() {
        return requiredDate;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setRequiredDate(String requiredDate) {
        this.requiredDate = requiredDate;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "bill{" + "id=" + id + ", customerID=" + customerID + ", total=" + total + ", createDate=" + createDate + ", requiredDate=" + requiredDate + ", status=" + status + '}';
    }
    
    
}
