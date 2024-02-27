/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author SINHDQ
 */
public class contact {
    private int id;
    private String fullName;
    private String email;
    private String message;
    private boolean status;
    private int idaccount;

    public contact() {
    }

    public contact(int id, String fullName, String email, String message, boolean status, int idaccount) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.message = message;
        this.status = status;
        this.idaccount = idaccount;
    }

    public contact(String fullName, String email, String message, boolean status, int idaccount) {
        this.fullName = fullName;
        this.email = email;
        this.message = message;
        this.status = status;
        this.idaccount = idaccount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdaccount() {
        return idaccount;
    }

    public void setIdaccount(int idaccount) {
        this.idaccount = idaccount;
    }

    @Override
    public String toString() {
        return "contact{" + "id=" + id + ", fullName=" + fullName + ", email=" + email + ", message=" + message + ", status=" + status + ", idaccount=" + idaccount + '}';
    }

    
   
    
}
