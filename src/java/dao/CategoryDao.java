/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.DBConnection;
import model.Category;
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
public class CategoryDao {
    
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    public List<Category> getCategories(){
        List<Category> getArrayLists=new ArrayList<>();
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" SELECT * \n" +
                " FROM [Categories]  ");
             rs=ps.executeQuery();
            while(rs.next()){
                Category st=new Category();
                st.setCategoryID(rs.getInt("CategoryID"));
                st.setCategoryName(rs.getString("CategoryName"));
                st.setCategoryImage(rs.getString("CategoryImage"));;
                getArrayLists.add(st);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }finally{
            DBConnection.close(con, ps, rs);
        }

        return getArrayLists;
    }
    
     public Category getCategory(int id) {
        Category st=new Category();
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" SELECT * FROM  [Categories]"
                     + " WHERE [CategoryID] = ? " );
             ps.setInt(1, id);
             rs=ps.executeQuery();
            while(rs.next()){
                st.setCategoryID(rs.getInt("CategoryID"));
                st.setCategoryName(rs.getString("CategoryName"));
                st.setCategoryImage(rs.getString("CategoryImage"));
                return st;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }finally{
            DBConnection.close(con, ps, rs);
        }
        
        return st;
    }
    
      public void insert(Category st) {
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" Insert into Categories(CategoryName,CategoryImage) Values (?,?) " );
             ps.setString(1, st.getCategoryName());
             ps.setString(2, st.getCategoryImage());
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
    
    public void update(Category st) {
        try {
             con =DBConnection.open();
             ps= con.prepareStatement(" UPDATE [Categories] SET [CategoryName]=? , [CategoryImage]=?   "
                     + " WHERE [CategoryID] = ?   " );
             ps.setString(1, st.getCategoryName());
             ps.setString(2,st.getCategoryImage());
             ps.setInt(3, st.getCategoryID());
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
             ps= con.prepareStatement(" DELETE FROM [Categories]  "
                     + " WHERE [CategoryID]= ?   " );
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
