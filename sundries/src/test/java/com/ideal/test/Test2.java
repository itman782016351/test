package com.ideal.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test2 {

	public static void main(String[] args) throws Exception {
		String str = "结束";
//		byte[] byte1 = str.getBytes();
//		for (int i = 0; i < byte1.length; i++) {
//			System.out.println(byte1[i]);
//		}
//		System.out.println("-------------");
//		byte[] byteGBK = str.getBytes("GBK");
//		for (int i = 0; i < byteGBK.length; i++) {
//			System.out.println(byteGBK[i]);
//		}
//		System.out.println("---------------");
//		byte[] byteUTF8 = str.getBytes("UTF-8");
//		for (int i = 0; i < byteUTF8.length; i++) {
//			System.out.println(byteUTF8[i]);
//		}
		//System.out.println(new String(str.getBytes("UTF-8"), "GBK"));
		
		String date1="2019-06-07 10:56:42";
		String date2="20190607";
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMdd");
		Date date=new Date();
		
		Date _date1=sdf1.parse(date1);
		Date _date2=sdf2.parse(date2);
		
		
		System.out.println(_date2);
		
		Calendar cal=Calendar.getInstance();
		cal.setTime(_date2);
		cal.add(Calendar.DATE,1);
		System.out.println(cal.getTime());
		
		System.out.println(_date1.after(cal.getTime()));
		
		
		
		
		//System.out.println(sdf2.parse(date2).getYear()==sdf1.parse(date1).getYear());
		
		
	}
	
	
	

}
