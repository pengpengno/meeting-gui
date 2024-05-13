package com.github.meeting.gui.api;

import com.github.meeting.common.model.account.ChatMsgVo;
import com.github.meeting.common.model.account.chat.PullChatMsgVo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ChatApi {




    public Mono<Void> sendMsg(ChatMsgVo chatMsgVo){
        return null;
//        return webClient
//                .post()
//                .uri(ChatRoute.CHAT_ROUTE)
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(chatMsgVo)
//                .retrieve()
//                .onStatus(HttpStatusCode::isError,
//                    (clientResponse ) ->
//                        clientResponse
//                            .bodyToMono(ProblemDetail.class)
//                            .flatMap(problemDetail ->
//                            Mono.error(()->
//                            new RuntimeException(problemDetail.getDetail()))))
//                .bodyToMono(Void.class)
//                .doOnError((throwable)-> {
//                    log.error(ExceptionUtil.stacktraceToString(throwable) );
//                });
    }


    public Flux<ChatMsgVo> pullMsg(PullChatMsgVo chatMsgVo){
        return null;
//        return webClient
//                .post()
//                .uri(ChatRoute.CHAT_ROUTE+ChatRoute.HISTORY)
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(chatMsgVo)
//                .retrieve()
//                .onStatus(HttpStatusCode::isError,
//                        (clientResponse ) ->
//                            clientResponse
//                                    .bodyToMono(ProblemDetail.class)
//                                    .flatMap(problemDetail ->
//                                        Mono.error(()->
//                                            new RuntimeException(problemDetail.getDetail()))))
//                .bodyToFlux(ChatMsgVo.class)
//                .doOnError((throwable)-> log.error(ExceptionUtil.stacktraceToString(throwable) ));
    }

}
