package com.swag.ideal;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author zhaopei
 * @create 2019-03-08 19:26
 */
public class Swag {


    @Test
    public void insert() throws SQLException {
        Connection conn = getConnection();
        String sql = "select to_char(sysdate,'yyyy-MM-dd HH24:mi:ss') from dual";
        PreparedStatement pst = null;
        //String sql = "insert into b values(?)";
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }

    }

    @Test
    public void writeFile() throws Exception {
        Connection conn = getConnection();
        String sql = "SELECT  * FROM api";
        PreparedStatement pst = null;
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        String dir = "C:\\Users\\zhaopei\\Desktop\\swagyamls\\";
        PrintWriter pw = null;
        while (rs.next()) {
            String apiid = rs.getString(1);
            String apidesc = rs.getString(2);
            File swagFile = new File(dir + apiid + ".yaml");
            pw = new PrintWriter(swagFile);
            writeHead(pw, apidesc);
            sql = "select  DISTINCT  url from apiurl where apiid = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, apiid);
            ResultSet rs1 = pst.executeQuery();
            String url = "";
            while (rs1.next()) {
                url = rs1.getString(1);
                pw.println("  " + url + ":");
                sql = "select  * from apiurl where apiid = ? and  url=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, apiid);
                pst.setString(2, url);
                ResultSet rs2 = pst.executeQuery();
                while (rs2.next()) {
                    String httpmethod = rs2.getString(2).toLowerCase();
                    String methoddesc = rs2.getString(4);
                    writeBody(pw, httpmethod, methoddesc);
                }

            }
            writeTail(pw);
            pw.flush();
            pw.close();
        }

    }


    @Test
    public void read() throws Exception {
        String dir = "C:\\Users\\zhaopei\\Desktop\\";
        File dirFile = new File(dir);
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            System.out.println(f);
        }
        File sourcexsl = new File("C:\\Users\\zhaopei\\Desktop\\cc.xlsx");
        Workbook wb = new XSSFWorkbook(new FileInputStream(sourcexsl));
        //shelet1;
        Sheet s1 = wb.getSheetAt(0);
        Connection conn = getConnection();
        PreparedStatement pst = null;
        String sql = "insert into api values (?,?)";
        pst = conn.prepareStatement(sql);
        int lineNo = 0;
        for (int i = 1; i <= s1.getLastRowNum(); i++) {
            Row row = s1.getRow(i);
            String apiid = row.getCell(2).getStringCellValue();
            String apidesc = row.getCell(1).getStringCellValue();
            if (StringUtils.isNotEmpty(apiid) && StringUtils.isNotEmpty(apidesc)) {
                pst.setString(1, apiid.trim());
                pst.setString(2, apidesc.trim());
                pst.addBatch();
                lineNo++;
                if (lineNo % 100 == 0) {
                    pst.executeBatch();
                    pst.clearBatch();
                }
            }
        }
        if (lineNo % 100 != 0) {
            pst.executeBatch();
            pst.clearBatch();
        }
        //sheet2
        Sheet s2 = wb.getSheetAt(1);
        sql = "insert into apiurl values (?,?,?,?)";
        pst = conn.prepareStatement(sql);
        lineNo = 0;
        for (int i = 1; i <= s2.getLastRowNum(); i++) {
            Row row = s2.getRow(i);
            String apiid = row.getCell(1).getStringCellValue();
            String httpmethod = row.getCell(2).getStringCellValue();
            String url = row.getCell(3).getStringCellValue();
            String methoddesc = row.getCell(4).getStringCellValue();
            System.out.println(apiid + httpmethod + url + methoddesc + "======");
            if (StringUtils.isNotEmpty(apiid) && StringUtils.isNotEmpty(httpmethod) && StringUtils.isNotEmpty(url)) {
                pst.setString(1, apiid.trim());
                pst.setString(2, httpmethod.trim());
                pst.setString(3, url.trim());
                pst.setString(4, methoddesc.trim());
                pst.addBatch();
                lineNo++;
                if (lineNo % 10 == 0) {
                    pst.executeBatch();
                    pst.clearBatch();
                }
            }
        }
        if (lineNo % 10 != 0) {
            pst.executeBatch();
            pst.clearBatch();
        }


    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott", "tiger");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;

    }

    public void writeHead(PrintWriter pw, String apidesc) {
        pw.println("swagger: '2.0'");
        pw.println("info:");
        pw.println("  description: " + apidesc);
        pw.println("  version: '1.0'");
        pw.println("  title: " + apidesc);
        pw.println("  contact: {}");
        pw.println("host: 'res.pr.sh.ctc.com:8084'");
        pw.println("basePath: /res/resbizinst/mktrescenter/biz/inst");
        pw.println("tags:");
        pw.println("  - name: " + apidesc);
        pw.println("    description: " + apidesc);
        pw.println();
        pw.println();
        pw.println("paths:");
    }

    public void writeTail(PrintWriter pw) {
        pw.println("definitions:");
        pw.println("  FuncationalProductsDTO:");
        pw.println("    type: object");
        pw.println("    required:");
        pw.println("      - endDate");
        pw.println("      - id");
        pw.println("      - name");
        pw.println("      - number");
        pw.println("      - orderDate");
        pw.println("      - productId");
        pw.println("      - startDate");
        pw.println("    properties:");
        pw.println("      endDate:");
        pw.println("        type: string");
        pw.println("        description: 结束时间");
        pw.println("      id:");
        pw.println("        type: string");
        pw.println("        description: 编码");
        pw.println("      name:");
        pw.println("        type: string");
        pw.println("        description: 业务名称");
        pw.println("      number:");
        pw.println("        type: string");
        pw.println("        description: 产品编码");
        pw.println("      orderDate:");
        pw.println("        type: string");
        pw.println("        description: 订购时间");
        pw.println("      productId:");
        pw.println("        type: string");
        pw.println("        description: 用户实例ID");
        pw.println("      startDate:");
        pw.println("        type: string");
        pw.println("        description: 开始时间");
        pw.println("    title: FuncationalProductsDTO");
        pw.println("    description: 功能产品");
    }

    public void writeBody(PrintWriter pw, String httpmethod, String methoddesc) {
        pw.println("    " + httpmethod + ":");
        if (StringUtils.isNotEmpty(methoddesc)) {
            pw.println("      summary: " + methoddesc);
        }
        pw.println("      parameters:");
        pw.println("        - name: lanId");
        pw.println("          in: query");
        pw.println("          description: 本地网标识");
        pw.println("          required: true");
        pw.println("          type: string");
        pw.println("      responses:");
        pw.println("        '200':");
        pw.println("          description: OK");
        pw.println("          schema:");
        pw.println("            $ref: '#/definitions/FuncationalProductsDTO'");
        pw.println();
    }

}
