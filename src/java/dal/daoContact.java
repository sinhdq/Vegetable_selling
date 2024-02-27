/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import model.blog;
import model.contact;
import model.product;

/**
 *
 * @author SINHDQ
 */
public class daoContact extends DBContext{
    
    public void addContact(contact ct) {
        String sql = "insert into contact_HE150849(full_name, email, message, status, idaccount) values(?,?,?,?,?)";
        try {
           PreparedStatement pre = conn.prepareStatement(sql);
           pre.setString(1, ct.getFullName());
           pre.setString(2, ct.getEmail());
           pre.setString(3, ct.getMessage());
           pre.setBoolean(4, ct.isStatus());
           pre.setInt(5, ct.getIdaccount());
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Vector<contact> showContact() {
        String sql = "select * from contact_HE150849";
        Vector<contact> vector = new Vector<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String mail = rs.getString(3);
                String mess = rs.getString(4);
                boolean status = rs.getBoolean(5);
                int idaccount = rs.getInt(6);
                contact ct = new contact(id, name, mail, mess, status, idaccount);
                vector.add(ct);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }
    
     public void updateContact(contact ct) {
        String sql = "update contact_HE150849 set full_name = ?, email = ?, message = ?, status = ?, idaccount = ? where id = ?";
        try {
           PreparedStatement pre = conn.prepareStatement(sql);
           pre.setString(1, ct.getFullName());
           pre.setString(2, ct.getEmail());
           pre.setString(3, ct.getMessage());
           pre.setBoolean(4, ct.isStatus());
           pre.setInt(5, ct.getIdaccount());
           pre.setInt(6, ct.getId());
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     public Vector<contact> getContactByName(String fname) {
        String sql = "select * from contact_HE150849 where full_name like '%"+fname+"%'";
        Vector<contact> vector = new Vector<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String mail = rs.getString(3);
                String mess = rs.getString(4);
                boolean status = rs.getBoolean(5);
                int idaccount = rs.getInt(6);
                contact ct = new contact(id, name, mail, mess, status, idaccount);
                vector.add(ct);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }
      public void deleteContact(int id) {

        String sql = "delete from contact_HE150849 where id =" + id;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      public contact getContactById(int cid) {
        String sql = "select * from contact_HE150849 where id = "+cid;
        contact ct = null;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String mail = rs.getString(3);
                String mess = rs.getString(4);
                boolean status = rs.getBoolean(5);
                int idaccount = rs.getInt(6);
                ct = new contact(id, name, mail, mess, status, idaccount);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ct;
    }
          public static void main(String[] args) {
       daoContact dao = new daoContact();
              System.out.println(dao.getContactById(3).toString());

    }
    
}
