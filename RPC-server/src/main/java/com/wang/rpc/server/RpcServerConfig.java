package com.wang.rpc.server;

import com.wang.rpc.codec.Decoder;
import com.wang.rpc.codec.Encoder;
import com.wang.rpc.codec.JSONDecoder;
import com.wang.rpc.codec.JSONEncoder;
import com.wang.rpc.transport.HttpTransportServer;
import com.wang.rpc.transport.TransportServer;
import lombok.Data;

/**
 * server配置
 */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass= HttpTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3000;
}
