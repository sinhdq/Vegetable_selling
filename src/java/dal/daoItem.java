/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import model.item;
import model.product;

/**
 *
 * @author SINHDQ
 */
public class daoItem extends DBContext {

    public Vector<item> listCart(int cid) {
        String sql = "select p.id, p.depart_id, p.product_name, p.price, p.quantity, p.image, p.description, bi.quantity \n"
                + "from ((bill_HE150849 b join billitem_HE150849 bi on b.id = bi.bill_id) join product_HE150849 p on bi.product_id = p.id) \n"
                + "where customer_id = " + cid+" and b.status = 0";
        Vector<item> vector = new Vector<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int departId = rs.getInt(2);
                String name = rs.getString(3);
                Double price = rs.getDouble(4);
                int quantity = rs.getInt(5);
                String image = rs.getString(6);
                String des = rs.getString(7);
                int quantitycart = rs.getInt(8);
                product pro = new product(id, departId, name, price, quantity, image, des);
                item i = new item(pro, quantitycart);
                vector.add(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }
    
    public Vector<item> getCartByBillIdAndCusId(int bid, int cusid) {
        String sql = "select p.id, p.depart_id, p.product_name, p.price, p.quantity, p.image, p.description, bi.quantity \n" +
"                from ((bill_HE150849 b join billitem_HE150849 bi on b.id = bi.bill_id) join product_HE150849 p on bi.product_id = p.id) \n" +
"                where b.customer_id = "+cusid+" and b.id = "+bid;
        Vector<item> vector = new Vector<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int departId = rs.getInt(2);
                String name = rs.getString(3);
                Double price = rs.getDouble(4);
                int quantity = rs.getInt(5);
                String image = rs.getString(6);
                String des = rs.getString(7);
                int quantitycart = rs.getInt(8);
                product pro = new product(id, departId, name, price, quantity, image, des);
                item i = new item(pro, quantitycart);
                vector.add(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }


    public static void main(String[] args) {
        daoItem dao = new daoItem();
        Vector<item> list = dao.listCart(3);
        for (item o : list) {
            System.out.println(o.getProduct().toString());
        }
    }
}
