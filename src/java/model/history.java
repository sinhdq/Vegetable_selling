/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author SINHDQ
 */
public class history {

    private int id;
    private int id_user;
    private String username;
    private String action;
    private String date;

    public history() {
    }

    public history(int id, int id_user, String action, String date) {
        this.id = id;
        this.id_user = id_user;
        this.action = action;
        this.date = date;
    }

    public history(int id_user, String action, String date) {
        this.id_user = id_user;
        this.action = action;
        this.date = date;
    }

    public history(int id, String username, String action, String date) {
        this.id = id;
        this.username = username;
        this.action = action;
        this.date = date;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "history{" + "id=" + id + ", id_user=" + id_user + ", username=" + username + ", action=" + action + ", date=" + date + '}';
    }

    

}
