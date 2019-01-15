package com.ideal.test;

import java.util.ArrayList;

import org.junit.Test;

public class TestTransfer {
	public static void swap(int a , int b){
		int tmp = a;
		a = b;
		b = tmp;
		System.out.println("swap方法里，a的值是" 
				+ a + "；b的值是" + b);
	}
	public static void main(String[] args) {
		int a = 6;
		int b = 9;
		swap(a , b);
		System.out.println("swap后a的值是" 
				+ a + "；b的值是" + b);
}  
	
	@Test
	public void arrayListTest(){
		ArrayList al=new ArrayList<>(1);
		al.add(1);
		al.add(2);
		al.add(3);
		System.out.println(al.size());
		
		 int j=0;
    	 for(int n=0;n<3;n++)
    	 
    	 j++;
    	 System.out.println(j);
		
	}
   
	@Test
	public void testSplit(){
		String test="1|2|3|";
		
		System.out.println(test.split("\\|").length);
		System.out.println(test.split("\\|",-1).length);
		
	}
	 



}

