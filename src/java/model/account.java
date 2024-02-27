/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author SINHDQ
 */
public class account {
    private int id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String username;
    private String password;
    private boolean active;
    private int role;

    public account() {
    }

    public account(int id, String fullName, String email, String phone, String address, String username, String password, boolean active, int role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.username = username;
        this.password = password;
        this.active = active;
        this.role = role;
    }

    public account(String fullName, String email, String phone, String address, String username, String password, boolean active, int role) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.username = username;
        this.password = password;
        this.active = active;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public int getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "account{" + "id=" + id + ", fullName=" + fullName + ", email=" + email + ", phone=" + phone + ", address=" + address + ", username=" + username + ", password=" + password + ", active=" + active + ", role=" + role + '}';
    }

    
    
    
}
