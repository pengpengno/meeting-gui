package com.github.meeting.gui.api;

import com.github.meeting.common.model.account.session.SessionInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Component
@Slf4j
public class SessionApi {


//    @Autowired
//    WebClient webClient;

    private final HttpClient httpClient = HttpClient.create();

    public Flux<SessionInfoVo> sessionInfo(Long userId){
        return null;

//        return httpClient
//                .get()
//                .uri(uriBuilder -> uriBuilder
//                        .path(SessionRoute.SESSION_ROUTE)
//                        .queryParam("userId",userId)
//                        .build())
//                .retrieve()
//                .onStatus(HttpStatusCode::isError,
//                    (clientResponse ) ->
//                        clientResponse
//                            .bodyToMono(ProblemDetail.class)
//                            .flatMap(problemDetail ->
//                                Mono.error(()->
//                                    new RuntimeException(problemDetail.getDetail()))))
//                .bodyToFlux(SessionInfoVo.class)
//                .doOnError((throwable)-> {
//                    log.error(ExceptionUtil.stacktraceToString(throwable) );
//                });
    }


    public Mono<SessionInfoVo> sessionInfoBySessionId(Long sessionId){
        return null;
//        return webClient
//                .get()
//                .uri(uriBuilder -> uriBuilder
//                        .path(SessionRoute.SESSION_ROUTE+"/info")
//                        .queryParam("sessionId",sessionId)
//                        .build())
//                .retrieve()
//                .onStatus(HttpStatusCode::isError,
//                    (clientResponse ) ->
//                        clientResponse
//                            .bodyToMono(ProblemDetail.class)
//                            .flatMap(problemDetail ->
//                                Mono.error(()->
//                                    new RuntimeException(problemDetail.getDetail()))))
//                .bodyToMono(SessionInfoVo.class)
//                .doOnError((throwable)-> {
//                    log.error(ExceptionUtil.stacktraceToString(throwable) );
//                });
    }

}
