/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dal.DBConnection;
import model.Product;
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
public class ProductDao {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<Product> getProducts() {
        List<Product> getArrayLists = new ArrayList<>();
        try {
            con = DBConnection.open();
            ps = con.prepareStatement("SELECT * FROM Products");
            rs = ps.executeQuery();
            while (rs.next()) {
                Product st = new Product();
                st.setProductId(rs.getInt("ProductID"));
                st.setProductName(rs.getString("ProductName"));
                st.setUnitsPrice(rs.getLong("UnitsPrice"));
                st.setCategoryId(rs.getInt("CategoryID"));
                st.setUnitsInStock(rs.getInt("UnitsInStock"));
                st.setDescription(rs.getString("Description"));
                st.setManufacturerID(rs.getInt("ManufacturerID"));
                st.setIsContinues(rs.getBoolean("IsContinue"));
                st.setStars(rs.getInt("Stars"));
                getArrayLists.add(st);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBConnection.close(con, ps, rs);
        }

        return getArrayLists;
    }

    public List<Product> getProducts(int index, int order_by, int ManufacturerIDs, int CategoryID, long begin, long end, String name) {
        List<Product> getArrayLists = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE 1=1";
        sql = addGroubBy(sql, ManufacturerIDs);// WHERE ManufacturerID =
        sql = addCategoryID(sql, CategoryID);  //WHERE CategoryID=
        sql = addUnitPrice(sql, begin, end);    //WHERE UnitsPrice BETWEEN
        sql = addSearchByName(sql, name);
        StringBuilder sb = new StringBuilder(sql);

        switch (order_by) {
            case 1: {
                sb.append(" ORDER BY ProductName ASC OFFSET ? ROWS FETCH NEXT 9 ROWS ONLY");
                break;
            }
            case 2: {
                sb.append(" ORDER BY UnitsPrice ASC OFFSET ? ROWS FETCH NEXT 9 ROWS ONLY");
                break;
            }
            case 3: {
                sb.append(" ORDER BY UnitsPrice DESC OFFSET ? ROWS FETCH NEXT 9 ROWS ONLY");
                break;
            }
            default: {
                sb.append(" ORDER BY ProductID DESC OFFSET ? ROWS FETCH NEXT 9 ROWS ONLY");
                break;
            }
        }

        try {
            con = DBConnection.open();
            ps = con.prepareStatement(sb.toString());
            ps.setInt(1, index * 9);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product st = new Product();
                st.setProductId(rs.getInt("ProductID"));
                st.setProductName(rs.getString("ProductName"));
                st.setUnitsPrice(rs.getLong("UnitsPrice"));
                st.setCategoryId(rs.getInt("CategoryID"));
                st.setUnitsInStock(rs.getInt("UnitsInStock"));
                st.setDescription(rs.getString("Description"));
                st.setManufacturerID(rs.getInt("ManufacturerID"));
                st.setIsContinues(rs.getBoolean("IsContinue"));
                st.setStars(rs.getInt("Stars"));
                getArrayLists.add(st);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBConnection.close(con, ps, rs);
        }

        return getArrayLists;
    }

    public ArrayList<Product> getProducts(String name) {
        ArrayList<Product> getArrayLists = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE 1=1";
        sql = addSearchByName(sql, name);
        try {
            con = DBConnection.open();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product st = new Product();
                st.setProductId(rs.getInt("ProductID"));
                st.setProductName(rs.getString("ProductName"));
                st.setUnitsPrice(rs.getLong("UnitsPrice"));
                st.setCategoryId(rs.getInt("CategoryID"));
                st.setUnitsInStock(rs.getInt("UnitsInStock"));
                st.setDescription(rs.getString("Description"));
                st.setManufacturerID(rs.getInt("ManufacturerID"));
                st.setIsContinues(rs.getBoolean("IsContinue"));
                st.setStars(rs.getInt("Stars"));
                getArrayLists.add(st);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBConnection.close(con, ps, rs);
        }

        return getArrayLists;
    }

    private String addGroubBy(String sql, int ManufacturerIDs) {
        StringBuilder sb = new StringBuilder(sql);
        if (ManufacturerIDs > 0) {
            sb.append(" AND ManufacturerID =").append(ManufacturerIDs);
        }
        return sb.toString();
    }

    private String addCategoryID(String sql, int CategoryID) {
        StringBuilder sb = new StringBuilder(sql);
        if (CategoryID > 0) {
            sb.append(" AND CategoryID=").append(CategoryID);
        }
        return sb.toString();
    }

    private String addUnitPrice(String sql, long begin, long end) {
        StringBuilder sb = new StringBuilder(sql);
        if (end > 0) {
            sb.append(" AND UnitsPrice BETWEEN ").append(begin).append(" AND ").append(end);
        }
        return sb.toString();
    }

    private String addSearchByName(String sql, String name) {
        StringBuilder sb = new StringBuilder(sql);
        if (!name.isEmpty()) {
            sb.append(" AND ProductName like '% ").append(name).append("%'");
        }
        return sb.toString();
    }

