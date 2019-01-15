package com.ideal.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;


public class TestToString {
public static void main(String[] args) {
	String s=null;
	
		TestMethod obj=new TestMethod();
		a("");
//		Method m1=TestMethod.class.getMethod("contact", String.class);
//		m1.invoke(obj, s);
	} 
	
	
	
/*	try {
		a(s);
	} catch (Exception e) {
		e.printStackTrace();
	}*/

private static void a(String s) {
	try {
		s.charAt(0);
	} catch (Exception e) {
		throw e;
	}
	
}
}


 



