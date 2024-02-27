/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import model.billItem;

/**
 *
 * @author SINHDQ
 */
public class daoBillItem extends DBContext {

    public void addItem(billItem item) {

        String sql = "insert into billitem_HE150849(item_id, bill_id, product_id, quantity, total_price) values(?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, item.getItemID());
            pre.setInt(2, item.getBillID());
            pre.setInt(3, item.getProductID());
            pre.setInt(4, item.getQuantity());
            pre.setDouble(5, item.getTotalPrice());
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Vector<billItem> showBillItem() {
        String sql = "select * from billitem_HE150849";
        Vector<billItem> vector = null;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) { 
                int itemid = rs.getInt(1);
                int billid = rs.getInt(2);
                int proid = rs.getInt(3);
                int quan = rs.getInt(4);
                Double price = rs.getDouble(5);
                billItem item = new billItem(itemid, billid, proid, quan, price);
                vector.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }

    public void updateBillItem(billItem item) {
        String sql = "update billitem_HE150849 set quantity = ?, total_price = ? where item_id = ? and bill_id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, item.getQuantity());
            pre.setDouble(2, item.getTotalPrice());
            pre.setInt(3, item.getItemID());
            pre.setInt(4, item.getBillID());
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public billItem getBillItem(int iid, int bid) {
        String sql = "select * from billItems_HE150849 where itemID = " + iid + " and billID =" + bid;
        billItem item = null;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int itemid = rs.getInt(1);
                int billid = rs.getInt(2);
                int proid = rs.getInt(3);
                int quan = rs.getInt(4);
                Double price = rs.getDouble(5);
                item = new billItem(itemid, billid, proid, quan, price);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    public billItem getBillItemByBillidAndProductid(int bid, int pid) {
        String sql = "select * from billitem_HE150849 where bill_id = "+bid+" and product_id = "+pid;
        billItem item = null;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) { 
                int itemid = rs.getInt(1);
                int billid = rs.getInt(2);
                int proid = rs.getInt(3);
                int quan = rs.getInt(4);
                Double price = rs.getDouble(5);
                 item = new billItem(itemid, billid, proid, quan, price);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    public void deleteBillItem(int proid, int billid) {
        String sql = "delete from billitem_HE150849 where product_id = "+proid+" and bill_id = "+billid;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       public void deleteBillItemBillIDandItemid(int iid, int bid) {
        String sql = "delete from billitem_HE150849 where item_id = "+iid+" and bill_id = "+bid;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int CountItemByBillId(int bid) {
        String sql = "select * from billitem_HE150849 where bill_id = "+bid+" order by item_id DESC";
        int count = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                count = rs.getInt(1);
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public double TotalPriceByBillId(int bid) {
        String sql = "select total_price from billitem_HE150849 where bill_id =" + bid;
        double total = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Double price = rs.getDouble(1);
                total += price;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
    
       public int getQuantityProduct(int bid) {
        String sql = "select quantity from billitem_HE150849  where bill_id =" + bid;
        int quantities = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            int quantity = rs.getInt(1);
            quantities+=quantity;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantities;
    }

    public static void main(String[] args) {
        daoBillItem dao = new daoBillItem();
        //System.out.println(dao.TotalPriceByBillId(29));
        System.out.println(dao.CountItemByBillId(2));
    }
}