    public int getPageCount(int ManufacturerIDs, int CategoryID, long begin, long end, String name) {
        int st = 0;
        String sql = "SELECT COUNT(*) FROM Products WHERE 1=1";
        sql = addGroubBy(sql, ManufacturerIDs);
        sql = addCategoryID(sql, CategoryID);
        sql = addUnitPrice(sql, begin, end);
        sql = addSearchByName(sql, name);
        try {
            con = DBConnection.open();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                st = (int) Math.ceil(rs.getInt(1) / 9.0);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBConnection.close(con, ps, rs);
        }

        return st;
    }

    public Product getProduct(int id) {
        Product st = new Product();
        try {
            con = DBConnection.open();
            ps = con.prepareStatement(" SELECT * FROM  [Products]"
                    + " WHERE [ProductID] = ? ");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                st.setProductId(rs.getInt("ProductID"));
                st.setProductName(rs.getString("ProductName"));
                st.setUnitsPrice(rs.getLong("UnitsPrice"));
                st.setCategoryId(rs.getInt("CategoryID"));
                st.setUnitsInStock(rs.getInt("UnitsInStock"));
                st.setDescription(rs.getString("Description"));
                st.setManufacturerID(rs.getInt("ManufacturerID"));
                st.setIsContinues(rs.getBoolean("IsContinue"));
                st.setStars(rs.getInt("Stars"));
                return st;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBConnection.close(con, ps, rs);
        }

        return st;
    }

    public void insert(Product st) {
        try {
            con = DBConnection.open();
            ps = con.prepareStatement(" Insert into "
                    + "Products([ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) "
                    + " Values (?,?,?,?,?,?,?,?) ");

            ps.setString(1, st.getProductName());
            ps.setLong(2, st.getUnitsPrice());
            ps.setInt(3, st.getCategoryId());
            ps.setInt(4, st.getUnitsInStock());
            ps.setString(5, st.getDescription());
            ps.setInt(6, st.getManufacturerID());
            ps.setBoolean(7, st.isIsContinues());
            ps.setInt(8, st.getStars());
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

    public int update(Product st) {
        try {
            con = DBConnection.open();
            ps = con.prepareStatement(" UPDATE [Products] SET "
                    + " ProductName=? , "
                    + " UnitsPrice=? , "
                    + " CategoryID=? , "
                    + " UnitsInStock=? , "
                    + " Description=?,"
                    + " [ManufacturerID] = ?,"
                    + " [IsContinue] = ?,"
                    + " [Stars] = ?"
                    + " WHERE [ProductID] = ?   ");
            ps.setInt(9, st.getProductId());
            ps.setString(1, st.getProductName());
            ps.setLong(2, st.getUnitsPrice());
            ps.setInt(3, st.getCategoryId());
            ps.setInt(4, st.getUnitsInStock());
            ps.setString(5, st.getDescription());
            ps.setInt(6, st.getManufacturerID());
            ps.setBoolean(7, st.isIsContinues());
            ps.setInt(8, st.getStars());
            /*
             update thi khong can tra ve 
             executeUpdate tra ve so ban ghi chiu tac dong cua cau lenh update 
             */
            int ans =ps.executeUpdate();
            return ans ;
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBConnection.close(con, ps, rs);
        }
        return 0;
    }


    public void delete(int id) {
        try {
            con = DBConnection.open();
            ps = con.prepareStatement(" DELETE FROM [Products]  "
                    + " WHERE [ProductID]= ?   ");
            ps.setInt(1, id);
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

    public List<Product> getProductsHome() {
        List<Product> getArrayLists = new ArrayList<>();
        try {
            con = DBConnection.open();
            ps = con.prepareStatement("SELECT * FROM Products ORDER BY ProductID DESC OFFSET 0 ROWS FETCH NEXT 9 ROWS ONLY");
            rs = ps.executeQuery();
            while (rs.next()) {
                Product st = new Product();
                st.setProductId(rs.getInt("ProductID"));
                st.setProductName(rs.getString("ProductName"));
                st.setUnitsPrice(rs.getLong("UnitsPrice"));
                st.setCategoryId(rs.getInt("CategoryID"));
                st.setUnitsInStock(rs.getInt("UnitsInStock"));
                st.setDescription(rs.getString("Description"));
                st.setManufacturerID(rs.getInt("ManufacturerID"));
                st.setIsContinues(rs.getBoolean("IsContinue"));
                st.setStars(rs.getInt("Stars"));
                getArrayLists.add(st);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBConnection.close(con, ps, rs);
        }

        return getArrayLists;
    }

    public int insertProduct(String name, long unitprice,
            int CategoryID, int unitsInStock, String description, int manufacturerID, boolean isContinues, int stars) {
        int result=0;
        try {
            con = DBConnection.open();
            ps = con.prepareStatement(" Insert into "
                    + "Products([ProductName], [UnitsPrice], [CategoryID], [UnitsInStock], [Description], [ManufacturerID], [IsContinue], [Stars]) "
                    + " Values (?,?,?,?,?,?,?,?)");

            ps.setString(1, name);
            ps.setLong(2, unitprice);
            ps.setInt(3, CategoryID);
            ps.setInt(4, unitsInStock);
            ps.setString(5, description);
            ps.setInt(6, manufacturerID);
            ps.setBoolean(7, isContinues);
            ps.setInt(8, stars);
            /*
             update thi khong can tra ve 
             executeUpdate tra ve so ban ghi chiu tac dong cua cau lenh update 
             */
            result= ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBConnection.close(con, ps, rs);
        }
        return result;
    }

}
