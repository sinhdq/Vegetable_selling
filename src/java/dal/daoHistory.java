/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import model.account;
import model.history;

/**
 *
 * @author SINHDQ
 */
public class daoHistory extends DBContext {

    public Vector<history> showHisByUserName(String input) {
        String sql = "select h.id, a.username, h.history_name, h.date_action from history_HE150849 h, account_HE150849 a \n" +
"where h.id_user = a.id\n" +
"and a.username like '%"+input+"%'";
        Vector<history> vector = new Vector<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String action = rs.getString(3);
                String date = rs.getString(4);
                history his = new history(id, username, action, date);
                vector.add(his);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }

    public void addAccount(history his) {
        String sql = "insert into history_HE150849(id_user, history_name, date_action) values(?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, his.getId_user());
            pre.setString(2, his.getAction());
            pre.setString(3, his.getDate());
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeHistory(account acc, String action) {
        DateFormat df = new SimpleDateFormat("hh:mm:ss MM/dd/yyyy");
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String datenow = df.format(date);
        history his = new history(acc.getId(), action, datenow);
        addAccount(his);
    }

}
