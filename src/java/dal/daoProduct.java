/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import model.product;

/**
 *
 * @author SINHDQ
 */
public class daoProduct extends DBContext {

    //add new product
    public void addProduct(product pro) {

        String sql = "insert into product_HE150849(depart_id, product_name,price,quantity,image,description) values(?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
           pre.setInt(1, pro.getDepartID());
           pre.setString(2, pro.getProductName());
           pre.setDouble(3, pro.getPrice());
           pre.setInt(4, pro.getQuantity());
           pre.setString(5, pro.getImage());
           pre.setString(6, pro.getDescription());
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //show all product
    public Vector<product> showProduct() {
        String sql = "select * from product_HE150849";
        Vector<product> vector = new Vector<>();
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
               product pro = new product(id, departId, name, price, quantity, image, des);
                vector.add(pro);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }

//    //show all product
//    public Vector<product> showProductByStoreID(int idstore) {
//        String sql = "select pro.proID, pro.proName, pro.price, pro.image, pro.description \n"
//                + "from ((stocks_HE150849 stock join stores_HE150849 store on stock.storeID = store.storeID) join products_HE150849 pro on pro.proID = stock.productID) \n"
//                + "where store.storeID =" + idstore;
//        Vector<product> vector = new Vector<>();
//        try {
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                int id = rs.getInt(1);
//                String name = rs.getString(2);
//                Double price = rs.getDouble(3);
//                String image = rs.getString(4);
//                String des = rs.getString(5);
//                product pro = new product(id, name, price, image, des);
//                vector.add(pro);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return vector;
//    }

    //update product
    public void updateProduct(product pro) {
        String sql = "update product_HE150849 set depart_id = ?, product_name = ?, price = ?, quantity = ?, image = ?, description = ? where id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, pro.getDepartID());
            pre.setString(2, pro.getProductName());
            pre.setDouble(3, pro.getPrice());
            pre.setInt(4, pro.getQuantity());
            pre.setString(5, pro.getImage());
            pre.setString(6, pro.getDescription());
            pre.setInt(7, pro.getId());
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //delete product
    public void deleteProduct(int id) {

        String sql = "delete from product_HE150849 where id = " + id;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
//    public Vector<product> FindByName(String fname, int storeid) {
//        String sql = "select pro.proID, pro.proName, pro.price, pro.image, pro.description \n"
//                + "from ((stocks_HE150849 stock join stores_HE150849 store on stock.storeID = store.storeID) join products_HE150849 pro on pro.proID = stock.productID) \n"
//                + "where store.storeID = " + storeid + " and pro.proName like '%" + fname + "%'";
//        Vector<product> vector = new Vector<>();
//        try {
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                int id = rs.getInt(1);
//                String name = rs.getString(2);
//                Double price = rs.getDouble(3);
//                String image = rs.getString(4);
//                String des = rs.getString(5);
//                product pro = new product(id, name, price, image, des);
//                vector.add(pro);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return vector;
//    }

    public product FindById(int fid) {
        String sql = "select * from product_HE150849 where id =" + fid;
        product pro = null;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int id = rs.getInt(1);
                int departId = rs.getInt(2);
                String name = rs.getString(3);
                Double price = rs.getDouble(4);
                int quantity = rs.getInt(5);
                String image = rs.getString(6);
                String des = rs.getString(7);
               pro = new product(id, departId, name, price, quantity, image, des);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pro;
    }

//    public product FindProductInCartById(int sid, int cid, int pid) {
//        String sql = "select p.proID, p.proName, p.price, p.image, p.description\n" +
//"from ((bills_HE150849 b join billItems_HE150849 bi on b.billID = bi.billID) join products_HE150849 p on bi.productID = p.proID)\n" +
//"where b.storeID = "+sid+" and b.customerID = "+cid+" and bi.productID = "+pid;
//        product pro = null;
//        try {
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            if (rs.next()) {
//                int id = rs.getInt(1);
//                String name = rs.getString(2);
//                Double price = rs.getDouble(3);
//                String image = rs.getString(4);
//                String des = rs.getString(5);
//                pro = new product(id, name, price, image, des);
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return pro;
//    }
       public Vector<product> getProductByNameAndDepart(String proname, String departname) {
        String sql = "select p.id, p.depart_id, p.product_name, p.price, p.quantity, p.image, p.description \n" +
"from (product_HE150849 p join department_HE150849 d on p.depart_id = d.id) \n" +
"where p.product_name like '%"+proname+"%' and d.depart_name like '%"+departname+"%'";
        Vector<product> vector = new Vector<>();
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
               product pro = new product(id, departId, name, price, quantity, image, des);
                vector.add(pro);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }

    public static void main(String[] args) {
        daoProduct dao = new daoProduct();

    }
}
