package com.ideal.zhaopei;

import org.eclipse.jetty.webapp.WebAppContext;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author zhaopei
 * @create 2019-05-28 10:46
 */
public class TestJetty<T> {
    public static void main(String[] args) throws Exception {
        Map map = new HashMap<Object, Object>();
        Object obj = null;
        map.put("1", 1);
        map.put(obj, 2);
        System.out.println(map.get("1"));
        System.out.println(map.get(null));
        TestJetty testJetty = new TestJetty();
        testJetty.a(testJetty);
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

    public void a(T t) {
//        Class<T> clazz = (Class<T>)( (ParameterizedType) getClass().getGenericInterfaces()[0]);
        Type[] genericInterfaces = getClass().getGenericInterfaces();
//        System.out.println(getClass().getGenericInterfaces());
//        System.out.println(clazz);
    }

}
