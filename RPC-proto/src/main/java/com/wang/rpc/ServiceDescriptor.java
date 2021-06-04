package com.wang.rpc;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

//表示服务

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    private String clazz;
    private String method;
    private String returnType;
    private String[] parameterTypes;

    public static ServiceDescriptor from(Class clazz, Method method){
        ServiceDescriptor sdp = new ServiceDescriptor();
        sdp.setClazz(clazz.getName());
        sdp.setMethod(method.getName());
        sdp.setReturnType(method.getReturnType().getName());

        Class[] parameterClasses = method.getParameterTypes();
        String[] parametersTypes = new String[parameterClasses.length];
        for (int i=0;i<parameterClasses.length;i++){
            parametersTypes[i]=parameterClasses[i].getName();
        }
        sdp.setParameterTypes(parametersTypes);

        return sdp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceDescriptor that = (ServiceDescriptor) o;
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return "ServiceDescriptor{" +
                "clazz='" + clazz + '\'' +
                ", method='" + method + '\'' +
                ", returnType='" + returnType + '\'' +
                ", paramTypes=" + Arrays.toString(parameterTypes) +
                '}';
    }
}
