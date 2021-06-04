package com.wang.rpc.client;

import com.wang.rpc.Request;
import com.wang.rpc.Response;
import com.wang.rpc.ServiceDescriptor;
import com.wang.rpc.codec.Decoder;
import com.wang.rpc.codec.Encoder;
import com.wang.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import sun.misc.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * 调用远程服务的代理类
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {

    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);

        Response response = invokeRemote(request);
        if (response == null || response.getCode() != 0) {
            throw new IllegalStateException("fail to invoke remote: " + response);
        }
        return response.getData();
    }

    private Response invokeRemote(Request request) {
        TransportClient client = null;
        Response response = null;
        try {
            client = selector.select();
            byte[] bytes = encoder.encode(request);
            InputStream in = client.write(new ByteArrayInputStream(bytes));
            byte[] inBytes = IOUtils.readFully(in, in.available(), true);
            response = decoder.decode(inBytes, Response.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setCode(-1);
            response.setMessage("RpcClient got error:" + e.getClass() + ":" + e.getMessage());
        } finally {
            if (client != null) {
                selector.release(client);
            }
        }
        return response;
    }
}
