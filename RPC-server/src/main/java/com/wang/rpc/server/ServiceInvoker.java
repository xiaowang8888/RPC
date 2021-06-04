package com.wang.rpc.server;

import com.wang.rpc.Request;
import com.wang.rpc.common.utils.ReflectionUtils;

/**
 * 调用具体服务
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request) {
        return ReflectionUtils.invoke(service.getTarget(), service.getMethod(), request.getParameters());
    }
}
