package com.wang.rpc.example;

import com.wang.rpc.client.RpcClient;

public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);
        int result = service.add(1, 2);
        System.out.println(result);
    }
}
