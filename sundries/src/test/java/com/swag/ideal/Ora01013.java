package com.swag.ideal;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @author zhaopei
 * @create 2019-03-08 19:26
 */
public class Ora01013 {


    @Test
    public void commitTest() throws SQLException {
        Connection conn = getConnection();
        String sql = "insert into a values ('c')";
        PreparedStatement pst = null;
        //String sql = "insert into b values(?)";
        pst = conn.prepareStatement(sql);
        System.out.println(pst.execute());

    }


    @Test
    public void insert() throws SQLException {
        Connection conn = getConnection();
        String sql = "select count( t1.row_id) from siebel.s_prod_int t1 ,siebel.s_asset t2  where t1.row_id= t2.promotion_id";
        PreparedStatement pst = null;
        //String sql = "insert into b values(?)";
        pst = conn.prepareStatement(sql);
        pst.setQueryTimeout(20);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }

    }


    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            conn = DriverManager.getConnection("jdbc:oracle:thin:@10.145.196.67:1521:siebeldb2", "ordercenter", "ordercenter");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;

    }


}
