package com.wang.rpc.client;

import com.wang.rpc.Peer;
import com.wang.rpc.codec.Decoder;
import com.wang.rpc.codec.Encoder;
import com.wang.rpc.codec.JSONDecoder;
import com.wang.rpc.codec.JSONEncoder;
import com.wang.rpc.transport.HttpTransportClient;
import com.wang.rpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HttpTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1", 3000));
}
