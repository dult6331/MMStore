/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.DBConnection;
import model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alienware 15R3
 */
public class AccountDao {
    
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    public List<Account> getAccounts(){
        List<Account> getArrayLists=new ArrayList<>();
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" SELECT * \n" +
                " FROM Accounts  ");
             rs=ps.executeQuery();
            while(rs.next()){
                Account st=new Account();
                st.setAcountId(rs.getInt("AccountID"));
                st.setUserName(rs.getString("UserName"));
                st.setPassWord(rs.getString("PassWord"));
                st.setIsAdministrator(rs.getBoolean("IsAdministrator"));
                getArrayLists.add(st);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }finally{
            DBConnection.close(con, ps, rs);
        }

        return getArrayLists;
    }
    
     public Account getAccount(int id) {
        Account st=null;
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" SELECT * FROM  Accounts"
                     + " WHERE AccountID = ? " );
             ps.setInt(1, id);
             rs=ps.executeQuery();
            while(rs.next()){
                st= new Account();
                st.setAcountId(rs.getInt("AccountID"));
                st.setUserName(rs.getString("UserName"));
                st.setPassWord(rs.getString("PassWord"));
                st.setIsAdministrator(rs.getBoolean("IsAdministrator"));
                return st;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }finally{
            DBConnection.close(con, ps, rs);
        }
        
        return st;
    }
     public Account getAccount(String username) {
        Account st=null;
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" SELECT * FROM  Accounts"
                     + " WHERE Username = ? " );
             ps.setString(1, username);
             rs=ps.executeQuery();
            while(rs.next()){
                st= new Account();
                st.setAcountId(rs.getInt("AccountID"));
                st.setUserName(rs.getString("UserName"));
                st.setPassWord(rs.getString("PassWord"));
                st.setIsAdministrator(rs.getBoolean("IsAdministrator"));
                return st;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }finally{
            DBConnection.close(con, ps, rs);
        }
        
        return st;
    }
    
      public void insert(Account st) {
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" Insert into Accounts(Username,[Password],IsAdministrator) values (?,?,?)  " );
             ps.setString(1, st.getUserName());
             ps.setString(2, st.getPassWord());
             ps.setBoolean(3, st.isIsAdministrator());
             /*
             update thi khong can tra ve 
             executeUpdate tra ve so ban ghi chiu tac dong cua cau lenh update 
             */
             ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }finally{
            DBConnection.close(con, ps, rs);
        }
        
    }
    
    public void update(Account st) {
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" UPDATE Accounts SET UserName=? , Password=?  , IsAdministrator=?  "
                     + " WHERE AccountID = ?   " );
             ps.setString(1, st.getUserName());
             ps.setString(2,st.getPassWord());
             ps.setBoolean(3, st.isIsAdministrator());
             ps.setInt(4, st.getAcountId());
             /*
             update thi khong can tra ve 
             executeUpdate tra ve so ban ghi chiu tac dong cua cau lenh update 
             */
             ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }finally{
            DBConnection.close(con, ps, rs);
        }
    }

    public void delete(int id) {
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" DELETE FROM Accounts  "
                     + " WHERE AccountID= ?   " );
             ps.setInt(1, id);
             /*
             update thi khong can tra ve 
             executeUpdate tra ve so ban ghi chiu tac dong cua cau lenh update 
             */
             ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }finally{
            DBConnection.close(con, ps, rs);
        }
    }
    
}
