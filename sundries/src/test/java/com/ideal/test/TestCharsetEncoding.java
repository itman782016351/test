package com.ideal.test;

import java.io.*;

/*
1,javac�������ָ��-encoding �ַ��� ָ�������java�ļ�����ı��롣
����java�ļ��������ΪUTF-8��������ָ�����ַ���ΪGBK�����������
��Ϊ2���ֽ�Ϊ���Ĳ����ɶ�Ӧ�����������ַ���
2��jvm������ַ�������������java��������jvmʱ��ָ����-Dfile.enco
ding����������������ô��ַ�������û�У���Ͳ���ϵͳ��ǰ���ַ���һ�¡�
����windowsϵͳĬ�ϵ��ַ���ΪGBK��jvm�������class�ļ�ʱ���Ὣʶ��
���ַ��������ٰ���jvm�����ַ������б��뱣�����ڴ��У��൱��ת�룩��
3���ı��༭�����ĳ���ַ��������ļ�ʱ���ļ�����Ķ������ֽڲ�һ������
�����ַ�����ĳ���ַ���Ӧ���ֽ����洢�γ��ļ�������ͬ�����ַ���UTF-8��
GBK����ʱ���ļ�����ռ�õ��ֽ�����һ����GBK�ַ�����һ������ռ2��
�ֽڣ�UTF-8�ַ�����һ������ռ��3���ֽڡ�
4���ı��༭���reload with encoding��ָ��ĳ���ַ��������ļ�������
�ı��ļ�������ֽڡ���conver to �ǽ�ͬ�����ַ����ݴ�Դ�ַ�������תΪĿ
���ַ������룬�ַ����ݲ��䣬�����ļ���������ֽڻ�ı䡣
5��jvm��ȡ�ı��ļ���ʱ���Ƕ�ȡ�ļ�������ֽڣ���������jvm�ı����
���ȡ�����ֽڡ����Ե��ļ������jvm�ı��벻һ�£����ǽ������롣
6��String��getBytes("�ַ���")��������ָ�����ַ��������ַ��õ������ַ���
new String(Byte[], ""�ַ���"))����ָ�����ַ��������γ��ַ���
 */
public class TestCharsetEncoding {
    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("file.encoding"));
        String str = "结束";
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
        System.out.println(b.length + "����");
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

