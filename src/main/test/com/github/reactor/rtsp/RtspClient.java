package com.github.reactor.rtsp;

import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.rtsp.RtspDecoder;
import io.netty.handler.codec.rtsp.RtspEncoder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.tcp.TcpClient;
import reactor.netty.tcp.TcpSslContextSpec;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RtspClient {



    static final boolean SECURE = System.getProperty("secure") != null;
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", SECURE ? "8094" : "8094"));
    static final boolean WIRETAP = System.getProperty("wiretap") != null;

    @Test
    public void client() {

        TcpClient client =
                TcpClient.create()
                        .host(HOST)
                        .port(PORT)
                        .doOnConnected(connection -> {

                            connection.addHandlerLast(new RtspDecoder());

                            connection.addHandlerLast(new RtspEncoder());

                            }
                        )
                        .wiretap(WIRETAP);

        if (SECURE) {
            TcpSslContextSpec tcpSslContextSpec =
                    TcpSslContextSpec.forClient()
                            .configure(builder -> builder.trustManager(InsecureTrustManagerFactory.INSTANCE));
            client = client.secure(spec -> spec.sslContext(tcpSslContextSpec));
        }

        Connection conn = client.connectNow();
        conn.inbound()
                .receive()
                .asString(StandardCharsets.UTF_8)
                .doOnNext(System.out::println)
                .subscribe();

        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
        while (scanner.hasNext()) {
            String text = scanner.nextLine();
            conn.outbound()
                    .sendString(Mono.just(text + "\r\n"))
                    .then()
                    .subscribe();
            if ("bye".equalsIgnoreCase(text)) {
                break;
            }
        }

        conn.onDispose()
                .block();

    }
}
