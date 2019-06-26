package com.ideal.zhaopei;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhaopei
 * @create 2019-06-11 10:42
 */
public class MyServer extends Server {

    public MyServer(int i) {
        super(i);
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (_handler != null && isStarted()) {
            if ("TRACE".equals(request.getMethod())) {
                System.out.println("被拦截了。。。。");
                // response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            } else {
                System.out.println("正常处理请求");
                _handler.handle(target, baseRequest, request, response);
            }
        }
    }
}
