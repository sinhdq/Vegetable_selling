/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import model.bill;
import model.customerBill;

/**
 *
 * @author SINHDQ
 */
public class daoCustomerBill extends DBContext {

    public Vector<customerBill> getBill() {
        String sql = "select a.full_name, b.create_date, b.required_date, b.total, b.status, b.id, b.customer_id from (bill_HE150849 b join account_HE150849 a on b.customer_id = a.id) ";
        Vector<customerBill> vector = new Vector<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String fname = rs.getString(1);
                String cdate = rs.getString(2);
                String rdate = rs.getString(3);
                double total = rs.getDouble(4);
                int status = rs.getInt(5);
                int bid = rs.getInt(6);
                int cusid = rs.getInt(7);
                bill bill = new bill(bid, cusid, total, cdate, rdate, status);
                customerBill cb = new customerBill(bill, fname);
                vector.add(cb);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }
}
