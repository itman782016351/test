package com.ideal.test;

public class Test {

	public static void main(String[] args) throws Exception {
		String str = "缁";
		byte[] byte1 = str.getBytes();
		for (int i = 0; i < byte1.length; i++) {
			System.out.println(byte1[i]);
		}
		System.out.println("-------------");
		byte[] byteGBK = str.getBytes("GBK");
		for (int i = 0; i < byteGBK.length; i++) {
			System.out.println(byteGBK[i]);
		}
		System.out.println("---------------");
		byte[] byteUTF8 = str.getBytes("UTF-8");
		for (int i = 0; i < byteUTF8.length; i++) {
			System.out.println(byteUTF8[i]);
		}
		System.out.println(new String(str.getBytes("GBK"), "UTF-8"));
//		File f=new File("C:\\Users\\zhaopei\\Desktop\\a.xml");
//		FileReader fr=new FileReader(f);
//		BufferedReader br =new BufferedReader(fr);
//		String str2;
//		while ((str2=br.readLine())!=null) {
//			System.out.println("-------------");
//			byte[] byteGBK = str2.getBytes("GBK");
//			for (int i = 0; i < byteGBK.length; i++) {
//				System.out.println(byteGBK[i]);
//			}
//			System.out.println("---------------");
//			byte[] byteUTF8 = str2.getBytes("UTF-8");
//			for (int i = 0; i < byteUTF8.length; i++) {
//				System.out.println(byteUTF8[i]);
//			}
//			
//		}
		String a="";
		String [] b=a.split("##");
		for (String string : b) {
			System.out.println(string+"...");
		}
		System.out.println(b.length+"长度");
		
		
		
	}
	
	
	

}
