package com.ideal.test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class TestReferences {
	@Test
	public void systemTime(){
		
		for (int i = 0; i < 4; i++) {
			String tempID=String.valueOf(System.currentTimeMillis());
			try {
				Thread.currentThread().sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(tempID.substring(tempID.length()-4, tempID.length()));
		} 
		
		
		
	}
	
	
     
	@Test
	 public void a()  {
		try{
		 Class clazz =Class.forName("com.ideal.Test.A");
		 A a=(A)clazz.newInstance();
		 Method m=clazz.getMethod("b", String.class);
		 m.invoke(a, "zhaopei");
		 Integer i=0;
		 System.out.println(i.equals(""));
		}
		catch(Exception e){
			
		}
		}
	
	  @Test
	 public void b(){
		  try {
			int i=5;
			  int j;
			  j=5/0;
		} catch (Exception e) {
			StringWriter sw=new StringWriter();
			PrintWriter pw =new PrintWriter(sw);
			e.printStackTrace(pw);
			System.out.println(sw.toString());
		}
		  
		  
		//  System.out.println("Hello,world!");
	  }
	  
	  @Test
	  public void c(){
		  ConcurrentHashMap<Integer,AtomicInteger> connTimesMonitorMap=new ConcurrentHashMap<Integer, AtomicInteger>(2);
		  connTimesMonitorMap.put(1, new AtomicInteger(20));
		  connTimesMonitorMap.put(2, new AtomicInteger(23));
		  for (int i = 0; i < 1000; i++) {
			  connTimesMonitorMap.put(i+3, new AtomicInteger(11));
		}
		 
		  System.out.println(connTimesMonitorMap.get(3));
		  System.out.println(connTimesMonitorMap.size());
		  Set set =connTimesMonitorMap.entrySet();
	      for (Object object : set) {
	    	  Map.Entry entry=(Map.Entry)object;
			System.out.println(entry.getKey()+":"+entry.getValue());
			
		}
		  
	  }

}


class A {
	
 public	void b(String a){
		System.out.println(a);
	}
	
}