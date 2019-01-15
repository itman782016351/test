package com.ideal.test1;
public class UnicodeTest {
 
public static void main(String[] args) {
    String cn = "���������ݺ��������";
    System.out.println(cnToUnicode(cn));
    // �ַ��� : \u5f00\u59cb\u4efb\u52a1 ������ \ ��java����ת���ַ���Ҫд������������ʽ
   // String unicode = "\\u5f00\\u59cb\\u4efb\\u52a1";
    String unicode = "\\u000120\\u0001\\u4efb\\u52a1";
    System.out.println(unicodeToCn(unicode));
}
 
private static String unicodeToCn(String unicode) {
    /** �� \ u �ָ��Ϊjavaע��Ҳ��ʶ��unicode������м����һ���ո�*/
    String[] strs = unicode.split("\\\\u");
    String returnStr = "";
    // ����unicode�ַ����� \ u ��ͷ����˷ָ���ĵ�һ���ַ���""��
    for (int i = 1; i < strs.length; i++) {
      returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
    }
    return returnStr;
}
 
private static String cnToUnicode(String cn) {
    char[] chars = cn.toCharArray();
    String returnStr = "";
    for (int i = 0; i < chars.length; i++) {
      returnStr += "\\u" + Integer.toString(chars[i], 16);
    }
    return returnStr;
}
 
}