package com.wang.rpc.server;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceManagerTest {

    ServiceManager sm;

    @Before
    public void init(){
        sm=new ServiceManager();
    }

    @Test
    public void register() {
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class,bean);

    }

    @Test
    public void lookup() {

    }
}