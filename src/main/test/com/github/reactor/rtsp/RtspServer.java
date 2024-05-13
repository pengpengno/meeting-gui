package com.github.reactor.rtsp;

import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.netty.tcp.TcpServer;
import reactor.netty.tcp.TcpSslContextSpec;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.util.Date;

public class RtspServer {

    static final boolean SECURE = System.getProperty("secure") != null;
    static final int PORT = Integer.parseInt(System.getProperty("port", SECURE ? "8094" : "8094"));
    static final boolean WIRETAP = System.getProperty("wiretap") != null;


    @Test
    @SneakyThrows
    public void server() throws CertificateException, UnknownHostException {
        String hostName = InetAddress.getLocalHost().getHostName();
        TcpServer server =
                TcpServer.create()
                        .port(PORT)
//				         .wiretap(WIRETAP)
                        .wiretap("WIRETAP", LogLevel.INFO)
                        .doOnConnection(connection ->{
                            connection.addHandlerLast(new DelimiterBasedFrameDecoder(8092, Delimiters.lineDelimiter()));
                        })
                        .handle((in, out) -> {
                            Flux<String> welcomeFlux =
                                    Flux.just("Welcome to " + hostName + "!\r\n", "It is " + new Date() + " now.\r\n");

                            Flux<String> responses =
                                    in.receive()
                                            .asString()
                                            // Signals completion when 'bye' is encountered.
                                            // Reactor Netty will perform the necessary clean up, including
                                            // disposing the channel.
				                            .takeUntil("bye"::equalsIgnoreCase)
                                            .map(text -> {
                                                String response = "Did you say '" + text + "'?\r\n";
                                                if (text.isEmpty()) {
                                                    response = "Please type something.\r\n";
                                                }
                                                else if ("bye".equalsIgnoreCase(text)) {
                                                    response = "Have a good day!\r\n";
                                                }

                                                return response;
                                            });

                            return out.sendString(Flux.concat(welcomeFlux, responses));
                        });

        if (SECURE) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            server = server.secure(
                    spec -> spec.sslContext(TcpSslContextSpec.forServer(ssc.certificate(), ssc.privateKey())));
        }

        server.bindNow()
                .onDispose()
                .block();
    }
}
