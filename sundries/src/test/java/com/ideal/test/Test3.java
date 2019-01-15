package com.ideal.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

import javax.swing.filechooser.FileNameExtensionFilter;

public class Test3 {

	public static void main(String[] args) throws Exception {
		System.out.println(System.getProperty("file.encoding"));
		String str = "�";
		byte[] byte1 = str.getBytes();
		for (int i = 0; i < byte1.length; i++) {
			System.out.println(byte1[i]);
		}
		System.out.println("-------------");
		byte[] byte2 = str.getBytes("UTF-8");
		for (int i = 0; i < byte2.length; i++) {
			System.out.println(byte2[i]);
		}
		 
		//System.out.println(new String(str.getBytes("GBK"), "UTF-8"));
		File f=new File("C:\\Users\\zhaopei\\Desktop\\lm.txt");
		FileInputStream is=new FileInputStream(f);
		BufferedInputStream bs=new BufferedInputStream(is);
		byte[] b=new byte[3];
		int len=0;
		while ((len=bs.read())!=-1) {
			System.out.println(len+"~~~~~~");
			//bs.read(b, 0, len);
			byte[] a=new byte[3];
		}
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i]);
		}
		
		
		
	}
	
	
	

}
