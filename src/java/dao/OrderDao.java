/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Order;

/**
 *
 * @author Alienware 15R3
 */
public class OrderDao {
    
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    public List<Order> getOrder(){
        List<Order> getArrayLists=new ArrayList<>();
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" SELECT * \n" +
                " FROM [Orders]  ");
             rs=ps.executeQuery();
            while(rs.next()){
                Order st=new Order();
                st.setOrderId(rs.getInt("OrderID"));
                st.setAccountId(rs.getInt("AccountID"));
                st.setOrderDate(rs.getDate("OrderDate"));
                getArrayLists.add(st);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }finally{
            DBConnection.close(con, ps, rs);
        }

        return getArrayLists;
    }
    
    public List<Order> getOrders(int accountID){
        List<Order> getArrayLists=new ArrayList<>();
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" SELECT * FROM Orders WHERE AccountID = ?");
             ps.setInt(1, accountID);
             rs=ps.executeQuery();
            while(rs.next()){
                Order st=new Order();
                st.setOrderId(rs.getInt("OrderID"));
                st.setAccountId(rs.getInt("AccountID"));
                st.setOrderDate(rs.getDate("OrderDate"));
                getArrayLists.add(st);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }finally{
            DBConnection.close(con, ps, rs);
        }

        return getArrayLists;
    }
    
     public Order getOrder(int id) {
        Order st=new Order();
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" SELECT * FROM  [Orders]"
                     + " WHERE [OrderID] = ? " );
             ps.setInt(1, id);
             rs=ps.executeQuery();
            while(rs.next()){
                st.setOrderId(rs.getInt("OrderID"));
                st.setAccountId(rs.getInt("AccountID"));
                st.setOrderDate(rs.getDate("OrderDate"));
                return st;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }finally{
            DBConnection.close(con, ps, rs);
        }
        
        return st;
    }
     
     public Order getlastOrder() {
        Order st=new Order();
        try {
             con =DBConnection.open();
             ps= con.prepareStatement("SELECT TOP(1) * FROM Orders ORDER BY OrderID DESC" );
             rs=ps.executeQuery();
            while(rs.next()){
                st.setOrderId(rs.getInt("OrderID"));
                st.setAccountId(rs.getInt("AccountID"));
                st.setOrderDate(rs.getDate("OrderDate"));
                return st;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }finally{
            DBConnection.close(con, ps, rs);
        }
        
        return st;
    }
    
      public void insert(Order st) {
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" Insert into Orders(AccountID,OrderDate) Values (?,?) " );
             ps.setInt(1, st.getAccountId());
             ps.setDate(2, new java.sql.Date(st.getOrderDate().getTime()));
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
    
    public void update(Order st) {
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" UPDATE [Orders] SET [AccountID]=? , [OrderDate]=?   "
                     + " WHERE [OrderID] = ?   " );
             ps.setInt(1, st.getAccountId());
             ps.setDate(2,st.getOrderDate());
             ps.setInt(3, st.getOrderId());
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
             ps= con.prepareStatement(" DELETE FROM [Orders]  "
                     + " WHERE [OrderID]= ?   " );
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
