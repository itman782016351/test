package com.ideal.test;

public class Thread3 extends Thread{
    public void run(){  
        while(true){  
//        	try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
            if(Thread.currentThread().isInterrupted()){  
                System.out.println("Someone interrupted me.");  
            }  
            else{  
                System.out.println("Thread is Going...");  
            }
        }  
    }  

    public static void main(String[] args) throws InterruptedException {  
        Thread3 t = new Thread3();  
        t.start();  
        Thread.sleep(1);
        System.out.println("��ʼ�ж�.............");
        t.stop();
    }  
} 
 