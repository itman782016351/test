package com.ideal.io;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author zhaopei
 * @create 2019-05-06 21:56
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket();
//            s.setSoLinger(true,0);
            s.connect(new InetSocketAddress("127.0.0.1", 3113));

            OutputStream os = s.getOutputStream();
            os.write("hello".getBytes());

            s.close();

            System.in.read();//防止程序退出
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
