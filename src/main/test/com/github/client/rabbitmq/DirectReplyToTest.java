package com.github.client.rabbitmq;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;

/**
 * @author pengpeng
 * @description
 * @date 2023/3/27
 */
@Slf4j
//@SpringBootTest(classes = {APP.class})
public class DirectReplyToTest {

//    @Autowired
//    private RabbitTemplate rabbitTemplate;

    @Test
    public void sender(){
//        OnLineUser.UserSearch pengpeng = OnLineUser.UserSearch.newBuilder()
//                .addAccounts(Account.AccountInfo.newBuilder().setAccount("pengpeng").build())
//                .build();
////        OnLineUser.UserSearch userSearch = SessionMapper.INSTANCE.buildSearch(accountInfos);
//        Message message = new Message(pengpeng.toByteArray());
//        Message returnMessage = rabbitTemplate.sendAndReceive(message, new CorrelationData());
//        byte[] body = returnMessage.getBody();
//        try {
//            OnLineUser.UserSearch returnSearch = OnLineUser.UserSearch.parseFrom(body);
//            log.info("发送的数据为 {} ", JSON.toJSONString(returnSearch));
//            List<Account.AccountInfo> accountsList = returnSearch.getAccountsList();
//            List<AccountInfo> accountInfos = ProtoBufMapper.INSTANCE.proto2AccIterable(accountsList);
//            log.info("返回的数据为 {} ", JSON.toJSONString(accountInfos));
//        } catch (InvalidProtocolBufferException e) {
//            throw new RuntimeException("格式异常！");
//        }

    }
}
