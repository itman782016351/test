package com.ideal.test;

/**
 * @author zhaopei
 * @create 2019-04-25 18:40
 */
public class TestResourcePath {
    public static void main(String[] args) {
        String path = TestResourcePath.class.getClassLoader().getResource("").getPath();
        System.out.println("sss");
        System.out.println(path);
    }
}
