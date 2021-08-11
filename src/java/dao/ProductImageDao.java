/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.DBConnection;
import model.ProductImage;
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
public class ProductImageDao {
     private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    public List<ProductImage> getProductImages(){
        List<ProductImage> getArrayLists=new ArrayList<>();
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" SELECT * \n" +
                " FROM [ProductImages]  ");
             rs=ps.executeQuery();
            while(rs.next()){
                ProductImage st=new ProductImage();
                st.setProductId(rs.getInt("ProductID"));
                st.setImageUrl(rs.getString("ImageUrl"));
                st.setImageId(rs.getInt("ImageID"));
                getArrayLists.add(st);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }finally{
            DBConnection.close(con, ps, rs);
        }

        return getArrayLists;
    }
    
     public ArrayList<ProductImage> getProductImages (int id) {
        ArrayList<ProductImage> st=new ArrayList<>();
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" SELECT * FROM  [ProductImages]"
                     + " WHERE [ProductID] = ? " );
             ps.setInt(1, id);
             rs=ps.executeQuery();
            while(rs.next()){
                st.add(new ProductImage(rs.getInt("ImageID"),rs.getInt("ProductID"),rs.getString("ImageUrl")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }finally{
            DBConnection.close(con, ps, rs);
        }
        
        return st;
    }
    
      public ProductImage getProductImageByImageId (int id) {
        ProductImage st=new ProductImage();
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" SELECT * FROM  [ProductImages]"
                     + " WHERE [ImageID] = ? " );
             ps.setInt(1, id);
             rs=ps.executeQuery();
            while(rs.next()){
                st.setImageId(rs.getInt("ImageID"));
                st.setProductId(rs.getInt("ProductID"));
                st.setImageUrl(rs.getString("ImageUrl"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }finally{
            DBConnection.close(con, ps, rs);
        }
        
        return st;
    }
     
      public void insert(ProductImage st) {
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" Insert into "
                     + " ProductImages(ImageID,ProductID,ImageUrl) "
                     + " Values (?,?,?) " );
             ps.setInt(1, st.getImageId());
             ps.setInt(2, st.getProductId());
             ps.setString(3, st.getImageUrl());
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
    
    public void update(ProductImage st) {
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" UPDATE [ProductImages] SET "
                     + " ProductID=? , "
                     + " ImageUrl=?  "
                     + " WHERE [ImageID] = ?   " );
             ps.setInt(1, st.getProductId());
             ps.setString(2, st.getImageUrl());
             ps.setInt(3, st.getImageId());
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
             ps= con.prepareStatement(" DELETE FROM [ProductImages]  "
                     + " WHERE [ImageID]= ?   " );
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
    
    public ProductImage getFirstProductImages (int id) {
        ProductImage st = null;
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" SELECT * FROM  [ProductImages]"
                     + " WHERE [ProductID] = ? " );
             ps.setInt(1, id);
             rs=ps.executeQuery();
            if(rs.next()){
                st = new ProductImage(rs.getInt("ImageID"),rs.getInt("ProductID"),rs.getString("ImageUrl"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }finally{
            DBConnection.close(con, ps, rs);
        }
        
        return st;
    }
    
}
