package com.wang.rpc.server;

import com.wang.rpc.Request;
import com.wang.rpc.ServiceDescriptor;
import com.wang.rpc.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理rpc暴露的服务
 */
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager(){
        this.services=new ConcurrentHashMap<>();
    }

    //注册服务
    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method : methods) {
            ServiceInstance serviceInstance = new ServiceInstance(bean, method);
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass, method);
            services.put(sdp, serviceInstance);
            log.info("register service: {}:{}", sdp.getClazz(), sdp.getMethod());
        }
    }

    //查找服务
    public ServiceInstance lookup(Request request) {
        ServiceDescriptor sdp = request.getService();
        return services.get(sdp);
    }


}
