package com.ideal;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @author zhaopei
 * @create 2019-06-05 11:00
 */
public class createSwaggerFile {
    public static void main(String[] args) throws Exception {
        insert();
        read();
        writeFile();
    }


    public static void insert() throws SQLException {
        Connection conn = getConnection();
        String sql = "SELECT 1 FROM DUAL";
        PreparedStatement pst = null;
        //String sql = "insert into b values(?)";
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        sql = "TRUNCATE table  api";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        sql = "TRUNCATE table  apiurl";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();

    }

    public static void writeFile() throws Exception {
        Connection conn = getConnection();
        String sql = "SELECT  * FROM api";
        PreparedStatement pst = null;
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        String dir = "/crm/swagyamls/";
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


    public static void read() throws Exception {
        String dir = "/crm";
        File dirFile = new File(dir);
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            System.out.println(f);
        }
        File sourcexsl = new File("/crm/swagger.xlsx");
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
            //    String methoddesc = row.getCell(4).getStringCellValue();
            String methoddesc = "";
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

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://10.145.205.55:3306/test", "esbadmin", "esbroot");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;

    }

    public static void writeHead(PrintWriter pw, String apidesc) {
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

    public static void writeTail(PrintWriter pw) {
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

    public static void writeBody(PrintWriter pw, String httpmethod, String methoddesc) {
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
