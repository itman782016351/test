package com.ideal.zhaopei;

import org.eclipse.jetty.security.SecurityHandler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.UserIdentity;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.IOException;


/**
 * @author zhaopei
 * @create 2019-05-28 10:46
 */
public class TestJetty {
    public static void main(String[] args) throws Exception {
        MyServer server = new MyServer(8080);
        WebAppContext context = new WebAppContext();
        System.out.println(System.getProperty("user.dir"));
        context.setDescriptor(System.getProperty("user.dir") + "/testjetty/src/main/webapp/WEB-INF/web.xml");
        context.setResourceBase(System.getProperty("user.dir") + "/testjetty/src/main/webapp");
        context.setContextPath("/test");
        context.setParentLoaderPriority(true);
        server.setHandler(context);
        server.start();
        server.join();
    }
}
