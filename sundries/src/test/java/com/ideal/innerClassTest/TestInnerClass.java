package com.ideal.innerClassTest;

import org.junit.Test;

/**
 * @author zhaopei
 * @create 2019-04-10 11:41
 */
public class TestInnerClass {


    @Test
    public void test1() {
        String var1 = "invoke innerClass";
        test2(new InnerInterface() {
            @Override
            public String method1(String var2) {
                System.out.println(var1 + "inner..." + var2);
                return var1 + "haha";
            }
        });
    }

    String test2(InnerInterface innerInterface) {
        return innerInterface.method1("11");
    }
}
