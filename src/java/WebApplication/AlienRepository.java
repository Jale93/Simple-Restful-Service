/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebApplication;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jale.mammadli
 */
public class AlienRepository {
    List<Alien> aliens;
    Connection con = null;
//    public AlienRepository(){
//        aliens = new ArrayList<>();
//        
//        Alien a1 = new Alien();
//        a1.setName("Jale");
//        a1.setPoints(100);
//        a1.setId(100);
//        
//        Alien a2 = new Alien();
//        a2.setName("Jale1");
//        a2.setPoints(60);
//        a2.setId(101);
//        
//        aliens.add(a1);
//        aliens.add(a2);
//    }
    public AlienRepository(){
        String url = "jdbc:mysql://localhost:3306/restdb";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,username,password);
        } catch (SQLException ex) {
            Logger.getLogger(AlienRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlienRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Alien> getAliens(){
        List<Alien> aliens = new ArrayList<>();
        String sql = "select * from alien";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Alien a = new Alien();
                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setPoints(rs.getInt(3));
                aliens.add(a);
            }
        } catch (Exception ex) {
            Logger.getLogger(AlienRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aliens;
    }
    
    public Alien getAlien(int id){
        String sql = "select * from alien where id="+id ;
        Alien a = new Alien();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setPoints(rs.getInt(3));
                return a;
            }
        } catch (Exception ex) {
            Logger.getLogger(AlienRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void create(Alien a1) {
        String sql = "insert into alien values(?,?,?)";
         try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1,a1.getId());
            st.setString(2,a1.getName());
            st.setInt(3,a1.getPoints());
            st.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(AlienRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(Alien a1) {
        String sql = "update alien set name=?,points=? where id=?";
         try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,a1.getName());
            st.setInt(2,a1.getPoints());
            st.setInt(3,a1.getId());
            st.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(AlienRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int id) {
        String sql = "delete from alien where id=?";
         try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1,id);
            st.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(AlienRepository.class.getName()).log(Level.SEVERE, null, ex);
        }    }
    
}
