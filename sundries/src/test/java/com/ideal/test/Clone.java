package com.ideal.test;

public class Clone {
	/**
	 * @author zhaopei
	 * @param args
	 */
	
	public static void main(String[] args) {
		for (String string : args) {
			System.out.println(string+"---");
		}
		Parent item1=new Parent("john", 10);
		Son son1=new Son("joho's son", 4);
		item1.setSon(son1);
		Parent item2=item1.clone();
		
		item1.setName("alice");
        son1.setName("alice's son");		
		
		System.out.println(item1.toString());
		System.out.println(item2.toString());
		
		
		StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
		for (StackTraceElement stackTraceElement : stackTrace) {
			System.out.println(stackTraceElement.getMethodName());
			 
		}
		
		System.out.println(stackTrace);
		
		
		
	}
	
 
	public static class Parent implements Cloneable{
		String name = "";
		int age = 0;
		Son theson;
		Parent (String n, int age){
			this.name = n;
			this.age = age;
		}
 
		public void setName(String na) {
			name = na;
		}
		
		public void setSon(Son s) {
			theson = s;
		}
		@Override
	    protected Parent clone() {
			Parent clone = null;
	        try {
	            clone = (Parent) super.clone();
	        } catch (CloneNotSupportedException e){
	            throw new RuntimeException(e); // won't happen
	        }
	        clone.theson=theson.clone();
	        return clone;
	     }
		
		public String toString() {
			return "Parent[" + name + "===" + age + "];" + "---Son:"+ (theson != null ? theson.name : "null");
		}
	}
	public static class Son implements Cloneable {
		String name = "";
		int age = 0;
		Son (String n, int age){
			this.name = n;
			this.age = age;
		}
 
		public void setName(String na) {
			name = na;
		}
		@Override
	    protected Son clone() {
			Son clone = null;
	        try {
	            clone = (Son) super.clone();
	        } catch (CloneNotSupportedException e){
	            throw new RuntimeException(e); // won't happen
	        }
	        
	        return clone;
	     }
		
		public String toString() {
			return "Son[" + name + "===" + age + "];";
		}
	}
}
