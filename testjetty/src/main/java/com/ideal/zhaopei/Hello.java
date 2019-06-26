package com.ideal.zhaopei;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhaopei
 * @create 2019-06-10 14:34
 */
public class Hello extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get...");
        System.out.println(req.getMethod());
        PrintWriter pw = resp.getWriter();
        pw.println("aaaabbb");
        pw.flush();
    }


}
