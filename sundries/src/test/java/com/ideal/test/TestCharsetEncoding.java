package com.ideal.test;

import java.io.*;

/*
1,javac命令可以指定-encoding 字符集 指定编译的java文件本身的编码。
假如java文件本身编码为UTF-8，而编译指定的字符集为GBK，则编译器会
认为2个字节为中文并生成对应的乱码中文字符。
2、jvm本身的字符集决定规则：若java命令启动jvm时候指定了-Dfile.enco
ding的虚拟机参数，则用此字符集。若没有，则和操作系统当前的字符集一致。
例如windows系统默认的字符集为GBK。jvm本身加载class文件时，会将识别
此字符串本身，再按照jvm自身字符集进行编码保存在内存中（相当于转码）。
3、文本编辑软件按某种字符集保存文件时，文件本身的二进制字节不一样，即
按照字符集中某个字符对应的字节来存储形成文件。所以同样的字符用UTF-8和
GBK保存时候，文件本身占用的字节数不一样。GBK字符集中一个中文占2个
字节，UTF-8字符集中一个中文占用3个字节。
4、文本编辑软件reload with encoding是指用某个字符集解码文件本身，不
改变文件本身的字节。而conver to 是将同样的字符内容从源字符集编码转为目
标字符集编码，字符内容不变，但是文件本身保存的字节会改变。
5、jvm读取文本文件的时候，是读取文件本身的字节，并用自身jvm的编码解
码读取到的字节。所以当文件编码和jvm的编码不一致，就是解码乱码。
6、String的getBytes("字符集")方法是用指定的字符集编码字符得到编码字符，
new String(Byte[], ""字符集"))是用指定的字符集解码形成字符。
 */
public class TestCharsetEncoding {
    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("file.encoding"));
        String str = "缁撴潫";
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
        String a = "";
        String[] b = a.split("##");
        for (String string : b) {
            System.out.println(string + "...");
        }
        System.out.println(b.length + "长度");
        File filea = new File("a.txt");
        File fileb = new File("b.txt");

        FileReader fra = new FileReader(filea);
        BufferedReader bra = new BufferedReader(fra);
        String contenta = bra.readLine();
        System.out.println(contenta + "   contenta....");
        FileReader frb = new FileReader(fileb);
        BufferedReader brb = new BufferedReader(frb);
        String contentb = brb.readLine();
        System.out.println(contentb + "   contentb....");
    }
}

