package com.wang.rpc.common.utils;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ReflectionUtilsTest {

    @Test
    public void newInstance() {
        TestClass testClass = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(testClass);
    }

    @Test
    public void getPublicMehods() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        assertEquals(1,methods.length);

        String name = methods[0].getName();
        assertEquals("a",name);
    }

    @Test
    public void invoke() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        Method m = methods[0];

        TestClass testClass = new TestClass();
        Object invoke = ReflectionUtils.invoke(testClass, m);
        assertEquals("a",invoke);
    }
}