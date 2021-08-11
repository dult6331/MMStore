/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.DBConnection;
import model.OrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Odetail;

/**
 *
 * @author Alienware 15R3
 */
public class OrderDetailDao {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<OrderDetail> getOrderDatails() {
        List<OrderDetail> getArrayLists = new ArrayList<>();
        try {
            con = DBConnection.open();
            ps = con.prepareStatement(" SELECT * \n"
                    + " FROM [Orders]  ");
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail st = new OrderDetail();
                st.setOrderId(rs.getInt("OrderID"));
                st.setProductId(rs.getInt("ProductID"));
                st.setQuanity(rs.getInt("Quantity"));
                getArrayLists.add(st);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBConnection.close(con, ps, rs);
        }

        return getArrayLists;
    }

    public ArrayList<OrderDetail> getOrderDetailByOrderId(int id) {
        ArrayList<OrderDetail> st = new ArrayList<>();
        try {
            con = DBConnection.open();
            ps = con.prepareStatement(" SELECT * FROM  [OrderDetail]"
                    + " WHERE [OrderID] = ? ");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                st.add(new OrderDetail(rs.getInt("OrderID"), rs.getInt("ProductID"), rs.getInt("Quantity")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBConnection.close(con, ps, rs);
        }
        return st;
    }

    public OrderDetail getOrderDetail(int orderID, int productID) {
        OrderDetail st = new OrderDetail();
        try {
            con = DBConnection.open();
            ps = con.prepareStatement(" SELECT * FROM  [OrderDetail]"
                    + " WHERE [OrderID] = ? AND ProductID = ?");
            ps.setInt(1, orderID);
            ps.setInt(2, productID);
            rs = ps.executeQuery();
            while (rs.next()) {
                st.setOrderId(rs.getInt("OrderID"));
                st.setProductId(rs.getInt("ProductID"));
                st.setQuanity(rs.getInt("Quantity"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBConnection.close(con, ps, rs);
        }
        return st;
    }

    public ArrayList<OrderDetail> getOrderDetailByProductId(int id) {
        ArrayList<OrderDetail> st = new ArrayList<>();
        try {
            con = DBConnection.open();
            ps = con.prepareStatement(" SELECT * FROM  [OrderDetail]"
                    + " WHERE [ProductID] = ? ");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                st.add(new OrderDetail(rs.getInt("OrderID"), rs.getInt("ProductID"), rs.getInt("Quantity")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBConnection.close(con, ps, rs);
        }
        return st;
    }

    public void insert(OrderDetail st) {
        try {
            con = DBConnection.open();
            ps = con.prepareStatement("INSERT INTO OrderDetail (OrderID,ProductID,Quantity) VALUES (?,?,?)");
            ps.setInt(1, st.getOrderId());
            ps.setInt(2, st.getProductId());
            ps.setInt(3, st.getQuanity());
            /*
             update thi khong can tra ve 
             executeUpdate tra ve so ban ghi chiu tac dong cua cau lenh update 
             */
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBConnection.close(con, ps, rs);
        }

    }

    public void update(OrderDetail st) {
        try {
            con = DBConnection.open();
            ps = con.prepareStatement(" UPDATE [OrderDetail] SET [Quantity]=?    "
                    + " WHERE [OrderID] = ? AND ProductID = ?");
            ps.setInt(1, st.getQuanity());
            ps.setInt(2, st.getOrderId());
            ps.setInt(3, st.getProductId());
            /*
             update thi khong can tra ve 
             executeUpdate tra ve so ban ghi chiu tac dong cua cau lenh update 
             */
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBConnection.close(con, ps, rs);
        }
    }

    public void delete(int orderID, int productID) {
        try {
            con = DBConnection.open();
            ps = con.prepareStatement(" DELETE FROM [OrderDetail]  "
                    + " WHERE [OrderID]= ? AND ProductID = ?");
            ps.setInt(1, orderID);
            ps.setInt(2, productID);
            /*
             update thi khong can tra ve 
             executeUpdate tra ve so ban ghi chiu tac dong cua cau lenh update 
             */
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBConnection.close(con, ps, rs);
        }
    }
    public ArrayList<Odetail> getOdetail(int orderId){
        ArrayList<Odetail> lst = new ArrayList<>();
        Odetail st = null;
        try {
            con = DBConnection.open();
            ps = con.prepareStatement("SELECT od.ProductID, p.ProductName, p.UnitsPrice, od.Quantity FROM OrderDetail AS od "
                    + "INNER JOIN Products AS p ON od.ProductID = p.ProductID WHERE od.OrderID= ?");
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                st = new Odetail();
                st.setProductID(rs.getInt("ProductID"));
                st.setProductName(rs.getString("ProductName"));
                st.setUnitsPrice(rs.getLong("UnitsPrice"));
                st.setQuantity(rs.getInt("Quantity"));
                lst.add(st);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBConnection.close(con, ps, rs);
        }
        return lst;
    } 
}
