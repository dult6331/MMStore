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
import model.Manufacturer;
import model.Order;

/**
 *
 * @author Hfyl
 */
public class ManufacturerDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public ManufacturerDAO() {
    }

    public List<Manufacturer> getManufacturers() {
        List<Manufacturer> getArrayLists = new ArrayList<>();
        try {
            con = DBConnection.open();
            ps = con.prepareStatement("SELECT * FROM Manufacturers");
            rs = ps.executeQuery();
            while (rs.next()) {
                Manufacturer st = new Manufacturer();
                st.setManufacturerID(rs.getInt(1));
                st.setManufacturerName(rs.getString(2));
                getArrayLists.add(st);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBConnection.close(con, ps, rs);
        }

        return getArrayLists;
    }

    public Manufacturer getManufacturer(int id) {
        Manufacturer st = null;
        try {
            con = DBConnection.open();
            ps = con.prepareStatement("SELECT * FROM Manufacturers WHERE ManufacturerID= ? ");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                st = new Manufacturer();
                st.setManufacturerID(rs.getInt("ManufacturerID"));
                st.setManufacturerName(rs.getString("ManufacturerName"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DBConnection.close(con, ps, rs);
        }

        return st;
    }

    public void insert(Manufacturer st) {
        try {
            con = DBConnection.open();
            ps = con.prepareStatement(" INSERT INTO [dbo].[Manufacturers] ([ManufactureName]) VALUES (?) ");
            ps.setString(1, st.getManufacturerName());
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

    public void update(Manufacturer st) {
        try {
            con = DBConnection.open();
            ps = con.prepareStatement(" UPDATE [dbo].[Manufacturers] SET [ManufactureName] = ? WHERE [ManufacturerID] = ? ");
            ps.setString(1, st.getManufacturerName());
            ps.setInt(2, st.getManufacturerID());
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

    public void delete(int id) {
        try {
            con = DBConnection.open();
            ps = con.prepareStatement(" DELETE FROM [dbo].[Manufacturers] WHERE [ManufacturerID] = ? ");
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
}
