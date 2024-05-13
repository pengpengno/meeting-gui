package com.github.bytecpp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.rtsp.*;
import lombok.Data;
@Data
public class Rtsp {

    private  String host;
    private  int port;

    public Rtsp(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Rtsp() {
    }

    public void run() throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new RtspEncoder(), new RtspDecoder(),
                                new RtspResponseDecoder(), new RtspRequestEncoder(),
                                new SimpleChannelInboundHandler<ByteBuf>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                                        // 处理响应消息
                                        System.out.println(msg.toString());
                                    }
                                });
                    }
                });
        ChannelFuture future = bootstrap.connect(host, port).sync();
        // 构建RTSP请求消息
//        RtspHeaderNames headerNames = new RtspHeaderNames();
//        RtspHeaders headers = new RtspH ();
//        headers.set(RtspHeaderNames.CSEQ, 1);
//        headers.set(RtspHeaderNames.METHOD, RtspMethods.OPTIONS);
        ByteBuf content = Unpooled.EMPTY_BUFFER;

        content.writeBytes("CSeq: 1\r\n".getBytes());

        FullHttpRequest request = new DefaultFullHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.PLAY, "content");
        // 发送请求消息
        future.channel().writeAndFlush(request);
        future.channel().closeFuture().sync();
    }

    public static void main(String[] args) throws Exception {
        Rtsp client = new Rtsp("127.0.0.1", 554);
        client.run();
    }
}
