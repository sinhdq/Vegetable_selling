/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author SINHDQ
 */
public class customerBill{
        private bill bill;
        private String cusname;

    public customerBill() {
    }

    public customerBill(bill bill, String cusname) {
        this.bill = bill;
        this.cusname = cusname;
    }

    public bill getBill() {
        return bill;
    }

    public void setBill(bill bill) {
        this.bill = bill;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    @Override
    public String toString() {
        return "customerBill{" + "bill=" + bill + ", cusname=" + cusname + '}';
    }
        
        
}
