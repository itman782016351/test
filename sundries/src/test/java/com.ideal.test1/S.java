package com.ideal.test1;

import java.util.ArrayList;

public class S {

    public static void main(String[] args){//�ո�ָ�
//           System. out .println( "Program arguments" );
//           for ( String str:args ){
//               System. out .println( str );
//           }
//
//           System. out .println( "VM arguments" );
//           String syspro1 = "java.util.logging.config.file" ;
//           System. out .println( System.getProperty (syspro1) );
//           String syspro2 = "cc" ;
//           System. out .println( System.getProperty (syspro2) );
//    	String  a=null;
//    	switch (a) {
//		case "1":
//			System.out.println("1");
//			break;
//		default:
//			break;
//		}
//    	   
//    	
    	for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
    	a(args);
    	
    	String [] arrList=new String [2];
    	arrList[0]="aa";
    	arrList[1]="bb";
    	
    	a(arrList);
    	
    	
    	
    	
    	
    	
    	
    	
    	
        }
    
    public static void a(String ... mm){
    	for (int i = 0; i < mm.length; i++) {
    		System.out.println(mm[i]);
		}
    
    }
}