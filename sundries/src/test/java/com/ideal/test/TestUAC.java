/*
package com.ideal.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.junit.Test;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.xdb.XMLType;


public class TestUAC {
	@Test
	public void testUAC() {
		try {
			File f=new File("C:\\Users\\zhaopei\\Desktop\\Untitled2.xml");
			FileReader fr =new FileReader(f);
			BufferedReader br =new BufferedReader(fr);
			StringBuffer sb=new StringBuffer();
			String line=null;
			 while ((line=br.readLine())!=null) {
				sb.append(line);
			}
			System.out.println(sb.toString());
			String sql="select * from table(eai_query.crm_soo_pkg_b.chk_soo_order(?,?))";
			String driverName = "oracle.jdbc.driver.OracleDriver";  
			String url="jdbc:oracle:thin:@ (description=(address_list=(address=(protocol=tcp)(host=10.145.196.67)(port=1521)) )(connect_data= (server =dedicated)(sid = siebeldb2) ))";  
			String username = "eai_query";  
			String password = "copydb2012";  
			Class.forName(driverName);  
			Connection conn = DriverManager.getConnection(url, username, password);  
			
			ResultSet rs = null;
			OracleCallableStatement preStat = null;
			if (conn!=null) {
				preStat = (OracleCallableStatement) conn.prepareCall(sql);
				XMLType reqInXml = XMLType.createXML((OracleConnection) conn, sb.toString());
				preStat.setObject(1, reqInXml);
				preStat.setString(2, "02");
				rs = preStat.executeQuery();
				
				while (rs.next()) {
					System.out.println(rs.getString(1));
					System.out.println(rs.getString(2));
					System.out.println(rs.getString(3));
					System.out.println(rs.getString(4));
				}
				
			}
		}   catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		@Test
		public void testQuery() {
			try {
				File f=new File("C:\\Users\\zhaopei\\Desktop\\aaa.xml");
				FileReader fr =new FileReader(f);
				BufferedReader br =new BufferedReader(fr);
				StringBuffer sb=new StringBuffer();
				String line=null;
				 while ((line=br.readLine())!=null) {
					sb.append(line);
				}
				System.out.println(sb.toString());
				String sql="{ ?=call crm_soo_pkg.qry_soo_order(?)}";
				String sql2="select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') from dual";
				String driverName = "oracle.jdbc.driver.OracleDriver";  
				String url="jdbc:oracle:thin:@10.145.213.10:1521:siebeldb2";  
				String username = "eai_query";  
				String password = "copydb2012";  
				Class.forName(driverName);  
				Connection conn = DriverManager.getConnection(url, username, password);  
				CallableStatement preStat = null;
				String retstr="";
				if (conn!=null) {
					preStat =conn.prepareCall(sql);
					preStat.registerOutParameter(1, java.sql.Types.CLOB);
					XMLType reqInXml = XMLType.createXML((OracleConnection) conn, sb.toString());
					preStat.setObject(2, reqInXml);
					preStat.execute();
					 retstr = clobToString(preStat.getClob(1));
					System.out.println("返回结果："+retstr);
					preStat =conn.prepareCall(sql2);
					ResultSet rs=preStat.executeQuery();
					while (rs.next()) {
						System.out.println(rs.getString(1));
					}
					
					
				}
			}   catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
		private String clobToString(Object obj) {
			Clob clob = (Clob) obj;
			try {
				if (clob == null || clob.getCharacterStream() == null)
					return null;
				BufferedReader read = new BufferedReader(clob.getCharacterStream());
				String temp;
				StringBuffer buf = new StringBuffer(1024);
				while ((temp = read.readLine()) != null) {
					buf.append(temp).append("\n");
				}
				return buf.toString();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		@Test
		public void testStr(){
			String str="abcdefg.txt";
			System.out.println(str.lastIndexOf('.'));
			System.out.println(str.substring(1,str.lastIndexOf('.') ));
		}
	
}
*/
