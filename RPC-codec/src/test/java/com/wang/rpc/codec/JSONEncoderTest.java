package com.wang.rpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

public class JSONEncoderTest {

    @Test
    public void encode() {
        Encoder encoder = new JSONEncoder();

        TestBean bean = new TestBean();
        bean.setName("xiaowang");
        bean.setAge(18);
        byte[] encode = encoder.encode(bean);
        assertNotNull(encode);
    }
}