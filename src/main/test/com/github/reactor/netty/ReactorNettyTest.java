package com.github.reactor.netty;

import com.github.meeting.common.connect.connection.client.ClientLifeStyle;
import com.github.meeting.common.connect.connection.client.ClientToolkit;
import com.github.meeting.common.connect.connection.client.tcp.ReactorTcpClient;
import com.github.meeting.common.connect.model.proto.Account;
import io.netty.handler.logging.LogLevel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.Connection;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpClient;
import reactor.netty.tcp.TcpServer;
import reactor.test.StepVerifier;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * 异步线程处理
 * @author pengpeng
 * @date 2023/1/6
 */
@Slf4j
public class ReactorNettyTest {


    public  static String  HOST = "127.0.0.1";
    public  static Integer  PORT = 8080;
    public static String TCPLogger = "TCPLogger";
    @Test
    public void createServer(){
        DisposableServer server =
            TcpServer.create()
                    .port(PORT)
                    .wiretap("SLF4J",LogLevel.INFO)
                    .handle((inbound, outbound) ->{
                        inbound.receive().subscribe(k-> {
                            log.info("接受到了数据 {}",k.toString(Charset.defaultCharset()));
                        });
                        return outbound.sendString(Mono.just("hello"));
                            })
                    .doOnUnbound(disposableServer -> log.info("端口 {}",disposableServer.address()))
                    .bindNow()
                    ;
//        server.dispose();
        server.onDispose()
                .block();
        log.info("sss");
    }

    @Test
    @SneakyThrows
    public void createClient() {
        ClientLifeStyle connect = ReactorTcpClient.getInstance()
                .config(new InetSocketAddress(HOST, PORT))
                .connect();


        Mono<Void> ssss = ClientToolkit.reactiveClientAction().sendString("ssss");

        Mono<Void> message = ClientToolkit.reactiveClientAction()
                .sendMessage(Account.AccountInfo.newBuilder()
                .setEMail("pengpeng_on@163.com").build());

        ssss.subscribe();

        message.subscribe();

        StepVerifier.create(message)
                .expectComplete()
                ;

        Thread.sleep(100000l);


    }
    @Test
    public  void localClient() {
        TcpClient client =
                TcpClient.create()
                        .host(HOST)
                        .port(PORT)
//                        .wiretap(WIRETAP)
                ;

//            TcpSslContextSpec tcpSslContextSpec =
//                    TcpSslContextSpec.forClient()
//                            .configure(builder -> builder.trustManager(InsecureTrustManagerFactory.INSTANCE));
//            client = client.secure(spec -> spec.sslContext(tcpSslContextSpec));

        Connection connection =
                client.handle((in, out) ->
                                out.send(Flux.concat(ByteBufFlux.fromString(Mono.just("echo")),
                                in.receive().retain())))
                        .connectNow();

        connection.onDispose()
                .block();
    }
}
