/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import model.department;
import model.product;

/**
 *
 * @author SINHDQ
 */
public class daoDepartment extends DBContext{
        //show all product
    public Vector<department> showDepart() {
        String sql = "select * from department_HE150849";
        Vector<department> vector = new Vector<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                department depart = new department(id, name);
                vector.add(depart);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }
    
       public department getDepartById(int idd){
        String sql = "select * from department_HE150849 where id = "+idd;
        department depart = null;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                depart = new department(id, name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return depart;
    }
       public void deleteDepartment(int id) {

        String sql = "delete from department_HE150849 where id = " + id;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       public void addDepartment(department depart) {

        String sql = "insert into department_HE150849(depart_name) values(?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
           pre.setString(1, depart.getDepartName());
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
