package com.ideal.test;

import java.util.Calendar;

public class TestSon {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println(System.currentTimeMillis());
		 
		
		   Calendar calendar = Calendar.getInstance();
           SonClass son=new SonClass();
           son.jcff();
           
           GrandClass son1=new GrandClass();
           son1.jcff();
           GrandClass son2=new SonClass();
           son2.jcff();
           
	}

}
