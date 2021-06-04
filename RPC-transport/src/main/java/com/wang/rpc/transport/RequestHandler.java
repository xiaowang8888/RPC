package com.wang.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理请求的Handler
 */
public interface RequestHandler {
    void onRequest(InputStream receive, OutputStream toResp);
}
