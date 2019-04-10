package com.ideal.innerClassTest;

import org.junit.Test;

/**
 * @author zhaopei
 * @create 2019-04-10 11:41
 */
public class TestInnerClass {
    String var1 = "invoke innerClass";

    @Test
    public void test1() {

        test2(new InnerInterface() {
            @Override
            public String method1() {
                return va1 + "haha";
            }
        });
    }

    String test2(InnerInterface innerInterface) {
        return innerInterface.method1();
    }
}
