/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author SINHDQ
 */
public class department {
    private int id;
    private String departName;

    public department() {
    }

    public department(int id, String departName) {
        this.id = id;
        this.departName = departName;
    }

    public department(String departName) {
        this.departName = departName;
    }

    public int getId() {
        return id;
    }

    public String getDepartName() {
        return departName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    @Override
    public String toString() {
        return "department{" + "id=" + id + ", departName=" + departName + '}';
    }
    
    
}
