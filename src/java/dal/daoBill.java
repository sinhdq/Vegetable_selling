/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import model.bill;
import model.product;

/**
 *
 * @author SINHDQ
 */
public class daoBill extends DBContext{
    
    public void addBill(bill bill){
    
        String sql = "insert into bill_HE150849(customer_id, total, create_date, required_date, status) values(?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, bill.getCustomerID());
            pre.setDouble(2, bill.getTotal());
            pre.setString(3, bill.getCreateDate());
            pre.setString(4, bill.getRequiredDate());
            pre.setInt(5, bill.getStatus());
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Vector<bill> showBill(){
    String sql = "select * from bill_HE150849";
      Vector<bill> vector = new Vector<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int billid = rs.getInt(1);
                int cusid = rs.getInt(2);
                double total = rs.getDouble(3);
                String cdate = rs.getString(4);
                String rdate = rs.getString(5);
                int status = rs.getInt(6);
               bill bill = new bill(billid, cusid, total, cdate, rdate, status);
               vector.add(bill);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }
    
     public bill getBillByCusIdAndStatus(int cid, int sts){
    String sql = "select * from bill_HE150849 where customer_id = "+cid+" and status = "+sts;
      bill b = null;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                int billid = rs.getInt(1);
                int cusid = rs.getInt(2);
                double total = rs.getDouble(3);
                String cdate = rs.getString(4);
                String rdate = rs.getString(5);
                int status = rs.getInt(6);
               b = new bill(billid, cusid, total, cdate, rdate, status);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

     public void updateBill(bill bill){
    
        String sql = "update bill_HE150849 set total = ?, required_date = ?, status = ? where id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setDouble(1, bill.getTotal());
            pre.setString(2, bill.getRequiredDate());
            pre.setInt(3, bill.getStatus());
            pre.setInt(4, bill.getId());
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public static void main(String[] args) {
       daoBill dao = new daoBill();
//         Vector<bill> vc = dao.showBill();
//         for (bill o : vc) {
//             System.out.println(o.toString());
//         }
       // bill bll = new bill(10, 2, 1, 26000, "2022-10-10", 0);
       //dao.addBill(bll);
     // System.out.print(dao.getBill(1, 1).toString());
     bill bill = new bill(1, 3,40000, "2022-10-19","",0);
     dao.updateBill(bill);
          
    }
    
}
