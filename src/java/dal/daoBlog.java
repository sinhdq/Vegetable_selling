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
import model.product;

/**
 *
 * @author SINHDQ
 */
public class daoBlog extends DBContext{
    
    
    public void addBlog(blog blog) {

        String sql = "insert into blog_HE150849(id_create, contents, blog_detail, create_date, image, id_category) values(?,?,?,?,?,?)";
        try {
           PreparedStatement pre = conn.prepareStatement(sql);
           pre.setInt(1, blog.getIdCreate());
           pre.setString(2, blog.getContents());
           pre.setString(3, blog.getBlogDetails());
           pre.setString(4, blog.getCreateDate());
           pre.setString(5, blog.getImage());
           pre.setInt(6, blog.getId_category());
           pre.executeUpdate();
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
        //show all product
    public Vector<blog> showBlog(String input) {
        String sql = "select * from blog_HE150849 b where b.contents like '%"+input+"%'";
        Vector<blog> vector = new Vector<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int idc = rs.getInt(2);
                String content = rs.getString(3);
                String detail = rs.getString(4);
                String date = rs.getString(5);
                String image = rs.getString(6);
                int id_cate = rs.getInt(7);
                blog blog = new blog(id, idc, content, detail, date, image, id_cate);
                vector.add(blog);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }
    
        public void updateBlog(blog blog) {
        String sql = "update blog_HE150849 set id_create = ?, contents = ?, blog_detail = ?, create_date = ?, image = ?, id_category = ? where id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
           pre.setInt(1, blog.getIdCreate());
           pre.setString(2, blog.getContents());
           pre.setString(3, blog.getBlogDetails());
           pre.setString(4, blog.getCreateDate());
           pre.setString(5, blog.getImage());
           pre.setInt(6, blog.getId_category());
           pre.setInt(7, blog.getId());
           pre.executeUpdate();
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
        public void deleteBlog(int id) {

        String sql = "delete from blog_HE150849 where id =" + id;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
        
    //show all product
    public Vector<blog> getBlogByNameOrCategory(int idblog, String scontent) {
        String sql = "select b.id, b.id_create, b.contents, b.blog_detail, b.create_date, b.image, b.id_category \n" +
                     "from blog_HE150849 b join category_HE150849 c on b.id_category = c.id \n" +
                     "where c.id like '%"+idblog+"%' and b.contents like '%"+scontent+"%'";
        Vector<blog> vector = new Vector<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int idc = rs.getInt(2);
                String content = rs.getString(3);
                String detail = rs.getString(4);
                String date = rs.getString(5);
                String image = rs.getString(6);
                int id_cate = rs.getInt(7);
                blog blog = new blog(id, idc, content, detail, date, image, id_cate);
                vector.add(blog);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }
    
}
