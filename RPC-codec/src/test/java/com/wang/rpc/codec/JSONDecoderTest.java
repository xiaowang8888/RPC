package com.wang.rpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

public class JSONDecoderTest {

    @Test
    public void decode() {
        Encoder encoder = new JSONEncoder();

        TestBean bean = new TestBean();
        bean.setName("xiaowang");
        bean.setAge(18);
        byte[] encode = encoder.encode(bean);

        JSONDecoder jsonDecoder = new JSONDecoder();
        TestBean decode = jsonDecoder.decode(encode, TestBean.class);
        assertEquals(bean.getName(),decode.getName());
    }
}