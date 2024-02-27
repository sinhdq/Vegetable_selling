/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import model.account;

/**
 *
 * @author SINHDQ
 */
public class daoAccount extends DBContext {

    public Vector<account> showAccount() {
        String sql = "select * from account_HE150849";
        Vector<account> vector = new Vector<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String phone = rs.getString(4);
                String address = rs.getString(5);
                String user = rs.getString(6);
                String pass = rs.getString(7);
                boolean acitive = rs.getBoolean(8);
                int role = rs.getInt(9);
                account acc = new account(id, name, email, phone, address, user, pass, acitive, role);
                vector.add(acc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }

    public void addAccount(account acc) {

        String sql = "insert into account_HE150849(full_name, email, phone,address,username,password,active,role) values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, acc.getFullName());
            pre.setString(2, acc.getEmail());
            pre.setString(3, acc.getPhone());
            pre.setString(4, acc.getAddress());
            pre.setString(5, acc.getUsername());
            pre.setString(6, acc.getPassword());
            pre.setBoolean(7, acc.isActive());
            pre.setInt(8, acc.getRole());
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAccount(account acc) {
        String sql = "update account_HE150849 set full_name = ?, email = ?, phone = ?, address = ?, username = ?, password = ?, active = ?, role = ? where id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
           pre.setString(1, acc.getFullName());
            pre.setString(2, acc.getEmail());
            pre.setString(3, acc.getPhone());
            pre.setString(4, acc.getAddress());
            pre.setString(5, acc.getUsername());
            pre.setString(6, acc.getPassword());
            pre.setBoolean(7, acc.isActive());
            pre.setInt(8, acc.getRole());
            pre.setInt(9, acc.getId());
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
    
     public void deleteProduct(int id) {

        String sql = "delete from account_HE150849 where id = " + id;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
         public account getAccountByUserPass(String username, String password) {
        String sql = "select * from account_HE150849 where username = '"+username+"' and password ='"+password+"'";
        account acc = null;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String phone = rs.getString(4);
                String address = rs.getString(5);
                String user = rs.getString(6);
                String pass = rs.getString(7);
                boolean acitive = rs.getBoolean(8);
                int role = rs.getInt(9);
                acc = new account(id, name, email, phone, address, user, pass, acitive, role);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return acc;
    }
     public account getAccountByUserMail(String username, String mail) {
        String sql = "select * from account_HE150849 where username = '"+username+"' and email = '"+mail+"'";
        account acc = null;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String phone = rs.getString(4);
                String address = rs.getString(5);
                String user = rs.getString(6);
                String pass = rs.getString(7);
                boolean acitive = rs.getBoolean(8);
                int role = rs.getInt(9);
                acc = new account(id, name, email, phone, address, user, pass, acitive, role);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return acc;
    }
       public Vector<account> getAccountByName(String fname) {
        String sql = "select * from account_HE150849 where full_name like '%"+fname+"%' and role = 1";
        Vector<account> vector = new Vector<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String phone = rs.getString(4);
                String address = rs.getString(5);
                String user = rs.getString(6);
                String pass = rs.getString(7);
                boolean acitive = rs.getBoolean(8);
                int role = rs.getInt(9);
                account acc = new account(id, name, email, phone, address, user, pass, acitive, role);
                vector.add(acc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }
       public account getAccountById(int aid) {
        String sql = "select * from account_HE150849 where id = "+aid;
        account acc = null;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String phone = rs.getString(4);
                String address = rs.getString(5);
                String user = rs.getString(6);
                String pass = rs.getString(7);
                boolean acitive = rs.getBoolean(8);
                int role = rs.getInt(9);
                 acc = new account(id, name, email, phone, address, user, pass, acitive, role);
             
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return acc;
    }

    public static void main(String[] args) {
        daoAccount dao = new daoAccount();
//        Vector<account> vc = dao.showAccount();
//        for (account o : vc) {
//            System.out.println(o.toString());
//        }
          // dao.addAccount("");

    }
}
