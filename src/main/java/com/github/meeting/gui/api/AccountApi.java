package com.github.meeting.gui.api;


import com.github.meeting.common.connect.model.proto.Account;
import com.github.meeting.common.model.account.AccountAuthenticateVo;
import com.github.meeting.common.model.account.AccountVo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author pengpeng
 * @description
 * @date 2023/3/3
 */
@Component
@Slf4j
public class AccountApi {


//
//    @Autowired
//    public WebClient webClient;

    public Mono<AccountAuthenticateVo> auth (AccountVo accountVo) {
        return null;
//       return webClient
//                .post()
//                .uri(AccRoute.ACCOUNT_ROUTE+AccRoute.AUTH_POST)
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(accountVo)
//                .retrieve()
//                .onStatus(HttpStatusCode::isError,
//                    (clientResponse ) ->
//                        clientResponse
//                            .bodyToMono(ProblemDetail.class)
//                            .flatMap(problemDetail ->
//                                Mono.error(()->
//                                    new RuntimeException(problemDetail.getDetail()))))
//                .bodyToMono(AccountAuthenticateVo.class)
//                .doOnError((throwable)-> {
//                    log.error( throwable.getMessage());
//                });
    }


    /***
     * 登录 Api
     * @param accountVo
     * @return
     */
    public Mono<Account.AccountInfo> login (AccountVo accountVo) {
        return null;
//        return webClient
//                .post()
//                .uri(AccRoute.ACCOUNT_ROUTE+AccRoute.LOGIN)
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(accountVo)
//                .retrieve()
//                .onStatus(HttpStatusCode::isError,
//                        (clientResponse ) ->
//                        clientResponse
//                        .bodyToMono(ProblemDetail.class)
//                        .flatMap(problemDetail ->
//                        Mono.error(()->
//                        new RuntimeException(problemDetail.getDetail()))))
//                .bodyToMono(AccountInfo.class)
//                .doOnError((throwable)-> {
//                    log.error( throwable.getMessage());
//                });
    }



}
